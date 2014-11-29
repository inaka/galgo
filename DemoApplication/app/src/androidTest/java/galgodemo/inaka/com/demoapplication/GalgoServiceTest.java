package galgodemo.inaka.com.demoapplication;

import android.content.Intent;
import android.test.ServiceTestCase;
import android.test.suitebuilder.annotation.LargeTest;
import android.util.Log;

import com.inaka.galgo.GalgoService;

/**
 * Created by Arjan on 2014-11-27.
 */
public class GalgoServiceTest extends ServiceTestCase<GalgoService> {
    private GalgoService myService;
    private MainActivity myActivity;

    /**
     * Constructor
     *
     * @param serviceClass The type of the service under test.
     */
    public GalgoServiceTest(Class<GalgoService> serviceClass) {
        super(serviceClass);
    }

    public GalgoServiceTest() {
        super(GalgoService.class);
    }

    @Override
    protected void setUp() throws Exception{
        super.setUp();

        Intent intent = new Intent();
        intent.setClass(getContext(), GalgoService.class);
        startService(intent);
        myService = getService();
        myService.displayText("hello world", Log.DEBUG);
    }

    public void onCreateTest() {
        assertEquals(false, true);
    }

    public void displayTextTest() {

    }
}

