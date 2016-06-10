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
public class DrugStockInfoDao extends BaseHibernateDao<DrugStockInfo,java.lang.Integer>{

	public Class getEntityClass() {
		return DrugStockInfo.class;
	}
	
	public Page findPage(DrugStockInfoQuery query) {
        //XsqlBuilder syntax,please see http://code.google.com/p/rapid-xsqlbuilder
        // [column]为字符串拼接, {column}为使用占位符. [column]为使用字符串拼接,如username='[username]',偷懒时可以使用字符串拼接 
        // [column] 为PageRequest的属性
		String sql = "select t from DrugStockInfo t where 1=1 "
			  	+ "/~ and t.drugId = {drugId} ~/"
			  	+ "/~ and t.drugPlaceId = {drugPlaceId} ~/"
				+ "/~ and t.productionDate >= {productionDateBegin} ~/"
				+ "/~ and t.productionDate <= {productionDateEnd} ~/"
			  	+ "/~ and t.curAmount = {curAmount} ~/"
					+ "/~ and t.drugIdModel.drugName like '%[drugIdModelTag]%'  ~/"
					+ "/~ and t.drugPlaceIdModel.id like '%[drugPlaceIdModelTag]%'  ~/"
				+ "/~ order by [sortColumns] ~/";
        
		return pageQuery(sql,query);
	}
	public List<DrugStockInfo> findAllByQuery(DrugStockInfoQuery query){
		String sql = "select t from DrugStockInfo t where 1=1 "
				  	+ "/~ and t.drugId = {drugId} ~/"
				  	+ "/~ and t.drugPlaceId = {drugPlaceId} ~/"
					+ "/~ and t.productionDate >= {productionDateBegin} ~/"
					+ "/~ and t.productionDate <= {productionDateEnd} ~/"
				  	+ "/~ and t.curAmount = {curAmount} ~/"
					+ "/~ and t.drugIdModel.drugName like '%[drugIdModelTag]%'  ~/"
					+ "/~ and t.drugPlaceIdModel.id like '%[drugPlaceIdModelTag]%'  ~/"
					+ "/~ order by [sortColumns] ~/";
		return findAllBy(sql,query);
	}
	public List findExpiredDrugs(int pageNumber,int pageSize) {
		String sql="select DISTINCT t.DrugID as 药品ID,t.DrugName as 药品名称,concat(t.PeriodOfValidity) as 保质期,t.ProductionDate 生产日期,CURRENT_DATE() 当前日期,date_add(t.ProductionDate, interval PeriodOfValidity year) as 到期日期,datediff(date_add(t.ProductionDate, interval PeriodOfValidity year),CURRENT_DATE()) as 剩余天数 from drug_basic_info t,drug_stock_info s where datediff(date_add(t.ProductionDate, interval PeriodOfValidity year),CURRENT_DATE())<=890 and t.DrugID=s.DrugID;";
		List list=getSession().createSQLQuery(sql).list();
		return list;
	}
	public int getStockDrugNum(){
		String sql="select COUNT(*) from drug_stock_info;";
		return Integer.parseInt(getSession().createSQLQuery(sql).uniqueResult().toString());
	}
}
