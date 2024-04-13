package vnua.fita.bookstore.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vnua.fita.bookstore.bean.BookAndOrder;
import vnua.fita.bookstore.model.ShipperDao;

/**
 * Servlet implementation class SearchByAjax
 */
@WebServlet("/SearchByAjax")
public class SearchByAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchByAjax() {
        super();
        // TODO Auto-generated constructor stub
    }
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	response.setContentType("text/html;charset=UTF-8");
        
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
