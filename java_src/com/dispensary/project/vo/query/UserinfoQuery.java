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


public class UserinfoQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** 登录ID  */
	private java.lang.Integer userId;
	/** 员工ID */
	private java.lang.Integer meStId;
	/** 用户密码 */
	private java.lang.String password;
	/** userTypeId */
	private java.lang.Integer userTypeId;
	/** 登录名 */
	private java.lang.String userName;
	/** 是否有效账户 */
	private java.lang.Integer ifValidity;

	public java.lang.Integer getUserId() {
		return this.userId;
	}
	
	public void setUserId(java.lang.Integer value) {
		this.userId = value;
	}
	
	public java.lang.Integer getMeStId() {
		return this.meStId;
	}
	
	public void setMeStId(java.lang.Integer value) {
		this.meStId = value;
	}
	
	public java.lang.String getPassword() {
		return this.password;
	}
	
	public void setPassword(java.lang.String value) {
		this.password = value;
	}
	
	public java.lang.Integer getUserTypeId() {
		return this.userTypeId;
	}
	
	public void setUserTypeId(java.lang.Integer value) {
		this.userTypeId = value;
	}
	
	public java.lang.String getUserName() {
		return this.userName;
	}
	
	public void setUserName(java.lang.String value) {
		this.userName = value;
	}
	
	public java.lang.Integer getIfValidity() {
		return this.ifValidity;
	}
	
	public void setIfValidity(java.lang.Integer value) {
		this.ifValidity = value;
	}
	
		private java.lang.String userTypeIdModelTag;
		private java.lang.String meStIdModelTag;
		public java.lang.String getUserTypeIdModelTag(){
			return this.userTypeIdModelTag;
		}
		
		public  void setUserTypeIdModelTag(String usertypeTag){
			this.userTypeIdModelTag=usertypeTag;
		}
		public java.lang.String getMeStIdModelTag(){
			return this.meStIdModelTag;
		}
		
		public  void setMeStIdModelTag(String dispensarystaffTag){
			this.meStIdModelTag=dispensarystaffTag;
		}
	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

