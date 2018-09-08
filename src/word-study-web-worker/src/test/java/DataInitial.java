import com.goldfish.common.PageQuery;
import com.goldfish.common.log.LogTypeEnum;
import com.goldfish.constant.State;
import com.goldfish.dao.*;
import com.goldfish.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据库数据初始化
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath:spring-config.xml")
@ContextConfiguration(locations = {"classpath:spring/spring-config-datasource.xml", "classpath:spring/spring-config-dao.xml"})
public class DataInitial {
    @Resource
    private UnitWordsDao unitWordsDao;
    @Resource
    private WordStudyDao wordStudyDao;
    @Resource
    private WordDao wordDao;
    @Resource
    private AllwordDao allwordDao;
    @Resource
    private QuestionDao questionDao;

    @Test
    public void deleteSameWordsInWord() {
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("startIndex",0);
        paramMap.put("pageSize", 1000000000);
        List<Word> words = wordDao.getWordByPage(paramMap);

        if (words == null || words.isEmpty()) {
            System.out.println("No Unit Words.");
            return;
        }
        // 依次检查是否存在相同的word
        for (Word word : words) {
            System.out.println("Deal Word ID:"+word.getId());
            String spelling = word.getSpelling();
            String meaning = word.getMeaning();
            paramMap.put("spelling", spelling);
            paramMap.put("meaning", meaning);
            List<Word> subWords = wordDao.getWordByPage(paramMap);

            if (subWords == null || subWords.isEmpty()) {
                System.out.println(word.getId() + " "+ spelling + " " + meaning  +" :Not Found!!!");
                continue;
            }

            if (subWords.size() == 1) {
                continue;
            }
            System.out.println(spelling + " " + meaning  +" :Found More Then One!!!");
            int i = 0;
            for (Word ele:subWords) {
                // 保留第一个
                if (i == 0) {
                    i++;
                    continue;
                }
                // 删除重复项
                wordDao.deleteWord(ele.getId());
                System.out.println(ele.getId() + " "+ spelling +" Deleted.");
                i++;
            }
        }
    }

    @Test
    public void createUnitWordsRelation() {
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("startIndex",0);
        paramMap.put("pageSize", 1000000000);
        List<UnitWords> unitWords = unitWordsDao.getUnitWordsByPage(paramMap);

        if (unitWords == null || unitWords.isEmpty()) {
            System.out.println("No Unit Words.");
            return;
        }
        Date now = new Date();
        for (UnitWords unitWord : unitWords) {
            String spelling = unitWord.getSpelling();
            paramMap.put("spelling", spelling);
            List<Word> words = wordDao.getWordByPage(paramMap);
            if (words == null || words.isEmpty()) {
                System.out.println("ID=" + unitWord.getId() + " " + spelling + " :NotFound!!!");
                continue;
            }
            if (words.size() != 1) {
                System.out.println(spelling + " :Found More Then One!!!");
            }
            Word first = words.get(0);
            unitWord.setWordId(first.getId());
            unitWord.setUnit("Unit"+ unitWord.getUnitNbr());
            unitWord.setModified(now);
            unitWordsDao.updateUnitWords(unitWord);
            System.out.println("ID=" + unitWord.getId() + ",spelling="+unitWord.getSpelling()+",wordId=" + unitWord.getWordId() + "" +
                    ",wordspelling="+ first.getSpelling() + ",meaning="+ first.getMeaning());
        }
    }

    @Test
    public void fillFillWordStudy() {
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("startIndex",0);
        paramMap.put("pageSize", 250510);

        List<WordStudy> wordStudyByPage = wordStudyDao.getWordStudyByPage(paramMap);
        for (WordStudy wordStudy : wordStudyByPage) {
            UnitWords query = new UnitWords();
            query.setVocCode(wordStudy.getVocCode());
            UnitWords unitWords = unitWordsDao.getUnique(query);
            if (unitWords != null) {
                wordStudy.setLessonId(Integer.valueOf(String.valueOf(unitWords.getLessonId())));
                wordStudy.setUnitNbr(unitWords.getUnitNbr());
                wordStudyDao.updateWordStudy(wordStudy);
                System.out.println(wordStudy.getVocCode());
            } else {
                System.out.println(wordStudy.getVocCode() + "不存在！");
            }
        }




//        List<UnitWords> unitWordsByPage = unitWordsDao.getUnitWordsByPage(paramMap);
//        for (UnitWords unitWords : unitWordsByPage) {
//            WordStudy wordStudy = new WordStudy();
//            wordStudy.setVocCode(unitWords.getVocCode());
//            wordStudy.setLessonId(Integer.valueOf(String.valueOf(unitWords.getLessonId())));
//            wordStudy.setUnitNbr(unitWords.getUnitNbr());
//            wordStudyDao.updateWordStudy(wordStudy);
//            System.out.println(wordStudy.getVocCode());
//        }
    }

    @Test
    public void rebuildUnitWordsRelation() {
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("startIndex",0);
        paramMap.put("pageSize", 1000000000);

        List<Allword> allword = allwordDao.getAllwordByPage(paramMap);
        if (allword == null || allword.isEmpty()) {
            System.out.println("No Unit Words.");
            return;
        }
        Date now = new Date();
        for (Allword word : allword) {
            String voccode = word.getVoccode();
            paramMap.put("vocCode", voccode);
            List<UnitWords> unitWords = unitWordsDao.getUnitWordsByPage(paramMap);
            if (unitWords == null || unitWords.isEmpty()) {
                System.out.println("ID=" + word.getId() + " vocCode=" + voccode + " :NotFound!!!");
                continue;
            }
            if (unitWords.size() != 1) {
                System.out.println("vocCode=" + voccode + " :Found More Then One!!!");
            }
            Word query = new Word();
            query.setSpelling(word.getSpelling());
            query.setMeaning(word.getMeaning());
            Word unique = wordDao.getUnique(query);
            for (UnitWords unitWord : unitWords) {
                unitWord.setMeaning(word.getMeaning());
                if (unique != null) {
                    unitWord.setWordId(unique.getId());
                }
                unitWord.setModified(now);
                unitWordsDao.updateUnitWords(unitWord);
                System.out.println("ID=" + unitWord.getId() + ",vocCode="+ voccode +",meaning =" + unitWord.getMeaning() + ",wordId="+ unitWord.getWordId() + " SUCCESS.");
            }
        }
    }

    @Test
    public void initQuestionLessonUnitRelation() {

        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("startIndex",0);
        paramMap.put("pageSize", 1000000000);
        List<Question> questions = questionDao.getQuestionByPage(paramMap);
        for (Question question : questions) {
            String vocCode = question.getVocCode();

            UnitWords query = new UnitWords();
            query.setVocCode(vocCode);
            query.setState(State.VALID.getState());
            UnitWords unique = unitWordsDao.getUnique(query);
            if (unique == null) {
                System.out.println("UnitWord不存在，vocCode= " +  vocCode + ",spell= " + question.getSpelling());
                continue;
            }

            question.setLessonId(Integer.valueOf(String.valueOf(unique.getLessonId())));
            question.setUnitNbr(unique.getUnitNbr());
            question.setWordId(unique.getWordId());
            questionDao.updateQuestion(question);
            System.out.println("questid= "+question.getId()+" ,建立课程单元关联成功");
        }




    }




}
