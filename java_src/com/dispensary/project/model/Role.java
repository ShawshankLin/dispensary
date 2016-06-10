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
@Table(name = "role")
public class Role extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "角色";
	public static final String ALIAS_ROLE_ID = "角色ID ";
	
	public static final String ALIAS_ROLE_NAME = "角色名称";
	
	public static final String ALIAS_ROLE_DESCRIBE = "角色描述";
	
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * 角色ID        db_column: RoleID 
     */ 	
	
	private java.lang.Integer roleId;
    /**
     * 角色名称       db_column: RoleName 
     */ 	
	@Length(max=20)
	private java.lang.String roleName;
    /**
     * 角色描述       db_column: RoleDescribe 
     */ 	
	@Length(max=100)
	private java.lang.String roleDescribe;
	//columns END


	public Role(){
	}

	public Role(
		java.lang.Integer roleId
	){
		this.roleId = roleId;
	}

	

	public void setRoleId(java.lang.Integer value) {
		this.roleId = value;
	}
	
	@Id @GeneratedValue(generator="custom-id")
	@GenericGenerator(name="custom-id", strategy = "native")
	@Column(name = "RoleID", unique = true, nullable = false, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getRoleId() {
		return this.roleId;
	}
	
	@Column(name = "RoleName", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.String getRoleName() {
		return this.roleName;
	}
	
	public void setRoleName(java.lang.String value) {
		this.roleName = value;
	}
	
	@Column(name = "RoleDescribe", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getRoleDescribe() {
		return this.roleDescribe;
	}
	
	public void setRoleDescribe(java.lang.String value) {
		this.roleDescribe = value;
	}
	
	//获取表标识列的值,即作为关联类时显示的值
	@Transient
	public String getModelTagValue() {
				return this.roleName;
			
	}
	
	private Set rolePowerInfos = new HashSet(0);
	public void setRolePowerInfos(Set<RolePowerInfo> rolePowerInfo){
		this.rolePowerInfos = rolePowerInfo;
	}
	
	@OneToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "roleIdModel")
	public Set<RolePowerInfo> getRolePowerInfos() {
		return rolePowerInfos;
	}
	private Set userRoleInfos = new HashSet(0);
	public void setUserRoleInfos(Set<UserRoleInfo> userRoleInfo){
		this.userRoleInfos = userRoleInfo;
	}
	
	@OneToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "roleIdModel")
	public Set<UserRoleInfo> getUserRoleInfos() {
		return userRoleInfos;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("RoleId",getRoleId())
			.append("RoleName",getRoleName())
			.append("RoleDescribe",getRoleDescribe())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getRoleId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Role == false) return false;
		if(this == obj) return true;
		Role other = (Role)obj;
		return new EqualsBuilder()
			.append(getRoleId(),other.getRoleId())
			.isEquals();
	}
}

