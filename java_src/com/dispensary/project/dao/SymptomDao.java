/*
 */

package com.dispensary.project.dao;

import java.sql.SQLException;
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

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

@Repository
public class SymptomDao extends BaseHibernateDao<Symptom, java.lang.Integer> {

	public Class getEntityClass() {
		return Symptom.class;
	}

	public Page findPage(SymptomQuery query) {
		// XsqlBuilder syntax,please see
		// http://code.google.com/p/rapid-xsqlbuilder
		// [column]为字符串拼接, {column}为使用占位符.
		// [column]为使用字符串拼接,如username='[username]',偷懒时可以使用字符串拼接
		// [column] 为PageRequest的属性
		String sql = "select t from Symptom t where 1=1 "
				+"/~ and t.symptomId={symptomId}~/"
				+ "/~ and t.symptomName like '%[symptomName]%' ~/"
				+ "/~ order by [sortColumns] ~/";
		return pageQuery(sql, query);
	}

	public List<Symptom> findAllByQuery(SymptomQuery query) {
		String sql = "select t from Symptom t where 1=1 "
				+"/~ and t.symptomId={symptomId}~/"
				+ "/~ and t.symptomName like '%[symptomName]%' ~/"
				+ "/~ order by [sortColumns] ~/";
		return findAllBy(sql, query);
	}
}
