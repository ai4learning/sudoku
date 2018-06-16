package com.goldfish.vo.error;

import com.goldfish.vo.BaseVO;
import com.goldfish.vo.word.UnitWordVO;

import java.util.List;

/**
 *      * {
     "data":[
         {
             "Id":1516,
             "vocCode":"8a108cb7-42aa-d911-0142-ad8121f500d4",
             "spelling":"dame",
             "soundMark":"",
             "meaning":"夫人（女爵）",
             "soundMarkUs":"[deɪm]",
             "UnitId":0,
             "unitNbr":1,
             "vocIndex":0,
             "isCollected":false
         },
         {
             "Id":1518,
             "vocCode":"8a108cb7-42aa-d911-0142-ad8121f500d6",
             "spelling":"game",
             "soundMark":"",
             "meaning":"游戏;赌博",
             "soundMarkUs":"[ɡeɪm]",
             "UnitId":0,
             "unitNbr":1,
             "vocIndex":1,
             "isCollected":false
         }
     ],
     "success":true,
     "condition":0,
     "msg":"搞定！",
     "studytoken":"7b4db6fd-316c-4dc3-8ea8-47e0f560ebce",
     "bookInfo":{
         "Id":0,
         "moduleCode":"00000000-0000-0000-0000-000000000000",
         "bookName":"错词强化学...",
         "totalUnitNbr":0,
         "outDate":false,
         "startFrom":0,
         "studyMode":0
     },
     "totalNbr":0
 }
 * Created by John on 2018/6/16 0016.
 */
public class ErrorBookVO extends BaseVO{
    private String studytoken;
    private List<UnitWordVO> data;
    private ErrorBookInfo bookInfo;

    public String getStudytoken() {
        return studytoken;
    }

    public void setStudytoken(String studytoken) {
        this.studytoken = studytoken;
    }

    public List<UnitWordVO> getData() {
        return data;
    }

    public void setData(List<UnitWordVO> data) {
        this.data = data;
    }

    public ErrorBookInfo getBookInfo() {
        return bookInfo;
    }

    public void setBookInfo(ErrorBookInfo bookInfo) {
        this.bookInfo = bookInfo;
    }
}
