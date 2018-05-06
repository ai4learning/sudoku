package com.goldfish.domain;

import java.io.Serializable;

/**
 * @author hellosscat
 * @since 2018-5-2
 * Word Domain 类
 */
public class Word  implements Serializable {
	
  private static final long serialVersionUID = 6657401974266312986L;
	
	/**  ID  */
	private Integer id; 
	/**  单词拼写  */
	private String spelling; 
	/**  释义  */
	private String meaning; 
	/**  美音音标  */
	private String soundMarkUs; 
	/**  国际音标  */
	private String soundMarkUk; 
	/**  词性  */
	private String wordType; 
	/**  读音  */
	private String mediaUri; 
	/**  状态  */
	private Integer state; 
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
	
	  	

  	public void setSoundMarkUs(String soundMarkUs) {
  	  this.soundMarkUs=soundMarkUs;
  	}
  
  	public String getSoundMarkUs() {
  	  return this.soundMarkUs;
  	}
	
	  	

  	public void setSoundMarkUk(String soundMarkUk) {
  	  this.soundMarkUk=soundMarkUk;
  	}
  
  	public String getSoundMarkUk() {
  	  return this.soundMarkUk;
  	}
	
	  	

  	public void setWordType(String wordType) {
  	  this.wordType=wordType;
  	}
  
  	public String getWordType() {
  	  return this.wordType;
  	}
	
	  	

  	public void setMediaUri(String mediaUri) {
  	  this.mediaUri=mediaUri;
  	}
  
  	public String getMediaUri() {
  	  return this.mediaUri;
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
