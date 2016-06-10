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


public class MemoQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** id */
	private java.lang.Integer id;
	/** userId */
	private java.lang.Integer userId;
	/** title */
	private java.lang.String title;
	/** content */
	private java.lang.String content;
	/** attachment */
	private java.lang.String attachment;
	/** date */
	private java.util.Date dateBegin;
	private java.util.Date dateEnd;
	private java.util.Date dateString;
	/** status */
	private java.lang.Integer status;

	public java.lang.Integer getId() {
		return this.id;
	}
	
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	public java.lang.Integer getUserId() {
		return this.userId;
	}
	
	public void setUserId(java.lang.Integer value) {
		this.userId = value;
	}
	
	public java.lang.String getTitle() {
		return this.title;
	}
	
	public void setTitle(java.lang.String value) {
		this.title = value;
	}
	
	public java.lang.String getContent() {
		return this.content;
	}
	
	public void setContent(java.lang.String value) {
		this.content = value;
	}
	
	public java.lang.String getAttachment() {
		return this.attachment;
	}
	
	public void setAttachment(java.lang.String value) {
		this.attachment = value;
	}
	


	public java.util.Date getDateString() {
		return dateString;
	}

	public void setDateString(java.util.Date dateString) {
		this.dateString = dateString;
	}

	public java.util.Date getDateBegin() {
		return this.dateBegin;
	}
	
	public void setDateBegin(java.util.Date value) {
		this.dateBegin = value;
	}	
	
	public java.util.Date getDateEnd() {
		return this.dateEnd;
	}
	
	public void setDateEnd(java.util.Date value) {
		this.dateEnd = value;
	}
	
	public java.lang.Integer getStatus() {
		return this.status;
	}
	
	public void setStatus(java.lang.Integer value) {
		this.status = value;
	}
	
		private java.lang.String userIdModelTag;
		public java.lang.String getUserIdModelTag(){
			return this.userIdModelTag;
		}
		
		public  void setUserIdModelTag(String userinfoTag){
			this.userIdModelTag=userinfoTag;
		}
	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

