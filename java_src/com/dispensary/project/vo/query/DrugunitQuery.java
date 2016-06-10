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


public class DrugunitQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** 药品单位ID  */
	private java.lang.Integer drugUnitId;
	/** 药品单位名称 */
	private java.lang.String drugUnitName;

	public java.lang.Integer getDrugUnitId() {
		return this.drugUnitId;
	}
	
	public void setDrugUnitId(java.lang.Integer value) {
		this.drugUnitId = value;
	}
	
	public java.lang.String getDrugUnitName() {
		return this.drugUnitName;
	}
	
	public void setDrugUnitName(java.lang.String value) {
		this.drugUnitName = value;
	}
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

