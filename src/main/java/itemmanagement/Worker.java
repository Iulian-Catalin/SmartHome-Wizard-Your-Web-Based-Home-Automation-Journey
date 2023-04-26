package itemmanagement;

import java.util.concurrent.CountDownLatch;

public class Worker extends Thread {
    private int delay;
    private CountDownLatch latch;

    public Worker(int delay, CountDownLatch latch)
    {
        this.delay = delay;
        this.latch = latch;
    }

    @Override
    public void run()
    {
        try
        {
            Thread.sleep(delay);
            latch.countDown();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}