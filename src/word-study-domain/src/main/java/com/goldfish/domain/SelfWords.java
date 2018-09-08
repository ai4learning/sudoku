/**
 * Copyright(c) 2004-2018 bianfeng
 */


package com.goldfish.domain;

import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;


/**
 * @author hellosscat
 * @since 2018-5-8
 * 学生自身单词 Domain 类
 */
public class SelfWords  implements Serializable {
	
  private static final long serialVersionUID = 2025611733372198279L;
	
	/**  ID  */
	private Long id; 
	/**  模块CODE  */
	private String moduleCode; 
	/**  学生ID  */
	private Long studyId; 
	/**  类型  */
	private Integer type; 
	/**  学生ID  */
	private Long studentId; 
	/**  用户CODE  */
	private String userCode; 
	/**  课程ID  */
	private Integer lessonId; 
	/**  单元号  */
	private Integer unitNbr; 
	/**  单词CODE  */
	private String vocCode; 
	/**  单词索引  */
	private Integer vocIndex; 
	/**  单词ID  */
	private Integer wordId; 
	/**  单词拼写  */
	private String spelling; 
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
	
	  	

  	public void setModuleCode(String moduleCode) {
  	  this.moduleCode=moduleCode;
  	}
  
  	public String getModuleCode() {
  	  return this.moduleCode;
  	}
	
	  	

  	public void setStudyId(Long studyId) {
  	  this.studyId=studyId;
  	}
  
  	public Long getStudyId() {
  	  return this.studyId;
  	}
	
	  	

  	public void setType(Integer type) {
  	  this.type=type;
  	}
  
  	public Integer getType() {
  	  return this.type;
  	}
	
	  	

  	public void setStudentId(Long studentId) {
  	  this.studentId=studentId;
  	}
  
  	public Long getStudentId() {
  	  return this.studentId;
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
	
	  	

  	public void setVocCode(String vocCode) {
  	  this.vocCode=vocCode;
  	}
  
  	public String getVocCode() {
  	  return this.vocCode;
  	}
	
	  	

  	public void setVocIndex(Integer vocIndex) {
  	  this.vocIndex=vocIndex;
  	}
  
  	public Integer getVocIndex() {
  	  return this.vocIndex;
  	}
	
	  	

  	public void setWordId(Integer wordId) {
  	  this.wordId=wordId;
  	}
  
  	public Integer getWordId() {
  	  return this.wordId;
  	}
	
	  	

  	public void setSpelling(String spelling) {
  	  this.spelling=spelling;
  	}
  
  	public String getSpelling() {
  	  return this.spelling;
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
