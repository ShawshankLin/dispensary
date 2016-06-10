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
@Table(name = "supplier")
public class Supplier extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "供应商";
	public static final String ALIAS_SUPPLIER_ID = "厂家编号";
	
	public static final String ALIAS_SUPPLIER_NAME = "厂家名称";
	
	public static final String ALIAS_CONTACTS = "联系人";
	
	public static final String ALIAS_PINGYIN = "拼音码";
	
	public static final String ALIAS_ADDRESS = "住址";
	
	public static final String ALIAS_USER_TEL = "电话";
	
	public static final String ALIAS_USER_MOBILE = "移动电话";
	
	public static final String ALIAS_USER_EMAIL = " Email ";
	
	public static final String ALIAS_NOTE = "备注";
	
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * 厂家编号       db_column: SupplierID 
     */ 	
	
	private java.lang.Integer supplierId;
    /**
     * 厂家名称       db_column: SupplierName 
     */ 	
	@Length(max=100)
	private java.lang.String supplierName;
    /**
     * 联系人       db_column: Contacts 
     */ 	
	@Length(max=20)
	private java.lang.String contacts;
    /**
     * 拼音码       db_column: Pingyin 
     */ 	
	@Length(max=50)
	private java.lang.String pingyin;
    /**
     * 住址       db_column: Address 
     */ 	
	@Length(max=255)
	private java.lang.String address;
    /**
     * 电话       db_column: UserTel 
     */ 	
	@Length(max=20)
	private java.lang.String userTel;
    /**
     * 移动电话       db_column: UserMobile 
     */ 	
	@Length(max=20)
	private java.lang.String userMobile;
    /**
     *  Email        db_column: UserEmail 
     */ 	
	@Email @Length(max=100)
	private java.lang.String userEmail;
    /**
     * 备注       db_column: Note 
     */ 	
	@Length(max=255)
	private java.lang.String note;
	//columns END


	public Supplier(){
	}

	public Supplier(
		java.lang.Integer supplierId
	){
		this.supplierId = supplierId;
	}

	

	public void setSupplierId(java.lang.Integer value) {
		this.supplierId = value;
	}
	
	@Id @GeneratedValue(generator="custom-id")
	@GenericGenerator(name="custom-id", strategy = "native")
	@Column(name = "SupplierID", unique = true, nullable = false, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getSupplierId() {
		return this.supplierId;
	}
	
	@Column(name = "SupplierName", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getSupplierName() {
		return this.supplierName;
	}
	
	public void setSupplierName(java.lang.String value) {
		this.supplierName = value;
	}
	
	@Column(name = "Contacts", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.String getContacts() {
		return this.contacts;
	}
	
	public void setContacts(java.lang.String value) {
		this.contacts = value;
	}
	
	@Column(name = "Pingyin", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getPingyin() {
		return this.pingyin;
	}
	
	public void setPingyin(java.lang.String value) {
		this.pingyin = value;
	}
	
	@Column(name = "Address", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getAddress() {
		return this.address;
	}
	
	public void setAddress(java.lang.String value) {
		this.address = value;
	}
	
	@Column(name = "UserTel", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.String getUserTel() {
		return this.userTel;
	}
	
	public void setUserTel(java.lang.String value) {
		this.userTel = value;
	}
	
	@Column(name = "UserMobile", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.String getUserMobile() {
		return this.userMobile;
	}
	
	public void setUserMobile(java.lang.String value) {
		this.userMobile = value;
	}
	
	@Column(name = "UserEmail", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getUserEmail() {
		return this.userEmail;
	}
	
	public void setUserEmail(java.lang.String value) {
		this.userEmail = value;
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
				return this.supplierName;
			
	}
	
	private Set drugBasicInfos = new HashSet(0);
	public void setDrugBasicInfos(Set<DrugBasicInfo> drugBasicInfo){
		this.drugBasicInfos = drugBasicInfo;
	}
	
	@OneToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "supplierIdModel")
	public Set<DrugBasicInfo> getDrugBasicInfos() {
		return drugBasicInfos;
	}
	private Set drugStockIns = new HashSet(0);
	public void setDrugStockIns(Set<DrugStockIn> drugStockIn){
		this.drugStockIns = drugStockIn;
	}
	
	@OneToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "supplierIdModel")
	public Set<DrugStockIn> getDrugStockIns() {
		return drugStockIns;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("SupplierId",getSupplierId())
			.append("SupplierName",getSupplierName())
			.append("Contacts",getContacts())
			.append("Pingyin",getPingyin())
			.append("Address",getAddress())
			.append("UserTel",getUserTel())
			.append("UserMobile",getUserMobile())
			.append("UserEmail",getUserEmail())
			.append("Note",getNote())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getSupplierId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Supplier == false) return false;
		if(this == obj) return true;
		Supplier other = (Supplier)obj;
		return new EqualsBuilder()
			.append(getSupplierId(),other.getSupplierId())
			.isEquals();
	}
}

