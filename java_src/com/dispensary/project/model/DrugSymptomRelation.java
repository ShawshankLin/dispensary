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
@Table(name = "drug_symptom_relation")
public class DrugSymptomRelation extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "DrugSymptomRelation";
	public static final String ALIAS_ID = "id";
	
	public static final String ALIAS_DRUG_ID = "drugId";
	
	public static final String ALIAS_SYMPTOM_ID = "symptomId";
	
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * id       db_column: ID 
     */ 	
	
	private java.lang.Integer id;
    /**
     * drugId       db_column: DrugID 
     */ 	
	@NotNull 
	private java.lang.Integer drugId;
    /**
     * symptomId       db_column: SymptomID 
     */ 	
	@NotNull 
	private java.lang.Integer symptomId;
	//columns END


	public DrugSymptomRelation(){
	}

	public DrugSymptomRelation(
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
	
	@Column(name = "DrugID", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getDrugId() {
		return this.drugId;
	}
	
	public void setDrugId(java.lang.Integer value) {
		this.drugId = value;
	}
	
	@Column(name = "SymptomID", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getSymptomId() {
		return this.symptomId;
	}
	
	public void setSymptomId(java.lang.Integer value) {
		this.symptomId = value;
	}
	
	//获取表标识列的值,即作为关联类时显示的值
	@Transient
	public String getModelTagValue() {
				return this.id+"";
			
	}
	
	private Symptom symptomIdModel;
	public void setSymptomIdModel(Symptom symptom){
		this.symptomIdModel = symptom;
	}
	
	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "SymptomID",nullable = false, insertable = false, updatable = false) 
	})
	public Symptom getSymptomIdModel() {
		return symptomIdModel;
	}
	private DrugBasicInfo drugIdModel;
	public void setDrugIdModel(DrugBasicInfo drugBasicInfo){
		this.drugIdModel = drugBasicInfo;
	}
	
	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "DrugID",nullable = false, insertable = false, updatable = false) 
	})
	public DrugBasicInfo getDrugIdModel() {
		return drugIdModel;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("DrugId",getDrugId())
			.append("SymptomId",getSymptomId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof DrugSymptomRelation == false) return false;
		if(this == obj) return true;
		DrugSymptomRelation other = (DrugSymptomRelation)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

