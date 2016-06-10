/*
 */

package com.dispensary.project.dao;

import java.text.DateFormat;
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

import javax.persistence.criteria.CriteriaBuilder.In;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class PatiCaseHistoryDao extends BaseHibernateDao<PatiCaseHistory,java.lang.Integer>{

	public Class getEntityClass() {
		return PatiCaseHistory.class;
	}
	
	public Page findPage(PatiCaseHistoryQuery query) {
        //XsqlBuilder syntax,please see http://code.google.com/p/rapid-xsqlbuilder
        // [column]为字符串拼接, {column}为使用占位符. [column]为使用字符串拼接,如username='[username]',偷懒时可以使用字符串拼接 
        // [column] 为PageRequest的属性
		String sql = "select t from PatiCaseHistory t where 1=1 "
				+ "/~ and t.caseId like '%[caseId]%' ~/"
			  	+ "/~ and t.stuNum like '%[stuNum]%' ~/"
			  	+ "/~ and TO_DAYS(t.visitDate) =TO_DAYS({visitDateString}) ~/"
				+ "/~ and t.visitDate >= {visitDateBegin} ~/"
				+ "/~ and t.visitDate <= {visitDateEnd} ~/"
			  	+ "/~ and t.dispensaryRecord like '%[dispensaryRecord]%' ~/"
			  	+ "/~ and t.allergy like '%[allergy]%' ~/"
			  	+ "/~ and t.primaryCare like '%[primaryCare]%' ~/"
			  	+ "/~ and t.reExamination like '%[reExamination]%' ~/"
			  	+ "/~ and t.describes like '%[describes]%' ~/"
			  	+ "/~ and t.examineStatus like '%[examineStatus]%' ~/"
			  	+ "/~ and t.firstImpress like '%[firstImpress]%' ~/"
			  	+ "/~ and t.precriptId like '%[precriptId]%' ~/"
			  	+ "/~ and t.meStId = {meStId} ~/"
					+ "/~ and t.precriptIdModel.id like '%[precriptIdModelTag]%'  ~/"
					+ "/~ and t.meStIdModel.meStName like '%[meStIdModelTag]%'  ~/"
				+ "/~ order by [sortColumns] ~/";
        
		return pageQuery(sql,query);
	}
	public List<PatiCaseHistory> findAllByQuery(PatiCaseHistoryQuery query){
		String sql = "select t from PatiCaseHistory t where 1=1 "
				  	+ "/~ and t.caseId like '%[caseId]%' ~/"
				  	+ "/~ and t.stuNum like '%[stuNum]%' ~/"
				  	+ "/~ and TO_DAYS(t.visitDate) =TO_DAYS({visitDateString}) ~/"
					+ "/~ and t.visitDate >= {visitDateBegin} ~/"
					+ "/~ and t.visitDate <= {visitDateEnd} ~/"
				  	+ "/~ and t.dispensaryRecord like '%[dispensaryRecord]%' ~/"
				  	+ "/~ and t.allergy like '%[allergy]%' ~/"
				  	+ "/~ and t.primaryCare like '%[primaryCare]%' ~/"
				  	+ "/~ and t.reExamination like '%[reExamination]%' ~/"
				  	+ "/~ and t.describes like '%[describes]%' ~/"
				  	+ "/~ and t.examineStatus like '%[examineStatus]%' ~/"
				  	+ "/~ and t.firstImpress like '%[firstImpress]%' ~/"
				  	+ "/~ and t.precriptId like '%[precriptId]%' ~/"
				  	+ "/~ and t.meStId = {meStId} ~/"
					+ "/~ and t.precriptIdModel.id like '%[precriptIdModelTag]%'  ~/"
					+ "/~ and t.meStIdModel.meStName like '%[meStIdModelTag]%'  ~/"
					+ "/~ order by [sortColumns] ~/";
		return findAllBy(sql,query);
	}
	
	public List<Integer> getMaxID(){
		String sql="select max(id) from PatiCaseHistory";
		return this.getHibernateTemplate().find(sql);
	}
	//得到柱形图数据
	public List getPieData(Date starttime,Date endtime){
		String sql=null;
		if(starttime == null || endtime == null) {
			sql = "SELECT DATE_FORMAT(VisitDate,'%y-%m'),COUNT(*) FROM pati_case_history group  by DATE_FORMAT(VisitDate,'%m');";
    	} else {
    		sql="SELECT DATE_FORMAT(VisitDate,'%y-%m'),count(*) FROM pati_case_history WHERE VisitDate BETWEEN \'" + DateFormat.getDateInstance().format(starttime) + "\' and \'" + DateFormat.getDateInstance().format(endtime) + "\' GROUP BY DATE_FORMAT(VisitDate,'%y-%m');";
    	}
		return getSession().createSQLQuery(sql).list();
		
	}
	//得到病历数据（用于jschart）
	public List<Integer> getMonthVisits(){
		String sql="select ifnull(total,0) from `month` m LEFT JOIN (select count(Case_ID) total,VisitDate from pati_case_history group by month(VisitDate))t on m.id=month(VisitDate) order by m.id";
		return getSession().createSQLQuery(sql).list();
	}
	//得到病历数据（用于piechart）
	public List getMonthVisitsPie(){
		String sql="select ifnull(total,0),remark from `month` m LEFT JOIN (select count(Case_ID) total,VisitDate from pati_case_history group by month(VisitDate))t on m.id=month(VisitDate) order by m.id";
		return getSession().createSQLQuery(sql).list();
	}
	//得到折线图数据
	public List getLineData(Date starttime,Date endtime){
		String sql=null;
		if(starttime == null || endtime == null) {
			sql = "SELECT year(VisitDate),month(VisitDate),day(VisitDate),COUNT(*) FROM pati_case_history group  by DATE_FORMAT(VisitDate,'%m');";
    	} else {
    		sql="SELECT year(VisitDate),month(VisitDate),day(VisitDate),count(*) FROM pati_case_history WHERE VisitDate BETWEEN \'" + DateFormat.getDateInstance().format(starttime) + "\' and \'" + DateFormat.getDateInstance().format(endtime) + "\' GROUP BY DATE_FORMAT(VisitDate,'%y-%m');";
    	}
		return getSession().createSQLQuery(sql).list();
		
	}
	//获取历史就诊病例数
	public int getHistVisitsNum(){
		String sql="select count(*) from pati_case_history;";
		return Integer.parseInt(getSession().createSQLQuery(sql).uniqueResult().toString());
		
	}
	//获取昨日就诊数
	public int getYdayVisitsNum(){
		String sql="select count(*) from pati_case_history where TO_DAYS(VisitDate)=TO_DAYS(NOW())-1;";
		return Integer.parseInt(getSession().createSQLQuery(sql).uniqueResult().toString());
	}
	//获取前5就诊的医生
	public List getTopVisits(){
		String sql="select d.MeStId,d.MeStName,count(*) totalNum,max(VisitDate) from pati_case_history p,dispensarystaff d where p.meStId=d.meStId group by p.meStId ORDER BY totalNum DESC LIMIT 5;";
		return getSession().createSQLQuery(sql).list();
	}
}
