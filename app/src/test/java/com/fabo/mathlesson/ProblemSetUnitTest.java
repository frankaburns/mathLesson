package com.fabo.mathlesson;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import com.fabo.mathlesson.domain.BasicMath;
import com.fabo.mathlesson.domain.ProblemGenerator;


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
    @Test
    public void testRange100s() {
        ProblemGenerator pg = new ProblemGenerator(100);
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
    @Test
    public void testRange1000s() {
        ProblemGenerator pg = new ProblemGenerator(1000);
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