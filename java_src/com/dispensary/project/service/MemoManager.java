/*
 */

package com.dispensary.project.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
public class MemoManager extends BaseManager<Memo,java.lang.Integer>{

	private MemoDao memoDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,请注意大小写*/
	public void setMemoDao(MemoDao dao) {
		this.memoDao = dao;
	}
	public EntityDao getEntityDao() {
		return this.memoDao;
	}
	
	@Transactional(readOnly=true)
	public Page findPage(MemoQuery query) {
		return memoDao.findPage(query);
	}
	@Transactional(readOnly=true)
	public List<Memo> findAll(MemoQuery query) {
		return memoDao.findAllByQuery(query);
	}
	public Memo getNewMemo(String userId){
		List list=memoDao.getNewMemo(userId);
		if(list.size()==1){
			Object[] obj=(Object[]) list.get(0);
			Memo memo=new Memo();
			memo.setId(Integer.parseInt(obj[0].toString()));
			memo.setUserId(Integer.parseInt(obj[1].toString()));
			memo.setTitle(obj[2].toString());
			memo.setContent(obj[3].toString());
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				memo.setDate(format.parse(obj[5].toString()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			memo.setStatus(Integer.parseInt(obj[6].toString()));
			return memo;
		}
		return null;
	}
}
