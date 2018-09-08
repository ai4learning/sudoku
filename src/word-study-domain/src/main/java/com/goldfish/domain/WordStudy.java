/**
 * Copyright(c) 2004-2018 bianfeng
 */


package com.goldfish.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;


/**
 * @author hellosscat
 * @since 2018-5-8
 * 单词学习 Domain 类
 */
public class WordStudy  implements Serializable {
	
  private static final long serialVersionUID = -7262870976609136018L;
	
	/**  ID  */
	private Long id;
	/**  学生ID  */
	private Integer studentId;
	private Integer lessonId;
	private Integer unitNbr;
	/**  单词CODE  */
	private String vocCode;
	/**  用户单词CODE  */
	private String userVocCode;
	private Integer wordId;
	/**  单词拼写  */
	private String spell;
	private String meaning;
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
	private Integer isFstReadSuccess;
	/**  读失败次数  */
	private Integer readFailTimes; 
	/**  持续读失败次数  */
	private Integer continueReadFailTimes; 
	/**  是否半读  */
	private Integer isHalfReading;
	/**  是否第一个就拼写成功  */
	private Integer isFstSpellSuccess;
	/**  拼写错误次数  */
	private Integer spellFailTimes; 
	/**  连续拼写错误次数  */
	private Integer continueSpellFailTimes; 
	/**  是否为半写  */
	private Integer isHalfSpelling;
	/**  是否记住  */
	private Integer isRemember;
	/**  是否取消复习  */
	private Integer isCancelReview;
	/**  是否被收藏  */
	private Integer iscollected;
	/**  状态  */
	private Integer state; 
	/**  创建时间  */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date created; 
	/**  更新时间  */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date modified;
    /**
     * 学习时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private java.util.Date studied;

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
	
	  	

  	public void setIsFstReadSuccess(Integer isFstReadSuccess) {
  	  this.isFstReadSuccess=isFstReadSuccess;
  	}
  
  	public Integer getIsFstReadSuccess() {
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
	
	  	

  	public void setIsHalfReading(Integer isHalfReading) {
  	  this.isHalfReading=isHalfReading;
  	}
  
  	public Integer getIsHalfReading() {
  	  return this.isHalfReading;
  	}
	
	  	

  	public void setIsFstSpellSuccess(Integer isFstSpellSuccess) {
  	  this.isFstSpellSuccess=isFstSpellSuccess;
  	}
  
  	public Integer getIsFstSpellSuccess() {
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
	
	  	

  	public void setIsHalfSpelling(Integer isHalfSpelling) {
  	  this.isHalfSpelling=isHalfSpelling;
  	}
  
  	public Integer getIsHalfSpelling() {
  	  return this.isHalfSpelling;
  	}
	
	  	

  	public void setIsRemember(Integer isRemember) {
  	  this.isRemember=isRemember;
  	}
  
  	public Integer getIsRemember() {
  	  return this.isRemember;
  	}
	
	  	

  	public void setIsCancelReview(Integer isCancelReview) {
  	  this.isCancelReview=isCancelReview;
  	}
  
  	public Integer getIsCancelReview() {
  	  return this.isCancelReview;
  	}
	
	  	

  	public void setIscollected(Integer iscollected) {
  	  this.iscollected=iscollected;
  	}
  
  	public Integer getIscollected() {
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

	public Integer getWordId() {
		return wordId;
	}

	public void setWordId(Integer wordId) {
		this.wordId = wordId;
	}

	public String getMeaning() {
		return meaning;
	}

	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}

    public Date getStudied() {
        return studied;
    }

    public void setStudied(Date studied) {
        this.studied = studied;
    }

	public Integer getLessonId() {
		return lessonId;
	}

	public void setLessonId(Integer lessonId) {
		this.lessonId = lessonId;
	}

	public Integer getUnitNbr() {
		return unitNbr;
	}

	public void setUnitNbr(Integer unitNbr) {
		this.unitNbr = unitNbr;
	}
}
