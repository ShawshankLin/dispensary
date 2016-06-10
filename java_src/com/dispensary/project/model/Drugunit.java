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
@Table(name = "drugunit")
public class Drugunit extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "药品单位";
	public static final String ALIAS_DRUG_UNIT_ID = "药品单位ID ";
	
	public static final String ALIAS_DRUG_UNIT_NAME = "药品单位名称";
	
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * 药品单位ID        db_column: DrugUnitID 
     */ 	
	
	private java.lang.Integer drugUnitId;
    /**
     * 药品单位名称       db_column: DrugUnitName 
     */ 	
	@Length(max=50)
	private java.lang.String drugUnitName;
	//columns END


	public Drugunit(){
	}

	public Drugunit(
		java.lang.Integer drugUnitId
	){
		this.drugUnitId = drugUnitId;
	}

	

	public void setDrugUnitId(java.lang.Integer value) {
		this.drugUnitId = value;
	}
	
	@Id @GeneratedValue(generator="custom-id")
	@GenericGenerator(name="custom-id", strategy = "native")
	@Column(name = "DrugUnitID", unique = true, nullable = false, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getDrugUnitId() {
		return this.drugUnitId;
	}
	
	@Column(name = "DrugUnitName", unique = true, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getDrugUnitName() {
		return this.drugUnitName;
	}
	
	public void setDrugUnitName(java.lang.String value) {
		this.drugUnitName = value;
	}
	
	//获取表标识列的值,即作为关联类时显示的值
	@Transient
	public String getModelTagValue() {
				return this.drugUnitName;
			
	}
	
	private Set drugBasicInfos = new HashSet(0);
	public void setDrugBasicInfos(Set<DrugBasicInfo> drugBasicInfo){
		this.drugBasicInfos = drugBasicInfo;
	}
	
	@OneToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "drugKickBackModel")
	public Set<DrugBasicInfo> getDrugBasicInfos() {
		return drugBasicInfos;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("DrugUnitId",getDrugUnitId())
			.append("DrugUnitName",getDrugUnitName())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getDrugUnitId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Drugunit == false) return false;
		if(this == obj) return true;
		Drugunit other = (Drugunit)obj;
		return new EqualsBuilder()
			.append(getDrugUnitId(),other.getDrugUnitId())
			.isEquals();
	}
}

