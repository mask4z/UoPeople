package Unit5.LearningJournal;

public class HashTableTest {

    public static void main (String[] args) {
        HashTable test = new HashTable(10);
        test.put("test", "testing");
        System.out.println(test.containsKey("test"));
        test.put("test", "AnotherTest");
        test.put("test2", "AnotherTest2");
        test.put("test3", "AnotherTest3");
        test.put("test4", "AnotherTest4");

        System.out.println(test.get("test"));
        System.out.println(test.remove("test3"));
        System.out.println(test.size());
    }
}
