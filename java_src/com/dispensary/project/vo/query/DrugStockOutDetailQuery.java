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


public class DrugStockOutDetailQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** 序列号 */
	private java.lang.Integer id;
	/** 流水号 */
	private java.lang.String serialNumber;
	/** 药品编码 */
	private java.lang.Integer drugId;
	/** 出库数量 */
	private java.lang.Integer outAmount;
	/** 出库金额 */
	private java.lang.Long outPrice;
	/** 出库去向 */
	private java.lang.Integer outPlaceId;
	/** 出库源位置 */
	private java.lang.Integer fromPlaceId;
	/** productionDate */
	private java.util.Date productionDateBegin;
	private java.util.Date productionDateEnd;
	private java.util.Date productionDate;

	public java.lang.Integer getId() {
		return this.id;
	}
	
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	public java.lang.String getSerialNumber() {
		return this.serialNumber;
	}
	
	public void setSerialNumber(java.lang.String value) {
		this.serialNumber = value;
	}
	
	public java.lang.Integer getDrugId() {
		return this.drugId;
	}
	
	public void setDrugId(java.lang.Integer value) {
		this.drugId = value;
	}
	
	public java.lang.Integer getOutAmount() {
		return this.outAmount;
	}
	
	public void setOutAmount(java.lang.Integer value) {
		this.outAmount = value;
	}
	
	public java.lang.Long getOutPrice() {
		return this.outPrice;
	}
	
	public void setOutPrice(java.lang.Long value) {
		this.outPrice = value;
	}
	
	public java.lang.Integer getOutPlaceId() {
		return this.outPlaceId;
	}
	
	public void setOutPlaceId(java.lang.Integer value) {
		this.outPlaceId = value;
	}
	
	public java.lang.Integer getFromPlaceId() {
		return this.fromPlaceId;
	}
	
	public void setFromPlaceId(java.lang.Integer value) {
		this.fromPlaceId = value;
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

		private java.lang.String serialNumberModelTag;
		private java.lang.String outPlaceIdModelTag;
		private java.lang.String fromPlaceIdModelTag;
		public java.lang.String getSerialNumberModelTag(){
			return this.serialNumberModelTag;
		}
		
		public  void setSerialNumberModelTag(String drugStockOutTag){
			this.serialNumberModelTag=drugStockOutTag;
		}
		public java.lang.String getOutPlaceIdModelTag(){
			return this.outPlaceIdModelTag;
		}
		
		public  void setOutPlaceIdModelTag(String drugPlaceTag){
			this.outPlaceIdModelTag=drugPlaceTag;
		}
		public java.lang.String getFromPlaceIdModelTag(){
			return this.fromPlaceIdModelTag;
		}
		
		public  void setFromPlaceIdModelTag(String drugPlaceTag){
			this.fromPlaceIdModelTag=drugPlaceTag;
		}
	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

