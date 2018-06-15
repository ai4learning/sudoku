/**
 * Copyright(c) 2004-2018 bianfeng
 */


package com.goldfish.domain;

import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;


/**
 * @author hellosscat
 * @since 2018-5-8
 * 试卷 Domain 类
 */
public class Paper  implements Serializable {
	
  private static final long serialVersionUID = -4112898030901997161L;
	
	/**  ID  */
	private Long id; 
	/**  课程ID  */
	private Integer lessonId;

	private String moduleCode;
	/**  单元号  */
	private Integer unitNbr; 
	/**  试卷类型  */
	private Integer type; 
	/**  测试范围  */
	private Integer testArea; 
	/**  题数  */
	private Integer questionNbr; 
	/**  题目类型  */
	private String questonType; 
	/**  考试时长  */
	private Long standardDuration; 
	/**  题目  */
	private String questions; 
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
	
	  	

  	public void setType(Integer type) {
  	  this.type=type;
  	}
  
  	public Integer getType() {
  	  return this.type;
  	}
	
	  	

  	public void setTestArea(Integer testArea) {
  	  this.testArea=testArea;
  	}
  
  	public Integer getTestArea() {
  	  return this.testArea;
  	}
	
	  	

  	public void setQuestionNbr(Integer questionNbr) {
  	  this.questionNbr=questionNbr;
  	}
  
  	public Integer getQuestionNbr() {
  	  return this.questionNbr;
  	}
	
	  	

  	public void setQuestonType(String questonType) {
  	  this.questonType=questonType;
  	}
  
  	public String getQuestonType() {
  	  return this.questonType;
  	}
	
	  	

  	public void setStandardDuration(Long standardDuration) {
  	  this.standardDuration=standardDuration;
  	}
  
  	public Long getStandardDuration() {
  	  return this.standardDuration;
  	}
	
	  	

  	public void setQuestions(String questions) {
  	  this.questions=questions;
  	}
  
  	public String getQuestions() {
  	  return this.questions;
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
