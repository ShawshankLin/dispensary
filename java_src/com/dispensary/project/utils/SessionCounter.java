package com.dispensary.project.utils;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList; 

import javax.servlet.ServletException;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener; 

import org.apache.log4j.Logger; 

public class SessionCounter implements HttpSessionListener,ServletRequestListener {
    private static Logger log = Logger.getLogger(SessionCounter.class);
    private static final String CONTENT_TYPE = "text/html; charset=GBK";
    private static int activeSessions = 0;//当前活动的人数
    private HttpServletRequest request;
    private static ArrayList list = new ArrayList();//用来存放不同ip的地址 

    public void init() throws ServletException {
        log.info("SessionCounter init!"); 

    } 

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("SessionCounter doGet!");
        response.setContentType(CONTENT_TYPE);
        HttpSession session = request.getSession();
    } 

    public void destroy() {
        log.info("SessionCounter destroy!");
    } 

    public void requestDestroyed(ServletRequestEvent event) {
        //To change body of implemented methods use File | Settings | File Templates.
        log.info("SessionCounter requestDestroyed!");
    } 

    public void requestInitialized(ServletRequestEvent sre){
        request=(HttpServletRequest)sre.getServletRequest();
        log.info("SessionCounter requestInitialized!");
    } 

    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        log.info("SessionCounter sessionCreater!");
        String sessionId = httpSessionEvent.getSession().getId();
        Timestamp createTime = new Timestamp(System.currentTimeMillis());
        String loginIp = request.getRemoteAddr();
        boolean rs = true;
        if(list.size() > 0){
            for(int i = 0;i < list.size(); i ++){
                if(loginIp.equals(list.get(i))){
                    rs = false;
                }
            }
        }
        if(rs){                      //如果队列中存在相同的IP 则SESSION不增加
            list.add(loginIp);
           log.info("ipList队列新增ip: "+loginIp);
            activeSessions++;
            log.info("新增SESSION,sessionId = " + sessionId +"; createTime = " + createTime
                             + "; loginIp = " + loginIp +"; 当前总SESSION值为 "+activeSessions);
        }
    } 

    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        log.info("SessionCounter sessionDestroyed!");
        String sessionId = httpSessionEvent.getSession().getId();
        Timestamp overTime = new Timestamp(System.currentTimeMillis());
        String loginIp = request.getRemoteAddr();
        if(activeSessions>0){
            if(list.size() > 0){
                for(int i = 0;i < list.size(); i ++){
                    if(loginIp.equals(list.get(i))){
                        list.remove(i);  
                        log.info("ipList队列移除ip: "+loginIp);
                    }
                }
            }
            activeSessions--;                   //在用户销毁的时候,从队列中踢出这个IP
            log.info("销毁SESSION,sessionId = " + sessionId +"; overTime = " + overTime
                             + "; loginIp = " + loginIp +"; 当前总SESSION值为 "+activeSessions);
        }
    } 

    public static int getActiveSessions() {
        log.info("SessionCounter getActiveSessions!");
        return activeSessions;
    } 

    public void setActiveSessions(int i) {
        log.info("SessionCounter setActiveSessions!");
        activeSessions = i;
    } 

} 