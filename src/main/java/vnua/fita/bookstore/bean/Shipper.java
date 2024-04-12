package vnua.fita.bookstore.bean;

public class Shipper {
	private int id;
	private int orderId;
	private String image;
	private String reason;
	
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Shipper() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Shipper( int orderId, String image) {
		super();
		
		this.orderId = orderId;
		this.image = image;
	}
	public Shipper(int orderId, String image, String reason) {
		super();
		this.orderId = orderId;
		this.image = image;
		this.reason = reason;
	}
	public Shipper(String image,String reason) {
		super();
		this.image = image;
		this.reason = reason;
	}
	

}
