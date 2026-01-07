/*
 * BasicMathUnitTest.java     1.82 01/03/2026
 *
 * Copyright (c) 2025-2026 Francis A Burns.
 * 1140 E Marlin Dr. Chandler, Arizona 85286, U.S.A.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Francis A Burns. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Francis.
 */
package com.fabo.mathlesson.domain;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test the basic math functions
 */
public class BasicMathUnitTest {
    /**
     * Test the add function
     */
    @Test
    public void testAdd() {
        BasicMath bm = new BasicMath(BasicMath.ADD, 10, 10);
        int expected = 20;
        int actual = bm.doTheMath();
        assertEquals(expected, actual);
    }

    /**
     * Test the subtract function
     */
    @Test
    public void testSub() {
        BasicMath bm = new BasicMath(BasicMath.SUB, 13, 6);
        int expected = 7;
        int actual = bm.doTheMath();
        assertEquals(expected, actual);
    }

    /**
     * Test the multiply function
     */
    @Test
    public void testMul() {
        BasicMath bm = new BasicMath(BasicMath.MUL, 10, 2);
        int expected = 20;
        int actual = bm.doTheMath();
        assertEquals(expected, actual);
    }

    /**
     * Test the divide function
     */
    @Test
    public void testDiv() {
        BasicMath bm = new BasicMath(BasicMath.DIV, 10, 5);
        int expected = 2;
        int actual = bm.doTheMath();
        assertEquals(expected, actual);
    }
}
