package com.fabo.mathlesson.view;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

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
    int switch_on = 0;
    int switch_off = 0;
    int numProblems = 10;
    boolean rangeClicked = false;
    boolean randomClicked = false;
    Button range_button;
    Button random_button;
    Button[] function_buttons;
    Button[] level_buttons;

    FragmentConfigureBinding fragmentConfigureBinding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentConfigureBinding = FragmentConfigureBinding.inflate(inflater,container,false);

        function_buttons = new Button[] {fragmentConfigureBinding.functionButton0,fragmentConfigureBinding.functionButton1,fragmentConfigureBinding.functionButton2,fragmentConfigureBinding.functionButton3};
        level_buttons = new Button[] {fragmentConfigureBinding.digitButton0,fragmentConfigureBinding.digitButton1,fragmentConfigureBinding.digitButton2};

        random_button = fragmentConfigureBinding.randomButton;
        range_button = fragmentConfigureBinding.rangeButton;

        fragmentConfigureBinding.endEditTextNumberDecimal.setText(String.valueOf(end));
        fragmentConfigureBinding.startEditTextNumberDecimal.setText(String.valueOf(start));
        fragmentConfigureBinding.numEditTextNumberDecimal.setText(String.valueOf(numProblems));

        String color_switch_off = "#B5AEAF";
        String color_switch_on = "#B3FA97";

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
        });

        fragmentConfigureBinding.digitButton1.setOnClickListener(v -> {
            level_buttons[level].setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(color_switch_off)));
            level = 1;
            level_buttons[level].setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(color_switch_on)));
        });

        fragmentConfigureBinding.digitButton2.setOnClickListener(v -> {
            level_buttons[level].setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(color_switch_off)));
            level = 2;
            level_buttons[level].setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(color_switch_on)));
        });

        fragmentConfigureBinding.randomButton.setOnClickListener(v -> {
            if (randomClicked) {
                random = 0;
                random_button.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(color_switch_off)));
                randomClicked = false;
            } else {
                random = 1;
                random_button.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(color_switch_on)));
                randomClicked = true;


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

            start = Integer.parseInt(fragmentConfigureBinding.startEditTextNumberDecimal.getText().toString());
            end = Integer.parseInt(fragmentConfigureBinding.endEditTextNumberDecimal.getText().toString());
            numProblems = Integer.parseInt(fragmentConfigureBinding.numEditTextNumberDecimal.getText().toString());

            Bundle bundle = new Bundle();
            bundle.putInt("function",function);
            bundle.putInt("level",level);
            bundle.putInt("random",random);
            bundle.putInt("range",range);
            bundle.putInt("start",start);
            bundle.putInt("end",end);
            bundle.putInt("num",numProblems);


            Navigation.findNavController(v).navigate(
                    R.id.action_fragmentConfigure_to_fragmentLesson,
                    bundle,
                    new NavOptions.Builder().setPopUpTo(R.id.fragmentConfigure,false).build()
            );
        });

        // reset parameters.
        //
        function = 0;
        level = 0;
        random = 0;
        range = 0;
        start = 0;
        end = 10;
        numProblems = 10;

        fragmentConfigureBinding.endEditTextNumberDecimal.setText(String.valueOf(end));
        fragmentConfigureBinding.startEditTextNumberDecimal.setText(String.valueOf(start));
        fragmentConfigureBinding.numEditTextNumberDecimal.setText(String.valueOf(numProblems));

        return fragmentConfigureBinding.getRoot();

    }
}