package com.fabo.mathlesson.domain;

import java.util.ArrayList;
import java.util.List;

/*
 * ProblemGenerator.java     1.82 01/03/2026
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
 *
 * The ProblemGenerator class builds a List of BasicMath objects.  The generated list is defined by
 * several variables.<br>>
 * Note: Subtraction and Division are filtered to maintain whole digit solutions, so the problem set
 *       leve0 0 (10's) Addition Multiplication size 100, Subtraction size 56 and Division size 27.<br>>
 * The objects can be Ordered or randomized.  The size of the lesson is managed by the getProblem()
 * method and controlled by the lesser of the problem set size or configured number of problems.
 */
public
class ProblemGenerator {

    /*
        Lesson generation control variables
     */
    private  static int     lessonLevel;
    private  static int     startRow;
    private  static int     startCol;
    private  static int     endRow;
    private  static int     endCol;
    private  static int     problemsSolved;
    private  static int     numProblems;
    private  static int     lessonFunction; // 0-ADD, 1-SUB, 2-MUL, 3-DIV
    private  static boolean forceRandom;
    private  static boolean randomProblem;

    //
    // A list of BasicMath objects, the main lesson data.
    List<BasicMath> problemSet = new ArrayList<>();

    /**
     * Create a default problem generator with:
     *  - function - ADD
     *  - level - 0 (one digit)
     *  - random - false/ordered
     *  - numProblems - 10
     */
    public ProblemGenerator() {
        endRow         = 10;
        endCol         = 10;
        startRow       = 0;
        startCol       = 0;
        lessonLevel    = 0;
        numProblems    = 10;
        forceRandom    = false;
        randomProblem  = false;
        problemsSolved = 0;
    }

    /**
     * Set the number of problems for the lesson.
     * @param n - number of problems for this run
     */
    public void setNumProblems ( int n) {
      numProblems = n;
    }

    /**
     * Set the ordered/random for problems for the lesson.
     * @param p - randomize the problem objects
     */
    public void setRandomProblem ( boolean p) { randomProblem = p;
    }

    /**
     * Set the function for the problems in the lesson.
     * @param f - lesson function add, sub, mul, div
     */
    public void setLessonFunction (int f) {
        lessonFunction = f;
    }

    /**
     * Set the start range of problems for the lesson.
     * @param f - the start # of the beginning of a range
     */
    public void setStartRow (int f) { startRow = f; }

    /**
     * Set the end range of problems for the lesson.
     * @param f - the end # of the range
     */
    public void setEndRow (int f) { endRow = f; }

    /**
     * Set the bounds for the problem set based upon digit level
     * @param l - lesson level 0-3
     */
    public void setLessonLevel (int l) {

        lessonLevel = l;
        startRow = 0;
        if (lessonLevel == 0) {
            endRow = 10;
            endCol = 10;

        } else if (lessonLevel == 1) {
            endRow = 100;
            endCol = 100;
        } else if (lessonLevel == 2) {
            endRow = 1000;
            endCol = 1000;
            forceRandom = true;
        }
    }

    /**
     * Get the number of problems for the lesson
     * @return number of problems selected
     */
    public int getNumProblems () {
      return (numProblems);
    }

    /**
     *
     * Get the  problems set size
     * @return the problem set size based on level and function
     */
    public int getProblemCount () {
      return problemSet.size();
    }

    /**
     * Get the row count i.e. range
     * @return - row size based on selected lesson level
     */
    public int getRowCount () {
        return (lessonLevel == 0) ? 10 : (lessonLevel == 1) ? 100 : 1000;
    }

    /**
     * Get the next BasicMath object i.e. problem
     * @return get he next problem in the list
     */
    public BasicMath getProblem () {

        if (problemsSolved < Math.min(numProblems,problemSet.size())) {
           return problemSet.get(problemsSolved++);
        } else {
            problemsSolved = 0;
            return null;
        }
    }

    /**
     * Get a problem (BasicMath object) by index
     * @param index - the index in the ProblemSet List
     * @return - the BasicMath object referenced by index.
     */
    public BasicMath getProblem (int index) {

        if (index < problemSet.size()) {
            return problemSet.get(index);
        } else {
            return null;
        }
    }

    /**
     * Loop over the lesson and average the individual problem times
     * @return long average time
     */
    public long getAverageTime () {

        long totalTime = 0;
        long average = 0;
        int size = Math.min(numProblems,problemSet.size());

        for (int i=0; i<size; i++) {
            totalTime +=  problemSet.get(i).getElapsed();
        }
        average = (totalTime/size)/1000;
        return average;
    }

