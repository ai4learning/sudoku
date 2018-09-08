/**
 * Created by joey on 2018/7/5.
 */


import com.goldfish.common.PageQuery;
import com.goldfish.common.log.LogTypeEnum;
import com.goldfish.constant.State;
import com.goldfish.dao.AllwordDao;
import com.goldfish.dao.QuestionDao;
import com.goldfish.dao.UnitWordsDao;
import com.goldfish.dao.WordDao;
import com.goldfish.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
public class DataManager{
    @Resource
    private UnitWordsDao unitWordsDao;
    @Resource
    private WordDao wordDao;
    @Resource
    private AllwordDao allwordDao;
    @Resource
    private QuestionDao questionDao;

    private Date startTime;
    private Date endTime;
    private  SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
    Map<String, Word> wordMap = new HashMap<String, Word>();

    public void setStartTime(){
        startTime = new Date();
    }

    public long getTimeCost(){
        endTime = new Date();
        long interval = (endTime.getTime() - startTime.getTime())/1000;
        return interval;

    }

    @Test
    public void deleteSameWordsInWord() {
        Map<String, Object> paramMap = new HashMap<String, Object>();


        paramMap.put("startIndex", 0);
        paramMap.put("pageSize", 1000000000);
        List<Word> words = wordDao.getWordByPage(paramMap);

        setStartTime();

        if (words == null || words.isEmpty()) {
            System.out.println("No Unit Words.");
            return;
        }
        System.out.println("Get Unit Words Size :" + words.size());
        // 依次检查是否存在相同的word
        for (Word word : words) {
        // System.out.println("Deal Word ID:" + word.getId());
            String spelling = word.getSpelling();
            String meaning = word.getMeaning();
            String uniq_str =  ""+spelling+meaning;
            if (wordMap.get(uniq_str) == null) {
                wordMap.put(uniq_str, word);
            }
            else{
                wordDao.deleteWord(word.getId());
            }
        }

        long totalSeconds = getTimeCost();
        System.out.println("Total Seconds For Deduplicatioin For Word Table ," +totalSeconds);
    }

    @Test
    public void createUnitWordsRelation() {
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("startIndex",0);
        paramMap.put("pageSize", 1000000000);
        List<UnitWords> unitWords;

        setStartTime();
        unitWords = unitWordsDao.getUnitWordsByPage(paramMap);

        if (unitWords == null || unitWords.isEmpty()) {
            System.out.println("No Unit Words.");
            return;
        }
        System.out.println("Get Unit Words Size :" + unitWords.size());
        if ( wordMap == null || wordMap.isEmpty()){
            System.out.println("Run Method deleteSameWordsInWord() First.");
            deleteSameWordsInWord();
        }

        Date now = new Date();
        Word first;
        for (UnitWords unitWord : unitWords) {
            String spelling = unitWord.getSpelling();
            String meaning = unitWord.getMeaning();
            String uniq_str =  ""+spelling+meaning;

            first = wordMap.get(uniq_str);
            if ( first == null ){
                System.out.println("Internal Error,Word Get From Hash Map is Null.");
                return;
            }
            unitWord.setWordId(first.getId());
            unitWord.setUnit("Unit"+ unitWord.getUnitNbr());
            unitWord.setModified(now);
            unitWordsDao.updateUnitWords(unitWord);
//            System.out.println("ID=" + unitWord.getId() + ",spelling="+unitWord.getSpelling()+",wordId=" + unitWord.getWordId() + "" +
//                    ",wordspelling="+ first.getSpelling() + ",meaning="+ first.getMeaning());
        }
        long totalSeconds = getTimeCost();
        System.out.println("Total Seconds For Deduplicatioin For Word Table ," +totalSeconds);
    }


    @Test
    public void initQuestionLessonUnitRelation() {

        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("startIndex",0);
        paramMap.put("pageSize", 1000000000);
        List<Question> questions = questionDao.getQuestionByPage(paramMap);

        System.out.println("Get Unit Words Size :" + questions.size());
        for (Question question : questions) {
            String vocCode = question.getVocCode();

            UnitWords query = new UnitWords();
            query.setVocCode(vocCode);
            query.setState(State.VALID.getState()); //保证unit_words的单词都是valid
            UnitWords unique = unitWordsDao.getUnique(query);
            if (unique == null) {
                System.out.println("UnitWord不存在，vocCode= " +  vocCode + ",spell= " + question.getSpelling());
                continue;
            }
            question.setLessonId(Integer.valueOf(String.valueOf(unique.getLessonId())));
            question.setUnitNbr(unique.getUnitNbr());
            question.setWordId(unique.getWordId());
            questionDao.updateQuestion(question);
//            System.out.println("questid= "+question.getId()+" ,建立课程单元关联成功");
        }
    }

}