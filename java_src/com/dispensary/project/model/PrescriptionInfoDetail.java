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
@Table(name = "prescription_info_detail")
public class PrescriptionInfoDetail extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "处方明细";
	public static final String ALIAS_ID = "id";
	
	public static final String ALIAS_PRES_ID = "处方单号";
	
	public static final String ALIAS_DRUG_ID = "药品ID";
	
	public static final String ALIAS_AMOUNT = "数量";
	
	public static final String ALIAS_TIMES = "次数";
	
	public static final String ALIAS_DAYS = "天数";
	
	public static final String ALIAS_DRUG_SUM = "金额";
	
	public static final String ALIAS_NOTE = "备注";
	
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * id       db_column: ID 
     */ 	
	
	private java.lang.Integer id;
    /**
     * 单据内码       db_column: PresID 
     */ 	
	@NotNull 
	private java.lang.Integer presId;
    /**
     * 药品ID       db_column: DrugID 
     */ 	
	@NotNull 
	private java.lang.Integer drugId;
    /**
     * 数量       db_column: Amount 
     */ 	
	@NotNull 
	private java.lang.Integer amount;
    /**
     * 次数       db_column: Times 
     */ 	
	@Length(max=20)
	private java.lang.String times;
    /**
     * 天数       db_column: Days 
     */ 	
	
	private java.lang.Integer days;
    /**
     * 金额       db_column: DrugSum 
     */ 	
	@NotNull 
	private java.lang.Float drugSum;
    /**
     * 备注       db_column: Note 
     */ 	
	@Length(max=255)
	private java.lang.String note;
	//columns END


	public PrescriptionInfoDetail(){
	}

	public PrescriptionInfoDetail(
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
	
	@Column(name = "PresID", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getPresId() {
		return this.presId;
	}
	
	public void setPresId(java.lang.Integer value) {
		this.presId = value;
	}
	
	@Column(name = "DrugID", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getDrugId() {
		return this.drugId;
	}
	
	public void setDrugId(java.lang.Integer value) {
		this.drugId = value;
	}
	
	@Column(name = "Amount", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getAmount() {
		return this.amount;
	}
	
	public void setAmount(java.lang.Integer value) {
		this.amount = value;
	}
	
	@Column(name = "Times", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.String getTimes() {
		return this.times;
	}
	
	public void setTimes(java.lang.String value) {
		this.times = value;
	}
	
	@Column(name = "Days", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getDays() {
		return this.days;
	}
	
	public void setDays(java.lang.Integer value) {
		this.days = value;
	}
	
	@Column(name = "DrugSum", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
	public java.lang.Float getDrugSum() {
		return this.drugSum;
	}
	
	public void setDrugSum(java.lang.Float value) {
		this.drugSum = value;
	}
	
	@Column(name = "Note", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getNote() {
		return this.note;
	}
	
	public void setNote(java.lang.String value) {
		this.note = value;
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
	private PrescriptionInfo presIdModel;
	public void setPresIdModel(PrescriptionInfo prescriptionInfo){
		this.presIdModel = prescriptionInfo;
	}
	
	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "PresID",nullable = false, insertable = false, updatable = false) 
	})
	public PrescriptionInfo getPresIdModel() {
		return presIdModel;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("PresId",getPresId())
			.append("DrugId",getDrugId())
			.append("Amount",getAmount())
			.append("Times",getTimes())
			.append("Days",getDays())
			.append("DrugSum",getDrugSum())
			.append("Note",getNote())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof PrescriptionInfoDetail == false) return false;
		if(this == obj) return true;
		PrescriptionInfoDetail other = (PrescriptionInfoDetail)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

