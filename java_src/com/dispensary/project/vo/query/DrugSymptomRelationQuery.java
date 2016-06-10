/*
 */

package com.dispensary.project.vo.query;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;

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


public class DrugSymptomRelationQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** id */
	private java.lang.Integer id;
	/** drugId */
	private java.lang.Integer drugId;
	/** symptomId */
	private java.lang.Integer symptomId;

	public java.lang.Integer getId() {
		return this.id;
	}
	
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	public java.lang.Integer getDrugId() {
		return this.drugId;
	}
	
	public void setDrugId(java.lang.Integer value) {
		this.drugId = value;
	}
	
	public java.lang.Integer getSymptomId() {
		return this.symptomId;
	}
	
	public void setSymptomId(java.lang.Integer value) {
		this.symptomId = value;
	}
	
		private java.lang.String symptomIdModelTag;
		private java.lang.String drugIdModelTag;
		public java.lang.String getSymptomIdModelTag(){
			return this.symptomIdModelTag;
		}
		
		public  void setSymptomIdModelTag(String symptomTag){
			this.symptomIdModelTag=symptomTag;
		}
		public java.lang.String getDrugIdModelTag(){
			return this.drugIdModelTag;
		}
		
		public  void setDrugIdModelTag(String drugBasicInfoTag){
			this.drugIdModelTag=drugBasicInfoTag;
		}
	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

