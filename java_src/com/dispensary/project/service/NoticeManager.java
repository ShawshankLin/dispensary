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
public class NoticeManager extends BaseManager<Notice,java.lang.Integer>{

	private NoticeDao noticeDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,请注意大小写*/
	public void setNoticeDao(NoticeDao dao) {
		this.noticeDao = dao;
	}
	public EntityDao getEntityDao() {
		return this.noticeDao;
	}
	
	@Transactional(readOnly=true)
	public Page findPage(NoticeQuery query) {
		return noticeDao.findPage(query);
	}
	@Transactional(readOnly=true)
	public List<Notice> findAll(NoticeQuery query) {
		return noticeDao.findAllByQuery(query);
	}
	//返回最新的公告消息
	public Notice getNewNotice() {
		List list=noticeDao.getNewNotice();
		if(list.size()==1){
			Object[] obj=(Object[]) list.get(0);
			Notice notice=new Notice();
			notice.setId(Integer.parseInt(obj[0].toString()));
			notice.setUserId(Integer.parseInt(obj[1].toString()));
			notice.setTitle(obj[2].toString());
			notice.setContent(obj[3].toString());
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				notice.setDate(format.parse(obj[6].toString()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			notice.setStatus(Integer.parseInt(obj[7].toString()));
			return notice;
		}
		return null;
	}
}
