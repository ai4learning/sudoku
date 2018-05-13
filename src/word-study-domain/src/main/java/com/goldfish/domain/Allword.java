/**
 * Copyright(c) 2004-2018 bianfeng
 */


package com.goldfish.domain;

import java.io.Serializable;


/**
 * @author hellosscat
 * @since 2018-5-8
 * 原始单词 Domain 类
 */
public class Allword  implements Serializable {
	
  private static final long serialVersionUID = -6489606667018979464L;
	
	/**  Id  */
	private Integer id; 
	/**  vocCode  */
	private String voccode; 
	/**  spelling  */
	private String spelling; 
	/**  meaning  */
	private String meaning; 
	/**  soundMarkUs  */
	private String soundmarkus; 
	/**  soundMarkUk  */
	private String soundmarkuk; 
	/**  UnitId  */
	private String unitid; 
	/**  unitNbr  */
	private String unitnbr; 
	/**  lessonNbr  */
	private String lessonnbr; 
	/**  fstClassId  */
	private String fstclassid; 
	/**  vocIndex  */
	private String vocindex; 
	/**  isCollected  */
	private String iscollected; 

  	public void setId(Integer id) {
  	  this.id=id;
  	}
  
  	public Integer getId() {
  	  return this.id;
  	}
	
	  	

  	public void setVoccode(String voccode) {
  	  this.voccode=voccode;
  	}
  
  	public String getVoccode() {
  	  return this.voccode;
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
	
	  	

  	public void setSoundmarkus(String soundmarkus) {
  	  this.soundmarkus=soundmarkus;
  	}
  
  	public String getSoundmarkus() {
  	  return this.soundmarkus;
  	}
	
	  	

  	public void setSoundmarkuk(String soundmarkuk) {
  	  this.soundmarkuk=soundmarkuk;
  	}
  
  	public String getSoundmarkuk() {
  	  return this.soundmarkuk;
  	}
	
	  	

  	public void setUnitid(String unitid) {
  	  this.unitid=unitid;
  	}
  
  	public String getUnitid() {
  	  return this.unitid;
  	}
	
	  	

  	public void setUnitnbr(String unitnbr) {
  	  this.unitnbr=unitnbr;
  	}
  
  	public String getUnitnbr() {
  	  return this.unitnbr;
  	}
	
	  	

  	public void setLessonnbr(String lessonnbr) {
  	  this.lessonnbr=lessonnbr;
  	}
  
  	public String getLessonnbr() {
  	  return this.lessonnbr;
  	}
	
	  	

  	public void setFstclassid(String fstclassid) {
  	  this.fstclassid=fstclassid;
  	}
  
  	public String getFstclassid() {
  	  return this.fstclassid;
  	}
	
	  	

  	public void setVocindex(String vocindex) {
  	  this.vocindex=vocindex;
  	}
  
  	public String getVocindex() {
  	  return this.vocindex;
  	}
	
	  	

  	public void setIscollected(String iscollected) {
  	  this.iscollected=iscollected;
  	}
  
  	public String getIscollected() {
  	  return this.iscollected;
  	}
	
	  	

}
