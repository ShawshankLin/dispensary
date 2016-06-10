/*
 */

package com.dispensary.project.dao;

import java.util.List;

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


public class DispensarystaffDaoTest extends BaseDaoTestCase{
	
	private DispensarystaffDao dao;
	
	@Autowired
	public void setDispensarystaffDao(DispensarystaffDao dao) {
		this.dao = dao;
	}

	@Override 
	protected String[] getDbUnitDataFiles() {
	    //通过 TestMethodContext.getMethodName() 可以得到当前正在运行的测试方法名称
		return new String[]{"classpath:testdata/common.xml","classpath:testdata/Dispensarystaff.xml",
		                    "classpath:testdata/Dispensarystaff_"+TestMethodContext.getMethodName()+".xml"};
	}
	
	//数据库单元测试前会开始事务，结束时会回滚事务，所以测试方法可以不用关心测试数据的删除
	@Test
	public void findPage() {

		DispensarystaffQuery query = newDispensarystaffQuery();
		Page page = dao.findPage(query);
		
		assertEquals(pageNumber,page.getThisPageNumber());
		assertEquals(pageSize,page.getPageSize());
		List resultList = (List)page.getResult();
		assertNotNull(resultList);
		
	}
	
	static int pageNumber = 1;
	static int pageSize = 10;	
	public static DispensarystaffQuery newDispensarystaffQuery() {
		DispensarystaffQuery query = new DispensarystaffQuery();
		query.setPageNumber(pageNumber);
		query.setPageSize(pageSize);
		query.setSortColumns(null);
		
	  	query.setMeStName(new String("1"));
	  	query.setSex(new Integer("1"));
	  	query.setAge(new Integer("1"));
	  	query.setEducation(new String("1"));
	  	query.setAddress(new String("1"));
	  	query.setTel(new String("1"));
	  	query.setMobile(new String("1"));
	  	query.setEmail(new String("1"));
	  	query.setWorkPlace(new String("1"));
	  	query.setNote(new String("1"));
		return query;
	}
	
}
