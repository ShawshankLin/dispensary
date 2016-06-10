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
@Table(name = "role_power_info")
public class RolePowerInfo extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "RolePowerInfo";
	public static final String ALIAS_ID = "序列号";
	
	public static final String ALIAS_ROLE_ID = "角色ID ";
	
	public static final String ALIAS_POWER_ID = "权限ID";
	
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * 序列号       db_column: ID 
     */ 	
	
	private java.lang.Integer id;
    /**
     * 角色ID        db_column: RoleID 
     */ 	
	
	private java.lang.Integer roleId;
    /**
     * 权限ID       db_column: PowerID 
     */ 	
	
	private java.lang.Integer powerId;
	//columns END


	public RolePowerInfo(){
	}

	public RolePowerInfo(
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
	
	@Column(name = "RoleID", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getRoleId() {
		return this.roleId;
	}
	
	public void setRoleId(java.lang.Integer value) {
		this.roleId = value;
	}
	
	@Column(name = "PowerID", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getPowerId() {
		return this.powerId;
	}
	
	public void setPowerId(java.lang.Integer value) {
		this.powerId = value;
	}
	
	//获取表标识列的值,即作为关联类时显示的值
	@Transient
	public String getModelTagValue() {
				return this.id+"";
			
	}
	
	private Power powerIdModel;
	public void setPowerIdModel(Power power){
		this.powerIdModel = power;
	}
	
	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "PowerID",nullable = false, insertable = false, updatable = false) 
	})
	public Power getPowerIdModel() {
		return powerIdModel;
	}
	private Role roleIdModel;
	public void setRoleIdModel(Role role){
		this.roleIdModel = role;
	}
	
	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "RoleID",nullable = false, insertable = false, updatable = false) 
	})
	public Role getRoleIdModel() {
		return roleIdModel;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("RoleId",getRoleId())
			.append("PowerId",getPowerId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof RolePowerInfo == false) return false;
		if(this == obj) return true;
		RolePowerInfo other = (RolePowerInfo)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

