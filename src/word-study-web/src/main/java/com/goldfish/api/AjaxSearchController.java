package com.goldfish.api;

import com.goldfish.algorithm.bktree.BKTree;
import com.goldfish.algorithm.bktree.SpellChecker;
import com.goldfish.constant.CommonConstant;
import com.goldfish.domain.Course;
import com.goldfish.domain.UnitWords;
import com.goldfish.domain.Word;
import com.goldfish.service.CourseService;
import com.goldfish.service.UnitWordsService;
import com.goldfish.service.WordService;
import com.goldfish.vo.word.SimilarWordVO;
import com.goldfish.vo.word.SimilarWordsVO;
import com.goldfish.vo.word.WordDetailVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhangjingtao
 * @date 2019/1/8 0008.
 */
@Controller
@RequestMapping("/api/Ajax")
public class AjaxSearchController {
    @Resource
    private WordService wordService;
    @Resource
    private UnitWordsService unitWordsService;
    @Resource
    private CourseService courseService;
    private BKTree bkTree;

    public AjaxSearchController() {
        new Thread(() -> {
            try {
                while (wordService==null) {
                    Thread.sleep(2000);
                }
                Word queryWord = new Word();
                List<Word> wordList =  wordService.getListByExample(queryWord).getDefaultModel();
                System.out.println("BKTree加载成功，加载单词量："+wordList.size());
                bkTree = SpellChecker.getBKTree(new ArrayList<>(new HashSet<>(wordList.stream().map(Word::getSpelling).collect(Collectors.toList()))));
            } catch (InterruptedException e) { }
        }).start();
    }

    @RequestMapping(value = "AjaxGetSimilarWords", method = {RequestMethod.GET})
    public @ResponseBody
    SimilarWordsVO doAjaxGetSimilarWords(@RequestParam String keyword) {
        SimilarWordsVO words = new SimilarWordsVO();
        words.setData(bkTree.get10SimilarWords(keyword).stream().map(spell->{
            Word queryWord = new Word();
            queryWord.setSpelling(spell);
            Word word = wordService.getUnique(queryWord).getDefaultModel();
            return new SimilarWordVO(spell,word.getMeaning());
        }).collect(Collectors.toList()));
        words.setSuccess(true);
        words.setMsg(CommonConstant.SUCCESS);
        return words;
    }

    @RequestMapping(value = "AjaxGetWord", method = {RequestMethod.GET})
    public @ResponseBody
    WordDetailVO doAjaxGetWord(@RequestParam String keyword) {
        WordDetailVO wordDetail = new WordDetailVO();
        //查询单词本身含义
        Word queryWord = new Word();
        queryWord.setSpelling(keyword);
        Word word = wordService.getUnique(queryWord).getDefaultModel();
        if (word==null){
            wordDetail.setSuccess(false);
            wordDetail.setMsg("未查询到"+keyword);
            return wordDetail;
        }
        wordDetail.setId(word.getId());
        wordDetail.setSpelling(keyword);
        wordDetail.setMeaning(word.getMeaning());
        wordDetail.setSoundmark(word.getSoundMarkUs());
        //查询单词单元信息
        UnitWords queryUnitWords = new UnitWords();
        queryUnitWords.setSpelling(keyword);
        UnitWords unitWords = unitWordsService.getUnique(queryUnitWords).getDefaultModel();
        wordDetail.setUnit(unitWords.getUnit());
        wordDetail.setUnitNbr(unitWords.getUnitNbr());
        wordDetail.setLessionId(unitWords.getLessonId().intValue());
        //查询课程信息
        Course queryCourse = new Course();
        queryCourse.setBookNumber(unitWords.getLessonId().intValue());
        Course course = courseService.getUnique(queryCourse).getDefaultModel();
        wordDetail.setBookName(course.getBookName());
        wordDetail.setBookNumber(course.getBookNumber());
        wordDetail.setCoverImageURL(course.getCoverImageUrl());
        wordDetail.setModuleCode(course.getModuleCode());

        wordDetail.setSuccess(true);
        wordDetail.setMsg(CommonConstant.SUCCESS);
        return wordDetail;
    }
}
