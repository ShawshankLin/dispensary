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


public class ReceiptDaoTest extends BaseDaoTestCase{
	
	private ReceiptDao dao;
	
	@Autowired
	public void setReceiptDao(ReceiptDao dao) {
		this.dao = dao;
	}

	@Override 
	protected String[] getDbUnitDataFiles() {
	    //通过 TestMethodContext.getMethodName() 可以得到当前正在运行的测试方法名称
		return new String[]{"classpath:testdata/common.xml","classpath:testdata/Receipt.xml",
		                    "classpath:testdata/Receipt_"+TestMethodContext.getMethodName()+".xml"};
	}
	
	//数据库单元测试前会开始事务，结束时会回滚事务，所以测试方法可以不用关心测试数据的删除
	@Test
	public void findPage() {

		ReceiptQuery query = newReceiptQuery();
		Page page = dao.findPage(query);
		
		assertEquals(pageNumber,page.getThisPageNumber());
		assertEquals(pageSize,page.getPageSize());
		List resultList = (List)page.getResult();
		assertNotNull(resultList);
		
	}
	
	static int pageNumber = 1;
	static int pageSize = 10;	
	public static ReceiptQuery newReceiptQuery() {
		ReceiptQuery query = new ReceiptQuery();
		query.setPageNumber(pageNumber);
		query.setPageSize(pageSize);
		query.setSortColumns(null);
		
	  	query.setPresId(new Integer("1"));
	  	query.setDrugFee(new Float("1"));
	  	query.setTransferFee(new Float("1"));
	  	query.setOxygenFee(new Float("1"));
	  	query.setPhysicalFee(new Float("1"));
	  	query.setEmergencyFee(new Float("1"));
	  	query.setInjectionFee(new Float("1"));
	  	query.setNebulizationFee(new Float("1"));
	  	query.setRegisterFee(new Float("1"));
	  	query.setFeeSum(new Float("1"));
	  	query.setMeStId(new Integer("1"));
		query.setRecDateBegin(new Date(System.currentTimeMillis()));
		query.setRecDateEnd(new Date(System.currentTimeMillis()));
		return query;
	}
	
}
