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
@Table(name = "userinfo")
public class Userinfo extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "用户";
	public static final String ALIAS_USER_ID = "登录ID ";
	
	public static final String ALIAS_ME_ST_ID = "员工ID";
	
	public static final String ALIAS_PASSWORD = "用户密码";
	
	public static final String ALIAS_USER_TYPE_ID = "用户类型";
	
	public static final String ALIAS_USER_NAME = "登录名";
	
	public static final String ALIAS_IF_VALIDITY = "是否有效账户";
	
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * 登录ID        db_column: UserID 
     */ 	
	
	private java.lang.Integer userId;
    /**
     * 员工ID       db_column: MeStID 
     */ 	
	@NotNull 
	private java.lang.Integer meStId;
    /**
     * 用户密码       db_column: Password 
     */ 	
	@Length(max=32)
	private java.lang.String password;
    /**
     * userTypeId       db_column: UserTypeID 
     */ 	
	@NotNull 
	private java.lang.Integer userTypeId;
    /**
     * 登录名       db_column: UserName 
     */ 	
	@NotBlank @Length(max=30)
	private java.lang.String userName;
    /**
     * 是否有效账户       db_column: If_validity 
     */ 	
	
	private java.lang.Integer ifValidity;
	//columns END


	public Userinfo(){
	}

	public Userinfo(
		java.lang.Integer userId
	){
		this.userId = userId;
	}

	

	public void setUserId(java.lang.Integer value) {
		this.userId = value;
	}
	
	@Id @GeneratedValue(generator="custom-id")
	@GenericGenerator(name="custom-id", strategy = "assigned")
	@Column(name = "UserID", unique = true, nullable = false, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getUserId() {
		return this.userId;
	}
	
	@Column(name = "MeStID", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getMeStId() {
		return this.meStId;
	}
	
	public void setMeStId(java.lang.Integer value) {
		this.meStId = value;
	}
	
	@Column(name = "Password", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public java.lang.String getPassword() {
		return this.password;
	}
	
	public void setPassword(java.lang.String value) {
		this.password = value;
	}
	
	@Column(name = "UserTypeID", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getUserTypeId() {
		return this.userTypeId;
	}
	
	public void setUserTypeId(java.lang.Integer value) {
		this.userTypeId = value;
	}
	
	@Column(name = "UserName", unique = false, nullable = false, insertable = true, updatable = true, length = 30)
	public java.lang.String getUserName() {
		return this.userName;
	}
	
	public void setUserName(java.lang.String value) {
		this.userName = value;
	}
	
	@Column(name = "If_validity", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getIfValidity() {
		return this.ifValidity;
	}
	
	public void setIfValidity(java.lang.Integer value) {
		this.ifValidity = value;
	}
	
	//获取表标识列的值,即作为关联类时显示的值
	@Transient
	public String getModelTagValue() {
				return this.userName;
			
	}
	
	private Set userRoleInfos = new HashSet(0);
	public void setUserRoleInfos(Set<UserRoleInfo> userRoleInfo){
		this.userRoleInfos = userRoleInfo;
	}
	
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "userIdModel")
	public Set<UserRoleInfo> getUserRoleInfos() {
		return userRoleInfos;
	}
	private Usertype userTypeIdModel;
	public void setUserTypeIdModel(Usertype usertype){
		this.userTypeIdModel = usertype;
	}
	
	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "UserTypeID",nullable = false, insertable = false, updatable = false) 
	})
	public Usertype getUserTypeIdModel() {
		return userTypeIdModel;
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
			.append("UserId",getUserId())
			.append("MeStId",getMeStId())
			.append("Password",getPassword())
			.append("UserTypeId",getUserTypeId())
			.append("UserName",getUserName())
			.append("IfValidity",getIfValidity())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getUserId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Userinfo == false) return false;
		if(this == obj) return true;
		Userinfo other = (Userinfo)obj;
		return new EqualsBuilder()
			.append(getUserId(),other.getUserId())
			.isEquals();
	}
}

