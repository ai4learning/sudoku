package com.goldfish.vo.unit;

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
public class UnitWordStudyVO {

    private Long id;
    private String vocCode;
    private Integer vocIndex;
    private String spelling;
    private String meaning;
    private String soundMarkUs;
    private String soundMarkUk;

    private Integer unitId;
    private Integer unitNbr;
    private Integer lessonNbr;
    private Integer fstClassId;
    private boolean isCollected;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVocCode() {
        return vocCode;
    }

    public void setVocCode(String vocCode) {
        this.vocCode = vocCode;
    }

    public Integer getVocIndex() {
        return vocIndex;
    }

    public void setVocIndex(Integer vocIndex) {
        this.vocIndex = vocIndex;
    }

    public String getSpelling() {
        return spelling;
    }

    public void setSpelling(String spelling) {
        this.spelling = spelling;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getSoundMarkUs() {
        return soundMarkUs;
    }

    public void setSoundMarkUs(String soundMarkUs) {
        this.soundMarkUs = soundMarkUs;
    }

    public String getSoundMarkUk() {
        return soundMarkUk;
    }

    public void setSoundMarkUk(String soundMarkUk) {
        this.soundMarkUk = soundMarkUk;
    }

    public Integer getUnitId() {
        return unitId;
    }

    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
    }

    public Integer getUnitNbr() {
        return unitNbr;
    }

    public void setUnitNbr(Integer unitNbr) {
        this.unitNbr = unitNbr;
    }

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

    public boolean isCollected() {
        return isCollected;
    }

    public void setIsCollected(boolean isCollected) {
        this.isCollected = isCollected;
    }
}
