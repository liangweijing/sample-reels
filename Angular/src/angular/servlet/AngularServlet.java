package angular.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.javafx.tk.RenderJob;

import angular.dao.TestAngularDao;
import angular.impl.TestAngularDaoImpl;
import angular.pojo.User;
import net.sf.json.JSONArray;



/**
 * Servlet implementation class AngularServlet
 */
public class AngularServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	TestAngularDao tad=new TestAngularDaoImpl();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AngularServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String method= request.getParameter("jump");
		System.out.println("方法："+method);
		if("add".equals(method)) {
			addUser(request,response);
		}else if("fin".equals(method)) {
			findUser(request,response);
		}
		
	}

	private void findUser(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		List<User> users=tad.findUser();
		//把集合-->json数据JSONArray,JSONObject
		JSONArray jsonArray=JSONArray.fromObject(users);
		String json=jsonArray.toString();
		try {
			response.getWriter().write(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("得到的json数据格式"+json);
		
	}

	private void addUser(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String name=request.getParameter("username");
		System.out.println(name);
		int result=tad.AddNameAngular(name);
		if(result==1) {
			System.out.println("成功");
			try {
				response.getWriter().println(result);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
		
		else
			System.out.println("失败");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
