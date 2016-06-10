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
@Table(name = "symptom")
public class Symptom extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "症状";
	public static final String ALIAS_SYMPTOM_ID = "症状ID ";
	
	public static final String ALIAS_SYMPTOM_NAME = "症状名称";
	
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * 症状ID        db_column: SymptomID 
     */ 	
	
	private java.lang.Integer symptomId;
    /**
     * 症状名称       db_column: SymptomName 
     */ 	
	@Length(max=50)
	private java.lang.String symptomName;
	//columns END


	public Symptom(){
	}

	public Symptom(
		java.lang.Integer symptomId
	){
		this.symptomId = symptomId;
	}

	

	public void setSymptomId(java.lang.Integer value) {
		this.symptomId = value;
	}
	
	@Id @GeneratedValue(generator="custom-id")
	@GenericGenerator(name="custom-id", strategy = "native")
	@Column(name = "SymptomID", unique = true, nullable = false, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getSymptomId() {
		return this.symptomId;
	}
	
	@Column(name = "SymptomName", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getSymptomName() {
		return this.symptomName;
	}
	
	public void setSymptomName(java.lang.String value) {
		this.symptomName = value;
	}
	
	//获取表标识列的值,即作为关联类时显示的值
	@Transient
	public String getModelTagValue() {
				return this.symptomName;
			
	}
	
	private Set drugSymptomRelations = new HashSet(0);
	public void setDrugSymptomRelations(Set<DrugSymptomRelation> drugSymptomRelation){
		this.drugSymptomRelations = drugSymptomRelation;
	}
	
	@OneToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "symptomIdModel")
	public Set<DrugSymptomRelation> getDrugSymptomRelations() {
		return drugSymptomRelations;
	}
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("SymptomId",getSymptomId())
			.append("SymptomName",getSymptomName())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getSymptomId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Symptom == false) return false;
		if(this == obj) return true;
		Symptom other = (Symptom)obj;
		return new EqualsBuilder()
			.append(getSymptomId(),other.getSymptomId())
			.isEquals();
	}
}

