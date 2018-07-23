package com.goldfish.service.impl;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Date;
import java.util.concurrent.ThreadPoolExecutor;

import com.goldfish.common.log.LogTypeEnum;
import com.goldfish.concurrent.threadpool.ThreadConstant;
import com.goldfish.concurrent.threadpool.ThreadPoolContext;
import com.goldfish.constant.*;
import com.goldfish.dao.cache.local.CourseContext;
import com.goldfish.dao.cache.local.UnitWordContext;
import com.goldfish.domain.*;
import com.goldfish.manager.*;
import com.goldfish.vo.BaseVO;
import com.goldfish.vo.BasicVO;
import com.goldfish.vo.CurrentStudyPositionVO;
import com.goldfish.vo.course.BookStudyVO;
import com.goldfish.vo.unit.*;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;
import com.goldfish.common.PageQuery;
import com.goldfish.common.CommonResult;
import com.goldfish.service.UnitStudyService;


/**
 * @author hellosscat
 * @since 2018-5-8
 *<p>  单元单词学习service实现</p>
 *
 */
@Service("unitStudyService")
public class UnitStudyServiceImpl implements UnitStudyService,DisposableBean {

	private static final Logger logger = Logger.getLogger(UnitStudyServiceImpl.class);
	
	@Resource(name= "unitStudyManager")
	private UnitStudyManager unitStudyManager;
	@Resource
	private WordStudyManager wordStudyManager;
	@Resource
	private SelfWordsManager selfWordsManager;
	@Resource
	private CourseContext courseContext;
	@Resource
	private UnitWordContext unitWordContext;
	@Resource
    private CourseStudyManager courseStudyManager;
	@Resource
	private WordManager wordManager;
	private ThreadPoolExecutor threadPool = ThreadPoolContext.getThreadPool(ThreadConstant.Course_Learning, false);


	@Override
	public SaveUnitStudyVO doAjaxInterruptSaveUnit(Integer userId, SaveUnitStudyVO saveUnitStudyVO, String moduleCode, String extra, Integer unitNbr, String studyToken, Integer isContinue, Long seconds4SpellingLetter, Long totalReadingTime, Long totalWritingTime, List<WordStudyDto> vocDataAfterReview, Integer totalWordsNbr) {
		// 1.保存单元学习记录
		UnitStudy unitStudy = getUnitStudy(userId, moduleCode, unitNbr, saveUnitStudyVO);
		if (unitStudy == null) {
			return saveUnitStudyVO;
		}
		try{
			// 1.保存每个单词的学习情况
			WordStudy lastStudyWord = new WordStudy();
			for (WordStudyDto dto : vocDataAfterReview) {
				WordStudy wordStudy = updateWordStudy(dto,userId);
				doErrorWord(userId, moduleCode, unitNbr, dto);
				lastStudyWord = wordStudy;
			}
			// 2.更新单元学习
			unitStudy.setStudyPositionCode(studyToken);
			// 1.校验是否存在未记住的单词
			WordStudy queryWord = new WordStudy();
			queryWord.setStudentId(userId);
			queryWord.setLessonId(unitStudy.getLessonId());
			queryWord.setUnitNbr(unitNbr);
			queryWord.setIsRemember(State.NO.getState());
			queryWord.setState(State.VALID.getState());
			WordStudy notRememberWord = wordStudyManager.getUnique(queryWord);
			// 全部学习完毕
			if (notRememberWord == null) {
				/**  是否学习完成  */
				unitStudy.setIsFinished(FinishState.COMPLETE.getState());
				if (unitStudy.getIsTested() >= FinishState.COMPLETE.getState()) {
					unitStudy.setIsAllFinished(FinishState.COMPLETE.getState());
				}
				// 单元学习完毕，则到单元测试阶段
				unitStudy.setCurrentPhase(StudyPhase.UNIT_TEST.getPhase());
			}
			// 更新当前待学习单词
			else {
				unitStudy.setVocCode(notRememberWord.getVocCode());
				unitStudy.setSpelling(notRememberWord.getSpell());
			}
			// a.更新学习时间等字段
			unitStudy.setTotalReadingTime(totalReadingTime);
			unitStudy.setTotalWritingTime(totalWritingTime);
			unitStudy.setTotalNumber(totalWordsNbr);

			/**  位置类型  */
			unitStudy.setPositionType(1);// 1表示单词
			/**  当前保存，则为当前学习位置，当前写是，同时更新该学生该课程其他单元为false  */
			unitStudy.setIsCurrentPos(1);// 当前保存，则为当前位置

			CommonResult<UnitStudy> updateUnitStudyResult = this.updateUnitWordsStudy(unitStudy);
			if (updateUnitStudyResult == null || !updateUnitStudyResult.isSuccess()) {
				LogTypeEnum.DEFAULT.error("更新学生单元学习失败");
				saveUnitStudyVO.setMsg("更新学生单元学习失败");
				return saveUnitStudyVO;
			}
			// 更新其他单元为非当前学习单元
			unitStudyManager.otherUnitNotCurStudyPosition(unitStudy);
			saveUnitStudyVO.setLatestStudyPosition(fillLastPostion(unitStudy, studyToken, lastStudyWord, seconds4SpellingLetter));
			saveUnitStudyVO.setSuccess(true);
			saveCourseStudySchedule(userId,unitStudy.getLessonId(),unitNbr,lastStudyWord.getVocCode(),lastStudyWord.getSpell());
		} catch (Exception e) {
			LogTypeEnum.DEFAULT.error(e, "保存单元学习异常");
			saveUnitStudyVO.setMsg("保存单元学习异常");
			saveUnitStudyVO.setSuccess(false);
		}
		return saveUnitStudyVO;
	}

