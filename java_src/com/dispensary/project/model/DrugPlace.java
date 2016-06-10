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
@Table(name = "drug_place")
public class DrugPlace extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "药品存放位置";
	public static final String ALIAS_DRUG_PLACE_ID = "位置编码";
	
	public static final String ALIAS_DRUG_PLACE = "位置描述";
	
	public static final String ALIAS_STATUS = "位置状态";
	
	public static final String ALIAS_PLACE_TYPE = "位置类型";
	
	public static final String ALIAS_IS_STOREROOM = "库房类型";
	
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * 位置编码       db_column: DrugPlaceID 
     */ 	
	
	private java.lang.Integer drugPlaceId;
    /**
     * 位置描述       db_column: DrugPlace 
     */ 	
	@Length(max=50)
	private java.lang.String drugPlace;
    /**
     * 位置状态       db_column: Status 
     */ 	
	
	private java.lang.Integer status;
    /**
     * 位置类型       db_column: PlaceType 
     */ 	
	
	private java.lang.Integer placeType;
    /**
     * 库房类型       db_column: isStoreroom 
     */ 	
	
	private java.lang.Integer isStoreroom;
	//columns END

	private String id;


	public DrugPlace(){
	}

	public DrugPlace(
		java.lang.Integer drugPlaceId
	){
		this.drugPlaceId = drugPlaceId;
	}

	

	public void setDrugPlaceId(java.lang.Integer value) {
		this.drugPlaceId = value;
	}
	
	@Id @GeneratedValue(generator="custom-id")
	@GenericGenerator(name="custom-id", strategy = "native")
	@Column(name = "DrugPlaceID", unique = true, nullable = false, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getDrugPlaceId() {
		return this.drugPlaceId;
	}
	
	@Column(name = "DrugPlace", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getDrugPlace() {
		return this.drugPlace;
	}
	
	public void setDrugPlace(java.lang.String value) {
		this.drugPlace = value;
	}
	
	@Column(name = "Status", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getStatus() {
		return this.status;
	}
	
	public void setStatus(java.lang.Integer value) {
		this.status = value;
	}
	
	@Column(name = "PlaceType", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getPlaceType() {
		return this.placeType;
	}
	
	public void setPlaceType(java.lang.Integer value) {
		this.placeType = value;
	}
	
	@Column(name = "isStoreroom", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getIsStoreroom() {
		return this.isStoreroom;
	}
	
	public void setIsStoreroom(java.lang.Integer value) {
		this.isStoreroom = value;
	}
	
	//获取表标识列的值,即作为关联类时显示的值
	@Transient
	public String getModelTagValue() {
				return this.id+"";
			
	}
	
	private Set drugStockInfos = new HashSet(0);
	public void setDrugStockInfos(Set<DrugStockInfo> drugStockInfo){
		this.drugStockInfos = drugStockInfo;
	}
	
	@OneToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "drugPlaceIdModel")
	public Set<DrugStockInfo> getDrugStockInfos() {
		return drugStockInfos;
	}
	private Set drugStockOutDetails = new HashSet(0);
	public void setDrugStockOutDetails(Set<DrugStockOutDetail> drugStockOutDetail){
		this.drugStockOutDetails = drugStockOutDetail;
	}
	
	@OneToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "outPlaceIdModel")
	public Set<DrugStockOutDetail> getDrugStockOutDetails() {
		return drugStockOutDetails;
	}
	private Set drugStockInDetails = new HashSet(0);
	public void setDrugStockInDetails(Set<DrugStockInDetail> drugStockInDetail){
		this.drugStockInDetails = drugStockInDetail;
	}
	
	@OneToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "inPlaceIdModel")
	public Set<DrugStockInDetail> getDrugStockInDetails() {
		return drugStockInDetails;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("DrugPlaceId",getDrugPlaceId())
			.append("DrugPlace",getDrugPlace())
			.append("Status",getStatus())
			.append("PlaceType",getPlaceType())
			.append("IsStoreroom",getIsStoreroom())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getDrugPlaceId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof DrugPlace == false) return false;
		if(this == obj) return true;
		DrugPlace other = (DrugPlace)obj;
		return new EqualsBuilder()
			.append(getDrugPlaceId(),other.getDrugPlaceId())
			.isEquals();
	}
}

