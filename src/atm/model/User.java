package atm.model;

public class User {

	private int userId;
	private String name;
	private String pin;
	
	public User(int userId, String name, String pin) {
		this.userId = userId;
		this.name = name;
		this.pin = pin;
	}
	public int getUserId() {
		return userId;
	}
	public String getName() {
		return name;
	}
	public String getPin() {
		return pin;
	}
	
	public void setPin(String pin) {
		this.pin=pin;
	}
}
