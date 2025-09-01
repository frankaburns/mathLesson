package com.fabo.mathlesson;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import com.fabo.mathlesson.domain.BasicMath;
import com.fabo.mathlesson.domain.ProblemGenerator;

/**
 * Test problem set generation
 */

public class ProblemSetUnitTest {

    /**
     * Verify the correct problem set size for 1 digit addition lessons
     */
    @Test
    public void testAdd() {
        ProblemGenerator pg = new ProblemGenerator();
        pg.setNumProblems(10);
        pg.setRandomProblem(true);
        pg.setLessonLevel(0);
        pg.setLessonFunction(0);
        pg.buildProblemSet();
        int expected = 100;
        int actual = pg.getProblemCount();
        assertEquals(expected, actual);
    }

    /**
     * Verify the correct problem set size for 1 digit subtraction lessons
     */
    @Test
    public void testSub() {
        ProblemGenerator pg = new ProblemGenerator();
        pg.setNumProblems(10);
        pg.setRandomProblem(true);
        pg.setLessonLevel(0);
        pg.setLessonFunction(1);
        pg.buildProblemSet();
        int expected = 55;
        int actual = pg.getProblemCount();
        assertEquals(expected, actual);
    }

    /**
     * Verify the correct problem set size for 1 digit multiplication lessons
     */
    @Test
    public void testMul() {
        ProblemGenerator pg = new ProblemGenerator();
        pg.setNumProblems(10);
        pg.setRandomProblem(true);
        pg.setLessonLevel(0);
        pg.setLessonFunction(2);
        pg.buildProblemSet();
        int expected = 100;
        int actual = pg.getProblemCount();
        assertEquals(expected, actual);
    }

    /**
     * Verify the correct problem set size for 1 digit division lessons
     */
    @Test
    public void testDiv() {
        ProblemGenerator pg = new ProblemGenerator();
        pg.setNumProblems(10);
        pg.setRandomProblem(true);
        pg.setLessonLevel(0);
        pg.setLessonFunction(3);
        pg.buildProblemSet();
        int expected = 27;
        int actual = pg.getProblemCount();
        assertEquals(expected, actual);
    }

    /**
     * Test the range start and end for a 1 digit range of 3 to 5
     */
    @Test
    public void testRange10s() {
        ProblemGenerator pg = new ProblemGenerator();
        pg.setNumProblems(10);
        pg.setRandomProblem(false);
        pg.setLessonLevel(0);
        pg.setLessonFunction(0);
        pg.setStartRow(3);
        pg.setEndRow(5);
        pg.buildProblemSet();
        int expected = 0;
        int actual = 0;

        System.out.println("Start Problem: " + pg.getProblem(0).getNumerator() + ", " + pg.getProblem(0).getDenominator());

        expected = 3;
        actual = pg.getProblem(0).getNumerator();
        assertEquals(expected, actual);

        expected = 1;
        actual = pg.getProblem(0).getDenominator();
        assertEquals(expected, actual);

        System.out.println("EndProblem Problem: " + pg.getProblem(pg.getProblemCount()-1).getNumerator() + ", " + pg.getProblem(pg.getProblemCount()-1).getDenominator());

        expected = 4;
        actual = pg.getProblem(pg.getProblemCount()-1).getNumerator();
        assertEquals(expected, actual);

        expected = 10;
        actual = pg.getProblem(pg.getProblemCount()-1).getDenominator();
        assertEquals(expected, actual);

   }

    /**
     * Test the range start and end for a 10 digit range of 30 to 50
     */
    @Test
    public void testRange100s() {
        ProblemGenerator pg = new ProblemGenerator();
        pg.setNumProblems(100);
        pg.setRandomProblem(false);
        pg.setLessonLevel(1);
        pg.setLessonFunction(0);
        pg.setStartRow(30);
        pg.setEndRow(50);
        pg.buildProblemSet();
        int expected = 0;
        int actual = 0;

        System.out.println("Start Problem: " + pg.getProblem(0).getNumerator() + ", " + pg.getProblem(0).getDenominator());

        expected = 30;
        actual = pg.getProblem(0).getNumerator();
        assertEquals(expected, actual);
        System.out.println("Start Problem: " + pg.getProblem(0).getNumerator() + ", " + pg.getProblem(0).getDenominator());

        expected = 1;
        actual = pg.getProblem(0).getDenominator();
        assertEquals(expected, actual);

        System.out.println("EndProblem Problem: " + pg.getProblem(pg.getProblemCount()-1).getNumerator() + ", " + pg.getProblem(pg.getProblemCount()-1).getDenominator());

        expected = 49;
        actual = pg.getProblem(pg.getProblemCount()-1).getNumerator();
        assertEquals(expected, actual);

        expected = 100;
        actual = pg.getProblem(pg.getProblemCount()-1).getDenominator();
        assertEquals(expected, actual);

    }

    /**
     * Test the range start and end for a 3 digit range of 300 to 500
     */
    @Test
    public void testRange1000s() {
        ProblemGenerator pg = new ProblemGenerator();
        pg.setNumProblems(10);
        pg.setRandomProblem(false);
        pg.setLessonLevel(2);
        pg.setLessonFunction(0);
        pg.setStartRow(300);
        pg.setEndRow(500);
        pg.buildProblemSet();

        int expected = 0;
        int actual = 0;

        System.out.println("Start Problem: " + pg.getProblem(0).getNumerator() + ", " + pg.getProblem(0).getDenominator());

        expected = 300;
        actual = pg.getProblem(0).getNumerator();
        assertEquals(expected, actual);
        System.out.println("Start Problem: " + pg.getProblem(0).getNumerator() + ", " + pg.getProblem(0).getDenominator());

        expected = 1;
        actual = pg.getProblem(0).getDenominator();
        assertEquals(expected, actual);

        System.out.println("EndProblem Problem: " + pg.getProblem(pg.getProblemCount()-1).getNumerator() + ", " + pg.getProblem(pg.getProblemCount()-1).getDenominator());

        expected = 499;
        actual = pg.getProblem(pg.getProblemCount()-1).getNumerator();
        assertEquals(expected, actual);

        expected = 1000;
        actual = pg.getProblem(pg.getProblemCount()-1).getDenominator();
        assertEquals(expected, actual);
    }

};