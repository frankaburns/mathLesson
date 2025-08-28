package com.fabo.sarahMath;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ProblemSetUnitTest {

    @Test
    public void testAdd() {
        ProblemGenerator pg = new ProblemGenerator( 10);
        pg.setNumProblems(10);
        pg.setRandomProblem(true);
        pg.setLessonLevel(0);
        pg.setLessonFunction(0);
        pg.buildProblemSet();
        int expected = 100;
        int actual = pg.getProblemCount();
        assertEquals(expected, actual);
    }

    @Test
    public void testSub() {
        ProblemGenerator pg = new ProblemGenerator( 10);
        pg.setNumProblems(10);
        pg.setRandomProblem(true);
        pg.setLessonLevel(0);
        pg.setLessonFunction(1);
        pg.buildProblemSet();
        int expected = 55;
        int actual = pg.getProblemCount();
        assertEquals(expected, actual);
    }

    @Test
    public void testMul() {
        ProblemGenerator pg = new ProblemGenerator( 10);
        pg.setNumProblems(10);
        pg.setRandomProblem(true);
        pg.setLessonLevel(0);
        pg.setLessonFunction(2);
        pg.buildProblemSet();
        int expected = 100;
        int actual = pg.getProblemCount();
        assertEquals(expected, actual);
    }

    @Test
    public void testDiv() {
        ProblemGenerator pg = new ProblemGenerator( 10);
        pg.setNumProblems(10);
        pg.setRandomProblem(true);
        pg.setLessonLevel(0);
        pg.setLessonFunction(3);
        pg.buildProblemSet();
        int expected = 27;
        int actual = pg.getProblemCount();
        assertEquals(expected, actual);
    }
    @Test
    public void testRange10s() {
        ProblemGenerator pg = new ProblemGenerator(10);
        pg.setNumProblems(10);
        pg.setRandomProblem(false);
        pg.setLessonLevel(0);
        pg.setLessonFunction(3);
        pg.buildProblemSet();
        pg.setStartRow(3);
        pg.setEndRow(5)
        pg.buildProblemSet();
        System.out.println("Start Problem: " + pg.getProblem(0).getNumerator() + ", " + pg.getProblem(0).getDenominator());
        System.out.println("EndProblem Problem: " + pg.getProblem(pg.getProblemCount()-1).getNumerator() + ", " + pg.getProblem(pg.getProblemCount()-1).getDenominator());
        int expected = 100;
        int actual = pg.getProblemCount();
        assertEquals(expected, actual);
    }
    @Test
    public void testRange100s() {
        ProblemGenerator pg = new ProblemGenerator(100);
        pg.setNumProblems(100);
        pg.setRandomProblem(false);
        pg.setLessonLevel(1);
        pg.setLessonFunction(0);
        pg.setStartRow(30);
        pg.setEndRow(50)
        pg.buildProblemSet();
        int expected = 3000;
        System.out.println("Start Problem: " + pg.getProblem(0).getNumerator() + ", " + pg.getProblem(0).getDenominator());
        System.out.println("EndProblem Problem: " + pg.getProblem(pg.getProblemCount()-1).getNumerator() + ", " + pg.getProblem(pg.getProblemCount()-1).getDenominator());
        int actual = pg.getProblemCount();
        assertEquals(expected, actual);
    }
    @Test
    public void testRange1000s() {
        ProblemGenerator pg = new ProblemGenerator(1000);
        pg.setNumProblems(10);
        pg.setRandomProblem(false);
        pg.setLessonLevel(2);
        pg.setLessonFunction(3);
        pg.buildProblemSet();
        pg.setStartRow(300);
        pg.setEndRow(500)
        int expected = 300000;
        System.out.println("Start Problem: " + pg.getProblem(0).getNumerator() + ", " + pg.getProblem(0).getDenominator());
        System.out.println("EndProblem Problem: " + pg.getProblem(pg.getProblemCount()-1).getNumerator() + ", " + pg.getProblem(pg.getProblemCount()-1).getDenominator());
        int actual = pg.getProblemCount();
        assertEquals(expected, actual);
    }

};