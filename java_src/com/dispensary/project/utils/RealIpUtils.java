package com.dispensary.project.utils;



import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

/**
 * Created with IntelliJ IDEA.
 * User: R
 * Date: 13-8-1
 * Time: 上午11:34
 * To change this template use File | Settings | File Templates.
 */
public class RealIpUtils {

    /**
     * 取所有IP段的私有IP段
     * A类  私有地址  10.0.0.0---10.255.255.255  保留地址 127.0.0.0---127.255.255.255
     * B类  私有地址 172.16.0.0-172.31.255.255
     * C类  私有地址 192.168.0.0-192.168.255.255
     * D类  地址不分网络地址和主机地址
     * E类  地址不分网络地址和主机地址
     */
    private static long aBegin = getIpNum("10.0.0.0");
    private static long aEnd = getIpNum("10.255.255.255");
    private static long bBegin = getIpNum("172.16.0.0");
    private static long bEnd = getIpNum("172.31.255.255");
    private static long cBegin = getIpNum("192.168.0.0");
    private static long cEnd = getIpNum("192.168.255.255");
    private static long saveBegin = getIpNum("127.0.0.0");
    private static long saveEnd = getIpNum("127.255.255.255");

    //跟IP有关需要做判断的header参数
    private static Set<String> ipHeaderNames = new HashSet<String>(){
        {
            add("clientip");//终端的IP即终端上网时动态分配的IP
            add("x-forwarded-for");//简称XFF头，它代表客户端，也就是HTTP的请求端真实的IP
            add("proxy-client-ip");//代理客服端IP
            add("wl-proxy-client-ip");//WebLogic代理客服端IP（weblogic设置了才会有这个参数）
        }
    };

    public static String getRealIpAddr(HttpServletRequest request) {

        System.out.println("-------------getRealIpAddr start---------------------");

        //防止在header中的参数名有大小写之分，重新将需要处理的参数值和内容装填入Map中
        Map<String,String> ipHeaders = new HashMap<String,String>();
        Enumeration<String> headerNames = request.getHeaderNames();
        StringBuilder headerNamesStr = new StringBuilder();
        while (headerNames.hasMoreElements()){
            String nowName = headerNames.nextElement();
            headerNamesStr.append(nowName+",");
            if(ipHeaderNames.contains(nowName.toLowerCase())){
                ipHeaders.put(nowName.toLowerCase(),request.getHeader(nowName));//装填key和value
            }
        }

        System.out.println("headerNamesStr : "+headerNamesStr);

        //取正确的IP
        String ipAddress = null;
        ipAddress = ipHeaders.get("clientip");//取clientip与client-ip有区别

        System.out.println("clientip:"+ipAddress);

        if(ipAddress!=null&&isInnerIP(ipAddress)){//若取得的clientip是内网IP，则置为0
            ipAddress = null;
        }

        //若clientip为空或者是内网IP则取x-forwarded-for
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)){
            String xForwardedIpAddress = ipHeaders.get("x-forwarded-for");
            System.out.println("x-forwarded-for:"+ipAddress);
            //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (StringUtils.isNotEmpty(xForwardedIpAddress)) {
                String[] ipList = xForwardedIpAddress.split(",");
                for (String nowIp : ipList) {
                    if(isIpAddress(nowIp)&&!isInnerIP(nowIp)){//取第一个不是内网IP的IP作为真实IP
                        ipAddress = nowIp;
                        break;
                    }
                }
            }
        }

        //若x-forwarded-for为空则取proxy-client-ip
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = ipHeaders.get("proxy-client-ip");
            System.out.println("proxy-client-ip:"+ipAddress);
        }

        //若proxy-client-ip为空则取wl-proxy-client-ip
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = ipHeaders.get("wl-proxy-client-ip");
            System.out.println("wl-proxy-client-ip:"+ipAddress);
        }

        //若wl-proxy-client-ip为空则取RemoteAddr
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            System.out.println("remote-addr:"+ipAddress);
            if (ipAddress.equals("127.0.0.1")) {
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress = inet.getHostAddress();
            }
        }

        System.out.println("real-ip:"+ipAddress);

        System.out.println("-------------getRealIpAddr end---------------------");

        return ipAddress;
    }


    public static boolean isInnerIP(String ipAddress) {
        boolean isInnerIp = false;
        long ipNum = getIpNum(ipAddress);
        isInnerIp = isInner(ipNum, aBegin, aEnd) || isInner(ipNum, bBegin, bEnd) || isInner(ipNum, cBegin, cEnd) || isInner(ipNum, saveBegin, saveEnd);
        return isInnerIp;
    }

    private static long getIpNum(String ipAddress) {
        String[] ip = ipAddress.split("\\.");
        long a = Integer.parseInt(ip[0]);
        long b = Integer.parseInt(ip[1]);
        long c = Integer.parseInt(ip[2]);
        long d = Integer.parseInt(ip[3]);

        long ipNum = a * 256 * 256 * 256 + b * 256 * 256 + c * 256 + d;
        return ipNum;
    }

    private static boolean isInner(long userIp, long begin, long end) {
        return (userIp >= begin) && (userIp <= end);
    }


    /**
     * 检验是否是合法的IP地址 * @param address String IP地址 * @return boolean IP地址是否合法
     */
    public static boolean isIpAddress(String address) {
        if(StringUtils.isEmpty(address)) return false;
        String regex = "(((2[0-4]d)|(25[0-5]))|(1d{2})|([1-9]d)|(d))[.](((2[0-4]d)|(25[0-5]))|(1d{2})|([1-9]d)|(d))[.]"
                + "(((2[0-4]d)|(25[0-5]))|(1d{2})|([1-9]d)|(d))[.](((2[0-4]d)|(25[0-5]))|(1d{2})|([1-9]d)|(d))";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(address);
        return m.matches();
    }
}

