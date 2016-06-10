/*
 */

package com.dispensary.project.service;

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
public class SupplierManager extends BaseManager<Supplier,java.lang.Integer>{

	private SupplierDao supplierDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,请注意大小写*/
	public void setSupplierDao(SupplierDao dao) {
		this.supplierDao = dao;
	}
	public EntityDao getEntityDao() {
		return this.supplierDao;
	}
	
	@Transactional(readOnly=true)
	public Page findPage(SupplierQuery query) {
		return supplierDao.findPage(query);
	}
	@Transactional(readOnly=true)
	public List<Supplier> findAll(SupplierQuery query) {
		return supplierDao.findAllByQuery(query);
	}
	
}
