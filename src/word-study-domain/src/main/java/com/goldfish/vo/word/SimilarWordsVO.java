package com.goldfish.vo.word;

import com.goldfish.vo.BasicVO;

import java.util.List;

/**
 * @author zhangjingtao
 * @date 2019/1/12 0012.
 */
public class SimilarWordsVO extends BasicVO {
    private List<SimilarWordVO> data;

    public List<SimilarWordVO> getData() {
        return data;
    }

    public void setData(List<SimilarWordVO> data) {
        this.data = data;
    }
}
