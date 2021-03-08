public class ThreadResources {

    public void resourceMethod() {
        long resourceCount = 0;
        resourceCount++;
    }

    public void objectResources(){
        LocalObject myObject = new LocalObject();
        myObject.callMethod();
    }

    public void methodTest(LocalObject localObject){
        localObject.setValue("test");
    }
}

class LocalObject{

    public void callMethod(){

    }

    public void setValue(String test) {
    }
}

class NotThreadSafe{
    //builder is unsafe
    StringBuilder builder = new StringBuilder();

    public void add(String text){
        this.builder.append(text);
    }

}
// Create 2 threads --> A and B
// NotThreadSafe nts = new notThreadSafe()
//Both threads A & B use the same nts instance
//Both call add simultaneously which will cause race conditions

// references to objects are stored on the local thread stack whereas the object is stored on the heap
// local objects from all threads are saved to the same heap, but they are thread-safe as long as the variable is not passed to a method outside of the Thread



/**
 * Deadlock prevention
 *
 * Lock timeout, if it hasn't got all locks received after waiting. All locks have been released so others can obtain locks
 *
 * Deadlock Detection: Uses a map to store requests as well as which threads have the lock currently, sample solution: release all locks and examine if the same problem occurs
 * Assign priority to certain threads so that only one or a few threads backup and drop their lock
 *
 **/

