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


public class PatiCaseHistoryDaoTest extends BaseDaoTestCase{
	
	private PatiCaseHistoryDao dao;
	
	@Autowired
	public void setPatiCaseHistoryDao(PatiCaseHistoryDao dao) {
		this.dao = dao;
	}

	@Override 
	protected String[] getDbUnitDataFiles() {
	    //通过 TestMethodContext.getMethodName() 可以得到当前正在运行的测试方法名称
		return new String[]{"classpath:testdata/common.xml","classpath:testdata/PatiCaseHistory.xml",
		                    "classpath:testdata/PatiCaseHistory_"+TestMethodContext.getMethodName()+".xml"};
	}
	
	//数据库单元测试前会开始事务，结束时会回滚事务，所以测试方法可以不用关心测试数据的删除
	@Test
	public void findPage() {

		PatiCaseHistoryQuery query = newPatiCaseHistoryQuery();
		Page page = dao.findPage(query);
		
		assertEquals(pageNumber,page.getThisPageNumber());
		assertEquals(pageSize,page.getPageSize());
		List resultList = (List)page.getResult();
		assertNotNull(resultList);
		
	}
	
	static int pageNumber = 1;
	static int pageSize = 10;	
	public static PatiCaseHistoryQuery newPatiCaseHistoryQuery() {
		PatiCaseHistoryQuery query = new PatiCaseHistoryQuery();
		query.setPageNumber(pageNumber);
		query.setPageSize(pageSize);
		query.setSortColumns(null);
		
	  	query.setCaseId(new String("1"));
	  	query.setStuNum(new String("1"));
		query.setVisitDateBegin(new Date(System.currentTimeMillis()));
		query.setVisitDateEnd(new Date(System.currentTimeMillis()));
	  	query.setDispensaryRecord(new String("1"));
	  	query.setAllergy(new String("1"));
	  	query.setPrimaryCare(new String("1"));
	  	query.setReExamination(new String("1"));
	  	query.setDescribes(new String("1"));
	  	query.setExamineStatus(new String("1"));
	  	query.setFirstImpress(new String("1"));
	  	query.setMeStId(new Integer("1"));
		return query;
	}
	
}
