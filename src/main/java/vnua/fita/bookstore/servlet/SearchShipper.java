package vnua.fita.bookstore.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vnua.fita.bookstore.bean.BookAndOrder;
import vnua.fita.bookstore.model.BookDao;
import vnua.fita.bookstore.model.ShipperDao;

/**
 * Servlet implementation class SearchShipper
 */
@WebServlet(name = "SearchShipper", urlPatterns = {"/searchAjax"})
public class SearchShipper extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchShipper() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		// TODO Auto-generated method stub
    	request.setCharacterEncoding("UTF-8");
    	String keyword = request.getParameter("keyword");
    	ShipperDao dao = new ShipperDao();
    	List<BookAndOrder> list = dao.listAllOrder_2_Sp(keyword);
		
		request.setAttribute("bookList", list);
		request.setAttribute("keyword", keyword);
		PrintWriter writer = response.getWriter();
		for (BookAndOrder book: list) {
			writer.println("<tr>\r\n"
					+ "					<td>${book.orderNo}</td>\r\n"
					+ "					<td>${book.fullname}</td>\r\n"
					+ "					<td>${book.mobile} </td>\r\n"
					+ "					<td><fmt:formatNumber type=\"number\" maxFractionDigits=\"0\" value=\"${book.totalCost}\"/><sup>đ</sup></td>\r\n"
					+ "					<td><fmt:formatDate value=\"${book.orderDate}\" pattern=\"dd-MM-yyyy HH:mm\"/> </td>\r\n"
					+ "					\r\n"
					+ "					<c:if test=\"${book.orderStatus ==2}\">\r\n"
					+ "					<td align=\"center\">Đơn <span style=\"color: red\">cần giao</span></td>\r\n"
					+ "						<td align=\"center\">\r\n"
					+ "							<a href=\"detailShipper?orderNo=${book.orderNo }&orderStatus=2\">Xem chi tiết</a>\r\n"
					+ "						</td>\r\n"
					+ "					</c:if>\r\n"
					+ "					<c:if test=\"${book.orderStatus ==3}\">\r\n"
					+ "					<td align=\"center\">Đơn cần <span style=\"color: red\">đã giao</span>  thành công</td>\r\n"
					+ "						<td align=\"center\">\r\n"
					+ "							<a href=\"detailShipper?orderNo=${book.orderNo }&orderStatus=3\">Xem chi tiết</a>\r\n"
					+ "						</td>\r\n"
					+ "					</c:if>\r\n"
					+ "					<c:if test=\"${book.orderStatus ==4}\">\r\n"
					+ "					<td align=\"center\">Đơn <span style=\"color: red\">cần trả</span> </td>\r\n"
					+ "						<td align=\"center\">\r\n"
					+ "							<a href=\"detailShipper?orderNo=${book.orderNo }&orderStatus=3\">Xem chi tiết</a>\r\n"
					+ "						</td>\r\n"
					+ "					</c:if>\r\n"
					+ "					<c:if test=\"${book.orderStatus ==5}\">\r\n"
					+ "					<td align=\"center\">Đơn <span style=\"color: red\">đã trả</span> thành công</td>\r\n"
					+ "						<td align=\"center\">\r\n"
					+ "							<a href=\"detailShipper?orderNo=${book.orderNo }&orderStatus=5\">Xem chi tiết</a>\r\n"
					+ "						</td>\r\n"
					+ "					</c:if>\r\n"
					+ "					\r\n"
					+ "				</tr>");
		}
		
	}
	protected void searchBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = (String)request.getSession().getAttribute("name");
		String keyword = (String)request.getParameter("keyword");
		String errors = null;
//		if(name!=null && !name.isEmpty()) {
//			List<Book> listBook = bookDAO.searchBooks(keyword);
//			request.setAttribute("searchListBook", listBook);
//			RequestDispatcher dispatcher = request.getRequestDispatcher("SearchResultArea.jsp");
//			
//			dispatcher.forward(request, response);
//		}else {
//			RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");
//			dispatcher.forward(request, response);
		List<BookAndOrder> list = BookDao.listAllOrder_2();
		if (list.isEmpty() ) {
			errors = "Không thể lấy dữ liệu";
		}
		else {
			request.setAttribute("errors", errors);
			request.setAttribute("bookList", list);
			RequestDispatcher rd = this.getServletContext()
					.getRequestDispatcher("/Views/shipperHome.jsp");
			rd.forward(request, response);
		}
		
		
//		BookAndOrder list1 = ShipperDao.listAllOrder_2_Sp(keyword);
//		if (list1 == null) {
//			errors = "Không thể lấy dữ liệu";
//		}
//		if (keyword != null) {
//			request.setAttribute("bookList", list);
//			request.setAttribute("keyword", keyword);
//			RequestDispatcher rd = this.getServletContext()
//					.getRequestDispatcher("/Views/shipperHome.jsp");
//			rd.forward(request, response);
//		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
