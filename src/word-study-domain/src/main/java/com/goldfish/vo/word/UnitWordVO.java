package com.goldfish.vo.word;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by John on 2018/6/16 0016.
 */
public class UnitWordVO {

    @JSONField( name="Id")
    protected Long id;
    protected String vocCode;
    protected Integer vocIndex;
    protected String spelling;
    protected String meaning;
    protected String soundMarkUs;
    @JSONField( name="UnitId")
    protected Integer unitId;
    protected Integer unitNbr;
    protected boolean isCollected;

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

    public boolean isCollected() {
        return isCollected;
    }

    public void setIsCollected(boolean isCollected) {
        this.isCollected = isCollected;
    }
}
