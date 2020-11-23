package Maze;

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
      PairInt newPairInt = (PairInt) p;
      if (this.x == newPairInt.getX() && this.y == newPairInt.getY()) {
        return true;
      } else {
        return false;
      }
    } else {
      return false;
    }
  }

  public String toString() {
    StringBuilder result = new StringBuilder();
    result.append('(');
    result.append(this.x);
    result.append(',');
    result.append(this.y);
    result.append(')');
    return result.toString();
  }

  public PairInt copy() {
    return new PairInt(this.x, this.y);
  }
}
