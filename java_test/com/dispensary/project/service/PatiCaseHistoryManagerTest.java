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


public class PatiCaseHistoryManagerTest extends BaseManagerTestCase{

	private PatiCaseHistoryManager manager;
	
	@Autowired
	public void setPatiCaseHistoryManager(PatiCaseHistoryManager manager) {
		this.manager = manager;
	}

    @Override
    protected String[] getDbUnitDataFiles() {
        //通过 TestMethodContext.getMethodName() 可以得到当前正在运行的测试方法名称
        return new String[]{"classpath:testdata/common.xml","classpath:testdata/PatiCaseHistory.xml",
                            "classpath:testdata/PatiCaseHistory_"+TestMethodContext.getMethodName()+".xml"};
    }

	//数据库单元测试前会开始事务，结束时会回滚事务，所以测试方法可以不用关心测试数据的删除
	@Test
	public void crud() {

		PatiCaseHistory obj = newPatiCaseHistory();
		manager.save(obj);
		manager.getEntityDao().flush();
		
		manager.update(obj);
		manager.getEntityDao().flush();
		
		assertNotNull(obj.getId());
		
		manager.removeById(obj.getId());
		manager.getEntityDao().flush();
	
	}
	
	public static PatiCaseHistory newPatiCaseHistory() {
		PatiCaseHistory obj = new PatiCaseHistory();
		
	  	obj.setCaseId(new java.lang.String("1"));
	  	obj.setStuNum(new java.lang.String("1"));
	  	obj.setVisitDate(new java.util.Date(System.currentTimeMillis()));
	  	obj.setDispensaryRecord(new java.lang.String("1"));
	  	obj.setAllergy(new java.lang.String("1"));
	  	obj.setPrimaryCare(new java.lang.String("1"));
	  	obj.setReExamination(new java.lang.String("1"));
	  	obj.setDescribes(new java.lang.String("1"));
	  	obj.setExamineStatus(new java.lang.String("1"));
	  	obj.setFirstImpress(new java.lang.String("1"));
	  	obj.setMeStId(new java.lang.Integer("1"));
		return obj;
	}
}
