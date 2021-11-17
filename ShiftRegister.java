import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class ShiftRegister {
   
   static Scanner kb = new Scanner(System.in);
  public static void main(String[] args) {

    ArrayList<String> aList = new ArrayList();
    aList = getVariables();
    ArrayList<String> switches = getSwitches();

    printList(aList);

    aList = shift(aList, switches);

    printList(aList);

    while(!aList.get(0).equals("z")) {
      aList = shift(aList, switches);
      printList(aList);
    }

    kb.close();
  }

  public static ArrayList<String> shift (ArrayList<String> list, ArrayList<String> switches) {
      String topElement = list.get(0);

      for(String s : switches) {
         topElement += list.get(Integer.parseInt(s));
      }

      for (int i = 0; i < list.size() - 1; i++) {
         Collections.swap(list, i, i+1);
      }

      topElement = containsEvenNumber(topElement);
      list.set(list.size() - 1, topElement);

      return list;
  }

  public static ArrayList<String> getSwitches() {
    System.out.println("Where are the switches? (int; ex. 123 means there are switches after variables a1,a2, and a3)");
    String switchString = kb.next();
    ArrayList<String> switchList = new ArrayList<>();
    for (int i = 0; i < switchString.length(); i++) {
      switchList.add(switchString.substring(i, i + 1));
    }
    return switchList;
  }

  public static void printList(ArrayList<String> list) {
      for(int i = 0; i < list.size(); i++) {
          System.out.print(list.get(i) + ", ");
      }
      System.out.println();
  }

  public static ArrayList<String> getVariables() {
      ArrayList<String> varList = new ArrayList();
      ArrayList<String> tempList = new ArrayList();
      tempList.add("z");
      tempList.add("o");
      tempList.add("t");
      tempList.add("h");
      tempList.add("f");
      tempList.add("v");
      tempList.add("x");
      tempList.add("s");
      tempList.add("e");
      tempList.add("n");
      tempList.add("t");
    int value = 0;
    while(value == 0 || value > 11) {
        System.out.println("How many variables will be used? (int; max 11)");
        value = kb.nextInt();
    }

    for(int i = 0; i < value; i++) {
        varList.add(tempList.get(i));
    }
    return varList;
  }

  public static String containsEvenNumber(String s1) {
     int length = s1.length();
     for (int i = 0; i < length - 1; i++) {
        int n = 1;
        ArrayList<Integer> indices = new ArrayList<>();
        indices.add(0 + i);
        for (int j = i + 1; j < length; j++) {
           if(s1.charAt(i) == s1.charAt(j)) {
              n++;
              indices.add(j);
           }
        }
        if(n > 1) {
           if(n % 2 == 1) {
              indices.remove(0);
           }
           int removed = 0;
           while(!indices.isEmpty()) {
              s1 = charRemoveAt(s1, indices.get(0) - removed);
              indices.remove(0);
              removed++;
           }
           length = s1.length();
           i--;
        }
     }
     return s1;
  }
  public static String charRemoveAt(String str, int p) {
     return str.substring(0, p) + str.substring(p + 1);
  }
}