	@Override
	public RichUnitStudyVO getUnit(Integer userId, String moduleCode, Integer unit, RichUnitStudyVO richUnitStudyVO) {
		// 1.获取单元学习信息
		UnitStudy unitStudy = this.getUnitStudy(userId, moduleCode, unit, richUnitStudyVO);
		if (unitStudy == null) {
			return richUnitStudyVO;
		}
		// 2.填充课本信息
		Course course = this.getCourseInfo(unitStudy.getLessonId(), richUnitStudyVO);
		if (course == null) {
			return richUnitStudyVO;
		}
		if (!fullfillCourseStudyInfo(userId, richUnitStudyVO, unitStudy, course)){
			return richUnitStudyVO;
		}
		// 3.填充单词学习
		List<WordStudy> studyWords = this.getWordStudy(userId, course.getBookNumber(), unitStudy.getUnitNbr(), richUnitStudyVO);
		if (studyWords == null || studyWords.isEmpty()) {
			return richUnitStudyVO;
		}
		fullfillWordsInfo(richUnitStudyVO, unitStudy, studyWords);
		return richUnitStudyVO;
	}

	private void fullfillWordsInfo(RichUnitStudyVO richUnitStudyVO, final UnitStudy unitStudy, List<WordStudy> studyWords) {
		// 遍历，封装单元内单词学习情况
		List<UnitStudyVO> wordStudyVOs = new ArrayList<UnitStudyVO>(studyWords.size());
		richUnitStudyVO.setData(wordStudyVOs);
		richUnitStudyVO.setSuccess(true);
		for (WordStudy wordStudy : studyWords) {
			UnitStudyVO unitStudyVO = new UnitStudyVO();
			wordStudyVOs.add(unitStudyVO);
			unitStudyVO.setVocCode(wordStudy.getVocCode());
			unitStudyVO.setSpelling(wordStudy.getSpell());
			unitStudyVO.setMeaning(wordStudy.getMeaning());
			unitStudyVO.setUnitId(0);
			unitStudyVO.setUnitNbr(wordStudy.getUnitNbr());
			unitStudyVO.setLessonNbr(wordStudy.getLessonId());
			// 查询是否被收藏
			unitStudyVO.setIsCollected(wordStudy.getIscollected() == 1);

			// 通过word查询音标信息
			Word wordQuery = new Word();
			wordQuery.setId(wordStudy.getWordId());
			wordQuery.setState(State.VALID.getState());
			Word word = wordManager.getUnique(wordQuery);
			unitStudyVO.setSoundMarkUs(word.getSoundMarkUs());
			unitStudyVO.setSoundMarkUk(word.getSoundMarkUk());

			UnitWords unitWord = unitWordContext.getUnitWord(wordStudy.getVocCode());
			// 通过unitWord获取
			unitStudyVO.setId(unitWord.getId());
			unitStudyVO.setFstClassId(unitWord.getFstClassId());
			unitStudyVO.setVocIndex(unitWord.getVocIndex());
			// 更新单元学习状态
			threadPool.execute(new Runnable() {
				@Override
				public void run() {
					if (unitStudy.getIsFinished() == FinishState.NOT_START.getState()) {
						// 单元学习状态为开始，则更新为未完成
						unitStudy.setIsFinished(FinishState.NOT_COMPLETE.getState());
						unitStudyManager.updateUnitWordsStudy(unitStudy);
					}
				}
			});
		}
		wordStudyVOs.sort(new Comparator<UnitStudyVO>() {
			@Override
			public int compare(UnitStudyVO o1, UnitStudyVO o2) {
				return o1.getVocIndex()-o2.getVocIndex();
			}
		});
	}

