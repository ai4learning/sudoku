package com.goldfish.vo.unit;

/**
 * Created by John on 2018/6/2 0002.
 */
public class WordStudyDto {

    private Integer memoryLevel;
    private Long timeLeft;
    private String userVocCode;
    private String userCode;
    private double finishReadingTime;
    private boolean isFstReadSuccess;
    private Integer readFailTimes;
    private Integer continueReadFailTimes;

    private boolean isHalfReading;
    private boolean isFstSpellSuccess;
    private Integer spellFailTimes;
    private Integer continueSpellFailTimes;

    private boolean isHalfSpelling;
    private boolean isRemember;
    private boolean isCancelReview;

    private String vocCode;

    public Integer getMemoryLevel() {
        return memoryLevel;
    }

    public void setMemoryLevel(Integer memoryLevel) {
        this.memoryLevel = memoryLevel;
    }

    public Long getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(Long timeLeft) {
        this.timeLeft = timeLeft;
    }

    public String getUserVocCode() {
        return userVocCode;
    }

    public void setUserVocCode(String userVocCode) {
        this.userVocCode = userVocCode;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public double getFinishReadingTime() {
        return finishReadingTime;
    }

    public void setFinishReadingTime(double finishReadingTime) {
        this.finishReadingTime = finishReadingTime;
    }

    public boolean isFstReadSuccess() {
        return isFstReadSuccess;
    }

    public void setIsFstReadSuccess(boolean isFstReadSuccess) {
        this.isFstReadSuccess = isFstReadSuccess;
    }

    public Integer getReadFailTimes() {
        return readFailTimes;
    }

    public void setReadFailTimes(Integer readFailTimes) {
        this.readFailTimes = readFailTimes;
    }

    public Integer getContinueReadFailTimes() {
        return continueReadFailTimes;
    }

    public void setContinueReadFailTimes(Integer continueReadFailTimes) {
        this.continueReadFailTimes = continueReadFailTimes;
    }

    public boolean isHalfReading() {
        return isHalfReading;
    }

    public void setIsHalfReading(boolean isHalfReading) {
        this.isHalfReading = isHalfReading;
    }

    public boolean isFstSpellSuccess() {
        return isFstSpellSuccess;
    }

    public void setIsFstSpellSuccess(boolean isFstSpellSuccess) {
        this.isFstSpellSuccess = isFstSpellSuccess;
    }

    public Integer getSpellFailTimes() {
        return spellFailTimes;
    }

    public void setSpellFailTimes(Integer spellFailTimes) {
        this.spellFailTimes = spellFailTimes;
    }

    public Integer getContinueSpellFailTimes() {
        return continueSpellFailTimes;
    }

    public void setContinueSpellFailTimes(Integer continueSpellFailTimes) {
        this.continueSpellFailTimes = continueSpellFailTimes;
    }

    public boolean isHalfSpelling() {
        return isHalfSpelling;
    }

    public void setIsHalfSpelling(boolean isHalfSpelling) {
        this.isHalfSpelling = isHalfSpelling;
    }

    public boolean isRemember() {
        return isRemember;
    }

    public void setIsRemember(boolean isRemember) {
        this.isRemember = isRemember;
    }

    public boolean isCancelReview() {
        return isCancelReview;
    }

    public void setIsCancelReview(boolean isCancelReview) {
        this.isCancelReview = isCancelReview;
    }

    public String getVocCode() {
        return vocCode;
    }

    public void setVocCode(String vocCode) {
        this.vocCode = vocCode;
    }
}
