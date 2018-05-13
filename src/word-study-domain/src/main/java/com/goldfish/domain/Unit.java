/**
 * Copyright(c) 2004-2018 bianfeng
 */


package com.goldfish.domain;

import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;


/**
 * @author hellosscat
 * @since 2018-5-8
 * 课程单元 Domain 类
 */
public class Unit  implements Serializable {
	
  private static final long serialVersionUID = -7318057365187516194L;
	
	/**  ID  */
	private Long id; 
	/**  单元CODE  */
	private String moduleCode; 
	/**  课程ID  */
	private Long lessonId;
	/**  课程名 **/
	private String lessonName;
	/**  单元号  */
	private Integer unitNbr; 
	/**  单元名称  */
	private String unit; 
	/**  单元单词数  */
	private Integer totalWords; 
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
	
	  	

  	public void setUnit(String unit) {
  	  this.unit=unit;
  	}
  
  	public String getUnit() {
  	  return this.unit;
  	}
	
	  	

  	public void setTotalWords(Integer totalWords) {
  	  this.totalWords=totalWords;
  	}
  
  	public Integer getTotalWords() {
  	  return this.totalWords;
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

	public String getLessonName() {
		return lessonName;
	}

	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}
}
