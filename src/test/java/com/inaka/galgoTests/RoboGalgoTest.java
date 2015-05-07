package com.inaka.galgoTests;

import com.inaka.galgo.BuildConfig;

import org.junit.Test;
import org.robolectric.Robolectric;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by guillea on 5/7/15.
 */

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class RoboGalgoTest {

    @Test
    public void positiveTest() {
        assertThat("Robo Working !", equalTo("Robo Working !"));
    }

    @Test
    public void negativeTest() {
        assertThat("Robo Working !", equalTo("Robo Not Working !"));
    }

}
