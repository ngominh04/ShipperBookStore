package vnua.fita.bookstore.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
			preStatement.setString(0, shipper.getReason());
			
			insertResult = preStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return insertResult;
	}
}
