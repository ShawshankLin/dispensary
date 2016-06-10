/*
 */

package com.dispensary.project.dao;

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


import static cn.org.rapid_framework.util.ObjectUtils.*;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class ReceiptDao extends BaseHibernateDao<Receipt,java.lang.Integer>{

	public Class getEntityClass() {
		return Receipt.class;
	}
	
	public Page findPage(ReceiptQuery query) {
        //XsqlBuilder syntax,please see http://code.google.com/p/rapid-xsqlbuilder
        // [column]为字符串拼接, {column}为使用占位符. [column]为使用字符串拼接,如username='[username]',偷懒时可以使用字符串拼接 
        // [column] 为PageRequest的属性
		String sql = "select t from Receipt t where 1=1 "
			  	+ "/~ and t.presId = {presId} ~/"
			  	+ "/~ and t.drugFee = {drugFee} ~/"
			  	+ "/~ and t.transferFee = {transferFee} ~/"
			  	+ "/~ and t.oxygenFee = {oxygenFee} ~/"
			  	+ "/~ and t.physicalFee = {physicalFee} ~/"
			  	+ "/~ and t.emergencyFee = {emergencyFee} ~/"
			  	+ "/~ and t.injectionFee = {injectionFee} ~/"
			  	+ "/~ and t.nebulizationFee = {nebulizationFee} ~/"
			  	+ "/~ and t.registerFee = {registerFee} ~/"
			  	+ "/~ and t.feeSum = {feeSum} ~/"
			  	+ "/~ and t.meStId = {meStId} ~/"
				+ "/~ and t.recDate >= {recDateBegin} ~/"
				+ "/~ and t.recDate <= {recDateEnd} ~/"
					+ "/~ and t.meStIdModel.meStName like '%[meStIdModelTag]%'  ~/"
					+ "/~ and t.presIdModel.id like '%[presIdModelTag]%'  ~/"
				+ "/~ order by [sortColumns] ~/";
        
		return pageQuery(sql,query);
	}
	public List<Receipt> findAllByQuery(ReceiptQuery query){
		String sql = "select t from Receipt t where 1=1 "
				  	+ "/~ and t.presId = {presId} ~/"
				  	+ "/~ and t.drugFee = {drugFee} ~/"
				  	+ "/~ and t.transferFee = {transferFee} ~/"
				  	+ "/~ and t.oxygenFee = {oxygenFee} ~/"
				  	+ "/~ and t.physicalFee = {physicalFee} ~/"
				  	+ "/~ and t.emergencyFee = {emergencyFee} ~/"
				  	+ "/~ and t.injectionFee = {injectionFee} ~/"
				  	+ "/~ and t.nebulizationFee = {nebulizationFee} ~/"
				  	+ "/~ and t.registerFee = {registerFee} ~/"
				  	+ "/~ and t.feeSum = {feeSum} ~/"
				  	+ "/~ and t.meStId = {meStId} ~/"
					+ "/~ and t.recDate >= {recDateBegin} ~/"
					+ "/~ and t.recDate <= {recDateEnd} ~/"
					+ "/~ and t.meStIdModel.meStName like '%[meStIdModelTag]%'  ~/"
					+ "/~ and t.presIdModel.id like '%[presIdModelTag]%'  ~/"
					+ "/~ order by [sortColumns] ~/";
		return findAllBy(sql,query);
	}

}
