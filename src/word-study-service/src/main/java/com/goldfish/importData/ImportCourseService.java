package com.goldfish.importData;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.metadata.Sheet;
import com.goldfish.domain.Course;
import com.goldfish.domain.Unit;
import com.goldfish.domain.UnitWords;
import com.goldfish.domain.Word;
import com.goldfish.service.CourseService;
import com.goldfish.service.UnitService;
import com.goldfish.service.UnitWordsService;
import com.goldfish.service.WordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangjingtao
 * @date 2019/2/10 0010.
 */
@Service("importCourseService")
public class ImportCourseService {
    @Resource
    private CourseService courseService;
    @Resource
    private UnitService unitService;
    @Resource
    private UnitWordsService unitWordsService;
    @Resource
    private WordService wordService;

    @Transactional
    public void importCourse(File readyImportfile) throws IOException {
        InputStream inputStream = new FileInputStream(readyImportfile);
        try {
            ExcelListener excelListener = new ExcelListener();
            ExcelReader excelReader = EasyExcelFactory.getReader(inputStream,excelListener);
            List<Sheet> sheets = excelReader.getSheets();
            for (Sheet sheet:sheets) {
                if(sheet.getSheetNo() ==1) {
                    sheet.setHeadLineMun(1);
                    sheet.setClazz(Course.class);
                    excelReader.read(sheet);
                }else if(sheet.getSheetNo() ==2){
                    sheet.setHeadLineMun(1);
                    sheet.setClazz(Unit.class);
                    excelReader.read(sheet);
                }
                else if(sheet.getSheetNo() ==3){
                    sheet.setHeadLineMun(1);
                    sheet.setClazz(UnitWords.class);
                    excelReader.read(sheet);
                }
            }
            this.checkAll(excelListener.getData());
        } finally {
            inputStream.close();
        }
    }

    private void checkAll(List<Object> lists){
        Course course = new Course();
        Map<Integer,Unit> unitMap = new HashMap<>();
        Map<String,UnitWords> unitWordsMap = new HashMap<>();
        int currentUnit = 0,voc_index = 0;
        for(Object object : lists) {
            if (object.getClass().equals(Course.class)) {
                course = (Course)object;
                this.courseService.addCourse(course);
            } else if (object.getClass().equals(Unit.class)) {
                Unit unit = (Unit)object;
                unit.getInfoFromCourse(course);
                unitMap.put(unit.getUnitNbr(),unit);
                this.unitService.addUnit(unit);
            } else if (object.getClass().equals(UnitWords.class)) {
                // 1、填充unitWords
                UnitWords unitWords = (UnitWords)object;
                unitWords.setLessonId(course.getBookNumber().longValue());
                unitWords.setUnit(unitMap.get(unitWords.getUnitNbr()).getUnit());
                unitWords.setFstClassId(2);
                if (currentUnit==unitWords.getUnitNbr()){
                    unitWords.setVocIndex(voc_index++);
                }else{
                    voc_index=0;
                    currentUnit=unitWords.getUnitNbr();
                    unitWords.setVocIndex(voc_index++);
                }
                // 2、填充Word并入库
                Word word = new Word(unitWords.getSpelling(),unitWords.getMeaning(),unitWords.getSoundMarkUs());
                Word word1 = this.wordService.addWord(word).getDefaultModel();
                // 3、再填充unitWords
                unitWords.setWordId(word1.getId());
                this.unitWordsService.addUnitWords(unitWords);
            }
        }
    }
}