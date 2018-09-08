/**
 * Copyright(c) 2004-2018 bianfeng
 */


package com.goldfish.domain;

import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;


/**
 * @author hellosscat
 * @since 2018-5-8
 * 课程学习 Domain 类
 */
public class CourseStudy  extends StudyPosition implements Serializable {
	
  private static final long serialVersionUID = -8682793933625864579L;
	/**  ID  */
	private Long id; 
	/**  学生ID  */
	private Integer studentId; 
	/**  学生CODE  */
	private String studentCode; 
	/**  课程ID  */
	private Integer lessonId;
	/**  是当前正在学习课程  */
	private Boolean currentStudyBook;
	/**  从哪开始学  */
	private Integer startFrom; 
	/**  学习模式  */
	private Integer studyMode; 
	/**  完成单词数统计  */
	private Integer completeWordCount;

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

  	public void setStartFrom(Integer startFrom) {
  	  this.startFrom=startFrom;
  	}
  
  	public Integer getStartFrom() {
  	  return this.startFrom;
  	}
	
	  	

  	public void setStudyMode(Integer studyMode) {
  	  this.studyMode=studyMode;
  	}
  
  	public Integer getStudyMode() {
  	  return this.studyMode;
  	}
	
	  	

  	public void setCompleteWordCount(Integer completeWordCount) {
  	  this.completeWordCount=completeWordCount;
  	}
  
  	public Integer getCompleteWordCount() {
  	  return this.completeWordCount;
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

	public Boolean isCurrentStudyBook() {
		return currentStudyBook;
	}



	public void setCurrentStudyBook(Boolean currentStudyBook) {
		this.currentStudyBook = currentStudyBook;
	}
}
