package model;

public class Acount {

	private String id;
	private String password;
	private int topScore;

	public Acount(String id, String password, int topScore) {
		this.id = id;
		this.password = password;
		this.topScore = topScore; // Ensure this constructor parameter is properly set
	}

	public String getId() {
		return id;
	}

	public int getTopScore() {
		return topScore;
	}

	public String getPassword() {
		return password;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setTopScore(int topScore) {
		this.topScore = topScore;
	}

	public boolean checkPassword(String input) {
		return password.equals(input);
	}
}
