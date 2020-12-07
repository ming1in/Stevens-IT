package homeworks.hw_6;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.junit.jupiter.api.Test;

class AnagramsTest {

  @Test
  void test1() {
    Anagrams a = new Anagrams();
    try {
      a.processFile("words_alpha.txt");
    } catch (IOException e1) {
      e1.printStackTrace();
    }
    ArrayList<Map.Entry<Long, ArrayList<String>>> maxEntries = a.getMaxEntries();
    int length = maxEntries.get(0).getValue().size();
    long key = maxEntries.get(0).getKey();
    assertEquals(key, 236204078);
    assertEquals(length, 15);
  }

}
