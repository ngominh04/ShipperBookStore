package vnua.fita.bookstore.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vnua.fita.bookstore.bean.Book;
import vnua.fita.bookstore.bean.BookAndOrder;
import vnua.fita.bookstore.model.BookDao;
import vnua.fita.bookstore.model.ShipperDao;
import vnua.fita.bookstore.utils.MyUtils;

/**
 * Servlet implementation class ClientHomeServlet
 */
@WebServlet(urlPatterns = {"/clientHome"})
public class ClientHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
//	private BookDao bookDao;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientHomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		String erros =null;
		List<Book> list = null;
		int page = 1;
		int revordsPerPage =2;
		
		if (request.getParameter("page") != null) {
			page =Integer.parseInt(request.getParameter("page"));
		}
		
		String keyword = request.getParameter("keyword");
		int noOfRecords = BookDao.getNoOfRecords(keyword);
		int noOfPages = (int) Math.ceil(noOfRecords*1.0/revordsPerPage);

		list = BookDao.listAllBooks((page-1)*revordsPerPage, revordsPerPage,keyword);
		
		request.setAttribute("bookList", list);
		request.setAttribute("noOfPages", noOfPages);
		request.setAttribute("page", page);
		request.setAttribute("keyword", keyword);
		
		if (keyword != null) {
			RequestDispatcher rd = this.getServletContext()
					.getRequestDispatcher("/Views/searchBookClient.jsp");
			rd.forward(request, response);
		}
		else {
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/Views/clientHomeView.jsp");
		dispatcher.forward(request, response);
		}	
	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
