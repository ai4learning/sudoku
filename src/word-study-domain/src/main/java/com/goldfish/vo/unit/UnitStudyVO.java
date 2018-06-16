package com.goldfish.vo.unit;

import com.alibaba.fastjson.annotation.JSONField;
import com.goldfish.vo.word.UnitWordVO;

/**
 *             "Id":1490,
 *             "vocCode":"8a108cb7-42aa-d911-0142-ad8121b400b0",
 *             "spelling":"Miss",
 *             "meaning":"小姐;女士",
 *             "soundMarkUs":"[mɪs]",
 *             "soundMarkUk":"",
 *             "UnitId":0,
 *             "unitNbr":3,
 *             "lessonNbr":5,
 *             "fstClassId":8,
 *             "vocIndex":30,
 *             "isCollected":false
 * Created by John on 2018/6/2 0002.
 */
public class UnitStudyVO extends UnitWordVO{

    private Integer lessonNbr;
    private Integer fstClassId;
    private String soundMarkUk;

    public Integer getLessonNbr() {
        return lessonNbr;
    }

    public void setLessonNbr(Integer lessonNbr) {
        this.lessonNbr = lessonNbr;
    }

    public Integer getFstClassId() {
        return fstClassId;
    }

    public void setFstClassId(Integer fstClassId) {
        this.fstClassId = fstClassId;
    }

    public String getSoundMarkUk() {
        return soundMarkUk;
    }

    public void setSoundMarkUk(String soundMarkUk) {
        this.soundMarkUk = soundMarkUk;
    }
}
