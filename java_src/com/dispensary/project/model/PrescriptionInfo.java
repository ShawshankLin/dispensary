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
@Table(name = "prescription_info")
public class PrescriptionInfo extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "处方";
	public static final String ALIAS_PRES_ID = "处方号";
	
	public static final String ALIAS_CASE_ID = "病历单ID";
	
	public static final String ALIAS_PERS_DATE = "开单日期";
	
	public static final String ALIAS_STATE = "处方状态";
	//此处的药品总价即为总费用
	public static final String ALIAS_DRUG_SUM = "药品总价";
	
	
	//date formats
	public static final String FORMAT_PERS_DATE = DATE_TIME_FORMAT;
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * 序列号       db_column: PresID 
     */ 	
	
	private java.lang.Integer presId;
    /**
     * 病历单ID       db_column: Case_ID 
     */ 	
	@NotBlank @Length(max=20)
	private java.lang.String caseId;
    /**
     * 开单日期       db_column: PersDate 
     */ 	
	
	private java.util.Date persDate;
    /**
     * 0已处理1未处理       db_column: State 
     */ 	
	@NotNull 
	private java.lang.Integer state;
    /**
     * 药品总价       db_column: DrugSum 
     */ 	
	@NotNull 
	private java.lang.Float drugSum;
	//columns END

	private String id;


	public PrescriptionInfo(){
	}

	public PrescriptionInfo(
		java.lang.Integer presId
	){
		this.presId = presId;
	}

	

	public void setPresId(java.lang.Integer value) {
		this.presId = value;
	}
	
	@Id @GeneratedValue(generator="custom-id")
	@GenericGenerator(name="custom-id", strategy = "increment")
	@Column(name = "PresID", unique = true, nullable = false, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getPresId() {
		return this.presId;
	}
	
	@Column(name = "Case_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 20)
	public java.lang.String getCaseId() {
		return this.caseId;
	}
	
	public void setCaseId(java.lang.String value) {
		this.caseId = value;
	}
	
	@Transient
	public String getPersDateString() {
		return DateConvertUtils.format(getPersDate(), FORMAT_PERS_DATE);
	}
	public void setPersDateString(String value) {
		setPersDate(DateConvertUtils.parse(value, FORMAT_PERS_DATE,java.util.Date.class));
	}
	
	@Column(name = "PersDate", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	public java.util.Date getPersDate() {
		return this.persDate;
	}
	
	public void setPersDate(java.util.Date value) {
		this.persDate = value;
	}
	
	@Column(name = "State", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getState() {
		return this.state;
	}
	
	public void setState(java.lang.Integer value) {
		this.state = value;
	}
	
	@Column(name = "DrugSum", unique = false, nullable = false, insertable = true, updatable = true, length = 12)
	public java.lang.Float getDrugSum() {
		return this.drugSum;
	}
	
	public void setDrugSum(java.lang.Float value) {
		this.drugSum = value;
	}
	
	//获取表标识列的值,即作为关联类时显示的值
	@Transient
	public String getModelTagValue() {
				return this.id+"";
			
	}
	
	private Set prescriptionInfoDetails = new HashSet(0);
	public void setPrescriptionInfoDetails(Set<PrescriptionInfoDetail> prescriptionInfoDetail){
		this.prescriptionInfoDetails = prescriptionInfoDetail;
	}
	
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "presIdModel")
	public Set<PrescriptionInfoDetail> getPrescriptionInfoDetails() {
		return prescriptionInfoDetails;
	}
	private Set receipts = new HashSet(0);
	public void setReceipts(Set<Receipt> receipt){
		this.receipts = receipt;
	}
	
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "presIdModel")
	public Set<Receipt> getReceipts() {
		return receipts;
	}
	private PatiCaseHistory caseIdModel;
	public void setCaseIdModel(PatiCaseHistory patiCaseHistory){
		this.caseIdModel = patiCaseHistory;
	}
	
	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "Case_ID",referencedColumnName= "Case_ID",nullable = false, insertable = false, updatable = false,unique=true) 
	})
	public PatiCaseHistory getCaseIdModel() {
		return caseIdModel;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("PresId",getPresId())
			.append("CaseId",getCaseId())
			.append("PersDate",getPersDate())
			.append("State",getState())
			.append("DrugSum",getDrugSum())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getPresId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof PrescriptionInfo == false) return false;
		if(this == obj) return true;
		PrescriptionInfo other = (PrescriptionInfo)obj;
		return new EqualsBuilder()
			.append(getPresId(),other.getPresId())
			.isEquals();
	}
}

