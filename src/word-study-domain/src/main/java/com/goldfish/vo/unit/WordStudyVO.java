package com.goldfish.vo.unit;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * Created by John on 2018/6/2 0002.
 */
public class WordStudyVO extends WordStudyDto{
    @JSONField( name="Id")
    private Integer id = 0;
    private String studyToken;
    private String remark;
    private Long startTime;
    private Date lastReviewTime;
    private Long lastComputeTime;


    private Date createTime;
    private Long reviewTimes;

    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudyToken() {
        return studyToken;
    }

    public void setStudyToken(String studyToken) {
        this.studyToken = studyToken;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Date getLastReviewTime() {
        return lastReviewTime;
    }

    public void setLastReviewTime(Date lastReviewTime) {
        this.lastReviewTime = lastReviewTime;
    }

    public Long getLastComputeTime() {
        return lastComputeTime;
    }

    public void setLastComputeTime(Long lastComputeTime) {
        this.lastComputeTime = lastComputeTime;
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getReviewTimes() {
        return reviewTimes;
    }

    public void setReviewTimes(Long reviewTimes) {
        this.reviewTimes = reviewTimes;
    }



    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
