package com.goldfish.domain;

import java.io.Serializable;

/**
 * @author hellosscat
 * @since 2018-5-2
 * WordStudy Domain 类
 */
public class WordStudy  implements Serializable {
	
  private static final long serialVersionUID = 5978463533605384612L;
	
	/**  ID  */
	private Long id; 
	/**  单词CODE  */
	private String vocCode; 
	/**  用户单词CODE  */
	private String userVocCode; 
	/**  单词拼写  */
	private String spell; 
	/**  学生ID  */
	private Integer studentId; 
	/**  用户CODE  */
	private String userCode; 
	/**  记忆等级  */
	private Integer memoryLevel; 
	/**  复习次数  */
	private Integer reviewTimes; 
	/**  剩余时间  */
	private Long timeLeft; 
	/**  完成阅读时间  */
	private Long finishReadingTime; 
	/**  是否第一次就读成功  */
	private String isFstReadSuccess; 
	/**  读失败次数  */
	private Integer readFailTimes; 
	/**  持续读失败次数  */
	private Integer continueReadFailTimes; 
	/**  是否半读  */
	private String isHalfReading; 
	/**  是否第一个就拼写成功  */
	private String isFstSpellSuccess; 
	/**  拼写错误次数  */
	private Integer spellFailTimes; 
	/**  连续拼写错误次数  */
	private Integer continueSpellFailTimes; 
	/**  是否为半写  */
	private String isHalfSpelling; 
	/**  是否记住  */
	private String isRemember; 
	/**  是否取消复习  */
	private String isCancelReview; 
	/**  是否被收藏  */
	private String iscollected; 
	/**  状态  */
	private Integer state; 
	/**  创建时间  */
	private java.util.Date created; 
	/**  更新时间  */
	private java.util.Date modified; 

  	public void setId(Long id) {
  	  this.id=id;
  	}
  
  	public Long getId() {
  	  return this.id;
  	}
	
	  	

  	public void setVocCode(String vocCode) {
  	  this.vocCode=vocCode;
  	}
  
  	public String getVocCode() {
  	  return this.vocCode;
  	}
	
	  	

  	public void setUserVocCode(String userVocCode) {
  	  this.userVocCode=userVocCode;
  	}
  
  	public String getUserVocCode() {
  	  return this.userVocCode;
  	}
	
	  	

  	public void setSpell(String spell) {
  	  this.spell=spell;
  	}
  
  	public String getSpell() {
  	  return this.spell;
  	}
	
	  	

  	public void setStudentId(Integer studentId) {
  	  this.studentId=studentId;
  	}
  
  	public Integer getStudentId() {
  	  return this.studentId;
  	}
	
	  	

  	public void setUserCode(String userCode) {
  	  this.userCode=userCode;
  	}
  
  	public String getUserCode() {
  	  return this.userCode;
  	}
	
	  	

  	public void setMemoryLevel(Integer memoryLevel) {
  	  this.memoryLevel=memoryLevel;
  	}
  
  	public Integer getMemoryLevel() {
  	  return this.memoryLevel;
  	}
	
	  	

  	public void setReviewTimes(Integer reviewTimes) {
  	  this.reviewTimes=reviewTimes;
  	}
  
  	public Integer getReviewTimes() {
  	  return this.reviewTimes;
  	}
	
	  	

  	public void setTimeLeft(Long timeLeft) {
  	  this.timeLeft=timeLeft;
  	}
  
  	public Long getTimeLeft() {
  	  return this.timeLeft;
  	}
	
	  	

  	public void setFinishReadingTime(Long finishReadingTime) {
  	  this.finishReadingTime=finishReadingTime;
  	}
  
  	public Long getFinishReadingTime() {
  	  return this.finishReadingTime;
  	}
	
	  	

  	public void setIsFstReadSuccess(String isFstReadSuccess) {
  	  this.isFstReadSuccess=isFstReadSuccess;
  	}
  
  	public String getIsFstReadSuccess() {
  	  return this.isFstReadSuccess;
  	}
	
	  	

  	public void setReadFailTimes(Integer readFailTimes) {
  	  this.readFailTimes=readFailTimes;
  	}
  
  	public Integer getReadFailTimes() {
  	  return this.readFailTimes;
  	}
	
	  	

  	public void setContinueReadFailTimes(Integer continueReadFailTimes) {
  	  this.continueReadFailTimes=continueReadFailTimes;
  	}
  
  	public Integer getContinueReadFailTimes() {
  	  return this.continueReadFailTimes;
  	}
	
	  	

  	public void setIsHalfReading(String isHalfReading) {
  	  this.isHalfReading=isHalfReading;
  	}
  
  	public String getIsHalfReading() {
  	  return this.isHalfReading;
  	}
	
	  	

  	public void setIsFstSpellSuccess(String isFstSpellSuccess) {
  	  this.isFstSpellSuccess=isFstSpellSuccess;
  	}
  
  	public String getIsFstSpellSuccess() {
  	  return this.isFstSpellSuccess;
  	}
	
	  	

  	public void setSpellFailTimes(Integer spellFailTimes) {
  	  this.spellFailTimes=spellFailTimes;
  	}
  
  	public Integer getSpellFailTimes() {
  	  return this.spellFailTimes;
  	}
	
	  	

  	public void setContinueSpellFailTimes(Integer continueSpellFailTimes) {
  	  this.continueSpellFailTimes=continueSpellFailTimes;
  	}
  
  	public Integer getContinueSpellFailTimes() {
  	  return this.continueSpellFailTimes;
  	}
	
	  	

  	public void setIsHalfSpelling(String isHalfSpelling) {
  	  this.isHalfSpelling=isHalfSpelling;
  	}
  
  	public String getIsHalfSpelling() {
  	  return this.isHalfSpelling;
  	}
	
	  	

  	public void setIsRemember(String isRemember) {
  	  this.isRemember=isRemember;
  	}
  
  	public String getIsRemember() {
  	  return this.isRemember;
  	}
	
	  	

  	public void setIsCancelReview(String isCancelReview) {
  	  this.isCancelReview=isCancelReview;
  	}
  
  	public String getIsCancelReview() {
  	  return this.isCancelReview;
  	}
	
	  	

  	public void setIscollected(String iscollected) {
  	  this.iscollected=iscollected;
  	}
  
  	public String getIscollected() {
  	  return this.iscollected;
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
