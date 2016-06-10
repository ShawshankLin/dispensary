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
public class DrugStockInManager extends BaseManager<DrugStockIn,java.lang.Integer>{

	private DrugStockInDao drugStockInDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,请注意大小写*/
	public void setDrugStockInDao(DrugStockInDao dao) {
		this.drugStockInDao = dao;
	}
	public EntityDao getEntityDao() {
		return this.drugStockInDao;
	}
	
	@Transactional(readOnly=true)
	public Page findPage(DrugStockInQuery query) {
		return drugStockInDao.findPage(query);
	}
	@Transactional(readOnly=true)
	public List<DrugStockIn> findAll(DrugStockInQuery query) {
		return drugStockInDao.findAllByQuery(query);
	}
	public String getStockInId(){
		List<Integer> maxId=drugStockInDao.getMaxId();
		if(maxId.get(0)!=null){
			return "in"+String.format("%06d",maxId.get(0)+1);
		}
		return "in"+String.format("%06d",1);
	}
	public String getSerialNum(){
		SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmss");
		String nowdate = format.format(new Date());
		return nowdate;
	}
}
