/*
 */

package com.dispensary.project.vo.query;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;

import java.util.*;

import javacommon.base.*;
import javacommon.util.*;

import cn.org.rapid_framework.util.*;
import cn.org.rapid_framework.web.util.*;
import cn.org.rapid_framework.page.*;
import cn.org.rapid_framework.page.impl.*;

import com.dispensary.project.model.*;
import com.dispensary.project.dao.*;
import com.dispensary.project.service.*;
import com.dispensary.project.vo.query.*;

/**
 * @author jxx
 * @version 1.0
 * @since 1.0
 */


public class PatiCaseHistoryQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** 序列号  */
	private java.lang.Integer id;
	/** 病历单ID  */
	private java.lang.String caseId;
	/** 学号 */
	private java.lang.String stuNum;
	/** 就诊日期 */
	private java.util.Date visitDateBegin;
	private java.util.Date visitDateEnd;
	private java.util.Date visitDateString;
	/** 既往史 */
	private java.lang.String dispensaryRecord;
	/** 过敏史 */
	private java.lang.String allergy;
	/** 初诊 */
	private java.lang.String primaryCare;
	/** 复诊 */
	private java.lang.String reExamination;
	/** 主诉 */
	private java.lang.String describes;
	/** 检查情况 */
	private java.lang.String examineStatus;
	/** 初步诊断 */
	private java.lang.String firstImpress;
	/** 主治医生ID */
	private java.lang.Integer meStId;

	public java.lang.Integer getId() {
		return this.id;
	}
	
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	public java.lang.String getCaseId() {
		return this.caseId;
	}
	
	public void setCaseId(java.lang.String value) {
		this.caseId = value;
	}
	
	public java.lang.String getStuNum() {
		return this.stuNum;
	}
	
	public void setStuNum(java.lang.String value) {
		this.stuNum = value;
	}
	
	public java.util.Date getVisitDateBegin() {
		return this.visitDateBegin;
	}
	
	public void setVisitDateBegin(java.util.Date value) {
		this.visitDateBegin = value;
	}	
	
	public java.util.Date getVisitDateEnd() {
		return this.visitDateEnd;
	}
	
	public void setVisitDateEnd(java.util.Date value) {
		this.visitDateEnd = value;
	}
	
	
	public java.util.Date getVisitDateString() {
		return visitDateString;
	}

	public void setVisitDateString(java.util.Date visitDateString) {
		this.visitDateString = visitDateString;
	}

	public java.lang.String getDispensaryRecord() {
		return this.dispensaryRecord;
	}
	
	public void setDispensaryRecord(java.lang.String value) {
		this.dispensaryRecord = value;
	}
	
	public java.lang.String getAllergy() {
		return this.allergy;
	}
	
	public void setAllergy(java.lang.String value) {
		this.allergy = value;
	}
	
	public java.lang.String getPrimaryCare() {
		return this.primaryCare;
	}
	
	public void setPrimaryCare(java.lang.String value) {
		this.primaryCare = value;
	}
	
	public java.lang.String getReExamination() {
		return this.reExamination;
	}
	
	public void setReExamination(java.lang.String value) {
		this.reExamination = value;
	}
	
	public java.lang.String getDescribes() {
		return this.describes;
	}
	
	public void setDescribes(java.lang.String value) {
		this.describes = value;
	}
	
	public java.lang.String getExamineStatus() {
		return this.examineStatus;
	}
	
	public void setExamineStatus(java.lang.String value) {
		this.examineStatus = value;
	}
	
	public java.lang.String getFirstImpress() {
		return this.firstImpress;
	}
	
	public void setFirstImpress(java.lang.String value) {
		this.firstImpress = value;
	}
	
	public java.lang.Integer getMeStId() {
		return this.meStId;
	}
	
	public void setMeStId(java.lang.Integer value) {
		this.meStId = value;
	}
	
		private java.lang.String stuNumModelTag;
		private java.lang.String meStIdModelTag;
		public java.lang.String getStuNumModelTag(){
			return this.stuNumModelTag;
		}
		
		public  void setStuNumModelTag(String studentTag){
			this.stuNumModelTag=studentTag;
		}
		public java.lang.String getMeStIdModelTag(){
			return this.meStIdModelTag;
		}
		
		public  void setMeStIdModelTag(String dispensarystaffTag){
			this.meStIdModelTag=dispensarystaffTag;
		}
	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

