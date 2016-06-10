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

import static cn.org.rapid_framework.util.ObjectUtils.*;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class NoticeDao extends BaseHibernateDao<Notice,java.lang.Integer>{

	public Class getEntityClass() {
		return Notice.class;
	}
	
	public Page findPage(NoticeQuery query) {
        //XsqlBuilder syntax,please see http://code.google.com/p/rapid-xsqlbuilder
        // [column]为字符串拼接, {column}为使用占位符. [column]为使用字符串拼接,如username='[username]',偷懒时可以使用字符串拼接 
        // [column] 为PageRequest的属性
		String sql = "select t from Notice t where 1=1 "
				+"/~ and t.id={id} ~/"
			  	+ "/~ and t.userId = {userId} ~/"
			  	+ "/~ and t.title like '%[title]%' ~/"
			  	+ "/~ and t.content like '%[content]%' ~/"
			  	+ "/~ and t.attachment like '%[attachment]%' ~/"
			  	+ "/~ and TO_DAYS(t.date) =TO_DAYS({dateString}) ~/"
				+ "/~ and t.date >= {dateBegin} ~/"
				+ "/~ and t.date <= {dateEnd} ~/"
				+ "/~ and t.fileName like '%[fileName]%' ~/"
			  	+ "/~ and t.status = {status} ~/"
					+ "/~ and t.userIdModel.userName like '%[userIdModelTag]%'  ~/"
				+ "/~ order by [sortColumns] ~/";
        
		return pageQuery(sql,query);
	}
	public List<Notice> findAllByQuery(NoticeQuery query){
		String sql = "select t from Notice t where 1=1 "
					+"/~ and t.id={id} ~/"
				  	+ "/~ and t.userId = {userId} ~/"
				  	+ "/~ and t.title like '%[title]%' ~/"
				  	+ "/~ and t.content like '%[content]%' ~/"
				  	+ "/~ and t.attachment like '%[attachment]%' ~/"
				  	+ "/~ and TO_DAYS(t.date) =TO_DAYS({dateString}) ~/"
					+ "/~ and t.date >= {dateBegin} ~/"
					+ "/~ and t.date <= {dateEnd} ~/"
				  	+ "/~ and t.status = {status} ~/"
				  	+ "/~ and t.fileName like '%[fileName]%' ~/"
					+ "/~ and t.userIdModel.userName like '%[userIdModelTag]%'  ~/"
					+ "/~ order by [sortColumns] ~/";
		return findAllBy(sql,query);
	}
	public List getNewNotice(){
		String sql="SELECT * from `notice` where `Date`=(select max(Date) from notice) and `Status`=1;";
		return getSession().createSQLQuery(sql).list();
	}
}
