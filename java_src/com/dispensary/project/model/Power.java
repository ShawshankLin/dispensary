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
@Table(name = "power")
public class Power extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "Power";
	public static final String ALIAS_POWER_ID = "权限ID ";
	
	public static final String ALIAS_POWER_NAME = "权限名称";
	
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * 权限ID        db_column: PowerID 
     */ 	
	
	private java.lang.Integer powerId;
    /**
     * 权限名称       db_column: PowerName 
     */ 	
	@Length(max=20)
	private java.lang.String powerName;
	//columns END


	public Power(){
	}

	public Power(
		java.lang.Integer powerId
	){
		this.powerId = powerId;
	}

	

	public void setPowerId(java.lang.Integer value) {
		this.powerId = value;
	}
	
	@Id @GeneratedValue(generator="custom-id")
	@GenericGenerator(name="custom-id", strategy = "native")
	@Column(name = "PowerID", unique = true, nullable = false, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getPowerId() {
		return this.powerId;
	}
	
	@Column(name = "PowerName", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.String getPowerName() {
		return this.powerName;
	}
	
	public void setPowerName(java.lang.String value) {
		this.powerName = value;
	}
	
	//获取表标识列的值,即作为关联类时显示的值
	@Transient
	public String getModelTagValue() {
				return this.powerName;
			
	}
	
	private Set rolePowerInfos = new HashSet(0);
	public void setRolePowerInfos(Set<RolePowerInfo> rolePowerInfo){
		this.rolePowerInfos = rolePowerInfo;
	}
	
	@OneToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "powerIdModel")
	public Set<RolePowerInfo> getRolePowerInfos() {
		return rolePowerInfos;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("PowerId",getPowerId())
			.append("PowerName",getPowerName())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getPowerId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Power == false) return false;
		if(this == obj) return true;
		Power other = (Power)obj;
		return new EqualsBuilder()
			.append(getPowerId(),other.getPowerId())
			.isEquals();
	}
}

