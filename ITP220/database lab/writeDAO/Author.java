package writeDAO;
//POJO

public class Author {
	
	private int authorId;
	private String firstName;
	private String lastName;
	
	public Author(int inAuthorId, String inFirstName, String inLastName) {
		this.authorId = inAuthorId;
		this.firstName = inFirstName;
		this.lastName = inLastName;
	}
	
	public int getAuthorId() {
		return this.authorId;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	@Override
	public String toString() {
		return new StringBuilder().append(this.getAuthorId()).append("-")
			.append(this.getFirstName()).append("-")
			.append(this.getLastName()).toString();
	}
}
