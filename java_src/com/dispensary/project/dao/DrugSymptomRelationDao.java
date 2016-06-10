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
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class DrugSymptomRelationDao extends BaseHibernateDao<DrugSymptomRelation,java.lang.Integer>{

	public Class getEntityClass() {
		return DrugSymptomRelation.class;
	}
	
	public Page findPage(DrugSymptomRelationQuery query) {
        //XsqlBuilder syntax,please see http://code.google.com/p/rapid-xsqlbuilder
        // [column]为字符串拼接, {column}为使用占位符. [column]为使用字符串拼接,如username='[username]',偷懒时可以使用字符串拼接 
        // [column] 为PageRequest的属性
		String sql = "select t from DrugSymptomRelation t where 1=1 "
			  	+ "/~ and t.drugId = {drugId} ~/"
			  	+ "/~ and t.symptomId = {symptomId} ~/"
					+ "/~ and t.symptomIdModel.symptomName like '%[symptomIdModelTag]%'  ~/"
					+ "/~ and t.drugIdModel.drugName like '%[drugIdModelTag]%'  ~/"
				+ "/~ order by [sortColumns] ~/";
        
		return pageQuery(sql,query);
	}
	public List<DrugSymptomRelation> findAllByQuery(DrugSymptomRelationQuery query){
		String sql = "select t from DrugSymptomRelation t where 1=1 "
				  	+ "/~ and t.drugId = {drugId} ~/"
				  	+ "/~ and t.symptomId = {symptomId} ~/"
					+ "/~ and t.symptomIdModel.symptomName like '%[symptomIdModelTag]%'  ~/"
					+ "/~ and t.drugIdModel.drugName like '%[drugIdModelTag]%'  ~/"
					+ "/~ order by [sortColumns] ~/";
		return findAllBy(sql,query);
	}
	public List<Integer> findAllExcept(int symptomId){
		String sql="select distinct drugId from DrugSymptomRelation where symptomId!=:symptomId";
		return getSession().createQuery(sql).setParameter("symptomId", symptomId).list();
	}

}
