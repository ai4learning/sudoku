package com.goldfish.domain;

import java.io.Serializable;

/**
 * @author hellosscat
 * @since 2018-5-2
 * Course Domain 类
 */
public class Course  implements Serializable {
	
  private static final long serialVersionUID = 8573608387962473452L;
	
	/**  ID  */
	private Integer id; 
	/**  课程类型  */
	private Integer type; 
	/**  课程CODE  */
	private String moduleCode; 
	/**  课程号  */
	private Integer bookNumber; 
	/**  课程名  */
	private String bookName; 
	/**  课程类型  */
	private Integer bookType; 
	/**  年级类型  */
	private Integer orderType; 
	/**  课程状态  */
	private Integer bookState; 
	/**  课程定价  */
	private java.math.BigDecimal bookPrice; 
	/**  课程简介  */
	private String introduce; 
	/**  封面  */
	private String coverImageUrl; 
	/**  总单元数  */
	private Integer totalUnitNbr; 
	/**  单词总数  */
	private Integer totalWords; 
	/**  是否过期  */
	private String outDate; 
	/**  单元类型  */
	private String unitType; 
	/**  扩展信息  */
	private String ext; 
	/**  创建时间  */
	private java.util.Date created; 
	/**  修改时间  */
	private java.util.Date modified; 

  	public void setId(Integer id) {
  	  this.id=id;
  	}
  
  	public Integer getId() {
  	  return this.id;
  	}
	
	  	

  	public void setType(Integer type) {
  	  this.type=type;
  	}
  
  	public Integer getType() {
  	  return this.type;
  	}
	
	  	

  	public void setModuleCode(String moduleCode) {
  	  this.moduleCode=moduleCode;
  	}
  
  	public String getModuleCode() {
  	  return this.moduleCode;
  	}
	
	  	

  	public void setBookNumber(Integer bookNumber) {
  	  this.bookNumber=bookNumber;
  	}
  
  	public Integer getBookNumber() {
  	  return this.bookNumber;
  	}
	
	  	

  	public void setBookName(String bookName) {
  	  this.bookName=bookName;
  	}
  
  	public String getBookName() {
  	  return this.bookName;
  	}
	
	  	

  	public void setBookType(Integer bookType) {
  	  this.bookType=bookType;
  	}
  
  	public Integer getBookType() {
  	  return this.bookType;
  	}
	
	  	

  	public void setOrderType(Integer orderType) {
  	  this.orderType=orderType;
  	}
  
  	public Integer getOrderType() {
  	  return this.orderType;
  	}
	
	  	

  	public void setBookState(Integer bookState) {
  	  this.bookState=bookState;
  	}
  
  	public Integer getBookState() {
  	  return this.bookState;
  	}
	
	  	

  	public void setBookPrice(java.math.BigDecimal bookPrice) {
  	  this.bookPrice=bookPrice;
  	}
  
  	public java.math.BigDecimal getBookPrice() {
  	  return this.bookPrice;
  	}
	
	  	

  	public void setIntroduce(String introduce) {
  	  this.introduce=introduce;
  	}
  
  	public String getIntroduce() {
  	  return this.introduce;
  	}
	
	  	

  	public void setCoverImageUrl(String coverImageUrl) {
  	  this.coverImageUrl=coverImageUrl;
  	}
  
  	public String getCoverImageUrl() {
  	  return this.coverImageUrl;
  	}
	
	  	

  	public void setTotalUnitNbr(Integer totalUnitNbr) {
  	  this.totalUnitNbr=totalUnitNbr;
  	}
  
  	public Integer getTotalUnitNbr() {
  	  return this.totalUnitNbr;
  	}
	
	  	

  	public void setTotalWords(Integer totalWords) {
  	  this.totalWords=totalWords;
  	}
  
  	public Integer getTotalWords() {
  	  return this.totalWords;
  	}
	
	  	

  	public void setOutDate(String outDate) {
  	  this.outDate=outDate;
  	}
  
  	public String getOutDate() {
  	  return this.outDate;
  	}
	
	  	

  	public void setUnitType(String unitType) {
  	  this.unitType=unitType;
  	}
  
  	public String getUnitType() {
  	  return this.unitType;
  	}
	
	  	

  	public void setExt(String ext) {
  	  this.ext=ext;
  	}
  
  	public String getExt() {
  	  return this.ext;
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