	private boolean fullfillCourseStudyInfo(Integer userId, RichUnitStudyVO richUnitStudyVO, UnitStudy unitStudy, Course course) {
		BookStudyVO bookStudyVO = new BookStudyVO();
		richUnitStudyVO.setBookInfo(bookStudyVO);
		bookStudyVO.setId(course.getBookNumber());
		bookStudyVO.setModuleCode(course.getModuleCode());
		bookStudyVO.setBookName(course.getBookName());
		bookStudyVO.setCoverImageUrl(course.getCoverImageUrl());
		bookStudyVO.setTotalUnitNbr(course.getTotalUnitNbr());
		bookStudyVO.setOutDate(course.isOutDate());
		// 4.根据用户ID查询用户课程信息
		CourseStudy courseStudy = this.getCourseStudy(userId, course.getBookNumber(), richUnitStudyVO);
		if (courseStudy == null) {
			return false;
		}
		bookStudyVO.setStartFrom(courseStudy.getStartFrom());
		bookStudyVO.setStudyMode(courseStudy.getStudyMode());

		// 5.填充学习位置
		/*设置当前单元内学习位置信息*/
		CurrentStudyPositionVO currentPositionVO = new CurrentStudyPositionVO();
		if (unitStudy.getIsAllFinished() != FinishState.COMPLETE.getState()) {
			currentPositionVO = this.getCouseStudyPositionVo(PositionType.UNIT_STUDY_POSTION, null, unitStudy, richUnitStudyVO);
			if (currentPositionVO == null) {
				return false;
			}
		}
		richUnitStudyVO.setStudyPos(currentPositionVO);
		return true;
	}


	private UnitStudy getUnitStudy(Integer userId, String moduleCode, Integer unitNbr, RichUnitStudyVO richUnitStudyVO) {

		UnitStudy unitStudyQuery = new UnitStudy();
		unitStudyQuery.setStudentId(userId);
		unitStudyQuery.setLessonCode(moduleCode);
		unitStudyQuery.setUnitNbr(unitNbr);
		unitStudyQuery.setState(State.VALID.getState());
		CommonResult<UnitStudy> unitStudyResult = this.getUnique(unitStudyQuery);
		if (unitStudyResult == null || !unitStudyResult.isSuccess()) {
			LogTypeEnum.DEFAULT.error("获取学生单元学习情况异常");
			richUnitStudyVO.setMsg("获取学生单元学习情况异常");
			return null;
		}

		UnitStudy unitStudy = unitStudyResult.getDefaultModel();
		if (unitStudy == null) {
			LogTypeEnum.DEFAULT.info("不存在该学生单元学习记录");
			richUnitStudyVO.setMsg("不存在该学生单元学习记录");
			return null;
		}
		return unitStudy;
	}

	private Course getCourseInfo(Integer lessonId, BaseVO baseVO) {
		Course course = courseContext.getCourse(lessonId);
		if (course == null) {
			LogTypeEnum.DEFAULT.error("找不到这门课，lessonId={}", lessonId);
			baseVO.setSuccess(true);
			baseVO.setMsg("找不到这门课");
			return null;
		}
		return course;
	}


