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


public class FeetypeQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** 费用ID */
	private java.lang.Integer feeTypeId;
	/** 费用名称 */
	private java.lang.String feeTypeName;

	public java.lang.Integer getFeeTypeId() {
		return this.feeTypeId;
	}
	
	public void setFeeTypeId(java.lang.Integer value) {
		this.feeTypeId = value;
	}
	
	public java.lang.String getFeeTypeName() {
		return this.feeTypeName;
	}
	
	public void setFeeTypeName(java.lang.String value) {
		this.feeTypeName = value;
	}
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

