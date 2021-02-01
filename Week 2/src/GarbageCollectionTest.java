public class GarbageCollectionTest {

    public void finalize(){
        System.out.println("Object is garbage collected");
    }
    public static void main(String[] args) {
        GarbageCollectionTest gcTest1 = new GarbageCollectionTest();
        GarbageCollectionTest gcTest2 = new GarbageCollectionTest();

        gcTest1 = null;
        gcTest2 = null;

        System.gc();
    }

}
