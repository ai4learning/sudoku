/**
 * Copyright(c) 2004-2018 bianfeng
 */


package com.goldfish.domain;

import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;


/**
 * @author hellosscat
 * @since 2018-5-8
 * 单元内单词 Domain 类
 */
public class UnitWords  implements Serializable {
	
  private static final long serialVersionUID = -3095041461388608385L;
	
	/**  ID  */
	private Long id; 
	/**  单元CODE  */
	private String unitCode; 
	/**  课程ID  */
	private Long lessonId; 
	/**  单元号  */
	private Integer unitNbr; 
	/**  单词归类  */
	private Integer fstClassId; 
	/**  单元名  */
	private String unit; 
	/**  单词CODE  */
	private String vocCode; 
	/**  单词索引号  */
	private Integer vocIndex; 
	/**  单词ID  */
	private Integer wordId; 
	/**  拼写  */
	private String spelling; 
	/**  释义  */
	private String meaning; 
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
	
	  	

  	public void setUnitCode(String unitCode) {
  	  this.unitCode=unitCode;
  	}
  
  	public String getUnitCode() {
  	  return this.unitCode;
  	}
	
	  	

  	public void setLessonId(Long lessonId) {
  	  this.lessonId=lessonId;
  	}
  
  	public Long getLessonId() {
  	  return this.lessonId;
  	}
	
	  	

  	public void setUnitNbr(Integer unitNbr) {
  	  this.unitNbr=unitNbr;
  	}
  
  	public Integer getUnitNbr() {
  	  return this.unitNbr;
  	}
	
	  	

  	public void setFstClassId(Integer fstClassId) {
  	  this.fstClassId=fstClassId;
  	}
  
  	public Integer getFstClassId() {
  	  return this.fstClassId;
  	}
	
	  	

  	public void setUnit(String unit) {
  	  this.unit=unit;
  	}
  
  	public String getUnit() {
  	  return this.unit;
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
	
	  	

  	public void setMeaning(String meaning) {
  	  this.meaning=meaning;
  	}
  
  	public String getMeaning() {
  	  return this.meaning;
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
