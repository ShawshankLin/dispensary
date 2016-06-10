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
@Table(name = "receipt")
public class Receipt extends BaseEntity implements java.io.Serializable{
	private static final float serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "收据单";
	public static final String ALIAS_REC_ID = "收据单ID";
	
	public static final String ALIAS_PRES_ID = "处方单编号";
	
	public static final String ALIAS_DRUG_FEE = "药品费用";
	
	public static final String ALIAS_TRANSFER_FEE = "换药费";
	
	public static final String ALIAS_OXYGEN_FEE = "输氧费";
	
	public static final String ALIAS_PHYSICAL_FEE = "理疗费";
	
	public static final String ALIAS_EMERGENCY_FEE = "急诊费";
	
	public static final String ALIAS_INJECTION_FEE = "注射费用";
	
	public static final String ALIAS_NEBULIZATION_FEE = "超声雾化费";
	
	public static final String ALIAS_REGISTER_FEE = "挂号费";
	
	public static final String ALIAS_FEE_SUM = "费用总计";
	
	public static final String ALIAS_ME_ST_ID = "收费员ID ";
	
	public static final String ALIAS_REC_DATE = "收费日期";
	
	
	//date formats
	public static final String FORMAT_REC_DATE = DATE_FORMAT;
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * 收据单ID       db_column: RecID 
     */ 	
	
	private java.lang.Integer recId;
    /**
     * 处方单编号       db_column: PresID 
     */ 	
	@NotNull 
	private java.lang.Integer presId;
    /**
     * 药品总售价       db_column: DrugFee 
     */ 	
	
	private float drugFee;
    /**
     * 换药费       db_column: TransferFee 
     */ 	
	
	private float transferFee;
    /**
     * 输氧费       db_column: OxygenFee 
     */ 	
	
	private float oxygenFee;
    /**
     * 理疗       db_column: PhysicalFee 
     */ 	
	
	private float physicalFee;
    /**
     * 急诊费       db_column: EmergencyFee 
     */ 	
	
	private float emergencyFee;
    /**
     * 注射费用       db_column: InjectionFee 
     */ 	
	
	private float injectionFee;
    /**
     * 超声雾化       db_column: NebulizationFee 
     */ 	
	
	private float nebulizationFee;
    /**
     * 挂号费用       db_column: RegisterFee 
     */ 	
	
	private float registerFee;
    /**
     * 费用总计       db_column: FeeSum 
     */ 	
	
	private float feeSum;
    /**
     * 收费员ID        db_column: MeStID 
     */ 	
	
	private java.lang.Integer meStId;
    /**
     * 收费日期       db_column: RecDate 
     */ 	
	
	private java.util.Date recDate;
	//columns END


	public Receipt(){
	}

	public Receipt(
		java.lang.Integer recId
	){
		this.recId = recId;
	}

	

	public void setRecId(java.lang.Integer value) {
		this.recId = value;
	}
	
	@Id @GeneratedValue(generator="custom-id")
	@GenericGenerator(name="custom-id", strategy = "native")
	@Column(name = "RecID", unique = true, nullable = false, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getRecId() {
		return this.recId;
	}
	
	@Column(name = "PresID", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getPresId() {
		return this.presId;
	}
	
	public void setPresId(java.lang.Integer value) {
		this.presId = value;
	}
	
	@Column(name = "DrugFee", unique = false, nullable = true, insertable = true, updatable = true, length = 12)
	public float getDrugFee() {
		return this.drugFee;
	}
	
	public void setDrugFee(float value) {
		this.drugFee = value;
	}
	
	@Column(name = "TransferFee", unique = false, nullable = true, insertable = true, updatable = true, length = 12)
	public float getTransferFee() {
		return this.transferFee;
	}
	
	public void setTransferFee(float value) {
		this.transferFee = value;
	}
	
	@Column(name = "OxygenFee", unique = false, nullable = true, insertable = true, updatable = true, length = 12)
	public float getOxygenFee() {
		return this.oxygenFee;
	}
	
	public void setOxygenFee(float value) {
		this.oxygenFee = value;
	}
	
	@Column(name = "PhysicalFee", unique = false, nullable = true, insertable = true, updatable = true, length = 12)
	public float getPhysicalFee() {
		return this.physicalFee;
	}
	
	public void setPhysicalFee(float value) {
		this.physicalFee = value;
	}
	
	@Column(name = "EmergencyFee", unique = false, nullable = true, insertable = true, updatable = true, length = 12)
	public float getEmergencyFee() {
		return this.emergencyFee;
	}
	
	public void setEmergencyFee(float value) {
		this.emergencyFee = value;
	}
	
	@Column(name = "InjectionFee", unique = false, nullable = true, insertable = true, updatable = true, length = 12)
	public float getInjectionFee() {
		return this.injectionFee;
	}
	
	public void setInjectionFee(float value) {
		this.injectionFee = value;
	}
	
	@Column(name = "NebulizationFee", unique = false, nullable = true, insertable = true, updatable = true, length = 12)
	public float getNebulizationFee() {
		return this.nebulizationFee;
	}
	
	public void setNebulizationFee(float value) {
		this.nebulizationFee = value;
	}
	
	@Column(name = "RegisterFee", unique = false, nullable = true, insertable = true, updatable = true, length = 12)
	public float getRegisterFee() {
		return this.registerFee;
	}
	
	public void setRegisterFee(float value) {
		this.registerFee = value;
	}
	
	@Column(name = "FeeSum", unique = false, nullable = true, insertable = true, updatable = true, length = 12)
	public float getFeeSum() {
		return this.feeSum;
	}
	
	public void setFeeSum(float value) {
		this.feeSum = value;
	}
	
	@Column(name = "MeStID", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getMeStId() {
		return this.meStId;
	}
	
	public void setMeStId(java.lang.Integer value) {
		this.meStId = value;
	}
	
	@Transient
	public String getRecDateString() {
		return DateConvertUtils.format(getRecDate(), FORMAT_REC_DATE);
	}
	public void setRecDateString(String value) {
		setRecDate(DateConvertUtils.parse(value, FORMAT_REC_DATE,java.util.Date.class));
	}
	
	@Column(name = "RecDate", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	public java.util.Date getRecDate() {
		return this.recDate;
	}
	
	public void setRecDate(java.util.Date value) {
		this.recDate = value;
	}
	
	//获取表标识列的值,即作为关联类时显示的值
	@Transient
	public String getModelTagValue() {
				return this.recId+"";
			
	}
	
	private Dispensarystaff meStIdModel;
	public void setMeStIdModel(Dispensarystaff dispensarystaff){
		this.meStIdModel = dispensarystaff;
	}
	
	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "MeStID",nullable = false, insertable = false, updatable = false) 
	})
	public Dispensarystaff getMeStIdModel() {
		return meStIdModel;
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
			.append("RecId",getRecId())
			.append("PresId",getPresId())
			.append("DrugFee",getDrugFee())
			.append("TransferFee",getTransferFee())
			.append("OxygenFee",getOxygenFee())
			.append("PhysicalFee",getPhysicalFee())
			.append("EmergencyFee",getEmergencyFee())
			.append("InjectionFee",getInjectionFee())
			.append("NebulizationFee",getNebulizationFee())
			.append("RegisterFee",getRegisterFee())
			.append("FeeSum",getFeeSum())
			.append("MeStId",getMeStId())
			.append("RecDate",getRecDate())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getRecId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Receipt == false) return false;
		if(this == obj) return true;
		Receipt other = (Receipt)obj;
		return new EqualsBuilder()
			.append(getRecId(),other.getRecId())
			.isEquals();
	}
}

