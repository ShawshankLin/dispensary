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
@Table(name = "drug_stock_in")
public class DrugStockIn extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "入库单";
	public static final String ALIAS_ID = "序列号";
	
	public static final String ALIAS_SERIAL_NUMBER = "流水号";
	
	public static final String ALIAS_STOCK_IN_ID = "入库单号";
	
	public static final String ALIAS_IN_DATE = "入库时间";
	
	public static final String ALIAS_TOTAL = "总数量";
	
	public static final String ALIAS_SUM = "总金额";
	
	public static final String ALIAS_OPERATOR_ID = "经手人";
	
	public static final String ALIAS_SUPPLIER_ID = "供应商";
	
	
	//date formats
	public static final String FORMAT_IN_DATE = DATE_FORMAT;
	

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
     * 入库单号       db_column: Stock_In_ID 
     */ 	
	@NotBlank @Length(max=20)
	private java.lang.String stockInId;
    /**
     * 入库时间       db_column: InDate 
     */ 	
	@NotNull 
	private java.util.Date inDate;
    /**
     * 总数量       db_column: Total 
     */ 	
	
	private java.lang.Integer total;
    /**
     * 总金额       db_column: Sum 
     */ 	
	
	private java.lang.Float sum;
    /**
     * 入库经手人编号       db_column: OperatorID 
     */ 	
	@NotNull 
	private java.lang.Integer operatorId;
    /**
     * 厂家编号       db_column: SupplierID 
     */ 	
	@NotNull 
	private java.lang.Integer supplierId;
	//columns END


	public DrugStockIn(){
	}

	public DrugStockIn(
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
	
	@Column(name = "Stock_In_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 20)
	public java.lang.String getStockInId() {
		return this.stockInId;
	}
	
	public void setStockInId(java.lang.String value) {
		this.stockInId = value;
	}
	
	@Transient
	public String getInDateString() {
		return DateConvertUtils.format(getInDate(), FORMAT_IN_DATE);
	}
	public void setInDateString(String value) {
		setInDate(DateConvertUtils.parse(value, FORMAT_IN_DATE,java.util.Date.class));
	}
	
	@Column(name = "InDate", unique = false, nullable = false, insertable = true, updatable = true, length = 0)
	public java.util.Date getInDate() {
		return this.inDate;
	}
	
	public void setInDate(java.util.Date value) {
		this.inDate = value;
	}
	
	@Column(name = "Total", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getTotal() {
		return this.total;
	}
	
	public void setTotal(java.lang.Integer value) {
		this.total = value;
	}
	
	@Column(name = "Sum", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Float getSum() {
		return this.sum;
	}
	
	public void setSum(java.lang.Float value) {
		this.sum = value;
	}
	
	@Column(name = "OperatorID", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getOperatorId() {
		return this.operatorId;
	}
	
	public void setOperatorId(java.lang.Integer value) {
		this.operatorId = value;
		
	}
	
	@Column(name = "SupplierID", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getSupplierId() {
		return this.supplierId;
	}
	
	public void setSupplierId(java.lang.Integer value) {
		this.supplierId = value;
	}
	
	//获取表标识列的值,即作为关联类时显示的值
	@Transient
	public String getModelTagValue() {
				return this.id+"";
			
	}
	
	private Set drugStockInDetails = new HashSet(0);
	public void setDrugStockInDetails(Set<DrugStockInDetail> drugStockInDetail){
		this.drugStockInDetails = drugStockInDetail;
	}
	
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "serialNumberModel")
	public Set<DrugStockInDetail> getDrugStockInDetails() {
		return drugStockInDetails;
	}
	private Dispensarystaff operatorIdModel;
	public void setOperatorIdModel(Dispensarystaff dispensarystaff){
		this.operatorIdModel = dispensarystaff;
	}
	
	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "OperatorID",nullable = false, insertable = false, updatable = false) 
	})
	public Dispensarystaff getOperatorIdModel() {
		return operatorIdModel;
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

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("SerialNumber",getSerialNumber())
			.append("StockInId",getStockInId())
			.append("InDate",getInDate())
			.append("Total",getTotal())
			.append("Sum",getSum())
			.append("OperatorId",getOperatorId())
			.append("SupplierId",getSupplierId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof DrugStockIn == false) return false;
		if(this == obj) return true;
		DrugStockIn other = (DrugStockIn)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

