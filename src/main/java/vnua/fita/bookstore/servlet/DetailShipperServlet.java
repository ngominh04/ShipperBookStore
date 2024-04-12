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
////		String orderNo = request.getParameter("orderNo");
//		String reason = request.getParameter("reason");
//		Part filePart = request.getPart("file");
//		String imagePath = null;
//		BookAndOrder order = BookDao.getOrder(orderNo);
//		if ("shipperOrder3".equals(pathInfo)) {
//			String fileName = "shipper" + "_" + MyUtils.getTimeLabel()+ MyUtils.extractFileExxtension(filePart);
//			
//			String contextPath = getServletContext().getRealPath("/"); // Lấy đường dẫn
//																		// thực của ứng
//																		// dụng web
//			String savePath = contextPath + "img"; // Đường dẫn đến thư mục 'img'
//			
//			File fileSaveDir = new File(savePath);
//			if (!fileSaveDir.exists()) {
//				fileSaveDir.mkdir(); // Tạo thư mục 'img' nếu nó không tồn tại
//			}
//			
//			String filePath = savePath + File.separator + fileName; // Đường dẫn file cuối
//																	// cùng để lưu trữ ảnh
//			filePart.write(filePath); // Lưu file ảnh
//			imagePath = "img" + File.separator + fileName;
//			Shipper shipper = new Shipper( order.getOrderId(), imagePath);
//			Boolean check = ShipperDao.insertShipper(shipper); // lưu lại ảnh shipper
//			order.setOrderStatus(3);
//			Boolean order2 = BookDao.updateOrderShipper(order); // lưu chuyển trạng thái đơn
//			response.sendRedirect(request.getContextPath() + "/shipperHome");
//		}
//		if ("shipperOrder5".equals(pathInfo)) {
//			String fileName = "shipper" + "_" + MyUtils.getTimeLabel()+ MyUtils.extractFileExxtension(filePart);
//			
//			String contextPath = getServletContext().getRealPath("/"); // Lấy đường dẫn
//																		// thực của ứng
//																		// dụng web
//			String savePath = contextPath + "img"; // Đường dẫn đến thư mục 'img'
//			
//			File fileSaveDir = new File(savePath);
//			if (!fileSaveDir.exists()) {
//				fileSaveDir.mkdir(); // Tạo thư mục 'img' nếu nó không tồn tại
//			}
//			
//			String filePath = savePath + File.separator + fileName; // Đường dẫn file cuối
//																	// cùng để lưu trữ ảnh
//			filePart.write(filePath); // Lưu file ảnh
//			imagePath = "img" + File.separator + fileName;
//			Shipper shipper = new Shipper( order.getOrderId(), imagePath,reason);
//			Boolean check = ShipperDao.insertShipper_Img_Rea(shipper); // lưu lại ảnh shipper
//			order.setOrderStatus(5);
//			Boolean order2 = BookDao.updateOrderShipper(order); // lưu chuyển trạng thái đơn
//			response.sendRedirect(request.getContextPath() + "/shipperHome");
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