	/**
	 * 获取课程学习
	 *
	 * @param studentId
	 * @param lessonId
	 * @param baseVO
	 * @return
	 */
	private CourseStudy getCourseStudy(Integer studentId, Integer lessonId, BaseVO baseVO) {
		CourseStudy courseStudyQuery = new CourseStudy();
		courseStudyQuery.setLessonId(lessonId);
		courseStudyQuery.setStudentId(studentId);
		courseStudyQuery.setStatus(State.VALID.getState());
		courseStudyQuery.setCurrentStudyBook(null);
		CourseStudy studentCourse = courseStudyManager.getUnique(courseStudyQuery);
		if (studentCourse == null) {
			LogTypeEnum.DEFAULT.info("学生关联课程为空");
			baseVO.setMsg("学生未关联课程");
			baseVO.setSuccess(true);
			return null;
		}
		return studentCourse;
	}

	protected WordStudy updateWordStudy(WordStudyDto dto,Integer userId) {
	    WordStudy wordStudyQuery = new WordStudy();
	    wordStudyQuery.setVocCode(dto.getVocCode());
	    wordStudyQuery.setStudentId(userId);
        wordStudyQuery.setState(State.VALID.getState());
		WordStudy wordStudy = wordStudyManager.getUnique(wordStudyQuery);
		wordStudy.setMemoryLevel(dto.getMemoryLevel());
		wordStudy.setTimeLeft(dto.getTimeLeft());
		wordStudy.setUserVocCode(dto.getUserVocCode());
		wordStudy.setUserCode(dto.getUserCode());
		wordStudy.setFinishReadingTime((long) (int) (dto.getFinishReadingTime() * 1000));
		wordStudy.setIsFstReadSuccess(State.getBoolInt().get(dto.isFstReadSuccess()));
		wordStudy.setReadFailTimes(dto.getReadFailTimes());
		wordStudy.setContinueReadFailTimes(dto.getContinueReadFailTimes());
		wordStudy.setIsHalfReading(State.getBoolInt().get(dto.isHalfReading()));
		wordStudy.setIsFstSpellSuccess(State.getBoolInt().get(dto.isFstSpellSuccess()));
		wordStudy.setSpellFailTimes(dto.getSpellFailTimes());
		wordStudy.setContinueSpellFailTimes(dto.getContinueSpellFailTimes());
		wordStudy.setIsHalfSpelling(State.getBoolInt().get(dto.isHalfSpelling()));
		wordStudy.setIsRemember(State.getBoolInt().get(dto.isRemember()));
		wordStudy.setIsCancelReview(State.getBoolInt().get(dto.isCancelReview()));
		wordStudy.setVocCode(dto.getVocCode());
		Date cur = new Date();
		wordStudy.setModified(cur);
		wordStudy.setStudied(cur);
		wordStudyManager.updateWordStudy(wordStudy);
		return wordStudy;
	}

	private LastStudyPositonVO fillLastPostion(UnitStudy unitStudy, String studyToken, WordStudy lastStudyWord, Long seconds4SpellingLetter) {
		LastStudyPositonVO vo = new LastStudyPositonVO();
		vo.setId(unitStudy.getId());
		vo.setStudyPositionCode(unitStudy.getStudyPositionCode());
		vo.setStudytoken(studyToken);
		vo.setModuleCode(unitStudy.getLessonCode());
		vo.setPositionType(unitStudy.getPositionType());
		vo.setRemark(null);
		vo.setVocCode(unitStudy.getVocCode());
		vo.setTotalReadingTime(unitStudy.getTotalReadingTime());
		vo.setTotalWritingTime(unitStudy.getTotalWritingTime());
		vo.setUserCode(lastStudyWord.getUserCode());
		vo.setUnitNbr(unitStudy.getUnitNbr());
		vo.setWordCount(unitStudy.getTotalNumber());
		vo.setIsContinue(false);
		vo.setSeconds4SpellingLetter(seconds4SpellingLetter);
		vo.setIsCurrentPos(unitStudy.getIsCurrentPos() == 1);
		vo.setIsFinished(unitStudy.getIsFinished() + 1);// 1-->2; 0-->1
		vo.setSpelling(unitStudy.getSpelling());
		vo.setIsAllFinished(unitStudy.getIsAllFinished() == 1);
		vo.setCreatetime(unitStudy.getCreated());
		vo.setIsTested(unitStudy.getIsTested());
		vo.setStatus(unitStudy.getStatus());
		return vo;
	}

