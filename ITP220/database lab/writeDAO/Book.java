package writeDAO;
//POJO

public class Book {
	
	private String isbn;
	private String title;
	private int editionNumber;
	private String copyright;
	
	public Book(String inIsbn, String inTitle, int inEditionNumber, String inCopyright) {
		this.isbn = inIsbn;
		this.title = inTitle;
		this.editionNumber = inEditionNumber;
		this.copyright = inCopyright;
	}
	
	public String getIsbn() {
		return this.isbn;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public int getEditionNumber() {
		return this.editionNumber;
	}
	
	public String getCopyright() {
		return this.copyright;
	}
	
	@Override
	public String toString() {
		return new StringBuilder()
				.append(this.getIsbn())
				.append("-")
				.append(this.getTitle())
				.append("-")
				.append(this.getEditionNumber())
				.append("-")
				.append(this.getCopyright())
				.toString();
	}
}
