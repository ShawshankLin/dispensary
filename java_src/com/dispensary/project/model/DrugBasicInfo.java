/*
 */

package com.dispensary.project.model;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import javax.validation.constraints.*;
import org.hibernate.validator.constraints.*;
import org.springframework.expression.spel.ast.Assign;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;



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


@Entity
@Table(name = "drug_basic_info")
public class DrugBasicInfo extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "药品";
	public static final String ALIAS_DRUG_ID = "药品编码";
	
	public static final String ALIAS_DRUG_NAME = "药品名称";
	
	public static final String ALIAS_DRUG_PINGYIN = "拼音码";
	
	public static final String ALIAS_DRUG_EFFECT = "包药单位";
	
	public static final String ALIAS_DRUG_KICK_BACK = "发药单位";
	
	public static final String ALIAS_DRUG_NOTE = "整散比";
	
	public static final String ALIAS_QUANTITY_UNIT = "每次用量单位";
	
	public static final String ALIAS_DRUG_USAGE = "用法";
	
	public static final String ALIAS_DRUG_SPEC = "规格";
	
	public static final String ALIAS_DRUG_TABU = "剂型";
	
	public static final String ALIAS_COST_PRICE = "进货价";
	
	public static final String ALIAS_RETAIL_PRICE = "零售价";
	
	public static final String ALIAS_PRODUCTION_DATE = "生产日期";
	
	public static final String ALIAS_PERIOD_OF_VALIDITY = "保质期";
	
	public static final String ALIAS_UP_LIMIT = "药库上限";
	
	public static final String ALIAS_DOWN_LIMIT = "药库下限";
	
	public static final String ALIAS_UP_LIMIT1 = "药房上限";
	
	public static final String ALIAS_DOWN_LIMIT1 = "药房下限";
	
	public static final String ALIAS_SYMPTOM_ID = "症状ID ";
	
	public static final String ALIAS_IS_DRUG = "是否处方药品";
	
	public static final String ALIAS_SUPPLIER_ID = "供应商";
	
	
	//date formats
	public static final String FORMAT_PRODUCTION_DATE = DATE_FORMAT;
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * 药品编码       db_column: DrugID 
     */ 	
	
	private java.lang.Integer drugId;
    /**
     * 药品名称       db_column: DrugName 
     */ 	
	@Length(max=50)
	private java.lang.String drugName;
    /**
     * 拼音码       db_column: DrugPingyin 
     */ 	
	@Length(max=50)
	private java.lang.String drugPingyin;
    /**
     * 包药单位       db_column: DrugEffect 
     */ 	
	
	private java.lang.Integer drugEffect;
    /**
     * 发药单位       db_column: DrugKickBack 
     */ 	
	
	private java.lang.Integer drugKickBack;
    /**
     * 整散比       db_column: DrugNote 
     */ 	
	@Length(max=500)
	private java.lang.String drugNote;
    /**
     * 每次用量单位       db_column: QuantityUnit 
     */ 	
	
	private java.lang.Integer quantityUnit;
    /**
     * 用法       db_column: DrugUsage 
     */ 	
	@Length(max=50)
	private java.lang.String drugUsage;
    /**
     * 规格       db_column: DrugSpec 
     */ 	
	@Length(max=50)
	private java.lang.String drugSpec;
    /**
     * 剂型       db_column: DrugTabu 
     */ 	
	
	private java.lang.Integer drugTabu;
    /**
     * 进货价       db_column: CostPrice 
     */ 	
	
	private java.lang.Float costPrice;
    /**
     * 零售价       db_column: RetailPrice 
     */ 	
	
	private java.lang.Float retailPrice;
    /**
     * 生产日期       db_column: ProductionDate 
     */ 	
	
	private java.util.Date productionDate;
    /**
     * 保质期       db_column: PeriodOfValidity 
     */ 	
	
	private java.lang.Integer periodOfValidity;
    /**
     * 药库上限       db_column: UpLimit 
     */ 	
	
	private java.lang.Integer upLimit;
    /**
     * 药库下限       db_column: DownLimit 
     */ 	
	
	private java.lang.Integer downLimit;
    /**
     * 药房上限       db_column: UpLimit1 
     */ 	
	
	private java.lang.Integer upLimit1;
    /**
     * 药房下限       db_column: DownLimit1 
     */ 	
	
	private java.lang.Integer downLimit1;
    /**
     * 是否处方药品       db_column: isDrug 
     */ 	
	
	private java.lang.Integer isDrug;
    /**
     * supplierId       db_column: SupplierID 
     */ 	
	
	private java.lang.Integer supplierId;
	
    /**
     * 费用ID       db_column: FeeTypeID 
     */ 	
	private java.lang.Integer feeTypeId;
	//columns END


	public DrugBasicInfo(){
	}

	public DrugBasicInfo(
		java.lang.Integer drugId
	){
		this.drugId = drugId;
	}

	

	public void setDrugId(java.lang.Integer value) {
		this.drugId = value;
	}
	
	@Id @GeneratedValue(generator="custom-id")
	@GenericGenerator(name="custom-id", strategy = "native")
	@Column(name = "DrugID", unique = true, nullable = false, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getDrugId() {
		return this.drugId;
	}
	
	@Column(name = "DrugName", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getDrugName() {
		return this.drugName;
	}
	
	public void setDrugName(java.lang.String value) {
		this.drugName = value;
	}
	
	@Column(name = "DrugPingyin", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getDrugPingyin() {
		return this.drugPingyin;
	}
	
	public void setDrugPingyin(java.lang.String value) {
		this.drugPingyin = value;
	}
	
	@Column(name = "DrugEffect", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getDrugEffect() {
		return this.drugEffect;
	}
	
	public void setDrugEffect(java.lang.Integer value) {
		this.drugEffect = value;
	}
	
	@Column(name = "DrugKickBack", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getDrugKickBack() {
		return this.drugKickBack;
	}
	
	public void setDrugKickBack(java.lang.Integer value) {
		this.drugKickBack = value;
	}
	
	@Column(name = "DrugNote", unique = false, nullable = true, insertable = true, updatable = true, length = 500)
	public java.lang.String getDrugNote() {
		return this.drugNote;
	}
	
	public void setDrugNote(java.lang.String value) {
		this.drugNote = value;
	}
	
	@Column(name = "QuantityUnit", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getQuantityUnit() {
		return this.quantityUnit;
	}
	
	public void setQuantityUnit(java.lang.Integer value) {
		this.quantityUnit = value;
	}
	
	@Column(name = "DrugUsage", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getDrugUsage() {
		return this.drugUsage;
	}
	
	public void setDrugUsage(java.lang.String value) {
		this.drugUsage = value;
	}
	
	@Column(name = "DrugSpec", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getDrugSpec() {
		return this.drugSpec;
	}
	
	public void setDrugSpec(java.lang.String value) {
		this.drugSpec = value;
	}
	
	@Column(name = "DrugTabu", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getDrugTabu() {
		return this.drugTabu;
	}
	
	public void setDrugTabu(java.lang.Integer value) {
		this.drugTabu = value;
	}
	
	@Column(name = "CostPrice", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Float getCostPrice() {
		return this.costPrice;
	}
	
	public void setCostPrice(java.lang.Float value) {
		this.costPrice = value;
	}
	
	@Column(name = "RetailPrice", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Float getRetailPrice() {
		return this.retailPrice;
	}
	
	public void setRetailPrice(java.lang.Float value) {
		this.retailPrice = value;
	}
	
	@Transient
	public String getProductionDateString() {
		return DateConvertUtils.format(getProductionDate(), FORMAT_PRODUCTION_DATE);
	}
	public void setProductionDateString(String value) {
		setProductionDate(DateConvertUtils.parse(value, FORMAT_PRODUCTION_DATE,java.util.Date.class));
	}
	
	@Column(name = "ProductionDate", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	public java.util.Date getProductionDate() {
		return this.productionDate;
	}
	
	public void setProductionDate(java.util.Date value) {
		this.productionDate = value;
	}
	
	@Column(name = "PeriodOfValidity", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getPeriodOfValidity() {
		return this.periodOfValidity;
	}
	
	public void setPeriodOfValidity(java.lang.Integer value) {
		this.periodOfValidity = value;
	}
	
	@Column(name = "UpLimit", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getUpLimit() {
		return this.upLimit;
	}
	
	public void setUpLimit(java.lang.Integer value) {
		this.upLimit = value;
	}
	
	@Column(name = "DownLimit", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getDownLimit() {
		return this.downLimit;
	}
	
	public void setDownLimit(java.lang.Integer value) {
		this.downLimit = value;
	}
	
	@Column(name = "UpLimit1", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getUpLimit1() {
		return this.upLimit1;
	}
	
	public void setUpLimit1(java.lang.Integer value) {
		this.upLimit1 = value;
	}
	
	@Column(name = "DownLimit1", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getDownLimit1() {
		return this.downLimit1;
	}
	
	public void setDownLimit1(java.lang.Integer value) {
		this.downLimit1 = value;
	}
	
	
	@Column(name = "isDrug", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getIsDrug() {
		return this.isDrug;
	}
	
	public void setIsDrug(java.lang.Integer value) {
		this.isDrug = value;
	}
	
	@Column(name = "SupplierID", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getSupplierId() {
		return this.supplierId;
	}
	
	public void setSupplierId(java.lang.Integer value) {
		this.supplierId = value;
	}
	
	@Column(name = "FeeTypeID", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getFeeTypeId() {
		return this.feeTypeId;
	}
	
	public void setFeeTypeId(java.lang.Integer value) {
		this.feeTypeId = value;
	}
	
	//获取表标识列的值,即作为关联类时显示的值
	@Transient
	public String getModelTagValue() {
				return this.drugName;
			
	}
	
	private Set drugStockInfos = new HashSet(0);
	public void setDrugStockInfos(Set<DrugStockInfo> drugStockInfo){
		this.drugStockInfos = drugStockInfo;
	}
	
	@OneToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "drugIdModel")
	public Set<DrugStockInfo> getDrugStockInfos() {
		return drugStockInfos;
	}
	private Set drugStockInDetails = new HashSet(0);
	public void setDrugStockInDetails(Set<DrugStockInDetail> drugStockInDetail){
		this.drugStockInDetails = drugStockInDetail;
	}
	
	@OneToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "drugIdModel")
	public Set<DrugStockInDetail> getDrugStockInDetails() {
		return drugStockInDetails;
	}
	private Set prescriptionInfoDetails = new HashSet(0);
	public void setPrescriptionInfoDetails(Set<PrescriptionInfoDetail> prescriptionInfoDetail){
		this.prescriptionInfoDetails = prescriptionInfoDetail;
	}
	
	@OneToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "drugIdModel")
	public Set<PrescriptionInfoDetail> getPrescriptionInfoDetails() {
		return prescriptionInfoDetails;
	}
	private Set drugSymptomRelations = new HashSet(0);
	public void setDrugSymptomRelations(Set<DrugSymptomRelation> drugSymptomRelation){
		this.drugSymptomRelations = drugSymptomRelation;
	}
	
	@OneToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "drugIdModel")
	public Set<DrugSymptomRelation> getDrugSymptomRelations() {
		return drugSymptomRelations;
	}
	private Drugunit quantityUnitModel;
	public void setQuantityUnitModel(Drugunit drugunit){
		this.quantityUnitModel = drugunit;
	}
	
	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "QuantityUnit",nullable = false, insertable = false, updatable = false) 
	})
	public Drugunit getQuantityUnitModel() {
		return quantityUnitModel;
	}
	private Supplier supplierIdModel;
	public void setSupplierIdModel(Supplier supplier){
		this.supplierIdModel = supplier;
	}
	
	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "SupplierID",nullable = false, insertable = false, updatable = false) 
	})
	public Supplier getSupplierIdModel() {
		return supplierIdModel;
	}
	private Drugunit drugEffectModel;
	public void setDrugEffectModel(Drugunit drugunit){
		this.drugEffectModel = drugunit;
	}
	
	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "DrugEffect",nullable = false, insertable = false, updatable = false) 
	})
	public Drugunit getDrugEffectModel() {
		return drugEffectModel;
	}
	private Drugunit drugKickBackModel;
	public void setDrugKickBackModel(Drugunit drugunit){
		this.drugKickBackModel = drugunit;
	}
	
	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "DrugKickBack",nullable = false, insertable = false, updatable = false) 
	})
	public Drugunit getDrugKickBackModel() {
		return drugKickBackModel;
	}
	private Feetype feeTypeIdModel;
	public void setFeeTypeIdModel(Feetype feetype){
		this.feeTypeIdModel = feetype;
	}
	
	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "FeeTypeID",nullable = false, insertable = false, updatable = false) 
	})
	public Feetype getFeeTypeIdModel() {
		return feeTypeIdModel;
	}
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("DrugId",getDrugId())
			.append("DrugName",getDrugName())
			.append("DrugPingyin",getDrugPingyin())
			.append("DrugEffect",getDrugEffect())
			.append("DrugKickBack",getDrugKickBack())
			.append("DrugNote",getDrugNote())
			.append("QuantityUnit",getQuantityUnit())
			.append("DrugUsage",getDrugUsage())
			.append("DrugSpec",getDrugSpec())
			.append("DrugTabu",getDrugTabu())
			.append("CostPrice",getCostPrice())
			.append("RetailPrice",getRetailPrice())
			.append("ProductionDate",getProductionDate())
			.append("PeriodOfValidity",getPeriodOfValidity())
			.append("UpLimit",getUpLimit())
			.append("DownLimit",getDownLimit())
			.append("UpLimit1",getUpLimit1())
			.append("DownLimit1",getDownLimit1())
			.append("IsDrug",getIsDrug())
			.append("SupplierId",getSupplierId())
			.append("FeeTypeId",getFeeTypeId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getDrugId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof DrugBasicInfo == false) return false;
		if(this == obj) return true;
		DrugBasicInfo other = (DrugBasicInfo)obj;
		return new EqualsBuilder()
			.append(getDrugId(),other.getDrugId())
			.isEquals();
	}
}

