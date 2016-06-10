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
@Table(name = "drug_stock_out")
public class DrugStockOut extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "出库单";
	public static final String ALIAS_ID = "序列号";
	
	public static final String ALIAS_SERIAL_NUMBER = "流水号";
	
	public static final String ALIAS_STOCK_OUT_ID = "出库编号";
	
	public static final String ALIAS_OUT_DATE = "出库时间";
	
	public static final String ALIAS_TOTAL = "总数量";
	
	public static final String ALIAS_SUM = "总金额";
	
	public static final String ALIAS_OPERATOR_ID = "出库经手人编号";
	
	
	//date formats
	public static final String FORMAT_OUT_DATE = DATE_FORMAT;
	

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
     * 出库编号       db_column: StockOutID 
     */ 	
	@NotBlank @Length(max=20)
	private java.lang.String stockOutId;
    /**
     * 出库时间       db_column: OutDate 
     */ 	
	@NotNull 
	private java.util.Date outDate;
    /**
     * 总数量       db_column: Total 
     */ 	
	
	private java.lang.Integer total;
    /**
     * 总金额       db_column: Sum 
     */ 	
	
	private java.lang.Long sum;
    /**
     * 出库经手人编号       db_column: OperatorID 
     */ 	
	@NotNull 
	private java.lang.Integer operatorId;
	//columns END


	public DrugStockOut(){
	}

	public DrugStockOut(
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
	
	@Column(name = "StockOutID", unique = false, nullable = false, insertable = true, updatable = true, length = 20)
	public java.lang.String getStockOutId() {
		return this.stockOutId;
	}
	
	public void setStockOutId(java.lang.String value) {
		this.stockOutId = value;
	}
	
	@Transient
	public String getOutDateString() {
		return DateConvertUtils.format(getOutDate(), FORMAT_OUT_DATE);
	}
	public void setOutDateString(String value) {
		setOutDate(DateConvertUtils.parse(value, FORMAT_OUT_DATE,java.util.Date.class));
	}
	
	@Column(name = "OutDate", unique = false, nullable = false, insertable = true, updatable = true, length = 0)
	public java.util.Date getOutDate() {
		return this.outDate;
	}
	
	public void setOutDate(java.util.Date value) {
		this.outDate = value;
	}
	
	@Column(name = "Total", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getTotal() {
		return this.total;
	}
	
	public void setTotal(java.lang.Integer value) {
		this.total = value;
	}
	
	@Column(name = "Sum", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Long getSum() {
		return this.sum;
	}
	
	public void setSum(java.lang.Long value) {
		this.sum = value;
	}
	
	@Column(name = "OperatorID", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getOperatorId() {
		return this.operatorId;
	}
	
	public void setOperatorId(java.lang.Integer value) {
		this.operatorId = value;
	}
	
	//获取表标识列的值,即作为关联类时显示的值
	@Transient
	public String getModelTagValue() {
				return this.id+"";
			
	}
	
	private Set drugStockOutDetails = new HashSet(0);
	public void setDrugStockOutDetails(Set<DrugStockOutDetail> drugStockOutDetail){
		this.drugStockOutDetails = drugStockOutDetail;
	}
	
	@OneToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "serialNumberModel")
	public Set<DrugStockOutDetail> getDrugStockOutDetails() {
		return drugStockOutDetails;
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

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("SerialNumber",getSerialNumber())
			.append("StockOutId",getStockOutId())
			.append("OutDate",getOutDate())
			.append("Total",getTotal())
			.append("Sum",getSum())
			.append("OperatorId",getOperatorId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof DrugStockOut == false) return false;
		if(this == obj) return true;
		DrugStockOut other = (DrugStockOut)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

