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
public class MemoDao extends BaseHibernateDao<Memo,java.lang.Integer>{

	public Class getEntityClass() {
		return Memo.class;
	}
	
	public Page findPage(MemoQuery query) {
        //XsqlBuilder syntax,please see http://code.google.com/p/rapid-xsqlbuilder
        // [column]为字符串拼接, {column}为使用占位符. [column]为使用字符串拼接,如username='[username]',偷懒时可以使用字符串拼接 
        // [column] 为PageRequest的属性
		String sql = "select t from Memo t where 1=1 "
				+"/~ and t.id={id} ~/"
			  	+ "/~ and t.userId = {userId} ~/"
			  	+ "/~ and t.title like '%[title]%' ~/"
			  	+ "/~ and t.content like '%[content]%' ~/"
			  	+ "/~ and t.attachment like '%[attachment]%' ~/"
			  	+ "/~ and TO_DAYS(t.date) = TO_DAYS({dateString}) ~/"
				+ "/~ and t.date >= {dateBegin} ~/"
				+ "/~ and t.date <= {dateEnd} ~/"
			  	+ "/~ and t.status = {status} ~/"
					+ "/~ and t.userIdModel.userName like '%[userIdModelTag]%'  ~/"
				+ "/~ order by [sortColumns] ~/";
        
		return pageQuery(sql,query);
	}
	public List<Memo> findAllByQuery(MemoQuery query){
		String sql = "select t from Memo t where 1=1 "
					+"/~ and t.id={id} ~/"
				  	+ "/~ and t.userId = {userId} ~/"
				  	+ "/~ and t.title like '%[title]%' ~/"
				  	+ "/~ and t.content like '%[content]%' ~/"
				  	+ "/~ and t.attachment like '%[attachment]%' ~/"
				  	+ "/~ and TO_DAYS(t.date) = TO_DAYS({dateString}) ~/"
					+ "/~ and t.date >= {dateBegin} ~/"
					+ "/~ and t.date <= {dateEnd} ~/"
				  	+ "/~ and t.status = {status} ~/"
					+ "/~ and t.userIdModel.userName like '%[userIdModelTag]%'  ~/"
					+ "/~ order by [sortColumns] ~/";
		return findAllBy(sql,query);
	}
	public List getNewMemo(String userId){
		String sql="SELECT * from memo where UserID=? and Date=(select max(Date) from memo);";
		return getSession().createSQLQuery(sql).setString(0, userId).list();
	}
}
