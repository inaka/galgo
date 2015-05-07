package com.inaka.galgoTests;

import junit.framework.TestCase;

/**
 * Created by guillea on 5/7/15.
 */
public class JUnitGalgoTest extends TestCase {

    public void testPositive() {
        assertEquals("Robo Working !", "Robo Working !");
    }

    public void testNegative() {
        assertEquals("Robo Working !", "Robo Not Working !");
    }

}
