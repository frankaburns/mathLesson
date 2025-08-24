package com.fabo.mathlesson.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.fabo.mathlesson.R;
import com.fabo.mathlesson.databinding.FragmentLessonBinding;
import com.fabo.mathlesson.domain.BasicMath;
import com.fabo.mathlesson.domain.ProblemGenerator;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class FragmentLesson extends Fragment {

    int function = 0;
    int level = 0;
    int random = 0;
    int range = 0;
    int numProblems = 0;
    int wrong = 0;
    int numWrong = 0;
    int correct = 0;
    String blank = " ";
    String functionStr;
    String number = null;
    String result = "";
   boolean buttonEqualsControl = false;
    long end;
    long start;
    long averageTime;
    BasicMath problem = null;
    private ProblemGenerator pg = null;

    private @NonNull FragmentLessonBinding fargmentLessonBinding;
    SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fargmentLessonBinding = FragmentLessonBinding.inflate(inflater,container,false);

        if (pg == null) {
            pg = new ProblemGenerator(20);
        } else {
            pg.clearProblemSet();
        }

        correct = 0;

        if (getArguments() != null){
            function = getArguments().getInt("function");
            pg.setLessonFunction(function);
            switch (function) {
                case 0:
                    pg.setLessonFunction(BasicMath.ADD);
                    functionStr = "+ ";
                    break;
                case 1:
                    pg.setLessonFunction(BasicMath.SUB);
                    functionStr = "- ";
                    break;
                case 2:
                    pg.setLessonFunction(BasicMath.MUL);
                    functionStr = "x ";
                    break;
                case 3:
                    pg.setLessonFunction(BasicMath.DIV);
                    functionStr = "/ ";
                    break;
            }

            level = getArguments().getInt("level");
            pg.setLessonLevel(level);
            switch (level) {
                case 0:
                    end = 10;
                    break;
                case 1:
                    end = 100;
                     break;
                case 2:
                    end = 1000;
                    break;
            }

            random = getArguments().getInt("random");
            pg.setRandomProblem(random == 1);

            range = getArguments().getInt("range");
            if (range == 1) {
                start = getArguments().getInt("start");
                pg.setStartRow((int)start);
                end = getArguments().getInt("end");
                pg.setEndRow((int)end);
            }

            numProblems = getArguments().getInt("num");
            pg.setNumProblems(numProblems);
        }

        pg.buildProblemSet();
        problem = pg.getProblem();

        String numerator = "" + problem.getNumerator();
        fargmentLessonBinding.textViewNumerator.setText(numerator);
        String denominator = functionStr + problem.getDenominator();
        SpannableString spannableString = new SpannableString(denominator);

        // Underline the entire string
        spannableString.setSpan(new UnderlineSpan(), 0, spannableString.length(), 0);

        // Or underline a specific portion (e.g., "underlined")
        // spannableString.setSpan(new UnderlineSpan(), 12, 22, 0);
        fargmentLessonBinding.textViewDenominator.setText(spannableString);

        fargmentLessonBinding.btn0.setOnClickListener(v -> onNumberClicked("0"));
        fargmentLessonBinding.btn1.setOnClickListener(v -> onNumberClicked("1"));
        fargmentLessonBinding.btn2.setOnClickListener(v -> onNumberClicked("2"));
        fargmentLessonBinding.btn3.setOnClickListener(v -> onNumberClicked("3"));
        fargmentLessonBinding.btn4.setOnClickListener(v -> onNumberClicked("4"));
        fargmentLessonBinding.btn5.setOnClickListener(v -> onNumberClicked("5"));
        fargmentLessonBinding.btn6.setOnClickListener(v -> onNumberClicked("6"));
        fargmentLessonBinding.btn7.setOnClickListener(v -> onNumberClicked("7"));
        fargmentLessonBinding.btn8.setOnClickListener(v -> onNumberClicked("8"));
        fargmentLessonBinding.btn9.setOnClickListener(v -> onNumberClicked("9"));

        fargmentLessonBinding.btnDel.setOnClickListener(v -> {

            if (number == null || number.length() == 1) {
                onButtonACClicked();
            } else {
                number = number.substring(0, number.length() - 1);
                fargmentLessonBinding.textViewResult.setText(number);
            }

        });

        fargmentLessonBinding.btnEquals.setOnClickListener(v -> {

            // fargmentLessonBinding.textViewComment.setText(blank);
            fargmentLessonBinding.textViewResult.setText(result);
            problemSolution();
            number = null;
            buttonEqualsControl = true;
        });

        // Inflate the layout for this fragment
        return fargmentLessonBinding.getRoot();
    }


    public void problemSolution(){
        //
        // make sure problem exists
        int size = Math.min(pg.getNumProblems(), pg.getProblemCount());

        if (problem != null && number != null) {
            //
            // ask the object for the answer and compare it to the supplied answer
            // wrong:
            // give two tries then put the answer up for third try
            if (problem.doTheMath() != Integer.parseInt(number)) {
                if (wrong < 3) {
                    String msg;
                    wrong++;
                    problem.setStatus(false);
                    if (wrong == 2) {
                        msg = "Try: " +  problem.doTheMath();
                    } else {
                        msg = "Please try again.";
                    }
                    fargmentLessonBinding.textViewResult.setText(msg);
                    //
                    // hit max wrong, note and get next problem
                } else {
                    wrong = 0;
                    numWrong++;
                    problem.setEnd();
                    problem = pg.getProblem();
                }
                //
                // correct:
                //         if it was wrong clean up
                //         get the next problem
            } else {
                if (wrong > 0) {
                    wrong = 0;
                    numWrong++;
                } else {
                    correct++;
                }
                problem.setEnd();
                problem = pg.getProblem();
            }

            //
            // make sure we have a problem
            //
            if (problem != null) {

                fargmentLessonBinding.textViewCorrect.setText(String.valueOf(correct));
                fargmentLessonBinding.textViewWrong.setText(String.valueOf(numWrong));
                fargmentLessonBinding.textViewTotal.setText(String.valueOf(size));

                String numerator = "" + problem.getNumerator();
                fargmentLessonBinding.textViewNumerator.setText(numerator);
                String denominator = functionStr + problem.getDenominator();
                SpannableString spannableString = new SpannableString(denominator);

                // Underline the entire string
                spannableString.setSpan(new UnderlineSpan(), 0, spannableString.length(), 0);

                // Or underline a specific portion (e.g., "underlined")
                // spannableString.setSpan(new UnderlineSpan(), 12, 22, 0);
                fargmentLessonBinding.textViewDenominator.setText(spannableString);
            }
        } else {

            numWrong++;
            if (problem != null) {
                problem.setEnd();
                problem = pg.getProblem();
            }

            if (problem != null) {
                fargmentLessonBinding.textViewCorrect.setText(String.valueOf(correct));
                fargmentLessonBinding.textViewWrong.setText(String.valueOf(numWrong));
                fargmentLessonBinding.textViewTotal.setText(String.valueOf(size));

                String numerator = "" + problem.getNumerator();
                fargmentLessonBinding.textViewNumerator.setText(numerator);
                String denominator = functionStr + problem.getDenominator();
                SpannableString spannableString = new SpannableString(denominator);

                // Underline the entire string
                spannableString.setSpan(new UnderlineSpan(), 0, spannableString.length(), 0);

                // Or underline a specific portion (e.g., "underlined")
                // spannableString.setSpan(new UnderlineSpan(), 12, 22, 0);
                fargmentLessonBinding.textViewDenominator.setText(spannableString);
            }
        }

        if (problem == null) {

            averageTime = pg.getAverageTime();
            // send results to result fragment
            Bundle bundle = new Bundle();
            bundle.putInt("correct",correct);
            bundle.putInt("wrong",numWrong);
            bundle.putInt("total",size);
            bundle.putInt("averageTime",(int)averageTime);

            Navigation.findNavController(this.fargmentLessonBinding.getRoot()).navigate(
                    R.id.action_fragmentLesson_to_fragmentResult,
                    bundle,
                    new NavOptions.Builder().setPopUpTo(R.id.fragmentConfigure,false).build()
            );
        }
    }

    /**
     *
     * @param clickedNumber - number clicked as defined by resources
     *                        delivered by android.
     */
    public void onNumberClicked(String clickedNumber){
        if (number == null || buttonEqualsControl){
            number = clickedNumber;
        }else {
            number += clickedNumber;
        }
        fargmentLessonBinding.textViewResult.setText(number);
        buttonEqualsControl = false;
    }

    /**
     * basic reset function for character gathering
     */
    public void onButtonACClicked(){

        number = null;
        fargmentLessonBinding.textViewResult.setText("0");
        buttonEqualsControl = false;
        result = "";

    }
}