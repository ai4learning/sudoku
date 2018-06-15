package com.goldfish.vo;

import com.alibaba.fastjson.annotation.JSONField;

/**
 *  *             "currentPosition":{
 *                 "Id":514981,
 *                 "studyPositionCode":"68c7bd11-5ebf-4c67-a8c8-e8d3c3b24840",
 *                 "positionType":0,
 *                 "vocCode":"8a108cb7-42aa-d911-0142-ad8123f50222",
 *                 "unitNbr":14,
 *                 "isCurrentPos":true,
 *                 "isFinished":0,
 *                 "spelling":"three",
 *                 "isAllFinished":true,
 *                 "isTested":0,
 *                 "Status":0
 *             },
 * Created by John on 2018/5/21 0021.
 */
public class CurrentStudyPositionVO {
    @JSONField( name="Id")
    private Long id;
    private String studyPositionCode;
    private Integer positionType;

    private String vocCode;
    private String spelling;

    private Integer unitNbr;
    private boolean isCurrentPos;
    @JSONField( name="isFinished")
    private Integer isFinished = 0;
    @JSONField( name="isAllFinished")
    private boolean isAllFinished;
    @JSONField( name="isTested")
    private Integer isTested = 0;
    private Integer Status = 0;

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

    public Integer getPositionType() {
        return positionType;
    }

    public void setPositionType(Integer positionType) {
        this.positionType = positionType;
    }

    public String getVocCode() {
        return vocCode;
    }

    public void setVocCode(String vocCode) {
        this.vocCode = vocCode;
    }

    public String getSpelling() {
        return spelling;
    }

    public void setSpelling(String spelling) {
        this.spelling = spelling;
    }

    public Integer getUnitNbr() {
        return unitNbr;
    }

    public void setUnitNbr(Integer unitNbr) {
        this.unitNbr = unitNbr;
    }

    public boolean isCurrentPos() {
        return isCurrentPos;
    }

    public void setIsCurrentPos(boolean isCurrentPos) {
        this.isCurrentPos = isCurrentPos;
    }

    public Integer getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(Integer isFinished) {
        this.isFinished = isFinished;
    }

    public boolean isAllFinished() {
        return isAllFinished;
    }

    public void setIsAllFinished(boolean isAllFinished) {
        this.isAllFinished = isAllFinished;
    }

    public Integer getIsTested() {
        return isTested;
    }

    public void setIsTested(Integer isTested) {
        this.isTested = isTested;
    }

    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer status) {
        Status = status;
    }
}
