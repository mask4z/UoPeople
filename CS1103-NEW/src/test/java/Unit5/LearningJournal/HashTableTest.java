package Unit5.LearningJournal;

class HashTableTest {

    public static void main (String[] args) {

        HashTable test = new HashTable(10);
        test.put("test", "testing");
        System.out.println(test.containsKey("test"));
        test.put("test", "AnotherTest");
        System.out.println(test.get("test"));
    }
}