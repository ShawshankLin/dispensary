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


public class DrugStockInDetailQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** 序列号 */
	private java.lang.Integer id;
	/** 流水号 */
	private java.lang.String serialNumber;
	/** 药品编码 */
	private java.lang.Integer drugId;
	/** 药品存放位置 */
	private java.lang.Integer inPlaceId;
	/** 金额 */
	private java.lang.Float inPrice;
	/** 入库数量 */
	private java.lang.Float inStocks;
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
	
	public java.lang.Integer getInPlaceId() {
		return this.inPlaceId;
	}
	
	public void setInPlaceId(java.lang.Integer value) {
		this.inPlaceId = value;
	}
	
	public java.lang.Float getInPrice() {
		return this.inPrice;
	}
	
	public void setInPrice(java.lang.Float value) {
		this.inPrice = value;
	}
	
	public java.lang.Float getInStocks() {
		return this.inStocks;
	}
	
	public void setInStocks(java.lang.Float value) {
		this.inStocks = value;
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

		private java.lang.String inPlaceIdModelTag;
		private java.lang.String serialNumberModelTag;
		private java.lang.String drugIdModelTag;
		public java.lang.String getInPlaceIdModelTag(){
			return this.inPlaceIdModelTag;
		}
		
		public  void setInPlaceIdModelTag(String drugPlaceTag){
			this.inPlaceIdModelTag=drugPlaceTag;
		}
		public java.lang.String getSerialNumberModelTag(){
			return this.serialNumberModelTag;
		}
		
		public  void setSerialNumberModelTag(String drugStockInTag){
			this.serialNumberModelTag=drugStockInTag;
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

