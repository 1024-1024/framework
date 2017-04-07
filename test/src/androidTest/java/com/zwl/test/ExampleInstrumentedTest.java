package com.zwl.test;

import android.support.test.runner.AndroidJUnit4;
import android.util.SparseArray;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        SparseArray<String> a = new SparseArray<String>();
        a.put(0, "11");
        System.out.println(a.indexOfKey(2) < 0);
    }
}
