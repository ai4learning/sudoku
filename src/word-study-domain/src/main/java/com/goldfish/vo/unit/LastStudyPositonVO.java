package com.goldfish.vo.unit;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * Created by John on 2018/6/15 0015.
 */
public class LastStudyPositonVO {
    @JSONField( name = "Id")
    private Long id;
    private String studyPositionCode;
    private String studytoken;
    private String moduleCode;
    private Integer positionType = 0;
    private String remark = null;
    private String vocCode;
    private Long totalReadingTime;
    private Long totalWritingTime;
    private String userCode;
    private Integer unitNbr;
    private Integer wordCount;
    private Boolean isContinue;
    private Integer seconds4SpellingLetter;
    private Boolean isCurrentPos;
    private Integer isFinished;
    private String spelling;
    private Boolean isAllFinished;
    private Date createtime;
    @JSONField( name = "IsTested")
    private Integer isTested;
    @JSONField( name = "Status")
    private Integer status = 1;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudyPositionCode() {
        return studyPositionCode;
    }

    public void setStudyPositionCode(String studyPositionCode) {
        this.studyPositionCode = studyPositionCode;
    }

    public String getStudytoken() {
        return studytoken;
    }

    public void setStudytoken(String studytoken) {
        this.studytoken = studytoken;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public Integer getPositionType() {
        return positionType;
    }

    public void setPositionType(Integer positionType) {
        this.positionType = positionType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getVocCode() {
        return vocCode;
    }

    public void setVocCode(String vocCode) {
        this.vocCode = vocCode;
    }

    public Long getTotalReadingTime() {
        return totalReadingTime;
    }

    public void setTotalReadingTime(Long totalReadingTime) {
        this.totalReadingTime = totalReadingTime;
    }

    public Long getTotalWritingTime() {
        return totalWritingTime;
    }

    public void setTotalWritingTime(Long totalWritingTime) {
        this.totalWritingTime = totalWritingTime;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public Integer getUnitNbr() {
        return unitNbr;
    }

    public void setUnitNbr(Integer unitNbr) {
        this.unitNbr = unitNbr;
    }

    public Integer getWordCount() {
        return wordCount;
    }

    public void setWordCount(Integer wordCount) {
        this.wordCount = wordCount;
    }

    public Boolean getIsContinue() {
        return isContinue;
    }

    public void setIsContinue(Boolean isContinue) {
        this.isContinue = isContinue;
    }

    public Integer getSeconds4SpellingLetter() {
        return seconds4SpellingLetter;
    }

    public void setSeconds4SpellingLetter(Integer seconds4SpellingLetter) {
        this.seconds4SpellingLetter = seconds4SpellingLetter;
    }

    public Boolean getIsCurrentPos() {
        return isCurrentPos;
    }

    public void setIsCurrentPos(Boolean isCurrentPos) {
        this.isCurrentPos = isCurrentPos;
    }

    public Integer getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(Integer isFinished) {
        this.isFinished = isFinished;
    }

    public String getSpelling() {
        return spelling;
    }

    public void setSpelling(String spelling) {
        this.spelling = spelling;
    }

    public Boolean getIsAllFinished() {
        return isAllFinished;
    }

    public void setIsAllFinished(Boolean isAllFinished) {
        this.isAllFinished = isAllFinished;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getIsTested() {
        return isTested;
    }

    public void setIsTested(Integer isTested) {
        this.isTested = isTested;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
