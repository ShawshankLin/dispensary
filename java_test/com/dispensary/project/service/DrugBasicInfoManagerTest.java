/*
 */

package com.dispensary.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Test;

import cn.org.rapid_framework.test.context.TestMethodContext;
import static junit.framework.Assert.*;

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


public class DrugBasicInfoManagerTest extends BaseManagerTestCase{

	private DrugBasicInfoManager manager;
	
	@Autowired
	public void setDrugBasicInfoManager(DrugBasicInfoManager manager) {
		this.manager = manager;
	}

    @Override
    protected String[] getDbUnitDataFiles() {
        //通过 TestMethodContext.getMethodName() 可以得到当前正在运行的测试方法名称
        return new String[]{"classpath:testdata/common.xml","classpath:testdata/DrugBasicInfo.xml",
                            "classpath:testdata/DrugBasicInfo_"+TestMethodContext.getMethodName()+".xml"};
    }

	//数据库单元测试前会开始事务，结束时会回滚事务，所以测试方法可以不用关心测试数据的删除
	@Test
	public void crud() {

		DrugBasicInfo obj = newDrugBasicInfo();
		manager.save(obj);
		manager.getEntityDao().flush();
		
		manager.update(obj);
		manager.getEntityDao().flush();
		
		assertNotNull(obj.getDrugId());
		
		manager.removeById(obj.getDrugId());
		manager.getEntityDao().flush();
	
	}
	
	public static DrugBasicInfo newDrugBasicInfo() {
		DrugBasicInfo obj = new DrugBasicInfo();
		
	  	obj.setDrugName(new java.lang.String("1"));
	  	obj.setDrugPingyin(new java.lang.String("1"));
	  	obj.setDrugEffect(new java.lang.Integer("1"));
	  	obj.setDrugKickBack(new java.lang.Integer("1"));
	  	obj.setDrugNote(new java.lang.String("1"));
	  	obj.setQuantityUnit(new java.lang.Integer("1"));
	  	obj.setDrugUsage(new java.lang.String("1"));
	  	obj.setDrugSpec(new java.lang.String("1"));
	  	obj.setDrugTabu(new java.lang.Integer("1"));
	  	obj.setCostPrice(new java.lang.Float("1"));
	  	obj.setRetailPrice(new java.lang.Float("1"));
	  	obj.setProductionDate(new java.util.Date(System.currentTimeMillis()));
	  	obj.setPeriodOfValidity(new java.lang.Integer("1"));
	  	obj.setUpLimit(new java.lang.Integer("1"));
	  	obj.setDownLimit(new java.lang.Integer("1"));
	  	obj.setUpLimit1(new java.lang.Integer("1"));
	  	obj.setDownLimit1(new java.lang.Integer("1"));
	  	obj.setIsDrug(new java.lang.Integer("1"));
	  	obj.setSupplierId(new java.lang.Integer("1"));
		return obj;
	}
}
