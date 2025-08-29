package com.fabo.mathlesson.domain;
public class BasicMath {

  public final static int ADD = 0;
  public final static int SUB = 1;
  public final static int MUL = 2;
  public final static int DIV = 3;
  private boolean  status = true;
  private boolean printStats = false;
  private long start = 0;
  private long elapsed;
  private final int function;
  private final int numerator;
  private final int denominator;

  /**
   *
   * @param func - math function (0-add, 1-sub, 2-mul, 3-div
   * @param top - numerator value
   * @param bottom - denominator value
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
    if (printStats) {
      System.out.println("problem time: " + elapsed / 1000);
    }
  }

  /**
   * getters and setters
   */
  public void setStatus (boolean s) {
    status = s;
  }

  public boolean getStatus () {
    return status;
  }

  public long getElapsed () {
    return elapsed;
  }

  public int getNumerator () {
    start = System.currentTimeMillis();
    return (numerator);
  }

  public int getDenominator () {
    return (denominator);
  }
}