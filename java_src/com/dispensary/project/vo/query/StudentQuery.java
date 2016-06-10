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


public class StudentQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** 学生学号 */
	private java.lang.String stuNum;
	/** 学生姓名 */
	private java.lang.String stuName;
	/** 性别 */
	private java.lang.Integer sex;
	/** 出生日期 */
	private java.util.Date birthDateBegin;
	private java.util.Date birthDateEnd;
	private java.util.Date birthDate;
	/** 身份证 */
	private java.lang.String idcard;
	/** 学生电话 */
	private java.lang.String tel;
	/** 系别 */
	private java.lang.String department;
	/** 专业 */
	private java.lang.String major;
	/** 学生所在班级 */
	private java.lang.String stuClass;
	/** 宿舍号 */
	private java.lang.String address;

	public java.lang.String getStuNum() {
		return this.stuNum;
	}
	
	public void setStuNum(java.lang.String value) {
		this.stuNum = value;
	}
	
	public java.lang.String getStuName() {
		return this.stuName;
	}
	
	public void setStuName(java.lang.String value) {
		this.stuName = value;
	}
	
	public java.lang.Integer getSex() {
		return this.sex;
	}
	
	public void setSex(java.lang.Integer value) {
		this.sex = value;
	}
	
	public java.util.Date getBirthDateBegin() {
		return this.birthDateBegin;
	}
	
	public void setBirthDateBegin(java.util.Date value) {
		this.birthDateBegin = value;
	}	
	
	public java.util.Date getBirthDateEnd() {
		return this.birthDateEnd;
	}
	
	public void setBirthDateEnd(java.util.Date value) {
		this.birthDateEnd = value;
	}
	
	public java.lang.String getIdcard() {
		return this.idcard;
	}
	
	public void setIdcard(java.lang.String value) {
		this.idcard = value;
	}
	
	public java.lang.String getTel() {
		return this.tel;
	}
	
	public void setTel(java.lang.String value) {
		this.tel = value;
	}
	
	public java.lang.String getDepartment() {
		return this.department;
	}
	
	public void setDepartment(java.lang.String value) {
		this.department = value;
	}
	
	public java.lang.String getMajor() {
		return this.major;
	}
	
	public void setMajor(java.lang.String value) {
		this.major = value;
	}
	
	public java.util.Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(java.util.Date birthDate) {
		this.birthDate = birthDate;
	}

	public java.lang.String getStuClass() {
		return this.stuClass;
	}
	
	public void setStuClass(java.lang.String value) {
		this.stuClass = value;
	}
	
	public java.lang.String getAddress() {
		return this.address;
	}
	
	public void setAddress(java.lang.String value) {
		this.address = value;
	}
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

