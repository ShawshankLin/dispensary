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


public class ReceiptQuery extends BaseQuery implements Serializable {
    private static final Long serialVersionUID = 3148176768559230877L;
    

	/** 收据单ID */
	private java.lang.Integer recId;
	/** 处方单编号 */
	private java.lang.Integer presId;
	/** 药品总售价 */
	private Float drugFee;
	/** 换药费 */
	private Float transferFee;
	/** 输氧费 */
	private Float oxygenFee;
	/** 理疗 */
	private Float physicalFee;
	/** 急诊费 */
	private Float emergencyFee;
	/** 注射费用 */
	private Float injectionFee;
	/** 超声雾化 */
	private Float nebulizationFee;
	/** 挂号费用 */
	private Float registerFee;
	/** 费用总计 */
	private Float feeSum;
	/** 收费员ID  */
	private java.lang.Integer meStId;
	/** 收费日期 */
	private java.util.Date recDateBegin;
	private java.util.Date recDateEnd;

	public java.lang.Integer getRecId() {
		return this.recId;
	}
	
	public void setRecId(java.lang.Integer value) {
		this.recId = value;
	}
	
	public java.lang.Integer getPresId() {
		return this.presId;
	}
	
	public void setPresId(java.lang.Integer value) {
		this.presId = value;
	}
	
	public Float getDrugFee() {
		return this.drugFee;
	}
	
	public void setDrugFee(Float value) {
		this.drugFee = value;
	}
	
	public Float getTransferFee() {
		return this.transferFee;
	}
	
	public void setTransferFee(Float value) {
		this.transferFee = value;
	}
	
	public Float getOxygenFee() {
		return this.oxygenFee;
	}
	
	public void setOxygenFee(Float value) {
		this.oxygenFee = value;
	}
	
	public Float getPhysicalFee() {
		return this.physicalFee;
	}
	
	public void setPhysicalFee(Float value) {
		this.physicalFee = value;
	}
	
	public Float getEmergencyFee() {
		return this.emergencyFee;
	}
	
	public void setEmergencyFee(Float value) {
		this.emergencyFee = value;
	}
	
	public Float getInjectionFee() {
		return this.injectionFee;
	}
	
	public void setInjectionFee(Float value) {
		this.injectionFee = value;
	}
	
	public Float getNebulizationFee() {
		return this.nebulizationFee;
	}
	
	public void setNebulizationFee(Float value) {
		this.nebulizationFee = value;
	}
	
	public Float getRegisterFee() {
		return this.registerFee;
	}
	
	public void setRegisterFee(Float value) {
		this.registerFee = value;
	}
	
	public Float getFeeSum() {
		return this.feeSum;
	}
	
	public void setFeeSum(Float value) {
		this.feeSum = value;
	}
	
	public java.lang.Integer getMeStId() {
		return this.meStId;
	}
	
	public void setMeStId(java.lang.Integer value) {
		this.meStId = value;
	}
	
	public java.util.Date getRecDateBegin() {
		return this.recDateBegin;
	}
	
	public void setRecDateBegin(java.util.Date value) {
		this.recDateBegin = value;
	}	
	
	public java.util.Date getRecDateEnd() {
		return this.recDateEnd;
	}
	
	public void setRecDateEnd(java.util.Date value) {
		this.recDateEnd = value;
	}
	
		private java.lang.String meStIdModelTag;
		private java.lang.String presIdModelTag;
		public java.lang.String getMeStIdModelTag(){
			return this.meStIdModelTag;
		}
		
		public  void setMeStIdModelTag(String dispensarystaffTag){
			this.meStIdModelTag=dispensarystaffTag;
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

