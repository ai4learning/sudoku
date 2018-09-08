/**
 * Copyright(c) 2004-2018 bianfeng
 */


package com.goldfish.domain;

import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;


/**
 * @author hellosscat
 * @since 2018-5-8
 * 任务表 Domain 类
 */
public class Task  implements Serializable {
	
  private static final long serialVersionUID = 2965571629077273006L;
	
	/**  ID  */
	private Long id; 
	/**  用户ID  */
	private Integer userId; 
	/**  业务ID  */
	private Long businessId; 
	/**  类型  */
	private Integer type; 
	/**  任务内容  */
	private String content; 
	/**  扩展信息  */
	private String ext; 
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
	
	  	

  	public void setUserId(Integer userId) {
  	  this.userId=userId;
  	}
  
  	public Integer getUserId() {
  	  return this.userId;
  	}
	
	  	

  	public void setBusinessId(Long businessId) {
  	  this.businessId=businessId;
  	}
  
  	public Long getBusinessId() {
  	  return this.businessId;
  	}
	
	  	

  	public void setType(Integer type) {
  	  this.type=type;
  	}
  
  	public Integer getType() {
  	  return this.type;
  	}
	
	  	

  	public void setContent(String content) {
  	  this.content=content;
  	}
  
  	public String getContent() {
  	  return this.content;
  	}
	
	  	

  	public void setExt(String ext) {
  	  this.ext=ext;
  	}
  
  	public String getExt() {
  	  return this.ext;
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
