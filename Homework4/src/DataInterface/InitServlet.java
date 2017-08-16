package DataInterface;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class InitServlet
 */
@WebServlet("/InitServlet")
public class InitServlet extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InitServlet() {
        super();
        
        System.out.println("Initialised.....");
   
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	      DataReader db = (DataReader)request.getServletContext().getAttribute("data");
	      if (db ==null){
	    	  db = new DataReader();
	    	  ServletContext context = getServletContext();
	    	  context.setAttribute("data", db);
	    	  System.out.println("Data Loaded to memory");
	      }
	      System.out.println("search.jsp");
	      request.getSession().invalidate();
		response.sendRedirect("search.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		
		DataReader db;

		db = (DataReader)request.getServletContext().getAttribute("data");

		
		System.out.println("post method+++++++++++++++++");
		
		ArrayList<String> page = new ArrayList<String>();
		
		
		
		
		String input = request.getParameter("country");
		
	    System.out.println("input ----> "+input);
	    
	    try
	    {
	    
	    page = db.Search(input);
//	    ServletContext context = getServletContext();
//	    context.setAttribute();
	    
	    }catch(NullPointerException e){
			System.out.println("No results found");
		}
	    
	    
	    HttpSession session =request.getSession();
	    session.setAttribute("result", page);
	    session.setAttribute("input", input);
//	    request.setAttribute("input", input);
	    
	    
	    response.sendRedirect("search.jsp");
	    
	    
	}

}
