package vnua.fita.bookstore.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import vnua.fita.bookstore.bean.BookAndOrder;
import vnua.fita.bookstore.bean.Order;
import vnua.fita.bookstore.bean.Shipper;
import vnua.fita.bookstore.database.Database;

public class ShipperDao {
	
	public static boolean insertShipper(Shipper shipper) {
		boolean insertResult = false;
		String sql = "INSERT INTO tblorder_shipper(order_id, image) VALUE (?,?)";
		Connection jdbcConnection = Database.getConnection();
		try {
			PreparedStatement preStatement = jdbcConnection.prepareStatement(sql);
			preStatement.setInt(1, shipper.getOrderId());
			preStatement.setString(2, shipper.getImage());
			
			insertResult = preStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return insertResult;
	}
	public static boolean insertShipper_Img_Rea(Shipper shipper) {
		boolean insertResult = false;
		String sql = "INSERT INTO tblorder_shipper(order_id, image,reason) VALUE (?,?,?)";
		Connection jdbcConnection = Database.getConnection();
		try {
			PreparedStatement preStatement = jdbcConnection.prepareStatement(sql);
			preStatement.setInt(1, shipper.getOrderId());
			preStatement.setString(2, shipper.getImage());
			preStatement.setString(3, shipper.getReason());
			
			insertResult = preStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return insertResult;
	}
	// order shipper có keywword
	public static BookAndOrder listAllOrder_2_Sp(String keyword) {
		// cau lenh sql
		BookAndOrder book = null;
		String sql = "select  t.order_status orderStatus, t.order_date orderDate,t.total_cost orderPrice,tb.quantity orderQuantity,t.order_no orderNo,\r\n"
				+ "                t2.fullname fullname,t2.mobile mobile\r\n"
				+ "from tblorder t\r\n"
				+ "         inner join shipper_book_store.tblorder_book tb on t.order_id = tb.order_id\r\n"
				+ "         inner join tbluser t2 on t2.username = t.customer_username\r\n"
				+ "where t.order_no = ?";
		// tao ket noi
		Connection jdbcConnection = Database.getConnection();
		try {
			// tao doi tuong truy van CSDL
			PreparedStatement preparedStatement = jdbcConnection.prepareStatement(sql);
			// thuc hien truy van
			
			preparedStatement.setString(1, keyword); // lấy theo key
			ResultSet resultSet = preparedStatement.executeQuery();
			// duyet qua danh sach ban ghi ket qua tra ve
			if (resultSet.next()) {
				String orderNo = resultSet.getString("orderNo");
				String fullName = resultSet.getString("fullname");
				String mobile = resultSet.getString("mobile");
				Float totalCost = resultSet.getFloat("orderPrice");
				Date orderDate = resultSet.getTimestamp("orderDate");
				int orderStatus = resultSet.getInt("orderStatus");
				book = new BookAndOrder(orderNo, orderDate, totalCost, fullName, mobile,orderStatus);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return book;
	}
	public static Shipper imgShipper(String orderNo) {
		String sql ="select * from tblorder_shipper\r\n"
				+ "    inner join tblorder t on tblorder_shipper.order_id = t.order_id\r\n"
				+ "where t.order_no = ?";
		Shipper shipper = null;
		Connection connection = Database.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, orderNo);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				String img = resultSet.getString("image");
				String reason = resultSet.getString("reason");
				shipper = new Shipper( img,reason);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return shipper;
	}
}
