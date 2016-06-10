package com.dispensary.project.utils;

import java.util.HashMap;
import java.util.Map;

import com.dispensary.project.model.Userinfo;

public class Application {
	private Map<Integer, Userinfo> onlineUser=new HashMap<Integer, Userinfo>();

	public Map<Integer, Userinfo> getOnlineUser() {
		return onlineUser;
	}

	public void setOnlineUser(Map<Integer, Userinfo> onlineUser) {
		this.onlineUser = onlineUser;
	}


}
