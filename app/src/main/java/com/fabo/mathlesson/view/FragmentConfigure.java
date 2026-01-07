/*
 * FragmentConfigure.java     1.82 01/03/2026
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
*/
package com.fabo.mathlesson.view;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.fabo.mathlesson.R;
import com.fabo.mathlesson.databinding.FragmentConfigureBinding;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class FragmentConfigure extends Fragment {
    int function = 0;
    int level = 0;
    int random = 0;
    int range = 0;
    int start = 0;
    int end = 10;
    int numProblems = 10;
    boolean rangeClicked = false;
    boolean randomClicked = false;
    boolean forceRandom = false;

    Button range_button;
    Button random_button;
    Button[] function_buttons;
    Button[] level_buttons;
    String color_switch_off = "#B5AEAF";
    String color_switch_on  = "#B3FA97";

    FragmentConfigureBinding fragmentConfigureBinding;


    /**
     * This fragment view is defining the Configuration View for the Math Lesson app.
     * The onCreateView method defines the actions for each button or textedit on the
     * configuration view.  This basically keeping track of settings and/or adjusting
     * color/text based upon the last click.<br>
     * The Start button invokes the lesson fragment with the either default or modified
     * problem set settings.<br>
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,<br>
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.<br>
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.<br>
     * @return - the newly created View
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentConfigureBinding = FragmentConfigureBinding.inflate(inflater,container,false);

        function_buttons = new Button[] {fragmentConfigureBinding.functionButton0,fragmentConfigureBinding.functionButton1,fragmentConfigureBinding.functionButton2,fragmentConfigureBinding.functionButton3};
        level_buttons = new Button[] {fragmentConfigureBinding.digitButton0,fragmentConfigureBinding.digitButton1,fragmentConfigureBinding.digitButton2};

        random_button = fragmentConfigureBinding.randomButton;
        range_button = fragmentConfigureBinding.rangeButton;

        if (savedInstanceState != null) {
            if (getArguments() != null){
                function = 0;
                level = 0;
                random = 0;
                range = 0;
                start = 0;
                end = (int) Math.pow(10, (level+1));
                numProblems = getArguments().getInt("num");
            }
        }
        fragmentConfigureBinding.endEditTextNumberDecimal.setText(String.valueOf(end));
        fragmentConfigureBinding.startEditTextNumberDecimal.setText(String.valueOf(start));
        fragmentConfigureBinding.numEditTextNumberDecimal.setText(String.valueOf(numProblems));

        fragmentConfigureBinding.functionButton0.setOnClickListener(v -> {

            function_buttons[function].setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(color_switch_off)));
            function = 0;
            function_buttons[function].setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(color_switch_on)));
        });

        fragmentConfigureBinding.functionButton1.setOnClickListener(v -> {
            function_buttons[function].setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(color_switch_off)));
            function = 1;
            function_buttons[function].setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(color_switch_on)));
        });

        fragmentConfigureBinding.functionButton2.setOnClickListener(v -> {
            function_buttons[function].setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(color_switch_off)));
            function = 2;
            function_buttons[function].setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(color_switch_on)));
        });

        fragmentConfigureBinding.functionButton3.setOnClickListener(v -> {
            function_buttons[function].setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(color_switch_off)));
            function = 3;
            function_buttons[function].setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(color_switch_on)));
        });

        fragmentConfigureBinding.digitButton0.setOnClickListener(v -> {
            level_buttons[level].setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(color_switch_off)));
            level = 0;
            level_buttons[level].setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(color_switch_on)));
            forceRandom = false;
        });

        fragmentConfigureBinding.digitButton1.setOnClickListener(v -> {
            level_buttons[level].setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(color_switch_off)));
            level = 1;
            level_buttons[level].setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(color_switch_on)));
            forceRandom = false;
        });

        fragmentConfigureBinding.digitButton2.setOnClickListener(v -> {
            level_buttons[level].setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(color_switch_off)));
            level = 2;
            level_buttons[level].setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(color_switch_on)));
            forceRandom = true;
            random_button.setText(R.string.Random);
            random_button.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(color_switch_on)));
            randomClicked = true;
            random = 1;
        });

        fragmentConfigureBinding.randomButton.setOnClickListener(v -> {
            if (!forceRandom) {
                if (randomClicked) {
                    random = 0;
                    random_button.setText(R.string.Ordered);
                    random_button.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(color_switch_on)));
                    randomClicked = false;
                } else {
                    random = 1;
                    random_button.setText(R.string.Random);
                    random_button.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(color_switch_on)));
                    randomClicked = true;
                }
            }
        });

        fragmentConfigureBinding.rangeButton.setOnClickListener(v -> {
            if (rangeClicked) {
                range = 0;
                range_button.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(color_switch_off)));

                fragmentConfigureBinding.startEditTextNumberDecimal.setVisibility(INVISIBLE);
                fragmentConfigureBinding.startTextView.setVisibility(INVISIBLE);

                fragmentConfigureBinding.endEditTextNumberDecimal.setVisibility(INVISIBLE);
                fragmentConfigureBinding.endTextView.setVisibility(INVISIBLE);
                rangeClicked = false;
            } else {
                range = 1;
                range_button.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(color_switch_on)));

                fragmentConfigureBinding.startEditTextNumberDecimal.setVisibility(VISIBLE);
                fragmentConfigureBinding.startTextView.setVisibility(VISIBLE);

                fragmentConfigureBinding.endEditTextNumberDecimal.setVisibility(VISIBLE);
                fragmentConfigureBinding.endTextView.setVisibility(VISIBLE);
                rangeClicked = true;
            }
        });

        fragmentConfigureBinding.doneButton.setOnClickListener(v -> {

            String input = fragmentConfigureBinding.startEditTextNumberDecimal.getText().toString();
            start = (input != null && !input.isEmpty()) ? Integer.parseInt(input) : 0;

            double base = 10;
            double exponent = level+1;

            int result = (int)Math.pow(base, exponent);

            input = fragmentConfigureBinding.endEditTextNumberDecimal.getText().toString();
            end = (input != null && !input.isEmpty()) ? Integer.parseInt(input) : result;

            input = fragmentConfigureBinding.numEditTextNumberDecimal.getText().toString();
            numProblems = (input != null && !input.isEmpty()) ? Integer.parseInt(input) : 10;

            Bundle bundle = new Bundle();
            bundle.putInt("function",function);
            bundle.putInt("level",level);
            bundle.putInt("random",random);
            bundle.putInt("range",range);
            bundle.putInt("start",start);
            bundle.putInt("end",end);
            bundle.putInt("num",numProblems);

            onSaveInstanceState(bundle);

            Navigation.findNavController(v).navigate(
                    R.id.action_fragmentConfigure_to_fragmentLesson,
                    bundle,
                    new NavOptions.Builder().setPopUpTo(R.id.fragmentConfigure,false).build()
            );
        });

        return fragmentConfigureBinding.getRoot();

    }
    // Inside your Fragment
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("key_data", "some data");
    }
}