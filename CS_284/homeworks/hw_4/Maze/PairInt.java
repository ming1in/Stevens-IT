public class PairInt {
  private int x;
  private int y;

  public PairInt(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return this.x;
  }

  public int getY() {
    return this.y;
  }

  public void setX(int x) {
    this.x = x;
  }

  public void setY(int y) {
    this.y = y;
  }

  public boolean equals(Object p) {
    if (p instanceof PairInt) {
      if (this.x == p.getX() && this.y == p.getY()) {
        return true;
      } else {
        return false;
      }
    } else {
      return false;
    }
  }

  public String toString() {
    StringBuilder string = new StringBuilder();
    string.append('(');
    string.append(this.x);
    string.append(',');
    string.append(this.y);
    string.append(')');
    return string;
  }

  public PairInt copy() {
    return new PairInt(this.x, this.y);
  }
}
