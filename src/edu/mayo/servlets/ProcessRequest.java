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
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.getWriter().append("<table><tr><td>Service:</td><td>Container Name:</td></tr>");
		//response.getWriter().append("<br>UID: ").append(request.getParameter("UID"));
		
		Container c = new Container(); 

		if (request.getParameter("tomcat") != null){

			response.getWriter().append("<tr><td>");
			response.getWriter().append("Tomcat: ").append(request.getParameter("tomcat"));
			response.getWriter().append("</td><td>");
			response.getWriter().append(request.getParameter("UID")+"_tomcat");
			response.getWriter().append("</td></tr>");
			
			c.create(request.getParameter("UID")+"_tomcat", "tomcat", "http://192.168.1.97:4243");
			c.start(request.getParameter("UID")+"_tomcat","http://192.168.1.97:4243");
			
		}
		
		if (request.getParameter("redis") != null){
			
			response.getWriter().append("<tr><td>");
			response.getWriter().append("Redis: ").append(request.getParameter("redis"));
			response.getWriter().append("</td><td>");
			response.getWriter().append(request.getParameter("UID")+"_redis");
			response.getWriter().append("</td></tr>");
			
			c.create(request.getParameter("UID")+"_redis", "redis", "http://192.168.1.97:4243");
			c.start(request.getParameter("UID")+"_redis","http://192.168.1.97:4243");
			
		}

		if (request.getParameter("mongo") != null){
	
			response.getWriter().append("<tr><td>");
			response.getWriter().append("Mongo: ").append(request.getParameter("mongo"));
			response.getWriter().append("</td><td>");
			response.getWriter().append(request.getParameter("UID")+"_mongo");
			response.getWriter().append("</td></tr>");
			
			c.create(request.getParameter("UID")+"_mongo", "mongo", "http://192.168.1.97:4243");
			c.start(request.getParameter("UID")+"_mongo","http://192.168.1.97:4243");
	
		}
		
		response.getWriter().append("</table>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
