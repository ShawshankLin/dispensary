/*
 */

package com.dispensary.project.vo.query;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;

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


public class SupplierQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** 厂家编号 */
	private java.lang.Integer supplierId;
	/** 厂家名称 */
	private java.lang.String supplierName;
	/** 联系人 */
	private java.lang.String contacts;
	/** 拼音码 */
	private java.lang.String pingyin;
	/** 住址 */
	private java.lang.String address;
	/** 电话 */
	private java.lang.String userTel;
	/** 移动电话 */
	private java.lang.String userMobile;
	/**  Email  */
	private java.lang.String userEmail;
	/** 备注 */
	private java.lang.String note;

	public java.lang.Integer getSupplierId() {
		return this.supplierId;
	}
	
	public void setSupplierId(java.lang.Integer value) {
		this.supplierId = value;
	}
	
	public java.lang.String getSupplierName() {
		return this.supplierName;
	}
	
	public void setSupplierName(java.lang.String value) {
		this.supplierName = value;
	}
	
	public java.lang.String getContacts() {
		return this.contacts;
	}
	
	public void setContacts(java.lang.String value) {
		this.contacts = value;
	}
	
	public java.lang.String getPingyin() {
		return this.pingyin;
	}
	
	public void setPingyin(java.lang.String value) {
		this.pingyin = value;
	}
	
	public java.lang.String getAddress() {
		return this.address;
	}
	
	public void setAddress(java.lang.String value) {
		this.address = value;
	}
	
	public java.lang.String getUserTel() {
		return this.userTel;
	}
	
	public void setUserTel(java.lang.String value) {
		this.userTel = value;
	}
	
	public java.lang.String getUserMobile() {
		return this.userMobile;
	}
	
	public void setUserMobile(java.lang.String value) {
		this.userMobile = value;
	}
	
	public java.lang.String getUserEmail() {
		return this.userEmail;
	}
	
	public void setUserEmail(java.lang.String value) {
		this.userEmail = value;
	}
	
	public java.lang.String getNote() {
		return this.note;
	}
	
	public void setNote(java.lang.String value) {
		this.note = value;
	}
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

