package vnua.fita.bookstore.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vnua.fita.bookstore.bean.Book;
import vnua.fita.bookstore.bean.Cart;
import vnua.fita.bookstore.bean.CartItem;
import vnua.fita.bookstore.model.BookDao;
import vnua.fita.bookstore.utils.MyUtils;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet(urlPatterns =  {"/cartBook/addToCart","/cartBook/removeFromCart","/cartBook/viewCart"})
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	
    	HttpSession session = request.getSession();
		List<String> errors = new ArrayList<String>();
		String servletPath = request.getServletPath();
		String pathInfo = MyUtils.getPathInfoFromServletPath(servletPath);
		String bookIdStr = request.getParameter("bookId");
		String quantityPurchasedStr = request.getParameter("quantityPurchased");
		int bookId = -1;
		int quantityPurchased = -1;
		try {
			if(bookIdStr != null) { //nếu có bookId gửi tới
				bookId = Integer.parseInt(bookIdStr);
			}
		} catch (NumberFormatException e) {
			errors.add("Giá trị id không hợp lệ");
		}
		
		try {
			if(quantityPurchasedStr != null) { //nếu có quantityPurchased gửi tới
				quantityPurchased = Integer.parseInt(quantityPurchasedStr);
			}
		} catch (NumberFormatException e) {
			errors.add("Giá trị số lượng không hợp lệ");
		}
		
		if(errors.isEmpty()) {
			if("addToCart".equals(pathInfo)) {//thêm vào giỏ hàng
				Book selectedBook = BookDao.getBook(bookId);
				Cart cartOfCustomer = MyUtils.getCartOfCustomer(session);
				if(cartOfCustomer == null) { //chưa tồn tại giỏ hàng
					cartOfCustomer = new Cart();
				}
				cartOfCustomer.addCartItemToCart(bookId, new CartItem(selectedBook, quantityPurchased));
				MyUtils.storeCart(session, cartOfCustomer);
				
			}else if("removeFromCart".equals(pathInfo)){ //xóa từ giỏ hàng
				Cart cartOfCustomer = MyUtils.getCartOfCustomer(session);
				cartOfCustomer.removeCartItemFromCart(bookId);
				MyUtils.storeCart(session, cartOfCustomer);
			}
			
			RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/Views/cartView.jsp");
			rd.forward(request, response);
		}else { 
			response.sendRedirect(request.getContextPath()+"/clientHome");
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
