package com.hpe.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hpe.entity.Menus;
import com.hpe.entity.MenusOrder;
import com.hpe.entity.Users;
import com.hpe.service.FrontStageService;
import com.hpe.service.MenuService;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
     FrontStageService fss=new FrontStageService();
     
     MenuService ms=new MenuService();
  
     
   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String methods=request.getParameter("methods");
		if("regist".equals(methods)) {
			userRegist(request,response);
		}else if("login".equals(methods)) {
			userLogin(request,response);
		}else if("mycar".equals(methods)) {
			int menuid=Integer.parseInt(request.getParameter("id"));
			putInCar(request,response,menuid);
		}else if("update".equals(methods)) {
			updateUser(request,response);
		}else if("getOrder".equals(methods)) {
			Users u=(Users) request.getSession().getAttribute("user");
			System.out.println(u.getId());
			getUserOrder(request,response,u.getId());
		}
	}

	private void getUserOrder(HttpServletRequest request, HttpServletResponse response, int userid) {
		// TODO Auto-generated method stub
		List<MenusOrder> myOrder=fss.getUserOrder(userid);
		System.out.println(null==myOrder);
		this.getServletContext().setAttribute("myOrder", myOrder);
	}

	private void updateUser(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void putInCar(HttpServletRequest request, HttpServletResponse response, int menuid) {
		// TODO Auto-generated method stub
		
		boolean flag=false;
		//正在加入购物车的菜单
		Menus curmenu=ms.findMenuById(menuid);
		//创建或者获得用户的session对象
		HttpSession session=request.getSession();
		//从会话中得到购物车信息
		List<Menus> mycar=(List<Menus>) session.getAttribute("mycar");
		
		 //问题一：加入第二种菜品的数量总是空，因为else的括号范围错了，而且增强for循环找到一个对应的就要退出才对。加入同一个菜品
	     //依然新建了一条记录且数字总是1，因为每调用一次函数map就清零重新创建了
		//问题二：清空购物车再加入数量没有重新计数，不是重新获得的session的属性
		
		//创建一个记录购物车菜品数量的集合
		Map<Integer,Integer> menunum=(Map<Integer, Integer>) session.getAttribute("menunum");
		
		if(mycar==null&&menunum==null) {
			//首次购买或者清空了购物车 , 创建一个购物车（含有菜品信息的car和购买数量信息的menunum）属性
			mycar=new ArrayList();
			menunum=new HashMap();
			session.setAttribute("mycar", mycar);
			session.setAttribute("menunum", menunum);
			//将菜品放入购物车直接回去
			mycar.add(curmenu);
			menunum.put(menuid, 1);
			addSession(response,session);
			return;
		}else {
			//购物车不空，将商品放入购物车之前遍历购物车是否已经有该菜品了，则数量加一不需要放入新的menu对象
			for(Entry<Integer, Integer> entry : menunum.entrySet()){
			    if(menuid==entry.getKey()) {
			    	flag=true;
			    	//System.out.println(entry.getKey());
			    	//System.out.println(entry.getValue());
			    	menunum.put(entry.getKey(), entry.getValue()+1);
			    	//System.out.println(entry.getValue());
			    	addSession(response, session);
			    	break;
			    	
			    }   
			}
			
		}
		if(flag) {//return;//购物车已有相同的直接返回不需要添加对象
			return;
		}else {
			//购物车非空且加入的是新的菜品则执行加入新的对象,并加入map
			   mycar.add(curmenu);
			   menunum.put(menuid, 1);
			   addSession(response,session);
		}
		 
	}

private void addSession(HttpServletResponse response, HttpSession session) {
		// TODO Auto-generated method stub
	//创建cookie存放Session的标识号
			Cookie cookie=new Cookie("JSESSIONID",session.getId());
			cookie.setMaxAge(60*30);
			cookie.setPath("/grandmamdish");
			response.addCookie(cookie);
			try {
				response.getWriter().write("<script>alert('加入餐车成功');</script>");//问题少了分号所以没执行
				response.getWriter().write("<script>window.parent.location.href='/grandmamdish/User/main.jsp';</script>");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	private void userLogin(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String userName = request.getParameter("userName");
		String userpwd=request.getParameter("userpwd");
		 Users u=new Users();
		u=fss.AdminLogin(userName, userpwd);
		if(null!=u) {
			request.setAttribute("result", "true");
			
			request.getSession().setAttribute("user", u);
			try {
				//加了index在main之上，index中跳转main，这样登录成功转发index就不在框架中打开
				response.getWriter().write("<script>alert('登录成功');</script>");
				response.getWriter().write("<script>window.parent.location.href='/grandmamdish/User/index.jsp';</script>");
//				request.getRequestDispatcher("/User/index.jsp").forward(request, response);
			}  catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}else {
			try {
				response.getWriter().print("<script>alert('登录失败')</script>");
				request.getRequestDispatcher("/User/UserLogin.jsp").forward(request, response);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			request.setAttribute("result", "false");
			//从userlogin提交表单成功后转跳而来，执行完继续表单后的内容吧？
		}
	}

	private void userRegist(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String userName=request.getParameter("name");
		String userpwd=request.getParameter("pwd");
		String qpwd=request.getParameter("qpwd");
		String realName=request.getParameter("realname");
		String age=request.getParameter("age");
		String card=request.getParameter("card");
		String address=request.getParameter("address");
		String phone=request.getParameter("phone");
		String email=request.getParameter("email");
		String code=request.getParameter("code");
		String sex=request.getParameter("sex");
		Users newuser=new Users(0, userName, userpwd, userpwd, sex, age, card, address, phone, email, code, "1");
		newuser=fss.userRegist(newuser);
		if(null!=newuser) {
//			this.getServletContext().setAttribute("user", newuser);
			try {
				response.getWriter().write("<script>alert('注册成功，请重新登录');</script>");
				request.getRequestDispatcher("/User/UserLogin.jsp").forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			
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
