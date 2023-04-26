package itemmanagement;

import db.DBItemList;

import java.util.concurrent.CountDownLatch;

public class TimerItemClass {

    public static void setTimer(MyItemList mfl) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Worker worker = new Worker(10000, latch);
        worker.start();
        latch.await();
        DBItemList db = new DBItemList();
        db.PowerItem(mfl);
    }


}
