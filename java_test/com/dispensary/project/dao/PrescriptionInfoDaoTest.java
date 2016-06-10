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


public class PrescriptionInfoDaoTest extends BaseDaoTestCase{
	
	private PrescriptionInfoDao dao;
	
	@Autowired
	public void setPrescriptionInfoDao(PrescriptionInfoDao dao) {
		this.dao = dao;
	}

	@Override 
	protected String[] getDbUnitDataFiles() {
	    //通过 TestMethodContext.getMethodName() 可以得到当前正在运行的测试方法名称
		return new String[]{"classpath:testdata/common.xml","classpath:testdata/PrescriptionInfo.xml",
		                    "classpath:testdata/PrescriptionInfo_"+TestMethodContext.getMethodName()+".xml"};
	}
	
	//数据库单元测试前会开始事务，结束时会回滚事务，所以测试方法可以不用关心测试数据的删除
	@Test
	public void findPage() {

		PrescriptionInfoQuery query = newPrescriptionInfoQuery();
		Page page = dao.findPage(query);
		
		assertEquals(pageNumber,page.getThisPageNumber());
		assertEquals(pageSize,page.getPageSize());
		List resultList = (List)page.getResult();
		assertNotNull(resultList);
		
	}
	
	static int pageNumber = 1;
	static int pageSize = 10;	
	public static PrescriptionInfoQuery newPrescriptionInfoQuery() {
		PrescriptionInfoQuery query = new PrescriptionInfoQuery();
		query.setPageNumber(pageNumber);
		query.setPageSize(pageSize);
		query.setSortColumns(null);
		
	  	query.setCaseId(new String("1"));
		query.setPersDateBegin(new Date(System.currentTimeMillis()));
		query.setPersDateEnd(new Date(System.currentTimeMillis()));
	  	query.setState(new Integer("1"));
	  	query.setDrugSum(new java.lang.Float("1"));
		return query;
	}
	
}
