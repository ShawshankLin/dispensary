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
@Table(name = "menu")
public class Menu extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "Menu";
	public static final String ALIAS_MENU_ID = "menuId";
	
	public static final String ALIAS_MENU_NAME = "menuName";
	
	public static final String ALIAS_MENU_URL = "menuUrl";
	
	public static final String ALIAS_PARENT = "parent";
	
	public static final String ALIAS_MENU_NO = "menuNo";
	
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * menuId       db_column: MenuID 
     */ 	
	
	private java.lang.Integer menuId;
    /**
     * menuName       db_column: MenuName 
     */ 	
	@Length(max=255)
	private java.lang.String menuName;
    /**
     * menuUrl       db_column: MenuUrl 
     */ 	
	@Length(max=255)
	private java.lang.String menuUrl;
    /**
     * parent       db_column: Parent 
     */ 	
	
	private java.lang.Integer parent;
    /**
     * menuNo       db_column: MenuNo 
     */ 	
	
	private java.lang.Integer menuNo;
	//columns END


	public Menu(){
	}

	public Menu(
		java.lang.Integer menuId
	){
		this.menuId = menuId;
	}

	

	public void setMenuId(java.lang.Integer value) {
		this.menuId = value;
	}
	
	@Id @GeneratedValue(generator="custom-id")
	@GenericGenerator(name="custom-id", strategy = "native")
	@Column(name = "MenuID", unique = true, nullable = false, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getMenuId() {
		return this.menuId;
	}
	
	@Column(name = "MenuName", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getMenuName() {
		return this.menuName;
	}
	
	public void setMenuName(java.lang.String value) {
		this.menuName = value;
	}
	
	@Column(name = "MenuUrl", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getMenuUrl() {
		return this.menuUrl;
	}
	
	public void setMenuUrl(java.lang.String value) {
		this.menuUrl = value;
	}
	
	@Column(name = "Parent", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getParent() {
		return this.parent;
	}
	
	public void setParent(java.lang.Integer value) {
		this.parent = value;
	}
	
	@Column(name = "MenuNo", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getMenuNo() {
		return this.menuNo;
	}
	
	public void setMenuNo(java.lang.Integer value) {
		this.menuNo = value;
	}
	
	//获取表标识列的值,即作为关联类时显示的值
	@Transient
	public String getModelTagValue() {
				return this.menuId+"";
			
	}
	
	private Set menus = new HashSet(0);
	public void setMenus(Set<Menu> menu){
		this.menus = menu;
	}
	
	@OneToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "parentModel")
	public Set<Menu> getMenus() {
		return menus;
	}
	private Set menuRoles = new HashSet(0);
	public void setMenuRoles(Set<MenuRole> menuRole){
		this.menuRoles = menuRole;
	}
	
	@OneToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "menuIdModel")
	public Set<MenuRole> getMenuRoles() {
		return menuRoles;
	}
	private Menu parentModel;
	public void setParentModel(Menu menu){
		this.parentModel = menu;
	}
	
	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "Parent",nullable = false, insertable = false, updatable = false) 
	})
	public Menu getParentModel() {
		return parentModel;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("MenuId",getMenuId())
			.append("MenuName",getMenuName())
			.append("MenuUrl",getMenuUrl())
			.append("Parent",getParent())
			.append("MenuNo",getMenuNo())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getMenuId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Menu == false) return false;
		if(this == obj) return true;
		Menu other = (Menu)obj;
		return new EqualsBuilder()
			.append(getMenuId(),other.getMenuId())
			.isEquals();
	}
}

