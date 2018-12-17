package com.hpe.web.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hpe.entity.Types;
import com.hpe.service.MenuService;
import com.hpe.service.MenuTypeService;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class MenuTypeServlet
 */
public class MenuTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    MenuTypeService mts=new MenuTypeService();
    MenuService ms=new MenuService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MenuTypeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String method=request.getParameter("method");
		
		if("ranktwo".equals(method)) {
			rankTwo(request,response);
		}else if("gettype".equals(method)) {
			queryTypes(request,response);
		}
		
		
		
	}

	private void queryTypes(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		List<Types> type=mts.queryTypes();
		request.setAttribute("types", type);
		
		
		try {
			request.getRequestDispatcher("/Administration/main_right_menusadd.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void rankTwo(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		//把集合-->json数据JSONArray,JSONObject
		Map<String,String> rankOrder=ms.getRanking();
		JSONArray JSONObject=JSONArray.fromObject(rankOrder);
		String json=JSONObject.toString();
		try {
			response.getWriter().write(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("json"+json);
        /*String result = json.toString();
        System.out.println(result);*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
