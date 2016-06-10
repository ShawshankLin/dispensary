package com.dispensary.project.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dispensary.project.model.Dispensarystaff;
import com.dispensary.project.model.Memo;
import com.dispensary.project.model.Notice;
import com.dispensary.project.model.UserRoleInfo;
import com.dispensary.project.model.Userinfo;

public class MySession {
	/**
	 * 用户信息
	 */
	private Userinfo userinfo;
	/**
	 * 公告
	 */
	private Notice notice;
	/**
	 * 个人备忘录
	 */
	private Memo memo;
	/**
	 * 历史就诊数
	 */
	private int histVisitsNum;
	/**
	 * 昨天就诊数
	 */
	private int ydayVisitsNum;
	/**
	 * 库存药品数
	 */
	private int stockDrugNum;
	/**
	 * 前5名医务人员
	 */
	private List<Dispensarystaff> topStaffs=new ArrayList<Dispensarystaff>();
	/**
	 * 用户角色
	 */
	private List<UserRoleInfo> roleList=new ArrayList<UserRoleInfo>();
	public Userinfo getUserinfo() {
		return userinfo;
	}
	public void setUserinfo(Userinfo userinfo) {
		this.userinfo = userinfo;
	}
	public Notice getNotice() {
		return notice;
	}
	public void setNotice(Notice notice) {
		this.notice = notice;
	}
	public Memo getMemo() {
		return memo;
	}
	public void setMemo(Memo memo) {
		this.memo = memo;
	}
	public int getHistVisitsNum() {
		return histVisitsNum;
	}
	public void setHistVisitsNum(int histVisitsNum) {
		this.histVisitsNum = histVisitsNum;
	}
	public int getYdayVisitsNum() {
		return ydayVisitsNum;
	}
	public void setYdayVisitsNum(int ydayVisitsNum) {
		this.ydayVisitsNum = ydayVisitsNum;
	}
	public int getStockDrugNum() {
		return stockDrugNum;
	}
	public void setStockDrugNum(int stockDrugNum) {
		this.stockDrugNum = stockDrugNum;
	}
	public List<Dispensarystaff> getTopStaffs() {
		return topStaffs;
	}
	public void setTopStaffs(List<Dispensarystaff> topStaffs) {
		this.topStaffs = topStaffs;
	}
	public List<UserRoleInfo> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<UserRoleInfo> roleList) {
		this.roleList = roleList;
	}


	
	
}
