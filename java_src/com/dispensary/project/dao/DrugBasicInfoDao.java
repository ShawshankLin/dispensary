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
public class DrugBasicInfoDao extends BaseHibernateDao<DrugBasicInfo,java.lang.Integer>{

	public Class getEntityClass() {
		return DrugBasicInfo.class;
	}
	
	public Page findPage(DrugBasicInfoQuery query) {
        //XsqlBuilder syntax,please see http://code.google.com/p/rapid-xsqlbuilder
        // [column]为字符串拼接, {column}为使用占位符. [column]为使用字符串拼接,如username='[username]',偷懒时可以使用字符串拼接 
        // [column] 为PageRequest的属性
		String sql = "select t from DrugBasicInfo t where 1=1 "
				+ "/~ and t.drugId={drugId} ~/"
			  	+ "/~ and t.drugName like '%[drugName]%' ~/"
			  	+ "/~ and t.drugPingyin like '%[drugPingyin]%' ~/"
			  	+ "/~ and t.drugEffect = {drugEffect} ~/"
			  	+ "/~ and t.drugKickBack = {drugKickBack} ~/"
			  	+ "/~ and t.drugNote like '%[drugNote]%' ~/"
			  	+ "/~ and t.quantityUnit like '%[quantityUnit]%' ~/"
			  	+ "/~ and t.drugUsage like '%[drugUsage]%' ~/"
			  	+ "/~ and t.drugSpec like '%[drugSpec]%' ~/"
			  	+ "/~ and t.drugTabu = {drugTabu} ~/"
			  	+ "/~ and t.costPrice = {costPrice} ~/"
			  	+ "/~ and t.retailPrice = {retailPrice} ~/"
				+ "/~ and t.productionDate >= {productionDateBegin} ~/"
				+ "/~ and t.productionDate <= {productionDateEnd} ~/"
			  	+ "/~ and t.periodOfValidity = {periodOfValidity} ~/"
			  	+ "/~ and t.upLimit = {upLimit} ~/"
			  	+ "/~ and t.downLimit = {downLimit} ~/"
			  	+ "/~ and t.upLimit1 = {upLimit1} ~/"
			  	+ "/~ and t.downLimit1 = {downLimit1} ~/"
			  	+ "/~ and t.symptomId = {symptomId} ~/"
			  	+ "/~ and t.isDrug = {isDrug} ~/"
			  	+ "/~ and t.supplierId = {supplierId} ~/"
			  	+ "/~ and t.feeTypeId = {feeTypeId} ~/"
					+ "/~ and t.symptomIdModel.symptomName like '%[symptomIdModelTag]%'  ~/"
					+ "/~ and t.drugTabuModel.drugUnitName like '%[drugTabuModelTag]%'  ~/"
					+ "/~ and t.supplierIdModel.supplierName like '%[supplierIdModelTag]%'  ~/"
					+ "/~ and t.drugEffectModel.drugUnitName like '%[drugEffectModelTag]%'  ~/"
					+ "/~ and t.drugKickBackModel.drugUnitName like '%[drugKickBackModelTag]%'  ~/"
					+ "/~ and t.drugKickBackModel.drugUnitName like '%[feeTypeIdModelTag]%'  ~/"
				+ "/~ order by [sortColumns] ~/";
        
		return pageQuery(sql,query);
	}
	public List<DrugBasicInfo> findAllByQuery(DrugBasicInfoQuery query){
		String sql = "select t from DrugBasicInfo t where 1=1 "
					+ "/~ and t.drugId={drugId} ~/"
				  	+ "/~ and t.drugName like '%[drugName]%' ~/"
				  	+ "/~ and t.drugPingyin like '%[drugPingyin]%' ~/"
				  	+ "/~ and t.drugEffect = {drugEffect} ~/"
				  	+ "/~ and t.drugKickBack = {drugKickBack} ~/"
				  	+ "/~ and t.drugNote like '%[drugNote]%' ~/"
				  	+ "/~ and t.quantityUnit like '%[quantityUnit]%' ~/"
				  	+ "/~ and t.drugUsage like '%[drugUsage]%' ~/"
				  	+ "/~ and t.drugSpec like '%[drugSpec]%' ~/"
				  	+ "/~ and t.drugTabu = {drugTabu} ~/"
				  	+ "/~ and t.costPrice = {costPrice} ~/"
				  	+ "/~ and t.retailPrice = {retailPrice} ~/"
					+ "/~ and t.productionDate >= {productionDateBegin} ~/"
					+ "/~ and t.productionDate <= {productionDateEnd} ~/"
				  	+ "/~ and t.periodOfValidity = {periodOfValidity} ~/"
				  	+ "/~ and t.upLimit = {upLimit} ~/"
				  	+ "/~ and t.downLimit = {downLimit} ~/"
				  	+ "/~ and t.upLimit1 = {upLimit1} ~/"
				  	+ "/~ and t.downLimit1 = {downLimit1} ~/"
				  	+ "/~ and t.symptomId = {symptomId} ~/"
				  	+ "/~ and t.isDrug = {isDrug} ~/"
				  	+ "/~ and t.supplierId = {supplierId} ~/"
				  	+ "/~ and t.feeTypeId = {feeTypeId} ~/"
					+ "/~ and t.symptomIdModel.symptomName like '%[symptomIdModelTag]%'  ~/"
					+ "/~ and t.drugTabuModel.drugUnitName like '%[drugTabuModelTag]%'  ~/"
					+ "/~ and t.supplierIdModel.supplierName like '%[supplierIdModelTag]%'  ~/"
					+ "/~ and t.drugEffectModel.drugUnitName like '%[drugEffectModelTag]%'  ~/"
					+ "/~ and t.drugKickBackModel.drugUnitName like '%[drugKickBackModelTag]%'  ~/"
					+ "/~ and t.drugKickBackModel.drugUnitName like '%[feeTypeIdModelTag]%'  ~/"
					+ "/~ order by [sortColumns] ~/";
		return findAllBy(sql,query);
	}
	
}
