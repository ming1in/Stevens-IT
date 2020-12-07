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
      a.processFile("/Users/minglin/Stevens-IT/CS_284/homeworks/hw_6/words_alpha.txt");
    } catch (IOException e1) {
      e1.printStackTrace();
    }

    ArrayList<Map.Entry<Long, ArrayList<String>>> maxEntries = a.getMaxEntries();
    assertEquals(maxEntries.get(0).getKey(), 236204078);
    assertEquals(maxEntries.get(0).getValue().size(), 15);
  }

}
