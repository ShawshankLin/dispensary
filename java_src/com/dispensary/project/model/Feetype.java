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
@Table(name = "feetype")
public class Feetype extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "费用类型";
	public static final String ALIAS_FEE_TYPE_ID = "费用ID";
	
	public static final String ALIAS_FEE_TYPE_NAME = "费用名称";
	
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * 费用ID       db_column: FeeTypeID 
     */ 	
	
	private java.lang.Integer feeTypeId;
    /**
     * 费用名称       db_column: FeeTypeName 
     */ 	
	@Length(max=50)
	private java.lang.String feeTypeName;
	//columns END


	public Feetype(){
	}

	public Feetype(
		java.lang.Integer feeTypeId
	){
		this.feeTypeId = feeTypeId;
	}

	

	public void setFeeTypeId(java.lang.Integer value) {
		this.feeTypeId = value;
	}
	
	@Id @GeneratedValue(generator="custom-id")
	@GenericGenerator(name="custom-id", strategy = "native")
	@Column(name = "FeeTypeID", unique = true, nullable = false, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getFeeTypeId() {
		return this.feeTypeId;
	}
	
	@Column(name = "FeeTypeName", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getFeeTypeName() {
		return this.feeTypeName;
	}
	
	public void setFeeTypeName(java.lang.String value) {
		this.feeTypeName = value;
	}
	
	//获取表标识列的值,即作为关联类时显示的值
	@Transient
	public String getModelTagValue() {
				return this.feeTypeName;
			
	}
	
	private Set drugBasicInfos = new HashSet(0);
	public void setDrugBasicInfos(Set<DrugBasicInfo> drugBasicInfo){
		this.drugBasicInfos = drugBasicInfo;
	}
	
	@OneToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "feeTypeIdModel")
	public Set<DrugBasicInfo> getDrugBasicInfos() {
		return drugBasicInfos;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("FeeTypeId",getFeeTypeId())
			.append("FeeTypeName",getFeeTypeName())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getFeeTypeId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Feetype == false) return false;
		if(this == obj) return true;
		Feetype other = (Feetype)obj;
		return new EqualsBuilder()
			.append(getFeeTypeId(),other.getFeeTypeId())
			.isEquals();
	}
}

