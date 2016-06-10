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


public class DrugStockOutQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** 序列号 */
	private java.lang.Integer id;
	/** 流水号 */
	private java.lang.String serialNumber;
	/** 出库编号 */
	private java.lang.String stockOutId;
	/** 出库时间 */
	private java.util.Date outDateBegin;
	private java.util.Date outDateEnd;
	private java.util.Date outDate;
	/** 总数量 */
	private java.lang.Integer total;
	/** 总金额 */
	private java.lang.Long sum;
	/** 出库经手人编号 */
	private java.lang.Integer operatorId;

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
	
	public java.lang.String getStockOutId() {
		return this.stockOutId;
	}
	
	public void setStockOutId(java.lang.String value) {
		this.stockOutId = value;
	}
	
	public java.util.Date getOutDateBegin() {
		return this.outDateBegin;
	}
	
	public void setOutDateBegin(java.util.Date value) {
		this.outDateBegin = value;
	}	
	
	public java.util.Date getOutDateEnd() {
		return this.outDateEnd;
	}
	
	public void setOutDateEnd(java.util.Date value) {
		this.outDateEnd = value;
	}
	
	public java.lang.Integer getTotal() {
		return this.total;
	}
	
	public void setTotal(java.lang.Integer value) {
		this.total = value;
	}
	
	public java.util.Date getOutDate() {
		return outDate;
	}

	public void setOutDate(java.util.Date outDate) {
		this.outDate = outDate;
	}

	public java.lang.Long getSum() {
		return this.sum;
	}
	
	public void setSum(java.lang.Long value) {
		this.sum = value;
	}
	
	public java.lang.Integer getOperatorId() {
		return this.operatorId;
	}
	
	public void setOperatorId(java.lang.Integer value) {
		this.operatorId = value;
	}
	
		private java.lang.String operatorIdModelTag;
		public java.lang.String getOperatorIdModelTag(){
			return this.operatorIdModelTag;
		}
		
		public  void setOperatorIdModelTag(String dispensarystaffTag){
			this.operatorIdModelTag=dispensarystaffTag;
		}
	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

