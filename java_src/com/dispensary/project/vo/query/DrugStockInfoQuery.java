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


public class DrugStockInfoQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** 序列号 */
	private java.lang.Integer id;
	/** 药品编码 */
	private java.lang.Integer drugId;
	/** 位置编码 */
	private java.lang.Integer drugPlaceId;
	/** productionDate */
	private java.util.Date productionDateBegin;
	private java.util.Date productionDateEnd;
	private java.util.Date productionDate;
	/** 当前数量 */
	private java.lang.Float curAmount;

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
	
	public java.lang.Integer getDrugPlaceId() {
		return this.drugPlaceId;
	}
	
	public void setDrugPlaceId(java.lang.Integer value) {
		this.drugPlaceId = value;
	}
	
	public java.util.Date getProductionDateBegin() {
		return this.productionDateBegin;
	}
	
	public void setProductionDateBegin(java.util.Date value) {
		this.productionDateBegin = value;
	}	
	
	public java.util.Date getProductionDateEnd() {
		return this.productionDateEnd;
	}
	
	public void setProductionDateEnd(java.util.Date value) {
		this.productionDateEnd = value;
	}
	
	public java.util.Date getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(java.util.Date productionDate) {
		this.productionDate = productionDate;
	}

	public java.lang.Float getCurAmount() {
		return this.curAmount;
	}
	
	public void setCurAmount(java.lang.Float value) {
		this.curAmount = value;
	}
	
		private java.lang.String drugIdModelTag;
		private java.lang.String drugPlaceIdModelTag;
		public java.lang.String getDrugIdModelTag(){
			return this.drugIdModelTag;
		}
		
		public  void setDrugIdModelTag(String drugBasicInfoTag){
			this.drugIdModelTag=drugBasicInfoTag;
		}
		public java.lang.String getDrugPlaceIdModelTag(){
			return this.drugPlaceIdModelTag;
		}
		
		public  void setDrugPlaceIdModelTag(String drugPlaceTag){
			this.drugPlaceIdModelTag=drugPlaceTag;
		}
	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

