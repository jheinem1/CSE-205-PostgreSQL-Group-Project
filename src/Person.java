
public class Person {
	
	private String fName;
	private String lName;
	private String address;
	private String emailAddress;
	private String position;
	
	private int ID;
	
	private String username;
	private String password;
	
	private static int lastID;
	
	
	
	
	
	public Person(String fName, String lName, String address, String emailAddress, String position,
			String username, String password) {
		if (isValidCharacters(fName)) { this.fName = fName; } 
		if (isValidCharacters(lName)) { this.lName = lName; } 
		if (isValidCharacters(address)) { this.address = address; } 
		if (isValidCharacters(emailAddress)) { this.emailAddress = emailAddress; } 
		if (isValidCharacters(position)) { this.position = position; }
		ID = lastID + 1;
		lastID += 1;
		if (isValidCharacters(username)) { this.username = username; }
		if (isValidCharacters(password)) { this.password = password; }
	}
	
	public String getFullName() {
		return fName + " " + lName;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		if (isValidCharacters(fName)) { 
			this.fName = fName;
		}
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		if (isValidCharacters(fName)) { 
			this.fName = fName;
		}
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		if (isValidCharacters(fName)) { 
			this.fName = fName;
		}
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		if (isValidCharacters(fName)) { 
			this.fName = fName;
		}
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		if (isValidCharacters(fName) && isValidPosition(position)) { 
			this.fName = fName;
		}
	}
	public int getID() {
		return ID;
	}
	private void setID(int iD) {
		ID = iD;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		if (isValidCharacters(fName)) { 
			this.fName = fName;
		}
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		if (isValidCharacters(fName)) { 
			this.fName = fName;
		}
	}

	public String encode() {
		return fName + "&" + lName + "&" + address + "&" + emailAddress + "&" + position + "&" + ID + "&" + username + "&" + password;
	}

	private static boolean isValidCharacters(String cheese) {
		
		if (cheese.contains("\\") || cheese.contains("&") || cheese.contains("\"")) {
			return false;
		}
		
		return true;		
	}
	
	private static boolean isValidPosition(String cheese) {
		
		switch(cheese) {
			case "Customer":
				return true;
			case "Employee":
				return true;
			case "Manager":
				return true;
			default:
				return false;
		}		
	}
	
	public static void decode(String encoded, Person pers) {
		
		
		pers.fName = encoded.substring(0, encoded.indexOf("&"));
		encoded = encoded.replaceFirst(encoded.substring(0, encoded.indexOf("&") + 1), "");
		
		
		pers.lName = encoded.substring(0, encoded.indexOf("&"));
		encoded = encoded.replaceFirst(encoded.substring(0, encoded.indexOf("&") + 1), "");
		
		pers.address = encoded.substring(0, encoded.indexOf("&"));
		encoded = encoded.replaceFirst(encoded.substring(0, encoded.indexOf("&") + 1), "");
		
		pers.emailAddress = encoded.substring(0, encoded.indexOf("&"));
		encoded = encoded.replaceFirst(encoded.substring(0, encoded.indexOf("&") + 1), "");
		
		pers.position = encoded.substring(0, encoded.indexOf("&"));
		encoded = encoded.replaceFirst(encoded.substring(0, encoded.indexOf("&") + 1), "");

		String temp = encoded.substring(0, encoded.indexOf("&"));
		pers.ID = Integer.parseInt(temp);
		encoded = encoded.replaceFirst(encoded.substring(0, encoded.indexOf("&") + 1), ""); 
		
		pers.username = encoded.substring(0, encoded.indexOf("&"));
		encoded = encoded.replaceFirst(encoded.substring(0, encoded.indexOf("&") + 1), "");
		
		pers.password = encoded;
		//encoded = encoded.replaceFirst(encoded.substring(0, encoded.indexOf("&")), "");
	}
	
	
	
	

}
