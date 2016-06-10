package javacommon.filter;

import java.io.IOException;

import javax.jms.Session;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
	
		HttpServletRequest request=(HttpServletRequest) req;
		HttpSession session=request.getSession();
		HttpServletResponse response=(HttpServletResponse) res;
		String uri = request.getRequestURI();
		String actionName = uri.substring(uri.lastIndexOf("/") + 1);
		if("login.jsp".equals(actionName) || "login.do".equals(actionName) || (!"login.do".equals(actionName) && (session != null ||session.getAttribute("loginUser")!=null))){
			chain.doFilter(request, response);
		}else{
			response.sendRedirect(request.getContextPath()+"/login.jsp");
		}
			
	}

	public void destroy() {
		// TODO Auto-generated method stub

	}
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

}
