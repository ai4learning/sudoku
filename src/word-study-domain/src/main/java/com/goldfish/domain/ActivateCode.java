/**
 * Copyright(c) 2004-2018 bianfeng
 */


package com.goldfish.domain;

import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;


/**
 * @author hellosscat
 * @since 2018-5-8
 * 激活码 Domain 类
 */
public class ActivateCode  implements Serializable {
	
  private static final long serialVersionUID = -8750541314246660208L;
	
	/**  ID  */
	private Long id; 
	/**  激活码  */
	private String activateCode; 
	/**  关联课程号  */
	private String lessonIds; 
	/**  超时时间  */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date expireTime; 
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
	
	  	

  	public void setActivateCode(String activateCode) {
  	  this.activateCode=activateCode;
  	}
  
  	public String getActivateCode() {
  	  return this.activateCode;
  	}
	
	  	

  	public void setLessonIds(String lessonIds) {
  	  this.lessonIds=lessonIds;
  	}
  
  	public String getLessonIds() {
  	  return this.lessonIds;
  	}
	
	  	

  	public void setExpireTime(java.util.Date expireTime) {
  	  this.expireTime=expireTime;
  	}
  
  	public java.util.Date getExpireTime() {
  	  return this.expireTime;
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
