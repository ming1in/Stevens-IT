/*
 Ming Lin
 I pledge my honor that i have abided by the stevens honor system
 */

public class BinaryNumber {
  //properties
  private int data[];
  private int length;

  //constructors
  BinaryNumber(int length) {
    this.data = new int[length];
    this.length = length;
  }

  BinaryNumber(String str) {
    int binary[] = new int[str.length()];

    for (int i = 0; i < str.length(); i++) {
      char c = str.charAt(i);
      binary[i] = java.lang.Character.getNumericValue(c);
    }

    length = str.length();
    data = binary;
  }

  public int getLength() {
    return this.length;
  }

  public int getDigit(int index) {
    if (index > length - 1 || index < 0) {
      throw new IndexOutOfBoundsException("The index provided doesn't exist");
    } else {
      return this.data[index];
    }
  }

  public int[] getInnerArray() {d
    return this.data;
  }

  public static int[] bwor(BinaryNumber bn1, BinaryNumber bn2) {
    if (bn1.length == bn2.length) {
      int[] result = new int[bn1.length];

      for (int i = 0; i < bn1.length; i++) {
        if (bn1.getDigit(i) == 1 || bn2.getDigit(i) == 1) {
          result[i] = 1;
        } else {
          result[i] = 0;
        }
      }
      return result;
    } else {
      throw new IllegalArgumentException("bn1 and bn2 must be the same length");
    }
  }

  public static int[] bwand(BinaryNumber bn1, BinaryNumber bn2) {
    if (bn1.length == bn2.length) {
      int[] result = new int[bn1.length];

      for (int i = 0; i < bn1.length; i++) {
        if (bn1.getDigit(i) == 1 && bn2.getDigit(i) == 1)
          result[i] = 1;
        else
          result[i] = 0;
      }

      return result;
    } else {
      throw new IllegalArgumentException("bn1 and bn2 must be the same length");
    }
  }

  public void bitShift(int direction, int amount) {
    if (amount < 0) {
      throw new IllegalArgumentException("amount has to be positive");

    }

    if (direction == 1) { //shift to the right
      int[] shiftedArray = new int[data.length - amount];

      for (int i = 0; i < data.length - amount; i++) {
        shiftedArray[i] = data[i];
      }

      this.data = shiftedArray;
    } else if (direction == -1) { //shift to the left
      int[] shiftedArray = new int[data.length + amount];

      for (int i = 0; i < data.length; i++) {
        shiftedArray[i] = data[i];
      }

      for (int i = 0; i < amount; i++) {
        shiftedArray[data.length + i] = 0;
      }

      this.data = shiftedArray;
    }
  }

  public void add(BinaryNumber aBinaryNumber) {
    int[] temp;

    if (this.length != aBinaryNumber.length) {
      temp = new int[this.length];
      int prependAmount = this.length - aBinaryNumber.length;
      int x = 0;

      for (int i = prependAmount; i < temp.length; i++) {
        temp[i] = aBinaryNumber.getDigit(x);
        x++;
      }

      aBinaryNumber.data = temp;
      aBinaryNumber.length = temp.length;
    }

    if (aBinaryNumber.length > this.length) {
      temp = new int[aBinaryNumber.length];
      int prependAmount = aBinaryNumber.length - this.length;
      int x = 0;

      for (int i = prependAmount; i < temp.length; i++) {
        temp[i] = this.data[x];
        x++;
      }

      this.data = temp;
      this.length = temp.length;
    }

    int carry = 0;
    int[] addedArray = new int[this.length];

    for (int i = this.length - 1; i >= 0; i--) {
      if (aBinaryNumber.getDigit(i) == 0 && this.data[i] == 0 && carry == 0) {
        addedArray[i] = 0;
        carry = 0;
      } else if (aBinaryNumber.getDigit(i) == 0 && this.data[i] == 0 && carry == 1) {
        addedArray[i] = 1;
        carry = 0;
      } else if (aBinaryNumber.getDigit(i) == 1 && this.data[i] == 1 && carry == 0) {
        addedArray[i] = 0;
        carry = 1;
      } else if (aBinaryNumber.getDigit(i) == 1 && this.data[i] == 1 && carry == 1) {
        addedArray[i] = 1;
        carry = 1;
      } else if (aBinaryNumber.getDigit(i) == 0 && this.data[i] == 1 && carry == 0) {
        addedArray[i] = 1;
        carry = 0;
      } else if (aBinaryNumber.getDigit(i) == 1 && this.data[i] == 0 && carry == 0) {
        addedArray[i] = 1;
        carry = 0;
      } else if (aBinaryNumber.getDigit(i) == 0 && this.data[i] == 1 && carry == 1) {
        addedArray[i] = 0;
        carry = 1;
      } else if (aBinaryNumber.getDigit(i) == 1 && this.data[i] == 0 && carry == 1) {
        addedArray[i] = 0;
        carry = 1;
      }
    }

    this.data = addedArray;
    this.length = addedArray.length;

    if (carry == 1) {
      int[] extraArray = new int[this.length + 1];
      int x = 0;

      int thisMuch = extraArray.length - this.length;
      extraArray[0] = 1;
      for (int i = thisMuch; i < extraArray.length; i++) {
        extraArray[i] = this.data[x];
        x++;
      }

      this.data = extraArray;
      this.length = extraArray.length;
    }

    for (int i = 0; i < this.data.length; i++) {
      System.out.println(this.data[i]);
    }
  }
  
  public String toString() {
    String str = "";

    for (int i = 0; i < data.length; i++) {
      str += data[i];
    }

    return str;
  }

  public int toDecimal() {
    int decimal = 0;
    for (int i = 0; i < data.length; i++) {
      if (data[i] == 1) {
        decimal += Math.pow(2, i);
      }
    }
    return decimal;
  }

  public static void main(String[] args) {
    BinaryNumber y = new BinaryNumber("10110");
    BinaryNumber x = new BinaryNumber("11101");
    
    y.add(x);
  }
}
