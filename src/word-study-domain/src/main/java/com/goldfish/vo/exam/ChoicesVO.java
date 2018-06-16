package com.goldfish.vo.exam;

/**
 * Created by Administrator on 2018/6/13 0013.
 */

/**
 * "choices":{
                     "A":"在…上方prep.",
                     "B":"猪肉",
                     "C":"num./n./a.一千;[pl.]许许多多，成千上万",
                     "D":"adv. 通常地;一般地"
                 },
 */
public class ChoicesVO {
    private static final String regexA = "'A': '";
    private static final String regexB = "'B': '";
    private static final String regexC = "'C': '";
    private static final String regexD = "'D': '";

    private String a;
    private String b;
    private String c;
    private String d;

    public ChoicesVO()
    {

    }

    //{'A': '海报,招贴画', 'B': 'adj.不愿意的，乐意的', 'C': '悲伤，悲痛', 'D': '黑板'}
    public ChoicesVO(String choices)
    {
        a = choices.replace("{'A': '","").split("', 'B'")[0];
        if(choices.contains(regexB))
            b = choices.split(regexB)[1].split("', 'C'")[0];
        if(choices.contains(regexC))
            c = choices.split(regexC)[1].split("', 'D'")[0];
        if(choices.contains(regexD))
            d = choices.split(regexD)[1].split("'")[0];
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }
}
