package com.goldfish.vo.collection;

import com.alibaba.fastjson.annotation.JSONField;
import com.goldfish.vo.BasicVO;
import com.goldfish.vo.word.UnitWordVO;

import java.util.List;

/**
 * Created by Administrator on 2018/6/19 0019.
 */
public class VocabularyVO extends BasicVO {
    @JSONField(name="data")
    private List<UnitWordVO> wordList;

    public List<UnitWordVO> getWordList() {
        return wordList;
    }

    public void setWordList(List<UnitWordVO> wordList) {
        this.wordList = wordList;
    }
}
