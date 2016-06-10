/*
 */

package com.dispensary.project.model;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import javax.validation.constraints.*;
import org.hibernate.validator.constraints.*;
import org.springframework.expression.spel.ast.Assign;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;



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


@Entity
@Table(name = "memo")
public class Memo extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "便签";
	public static final String ALIAS_ID = "id";
	
	public static final String ALIAS_USER_ID = "用户名臣";
	
	public static final String ALIAS_TITLE = "标题";
	
	public static final String ALIAS_CONTENT = "内容";
	
	public static final String ALIAS_ATTACHMENT = "附件";
	
	public static final String ALIAS_DATE = "创建日期";
	
	public static final String ALIAS_STATUS = "status";
	
	
	//date formats
	public static final String FORMAT_DATE = DATE_FORMAT;
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * id       db_column: ID 
     */ 	
	
	private java.lang.Integer id;
    /**
     * userId       db_column: UserID 
     */ 	
	@NotNull 
	private java.lang.Integer userId;
    /**
     * title       db_column: Title 
     */ 	
	@NotBlank @Length(max=65535)
	private java.lang.String title;
    /**
     * content       db_column: Content 
     */ 	
	@NotBlank @Length(max=65535)
	private java.lang.String content;
    /**
     * attachment       db_column: Attachment 
     */ 	
	@Length(max=255)
	private java.lang.String attachment;
    /**
     * date       db_column: Date 
     */ 	
	
	private java.util.Date date;
    /**
     * status       db_column: Status 
     */ 	
	@NotNull 
	private java.lang.Integer status;
	//columns END


	public Memo(){
	}

	public Memo(
		java.lang.Integer id
	){
		this.id = id;
	}

	

	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id @GeneratedValue(generator="custom-id")
	@GenericGenerator(name="custom-id", strategy = "native")
	@Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getId() {
		return this.id;
	}
	
	@Column(name = "UserID", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getUserId() {
		return this.userId;
	}
	
	public void setUserId(java.lang.Integer value) {
		this.userId = value;
	}
	
	@Column(name = "Title", unique = false, nullable = false, insertable = true, updatable = true, length = 65535)
	public java.lang.String getTitle() {
		return this.title;
	}
	
	public void setTitle(java.lang.String value) {
		this.title = value;
	}
	
	@Column(name = "Content", unique = false, nullable = false, insertable = true, updatable = true, length = 65535)
	public java.lang.String getContent() {
		return this.content;
	}
	
	public void setContent(java.lang.String value) {
		this.content = value;
	}
	
	@Column(name = "Attachment", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getAttachment() {
		return this.attachment;
	}
	
	public void setAttachment(java.lang.String value) {
		this.attachment = value;
	}
	
	@Transient
	public String getDateString() {
		return DateConvertUtils.format(getDate(), FORMAT_DATE);
	}
	public void setDateString(String value) {
		setDate(DateConvertUtils.parse(value, FORMAT_DATE,java.util.Date.class));
	}
	
	@Column(name = "Date", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	public java.util.Date getDate() {
		return this.date;
	}
	
	public void setDate(java.util.Date value) {
		this.date = value;
	}
	
	@Column(name = "Status", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getStatus() {
		return this.status;
	}
	
	public void setStatus(java.lang.Integer value) {
		this.status = value;
	}
	
	//获取表标识列的值,即作为关联类时显示的值
	@Transient
	public String getModelTagValue() {
				return this.id+"";
			
	}
	
	private Userinfo userIdModel;
	public void setUserIdModel(Userinfo userinfo){
		this.userIdModel = userinfo;
	}
	
	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "UserID",nullable = false, insertable = false, updatable = false) 
	})
	public Userinfo getUserIdModel() {
		return userIdModel;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("UserId",getUserId())
			.append("Title",getTitle())
			.append("Content",getContent())
			.append("Attachment",getAttachment())
			.append("Date",getDate())
			.append("Status",getStatus())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Memo == false) return false;
		if(this == obj) return true;
		Memo other = (Memo)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

