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
@Table(name = "pati_case_history")
public class PatiCaseHistory extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "病历";
	public static final String ALIAS_ID = "序列号 ";
	
	public static final String ALIAS_CASE_ID = "病历单ID ";
	
	public static final String ALIAS_STU_NUM = "学号";
	
	public static final String ALIAS_VISIT_DATE = "就诊日期";
	
	public static final String ALIAS_DISPENSARY_RECORD = "既往史";
	
	public static final String ALIAS_ALLERGY = "过敏史";
	
	public static final String ALIAS_PRIMARY_CARE = "初诊";
	
	public static final String ALIAS_RE_EXAMINATION = "复诊";
	
	public static final String ALIAS_DESCRIBES = "主诉";
	
	public static final String ALIAS_EXAMINE_STATUS = "检查情况";
	
	public static final String ALIAS_FIRST_IMPRESS = "初步诊断";
	
	public static final String ALIAS_ME_ST_ID = "主治医生";
	
	
	//date formats
	public static final String FORMAT_VISIT_DATE = DATE_TIME_FORMAT;
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * 序列号        db_column: ID 
     */ 	
	
	private java.lang.Integer id;
    /**
     * 病历单ID        db_column: Case_ID 
     */ 	
	@NotBlank @Length(max=20)
	private java.lang.String caseId;
    /**
     * 学号       db_column: StuNum 
     */ 	
	@NotBlank @Length(max=12)
	private java.lang.String stuNum;
    /**
     * 就诊日期       db_column: VisitDate 
     */ 	
	
	private java.util.Date visitDate;
    /**
     * 既往史       db_column: DispensaryRecord 
     */ 	
	@Length(max=255)
	private java.lang.String dispensaryRecord;
    /**
     * 过敏史       db_column: allergy 
     */ 	
	@Length(max=255)
	private java.lang.String allergy;
    /**
     * 初诊       db_column: primary_care 
     */ 	
	@Length(max=255)
	private java.lang.String primaryCare;
    /**
     * 复诊       db_column: re_examination 
     */ 	
	@Length(max=255)
	private java.lang.String reExamination;
    /**
     * 主诉       db_column: Describes 
     */ 	
	@Length(max=255)
	private java.lang.String describes;
    /**
     * 检查情况       db_column: ExamineStatus 
     */ 	
	@Length(max=255)
	private java.lang.String examineStatus;
    /**
     * 初步诊断       db_column: FirstImpress 
     */ 	
	@Length(max=255)
	private java.lang.String firstImpress;
    /**
     * 主治医生ID       db_column: MeStID 
     */ 	
	
	private java.lang.Integer meStId;
	//columns END


	public PatiCaseHistory(){
	}

	public PatiCaseHistory(
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
	
	@Column(name = "Case_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 20)
	public java.lang.String getCaseId() {
		return this.caseId;
	}
	
	public void setCaseId(java.lang.String value) {
		this.caseId = value;
	}
	
	@Column(name = "StuNum", unique = false, nullable = false, insertable = true, updatable = true, length = 12)
	public java.lang.String getStuNum() {
		return this.stuNum;
	}
	
	public void setStuNum(java.lang.String value) {
		this.stuNum = value;
	}
	
	@Transient
	public String getVisitDateString() {
		return DateConvertUtils.format(getVisitDate(), FORMAT_VISIT_DATE);
	}
	public void setVisitDateString(String value) {
		setVisitDate(DateConvertUtils.parse(value, FORMAT_VISIT_DATE,java.util.Date.class));
	}
	
	@Column(name = "VisitDate", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	public java.util.Date getVisitDate() {
		return this.visitDate;
	}
	
	public void setVisitDate(java.util.Date value) {
		this.visitDate = value;
	}
	
	@Column(name = "DispensaryRecord", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getDispensaryRecord() {
		return this.dispensaryRecord;
	}
	
	public void setDispensaryRecord(java.lang.String value) {
		this.dispensaryRecord = value;
	}
	
	@Column(name = "allergy", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getAllergy() {
		return this.allergy;
	}
	
	public void setAllergy(java.lang.String value) {
		this.allergy = value;
	}
	
	@Column(name = "primary_care", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getPrimaryCare() {
		return this.primaryCare;
	}
	
	public void setPrimaryCare(java.lang.String value) {
		this.primaryCare = value;
	}
	
	@Column(name = "re_examination", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getReExamination() {
		return this.reExamination;
	}
	
	public void setReExamination(java.lang.String value) {
		this.reExamination = value;
	}
	
	@Column(name = "Describes", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getDescribes() {
		return this.describes;
	}
	
	public void setDescribes(java.lang.String value) {
		this.describes = value;
	}
	
	@Column(name = "ExamineStatus", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getExamineStatus() {
		return this.examineStatus;
	}
	
	public void setExamineStatus(java.lang.String value) {
		this.examineStatus = value;
	}
	
	@Column(name = "FirstImpress", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getFirstImpress() {
		return this.firstImpress;
	}
	
	public void setFirstImpress(java.lang.String value) {
		this.firstImpress = value;
	}
	
	@Column(name = "MeStID", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getMeStId() {
		return this.meStId;
	}
	
	public void setMeStId(java.lang.Integer value) {
		this.meStId = value;
	}
	
	//获取表标识列的值,即作为关联类时显示的值
	@Transient
	public String getModelTagValue() {
				return this.id+"";
			
	}
	
	private Set prescriptionInfos = new HashSet(0);
	public void setPrescriptionInfos(Set<PrescriptionInfo> prescriptionInfo){
		this.prescriptionInfos = prescriptionInfo;
	}
	
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER, mappedBy = "caseIdModel")
	public Set<PrescriptionInfo> getPrescriptionInfos() {
		return prescriptionInfos;
	}
	private Student stuNumModel;
	public void setStuNumModel(Student student){
		this.stuNumModel = student;
	}
	
	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "StuNum",nullable = false, insertable = false, updatable = false) 
	})
	public Student getStuNumModel() {
		return stuNumModel;
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

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("CaseId",getCaseId())
			.append("StuNum",getStuNum())
			.append("VisitDate",getVisitDate())
			.append("DispensaryRecord",getDispensaryRecord())
			.append("Allergy",getAllergy())
			.append("PrimaryCare",getPrimaryCare())
			.append("ReExamination",getReExamination())
			.append("Describes",getDescribes())
			.append("ExamineStatus",getExamineStatus())
			.append("FirstImpress",getFirstImpress())
			.append("MeStId",getMeStId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof PatiCaseHistory == false) return false;
		if(this == obj) return true;
		PatiCaseHistory other = (PatiCaseHistory)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

