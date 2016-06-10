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
public class LogDao extends BaseHibernateDao<Log,java.lang.Integer>{

	public Class getEntityClass() {
		return Log.class;
	}
	
	public Page findPage(LogQuery query) {
        //XsqlBuilder syntax,please see http://code.google.com/p/rapid-xsqlbuilder
        // [column]为字符串拼接, {column}为使用占位符. [column]为使用字符串拼接,如username='[username]',偷懒时可以使用字符串拼接 
        // [column] 为PageRequest的属性
		String sql = "select t from Log t where 1=1 "
			  	+ "/~ and t.userId = {userId} ~/"
			  	+ "/~ and t.tag like '%[tag]%' ~/"
			  	+ "/~ and t.summary like '%[summary]%' ~/"
			  	+ "/~ and t.ip like '%[ip]%' ~/"
				+ "/~ and t.date >= {dateBegin} ~/"
				+ "/~ and t.date <= {dateEnd} ~/"
					+ "/~ and t.userIdModel.userName like '%[userIdModelTag]%'  ~/"
				+ "/~ order by [sortColumns] ~/";
        
		return pageQuery(sql,query);
	}
	public List<Log> findAllByQuery(LogQuery query){
		String sql = "select t from Log t where 1=1 "
				  	+ "/~ and t.userId = {userId} ~/"
				  	+ "/~ and t.tag like '%[tag]%' ~/"
				  	+ "/~ and t.summary like '%[summary]%' ~/"
				  	+ "/~ and t.ip like '%[ip]%' ~/"
					+ "/~ and t.date >= {dateBegin} ~/"
					+ "/~ and t.date <= {dateEnd} ~/"
					+ "/~ and t.userIdModel.userName like '%[userIdModelTag]%'  ~/"
					+ "/~ order by [sortColumns] ~/";
		return findAllBy(sql,query);
	}

}
