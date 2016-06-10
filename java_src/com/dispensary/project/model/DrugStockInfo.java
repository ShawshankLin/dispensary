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
@Table(name = "drug_stock_info")
public class DrugStockInfo extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "库存";
	public static final String ALIAS_ID = "序列号";
	
	public static final String ALIAS_DRUG_ID = "药品名称";
	
	public static final String ALIAS_DRUG_PLACE_ID = "存放位置";
	
	public static final String ALIAS_PRODUCTION_DATE = "生产日期";
	
	public static final String ALIAS_CUR_AMOUNT = "当前数量";
	
	
	//date formats
	public static final String FORMAT_PRODUCTION_DATE = DATE_FORMAT;
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * 序列号       db_column: ID 
     */ 	
	
	private java.lang.Integer id;
    /**
     * 药品编码       db_column: DrugID 
     */ 	
	@NotNull 
	private java.lang.Integer drugId;
    /**
     * 位置编码       db_column: DrugPlaceID 
     */ 	
	@NotNull 
	private java.lang.Integer drugPlaceId;
    /**
     * productionDate       db_column: ProductionDate 
     */ 	
	
	private java.util.Date productionDate;
    /**
     * 当前数量       db_column: CurAmount 
     */ 	
	@NotNull 
	private java.lang.Float curAmount;
	//columns END


	public DrugStockInfo(){
	}

	public DrugStockInfo(
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
	
	@Column(name = "DrugID", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getDrugId() {
		return this.drugId;
	}
	
	public void setDrugId(java.lang.Integer value) {
		this.drugId = value;
	}
	
	@Column(name = "DrugPlaceID", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getDrugPlaceId() {
		return this.drugPlaceId;
	}
	
	public void setDrugPlaceId(java.lang.Integer value) {
		this.drugPlaceId = value;
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
	
	@Column(name = "CurAmount", unique = false, nullable = false, insertable = true, updatable = true, length = 12)
	public java.lang.Float getCurAmount() {
		return this.curAmount;
	}
	
	public void setCurAmount(java.lang.Float value) {
		this.curAmount = value;
	}
	
	//获取表标识列的值,即作为关联类时显示的值
	@Transient
	public String getModelTagValue() {
				return this.id+"";
			
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
	private DrugPlace drugPlaceIdModel;
	public void setDrugPlaceIdModel(DrugPlace drugPlace){
		this.drugPlaceIdModel = drugPlace;
	}
	
	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "DrugPlaceID",nullable = false, insertable = false, updatable = false) 
	})
	public DrugPlace getDrugPlaceIdModel() {
		return drugPlaceIdModel;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("DrugId",getDrugId())
			.append("DrugPlaceId",getDrugPlaceId())
			.append("ProductionDate",getProductionDate())
			.append("CurAmount",getCurAmount())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof DrugStockInfo == false) return false;
		if(this == obj) return true;
		DrugStockInfo other = (DrugStockInfo)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

