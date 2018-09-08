package com.goldfish.dao.cache.local;

import com.goldfish.common.log.LogTypeEnum;
import com.goldfish.constant.State;
import com.goldfish.dao.WordDao;
import com.goldfish.domain.Word;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by John on 2018/7/23 0023.
 */
@Component("wordContext")
public class WordContext {

    private Map<Integer, Word> context = new ConcurrentHashMap<Integer, Word>(2048);
    @Resource
    private WordDao wordDao;

    public Word getWord(Integer wordId) {
        Word word = context.get(wordId);
        if (word != null) {
            return word;
        }
        word = wordDao.getWordById(wordId);
        if (word == null) {
            LogTypeEnum.DEFAULT.error("Word Not Exist, wordId={}", wordId);
            return null;
        }
        context.put(wordId, word);
        return word;
    }
}
