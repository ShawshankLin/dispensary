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
@Table(name = "usertype")
public class Usertype extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "用户类型";
	public static final String ALIAS_USER_TYPE_ID = "用户类型ID ";
	
	public static final String ALIAS_USER_TYPE_NAME = "用户类型名称";
	
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * 用户类型ID        db_column: UserTypeID 
     */ 	
	
	private java.lang.Integer userTypeId;
    /**
     * 用户类型名称       db_column: UserTypeName 
     */ 	
	@Length(max=30)
	private java.lang.String userTypeName;
	//columns END


	public Usertype(){
	}

	public Usertype(
		java.lang.Integer userTypeId
	){
		this.userTypeId = userTypeId;
	}

	

	public void setUserTypeId(java.lang.Integer value) {
		this.userTypeId = value;
	}
	
	@Id @GeneratedValue(generator="custom-id")
	@GenericGenerator(name="custom-id", strategy = "native")
	@Column(name = "UserTypeID", unique = true, nullable = false, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getUserTypeId() {
		return this.userTypeId;
	}
	
	@Column(name = "UserTypeName", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public java.lang.String getUserTypeName() {
		return this.userTypeName;
	}
	
	public void setUserTypeName(java.lang.String value) {
		this.userTypeName = value;
	}
	
	//获取表标识列的值,即作为关联类时显示的值
	@Transient
	public String getModelTagValue() {
				return this.userTypeName;
			
	}
	
	private Set userinfos = new HashSet(0);
	public void setUserinfos(Set<Userinfo> userinfo){
		this.userinfos = userinfo;
	}
	
	@OneToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "userTypeIdModel")
	public Set<Userinfo> getUserinfos() {
		return userinfos;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("UserTypeId",getUserTypeId())
			.append("UserTypeName",getUserTypeName())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getUserTypeId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Usertype == false) return false;
		if(this == obj) return true;
		Usertype other = (Usertype)obj;
		return new EqualsBuilder()
			.append(getUserTypeId(),other.getUserTypeId())
			.isEquals();
	}
}

