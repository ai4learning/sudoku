package com.goldfish.domain;

/**
 *      *      "Id":513527,
 *         "studyPositionCode":"e2b18f0b-bdd2-4413-9dc1-d305d3efaf7e",
 *         "positionType":0,
 *         "vocCode":"8a108cb7-42aa-d911-0142-ad81218b0093",
 *         "unitNbr":3,
 *         "isCurrentPos":true,
 *         "isFinished":0,
 *         "spelling":"it",
 *         "isAllFinished":false,
 *         "IsTested":0,
 *         "Status":0
 *
 * Created by John on 2018/6/2 0002.
 */
public class StudyPosition {
    /**  学习位置CODE  */
    private String studyPositionCode;
    /**  位置类型  */
    private Integer positionType;
    /**  单元号  */
    private Integer unitNbr;
    /**  单词CODE  */
    private String vocCode;
    /**  单词  */
    private String spelling;
    /**  是当前学习位置  */
    private Integer isCurrentPos;
    /**  是否学习完成  */
    private Integer isFinished;
    /**  是否测试完成  */
    private Integer isTested;
    /**  全部完成  */
    private Integer isAllFinished;
    /**  状态  */
    private Integer status;

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

    public Integer getUnitNbr() {
        return unitNbr;
    }

    public void setUnitNbr(Integer unitNbr) {
        this.unitNbr = unitNbr;
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

    public Integer getIsCurrentPos() {
        return isCurrentPos;
    }

    public void setIsCurrentPos(Integer isCurrentPos) {
        this.isCurrentPos = isCurrentPos;
    }

    public Integer getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(Integer isFinished) {
        this.isFinished = isFinished;
    }

    public Integer getIsTested() {
        return isTested;
    }

    public void setIsTested(Integer isTested) {
        this.isTested = isTested;
    }

    public Integer getIsAllFinished() {
        return isAllFinished;
    }

    public void setIsAllFinished(Integer isAllFinished) {
        this.isAllFinished = isAllFinished;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