	private CurrentStudyPositionVO getCouseStudyPositionVo(PositionType positionType,
														   CourseStudy courseStudy,
														   UnitStudy unitStudy,
														   BaseVO baseVO) {
		CurrentStudyPositionVO currentPositionVO = new CurrentStudyPositionVO();
		StudyPosition studyPosition = null;
		if (PositionType.COURSE_STUDY_POSITION == positionType) {
			currentPositionVO.setId(courseStudy.getId());
			studyPosition = courseStudy;
		} else {
			currentPositionVO.setId(unitStudy.getId());
			studyPosition = unitStudy;
		}
		currentPositionVO.setStudyPositionCode(studyPosition.getStudyPositionCode());
		currentPositionVO.setPositionType(studyPosition.getPositionType());
		currentPositionVO.setVocCode(studyPosition.getVocCode());

		// 查询课程单词获取spell
		UnitWords unitWordsQuery = new UnitWords();
		unitWordsQuery.setVocCode(studyPosition.getVocCode());
		unitWordsQuery.setState(State.VALID.getState());
		currentPositionVO.setSpelling(studyPosition.getSpelling());
		currentPositionVO.setUnitNbr(studyPosition.getUnitNbr());
		currentPositionVO.setIsCurrentPos(State.getMap().get(studyPosition.getIsCurrentPos()).getResult());
		currentPositionVO.setIsFinished(studyPosition.getIsFinished());
		currentPositionVO.setIsAllFinished(State.getMap().get(studyPosition.getIsAllFinished()).getResult());
		currentPositionVO.setIsTested(studyPosition.getIsTested());
		currentPositionVO.setStatus(studyPosition.getStatus() - 1);// 原因：VO:0表示有效，DB:1表示有效
		return currentPositionVO;
	}


	@Override
	public SaveFinishUnitStudyVO doAjaxFinishSaveUnit(Integer studentId, SaveFinishUnitStudyVO saveFinishUnitStudyVO,
			String moduleCode, String extra, Integer unitNbr, String studyToken,
		  Integer isContinue, Long seconds4SpellingLetter, Long totalReadingTime, Long totalWritingTime, List<WordStudyDto> vocDataAfterReview, Integer totalWordsNbr) {
		// 1.保存单元学习记录
		UnitStudy unitStudy = getUnitStudy(studentId, moduleCode, unitNbr, saveFinishUnitStudyVO);
		if (unitStudy == null) {
			return saveFinishUnitStudyVO;
		}
		try{
			// 1.保存每个单词的学习情况
			WordStudy lastStudyWord = null;
			for (WordStudyDto dto : vocDataAfterReview) {
				WordStudy wordStudy = updateWordStudy(dto,studentId);
				doErrorWord(studentId, moduleCode, unitNbr, dto);
				lastStudyWord = wordStudy;
			}
			// 2.更新单元学习
			unitStudy.setStudyPositionCode(studyToken);
			// 1.校验是否存在未记住的单词
			WordStudy queryWord = new WordStudy();
			queryWord.setStudentId(studentId);
			queryWord.setLessonId(unitStudy.getLessonId());
			queryWord.setUnitNbr(unitNbr);
			queryWord.setIsRemember(State.NO.getState());
			queryWord.setState(State.VALID.getState());
			WordStudy notRememberWord = wordStudyManager.getUnique(queryWord);
			// 全部学习完毕
			if (notRememberWord == null) {
				/**  是否学习完成  */
				unitStudy.setIsFinished(FinishState.COMPLETE.getState());
				if (unitStudy.getIsTested() >= FinishState.COMPLETE.getState()) {
					unitStudy.setIsAllFinished(FinishState.COMPLETE.getState());
				}
				// 单元学习完毕，则到单元测试阶段
				unitStudy.setCurrentPhase(StudyPhase.UNIT_TEST.getPhase());
			}
			// 更新当前待学习单词
			else {
				unitStudy.setVocCode(notRememberWord.getVocCode());
				unitStudy.setSpelling(notRememberWord.getSpell());
			}
			// a.更新学习时间等字段
			unitStudy.setTotalReadingTime(totalReadingTime);
			unitStudy.setTotalWritingTime(totalWritingTime);
			unitStudy.setTotalNumber(totalWordsNbr);

			/**  位置类型  */
			unitStudy.setPositionType(1);// 1表示单词
			/**  当前保存，则为当前学习位置，当前写是，同时更新该学生该课程其他单元为false  */
			unitStudy.setIsCurrentPos(1);// 当前保存，则为当前位置

			CommonResult<UnitStudy> updateUnitStudyResult = this.updateUnitWordsStudy(unitStudy);
			if (updateUnitStudyResult == null || !updateUnitStudyResult.isSuccess()) {
				LogTypeEnum.DEFAULT.error("更新学生单元学习失败");
				saveFinishUnitStudyVO.setMsg("更新学生单元学习失败");
				return saveFinishUnitStudyVO;
			}
			// 更新其他单元为非当前学习单元
			unitStudyManager.otherUnitNotCurStudyPosition(unitStudy);
			saveFinishUnitStudyVO.setSuccess(true);
            saveCourseStudySchedule(studentId,unitStudy.getLessonId(),unitNbr,lastStudyWord.getVocCode(),lastStudyWord.getSpell());
		} catch (Exception e) {
			LogTypeEnum.DEFAULT.error(e, "保存单元学习异常");
			saveFinishUnitStudyVO.setMsg("保存单元学习异常");
			saveFinishUnitStudyVO.setSuccess(false);
		}
		saveFinishUnitStudyVO.setLatestStudyPosition(fillLastPostion(vocDataAfterReview));
		return saveFinishUnitStudyVO;
	}

