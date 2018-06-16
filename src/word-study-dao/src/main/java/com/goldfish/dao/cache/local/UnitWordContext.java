package com.goldfish.dao.cache.local;

import com.goldfish.common.log.LogTypeEnum;
import com.goldfish.constant.State;
import com.goldfish.dao.CourseDao;
import com.goldfish.dao.UnitWordsDao;
import com.goldfish.domain.Course;
import com.goldfish.domain.UnitWords;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Component
public class UnitWordContext {

    private Map<String, UnitWords> context = new HashMap<String, UnitWords>(512);
    @Resource
    private UnitWordsDao unitWordsDao;

    public UnitWords getUnitWord(String vocCode) {
        UnitWords unitWords = context.get(vocCode);
        if (unitWords != null) {
            return unitWords;
        }
        UnitWords query = new UnitWords();
        query.setVocCode(vocCode);
        query.setState(State.VALID.getState());
        UnitWords unique = unitWordsDao.getUnique(query);
        if (unique == null) {
            LogTypeEnum.DEFAULT.error("UnitWords Not Exist, vocCode={}", vocCode);
            return null;
        }
        context.put(vocCode, unique);
        return unique;
    }
}
