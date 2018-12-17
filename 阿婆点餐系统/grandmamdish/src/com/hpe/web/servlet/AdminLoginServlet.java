package com.hpe.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hpe.entity.Admin;
import com.hpe.entity.MenusOrder;
import com.hpe.service.BackStageService;
import com.hpe.utils.Page;

/**
 * Servlet implementation class AdminLogin
 */
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    BackStageService bss=new BackStageService();
    public static Admin admin=new Admin(); //一直都要用这个登录的管理员的属性，干脆设一个静态的对象
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)  {
		// TODO Auto-generated method stub
		ServletContext servletContext=this.getServletContext();
		String methods=request.getParameter("methods");
		servletContext.setAttribute("status", " ");
		if("update".equals(methods)) {
			adminUpdate(request,response,servletContext);
		}else if("login".equals(methods)) {
			adminLogin(request,response,servletContext);
		}else if("queryOrderById".equals(methods)) {
			queryOrderById(request,response);
		}else if("search".equals(methods)) {
			searchOrder(request,response);
		}else if("statistic".equals(methods)) {
			statisticOrder(request,response);
		}else if("sales".equals(methods)) {
			getTotalSales(request,response);
		}else if("logintest".equals(methods)) {
			logintest(request,response);
		}
		
		
	}


	private void logintest(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String adminName = request.getParameter("changename");
		System.out.println("slhdi  "+adminName);
		int result=bss.logintest(adminName);
		System.out.println("result="+result);
		try {
			if(result>0)
				response.getWriter().print(result);//数字要用print不能write
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void getTotalSales(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int sales=bss.getTotalSales();
		this.getServletContext().setAttribute("sales", sales);
		try {
			request.getRequestDispatcher("/Administration/order_statistic.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void statisticOrder(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		//获取当前页
		String curPage = request.getParameter("curPage");
		//获取每页条数
		String pageNumber=request.getParameter("pageNumber");
		
		if(curPage==null) {
			curPage="1";//没传查哪一页则默认第一页
		}
		if(pageNumber==null) {
			pageNumber="6";//没传一页显示几条则默认6条
		}
		Page<MenusOrder> page=bss.statisticOrder(Integer.parseInt(curPage), Integer.parseInt(pageNumber));
		request.setAttribute("page", page);
		try {
			request.getRequestDispatcher("/Administration/order_statistic.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void searchOrder(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String userid=request.getParameter("userid");
		String menuname=request.getParameter("menuname");
		String date=request.getParameter("date");
		System.out.println("chuanrulema"+userid);
		List<MenusOrder> list=bss.queryOrder(userid,menuname,date);
		request.setAttribute("list", list);
		try {
			request.getRequestDispatcher("/Administration/order_search.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private void queryOrderById(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		//获取当前页
				String curPage = request.getParameter("curPage");
				//获取每页条数
				String pageNumber=request.getParameter("pageNumber");
				
				if(curPage==null) {
					curPage="1";//没传查哪一页则默认第一页
				}
				if(pageNumber==null) {
					pageNumber="6";//没传一页显示几条则默认6条
				}
		Page<MenusOrder> page=bss.queryOrderById(Integer.parseInt(curPage), Integer.parseInt(pageNumber));
		request.setAttribute("page", page);
		try {
			request.getRequestDispatcher("/Administration/order.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 *    管理员登录
	 */
	private void adminLogin(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext){
		// TODO Auto-generated method stub
		String adminName = request.getParameter("adminName");
		String adminpwd=request.getParameter("adminpwd");
		
		admin=bss.AdminLogin(adminName, adminpwd);
		if(null!=admin) {
			
			
			request.getSession().setAttribute("admin", admin);//无法在main_top.jsp中获取此admin属性，因为不是同一个请求了
			//这里转发到main.jsp又链接到main_top.jsp所以用与天地同寿所有servlet（.jsp也是一个servlet）都看得到的ServletContext
			try {
				request.getRequestDispatcher("/Administration/main.jsp").forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			servletContext.setAttribute("status", "false");
			try {
				request.getRequestDispatcher("/Administration/indext.jsp").forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void adminUpdate(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)  {
		// TODO Auto-generated method stub
		String adminName = request.getParameter("adminName");
		String adminpwd=request.getParameter("adminpwd");
		System.out.println(admin.getAdminName());
		admin=bss.AdminUpdateInfo(adminName,adminpwd,admin);
		System.out.println(null==admin);
		if(null!=admin) {
			servletContext.setAttribute("status", true);
			try {
				request.getRequestDispatcher("/Administration/indext.jsp").forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			servletContext.setAttribute("status", false);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)  {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
