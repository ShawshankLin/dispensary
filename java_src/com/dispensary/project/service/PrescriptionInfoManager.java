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
public class PrescriptionInfoManager extends BaseManager<PrescriptionInfo,java.lang.Integer>{

	private PrescriptionInfoDao prescriptionInfoDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,请注意大小写*/
	public void setPrescriptionInfoDao(PrescriptionInfoDao dao) {
		this.prescriptionInfoDao = dao;
	}
	public EntityDao getEntityDao() {
		return this.prescriptionInfoDao;
	}
	
	@Transactional(readOnly=true)
	public Page findPage(PrescriptionInfoQuery query) {
		return prescriptionInfoDao.findPage(query);
	}
	@Transactional(readOnly=true)
	public List<PrescriptionInfo> findAll(PrescriptionInfoQuery query) {
		return prescriptionInfoDao.findAllByQuery(query);
	}
	//获取处方编号
	public int getPresId(){
		List<Integer> presId=prescriptionInfoDao.getMaxId();
		if(presId.get(0)!=null){     
			return presId.get(0)+1;
		}
		return 1;
	}
	
}
