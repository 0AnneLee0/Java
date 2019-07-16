package lab16;
//POJO contains student info.

//import java.io.Serializable; -- keep track of version if decide to add attributes later
public class NovaStudent /*implements Serializable*/{
	//private static final long serialVersionUID = 1L;

	//---private attributes---
	private int id;
	private String firstName;
	private String lastName;

	//---constructor that accepts all attributes---
	public NovaStudent(int inId, String fn, String ln) {
		id=inId;
		firstName = fn;
		lastName = ln;
	}

	//---set methods---
	public void setId(int inId) {
		id = inId;
	}

	public void setFirstName(String fn) {
		firstName = fn;
	}

	public void setLastName(String ln) {
		lastName = ln;
	}


	//---get methods---
	public int getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}


	//---toString method---
	@ Override
	public String toString() {
		return new StringBuilder()
				.append("id:")   
				.append(String.format("%d",getId()))
				.append("  first name:")
				.append(getFirstName())
				.append("  last name:")
				.append(getLastName())
				.append("\n")
				.toString(); 
	}
}
