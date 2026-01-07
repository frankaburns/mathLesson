/*
 * FragmentResult.java     1.82 01/03/2026
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

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fabo.mathlesson.R;
import com.fabo.mathlesson.databinding.FragmentResultBinding;

import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class FragmentResult extends Fragment {

    FragmentResultBinding fragmentResultBinding;
    float correctNumber = 0F;
    float totalNumber = 0F;
    float wrongNumber = 0F;
    float avgTime = 0F;
    private BarChartCustomView barChartCustomView;

    /**
     *
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,<br>
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.<br>
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     *
     * @return - the newly created View
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentResultBinding = FragmentResultBinding.inflate(inflater,container,false);

        //int correct = FragmentResultArgs.fromBundle(getArguments()).getCorrect();
        if (getArguments() != null){
            avgTime = (float) getArguments().getInt("averageTime");
            wrongNumber = (float) getArguments().getInt("wrong");
            totalNumber = (float) getArguments().getInt("total");
            correctNumber = (float) getArguments().getInt("correct");
        }

        List<Float> data = Arrays.asList(correctNumber, wrongNumber, totalNumber, avgTime);
        barChartCustomView = (BarChartCustomView) fragmentResultBinding.resultChart;
        barChartCustomView.setData(data);

        fragmentResultBinding.buttonNewLesson.setOnClickListener(v -> {

            Navigation.findNavController(v).popBackStack(R.id.fragmentConfigure,false);

        });
        fragmentResultBinding.buttonExit.setOnClickListener(v -> {

            requireActivity().finish();

        });

        return fragmentResultBinding.getRoot();
    }
}