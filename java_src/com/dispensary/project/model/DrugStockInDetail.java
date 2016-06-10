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
@Table(name = "drug_stock_in_detail")
public class DrugStockInDetail extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "入库单明细";
	public static final String ALIAS_ID = "序列号";
	
	public static final String ALIAS_SERIAL_NUMBER = "流水号";
	
	public static final String ALIAS_DRUG_ID = "药品编码";
	
	public static final String ALIAS_IN_PLACE_ID = "药品存放位置";
	
	public static final String ALIAS_IN_PRICE = "金额";
	
	public static final String ALIAS_IN_STOCKS = "入库数量";
	
	public static final String ALIAS_PRODUCTION_DATE = "生产日期";
	
	
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
	@NotBlank @Length(max=100)
	private java.lang.String serialNumber;
    /**
     * 药品编码       db_column: DrugID 
     */ 	
	@NotNull 
	private java.lang.Integer drugId;
    /**
     * 药品存放位置       db_column: InPlaceID 
     */ 	
	@NotNull 
	private java.lang.Integer inPlaceId;
    /**
     * 金额       db_column: InPrice 
     */ 	
	
	private java.lang.Float inPrice;
    /**
     * 入库数量       db_column: InStocks 
     */ 	
	@NotNull 
	private java.lang.Float inStocks;
    /**
     * productionDate       db_column: ProductionDate 
     */ 	
	
	private java.util.Date productionDate;
	//columns END


	public DrugStockInDetail(){
	}

	public DrugStockInDetail(
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
	
	@Column(name = "InPlaceID", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getInPlaceId() {
		return this.inPlaceId;
	}
	
	public void setInPlaceId(java.lang.Integer value) {
		this.inPlaceId = value;
	}
	
	@Column(name = "InPrice", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Float getInPrice() {
		return this.inPrice;
	}
	
	public void setInPrice(java.lang.Float value) {
		this.inPrice = value;
	}
	
	@Column(name = "InStocks", unique = false, nullable = false, insertable = true, updatable = true, length = 5)
	public java.lang.Float getInStocks() {
		return this.inStocks;
	}
	
	public void setInStocks(java.lang.Float value) {
		this.inStocks = value;
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
	
	private DrugPlace inPlaceIdModel;
	public void setInPlaceIdModel(DrugPlace drugPlace){
		this.inPlaceIdModel = drugPlace;
	}
	
	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "InPlaceID",nullable = false, insertable = false, updatable = false) 
	})
	public DrugPlace getInPlaceIdModel() {
		return inPlaceIdModel;
	}
	private DrugStockIn serialNumberModel;
	public void setSerialNumberModel(DrugStockIn drugStockIn){
		this.serialNumberModel = drugStockIn;
	}
	
	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "SerialNumber",referencedColumnName="SerialNumber",nullable = false, insertable = false, updatable = false) 
	})
	public DrugStockIn getSerialNumberModel() {
		return serialNumberModel;
	}
	private DrugBasicInfo drugIdModel;
	public void setDrugIdModel(DrugBasicInfo drugBasicInfo){
		this.drugIdModel = drugBasicInfo;
	}
	
	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "DrugID",nullable = false, insertable = false, updatable = false) 
	})
	public DrugBasicInfo getDrugIdModel() {
		return drugIdModel;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("SerialNumber",getSerialNumber())
			.append("DrugId",getDrugId())
			.append("InPlaceId",getInPlaceId())
			.append("InPrice",getInPrice())
			.append("InStocks",getInStocks())
			.append("ProductionDate",getProductionDate())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof DrugStockInDetail == false) return false;
		if(this == obj) return true;
		DrugStockInDetail other = (DrugStockInDetail)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

