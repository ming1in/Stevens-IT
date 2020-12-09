package recitation.people;

public class Undergrad extends Student{
	private String year;
	
	public Undergrad(String fn, String ln, int age, double gpa, String year)
	{
		super(fn, ln, age, gpa);
		this.year = year;
	}
	
	public String getYear()
	{
		return this.year;
	}
	
	public void setYear(String year)
	{
		this.year = year;
	}
	
	public static void printOut(Undergrad s)
	{
		System.out.println(s.getFirstName() + " " + s.getLastName() + ": " + s.getAge() + ", " + s.getGpa() + ", " + s.getYear());
	}
	
	public static void main(String[] args) {
		Undergrad k = new Undergrad("mike", "mccreesh", 20, 4.0, "Junior");
		printOut(k);
	}
}
