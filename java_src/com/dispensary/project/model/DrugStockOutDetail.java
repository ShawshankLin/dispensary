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
@Table(name = "drug_stock_out_detail")
public class DrugStockOutDetail extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "DrugStockOutDetail";
	public static final String ALIAS_ID = "序列号";
	
	public static final String ALIAS_SERIAL_NUMBER = "流水号";
	
	public static final String ALIAS_DRUG_ID = "药品编码";
	
	public static final String ALIAS_OUT_AMOUNT = "出库数量";
	
	public static final String ALIAS_OUT_PRICE = "出库金额";
	
	public static final String ALIAS_OUT_PLACE_ID = "出库去向";
	
	public static final String ALIAS_FROM_PLACE_ID = "出库源位置";
	
	public static final String ALIAS_PRODUCTION_DATE = "productionDate";
	
	
	//date formats
	public static final String FORMAT_PRODUCTION_DATE = DATE_FORMAT;
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * 序列号       db_column: ID 
     */ 	
	
	private java.lang.Integer id;
    /**
     * 流水号       db_column: SerialNumber 
     */ 	
	@NotBlank @Length(max=20)
	private java.lang.String serialNumber;
    /**
     * 药品编码       db_column: DrugID 
     */ 	
	@NotNull 
	private java.lang.Integer drugId;
    /**
     * 出库数量       db_column: OutAmount 
     */ 	
	@NotNull 
	private java.lang.Integer outAmount;
    /**
     * 出库金额       db_column: OutPrice 
     */ 	
	
	private java.lang.Long outPrice;
    /**
     * 出库去向       db_column: OutPlaceID 
     */ 	
	@NotNull 
	private java.lang.Integer outPlaceId;
    /**
     * 出库源位置       db_column: FromPlaceID 
     */ 	
	@NotNull 
	private java.lang.Integer fromPlaceId;
    /**
     * productionDate       db_column: ProductionDate 
     */ 	
	
	private java.util.Date productionDate;
	//columns END


	public DrugStockOutDetail(){
	}

	public DrugStockOutDetail(
		java.lang.Integer id
	){
		this.id = id;
	}

	

	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id @GeneratedValue(generator="custom-id")
	@GenericGenerator(name="custom-id", strategy = "native")
	@Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getId() {
		return this.id;
	}
	
	@Column(name = "SerialNumber", unique = false, nullable = false, insertable = true, updatable = true, length = 20)
	public java.lang.String getSerialNumber() {
		return this.serialNumber;
	}
	
	public void setSerialNumber(java.lang.String value) {
		this.serialNumber = value;
	}
	
	@Column(name = "DrugID", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getDrugId() {
		return this.drugId;
	}
	
	public void setDrugId(java.lang.Integer value) {
		this.drugId = value;
	}
	
	@Column(name = "OutAmount", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getOutAmount() {
		return this.outAmount;
	}
	
	public void setOutAmount(java.lang.Integer value) {
		this.outAmount = value;
	}
	
	@Column(name = "OutPrice", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Long getOutPrice() {
		return this.outPrice;
	}
	
	public void setOutPrice(java.lang.Long value) {
		this.outPrice = value;
	}
	
	@Column(name = "OutPlaceID", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getOutPlaceId() {
		return this.outPlaceId;
	}
	
	public void setOutPlaceId(java.lang.Integer value) {
		this.outPlaceId = value;
	}
	
	@Column(name = "FromPlaceID", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getFromPlaceId() {
		return this.fromPlaceId;
	}
	
	public void setFromPlaceId(java.lang.Integer value) {
		this.fromPlaceId = value;
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
	
	//获取表标识列的值,即作为关联类时显示的值
	@Transient
	public String getModelTagValue() {
				return this.id+"";
			
	}
	
	private DrugStockOut serialNumberModel;
	public void setSerialNumberModel(DrugStockOut drugStockOut){
		this.serialNumberModel = drugStockOut;
	}
	
	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "SerialNumber",nullable = false, insertable = false, updatable = false) 
	})
	public DrugStockOut getSerialNumberModel() {
		return serialNumberModel;
	}
	private DrugPlace outPlaceIdModel;
	public void setOutPlaceIdModel(DrugPlace drugPlace){
		this.outPlaceIdModel = drugPlace;
	}
	
	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "OutPlaceID",nullable = false, insertable = false, updatable = false) 
	})
	public DrugPlace getOutPlaceIdModel() {
		return outPlaceIdModel;
	}
	private DrugPlace fromPlaceIdModel;
	public void setFromPlaceIdModel(DrugPlace drugPlace){
		this.fromPlaceIdModel = drugPlace;
	}
	
	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "FromPlaceID",nullable = false, insertable = false, updatable = false) 
	})
	public DrugPlace getFromPlaceIdModel() {
		return fromPlaceIdModel;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("SerialNumber",getSerialNumber())
			.append("DrugId",getDrugId())
			.append("OutAmount",getOutAmount())
			.append("OutPrice",getOutPrice())
			.append("OutPlaceId",getOutPlaceId())
			.append("FromPlaceId",getFromPlaceId())
			.append("ProductionDate",getProductionDate())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof DrugStockOutDetail == false) return false;
		if(this == obj) return true;
		DrugStockOutDetail other = (DrugStockOutDetail)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

