/*
 */

package com.dispensary.project.action;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.management.loading.PrivateClassLoader;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.dispatcher.RequestMap;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

import cn.org.rapid_framework.beanutils.BeanUtils;
import cn.org.rapid_framework.web.scope.Flash;

import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.ModelDriven;

import java.util.*;

import javacommon.base.*;
import javacommon.excel.Excel;
import javacommon.excel.Files;
import javacommon.util.*;
import cn.org.rapid_framework.util.*;
import cn.org.rapid_framework.web.util.*;
import cn.org.rapid_framework.page.*;
import cn.org.rapid_framework.page.impl.*;

import com.dispensary.project.model.*;
import com.dispensary.project.dao.*;
import com.dispensary.project.service.*;
import com.dispensary.project.utils.FunctionLib;
import com.dispensary.project.utils.MySession;
import com.dispensary.project.vo.query.*;
import com.google.gson.Gson;

import javax.annotation.Resource;

public class PatiCaseHistoryAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = "visitDate desc"; 
	
	//forward paths
	
	protected static final String QUERY_JSP = "/pages/PatiCaseHistory/query.jsp";
	protected static final String LIST_JSP= "/pages/PatiCaseHistory/list.jsp";
	protected static final String CREATE_JSP = "/pages/PatiCaseHistory/create.jsp";
	protected static final String EDIT_JSP = "/pages/PatiCaseHistory/edit.jsp";
	protected static final String SHOW_JSP = "/pages/PatiCaseHistory/show.jsp";
	protected static final String CONFIRM_JSP = "/pages/Treatment/prescription_view.jsp";
	protected static final String SHOW_COL_CHART="/pages/PatiCaseHistory/showColChart.jsp";
	protected static final String SHOW_PIE_CHART="/pages/PatiCaseHistory/showPieChart.jsp";
	protected static final String SHOW_LINE_CHART="/pages/PatiCaseHistory/showLineChart.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/pages/PatiCaseHistory/list.do";
	protected static final String VISIT_JSP = "!/pages/Treatment/visit.jsp";
	private PatiCaseHistoryManager patiCaseHistoryManager;
	@Resource private PrescriptionInfoManager prescriptionInfoManager;
	@Resource private PrescriptionInfoDetailManager prescriptionInfoDetailManager;
	@Resource private StudentManager studentManager;
	@Resource private DispensarystaffManager dispensarystaffManager;
	@Resource private DrugBasicInfoManager drugBasicInfoManager;
	private PatiCaseHistory patiCaseHistory;
	java.lang.Integer id = null;
	private String[] items;
	private Set<PrescriptionInfoDetail> prescriptionInfoDetails;
	public Set<PrescriptionInfoDetail> getPrescriptionInfoDetails() {
		return prescriptionInfoDetails;
	}
	public void setPrescriptionInfoDetails(
			Set<PrescriptionInfoDetail> prescriptionInfoDetails) {
		this.prescriptionInfoDetails = prescriptionInfoDetails;
	}
	
	private Set<PrescriptionInfo> prescriptionInfos=new HashSet<PrescriptionInfo>();
	public Set<PrescriptionInfo> getPrescriptionInfos() {
		return prescriptionInfos;
	}

	public void setPrescriptionInfos(Set<PrescriptionInfo> prescriptionInfos) {
		this.prescriptionInfos = prescriptionInfos;
	}
	public void prepare() throws Exception {
		patiCaseHistory = new PatiCaseHistory();
		//System.out.println(this.prescriptionInfoDetails.size());
		/*if (isNullOrEmptyString(id)) {
			patiCaseHistory = new PatiCaseHistory();
		} else {
			patiCaseHistory = (PatiCaseHistory)patiCaseHistoryManager.getById(id);
		}*/
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setPatiCaseHistoryManager(PatiCaseHistoryManager manager) {
		this.patiCaseHistoryManager = manager;
	}	
	
	public Object getModel() {
		return patiCaseHistory;
	}
	
	public void setId(java.lang.Integer val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	
	
	/** 执行搜索 */
	public String list() {
		PatiCaseHistoryQuery query = newQuery(PatiCaseHistoryQuery.class,DEFAULT_SORT_COLUMNS);
		
		Page page = patiCaseHistoryManager.findPage(query);
		savePage(page,query);
		return LIST_JSP;
	}
	/** 执行搜索 */
	public String search(){
		PatiCaseHistoryQuery caseQuery=new PatiCaseHistoryQuery();
		String searchType=getRequest().getParameter("searchType");
		String searchInfo=getRequest().getParameter("searchInfo").trim();
		try {
			searchInfo=java.net.URLDecoder.decode(searchInfo,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(null!=searchInfo&&!searchInfo.equals("")){
			if("caseId".equals(searchType)){
				caseQuery.setCaseId(searchInfo);
			}
			if("stuNum".equals(searchType)){
				caseQuery.setStuNum(searchInfo);
			}
			if("meStId".equals(searchType)){
				if(FunctionLib.isNum(searchInfo)){
					caseQuery.setMeStId(Integer.parseInt(searchInfo));
				}
			}
			if("meStName".equals(searchType)){
				caseQuery.setMeStIdModelTag(searchInfo);
			}
			if("visitDate".equals(searchType)){
				SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
				Date date=null;
				try {
					date = format.parse(searchInfo);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				caseQuery.setVisitDateString(date);
			}
		}
		Page page = patiCaseHistoryManager.findPage(caseQuery);
		savePage(page,caseQuery);
		return LIST_JSP;
	}
	//查询学生病例
	public void searchStuPaitCard(){
		PatiCaseHistoryQuery patiCaseHistoryQuery=new PatiCaseHistoryQuery();
		patiCaseHistoryQuery.setStuNum(patiCaseHistory.getStuNum());
		List<PatiCaseHistory> list=patiCaseHistoryManager.findAll(patiCaseHistoryQuery);
		JSONArray jsonArray = new JSONArray();
		PrintWriter out=null;
		try {
			out = getResponse().getWriter();
			for(int i=0;i<list.size();i++){
				PatiCaseHistory pch=(PatiCaseHistory) list.get(i);
				PatiCaseHistoryQuery query=new PatiCaseHistoryQuery();
				BeanUtils.copyProperties(query, pch);
				JSONObject jsonobj = JSONObject.fromObject(query);
				jsonArray.add(jsonobj);
			}
			out.println(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	/** 查看对象*/
	public String show() {
		return SHOW_JSP;
	}
	
	/** 进入新增页面*/
	public String create() {
		getRelative();
		return CREATE_JSP;
	}
	
	/** 保存新增对象 */
	public String save() {
		try {
			//得到处方号
			int presId=prescriptionInfoManager.getPresId();
			//得到病历号
			String cardId=patiCaseHistoryManager.getCardId(new Date());
			Iterator<PrescriptionInfo> news=prescriptionInfos.iterator();
			PrescriptionInfo newPres=null;
			while(news.hasNext()){
				newPres=news.next();
				newPres.setPresId(null);
				newPres.setCaseId(cardId);
				newPres.setPersDate(new Date());
				newPres.setState(0);
				//添加处方明细处方号
				Iterator<PrescriptionInfoDetail> iterator=prescriptionInfoDetails.iterator();
				while(iterator.hasNext()){
					PrescriptionInfoDetail detail=iterator.next();
					detail.setId(null);
					detail.setPresId(presId);
				}
				newPres.setPrescriptionInfoDetails(prescriptionInfoDetails);
			}
			//添加处方单到病历
			Set<PrescriptionInfo> presSet=new HashSet<PrescriptionInfo>();
			presSet.add(newPres);
			patiCaseHistory.setPrescriptionInfos(presSet);
			patiCaseHistory.setCaseId(cardId);
			patiCaseHistory.setVisitDate(new Date());
			MySession mySession=(MySession)getRequest().getSession().getAttribute("MySession");
			Userinfo user=null;
			if(null!=mySession&&mySession.getUserinfo()!=null){
				user=mySession.getUserinfo();
			}
			if(user.getMeStId()!=null){
				patiCaseHistory.setMeStId(user.getMeStId());
			}
			//添加病历到学生
			Set<PatiCaseHistory> caseSet=new HashSet<PatiCaseHistory>();
			caseSet.add(patiCaseHistory);
			//保存病历
			Student student=studentManager.getById(patiCaseHistory.getStuNum());
			student.setPatiCaseHistorys(caseSet);
			studentManager.save(student);
			PrintWriter out = null;
			try {
				out = getResponse().getWriter();
				out.println("SUCCESS");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (Exception e) {
			// TODO: handle exception
			showMessage2("提交处方单失败！","/pages/PatiCaseHistory/list.do");
		}

		return null;
	}

	/**进入更新页面*/
	public String edit() {
		getRelative();
		String id=getRequest().getParameter("id");
		if(id!=null){
			PatiCaseHistory pch=patiCaseHistoryManager.getById(Integer.parseInt(id));
			getRequest().setAttribute("pch", pch);
		}
		return EDIT_JSP;
	}
	/**保存更新对象*/
	public String update() {
		patiCaseHistoryManager.update(this.patiCaseHistory);
		Flash.current().success(UPDATE_SUCCESS);
		return LIST_ACTION;
	}
	/**删除对象*/
	public String delete(){
		String id=getRequest().getParameter("id");
		if(id!=null){
			patiCaseHistoryManager.removeById(Integer.parseInt(id));
		}
		//showMessage("删除成功！");
		return LIST_ACTION;
	}
	/**删除对象*/
	public String delBatch() {
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			java.lang.Integer id = new java.lang.Integer((String)params.get("id"));
			patiCaseHistoryManager.removeById(id);
		}
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}
	public void getRelative(){
		List<Student> studentList =  studentManager.findAll();
		requestMap.put("studentList",studentList);
		List<Dispensarystaff> dispensarystaffList =  dispensarystaffManager.findAll();
		requestMap.put("dispensarystaffList",dispensarystaffList);
		List<DrugBasicInfo> drugBasicInfoList=drugBasicInfoManager.findAll();
		requestMap.put("drugBasicInfoList", drugBasicInfoList);
	}
	public String getCaseJSON(){
		JSONArray jsonArray = new JSONArray();
		if(patiCaseHistory.getId()!=null){
			PatiCaseHistory pch =patiCaseHistoryManager.getById(patiCaseHistory.getId());
			Student stu=pch.getStuNumModel();
			StudentQuery stuQuery = new StudentQuery();
			BeanUtils.copyProperties(stuQuery, stu);
			JSONObject stuJson = JSONObject.fromObject(stuQuery);
			jsonArray.add(stuJson);
			PrescriptionInfoQuery presQuery=new PrescriptionInfoQuery();
			presQuery.setCaseId(pch.getCaseId());
			List<PrescriptionInfo> presList=prescriptionInfoManager.findAll(presQuery);
			if(presList.size()==1){
				PrescriptionInfo pres=presList.get(0);
				BeanUtils.copyProperties(presQuery, pres);
				JSONObject presJson = JSONObject.fromObject(presQuery);
				jsonArray.add(presJson);
				Set<PrescriptionInfoDetail> detail=pres.getPrescriptionInfoDetails();
				Iterator<PrescriptionInfoDetail> iterator=detail.iterator();
				while(iterator.hasNext()){
					PrescriptionInfoDetailQuery query=new PrescriptionInfoDetailQuery();
					BeanUtils.copyProperties(query, iterator.next());
					jsonArray.add(query);
				}
			}			
		}
		PrintWriter out = null;
		try {
			out = getResponse().getWriter();
			out.println(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public void getCaseReport() throws IOException {
		 // 创建当前日子
	     Date date = new Date();
	     // 格式化日期 
	     SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	     // 格式化日期(产生文件名)
	     String filename = sdf.format(date);
	     // 获得ServletContext对象
	     ServletContext servletContext = getRequest().getServletContext();
	     String realpath=servletContext.getRealPath("download") + "\\" + filename+ ".xls";
	     System.out.println(realpath);
	     // 创建文件
	     File f = new File(realpath);
		 try {
			f.createNewFile();
		 } catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		 }
	  
	      //标题数组
		  String title[] = {PatiCaseHistory.ALIAS_CASE_ID,PatiCaseHistory.ALIAS_STU_NUM,PatiCaseHistory.ALIAS_VISIT_DATE,
				  PatiCaseHistory.ALIAS_DISPENSARY_RECORD,PatiCaseHistory.ALIAS_ALLERGY,PatiCaseHistory.ALIAS_PRIMARY_CARE,
				  PatiCaseHistory.ALIAS_RE_EXAMINATION,PatiCaseHistory.ALIAS_DESCRIBES,PatiCaseHistory.ALIAS_EXAMINE_STATUS,PatiCaseHistory.ALIAS_FIRST_IMPRESS,PatiCaseHistory.ALIAS_ME_ST_ID};
		  List<List> lists = new ArrayList<List>();
		  //组成list
		  List<PatiCaseHistory> caseList = patiCaseHistoryManager.findAll();
		  Iterator it = caseList.iterator();
		  while (it.hasNext()) {
			  PatiCaseHistory pch =(PatiCaseHistory) it.next();
			  List<String> list = new ArrayList<String>();
			  list.add(pch.getCaseId()+"");
			  list.add(pch.getStuNum()+"");
			  list.add(pch.getVisitDateString());
			  list.add(pch.getDispensaryRecord()+"");
			  list.add(pch.getAllergy()+"");
			  list.add(pch.getPrimaryCare()+"");
			  list.add(pch.getReExamination()+"");
			  list.add(pch.getDescribes()+"");
			  list.add(pch.getExamineStatus()+"");
			  list.add(pch.getFirstImpress()+"");
			  list.add(pch.getMeStIdModel().getModelTagValue()+"");
			  lists.add(list);
		}
	  
	  // 生成excel文件(保存在服务器机上)
	  try {
		  Excel.writeExcel(new FileOutputStream(f), title, lists);
		  Files.exportFile(getResponse(), f, true);
	  	} catch (Exception e) {
	   // TODO Auto-generated catch block
	   e.printStackTrace();
	  }	
	}
	
	
	//统计学生就诊的病历柱形图
	public String bulidColChart() throws Exception {
    	/*PatiCaseHistoryQuery pchQuery=new PatiCaseHistoryQuery();
    	SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
    	String begin=getRequest().getParameter("visitDateBegin");
    	String end=getRequest().getParameter("visitDateEnd");
    	Date visitDateBegin=null;
    	Date visitDateEnd=null;
    	if(begin!=null&&!"".equals(begin)){
    		visitDateBegin=format.parse(begin);
    		pchQuery.setVisitDateBegin(visitDateBegin);
    	}
    	if(end!=null&&!"".equals(end)){
    		visitDateEnd=format.parse(end);
    		pchQuery.setVisitDateEnd(visitDateEnd);
    	}*/
    	
		CategoryDataset dataset = patiCaseHistoryManager.getCategoryDataSet();
        JFreeChart chart = ChartFactory.createBarChart3D( 
                           "医疗所每月就诊统计", // 图表标题
                           "就诊", // 目录轴的显示标签
                           "就诊数量", // 数值轴的显示标签
                            dataset, // 数据集
                            PlotOrientation.VERTICAL, // 图表方向：水平、垂直
                            true,  // 是否显示图例(对于简单的柱状图必须是 false)
                            true, // 是否生成工具
                            false  // 是否生成 URL 链接
                            ); 
                           
        FileOutputStream fos_png = null; 
        try { 
            fos_png = new FileOutputStream(getRequest().getServletContext().getRealPath("temp\\chart") + "\\colChart.png"); 
            ChartUtilities.writeChartAsJPEG(fos_png,1.0f,chart,1366,768,null); 
        } finally { 
            try { 
            	fos_png.close(); 
            } catch (Exception e) {} 
        }
        getRequest().setAttribute("chart", "colChart.png");
        return SHOW_COL_CHART;
    }
	public String bulidPieChart() throws Exception{
		/*PatiCaseHistoryQuery pchQuery=new PatiCaseHistoryQuery();
    	SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
    	String begin=getRequest().getParameter("visitDateBegin");
    	String end=getRequest().getParameter("visitDateEnd");
    	Date visitDateBegin=null;
    	Date visitDateEnd=null;
    	if(begin!=null&&!"".equals(begin)){
    		visitDateBegin=format.parse(begin);
    		pchQuery.setVisitDateBegin(visitDateBegin);
    	}
    	if(end!=null&&!"".equals(end)){
    		visitDateEnd=format.parse(end);
    		pchQuery.setVisitDateEnd(visitDateEnd);
    	}*/
		//设置数据源   
        PieDataset pieDataset = patiCaseHistoryManager.getPieDataSet(); 
        // 生产3D饼状图  
        PiePlot3D plot = new PiePlot3D(pieDataset);  
        JFreeChart chart = new JFreeChart(  
                "医疗所每月就诊统计",            // 图形标题  
                JFreeChart.DEFAULT_TITLE_FONT, // 标题字体  
                plot,                          // 图标标题对象  
                true                           // 是否显示图例  
        );  
        // 设置整个图片的背景色  
        //chart.setBackgroundPaint(Color.PINK);  
        // 设置图片有边框  
        //chart.setBorderVisible(true);  
        // 配置字体  
        Font kfont = new Font("宋体", Font.PLAIN, 12);    // 底部  
        Font titleFont = new Font("宋体", Font.BOLD, 25); // 图片标题  
        // 图片标题  
        chart.setTitle(new TextTitle(chart.getTitle().getText(), titleFont));  
        // 底部  
        chart.getLegend().setItemFont(kfont);  
        FileOutputStream fos_png = null; 
        try { 
            fos_png = new FileOutputStream(getRequest().getServletContext().getRealPath("temp\\chart") + "\\pieChart.png"); 
            ChartUtilities.writeChartAsJPEG(fos_png,1.0f,chart,1366,768,null); 
        } finally { 
            try { 
            	fos_png.close(); 
            } catch (Exception e) {} 
        }
        getRequest().setAttribute("chart", "pieChart.png");
		return SHOW_PIE_CHART;
	}
	/*用于jschart数据集
	 public String bulidPieChart(){
		List list = patiCaseHistoryManager.getPchData();
		JSONArray jsonArray = new JSONArray();
		for (int i = 0; i < list.size(); i++) {
			Object[] obj = (Object[])list.get(i);
			System.out.println(obj[1]);
			jsonArray.add(obj[1]);
		}
		PrintWriter out = null;
		try {
			out = getResponse().getWriter();
			out.println(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}*/
	public String bulidLineChart() throws Exception{
		/*PatiCaseHistoryQuery pchQuery=new PatiCaseHistoryQuery();
    	SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
    	String begin=getRequest().getParameter("visitDateBegin");
    	String end=getRequest().getParameter("visitDateEnd");
    	Date visitDateBegin=null;
    	Date visitDateEnd=null;
    	if(begin!=null&&!"".equals(begin)){
    		visitDateBegin=format.parse(begin);
    		pchQuery.setVisitDateBegin(visitDateBegin);
    	}
    	if(end!=null&&!"".equals(end)){
    		visitDateEnd=format.parse(end);
    		pchQuery.setVisitDateEnd(visitDateEnd);
    	}*/
        // 时间序列对象集合  
        TimeSeriesCollection chartTime = new TimeSeriesCollection();  
        chartTime.addSeries(patiCaseHistoryManager.getLineDateSet());  
        XYDataset date = chartTime;  
        try {  
            // 使用ChartFactory来创建时间序列的图表对象  
            JFreeChart chart = ChartFactory.createTimeSeriesChart(  
                    "医疗所每月就诊统计", // 图形标题  
                    "日期", // X轴说明  
                    "就诊数", // Y轴说明  
                    date, // 数据  
                    true, // 是否创建图例  
                    true, // 是否生成Tooltips  
                    false // 是否生产URL链接  
            );  
            // 设置整个图片的背景色  
            //chart.setBackgroundPaint(Color.PINK);  
            // 设置图片有边框  
            //chart.setBorderVisible(true);  
            // 获得图表区域对象  
            XYPlot xyPlot = (XYPlot) chart.getPlot();  
            // 设置报表区域的背景色  
            //xyPlot.setBackgroundPaint(Color.lightGray);  
            // 设置横 纵坐标网格颜色  
            //xyPlot.setDomainGridlinePaint(Color.GREEN);  
            //xyPlot.setRangeGridlinePaint(Color.GREEN);  
            // 设置横、纵坐标交叉线是否显示  
            xyPlot.setDomainCrosshairVisible(true);  
            xyPlot.setRangeCrosshairVisible(true);  
            // 获得数据点（X,Y）的render，负责描绘数据点  
            XYItemRenderer xyItemRenderer = xyPlot.getRenderer();  
            if (xyItemRenderer instanceof XYLineAndShapeRenderer) {  
                XYLineAndShapeRenderer xyLineAndShapeRenderer = (XYLineAndShapeRenderer) xyItemRenderer;  
                xyLineAndShapeRenderer.setShapesVisible(true); // 数据点可见  
                xyLineAndShapeRenderer.setShapesFilled(true); // 数据点是实心点  
                xyLineAndShapeRenderer.setSeriesFillPaint(0, Color.RED); // 数据点填充为蓝色  
                xyLineAndShapeRenderer.setUseFillPaint(true);// 将设置好的属性应用到render上  
            }  
            // 配置以下内容方可解决乱码问题  
            // 配置字体  
            Font xfont = new Font("宋体", Font.PLAIN, 12);    // X轴  
            Font yfont = new Font("宋体", Font.PLAIN, 12);    // Y轴  
            Font kfont = new Font("宋体", Font.PLAIN, 12);    // 底部  
            Font titleFont = new Font("宋体", Font.BOLD, 25); // 图片标题  
            // 图片标题  
            chart.setTitle(new TextTitle(chart.getTitle().getText(), titleFont));  
            // 底部  
            chart.getLegend().setItemFont(kfont);             
            // X 轴  
            ValueAxis domainAxis = xyPlot.getDomainAxis();  
            domainAxis.setLabelFont(xfont);// 轴标题  
            domainAxis.setTickLabelFont(xfont);// 轴数值  
            domainAxis.setTickLabelPaint(Color.BLUE); // 字体颜色  
            // Y 轴  
            ValueAxis rangeAxis = xyPlot.getRangeAxis();  
            rangeAxis.setLabelFont(yfont);  
            rangeAxis.setLabelPaint(Color.BLUE); // 字体颜色  
            rangeAxis.setTickLabelFont(yfont);  
            // 定义坐标轴上日期显示的格式  
            DateAxis dateAxis = (DateAxis) xyPlot.getDomainAxis();  
            // 设置日期格式  
            dateAxis.setDateFormatOverride(new SimpleDateFormat("yyyy-MM-dd"));  
            // 向客户端输出生成的图片  
            FileOutputStream fos_png = null; 
            try { 
                fos_png = new FileOutputStream(getRequest().getServletContext().getRealPath("temp\\chart") + "\\lineChart.png"); 
                ChartUtilities.writeChartAsJPEG(fos_png,1.0f,chart,1366,768,null); 
            } finally { 
                try { 
                	fos_png.close(); 
                } catch (Exception e) {} 
            }
            getRequest().setAttribute("chart", "lineChart.png");
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
		
		return SHOW_LINE_CHART;
	}
	public String showMonthVisitsChart(){
		getResponse().setContentType("text/html;charset=utf-8");
		PrintWriter out=null;
		String type=getRequest().getParameter("type");
		try {
			out = getResponse().getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		List<String> legend = new ArrayList<String>(Arrays.asList(new String[]{"每月就诊数"}));//数据分组
		List<String> category = new ArrayList<String>(Arrays.asList(new String []{"1月份","2月份","3月份","4月份","5月份","6月份","7月份","8月份","9月份","10月份","11月份","12月份"}));//横坐标
		List<Series> series = new ArrayList<Series>();//纵坐标
		
		//series.add(new Series("每月就诊数", "line", new ArrayList<Integer>(Arrays.asList(21,23,28,26,21,33,44))));
		series.add(new Series("每月就诊数",type,patiCaseHistoryManager.getMonthVisits()));
		EchartData data=new EchartData(legend, category, series);
		Gson gosn = new Gson();
		System.out.println(gosn.toJson(data));
		out.write(gosn.toJson(data));
		return null;
	}
	public String showMonthVisitsPieChart(){
		getResponse().setContentType("text/html;charset=utf-8");
		PrintWriter out=null;
		try {
			out = getResponse().getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		List<String> legend = new ArrayList<String>(Arrays.asList(new String[]{"每月就诊数"}));//数据分组
		List<String> category = new ArrayList<String>(Arrays.asList(new String []{"1月份","2月份","3月份","4月份","5月份","6月份","7月份","8月份","9月份","10月份","11月份","12月份"}));//横坐标
		List<Series> series = new ArrayList<Series>();//纵坐标
		
		//series.add(new Series("每月就诊数", "line", new ArrayList<Integer>(Arrays.asList(21,23,28,26,21,33,44))));
		series.add(new Series("每月就诊数", "pie",patiCaseHistoryManager.getMonthVisitsPie()));
		EchartData data=new EchartData(legend, category, series);
		Gson gosn = new Gson();
		System.out.println(gosn.toJson(data));
		out.write(gosn.toJson(data));
		return null;
	}
}
