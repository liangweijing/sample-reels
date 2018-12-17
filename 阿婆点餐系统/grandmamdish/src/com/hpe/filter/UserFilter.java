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

import com.hpe.entity.Users;




public class UserFilter implements Filter {

   
    public UserFilter() {
        // TODO Auto-generated constructor stub
    }

	
	public void destroy() {
		// TODO Auto-generated method stub
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req=(HttpServletRequest) request;
		HttpServletResponse resp=(HttpServletResponse) response;
		Users user=(Users) req.getSession().getAttribute("user");
		
		
		//System.out.println("*******8"+user);
		
		String path=req.getServletPath();
		//System.out.println("过滤的路径是"+path);
		if(null!=user || "/User/index.jsp".equals(path)||"/User/UserRegist.jsp".equals(path) || path.endsWith(".js") || path.endsWith(".css") || path.endsWith(".jpg") || path.endsWith(".png")) {
			chain.doFilter(request, response);
		}else {
			resp.getWriter().write("<script>alert('请登录');</script>");
			resp.sendRedirect("/grandmamdish/User/UserLogin.jsp");
		}
				
	
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
