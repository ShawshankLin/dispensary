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


public class DispensarystaffManagerTest extends BaseManagerTestCase{

	private DispensarystaffManager manager;
	
	@Autowired
	public void setDispensarystaffManager(DispensarystaffManager manager) {
		this.manager = manager;
	}

    @Override
    protected String[] getDbUnitDataFiles() {
        //通过 TestMethodContext.getMethodName() 可以得到当前正在运行的测试方法名称
        return new String[]{"classpath:testdata/common.xml","classpath:testdata/Dispensarystaff.xml",
                            "classpath:testdata/Dispensarystaff_"+TestMethodContext.getMethodName()+".xml"};
    }

	//数据库单元测试前会开始事务，结束时会回滚事务，所以测试方法可以不用关心测试数据的删除
	@Test
	public void crud() {

		Dispensarystaff obj = newDispensarystaff();
		manager.save(obj);
		manager.getEntityDao().flush();
		
		manager.update(obj);
		manager.getEntityDao().flush();
		
		assertNotNull(obj.getMeStId());
		
		manager.removeById(obj.getMeStId());
		manager.getEntityDao().flush();
	
	}
	
	public static Dispensarystaff newDispensarystaff() {
		Dispensarystaff obj = new Dispensarystaff();
		
	  	obj.setMeStName(new java.lang.String("1"));
	  	obj.setSex(new java.lang.Integer("1"));
	  	obj.setAge(new java.lang.Integer("1"));
	  	obj.setEducation(new java.lang.String("1"));
	  	obj.setAddress(new java.lang.String("1"));
	  	obj.setTel(new java.lang.String("1"));
	  	obj.setMobile(new java.lang.String("1"));
	  	obj.setEmail(new java.lang.String("1"));
	  	obj.setWorkPlace(new java.lang.String("1"));
	  	obj.setNote(new java.lang.String("1"));
		return obj;
	}
}
