/**
 * Copyright(c) 2004-2018 bianfeng
 */


package com.goldfish.domain;

import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;


/**
 * @author hellosscat
 * @since 2018-5-8
 * 单词学习统计 Domain 类
 */
public class WordStudyStatistic  implements Serializable {
	
  private static final long serialVersionUID = -5200108958347791231L;
	
	/**  ID  */
	private Long id; 
	/**  学生ID  */
	private Integer studentId; 
	/**  单词数  */
	private Integer count; 
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
	
	  	

  	public void setStudentId(Integer studentId) {
  	  this.studentId=studentId;
  	}
  
  	public Integer getStudentId() {
  	  return this.studentId;
  	}
	
	  	

  	public void setCount(Integer count) {
  	  this.count=count;
  	}
  
  	public Integer getCount() {
  	  return this.count;
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
