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
@Table(name = "student")
public class Student extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "学生";
	public static final String ALIAS_STU_NUM = "学生学号";
	
	public static final String ALIAS_STU_NAME = "学生姓名";
	
	public static final String ALIAS_SEX = "性别";
	
	public static final String ALIAS_BIRTH_DATE = "出生日期";
	
	public static final String ALIAS_IDCARD = "身份证";
	
	public static final String ALIAS_TEL = "学生电话";
	
	public static final String ALIAS_DEPARTMENT = "系别";
	
	public static final String ALIAS_MAJOR = "专业";
	
	public static final String ALIAS_STU_CLASS = "学生所在班级";
	
	public static final String ALIAS_ADDRESS = "宿舍号";
	
	
	//date formats
	public static final String FORMAT_BIRTH_DATE = DATE_FORMAT;
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * 学生学号       db_column: StuNum 
     */ 	
	@Length(max=20)
	private java.lang.String stuNum;
    /**
     * 学生姓名       db_column: StuName 
     */ 	
	@Length(max=20)
	private java.lang.String stuName;
    /**
     * 性别       db_column: Sex 
     */ 	
	
	private java.lang.Integer sex;
    /**
     * 出生日期       db_column: BirthDate 
     */ 	
	
	private java.util.Date birthDate;
    /**
     * 身份证       db_column: IDCard 
     */ 	
	@Length(max=30)
	private java.lang.String idcard;
    /**
     * 学生电话       db_column: Tel 
     */ 	
	@Length(max=30)
	private java.lang.String tel;
    /**
     * 系别       db_column: Department 
     */ 	
	@Length(max=30)
	private java.lang.String department;
    /**
     * 专业       db_column: Major 
     */ 	
	@Length(max=30)
	private java.lang.String major;
    /**
     * 学生所在班级       db_column: StuClass 
     */ 	
	@Length(max=50)
	private java.lang.String stuClass;
    /**
     * 宿舍号       db_column: Address 
     */ 	
	@Length(max=255)
	private java.lang.String address;
	//columns END


	public Student(){
	}

	public Student(
		java.lang.String stuNum
	){
		this.stuNum = stuNum;
	}

	

	public void setStuNum(java.lang.String value) {
		this.stuNum = value;
	}
	
	@Id @GeneratedValue(generator="custom-id")
	@GenericGenerator(name="custom-id", strategy = "assigned")
	@Column(name = "StuNum", unique = true, nullable = false, insertable = true, updatable = true, length = 20)
	public java.lang.String getStuNum() {
		return this.stuNum;
	}
	
	@Column(name = "StuName", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.String getStuName() {
		return this.stuName;
	}
	
	public void setStuName(java.lang.String value) {
		this.stuName = value;
	}
	
	@Column(name = "Sex", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getSex() {
		return this.sex;
	}
	
	public void setSex(java.lang.Integer value) {
		this.sex = value;
	}
	
	@Transient
	public String getBirthDateString() {
		return DateConvertUtils.format(getBirthDate(), FORMAT_BIRTH_DATE);
	}
	public void setBirthDateString(String value) {
		setBirthDate(DateConvertUtils.parse(value, FORMAT_BIRTH_DATE,java.util.Date.class));
	}
	
	@Column(name = "BirthDate", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	public java.util.Date getBirthDate() {
		return this.birthDate;
	}
	
	public void setBirthDate(java.util.Date value) {
		this.birthDate = value;
	}
	
	@Column(name = "IDCard", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public java.lang.String getIdcard() {
		return this.idcard;
	}
	
	public void setIdcard(java.lang.String value) {
		this.idcard = value;
	}
	
	@Column(name = "Tel", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public java.lang.String getTel() {
		return this.tel;
	}
	
	public void setTel(java.lang.String value) {
		this.tel = value;
	}
	
	@Column(name = "Department", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public java.lang.String getDepartment() {
		return this.department;
	}
	
	public void setDepartment(java.lang.String value) {
		this.department = value;
	}
	
	@Column(name = "Major", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public java.lang.String getMajor() {
		return this.major;
	}
	
	public void setMajor(java.lang.String value) {
		this.major = value;
	}
	
	@Column(name = "StuClass", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getStuClass() {
		return this.stuClass;
	}
	
	public void setStuClass(java.lang.String value) {
		this.stuClass = value;
	}
	
	@Column(name = "Address", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getAddress() {
		return this.address;
	}
	
	public void setAddress(java.lang.String value) {
		this.address = value;
	}
	
	//获取表标识列的值,即作为关联类时显示的值
	@Transient
	public String getModelTagValue() {
				return this.stuName;
			
	}
	
	private Set patiCaseHistorys = new HashSet(0);
	public void setPatiCaseHistorys(Set<PatiCaseHistory> patiCaseHistory){
		this.patiCaseHistorys = patiCaseHistory;
	}
	
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "stuNumModel")
	public Set<PatiCaseHistory> getPatiCaseHistorys() {
		return patiCaseHistorys;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("StuNum",getStuNum())
			.append("StuName",getStuName())
			.append("Sex",getSex())
			.append("BirthDate",getBirthDate())
			.append("Idcard",getIdcard())
			.append("Tel",getTel())
			.append("Department",getDepartment())
			.append("Major",getMajor())
			.append("StuClass",getStuClass())
			.append("Address",getAddress())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getStuNum())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Student == false) return false;
		if(this == obj) return true;
		Student other = (Student)obj;
		return new EqualsBuilder()
			.append(getStuNum(),other.getStuNum())
			.isEquals();
	}
}

