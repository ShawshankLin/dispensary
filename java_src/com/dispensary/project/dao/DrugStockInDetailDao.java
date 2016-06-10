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
public class DrugStockInDetailDao extends BaseHibernateDao<DrugStockInDetail,java.lang.Integer>{

	public Class getEntityClass() {
		return DrugStockInDetail.class;
	}
	
	public Page findPage(DrugStockInDetailQuery query) {
        //XsqlBuilder syntax,please see http://code.google.com/p/rapid-xsqlbuilder
        // [column]为字符串拼接, {column}为使用占位符. [column]为使用字符串拼接,如username='[username]',偷懒时可以使用字符串拼接 
        // [column] 为PageRequest的属性
		String sql = "select t from DrugStockInDetail t where 1=1 "
				+ "/~ and t.id={id} ~/"
			  	+ "/~ and t.serialNumber like '%[serialNumber]%' ~/"
			  	+ "/~ and t.drugId = {drugId} ~/"
			  	+ "/~ and t.inPlaceId = {inPlaceId} ~/"
			  	+ "/~ and t.inPrice = {inPrice} ~/"
			  	+ "/~ and t.inStocks = {inStocks} ~/"
				+ "/~ and t.productionDate >= {productionDateBegin} ~/"
				+ "/~ and t.productionDate <= {productionDateEnd} ~/"
					+ "/~ and t.inPlaceIdModel.id like '%[inPlaceIdModelTag]%'  ~/"
					+ "/~ and t.serialNumberModel.id like '%[serialNumberModelTag]%'  ~/"
					+ "/~ and t.drugIdModel.drugName like '%[drugIdModelTag]%'  ~/"
				+ "/~ order by [sortColumns] ~/";
        
		return pageQuery(sql,query);
	}
	public List<DrugStockInDetail> findAllByQuery(DrugStockInDetailQuery query){
		String sql = "select t from DrugStockInDetail t where 1=1 "
					+ "/~ and t.id={id} ~/"
				  	+ "/~ and t.serialNumber like '%[serialNumber]%' ~/"
				  	+ "/~ and t.drugId = {drugId} ~/"
				  	+ "/~ and t.inPlaceId = {inPlaceId} ~/"
				  	+ "/~ and t.inPrice = {inPrice} ~/"
				  	+ "/~ and t.inStocks = {inStocks} ~/"
					+ "/~ and t.productionDate >= {productionDateBegin} ~/"
					+ "/~ and t.productionDate <= {productionDateEnd} ~/"
					+ "/~ and t.inPlaceIdModel.id like '%[inPlaceIdModelTag]%'  ~/"
					+ "/~ and t.serialNumberModel.id like '%[serialNumberModelTag]%'  ~/"
					+ "/~ and t.drugIdModel.drugName like '%[drugIdModelTag]%'  ~/"
					+ "/~ order by [sortColumns] ~/";
		return findAllBy(sql,query);
	}
	public DrugStockInDetail getBySerialNumber(java.lang.String v) {
		return (DrugStockInDetail) findByProperty("serialNumber",v);
	}	
}
