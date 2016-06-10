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


public class DrugStockInQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** 序列号 */
	private java.lang.Integer id;
	/** 流水号 */
	private java.lang.String serialNumber;
	/** 入库单号 */
	private java.lang.String stockInId;
	/** 入库时间 */
	private java.util.Date inDateBegin;
	private java.util.Date inDateEnd;
	private java.util.Date inDate;
	/** 总数量 */
	private java.lang.Integer total;
	/** 总金额 */
	private java.lang.Float sum;
	/** 入库经手人编号 */
	private java.lang.Integer operatorId;
	/** 厂家编号 */
	private java.lang.Integer supplierId;

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
	
	public java.lang.String getStockInId() {
		return this.stockInId;
	}
	
	public void setStockInId(java.lang.String value) {
		this.stockInId = value;
	}
	
	public java.util.Date getInDateBegin() {
		return this.inDateBegin;
	}
	
	public void setInDateBegin(java.util.Date value) {
		this.inDateBegin = value;
	}	
	
	public java.util.Date getInDateEnd() {
		return this.inDateEnd;
	}
	
	public void setInDateEnd(java.util.Date value) {
		this.inDateEnd = value;
	}
	
	public java.lang.Integer getTotal() {
		return this.total;
	}
	
	public void setTotal(java.lang.Integer value) {
		this.total = value;
	}
	
	public java.util.Date getInDate() {
		return inDate;
	}

	public void setInDate(java.util.Date inDate) {
		this.inDate = inDate;
	}

	public java.lang.Float getSum() {
		return this.sum;
	}
	
	public void setSum(java.lang.Float value) {
		this.sum = value;
	}
	
	public java.lang.Integer getOperatorId() {
		return this.operatorId;
	}
	
	public void setOperatorId(java.lang.Integer value) {
		this.operatorId = value;
	}
	
	public java.lang.Integer getSupplierId() {
		return this.supplierId;
	}
	
	public void setSupplierId(java.lang.Integer value) {
		this.supplierId = value;
	}
	
		private java.lang.String operatorIdModelTag;
		private java.lang.String supplierIdModelTag;
		public java.lang.String getOperatorIdModelTag(){
			return this.operatorIdModelTag;
		}
		
		public  void setOperatorIdModelTag(String dispensarystaffTag){
			this.operatorIdModelTag=dispensarystaffTag;
		}
		public java.lang.String getSupplierIdModelTag(){
			return this.supplierIdModelTag;
		}
		
		public  void setSupplierIdModelTag(String supplierTag){
			this.supplierIdModelTag=supplierTag;
		}
	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

