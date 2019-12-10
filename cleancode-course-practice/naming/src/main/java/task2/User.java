package task2;

import java.util.Arrays;
import java.util.Date;

public class User {

	private Date dateOfBirth;

	private String name;

	private boolean isAdmin;

	private User[] subordinateUsers;

	private int rating;

	public User(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "User [dateOfBirth=" + dateOfBirth + ", name=" + name + ", isAdmin=" + isAdmin + ", subordinateUsers="
				+ Arrays.toString(subordinateUsers) + ", rating=" + rating + "]";
	}

}