	protected void doErrorWord(Integer studentId, String moduleCode, Integer unitNbr, WordStudyDto dto) {

		try {
			// 若为错词，则添加到错词本
			Integer readFailTimes = dto.getReadFailTimes();
			Integer spellFailTimes = dto.getSpellFailTimes();
			Integer continueReadFailTimes = dto.getContinueReadFailTimes();
			Integer continueSpellFailTimes = dto.getContinueSpellFailTimes();
			if (readFailTimes > 0 || spellFailTimes > 0 || continueReadFailTimes > 0 || continueSpellFailTimes > 0) {
                LogTypeEnum.DEFAULT.info("add to error book, vocCode={},userId={}", dto.getVocCode(), studentId);
                SelfWords errWord = new SelfWords();
                errWord.setModuleCode(moduleCode);
                errWord.setStudentId(Long.valueOf(String.valueOf(studentId)));
                errWord.setUserCode(dto.getUserCode());
                errWord.setType(WordLibType.ERROR_BOOK.getType());
                errWord.setLessonId(courseContext.getLessonIdByCode(moduleCode));
                errWord.setUnitNbr(unitNbr);

                errWord.setVocCode(dto.getVocCode());
                UnitWords unitWord = unitWordContext.getUnitWord(dto.getVocCode());
                errWord.setWordId(unitWord.getWordId());
                errWord.setSpelling(unitWord.getSpelling());
                errWord.setState(State.VALID.getState());
                Date current = new Date();
                errWord.setCreated(current);
                errWord.setModified(current);
                selfWordsManager.addSelfWords(errWord);
            }
		} catch (Exception e) {
			LogTypeEnum.DEFAULT.error(e, "插入错词本异常");
		}
	}

