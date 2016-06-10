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


public class StudentManagerTest extends BaseManagerTestCase{

	private StudentManager manager;
	
	@Autowired
	public void setStudentManager(StudentManager manager) {
		this.manager = manager;
	}

    @Override
    protected String[] getDbUnitDataFiles() {
        //通过 TestMethodContext.getMethodName() 可以得到当前正在运行的测试方法名称
        return new String[]{"classpath:testdata/common.xml","classpath:testdata/Student.xml",
                            "classpath:testdata/Student_"+TestMethodContext.getMethodName()+".xml"};
    }

	//数据库单元测试前会开始事务，结束时会回滚事务，所以测试方法可以不用关心测试数据的删除
	@Test
	public void crud() {

		Student obj = newStudent();
		manager.save(obj);
		manager.getEntityDao().flush();
		
		manager.update(obj);
		manager.getEntityDao().flush();
		
		assertNotNull(obj.getStuNum());
		
		manager.removeById(obj.getStuNum());
		manager.getEntityDao().flush();
	
	}
	
	public static Student newStudent() {
		Student obj = new Student();
		
	  	obj.setStuName(new java.lang.String("1"));
	  	obj.setSex(new java.lang.Integer("1"));
	  	obj.setBirthDate(new java.util.Date(System.currentTimeMillis()));
	  	obj.setIdcard(new java.lang.String("1"));
	  	obj.setTel(new java.lang.String("1"));
	  	obj.setDepartment(new java.lang.String("1"));
	  	obj.setMajor(new java.lang.String("1"));
	  	obj.setStuClass(new java.lang.String("1"));
	  	obj.setAddress(new java.lang.String("1"));
		return obj;
	}
}
