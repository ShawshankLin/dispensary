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


public class MenuQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** menuId */
	private java.lang.Integer menuId;
	/** menuName */
	private java.lang.String menuName;
	/** menuUrl */
	private java.lang.String menuUrl;
	/** parent */
	private java.lang.Integer parent;
	/** menuNo */
	private java.lang.Integer menuNo;

	public java.lang.Integer getMenuId() {
		return this.menuId;
	}
	
	public void setMenuId(java.lang.Integer value) {
		this.menuId = value;
	}
	
	public java.lang.String getMenuName() {
		return this.menuName;
	}
	
	public void setMenuName(java.lang.String value) {
		this.menuName = value;
	}
	
	public java.lang.String getMenuUrl() {
		return this.menuUrl;
	}
	
	public void setMenuUrl(java.lang.String value) {
		this.menuUrl = value;
	}
	
	public java.lang.Integer getParent() {
		return this.parent;
	}
	
	public void setParent(java.lang.Integer value) {
		this.parent = value;
	}
	
	public java.lang.Integer getMenuNo() {
		return this.menuNo;
	}
	
	public void setMenuNo(java.lang.Integer value) {
		this.menuNo = value;
	}
	
		private java.lang.String parentModelTag;
		public java.lang.String getParentModelTag(){
			return this.parentModelTag;
		}
		
		public  void setParentModelTag(String menuTag){
			this.parentModelTag=menuTag;
		}
	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

