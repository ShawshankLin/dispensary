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


public class SupplierManagerTest extends BaseManagerTestCase{

	private SupplierManager manager;
	
	@Autowired
	public void setSupplierManager(SupplierManager manager) {
		this.manager = manager;
	}

    @Override
    protected String[] getDbUnitDataFiles() {
        //通过 TestMethodContext.getMethodName() 可以得到当前正在运行的测试方法名称
        return new String[]{"classpath:testdata/common.xml","classpath:testdata/Supplier.xml",
                            "classpath:testdata/Supplier_"+TestMethodContext.getMethodName()+".xml"};
    }

	//数据库单元测试前会开始事务，结束时会回滚事务，所以测试方法可以不用关心测试数据的删除
	@Test
	public void crud() {

		Supplier obj = newSupplier();
		manager.save(obj);
		manager.getEntityDao().flush();
		
		manager.update(obj);
		manager.getEntityDao().flush();
		
		assertNotNull(obj.getSupplierId());
		
		manager.removeById(obj.getSupplierId());
		manager.getEntityDao().flush();
	
	}
	
	public static Supplier newSupplier() {
		Supplier obj = new Supplier();
		
	  	obj.setSupplierName(new java.lang.String("1"));
	  	obj.setContacts(new java.lang.String("1"));
	  	obj.setPingyin(new java.lang.String("1"));
	  	obj.setAddress(new java.lang.String("1"));
	  	obj.setUserTel(new java.lang.String("1"));
	  	obj.setUserMobile(new java.lang.String("1"));
	  	obj.setUserEmail(new java.lang.String("1"));
	  	obj.setNote(new java.lang.String("1"));
		return obj;
	}
}
