package com.goldfish.domain;

import java.io.Serializable;

/**
 * @author hellosscat
 * @since 2018-5-2
 * ActivateCode Domain 类
 */
public class ActivateCode  implements Serializable {
	
  private static final long serialVersionUID = -4984542524462387073L;
	
	/**  ID  */
	private Long id; 
	/**  激活码  */
	private String activateCode; 
	/**  课程ID  */
	private Long lessonId; 
	/**  超时时间  */
	private java.util.Date expireTime; 
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
	
	  	

  	public void setActivateCode(String activateCode) {
  	  this.activateCode=activateCode;
  	}
  
  	public String getActivateCode() {
  	  return this.activateCode;
  	}
	
	  	

  	public void setLessonId(Long lessonId) {
  	  this.lessonId=lessonId;
  	}
  
  	public Long getLessonId() {
  	  return this.lessonId;
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
