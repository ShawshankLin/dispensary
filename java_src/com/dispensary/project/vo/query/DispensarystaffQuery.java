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


public class DispensarystaffQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** 医务人员ID  */
	private java.lang.Integer meStId;
	/** 真实姓名 */
	private java.lang.String meStName;
	/** 性别 */
	private java.lang.Integer sex;
	/** 年龄 */
	private java.lang.Integer age;
	/** 学历 */
	private java.lang.String education;
	/** 住址 */
	private java.lang.String address;
	/** 电话 */
	private java.lang.String tel;
	/** 移动电话 */
	private java.lang.String mobile;
	/** Email */
	private java.lang.String email;
	/** 所属科室 */
	private java.lang.String workPlace;
	/** 备注 */
	private java.lang.String note;

	public java.lang.Integer getMeStId() {
		return this.meStId;
	}
	
	public void setMeStId(java.lang.Integer value) {
		this.meStId = value;
	}
	
	public java.lang.String getMeStName() {
		return this.meStName;
	}
	
	public void setMeStName(java.lang.String value) {
		this.meStName = value;
	}
	
	public java.lang.Integer getSex() {
		return this.sex;
	}
	
	public void setSex(java.lang.Integer value) {
		this.sex = value;
	}
	
	public java.lang.Integer getAge() {
		return this.age;
	}
	
	public void setAge(java.lang.Integer value) {
		this.age = value;
	}
	
	public java.lang.String getEducation() {
		return this.education;
	}
	
	public void setEducation(java.lang.String value) {
		this.education = value;
	}
	
	public java.lang.String getAddress() {
		return this.address;
	}
	
	public void setAddress(java.lang.String value) {
		this.address = value;
	}
	
	public java.lang.String getTel() {
		return this.tel;
	}
	
	public void setTel(java.lang.String value) {
		this.tel = value;
	}
	
	public java.lang.String getMobile() {
		return this.mobile;
	}
	
	public void setMobile(java.lang.String value) {
		this.mobile = value;
	}
	
	public java.lang.String getEmail() {
		return this.email;
	}
	
	public void setEmail(java.lang.String value) {
		this.email = value;
	}
	
	public java.lang.String getWorkPlace() {
		return this.workPlace;
	}
	
	public void setWorkPlace(java.lang.String value) {
		this.workPlace = value;
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

