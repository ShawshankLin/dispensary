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
@Table(name = "log")
public class Log extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "Log";
	public static final String ALIAS_ID = "id";
	
	public static final String ALIAS_USER_ID = "userId";
	
	public static final String ALIAS_TAG = "tag";
	
	public static final String ALIAS_SUMMARY = "summary";
	
	public static final String ALIAS_IP = "ip";
	
	public static final String ALIAS_DATE = "date";
	
	
	//date formats
	public static final String FORMAT_DATE = DATE_FORMAT;
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * id       db_column: ID 
     */ 	
	
	private java.lang.Integer id;
    /**
     * userId       db_column: UserID 
     */ 	
	@NotNull 
	private java.lang.Integer userId;
    /**
     * tag       db_column: Tag 
     */ 	
	@Length(max=20)
	private java.lang.String tag;
    /**
     * summary       db_column: Summary 
     */ 	
	@Length(max=255)
	private java.lang.String summary;
    /**
     * ip       db_column: Ip 
     */ 	
	@Length(max=50)
	private java.lang.String ip;
    /**
     * date       db_column: Date 
     */ 	
	
	private java.util.Date date;
	//columns END


	public Log(){
	}

	public Log(
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
	
	@Column(name = "UserID", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getUserId() {
		return this.userId;
	}
	
	public void setUserId(java.lang.Integer value) {
		this.userId = value;
	}
	
	@Column(name = "Tag", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.String getTag() {
		return this.tag;
	}
	
	public void setTag(java.lang.String value) {
		this.tag = value;
	}
	
	@Column(name = "Summary", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getSummary() {
		return this.summary;
	}
	
	public void setSummary(java.lang.String value) {
		this.summary = value;
	}
	
	@Column(name = "Ip", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getIp() {
		return this.ip;
	}
	
	public void setIp(java.lang.String value) {
		this.ip = value;
	}
	
	@Transient
	public String getDateString() {
		return DateConvertUtils.format(getDate(), FORMAT_DATE);
	}
	public void setDateString(String value) {
		setDate(DateConvertUtils.parse(value, FORMAT_DATE,java.util.Date.class));
	}
	
	@Column(name = "Date", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	public java.util.Date getDate() {
		return this.date;
	}
	
	public void setDate(java.util.Date value) {
		this.date = value;
	}
	
	//获取表标识列的值,即作为关联类时显示的值
	@Transient
	public String getModelTagValue() {
				return this.id+"";
			
	}
	
	private Userinfo userIdModel;
	public void setUserIdModel(Userinfo userinfo){
		this.userIdModel = userinfo;
	}
	
	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "UserID",nullable = false, insertable = false, updatable = false) 
	})
	public Userinfo getUserIdModel() {
		return userIdModel;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("UserId",getUserId())
			.append("Tag",getTag())
			.append("Summary",getSummary())
			.append("Ip",getIp())
			.append("Date",getDate())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Log == false) return false;
		if(this == obj) return true;
		Log other = (Log)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

