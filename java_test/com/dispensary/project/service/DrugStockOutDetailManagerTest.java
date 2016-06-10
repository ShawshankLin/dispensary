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


public class DrugStockOutDetailManagerTest extends BaseManagerTestCase{

	private DrugStockOutDetailManager manager;
	
	@Autowired
	public void setDrugStockOutDetailManager(DrugStockOutDetailManager manager) {
		this.manager = manager;
	}

    @Override
    protected String[] getDbUnitDataFiles() {
        //通过 TestMethodContext.getMethodName() 可以得到当前正在运行的测试方法名称
        return new String[]{"classpath:testdata/common.xml","classpath:testdata/DrugStockOutDetail.xml",
                            "classpath:testdata/DrugStockOutDetail_"+TestMethodContext.getMethodName()+".xml"};
    }

	//数据库单元测试前会开始事务，结束时会回滚事务，所以测试方法可以不用关心测试数据的删除
	@Test
	public void crud() {

		DrugStockOutDetail obj = newDrugStockOutDetail();
		manager.save(obj);
		manager.getEntityDao().flush();
		
		manager.update(obj);
		manager.getEntityDao().flush();
		
		assertNotNull(obj.getId());
		
		manager.removeById(obj.getId());
		manager.getEntityDao().flush();
	
	}
	
	public static DrugStockOutDetail newDrugStockOutDetail() {
		DrugStockOutDetail obj = new DrugStockOutDetail();
		
	  	obj.setSerialNumber(new java.lang.String("1"));
	  	obj.setDrugId(new java.lang.Integer("1"));
	  	obj.setOutAmount(new java.lang.Integer("1"));
	  	obj.setOutPrice(new java.lang.Long("1"));
	  	obj.setOutPlaceId(new java.lang.Integer("1"));
	  	obj.setFromPlaceId(new java.lang.Integer("1"));
	  	obj.setProductionDate(new java.util.Date(System.currentTimeMillis()));
		return obj;
	}
}
