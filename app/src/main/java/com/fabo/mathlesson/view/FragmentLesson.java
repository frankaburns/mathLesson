package com.fabo.mathlesson.view;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import com.fabo.mathlesson.R;
import com.fabo.mathlesson.databinding.FragmentLessonBinding;
import com.fabo.mathlesson.domain.BasicMath;
import com.fabo.mathlesson.domain.ProblemGenerator;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class FragmentLesson extends Fragment {

    // Problem set input variables sent from the configuration screen
    int function = 0;
    int level = 0;
    int random = 0;
    int range = 0;
    int numProblems = 0;

    // user tracking variables
    int wrong = 0;
    int numWrong = 0;
    int correct = 0;
    String functionStr;
    String number = null;
    String result = "";
    boolean buttonEqualsControl = false;
    long end;
    long start;
    long averageTime;

    // Working variables
    BasicMath problem = null;
    private ProblemGenerator pg = null;

    private FragmentLessonBinding fragmentLessonBinding;

    /**
     *
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,<br>
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.<br>
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.<br>
     *
     * @return - the newly created View
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentLessonBinding = FragmentLessonBinding.inflate(inflater,container,false);

        if (pg == null) {
            pg = new ProblemGenerator();
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
        fragmentLessonBinding.textViewNumerator.setText(numerator);
        String denominator = functionStr + problem.getDenominator();
        SpannableString spannableString = new SpannableString(denominator);

        // Underline the entire string
        spannableString.setSpan(new UnderlineSpan(), 0, spannableString.length(), 0);

        // Or underline a specific portion (e.g., "underlined")
        // spannableString.setSpan(new UnderlineSpan(), 12, 22, 0);
        fragmentLessonBinding.textViewDenominator.setText(spannableString);

        fragmentLessonBinding.btn0.setOnClickListener(v -> onNumberClicked("0"));
        fragmentLessonBinding.btn1.setOnClickListener(v -> onNumberClicked("1"));
        fragmentLessonBinding.btn2.setOnClickListener(v -> onNumberClicked("2"));
        fragmentLessonBinding.btn3.setOnClickListener(v -> onNumberClicked("3"));
        fragmentLessonBinding.btn4.setOnClickListener(v -> onNumberClicked("4"));
        fragmentLessonBinding.btn5.setOnClickListener(v -> onNumberClicked("5"));
        fragmentLessonBinding.btn6.setOnClickListener(v -> onNumberClicked("6"));
        fragmentLessonBinding.btn7.setOnClickListener(v -> onNumberClicked("7"));
        fragmentLessonBinding.btn8.setOnClickListener(v -> onNumberClicked("8"));
        fragmentLessonBinding.btn9.setOnClickListener(v -> onNumberClicked("9"));

        fragmentLessonBinding.btnDel.setOnClickListener(v -> {

            if (number == null || number.length() == 1) {
                onButtonACClicked();
            } else {
                number = number.substring(0, number.length() - 1);
                fragmentLessonBinding.textViewResult.setText(number);
            }

        });

        fragmentLessonBinding.btnEquals.setOnClickListener(v -> {

            // fragmentLessonBinding.textViewComment.setText(blank);
            fragmentLessonBinding.textViewResult.setText(result);
            problemSolution();
            number = null;
            buttonEqualsControl = true;
        });

        // Inflate the layout for this fragment
        return fragmentLessonBinding.getRoot();
    }

    /**
     * Verify the input answer from the student.<br>Update status variables.
     */
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
                    fragmentLessonBinding.textViewResult.setText(msg);
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

                fragmentLessonBinding.textViewCorrect.setText(String.valueOf(correct));
                fragmentLessonBinding.textViewWrong.setText(String.valueOf(numWrong));
                fragmentLessonBinding.textViewTotal.setText(String.valueOf(size));

                String numerator = "" + problem.getNumerator();
                fragmentLessonBinding.textViewNumerator.setText(numerator);
                String denominator = functionStr + problem.getDenominator();
                SpannableString spannableString = new SpannableString(denominator);

                // Underline the entire string
                spannableString.setSpan(new UnderlineSpan(), 0, spannableString.length(), 0);

                // Or underline a specific portion (e.g., "underlined")
                // spannableString.setSpan(new UnderlineSpan(), 12, 22, 0);
                fragmentLessonBinding.textViewDenominator.setText(spannableString);
            }
        } else {

            numWrong++;
            if (problem != null) {
                problem.setEnd();
                problem = pg.getProblem();
            }

            if (problem != null) {
                fragmentLessonBinding.textViewCorrect.setText(String.valueOf(correct));
                fragmentLessonBinding.textViewWrong.setText(String.valueOf(numWrong));
                fragmentLessonBinding.textViewTotal.setText(String.valueOf(size));

                String numerator = "" + problem.getNumerator();
                fragmentLessonBinding.textViewNumerator.setText(numerator);
                String denominator = functionStr + problem.getDenominator();
                SpannableString spannableString = new SpannableString(denominator);

                // Underline the entire string
                spannableString.setSpan(new UnderlineSpan(), 0, spannableString.length(), 0);

                // Or underline a specific portion (e.g., "underlined")
                // spannableString.setSpan(new UnderlineSpan(), 12, 22, 0);
                fragmentLessonBinding.textViewDenominator.setText(spannableString);
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

            Navigation.findNavController(this.fragmentLessonBinding.getRoot()).navigate(
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
        fragmentLessonBinding.textViewResult.setText(number);
        buttonEqualsControl = false;
    }

    /**
     * basic reset function for character gathering
     */
    public void onButtonACClicked(){

        number = null;
        fragmentLessonBinding.textViewResult.setText("0");
        buttonEqualsControl = false;
        result = "";

    }
}