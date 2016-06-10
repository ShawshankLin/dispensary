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


public class PrescriptionInfoManagerTest extends BaseManagerTestCase{

	private PrescriptionInfoManager manager;
	
	@Autowired
	public void setPrescriptionInfoManager(PrescriptionInfoManager manager) {
		this.manager = manager;
	}

    @Override
    protected String[] getDbUnitDataFiles() {
        //通过 TestMethodContext.getMethodName() 可以得到当前正在运行的测试方法名称
        return new String[]{"classpath:testdata/common.xml","classpath:testdata/PrescriptionInfo.xml",
                            "classpath:testdata/PrescriptionInfo_"+TestMethodContext.getMethodName()+".xml"};
    }

	//数据库单元测试前会开始事务，结束时会回滚事务，所以测试方法可以不用关心测试数据的删除
	@Test
	public void crud() {

		PrescriptionInfo obj = newPrescriptionInfo();
		manager.save(obj);
		manager.getEntityDao().flush();
		
		manager.update(obj);
		manager.getEntityDao().flush();
		
		assertNotNull(obj.getPresId());
		
		manager.removeById(obj.getPresId());
		manager.getEntityDao().flush();
	
	}
	
	public static PrescriptionInfo newPrescriptionInfo() {
		PrescriptionInfo obj = new PrescriptionInfo();
		
	  	obj.setCaseId(new java.lang.String("1"));
	  	obj.setPersDate(new java.util.Date(System.currentTimeMillis()));
	  	obj.setState(new java.lang.Integer("1"));
	  	obj.setDrugSum(new java.lang.Float("1"));
		return obj;
	}
}
