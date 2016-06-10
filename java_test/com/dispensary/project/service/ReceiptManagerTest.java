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


public class ReceiptManagerTest extends BaseManagerTestCase{

	private ReceiptManager manager;
	
	@Autowired
	public void setReceiptManager(ReceiptManager manager) {
		this.manager = manager;
	}

    @Override
    protected String[] getDbUnitDataFiles() {
        //通过 TestMethodContext.getMethodName() 可以得到当前正在运行的测试方法名称
        return new String[]{"classpath:testdata/common.xml","classpath:testdata/Receipt.xml",
                            "classpath:testdata/Receipt_"+TestMethodContext.getMethodName()+".xml"};
    }

	//数据库单元测试前会开始事务，结束时会回滚事务，所以测试方法可以不用关心测试数据的删除
	@Test
	public void crud() {

		Receipt obj = newReceipt();
		manager.save(obj);
		manager.getEntityDao().flush();
		
		manager.update(obj);
		manager.getEntityDao().flush();
		
		assertNotNull(obj.getRecId());
		
		manager.removeById(obj.getRecId());
		manager.getEntityDao().flush();
	
	}
	
	public static Receipt newReceipt() {
		Receipt obj = new Receipt();
		
	  	obj.setPresId(new java.lang.Integer("1"));
	  	obj.setDrugFee(new Long("1"));
	  	obj.setTransferFee(new Long("1"));
	  	obj.setOxygenFee(new Long("1"));
	  	obj.setPhysicalFee(new Long("1"));
	  	obj.setEmergencyFee(new Long("1"));
	  	obj.setInjectionFee(new Long("1"));
	  	obj.setNebulizationFee(new Long("1"));
	  	obj.setRegisterFee(new Long("1"));
	  	obj.setFeeSum(new Long("1"));
	  	obj.setMeStId(new java.lang.Integer("1"));
	  	obj.setRecDate(new java.util.Date(System.currentTimeMillis()));
		return obj;
	}
}
