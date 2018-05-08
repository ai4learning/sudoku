/**
 * Copyright(c) 2004-2018 bianfeng
 */


package com.goldfish.domain;

import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;


/**
 * @author hellosscat
 * @since 2018-5-8
 * 登录 Domain 类
 */
public class LoginRecord  implements Serializable {
	
  private static final long serialVersionUID = 3403201262748506870L;
	
	/**  ID  */
	private Long id; 
	/**  训练ID  */
	private String wordTrainingId; 
	/**  训练CODE  */
	private String wordTraningCode; 
	/**  学习token  */
	private String studyToken; 
	/**  用户ID  */
	private Integer userId; 
	/**  用户CODE  */
	private String userCode; 
	/**  用户名  */
	private String userName; 
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
	
	  	

  	public void setWordTrainingId(String wordTrainingId) {
  	  this.wordTrainingId=wordTrainingId;
  	}
  
  	public String getWordTrainingId() {
  	  return this.wordTrainingId;
  	}
	
	  	

  	public void setWordTraningCode(String wordTraningCode) {
  	  this.wordTraningCode=wordTraningCode;
  	}
  
  	public String getWordTraningCode() {
  	  return this.wordTraningCode;
  	}
	
	  	

  	public void setStudyToken(String studyToken) {
  	  this.studyToken=studyToken;
  	}
  
  	public String getStudyToken() {
  	  return this.studyToken;
  	}
	
	  	

  	public void setUserId(Integer userId) {
  	  this.userId=userId;
  	}
  
  	public Integer getUserId() {
  	  return this.userId;
  	}
	
	  	

  	public void setUserCode(String userCode) {
  	  this.userCode=userCode;
  	}
  
  	public String getUserCode() {
  	  return this.userCode;
  	}
	
	  	

  	public void setUserName(String userName) {
  	  this.userName=userName;
  	}
  
  	public String getUserName() {
  	  return this.userName;
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
