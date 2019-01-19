package com.goldfish.algorithm.bktree;

/**
 * Created by zhangjingtao on 2016/9/30.
 */

import java.util.List;

/**
 * 拼写纠错
 */
public class SpellChecker {
    private static MetricSpace ms = new LevensteinDistance();
    private static BKTree bk = new BKTree(ms);

    public static BKTree getBKTree(List<String> stringList){
        bk.clear();
        stringList.forEach(str->bk.put(str));
        return bk;
    }
}
