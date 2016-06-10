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


public class PrescriptionInfoDetailQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** id */
	private java.lang.Integer id;
	/** 单据内码 */
	private java.lang.Integer presId;
	/** 药品ID */
	private java.lang.Integer drugId;
	/** 数量 */
	private java.lang.Integer amount;
	/** 次数 */
	private java.lang.String times;
	/** 天数 */
	private java.lang.Integer days;
	/** 金额 */
	private java.lang.Float drugSum;
	/** 备注 */
	private java.lang.String note;

	public java.lang.Integer getId() {
		return this.id;
	}
	
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	public java.lang.Integer getPresId() {
		return this.presId;
	}
	
	public void setPresId(java.lang.Integer value) {
		this.presId = value;
	}
	
	public java.lang.Integer getDrugId() {
		return this.drugId;
	}
	
	public void setDrugId(java.lang.Integer value) {
		this.drugId = value;
	}
	
	public java.lang.Integer getAmount() {
		return this.amount;
	}
	
	public void setAmount(java.lang.Integer value) {
		this.amount = value;
	}
	
	public java.lang.String getTimes() {
		return this.times;
	}
	
	public void setTimes(java.lang.String value) {
		this.times = value;
	}
	
	public java.lang.Integer getDays() {
		return this.days;
	}
	
	public void setDays(java.lang.Integer value) {
		this.days = value;
	}
	
	public java.lang.Float getDrugSum() {
		return this.drugSum;
	}
	
	public void setDrugSum(java.lang.Float value) {
		this.drugSum = value;
	}
	
	public java.lang.String getNote() {
		return this.note;
	}
	
	public void setNote(java.lang.String value) {
		this.note = value;
	}
	
		private java.lang.String drugIdModelTag;
		private java.lang.String presIdModelTag;
		public java.lang.String getDrugIdModelTag(){
			return this.drugIdModelTag;
		}
		
		public  void setDrugIdModelTag(String drugBasicInfoTag){
			this.drugIdModelTag=drugBasicInfoTag;
		}
		public java.lang.String getPresIdModelTag(){
			return this.presIdModelTag;
		}
		
		public  void setPresIdModelTag(String prescriptionInfoTag){
			this.presIdModelTag=prescriptionInfoTag;
		}
	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

