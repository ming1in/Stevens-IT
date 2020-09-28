public class Person {

  //data field aka property
  private String firstName;
  private String lastName;
  private int age;

  //constructor
  public Person(String fn, String ln, int age) {
    this.firstName = fn;
    this.lastName = ln;
    this.age = age;
  }

  public String getLastName() { //getter
    return this.lastName;
  }
  
  public String getFirstName() {
    return this.firstName;
  }

  public int getAge() {
    return this.age;
  }

  public void setFirstName(String fn) { //setter
    this.firstName = fn;
  }

  public void setLastName(String ln) {
    this.lastName = ln;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public static void printOut(Person p) {
    System.out.println(p.getFirstName());
  }
  
  public static void main(String[] args) {
    Person k = new Person("ssd", "kss", 20);

    printOut(k);
  }

}

