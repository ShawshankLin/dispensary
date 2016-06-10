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
@Table(name = "dispensarystaff")
public class Dispensarystaff extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "医务人员";
	public static final String ALIAS_ME_ST_ID = "医务人员ID ";
	
	public static final String ALIAS_ME_ST_NAME = "员工姓名";
	
	public static final String ALIAS_SEX = "性别";
	
	public static final String ALIAS_AGE = "年龄";
	
	public static final String ALIAS_EDUCATION = "学历";
	
	public static final String ALIAS_ADDRESS = "住址";
	
	public static final String ALIAS_TEL = "电话";
	
	public static final String ALIAS_MOBILE = "移动电话";
	
	public static final String ALIAS_EMAIL = "Email";
	
	public static final String ALIAS_WORK_PLACE = "所属科室";
	
	public static final String ALIAS_NOTE = "备注";
	
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * 医务人员ID        db_column: MeStID 
     */ 	
	
	private java.lang.Integer meStId;
    /**
     * 真实姓名       db_column: MeStName 
     */ 	
	@Length(max=30)
	private java.lang.String meStName;
    /**
     * 性别       db_column: Sex 
     */ 	
	
	private java.lang.Integer sex;
    /**
     * 年龄       db_column: Age 
     */ 	
	
	private java.lang.Integer age;
    /**
     * 学历       db_column: Education 
     */ 	
	@Length(max=10)
	private java.lang.String education;
    /**
     * 住址       db_column: Address 
     */ 	
	@Length(max=200)
	private java.lang.String address;
    /**
     * 电话       db_column: Tel 
     */ 	
	@Length(max=20)
	private java.lang.String tel;
    /**
     * 移动电话       db_column: Mobile 
     */ 	
	@Length(max=20)
	private java.lang.String mobile;
    /**
     * Email       db_column: Email 
     */ 	
	@Email @Length(max=100)
	private java.lang.String email;
    /**
     * 所属科室       db_column: WorkPlace 
     */ 	
	@Length(max=20)
	private java.lang.String workPlace;
    /**
     * 备注       db_column: Note 
     */ 	
	@Length(max=255)
	private java.lang.String note;
	/**
     * 就诊数       db_column: topVisits 
    */
	private java.lang.Integer topVisits;
	/**
     * 最后就诊时间       db_column: Note 
     */
	private java.lang.String lastVisitedTime;
	//columns END


	public Dispensarystaff(){
	}

	public Dispensarystaff(
		java.lang.Integer meStId
	){
		this.meStId = meStId;
	}

	

	public void setMeStId(java.lang.Integer value) {
		this.meStId = value;
	}
	
	@Id @GeneratedValue(generator="custom-id")
	@GenericGenerator(name="custom-id", strategy = "native")
	@Column(name = "MeStID", unique = true, nullable = false, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getMeStId() {
		return this.meStId;
	}
	
	@Column(name = "MeStName", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public java.lang.String getMeStName() {
		return this.meStName;
	}
	
	public void setMeStName(java.lang.String value) {
		this.meStName = value;
	}
	
	@Column(name = "Sex", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getSex() {
		return this.sex;
	}
	
	public void setSex(java.lang.Integer value) {
		this.sex = value;
	}
	
	@Column(name = "Age", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getAge() {
		return this.age;
	}
	
	public void setAge(java.lang.Integer value) {
		this.age = value;
	}
	
	@Column(name = "Education", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.String getEducation() {
		return this.education;
	}
	
	public void setEducation(java.lang.String value) {
		this.education = value;
	}
	
	@Column(name = "Address", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getAddress() {
		return this.address;
	}
	
	public void setAddress(java.lang.String value) {
		this.address = value;
	}
	
	@Column(name = "Tel", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.String getTel() {
		return this.tel;
	}
	
	public void setTel(java.lang.String value) {
		this.tel = value;
	}
	
	@Column(name = "Mobile", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.String getMobile() {
		return this.mobile;
	}
	
	public void setMobile(java.lang.String value) {
		this.mobile = value;
	}
	
	@Column(name = "Email", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getEmail() {
		return this.email;
	}
	
	public void setEmail(java.lang.String value) {
		this.email = value;
	}
	
	@Column(name = "WorkPlace", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.String getWorkPlace() {
		return this.workPlace;
	}
	
	public void setWorkPlace(java.lang.String value) {
		this.workPlace = value;
	}
	
	@Column(name = "Note", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getNote() {
		return this.note;
	}
	
	public void setNote(java.lang.String value) {
		this.note = value;
	}
	
	@Transient
	public java.lang.Integer getTopVisits() {
		return topVisits;
	}

	public void setTopVisits(java.lang.Integer topVisits) {
		this.topVisits = topVisits;
	}
	@Transient
	public  java.lang.String getLastVisitedTime() {
		return lastVisitedTime;
	}

	public void setLastVisitedTime(java.lang.String lastVisitedTime) {
		this.lastVisitedTime = lastVisitedTime;
	}

	//获取表标识列的值,即作为关联类时显示的值
	@Transient
	public String getModelTagValue() {
				return this.meStName;
			
	}
	
	private Set drugStockIns = new HashSet(0);
	public void setDrugStockIns(Set<DrugStockIn> drugStockIn){
		this.drugStockIns = drugStockIn;
	}
	
	@OneToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "operatorIdModel")
	public Set<DrugStockIn> getDrugStockIns() {
		return drugStockIns;
	}
	private Set receipts = new HashSet(0);
	public void setReceipts(Set<Receipt> receipt){
		this.receipts = receipt;
	}
	
	@OneToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "meStIdModel")
	public Set<Receipt> getReceipts() {
		return receipts;
	}
	private Set drugStockOuts = new HashSet(0);
	public void setDrugStockOuts(Set<DrugStockOut> drugStockOut){
		this.drugStockOuts = drugStockOut;
	}
	
	@OneToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "operatorIdModel")
	public Set<DrugStockOut> getDrugStockOuts() {
		return drugStockOuts;
	}
	private Set patiCaseHistorys = new HashSet(0);
	public void setPatiCaseHistorys(Set<PatiCaseHistory> patiCaseHistory){
		this.patiCaseHistorys = patiCaseHistory;
	}
	
	@OneToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "meStIdModel")
	public Set<PatiCaseHistory> getPatiCaseHistorys() {
		return patiCaseHistorys;
	}
	private Set userinfos = new HashSet(0);
	public void setUserinfos(Set<Userinfo> userinfo){
		this.userinfos = userinfo;
	}
	
	@OneToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "meStIdModel")
	public Set<Userinfo> getUserinfos() {
		return userinfos;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("MeStId",getMeStId())
			.append("MeStName",getMeStName())
			.append("Sex",getSex())
			.append("Age",getAge())
			.append("Education",getEducation())
			.append("Address",getAddress())
			.append("Tel",getTel())
			.append("Mobile",getMobile())
			.append("Email",getEmail())
			.append("WorkPlace",getWorkPlace())
			.append("Note",getNote())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getMeStId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Dispensarystaff == false) return false;
		if(this == obj) return true;
		Dispensarystaff other = (Dispensarystaff)obj;
		return new EqualsBuilder()
			.append(getMeStId(),other.getMeStId())
			.isEquals();
	}
}

