package com.fabo.mathlesson.view;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fabo.mathlesson.R;
import com.fabo.mathlesson.databinding.FragmentResultBinding;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

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
            correctNumber = (float) getArguments().getInt("correct");
            wrongNumber = (float) getArguments().getInt("wrong");
            totalNumber = (float) getArguments().getInt("total");
            avgTime = (float) getArguments().getInt("averageTime");
        }

        ArrayList<BarEntry> barEntriesArrayListCorrect = new ArrayList<>();
        ArrayList<BarEntry> barEntriesArrayListTotal = new ArrayList<>();
        ArrayList<BarEntry> barEntriesArrayListWrong = new ArrayList<>();
        ArrayList<BarEntry> barEntriesArrayListTime = new ArrayList<>();

        barEntriesArrayListCorrect.add(new BarEntry(0F,correctNumber));
        barEntriesArrayListTotal.add(new BarEntry(1F,totalNumber));
        barEntriesArrayListWrong.add(new BarEntry(2F,wrongNumber));
        barEntriesArrayListTime.add(new BarEntry(3F,avgTime));

        BarDataSet barDataSetCorrect = createBarDataSet(barEntriesArrayListCorrect,"Correct Number", Color.GREEN,24F,Color.BLACK);
        BarDataSet barDataSetTotal = createBarDataSet(barEntriesArrayListTotal,"Total Number   ",Color.BLUE,24F,Color.BLACK);
        BarDataSet barDataSetWrong = createBarDataSet(barEntriesArrayListWrong,"Wrong Number  ",Color.RED,24F,Color.BLACK);
        BarDataSet barDataSetTime = createBarDataSet(barEntriesArrayListTime,"Average Time",Color.CYAN,24F,Color.BLACK);

        BarData barData = new BarData(barDataSetCorrect,barDataSetTotal,barDataSetWrong,barDataSetTime);
        fragmentResultBinding.resultChart.setData(barData);

        fragmentResultBinding.buttonNewLesson.setOnClickListener(v -> {

            Navigation.findNavController(v).popBackStack(R.id.fragmentConfigure,false);

        });
        fragmentResultBinding.buttonExit.setOnClickListener(v -> {

            requireActivity().finish();

        });


        return fragmentResultBinding.getRoot();
    }

    public BarDataSet createBarDataSet(ArrayList<BarEntry> entries, String label, int barColor, float valueTextSize, int valueTextColor){

        BarDataSet dataSet = new BarDataSet(entries,label);
        dataSet.setColor(barColor);
        dataSet.setValueTextSize(valueTextSize);
        dataSet.setValueTextColor(valueTextColor);
        return dataSet;

    }

}