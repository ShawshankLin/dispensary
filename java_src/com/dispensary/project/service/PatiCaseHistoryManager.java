/*
 */

package com.dispensary.project.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import javax.persistence.criteria.CriteriaBuilder.In;

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
public class PatiCaseHistoryManager extends BaseManager<PatiCaseHistory,java.lang.Integer>{

	private PatiCaseHistoryDao patiCaseHistoryDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,请注意大小写*/
	public void setPatiCaseHistoryDao(PatiCaseHistoryDao dao) {
		this.patiCaseHistoryDao = dao;
	}
	public EntityDao getEntityDao() {
		return this.patiCaseHistoryDao;
	}
	
	@Transactional(readOnly=true)
	public Page findPage(PatiCaseHistoryQuery query) {
		return patiCaseHistoryDao.findPage(query);
	}
	@Transactional(readOnly=true)
	public List<PatiCaseHistory> findAll(PatiCaseHistoryQuery query) {
		return patiCaseHistoryDao.findAllByQuery(query);
	}
	
	public String getCardId(Date date){
		SimpleDateFormat format = new SimpleDateFormat("yyMMdd");
		String nowdate = format.format(date);
		List<Integer> list=patiCaseHistoryDao.getMaxID();
		String id=null;
		if(list.get(0)!=null){
			id=String.format("%04d",list.get(0).intValue()+1);
		}else{
			id=String.format("%04d",1);
		}
		return nowdate+id;
	}
	//返会柱形数据
	public  CategoryDataset getCategoryDataSet() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset(); 
    	List list=patiCaseHistoryDao.getMonthVisits();
    	Iterator it = list.iterator();
		while(it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			dataset.addValue(Integer.parseInt((obj[1]).toString()), "就诊人数", (obj[0]).toString());
		}
        return dataset; 
    }
	//返回饼图数据
	public  PieDataset getPieDataSet() {
        DefaultPieDataset dataset = new DefaultPieDataset(); 
    	List list=patiCaseHistoryDao.getMonthVisits();
		Iterator it = list.iterator();
		while(it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			dataset.setValue((obj[0]).toString(),Double.parseDouble((obj[1]).toString()));
		}
        return dataset; 
    }
	//返回直线统计数据
	public TimeSeries getLineDateSet(){
		TimeSeries timeSeries = new TimeSeries("每日就诊数", Day.class);  
		List list=patiCaseHistoryDao.getMonthVisits();
		Iterator it = list.iterator();
		while(it.hasNext()){
			Object[] obj = (Object[]) it.next();
			// Day的组装格式是day-month-year 访问次数 
			timeSeries.add(new Day(1,Integer.parseInt(String.valueOf(obj[0])),Calendar.getInstance().get(Calendar.YEAR)),Integer.parseInt(String.valueOf(obj[1])));  
		}
		return timeSeries;
	}
	//查询每月就诊数
	public List<Integer> getMonthVisits(){
		return patiCaseHistoryDao.getMonthVisits();
	}
	public List<PieModel> getMonthVisitsPie(){
		List<Integer> list=patiCaseHistoryDao.getMonthVisitsPie();
		List<PieModel> newList=new ArrayList<PieModel>();
		Iterator iterator=list.iterator();
		while(iterator.hasNext()){
			Object[] objects=(Object[]) iterator.next();
			PieModel pie=new PieModel();
			pie.setValue(Integer.parseInt(objects[0].toString()));
			pie.setName(objects[1].toString());
			newList.add(pie);
		}
		return newList;
	}
	public int getHistVisitsNum(){
		return patiCaseHistoryDao.getHistVisitsNum();
	}
	public int getYdayVisitsNum(){
		return patiCaseHistoryDao.getYdayVisitsNum();
	}
	public List getTopVisits(){
		List topList=patiCaseHistoryDao.getTopVisits();
		List<Dispensarystaff> staffList=new ArrayList<Dispensarystaff>();
		for(int i=0;i<topList.size();i++){
			Object[] obj=(Object[]) topList.get(i);
			Dispensarystaff staff=new Dispensarystaff();
			staff.setMeStId(Integer.parseInt(obj[0].toString()));
			staff.setMeStName(obj[1].toString());
			staff.setTopVisits(Integer.parseInt(obj[2].toString()));
			SimpleDateFormat format=new SimpleDateFormat();
			staff.setLastVisitedTime(obj[3].toString());
			staffList.add(staff);
		}
		return staffList;
	}
}
