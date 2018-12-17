package com.hpe.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ExistServlet
 */
public class ExistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExistServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String type=request.getParameter("type");
		if("exist".equals(type)) {
			ExistProgram(request,response);
		}else if("emptycar".equals(type)) {
			EmptyMyCar(request,response);
		}
		
	}
	//清空购物车
	private void EmptyMyCar(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		//干掉session中的购物车
		HttpSession session = request.getSession();
		session.removeAttribute("mycar");
		session.removeAttribute("menunum");
		try {
			response.getWriter().write("<script>alert('清空成功');window.location.reload();</script>");
			response.getWriter().write("<script>window.parent.location.href='/grandmamdish/User/index.jsp';</script>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//用户退出
	private void ExistProgram(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		//干掉session
				HttpSession session = request.getSession();
				session.invalidate();
		request.getSession().removeAttribute("user");
		this.getServletContext().setAttribute("mycar","");//这样退出再登录购物车应该还在
		this.getServletContext().setAttribute("menunum","");
		try {
			response.getWriter().write("<script>alert('退出成功');</script>");
			//问题少了分号所以没执行
			//解决退出时main.jsp不显示middle.jsp，如果想重新打开新页面
			response.getWriter().write("<script>window.parent.location.href='/grandmamdish/User/index.jsp';</script>");
//			request.getRequestDispatcher("/User/index2.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
