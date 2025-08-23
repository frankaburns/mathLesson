package com.fabo.mathlesson.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    FragmentConfigureBinding fragmentConfigureBinding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentConfigureBinding = FragmentConfigureBinding.inflate(inflater,container,false);

        fragmentConfigureBinding.endEditTextNumberDecimal.setText(String.valueOf(end));
        fragmentConfigureBinding.startEditTextNumberDecimal.setText(String.valueOf(start));
        fragmentConfigureBinding.numEditTextNumberDecimal.setText(String.valueOf(numProblems));

        fragmentConfigureBinding.functionButton0.setOnClickListener(v -> {
            function = 0;
        });

        fragmentConfigureBinding.functionButton1.setOnClickListener(v -> {
            function = 1;
        });

        fragmentConfigureBinding.functionButton2.setOnClickListener(v -> {
            function = 2;
        });

        fragmentConfigureBinding.functionButton3.setOnClickListener(v -> {
            function = 3;
        });

        fragmentConfigureBinding.digitButton0.setOnClickListener(v -> {
            level = 0;
        });

        fragmentConfigureBinding.digitButton1.setOnClickListener(v -> {
            level = 1;
        });

        fragmentConfigureBinding.digitButton2.setOnClickListener(v -> {
            level =23;
        });

        fragmentConfigureBinding.randomButton.setOnClickListener(v -> {
            random = 1;
        });

        fragmentConfigureBinding.randomButton.setOnClickListener(v -> {
            random = 1;
        });

        fragmentConfigureBinding.rangeButton.setOnClickListener(v -> {
            range = 1;
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

            Navigation.findNavController(v).navigate(
                    R.id.action_fragmentConfigure_to_fragmentLesson,
                    bundle,
                    new NavOptions.Builder().setPopUpTo(R.id.fragmentConfigure,false).build()
            );
        });

        return fragmentConfigureBinding.getRoot();

    }
}