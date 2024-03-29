package vnua.fita.bookstore.servlet;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import vnua.fita.bookstore.bean.CartItem;
import vnua.fita.bookstore.bean.Order;
import vnua.fita.bookstore.config.VNPayConfig;
import vnua.fita.bookstore.model.OrderDao;
import vnua.fita.bookstore.utils.MyUtils;

@WebServlet(urlPatterns = {"/pay"})
public class PayServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String vnp_ResponseCode=req.getParameter("vnp_ResponseCode");
		resp.sendRedirect(req.getContextPath()+"/order");
	}
	
	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("1");
		String fowardPage = "/Views/cartView.jsp";
		String vnp_ResponseCode=req.getParameter("vnp_ResponseCode");
		if(vnp_ResponseCode=="00") {
			HttpSession session = req.getSession();

			Order order = (Order) session.getAttribute("order_vnpay");
			if (OrderDao.checkAndUpdateAvaiableBookOfOrder(order)) {
				boolean insertResult = OrderDao.insertOrder(order);
				if (insertResult) {
					req.setAttribute("CART_OF_CUSTOMER", MyUtils.getCartOfCustomer(session));
					req.setAttribute("ORDER_OF_CUSTOMER", order);
					MyUtils.deleteCart(session);
					fowardPage = "/Views/detailOrderView.jsp";
				} else {
					req.setAttribute("errors", "ADD_TO_CART_ACTION");
					fowardPage = "/Views/cartView.jsp";
				}
			} else {
				MyUtils.updateCartOfCustomer(session, converListToMap(order.getOrderBookList()));
				fowardPage = "/Views/cartView.jsp";
			}
		}
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(fowardPage);
		dispatcher.forward(req, resp);
    }
	
	private Map<Integer, CartItem> converListToMap(List<CartItem> orderBookList) {
		Map<Integer, CartItem> cartItemList = new HashMap<Integer, CartItem>();
		for (CartItem cartItem : orderBookList) {
			cartItemList.put(cartItem.getSelectedBook().getBookId(), cartItem);
		}
		return cartItemList;
	}
}