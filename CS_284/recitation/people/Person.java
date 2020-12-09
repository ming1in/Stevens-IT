package recitation.people;

public class Person {
	
	//data fields
	private String firstName;
	private String lastName;
	private int age;
	
	//constructor
	public Person(String fn, String ln, int age)
	{
		this.firstName = fn;
		this.lastName = ln;
		this.age = age;
	}
	
	//methods
	public String getFirstName()
	{
		return this.firstName;
	}
	
	public String getLastName()
	{
		return this.lastName;
	}
	
	public int getAge()
	{
		return this.age;
	}
	
	public void setFirstName(String fn)
	{
		this.firstName = fn;
	}
	
	public void setLastName(String ln)
	{
		this.lastName = ln;
	}
	
	public void setAge(int age)
	{
		this.age = age;
	}
	
	public static void printOut(Person p) 
	{
		System.out.println(p.getFirstName() + " " + p.getLastName() + ": " + p.getAge());
	}
	
	public static void main(String[] args) {
		Person k = new Person("mike", "mccreesh", 20);
		printOut(k);
	}
}
