/*
 */

package com.dispensary.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

@Service
@Transactional
public class DrugStockInfoManager extends BaseManager<DrugStockInfo,java.lang.Integer>{

	private DrugStockInfoDao drugStockInfoDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,请注意大小写*/
	public void setDrugStockInfoDao(DrugStockInfoDao dao) {
		this.drugStockInfoDao = dao;
	}
	public EntityDao getEntityDao() {
		return this.drugStockInfoDao;
	}
	
	@Transactional(readOnly=true)
	public Page findPage(DrugStockInfoQuery query) {
		return drugStockInfoDao.findPage(query);
	}
	@Transactional(readOnly=true)
	public List<DrugStockInfo> findAll(DrugStockInfoQuery query) {
		return drugStockInfoDao.findAllByQuery(query);
	}
	public Page findExpiredDrugs(int pageNumber,int pageSize) {
		List list=drugStockInfoDao.findExpiredDrugs(pageNumber,pageSize);
		List<DrugStockExpired> newList=new ArrayList<DrugStockExpired>();
		for(int i=0;i<list.size();i++){
			Object[] objects=(Object[]) list.get(i);
			DrugStockExpired expired=new DrugStockExpired();
			expired.setDrugId(objects[0].toString());
			expired.setDrugName(objects[1].toString());
			expired.setPeriodOfValidity(Integer.parseInt(objects[2].toString()));
			expired.setProductionDate(objects[3].toString());
			expired.setNowDate(objects[4].toString());
			expired.setToDate(objects[5].toString());
			expired.setLeftDay(Integer.parseInt(objects[6].toString()));
			newList.add(expired);
		}
		Page page=new Page(pageNumber, pageSize, list.size(), newList);
		return page;
	}
	public int getStockDrugNum(){
		return drugStockInfoDao.getStockDrugNum();
	}
}
