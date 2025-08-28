package com.fabo.mathlesson.domain;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public
class ProblemGenerator {

    public  static int     lessonLevel;
    public  static int     startRow;
    public  static int     startCol;
    public  static int     endRow;
    public  static int     endCol;
    public  static int     numWrong;
    public  static int     problemsSolved;
    public  static int     numProblems;
    public  static int     lessonFunction; // 0-ADD, 1-SUB, 2-MUL, 3-DIV
    public  static boolean randomProblem;
    public  static boolean showRandom = false;

    //
    // A list of BasicMath objects, the main lesson driver.
    List<BasicMath> problemSet = new ArrayList<>();

    /**
     * 
     * @param size - an arbitrary size to begin.
     */
    public ProblemGenerator(int size) {
        endRow         = size;
        endCol         = size;
        startRow       = 0;
        startCol       = 0;
        numWrong       = 0;
        lessonLevel    = size/10;
        numProblems    = 10;
        randomProblem  = false;
        problemsSolved = 0;
    }


    /**
     * 
     * @param n - number of problems for this run
     */
    public void setNumProblems ( int n) {
      numProblems = n;
    }

    /**
     * 
     * @param p - randomize the problem objects
     */
    public void setRandomProblem ( boolean p) {
      randomProblem = p;
    }

    /**
     *
     * @param f - lesson function add, sub, mul, div
     */
    public void setLessonFunction (int f) {
        lessonFunction = f;
    }

    /**
     *
     * @param f - the start # of the beginning of a range
     */
    public void setStartRow (int f) { startRow = f; }

    /**
     *
     * @param f - the end # of the range
     */
    public void setEndRow (int f) { endRow = f; }

    /**
     *
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
        }
    }

    /**
     * 
     * @return number of problems selected
     */
    public int getNumProblems () {
      return (numProblems);
    }

    /**
     * 
     * @return the problem set size based on level and function
     */
    public int getProblemCount () {
      return problemSet.size();
    }

    /**
     *
     * @return - row size based on selected lesson level
     */
    public int getRowCount () {
        return (lessonLevel == 0) ? 10 : (lessonLevel == 1) ? 100 : 1000;
    }

    /**
     * 
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
     *
     * @return get he next problem in the list
     */
    public BasicMath getProblem (int index) {

        if (index < problemSet.size()) {
            return problemSet.get(index);
        } else {
            return null;
        }
    }

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
     * randomly move the problem objects around in the list
     */
    private void shuffleArray () {
      Random random = new Random();
      int newPosition;
      int bound = problemSet.size();
      for( int prob = 0; prob < problemSet.size(); prob++ ){
          newPosition = random.nextInt(bound);
          BasicMath tmp = problemSet.get(newPosition);
          problemSet.set(newPosition, problemSet.get(prob));
          problemSet.set(prob, tmp);
          if (showRandom) {
              System.out.println("swapping " + prob + " with " + newPosition);
          }
       }
    }

    /**
     * Generate the requested problem set
     */
    public void buildProblemSet () {
        // build full problem set (100, 10,000, 1,000,000)
        // from 1 to size representing digits (0-10, 1-100, 2-1000)
        // build a problem from each main loop index and inner loop index
        // 0 = 100, 1 = 10,000, 2 = 1,000,000
        //
        for( int i = startRow, iPlusOne = (startRow == 0) ? 1 : startRow; i < endRow; i++, iPlusOne++ ) {
            for( int j = startCol, jPlusOne = startCol+1; j < endCol; j++, jPlusOne++ ){
               problemSet.add(new BasicMath(lessonFunction, iPlusOne, jPlusOne));
            }
        }

        // if asked to randomize, randomProblem == true
        //
        if (randomProblem) {
            shuffleArray ();
        }

        // Subtract, special case, filter out any with
        // with the numerator greater than the denominator
        //
        if (lessonFunction == BasicMath.SUB) {
            for (int i=0; i<problemSet.size(); ) {
                if (problemSet.get(i).getNumerator() < problemSet.get(i).getDenominator()) {
                    problemSet.remove(i);
                } else { i++; }
            }
        }

        // divide, special case, filter out any with
        // with the numerator greater than the denominator
        // and is divisible evenly by the denominator.
        //
        if (lessonFunction == BasicMath.DIV) {
            for (int i=0; i<problemSet.size(); ) {
                if ( (problemSet.get(i).getNumerator() < problemSet.get(i).getDenominator()) ||
                    ((problemSet.get(i).getNumerator()%problemSet.get(i).getDenominator()) != 0) ) {
                    problemSet.remove(i);
                } else { i++; }
            }
       }
       System.out.println(("Problem Set Size: " + problemSet.size()));
    }
}
