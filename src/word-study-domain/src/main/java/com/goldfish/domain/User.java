package com.goldfish.domain;

import java.io.Serializable;

/**
 * @author hellosscat
 * @since 2018-5-2
 * User Domain 类
 */
public class User  implements Serializable {
	
  private static final long serialVersionUID = 8364227660054241166L;
	
	/**  id  */
	private Long id; 
	/**  账户名  */
	private String userId; 
	/**  密码  */
	private String passwd; 
	/**  用户code  */
	private String userCode; 
	/**  别名  */
	private String nikeName; 
	/**  角色  */
	private Integer roleType; 
	/**  权限等级  */
	private Integer authorityLevel; 
	/**  父ID  */
	private Long parentId; 
	/**  激活码  */
	private String activateCode; 
	/**  用户状态  */
	private Integer userState; 
	/**  登录次数  */
	private Long totalLoginTimes; 
	/**  当前班级  */
	private Long currentClass; 
	/**  当前老师  */
	private Long currentTeacher; 
	/**  等级  */
	private Integer level; 
	/**  积分  */
	private Long cashPoint; 
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
	
	  	

  	public void setUserId(String userId) {
  	  this.userId=userId;
  	}
  
  	public String getUserId() {
  	  return this.userId;
  	}
	
	  	

  	public void setPasswd(String passwd) {
  	  this.passwd=passwd;
  	}
  
  	public String getPasswd() {
  	  return this.passwd;
  	}
	
	  	

  	public void setUserCode(String userCode) {
  	  this.userCode=userCode;
  	}
  
  	public String getUserCode() {
  	  return this.userCode;
  	}
	
	  	

  	public void setNikeName(String nikeName) {
  	  this.nikeName=nikeName;
  	}
  
  	public String getNikeName() {
  	  return this.nikeName;
  	}
	
	  	

  	public void setRoleType(Integer roleType) {
  	  this.roleType=roleType;
  	}
  
  	public Integer getRoleType() {
  	  return this.roleType;
  	}
	
	  	

  	public void setAuthorityLevel(Integer authorityLevel) {
  	  this.authorityLevel=authorityLevel;
  	}
  
  	public Integer getAuthorityLevel() {
  	  return this.authorityLevel;
  	}
	
	  	

  	public void setParentId(Long parentId) {
  	  this.parentId=parentId;
  	}
  
  	public Long getParentId() {
  	  return this.parentId;
  	}
	
	  	

  	public void setActivateCode(String activateCode) {
  	  this.activateCode=activateCode;
  	}
  
  	public String getActivateCode() {
  	  return this.activateCode;
  	}
	
	  	

  	public void setUserState(Integer userState) {
  	  this.userState=userState;
  	}
  
  	public Integer getUserState() {
  	  return this.userState;
  	}
	
	  	

  	public void setTotalLoginTimes(Long totalLoginTimes) {
  	  this.totalLoginTimes=totalLoginTimes;
  	}
  
  	public Long getTotalLoginTimes() {
  	  return this.totalLoginTimes;
  	}
	
	  	

  	public void setCurrentClass(Long currentClass) {
  	  this.currentClass=currentClass;
  	}
  
  	public Long getCurrentClass() {
  	  return this.currentClass;
  	}
	
	  	

  	public void setCurrentTeacher(Long currentTeacher) {
  	  this.currentTeacher=currentTeacher;
  	}
  
  	public Long getCurrentTeacher() {
  	  return this.currentTeacher;
  	}
	
	  	

  	public void setLevel(Integer level) {
  	  this.level=level;
  	}
  
  	public Integer getLevel() {
  	  return this.level;
  	}
	
	  	

  	public void setCashPoint(Long cashPoint) {
  	  this.cashPoint=cashPoint;
  	}
  
  	public Long getCashPoint() {
  	  return this.cashPoint;
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
