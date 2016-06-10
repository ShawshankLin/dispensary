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

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDao extends BaseHibernateDao<Student,java.lang.String>{

	public Class getEntityClass() {
		return Student.class;
	}
	
	public Page findPage(StudentQuery query) {
        //XsqlBuilder syntax,please see http://code.google.com/p/rapid-xsqlbuilder
        // [column]为字符串拼接, {column}为使用占位符. [column]为使用字符串拼接,如username='[username]',偷懒时可以使用字符串拼接 
        // [column] 为PageRequest的属性
		String sql = "select t from Student t where 1=1 "
				+ "/~ and t.stuNum like '[stuNum]%' ~/"
			  	+ "/~ and t.stuName like '%[stuName]%' ~/"
			  	+ "/~ and t.sex = {sex} ~/"
				+ "/~ and t.birthDate >= {birthDateBegin} ~/"
				+ "/~ and t.birthDate <= {birthDateEnd} ~/"
			  	+ "/~ and t.idcard like '%[idcard]%' ~/"
			  	+ "/~ and t.tel like '%[tel]%' ~/"
			  	+ "/~ and t.department like '%[department]%' ~/"
			  	+ "/~ and t.major like '%[major]%' ~/"
			  	+ "/~ and t.stuClass like '%[stuClass]%' ~/"
			  	+ "/~ and t.address like '%[address]%' ~/"
				+ "/~ order by [sortColumns] ~/";
        
		return pageQuery(sql,query);
	}
	public List<Student> findAllByQuery(StudentQuery query){
		String sql = "select t from Student t where 1=1 "
					+"/~ and t.stuNum like '[stuNum]%' ~/"
				  	+ "/~ and t.stuName like '%[stuName]%' ~/"
				  	+ "/~ and t.sex = {sex} ~/"
					+ "/~ and t.birthDate >= {birthDateBegin} ~/"
					+ "/~ and t.birthDate <= {birthDateEnd} ~/"
				  	+ "/~ and t.idcard like '%[idcard]%' ~/"
				  	+ "/~ and t.tel like '%[tel]%' ~/"
				  	+ "/~ and t.department like '%[department]%' ~/"
				  	+ "/~ and t.major like '%[major]%' ~/"
				  	+ "/~ and t.stuClass like '%[stuClass]%' ~/"
				  	+ "/~ and t.address like '%[address]%' ~/"
					+ "/~ order by [sortColumns] ~/";
		return findAllBy(sql,query);
	}
	public List getStuJSON(String stuNum,String stuName){
		StringBuffer sql = new StringBuffer("select StuNum,StuName from student ");
		if(stuNum!=null&&stuName==null)
			sql.append("where StuNum like '%"+stuNum+"%'");
		if(stuNum!=null&&stuName!=null)
			sql.append("and StuName like '%"+stuName+"%'");
		if(stuNum==null&&stuName!=null)
			sql.append("where StuName '%"+stuName+"%'");
		return getSession().createSQLQuery(sql.toString()).list();
	}

}
