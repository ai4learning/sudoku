/**
 * Copyright(c) 2004-2018 bianfeng
 */


package com.goldfish.domain;

import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;


/**
 * @author hellosscat
 * @since 2018-5-8
 * 考试 Domain 类
 */
public class Exam  implements Serializable {
	
  private static final long serialVersionUID = 2281226215371375617L;
	
	/**  ID  */
	private Long id; 
	/**  用户ID  */
	private Integer userId; 
	/**  用户CODE  */
	private String userCode; 
	/**  课程ID  */
	private Integer lessonId;

	private String moduleCode;
	/**  课程号  */
	private Integer unitNbr; 
	/**  试卷ID  */
	private Long pageId; 
	/**  测试类型  */
	private Integer testType; 
	/**  得分  */
	private Integer resultScore; 
	/**  考试时长  */
	private Long standardDuration; 
	/**  实际用时  */
	private Long realDuration; 
	/**  错误数  */
	private Integer errorNbr; 
	/**  积分  */
	private Integer cashPoint; 
	/**  积分类型  */
	private Integer cashPointType; 
	/**  是否通过  */
	private String isPass; 
	/**  状态  */
	private Integer state; 
	/**  创建时间  */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date created; 
	/**  修改时间  */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date modified; 

  	public void setId(Long id) {
  	  this.id=id;
  	}
  
  	public Long getId() {
  	  return this.id;
  	}
	
	  	

  	public void setUserId(Integer userId) {
  	  this.userId=userId;
  	}
  
  	public Integer getUserId() {
  	  return this.userId;
  	}
	
	  	

  	public void setUserCode(String userCode) {
  	  this.userCode=userCode;
  	}
  
  	public String getUserCode() {
  	  return this.userCode;
  	}
	
	  	

  	public void setLessonId(Integer lessonId) {
  	  this.lessonId=lessonId;
  	}
  
  	public Integer getLessonId() {
  	  return this.lessonId;
  	}
	
	  	

  	public void setUnitNbr(Integer unitNbr) {
  	  this.unitNbr=unitNbr;
  	}
  
  	public Integer getUnitNbr() {
  	  return this.unitNbr;
  	}
	
	  	

  	public void setPageId(Long pageId) {
  	  this.pageId=pageId;
  	}
  
  	public Long getPageId() {
  	  return this.pageId;
  	}
	
	  	

  	public void setTestType(Integer testType) {
  	  this.testType=testType;
  	}
  
  	public Integer getTestType() {
  	  return this.testType;
  	}
	
	  	

  	public void setResultScore(Integer resultScore) {
  	  this.resultScore=resultScore;
  	}
  
  	public Integer getResultScore() {
  	  return this.resultScore;
  	}
	
	  	

  	public void setStandardDuration(Long standardDuration) {
  	  this.standardDuration=standardDuration;
  	}
  
  	public Long getStandardDuration() {
  	  return this.standardDuration;
  	}
	
	  	

  	public void setRealDuration(Long realDuration) {
  	  this.realDuration=realDuration;
  	}
  
  	public Long getRealDuration() {
  	  return this.realDuration;
  	}
	
	  	

  	public void setErrorNbr(Integer errorNbr) {
  	  this.errorNbr=errorNbr;
  	}
  
  	public Integer getErrorNbr() {
  	  return this.errorNbr;
  	}
	
	  	

  	public void setCashPoint(Integer cashPoint) {
  	  this.cashPoint=cashPoint;
  	}
  
  	public Integer getCashPoint() {
  	  return this.cashPoint;
  	}
	
	  	

  	public void setCashPointType(Integer cashPointType) {
  	  this.cashPointType=cashPointType;
  	}
  
  	public Integer getCashPointType() {
  	  return this.cashPointType;
  	}
	
	  	

  	public void setIsPass(String isPass) {
  	  this.isPass=isPass;
  	}
  
  	public String getIsPass() {
  	  return this.isPass;
  	}
	
	  	

  	public void setState(Integer state) {
  	  this.state=state;
  	}
  
  	public Integer getState() {
  	  return this.state;
  	}
	
	  	

  	public void setCreated(java.util.Date created) {
  	  this.created=created;
  	}
  
  	public java.util.Date getCreated() {
  	  return this.created;
  	}
	
	  	

  	public void setModified(java.util.Date modified) {
  	  this.modified=modified;
  	}
  
  	public java.util.Date getModified() {
  	  return this.modified;
  	}

	public String getModuleCode() {
		return moduleCode;
	}

	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}
}
