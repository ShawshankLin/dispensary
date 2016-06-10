/*
 */

package com.dispensary.project.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

@Service
@Transactional
public class LogManager extends BaseManager<Log,java.lang.Integer>{

	private LogDao logDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,请注意大小写*/
	public void setLogDao(LogDao dao) {
		this.logDao = dao;
	}
	public EntityDao getEntityDao() {
		return this.logDao;
	}
	
	@Transactional(readOnly=true)
	public Page findPage(LogQuery query) {
		return logDao.findPage(query);
	}
	@Transactional(readOnly=true)
	public List<Log> findAll(LogQuery query) {
		return logDao.findAllByQuery(query);
	}
	
}