	private List<WordStudyVO> fillLastPostion(List<WordStudyDto> src) {
		if (src == null || src.isEmpty()) {
			return null;
		}
		List<WordStudyVO> target = new ArrayList<WordStudyVO>(src.size());
		for (WordStudyDto dto : src) {
			WordStudyVO wordStudyVO = new WordStudyVO();
			wordStudyVO.setUserCode(dto.getUserCode());
			wordStudyVO.setVocCode(dto.getVocCode());
			wordStudyVO.setFinishReadingTime(dto.getFinishReadingTime());
			wordStudyVO.setIsFstReadSuccess(dto.isFstReadSuccess());
			wordStudyVO.setReadFailTimes(dto.getReadFailTimes());
			wordStudyVO.setContinueReadFailTimes(dto.getContinueReadFailTimes());
			wordStudyVO.setIsFstSpellSuccess(dto.isFstSpellSuccess());
			wordStudyVO.setSpellFailTimes(dto.getSpellFailTimes());
			wordStudyVO.setContinueSpellFailTimes(dto.getContinueSpellFailTimes());
			wordStudyVO.setIsHalfReading(dto.isHalfReading());
			wordStudyVO.setIsHalfSpelling(dto.isHalfSpelling());
			wordStudyVO.setRemark(null);
			wordStudyVO.setStartTime(null);
			wordStudyVO.setLastReviewTime(new Date());
			wordStudyVO.setLastComputeTime(null);
			wordStudyVO.setIsRemember(dto.isRemember());
			wordStudyVO.setIsCancelReview(dto.isCancelReview());
			wordStudyVO.setCreateTime(null);
			wordStudyVO.setReviewTimes(null);
			wordStudyVO.setMemoryLevel(dto.getMemoryLevel());
			wordStudyVO.setTimeLeft(dto.getTimeLeft());
			wordStudyVO.setStatus(null);
			target.add(wordStudyVO);
		}
		return target;
	}


	private UnitStudy getUnitStudy(Integer studentId, String moduleCode, Integer unitNbr, BasicVO vo) {
		UnitStudy unitStudyQuery = new UnitStudy();
		unitStudyQuery.setStudentId(studentId);
		unitStudyQuery.setLessonCode(moduleCode);
		unitStudyQuery.setUnitNbr(unitNbr);
		unitStudyQuery.setState(State.VALID.getState());
		CommonResult<UnitStudy> unitStudyResult = this.getUnique(unitStudyQuery);
		if (unitStudyResult == null || !unitStudyResult.isSuccess()) {
			LogTypeEnum.DEFAULT.error("查询单元学习失败");
			vo.setMsg("查询单元学习失败");
			return null;
		}
		UnitStudy unitStudy = unitStudyResult.getDefaultModel();
		if (unitStudy == null) {
			LogTypeEnum.DEFAULT.error("学生单元学习不存在");
			vo.setMsg("学生单元学习不存在");
			vo.setSuccess(true);
			return null;
		}
		return unitStudy;
	}

