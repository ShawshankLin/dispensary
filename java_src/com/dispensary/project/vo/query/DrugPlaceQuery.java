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


public class DrugPlaceQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** 位置编码 */
	private java.lang.Integer drugPlaceId;
	/** 位置描述 */
	private java.lang.String drugPlace;
	/** 位置状态 */
	private java.lang.Integer status;
	/** 位置类型 */
	private java.lang.Integer placeType;
	/** 库房类型 */
	private java.lang.Integer isStoreroom;

	public java.lang.Integer getDrugPlaceId() {
		return this.drugPlaceId;
	}
	
	public void setDrugPlaceId(java.lang.Integer value) {
		this.drugPlaceId = value;
	}
	
	public java.lang.String getDrugPlace() {
		return this.drugPlace;
	}
	
	public void setDrugPlace(java.lang.String value) {
		this.drugPlace = value;
	}
	
	public java.lang.Integer getStatus() {
		return this.status;
	}
	
	public void setStatus(java.lang.Integer value) {
		this.status = value;
	}
	
	public java.lang.Integer getPlaceType() {
		return this.placeType;
	}
	
	public void setPlaceType(java.lang.Integer value) {
		this.placeType = value;
	}
	
	public java.lang.Integer getIsStoreroom() {
		return this.isStoreroom;
	}
	
	public void setIsStoreroom(java.lang.Integer value) {
		this.isStoreroom = value;
	}
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

