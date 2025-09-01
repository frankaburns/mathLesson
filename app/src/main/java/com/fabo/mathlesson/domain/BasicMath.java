package com.fabo.mathlesson.domain;

/**
 * This class defines a math problem the constructor sets the function(Add, Sub, Mul, Div).
 * The constructor takes a Numerator and a Denominator. The class also times from the last
 * getNumerator to the answer entered.
 */
public class BasicMath {

  /**
   * * Public addition static
   */

  public final static int ADD = 0;
  /**
   * Public subtraction static
   */
  public final static int SUB = 1;

   /**
   * Public multiplication static
   */
  public final static int MUL = 2;

  /**
   * Public division static
   */
  public final static int DIV = 3;

  /**
   * Private variables
   */
  private boolean  status = true;
  private long start = 0;
  private long elapsed;
  private final int function;
  private final int numerator;
  private final int denominator;

  /**
   * Create class set function and the Numerator and Denominator.
   *
   * @param func - math function (0-add, 1-sub, 2-mul, 3-div
   * @param top - numerator value
   * @param bottom - denominator value
   *
   * returns - BasicMath object configured to the input values
   */
  public BasicMath(int func, int top, int bottom) {
    function    = func;
    numerator   = top;
    denominator = bottom;
  }

  /**
   * perform the math operation requested  on the numerator and denominator
   * @return - calculated value
   */
  public int doTheMath () {

    int Result;

    switch (function) {
      case ADD:
        Result = numerator + denominator;
        break;

      case SUB:
        Result = numerator - denominator;
        break;

      case MUL:
        Result = numerator * denominator;
        break;

      case DIV:
        Result = numerator / denominator;
        break;

      default:
        Result = -1;
        break;
    }

    return Result;
  }

  /**
   * calculate the problem time
   */
  public void setEnd( )
  {
    long end = System.currentTimeMillis();
    elapsed = end - start;
  }

  /**
   * Set a problem status
   * @param s - right/wrong (true/false)
   */
  public void setStatus (boolean s) {
    status = s;
  }

  /**
   * Get the problem status right/wrong (true/false)
   * @return Status
   */
  public boolean getStatus () {
    return status;
  }

  /**
   * Get the problem execution time (milliseconds)
   * @return elapsed
   */
  public long getElapsed () {
    return elapsed;
  }

  /**
   * Get the problem Numerator
   * @return Numerator
   */
  public int getNumerator () {
    start = System.currentTimeMillis();
    return (numerator);
  }

  /**
   * Get the problem Denominator
   * @return Denominator
   */
  public int getDenominator () {
    return (denominator);
  }
}