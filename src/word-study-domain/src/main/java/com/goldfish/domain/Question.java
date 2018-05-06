package com.goldfish.domain;

import java.io.Serializable;

/**
 * @author hellosscat
 * @since 2018-5-2
 * Question Domain 类
 */
public class Question  implements Serializable {
	
  private static final long serialVersionUID = -8219524983900477791L;
	
	/**  ID  */
	private Long id; 
	/**  试题类型  */
	private Integer questionType; 
	/**  课程ID  */
	private Integer lessonId; 
	/**  单元号  */
	private Integer unitNbr; 
	/**  单词ID  */
	private Integer wordId; 
	/**  单词CODE  */
	private String vocCode; 
	/**  单词拼写  */
	private String spelling; 
	/**  题描述  */
	private String question; 
	/**  选项  */
	private String choices; 
	/**  正确答案  */
	private String answerIndex; 
	/**  难易层度  */
	private Integer level; 
	/**  状态  */
	private Integer state; 
	/**  创建时间  */
	private java.util.Date created; 
	/**  修改时间  */
	private java.util.Date modified; 

  	public void setId(Long id) {
  	  this.id=id;
  	}
  
  	public Long getId() {
  	  return this.id;
  	}
	
	  	

  	public void setQuestionType(Integer questionType) {
  	  this.questionType=questionType;
  	}
  
  	public Integer getQuestionType() {
  	  return this.questionType;
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
	
	  	

  	public void setWordId(Integer wordId) {
  	  this.wordId=wordId;
  	}
  
  	public Integer getWordId() {
  	  return this.wordId;
  	}
	
	  	

  	public void setVocCode(String vocCode) {
  	  this.vocCode=vocCode;
  	}
  
  	public String getVocCode() {
  	  return this.vocCode;
  	}
	
	  	

  	public void setSpelling(String spelling) {
  	  this.spelling=spelling;
  	}
  
  	public String getSpelling() {
  	  return this.spelling;
  	}
	
	  	

  	public void setQuestion(String question) {
  	  this.question=question;
  	}
  
  	public String getQuestion() {
  	  return this.question;
  	}
	
	  	

  	public void setChoices(String choices) {
  	  this.choices=choices;
  	}
  
  	public String getChoices() {
  	  return this.choices;
  	}
	
	  	

  	public void setAnswerIndex(String answerIndex) {
  	  this.answerIndex=answerIndex;
  	}
  
  	public String getAnswerIndex() {
  	  return this.answerIndex;
  	}
	
	  	

  	public void setLevel(Integer level) {
  	  this.level=level;
  	}
  
  	public Integer getLevel() {
  	  return this.level;
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
