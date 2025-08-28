package com.fabo.sarahMath;

import org.junit.Test;
import static org.junit.Assert.*;

public class BasicMathUnitTest {

    @Test
    public void testAdd() {
        BasicMath bm = new BasicMath(BasicMath.ADD, 10, 10);
        int expected = 20;
        int actual = bm.doTheMath();
        assertEquals(expected, actual);
    }

    @Test
    public void testSub() {
        BasicMath bm = new BasicMath(BasicMath.SUB, 13, 6);
        int expected = 7;
        int actual = bm.doTheMath();
        assertEquals(expected, actual);
    }

    @Test
    public void testMul() {
        BasicMath bm = new BasicMath(BasicMath.MUL, 10, 2);
        int expected = 20;
        int actual = bm.doTheMath();
        assertEquals(expected, actual);
    }

    @Test
    public void testDiv() {
        BasicMath bm = new BasicMath(BasicMath.DIV, 10, 5);
        int expected = 2;
        int actual = bm.doTheMath();
        assertEquals(expected, actual);
    }
}
