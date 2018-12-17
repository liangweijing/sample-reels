package com.hpe.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hpe.entity.Admin;

/**
 * Servlet Filter implementation class AdminFilter
 */
public class AdminFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AdminFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest)request;
		
		HttpServletResponse resp = (HttpServletResponse)response;
		
		//获取servlet路径，就是文件夹名/文件名
		String path = req.getServletPath();
		//System.out.println("req.getServletPath():"+path);
		
		Admin admin =(Admin) req.getSession().getAttribute("admin");
		//在访问一切除了下面这几种情况之外，如果用户为null则转跳到登录页，否则放行。这样无法直接点击main.jsp看到能容了
		if(admin != null || "/Administration/indext.jsp".equals(path) || path.endsWith(".js") || path.endsWith(".css") || path.endsWith(".jpg") || path.endsWith(".gif")){
			chain.doFilter(request, response);
		}else{
			//重定向到登录页(在首页登录时要放行，不登录肯定一直是null，样式类文件放行)
			resp.sendRedirect("/grandmamdish/Administration/indext.jsp");
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
