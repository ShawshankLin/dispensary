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
public class UsertypeDao extends BaseHibernateDao<Usertype,java.lang.Integer>{

	public Class getEntityClass() {
		return Usertype.class;
	}
	
	public Page findPage(UsertypeQuery query) {
        //XsqlBuilder syntax,please see http://code.google.com/p/rapid-xsqlbuilder
        // [column]为字符串拼接, {column}为使用占位符. [column]为使用字符串拼接,如username='[username]',偷懒时可以使用字符串拼接 
        // [column] 为PageRequest的属性
		String sql = "select t from Usertype t where 1=1 "
				+"/~ and t.userTypeId={userTypeId} ~/"
			  	+ "/~ and t.userTypeName like '%[userTypeName]%' ~/"
				+ "/~ order by [sortColumns] ~/";
        
		return pageQuery(sql,query);
	}
	public List<Usertype> findAllByQuery(UsertypeQuery query){
		String sql = "select t from Usertype t where 1=1 "
					+"/~ and t.userTypeId={userTypeId} ~/"
				  	+ "/~ and t.userTypeName like '%[userTypeName]%' ~/"
					+ "/~ order by [sortColumns] ~/";
		return findAllBy(sql,query);
	}

}
