public class Fruit implements Colorable {
	// data field
	private String color;
	private int acidity;
	public Fruit(String color, int acidity) {
		super();
		this.color = color;
		this.acidity = acidity;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getAcidity() {
		return acidity;
	}
	public void setAcidity(int acidity) {
		this.acidity = acidity;
	}
	
	
}
