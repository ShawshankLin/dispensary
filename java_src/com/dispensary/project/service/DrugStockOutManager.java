/*
 */

package com.dispensary.project.service;

import java.text.SimpleDateFormat;
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
public class DrugStockOutManager extends BaseManager<DrugStockOut,java.lang.Integer>{

	private DrugStockOutDao drugStockOutDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,请注意大小写*/
	public void setDrugStockOutDao(DrugStockOutDao dao) {
		this.drugStockOutDao = dao;
	}
	public EntityDao getEntityDao() {
		return this.drugStockOutDao;
	}
	
	@Transactional(readOnly=true)
	public Page findPage(DrugStockOutQuery query) {
		return drugStockOutDao.findPage(query);
	}
	@Transactional(readOnly=true)
	public List<DrugStockOut> findAll(DrugStockOutQuery query) {
		return drugStockOutDao.findAllByQuery(query);
	}
	public String getStockOutId(){
		List<Integer> maxId=drugStockOutDao.getMaxId();
		if(maxId.get(0)!=null){
			return "out"+String.format("%06d",maxId.get(0)+1);
		}else{
			return "out"+String.format("%06d",1);
		}
	}
	public String getSerialNum(String id){
		SimpleDateFormat format = new SimpleDateFormat("yyMMdd");
		String nowdate = format.format(new Date());
		return id+nowdate;
	}
}