	@Override
    public CommonResult<UnitStudy> addUnitWordsStudy(UnitStudy unitStudy) {
		CommonResult<UnitStudy> result = new CommonResult<UnitStudy>();
		try{
			
			unitStudy.setCreated(new Date());
			
			
			unitStudy.setModified(new Date());
			
			result.addDefaultModel(unitStudyManager.addUnitWordsStudy(unitStudy));
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("添加 单元单词学习失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	
	@Override
    public CommonResult<UnitStudy> updateUnitWordsStudy(UnitStudy unitStudy) {
		CommonResult<UnitStudy> result = new CommonResult<UnitStudy>();
		try {
			
					unitStudy.setModified(new Date());
					
			unitStudyManager.updateUnitWordsStudy(unitStudy);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("更新 单元单词学习失败", e);
			result.setSuccess(false);
		}
		return result;
	}


	@Override
    public CommonResult<UnitStudy> deleteUnitWordsStudy(Long id) {
		CommonResult<UnitStudy> result = new CommonResult<UnitStudy>();
		try {
			unitStudyManager.deleteUnitWordsStudy(id);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("删除 单元单词学习失败", e);
			result.setSuccess(false);
		}
		return result;
    }


    	@Override
        public CommonResult<UnitStudy> getUnitWordsStudyById(Long id) {
		CommonResult<UnitStudy> result = new CommonResult<UnitStudy>();
		try {
			result.addDefaultModel("unitWordsStudy", unitStudyManager.getUnitWordsStudyById(id));
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("根据主键获取 单元单词学习失败", e);
			result.setSuccess(false);
		}
		return result;
	}

	@Override
    public CommonResult<UnitStudy> getUnique(UnitStudy unitStudy) {
		CommonResult<UnitStudy> result = new CommonResult<UnitStudy>();
		try {
			result.addDefaultModel(unitStudyManager.getUnique(unitStudy));
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("根据example获取唯一 单元单词学习失败", e);
			result.setSuccess(false);
		}
		return result;
	}


	@Override
    public CommonResult<List<UnitStudy>> getListByExample(UnitStudy unitStudy) {
		CommonResult<List<UnitStudy>> result = new CommonResult<List<UnitStudy>>();
		try {
			List<UnitStudy> list = unitStudyManager.getListByExample(unitStudy);
			result.addDefaultModel("list", list);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("取得 单元单词学习失败", e);
			result.setSuccess(false);
		}
		return result;
	}

	
	@Override
    public CommonResult<List<UnitStudy>> getUnitWordsStudyByPage(PageQuery pageQuery) {
		CommonResult<List<UnitStudy>> result = new CommonResult<List<UnitStudy>>();
		try {
			int totalCount = this.count(pageQuery);
			if (totalCount > 0) {
				pageQuery.setTotalCount(totalCount);
				List<UnitStudy> list = unitStudyManager.getUnitWordsStudyByPage(pageQuery);
				result.addDefaultModel("list", list);
				result.addModel("pageQuery", pageQuery);
			}
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("分页获取 单元单词学习失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	
	@Override
    public int count(PageQuery pageQuery) {
		return unitStudyManager.count(pageQuery);
	}




	/******* getter and setter ***/
	public UnitStudyManager getUnitStudyManager() {
		return unitStudyManager;
	}

	public void setUnitStudyManager(UnitStudyManager unitStudyManager) {
		this.unitStudyManager = unitStudyManager;
	}

	@Override
    public int sumReading(PageQuery pageQuery){
		return unitStudyManager.sumReading(pageQuery);
	}

	@Override
    public int sumWriting(PageQuery pageQuery)
	{
		return unitStudyManager.sumWriting(pageQuery);
	}

    /**
     * 保存课程学习进度
     */
    private void saveCourseStudySchedule(int studentID,int lessonID,int unitNbr,String vocCode,String spelling)
    {
        CourseStudy courseStudyQuery = new CourseStudy();
        courseStudyQuery.setStudentId(studentID);
        courseStudyQuery.setLessonId(lessonID);
        CourseStudy courseStudy = courseStudyManager.getUnique(courseStudyQuery);
        courseStudy.setUnitNbr(unitNbr);
        courseStudy.setVocCode(vocCode);
        courseStudy.setSpelling(spelling);
        courseStudyManager.updateCourseStudy(courseStudy);
    }

	public List<WordStudy> getWordStudy(Integer userId, Integer lessonId, Integer unitNbr, RichUnitStudyVO richUnitStudyVO) {
		PageQuery pageQuery = new PageQuery(0, 1000000);
		pageQuery.addQueryParam("studentId", userId);
		pageQuery.addQueryParam("lessonId", lessonId);
		pageQuery.addQueryParam("unitNbr", unitNbr);
		pageQuery.addQueryParam("state", State.VALID.getState());
		pageQuery.addQueryParam("isRemember", State.NO.getState());
		// 降序查询，反过来查询正好是升序，因为插入时是逆序插入
		List<WordStudy> studyWords = wordStudyManager.getWordStudyByPage(pageQuery);
		if (studyWords == null) {
			LogTypeEnum.DEFAULT.error("获取单元内单词失败");
			richUnitStudyVO.setMsg("获取单元内单词失败");
			return null;
		}
		else if (studyWords.isEmpty()) {
			// 不存在未记住的单词，则重新学习
			pageQuery = new PageQuery(0, 1000000);
			pageQuery.addQueryParam("studentId", userId);
			pageQuery.addQueryParam("lessonId", lessonId);
			pageQuery.addQueryParam("unitNbr", unitNbr);
			pageQuery.addQueryParam("state", State.VALID.getState());
			studyWords = wordStudyManager.getWordStudyByPage(pageQuery);
		}
		return studyWords;
	}

	@Override
	public void destroy() throws Exception {
		if (threadPool != null && !threadPool.isShutdown()) {
			threadPool.shutdown();
		}
	}
}
