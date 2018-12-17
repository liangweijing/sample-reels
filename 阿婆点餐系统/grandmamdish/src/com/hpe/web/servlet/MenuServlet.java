package com.hpe.web.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hpe.entity.Menus;
import com.hpe.entity.MenusOrder;
import com.hpe.entity.Users;
import com.hpe.service.MenuService;
import com.hpe.utils.Page;
import com.jspsmart.upload.SmartFile;
import com.jspsmart.upload.SmartRequest;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class MenuServlet
 */
public class MenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    MenuService ms=new MenuService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MenuServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//获取action
		String action = request.getParameter("action");
		
		//判断action
		switch(action) {
		case "queryMenusByPage":
			queryMenusByPage(request,response);
			break;
		case "addMenu":
			addMenu(request,response);
			break;
		case "submitOrder":
			submitOrder(request,response);
			break;
		case "rank":
			getRanking(request,response);
			break;
		case "rank2":
			getRankingTwo(request,response);
		}
	}
	private void getRankingTwo(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		Map<String, String> rankOrder=ms.getRanking();
		//把集合-->json数据JSONArray,JSONObject
				JSONArray jsonArray=JSONArray.fromObject(rankOrder);
				String json=jsonArray.toString();
				try {
					response.getWriter().write(json);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("json"+json);
	}

	private void getRanking(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		Map<String, String> rankOrder=ms.getRanking();
		this.getServletContext().setAttribute("rankOrder", rankOrder);
		
		try {
			request.getRequestDispatcher("/User/index.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
	}

	private void submitOrder(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		List<MenusOrder> orders=new ArrayList();
		Users user= (Users) request.getSession().getAttribute("user");
		HttpSession session=request.getSession(false);//获取当前会话session，不存在返回null
		if(null==user) {
			try {
				response.getWriter().write("<script>alert(\"您还未登录！\");</script>");
				return;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(null==session) {
			try {
				response.getWriter().write("<script>alert(\"您的购物车为空！\");</script>");
				return;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			List<Menus> mycar = (List<Menus>) session.getAttribute("mycar");
			Map<Integer,Integer> menunum=(Map<Integer, Integer>) session.getAttribute("menunum");
			if(null==mycar||null==menunum) {
				try {
					response.getWriter().write("<script>alert(\"您的购物车为空！\");</script>");
					return;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				
				SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
				String date=sdf.format(new Date());
				for(Menus m:mycar) {
					Integer num=0;
					for(Integer key:menunum.keySet()) {
						if(m.getId()==key) {
							
							num=menunum.get(key);
							break;
						}
					}
					MenusOrder order=new MenusOrder(0,m.getId(),user.getId(),num.toString(),date,"1",null,null,null,null,null);
					orders.add(order);
				}
				
			}
		}
		int result=ms.addOrders(orders);
		if(result>0) {
			//提交成功，清空购物车
			
				try {
					response.sendRedirect("/grandmamdish/ExistServlet?type=emptycar");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}else {
			try {
				response.getWriter().write("<script>alert(\"提交失败！\");</script>");
				return;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}

	private void addMenu(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		//获取SmartUpload对象
		SmartUpload su=new SmartUpload();
		//初始化配置
		try {
			su.initialize(this.getServletConfig(), request, response);
			//上传
			su.upload();
		} catch (ServletException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SmartUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//获取请求对象
		SmartRequest req=su.getRequest();
		//获取上传的文件（域）
		SmartFile picFile=su.getFiles().getFile(0);
		//菜品名称(其他参数需要通过su.getRequest().getParameter()获得)
		String menuname = req.getParameter("menuname");
		String burden = req.getParameter("burden");
		int typeid=Integer.parseInt(req.getParameter("menutype"));
		String price = req.getParameter("price");
		
		String pricel = req.getParameter("price1");
	
		String brief = req.getParameter("brief");
		
		String menutype = req.getParameter("menutype");
		//获得第一个上传的文件的文件名
		String imgpath="images/"+picFile.getFileName();
//		String imgpath="images/"+request.getParameter("imgpath");
		Menus newMenu=ms.addMenu(menuname,typeid,burden,price,pricel,brief,imgpath);
		try {
		if(null!=newMenu) {
			//添加成功
			request.setAttribute("result", "上传成功");

			
				//保存文件到images文件夹中
				su.save("Administration/images/");
				response.getWriter().write("<script>alert('添加成功');window.location.href='/grandmamdish/Administration/main_right_menusinfo.jsp'</script>");
			
//				request.getRequestDispatcher("/Administration/main_right_menusadd.jsp").forward(request, response);
				
		}else {
			response.getWriter().write("<script>alert('添加失败');window.location.href='/grandmamdish/Administration/main_right_menusinfo.jsp'</script>");
		}	
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SmartUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

	//分页查询菜单
	private void queryMenusByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		Page p=ms.queryMenusByPage(Integer.parseInt(curPage), Integer.parseInt(pageNumber));
		request.setAttribute("page", p);
		String from = request.getParameter("from");
		if("houtai".equals(from)) {
			request.getRequestDispatcher("/Administration/main_right_menusinfo.jsp").forward(request, response);
		}else if("qiantai".equals(from)) {
			request.getRequestDispatcher("/User/middle.jsp").forward(request, response);
		}
//		response.sendRedirect(request.getHeader("referer")); //可以获取上一页的url然后redirect或forward就可以转过去了//返回上一页
//		response.getWriter().print("<script>history.go(-1)</script>"); 
//		System.out.println(p==null);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
