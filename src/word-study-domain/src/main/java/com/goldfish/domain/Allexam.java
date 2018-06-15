/**
 * Copyright(c) 2004-2018 bianfeng
 */


package com.goldfish.domain;

import java.io.Serializable;


/**
 * @author hellosscat
 * @since 2018-5-8
 * Allexam Domain 类
 */
public class Allexam  implements Serializable {
	
  private static final long serialVersionUID = -7360270956588220694L;
	
	/**  Id  */
	private Integer id; 
	/**  type  */
	private String type; 
	/**  answerIndex  */
	private String answerindex; 
	/**  choices  */
	private String choices; 
	/**  spelling  */
	private String spelling; 
	/**  vocCode  */
	private String voccode; 
	/**  question  */
	private String question; 

  	public void setId(Integer id) {
  	  this.id=id;
  	}
  
  	public Integer getId() {
  	  return this.id;
  	}
	
	  	

  	public void setType(String type) {
  	  this.type=type;
  	}
  
  	public String getType() {
  	  return this.type;
  	}
	
	  	

  	public void setAnswerindex(String answerindex) {
  	  this.answerindex=answerindex;
  	}
  
  	public String getAnswerindex() {
  	  return this.answerindex;
  	}
	
	  	

  	public void setChoices(String choices) {
  	  this.choices=choices;
  	}
  
  	public String getChoices() {
  	  return this.choices;
  	}
	
	  	

  	public void setSpelling(String spelling) {
  	  this.spelling=spelling;
  	}
  
  	public String getSpelling() {
  	  return this.spelling;
  	}
	
	  	

  	public void setVoccode(String voccode) {
  	  this.voccode=voccode;
  	}
  
  	public String getVoccode() {
  	  return this.voccode;
  	}
	
	  	

  	public void setQuestion(String question) {
  	  this.question=question;
  	}
  
  	public String getQuestion() {
  	  return this.question;
  	}
	
	  	

}
