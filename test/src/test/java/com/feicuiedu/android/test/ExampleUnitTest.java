package com.feicuiedu.android.test;

import org.junit.Test;

import java.util.Timer;
import java.util.TimerTask;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test_timer() {

        System.out.println(">>>>>>>>>>>>>>>>");
        Timer timer = new Timer();

        TimerTask ts = new TimerTask() {

            @Override
            public void run() {
                System.out.println(System.currentTimeMillis());
                    
            }
        };

        timer.schedule(ts,100,1000);
    }
}