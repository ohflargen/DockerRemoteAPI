package edu.mayo.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.mayo.docker.Container;

/**
 * Servlet implementation class BuildRequestForm
 */
@WebServlet("/ProcessRequest")
public class ProcessRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProcessRequest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.getWriter().append("<br>Tomcat: ").append(request.getParameter("tomcat"));
		response.getWriter().append("<br>Redis: ").append(request.getParameter("redis"));
		response.getWriter().append("<br>Mongo: ").append(request.getParameter("mongo"));
		response.getWriter().append("<br>UID: ").append(request.getParameter("UID"));
		
		Container c = new Container(); 

		if (request.getParameter("tomcat") != null){
			
			c.create(request.getParameter("UID")+"_tomcat", "tomcat", "http://192.168.1.97:4243");
			c.start(request.getParameter("UID")+"_tomcat","http://192.168.1.97:4243");
			
		}
		
		if (request.getParameter("redis") != null){
			
			c.create(request.getParameter("UID")+"_redis", "redis", "http://192.168.1.97:4243");
			c.start(request.getParameter("UID")+"_redis","http://192.168.1.97:4243");
			
		}

		if (request.getParameter("mongo") != null){
	
			c.create(request.getParameter("UID")+"_mongo", "mongo", "http://192.168.1.97:4243");
			c.start(request.getParameter("UID")+"_mongo","http://192.168.1.97:4243");
	
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
