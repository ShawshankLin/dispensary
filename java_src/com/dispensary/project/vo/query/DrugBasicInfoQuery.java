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


public class DrugBasicInfoQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** 药品编码 */
	private java.lang.Integer drugId;
	/** 药品名称 */
	private java.lang.String drugName;
	/** 拼音码 */
	private java.lang.String drugPingyin;
	/** 包药单位 */
	private java.lang.Integer drugEffect;
	/** 发药单位 */
	private java.lang.Integer drugKickBack;
	/** 整散比 */
	private java.lang.String drugNote;
	/** 每次用量单位 */
	private java.lang.String quantityUnit;
	/** 用法 */
	private java.lang.String drugUsage;
	/** 规格 */
	private java.lang.String drugSpec;
	/** 剂型 */
	private java.lang.Integer drugTabu;
	/** 进货价 */
	private java.lang.Float costPrice;
	/** 零售价 */
	private java.lang.Float retailPrice;
	/** 生产日期 */
	private java.util.Date productionDateBegin;
	private java.util.Date productionDateEnd;
	private java.util.Date productionDate;
	/** 保质期 */
	private java.lang.Integer periodOfValidity;
	/** 药库上限 */
	private java.lang.Integer upLimit;
	/** 药库下限 */
	private java.lang.Integer downLimit;
	/** 药房上限 */
	private java.lang.Integer upLimit1;
	/** 药房下限 */
	private java.lang.Integer downLimit1;
	/** 症状ID  */
	private java.lang.Integer symptomId;
	/** 是否处方药品 */
	private java.lang.Integer isDrug;
	/** supplierId */
	private java.lang.Integer supplierId;
	/** */
	private java.lang.Integer feeTypeId;

	public java.lang.Integer getDrugId() {
		return this.drugId;
	}
	
	public void setDrugId(java.lang.Integer value) {
		this.drugId = value;
	}
	
	public java.lang.String getDrugName() {
		return this.drugName;
	}
	
	public void setDrugName(java.lang.String value) {
		this.drugName = value;
	}
	
	public java.lang.String getDrugPingyin() {
		return this.drugPingyin;
	}
	
	public void setDrugPingyin(java.lang.String value) {
		this.drugPingyin = value;
	}
	
	public java.util.Date getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(java.util.Date productionDate) {
		this.productionDate = productionDate;
	}

	public java.lang.Integer getDrugEffect() {
		return this.drugEffect;
	}
	
	public void setDrugEffect(java.lang.Integer value) {
		this.drugEffect = value;
	}
	
	public java.lang.Integer getDrugKickBack() {
		return this.drugKickBack;
	}
	
	public void setDrugKickBack(java.lang.Integer value) {
		this.drugKickBack = value;
	}
	
	public java.lang.String getDrugNote() {
		return this.drugNote;
	}
	
	public void setDrugNote(java.lang.String value) {
		this.drugNote = value;
	}
	
	public java.lang.String getQuantityUnit() {
		return this.quantityUnit;
	}
	
	public void setQuantityUnit(java.lang.String value) {
		this.quantityUnit = value;
	}
	
	public java.lang.String getDrugUsage() {
		return this.drugUsage;
	}
	
	public void setDrugUsage(java.lang.String value) {
		this.drugUsage = value;
	}
	
	public java.lang.String getDrugSpec() {
		return this.drugSpec;
	}
	
	public void setDrugSpec(java.lang.String value) {
		this.drugSpec = value;
	}
	
	public java.lang.Integer getDrugTabu() {
		return this.drugTabu;
	}
	
	public void setDrugTabu(java.lang.Integer value) {
		this.drugTabu = value;
	}
	
	
	
	public java.lang.Float getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(java.lang.Float costPrice) {
		this.costPrice = costPrice;
	}

	public java.lang.Float getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(java.lang.Float retailPrice) {
		this.retailPrice = retailPrice;
	}

	public java.util.Date getProductionDateBegin() {
		return this.productionDateBegin;
	}
	
	public void setProductionDateBegin(java.util.Date value) {
		this.productionDateBegin = value;
	}	
	
	public java.util.Date getProductionDateEnd() {
		return this.productionDateEnd;
	}
	
	public void setProductionDateEnd(java.util.Date value) {
		this.productionDateEnd = value;
	}
	
	public java.lang.Integer getPeriodOfValidity() {
		return this.periodOfValidity;
	}
	
	public void setPeriodOfValidity(java.lang.Integer value) {
		this.periodOfValidity = value;
	}
	
	public java.lang.Integer getUpLimit() {
		return this.upLimit;
	}
	
	public void setUpLimit(java.lang.Integer value) {
		this.upLimit = value;
	}
	
	public java.lang.Integer getDownLimit() {
		return this.downLimit;
	}
	
	public void setDownLimit(java.lang.Integer value) {
		this.downLimit = value;
	}
	
	public java.lang.Integer getUpLimit1() {
		return this.upLimit1;
	}
	
	public void setUpLimit1(java.lang.Integer value) {
		this.upLimit1 = value;
	}
	
	public java.lang.Integer getDownLimit1() {
		return this.downLimit1;
	}
	
	public void setDownLimit1(java.lang.Integer value) {
		this.downLimit1 = value;
	}
	
	public java.lang.Integer getSymptomId() {
		return this.symptomId;
	}
	
	public void setSymptomId(java.lang.Integer value) {
		this.symptomId = value;
	}
	
	public java.lang.Integer getIsDrug() {
		return this.isDrug;
	}
	
	public void setIsDrug(java.lang.Integer value) {
		this.isDrug = value;
	}
	
	public java.lang.Integer getSupplierId() {
		return this.supplierId;
	}
	
	public void setSupplierId(java.lang.Integer value) {
		this.supplierId = value;
	}
	
		public java.lang.Integer getFeeTypeId() {
		return feeTypeId;
	}

	public void setFeeTypeId(java.lang.Integer feeTypeId) {
		this.feeTypeId = feeTypeId;
	}

		private java.lang.String symptomIdModelTag;
		private java.lang.String drugTabuModelTag;
		private java.lang.String supplierIdModelTag;
		private java.lang.String drugEffectModelTag;
		private java.lang.String drugKickBackModelTag;
		private java.lang.String feeTypeIdModelTag;
		public java.lang.String getSymptomIdModelTag(){
			return this.symptomIdModelTag;
		}
		
		public  void setSymptomIdModelTag(String symptomTag){
			this.symptomIdModelTag=symptomTag;
		}
		public java.lang.String getDrugTabuModelTag(){
			return this.drugTabuModelTag;
		}
		
		public  void setDrugTabuModelTag(String drugunitTag){
			this.drugTabuModelTag=drugunitTag;
		}
		public java.lang.String getSupplierIdModelTag(){
			return this.supplierIdModelTag;
		}
		
		public  void setSupplierIdModelTag(String supplierTag){
			this.supplierIdModelTag=supplierTag;
		}
		public java.lang.String getDrugEffectModelTag(){
			return this.drugEffectModelTag;
		}
		
		public  void setDrugEffectModelTag(String drugunitTag){
			this.drugEffectModelTag=drugunitTag;
		}
		public java.lang.String getDrugKickBackModelTag(){
			return this.drugKickBackModelTag;
		}
		
		public  void setDrugKickBackModelTag(String drugunitTag){
			this.drugKickBackModelTag=drugunitTag;
		}
		public java.lang.String getFeeTypeIdModelTag(){
			return this.feeTypeIdModelTag;
		}
		
		public  void setFeeTypeIdModelTag(String feetypeTag){
			this.feeTypeIdModelTag=feetypeTag;
		}
	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

