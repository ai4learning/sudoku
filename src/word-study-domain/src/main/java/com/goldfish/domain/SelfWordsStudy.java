/**
 * Copyright(c) 2004-2018 bianfeng
 */


package com.goldfish.domain;

import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;


/**
 * @author hellosscat
 * @since 2018-5-8
 * 学生自身单词学习 Domain 类
 */
public class SelfWordsStudy  implements Serializable {
	
  private static final long serialVersionUID = 6453241747280886716L;
	
	/**  ID  */
	private Long id; 
	/**  学生ID  */
	private Integer studentId; 
	/**  学生CODE  */
	private String studentCode; 
	/**  课程ID  */
	private Integer lessonId; 
	/**  课程号  */
	private Integer unitNbr; 
	/**  类型  */
	private Integer type; 
	/**  当前学习阶段  */
	private Integer currentPhase; 
	/**  总读时间  */
	private Long totalReadingTime; 
	/**  总写时间  */
	private Long totalWritingTime; 
	/**  总学习单词数  */
	private Integer totalNumber; 
	/**  是否学习完成  */
	private Integer isFinished; 
	/**  是否测试完成  */
	private Integer isTested; 
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
	
	  	

  	public void setIsFinished(Integer isFinished) {
  	  this.isFinished=isFinished;
  	}
  
  	public Integer getIsFinished() {
  	  return this.isFinished;
  	}
	
	  	

  	public void setIsTested(Integer isTested) {
  	  this.isTested=isTested;
  	}
  
  	public Integer getIsTested() {
  	  return this.isTested;
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
