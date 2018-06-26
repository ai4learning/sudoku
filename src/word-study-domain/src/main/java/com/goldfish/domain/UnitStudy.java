/**
 * Copyright(c) 2004-2018 bianfeng
 */


package com.goldfish.domain;

import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;


/**
 * @author hellosscat
 * @since 2018-5-8
 * 单元单词学习 Domain 类
 */
public class UnitStudy extends StudyPosition implements Serializable {
	
  private static final long serialVersionUID = 5871653462753605181L;
	
	/**  ID  */
	private Long id; 
	/**  学生ID  */
	private Integer studentId; 
	/**  学生CODE  */
	private String studentCode;
	/**  课程ID  */
	private Integer lessonId;
	/**  课程ModuleCode  */
	private String lessonCode;
	/**  单元号  */
	private Integer unitNbr; 
	/**  学习阶段  */
	private Integer currentPhase; 
	/**  总读时间  */
	private Long totalReadingTime; 
	/**  总写时间  */
	private Long totalWritingTime; 
	/**  总学习单词数  */
	private Integer totalNumber;
	/**  单词学习位置  */
	private Integer studyPos;
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
	
	  	

  	public void setStudentId(Integer studentId) {
  	  this.studentId=studentId;
  	}
  
  	public Integer getStudentId() {
  	  return this.studentId;
  	}
	
	  	

  	public void setStudentCode(String studentCode) {
  	  this.studentCode=studentCode;
  	}
  
  	public String getStudentCode() {
  	  return this.studentCode;
  	}
	
	  	

  	public void setLessonId(Integer lessonId) {
  	  this.lessonId=lessonId;
  	}
  
  	public Integer getLessonId() {
  	  return this.lessonId;
  	}

	public String getLessonCode() {
		return lessonCode;
	}

	public void setLessonCode(String lessonCode) {
		this.lessonCode = lessonCode;
	}

	public void setUnitNbr(Integer unitNbr) {
  	  this.unitNbr=unitNbr;
  	}
  
  	public Integer getUnitNbr() {
  	  return this.unitNbr;
  	}
	
	  	

  	public void setCurrentPhase(Integer currentPhase) {
  	  this.currentPhase=currentPhase;
  	}
  
  	public Integer getCurrentPhase() {
  	  return this.currentPhase;
  	}
	
	  	

  	public void setTotalReadingTime(Long totalReadingTime) {
  	  this.totalReadingTime=totalReadingTime;
  	}
  
  	public Long getTotalReadingTime() {
  	  return this.totalReadingTime;
  	}
	
	  	

  	public void setTotalWritingTime(Long totalWritingTime) {
  	  this.totalWritingTime=totalWritingTime;
  	}
  
  	public Long getTotalWritingTime() {
  	  return this.totalWritingTime;
  	}
	
	  	

  	public void setTotalNumber(Integer totalNumber) {
  	  this.totalNumber=totalNumber;
  	}
  
  	public Integer getTotalNumber() {
  	  return this.totalNumber;
  	}

  	public void setStudyPos(Integer studyPos) {
  	  this.studyPos=studyPos;
  	}
  
  	public Integer getStudyPos() {
  	  return this.studyPos;
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
}
