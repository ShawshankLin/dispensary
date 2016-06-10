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


public class PrescriptionInfoQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** 序列号 */
	private java.lang.Integer presId;
	/** 病历单ID */
	private java.lang.String caseId;
	/** 开单日期 */
	private java.util.Date persDateBegin;
	private java.util.Date persDateEnd;
	private java.util.Date persDateString;
	/** 0已处理1未处理 */
	private java.lang.Integer state;
	/** 药品总价 */
	private java.lang.Float drugSum;

	public java.lang.Integer getPresId() {
		return this.presId;
	}
	
	public void setPresId(java.lang.Integer value) {
		this.presId = value;
	}
	
	public java.lang.String getCaseId() {
		return this.caseId;
	}
	
	public void setCaseId(java.lang.String value) {
		this.caseId = value;
	}
	
	public java.util.Date getPersDateBegin() {
		return this.persDateBegin;
	}
	
	public void setPersDateBegin(java.util.Date value) {
		this.persDateBegin = value;
	}	
	
	public java.util.Date getPersDateEnd() {
		return this.persDateEnd;
	}
	
	public void setPersDateEnd(java.util.Date value) {
		this.persDateEnd = value;
	}
	


	public java.util.Date getPersDateString() {
		return persDateString;
	}

	public void setPersDateString(java.util.Date persDateString) {
		this.persDateString = persDateString;
	}

	public java.lang.Integer getState() {
		return this.state;
	}
	
	public void setState(java.lang.Integer value) {
		this.state = value;
	}
	
	public java.lang.Float getDrugSum() {
		return this.drugSum;
	}
	
	public void setDrugSum(java.lang.Float value) {
		this.drugSum = value;
	}
	
		private java.lang.String caseIdModelTag;
		public java.lang.String getCaseIdModelTag(){
			return this.caseIdModelTag;
		}
		
		public  void setCaseIdModelTag(String patiCaseHistoryTag){
			this.caseIdModelTag=patiCaseHistoryTag;
		}
	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

