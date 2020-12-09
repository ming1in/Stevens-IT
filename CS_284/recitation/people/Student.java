package recitation.people;

public class Student extends Person{
	
	private double gpa;
	
	public Student(String fn, String ln, int age, double gpa)
	{
		super(fn, ln, age);
		this.gpa = gpa;
	}
	
	public double getGpa()
	{
		return this.gpa;
	}
	
	public void setGpa(double gpa)
	{
		this.gpa = gpa;
	}
	
	public static void printOut(Student s)
	{
		System.out.println(s.getFirstName() + " " + s.getLastName() + ": " + s.getAge() + ", " + s.getGpa());
	}
	
	public static void main(String[] args) {
		Student k = new Student("mike", "mccreesh", 20, 4.0);
		printOut(k);
	}
}
