/*
 */

package com.dispensary.project.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

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
public class StudentManager extends BaseManager<Student,java.lang.String>{

	private StudentDao studentDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,请注意大小写*/
	public void setStudentDao(StudentDao dao) {
		this.studentDao = dao;
	}
	public EntityDao getEntityDao() {
		return this.studentDao;
	}
	
	@Transactional(readOnly=true)
	public Page findPage(StudentQuery query) {
		return studentDao.findPage(query);
	}
	@Transactional(readOnly=true)
	public List<Student> findAll(StudentQuery query) {
		return studentDao.findAllByQuery(query);
	}
	public List<Student> getStuJSON(String stuNum,String stuName){
		List list=studentDao.getStuJSON(stuNum,stuName);
		List<Student> stusList=new ArrayList<Student>();
		for(int i=0;i<list.size();i++){
			Object[] objs=(Object[])list.get(i);
			Student stu=new Student();
			stu.setStuNum(objs[0].toString());
			stu.setStuName(objs[1].toString());
			stusList.add(stu);
		}
		return stusList;
	}
	
	
}