    /**
     *
     * Clear the current lesson list of problems
     */
    public void clearProblemSet () {

        problemSet.clear();
    }

    /**
     * Generate the requested problem set
     *    build full problem set (100, 10,000, 1,000,000)
     *    from 1 to size representing digits (0-10, 1-100, 2-1000)
     *    build a problem from each main loop index and inner loop index
     *    0 = 100, 1 = 10,000, 2 = 1,000,000
     * <p>
     *    if asked to randomize, randomProblem == true
     *    Subtract, special case, filter out any with
     *    with the numerator greater than the denominator
     * <p>
     *    divide, special case, filter out any with
     *    with the numerator greater than the denominator
     *    and is divisible evenly by the denominator.
     */
    public void buildProblemSet () {
       if (forceRandom || randomProblem) {

            int min = 1;

            int oper1     = 0;
            int oper2     = 0;

            int incMax    = endCol;
            int increment = 0;

            if (lessonFunction == BasicMath.ADD || lessonFunction == BasicMath.MUL) {

                if (endCol == 1000 || endCol == 100) { incMax = increment = endCol/numProblems; }

                for (int i = 1; i<numProblems; i++) {
                    oper1 = min + (int)(Math.random() * ((incMax - min) + 1));
                    oper2 = min + (int)(Math.random() * ((incMax - min) + 1));
                    problemSet.add(new BasicMath(lessonFunction, oper1, oper2));
                    incMax += increment;
                }
            } else if (lessonFunction == BasicMath.SUB) {

                if (endCol == 1000 || endCol == 100) { incMax = increment = endCol/numProblems; }

                while (problemSet.size() < numProblems) {
                    oper1 = min + (int) (Math.random() * ((incMax - min) + 1));
                    oper2 = min + (int) (Math.random() * ((incMax - min) + 1));

                    if (oper1 > oper2) {
                        problemSet.add(new BasicMath(lessonFunction, oper1, oper2));
                    } else {
                        problemSet.add(new BasicMath(lessonFunction, oper2, oper1));
                    }
                    incMax += increment;
                }
            } else if (lessonFunction == BasicMath.DIV) {

                if (endCol == 1000 || endCol == 100) { incMax = increment = endCol/numProblems; }

                while (problemSet.size() < numProblems) {
                    oper1 = min + (int) (Math.random() * ((incMax - min) + 1));
                    oper2 = min + (int) (Math.random() * ((incMax - min) + 1));

                    if ( (oper1 > oper2) && (oper1%oper2 == 0) ) {
                        problemSet.add(new BasicMath(lessonFunction, oper1, oper2));
                        incMax += increment;
                    }
                }
            }
        } else {
           if (lessonFunction == BasicMath.ADD || lessonFunction == BasicMath.MUL) {
               for (int i = startRow, iPlusOne = (startRow == 0) ? 1 : startRow; i < endRow && problemSet.size() <= numProblems; i++, iPlusOne++) {
                   for (int j = startCol, jPlusOne = startCol + 1; j < endCol&& problemSet.size() <= numProblems; j++, jPlusOne++) {
                       problemSet.add(new BasicMath(lessonFunction, iPlusOne, jPlusOne));
                   }
               }


           } else if (lessonFunction == BasicMath.SUB) {
               for (int i = startRow, iPlusOne = (startRow == 0) ? 1 : startRow; i < endRow&& problemSet.size() <= numProblems; i++, iPlusOne++) {
                   for (int j = startCol, jPlusOne = startCol + 1; j < endCol&& problemSet.size() <= numProblems; j++, jPlusOne++) {
                       if (iPlusOne > jPlusOne) {
                           problemSet.add(new BasicMath(lessonFunction, iPlusOne, jPlusOne));
                       }
                   }
               }
           } else if (lessonFunction == BasicMath.DIV) {
               for (int i = startRow, iPlusOne = (startRow == 0) ? 1 : startRow; i < endRow&& problemSet.size() <= numProblems; i++, iPlusOne++) {
                   for (int j = startCol, jPlusOne = startCol + 1; j < endCol&& problemSet.size() <= numProblems; j++, jPlusOne++) {
                       if ((iPlusOne > jPlusOne) && (iPlusOne % jPlusOne == 0)) {
                           problemSet.add(new BasicMath(lessonFunction, iPlusOne, jPlusOne));
                       }
                   }
               }
           }
       }


        System.out.println(("Problem Set Size: " + problemSet.size()));
    }
}
