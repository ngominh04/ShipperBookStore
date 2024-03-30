package vnua.fita.bookstore.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import vnua.fita.bookstore.bean.BookAndOrder;
import vnua.fita.bookstore.bean.Shipper;
import vnua.fita.bookstore.model.BookDao;
import vnua.fita.bookstore.model.ShipperDao;
import vnua.fita.bookstore.utils.MyUtils;

/**
 * Servlet implementation class ShipperHomeServlet
 */
@WebServlet(urlPatterns = {"/shipperHome","/shipperHome1","/shipperHome2","/shipperHome3","/shipperHomeSearch"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 20) // 20MB
public class ShipperHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShipperHomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String servletPath = request.getServletPath();
		String pathInfo = MyUtils.getPathInfoFromServletPath(servletPath);
		String errors = null;
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		if("shipperHome".equals(pathInfo)) {
			List<BookAndOrder> list = BookDao.listAllOrder_2();
				if (list.isEmpty()) {
					errors = "Không thể lấy dữ liệu";
				}
				request.setAttribute("errors", errors);
				request.setAttribute("bookList", list);
				RequestDispatcher rd = this.getServletContext()
						.getRequestDispatcher("/Views/shipperHome.jsp");
				rd.forward(request, response);
		}
		if("shipperHome1".equals(pathInfo)) {
			List<BookAndOrder> list = BookDao.listAllOrder_3();
			if (list.isEmpty()) {
				errors = "Không thể lấy dữ liệu";
			}
	
			request.setAttribute("errors", errors);
			request.setAttribute("bookList", list);
			RequestDispatcher rd = this.getServletContext()
					.getRequestDispatcher("/Views/shipperHome.jsp");
			rd.forward(request, response);
		}
		if("shipperHome2".equals(pathInfo)) {
			List<BookAndOrder> list = BookDao.listAllOrder_4();
			
			if (list.isEmpty()) {
				errors = "Không thể lấy dữ liệu";
			}
	
			request.setAttribute("errors", errors);
			request.setAttribute("bookList", list);
			RequestDispatcher rd = this.getServletContext()
					.getRequestDispatcher("/Views/shipperHome.jsp");
			rd.forward(request, response);
		}
		if("shipperHome3".equals(pathInfo)) {
			List<BookAndOrder> list = BookDao.listAllOrder_5();
			
			if (list.isEmpty()) {
				errors = "Không thể lấy dữ liệu";
			}
	
			request.setAttribute("errors", errors);
			request.setAttribute("bookList", list);
			RequestDispatcher rd = this.getServletContext()
					.getRequestDispatcher("/Views/shipperHome.jsp");
			rd.forward(request, response);
		}
		
		
	}
	


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String servletPath = request.getServletPath();
		String pathInfo = MyUtils.getPathInfoFromServletPath(servletPath);
		String orderNo = request.getParameter("orderNo");
		String reason = request.getParameter("reason");
		Part filePart = request.getPart("file");
		String imagePath = null;
		
		BookAndOrder order = BookDao.getOrder(orderNo);
		
		if ("shipperHome".equals(pathInfo)) {
			String fileName = "shipper" + "_" + MyUtils.getTimeLabel()+ MyUtils.extractFileExxtension(filePart);
			
			String contextPath = getServletContext().getRealPath("/"); // Lấy đường dẫn
																		// thực của ứng
																		// dụng web
			String savePath = contextPath + "img"; // Đường dẫn đến thư mục 'img'
			
			File fileSaveDir = new File(savePath);
			if (!fileSaveDir.exists()) {
				fileSaveDir.mkdir(); // Tạo thư mục 'img' nếu nó không tồn tại
			}
			
			String filePath = savePath + File.separator + fileName; // Đường dẫn file cuối
																	// cùng để lưu trữ ảnh
			filePart.write(filePath); // Lưu file ảnh
			imagePath = "img" + File.separator + fileName;
			Shipper shipper = new Shipper( order.getOrderId(), imagePath);
			Boolean check = ShipperDao.insertShipper(shipper); // lưu lại ảnh shipper
			order.setOrderStatus(3);
			Boolean order2 = BookDao.updateOrderShipper(order); // lưu chuyển trạng thái đơn
			response.sendRedirect(request.getContextPath() + "/shipperHome");
		}
		if ("shipperHome2".equals(pathInfo)) {
			String fileName = "shipper" + "_" + MyUtils.getTimeLabel()+ MyUtils.extractFileExxtension(filePart);
			
			String contextPath = getServletContext().getRealPath("/"); // Lấy đường dẫn
																		// thực của ứng
																		// dụng web
			String savePath = contextPath + "img"; // Đường dẫn đến thư mục 'img'
			
			File fileSaveDir = new File(savePath);
			if (!fileSaveDir.exists()) {
				fileSaveDir.mkdir(); // Tạo thư mục 'img' nếu nó không tồn tại
			}
			
			String filePath = savePath + File.separator + fileName; // Đường dẫn file cuối
																	// cùng để lưu trữ ảnh
			filePart.write(filePath); // Lưu file ảnh
			imagePath = "img" + File.separator + fileName;
			Shipper shipper = new Shipper( order.getOrderId(), imagePath,reason);
			Boolean check = ShipperDao.insertShipper_Img_Rea(shipper); // lưu lại ảnh shipper
			order.setOrderStatus(5);
			Boolean order2 = BookDao.updateOrderShipper(order); // lưu chuyển trạng thái đơn
			response.sendRedirect(request.getContextPath() + "/shipperHome");
		}
		String keyword = request.getParameter("keyword");
		if ("shipperHomeSearch".equals(pathInfo)) {
			if (keyword != null) {
				BookAndOrder list = ShipperDao.listAllOrder_2_Sp(keyword);
				
				request.setAttribute("bookList", list);
				request.setAttribute("keyword", keyword);
				RequestDispatcher rd = this.getServletContext()
						.getRequestDispatcher("/Views/shipperHome.jsp");
				rd.forward(request, response);
			}
		}
	}

}
