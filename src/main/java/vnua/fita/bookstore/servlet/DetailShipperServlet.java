package vnua.fita.bookstore.servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import vnua.fita.bookstore.bean.Book;
import vnua.fita.bookstore.bean.BookAndOrder;
import vnua.fita.bookstore.bean.Shipper;
import vnua.fita.bookstore.model.BookDao;
import vnua.fita.bookstore.model.ShipperDao;
import vnua.fita.bookstore.utils.MyUtils;

/**
 * Servlet implementation class DetailShipperServlet
 */
@WebServlet(urlPatterns = {"/detailShipper","/shipperOrder3","/shipperOrder5"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 20) // 20MB
public class DetailShipperServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailShipperServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<String> errors = new ArrayList<String>();
		String orderNo = request.getParameter("orderNo");
		String servletPath = request.getServletPath();
		String pathInfo = MyUtils.getPathInfoFromServletPath(servletPath);
		if ("detailShipper".equals(pathInfo)) {
			List<BookAndOrder> book = BookDao.getBook(orderNo);
			BookAndOrder order = BookDao.getOrder(orderNo);
			if(book == null) {
				System.out.println("ko có sách");
			}else {
				Shipper img = ShipperDao.imgShipper(orderNo);
				request.setAttribute("img", img);
				request.setAttribute("book", book);
				request.setAttribute("order", order);
				
				RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/Views/detailShipper.jsp");
				dispatcher.forward(request, response);
			}
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
