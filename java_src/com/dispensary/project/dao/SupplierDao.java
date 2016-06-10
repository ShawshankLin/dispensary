/*
 */

package com.dispensary.project.dao;

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


import static cn.org.rapid_framework.util.ObjectUtils.*;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class SupplierDao extends BaseHibernateDao<Supplier,java.lang.Integer>{

	public Class getEntityClass() {
		return Supplier.class;
	}
	
	public Page findPage(SupplierQuery query) {
        //XsqlBuilder syntax,please see http://code.google.com/p/rapid-xsqlbuilder
        // [column]为字符串拼接, {column}为使用占位符. [column]为使用字符串拼接,如username='[username]',偷懒时可以使用字符串拼接 
        // [column] 为PageRequest的属性
		String sql = "select t from Supplier t where 1=1 "
				+ "/~ and t.supplierId={supplierId} ~/"
			  	+ "/~ and t.supplierName like '%[supplierName]%' ~/"
			  	+ "/~ and t.contacts like '%[contacts]%' ~/"
			  	+ "/~ and t.pingyin like '%[pingyin]%' ~/"
			  	+ "/~ and t.address like '%[address]%' ~/"
			  	+ "/~ and t.userTel like '%[userTel]%' ~/"
			  	+ "/~ and t.userMobile like '%[userMobile]%' ~/"
			  	+ "/~ and t.userEmail like '%[userEmail]%' ~/"
			  	+ "/~ and t.note like '%[note]%' ~/"
				+ "/~ order by [sortColumns] ~/";
        
		return pageQuery(sql,query);
	}

	public List<Supplier> findAllByQuery(SupplierQuery query){
		String sql = "select t from Supplier t where 1=1 "
					+ "/~ and t.supplierId={supplierId} ~/"
				  	+ "/~ and t.supplierName like '%[supplierName]%' ~/"
				  	+ "/~ and t.contacts like '%[contacts]%' ~/"
				  	+ "/~ and t.pingyin like '%[pingyin]%' ~/"
				  	+ "/~ and t.address like '%[address]%' ~/"
				  	+ "/~ and t.userTel like '%[userTel]%' ~/"
				  	+ "/~ and t.userMobile like '%[userMobile]%' ~/"
				  	+ "/~ and t.userEmail like '%[userEmail]%' ~/"
				  	+ "/~ and t.note like '%[note]%' ~/"
					+ "/~ order by [sortColumns] ~/";
		return findAllBy(sql,query);
	}

}
