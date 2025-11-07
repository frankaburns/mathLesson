package com.fabo.mathlesson.view;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.fabo.mathlesson.R;
import com.fabo.mathlesson.databinding.FragmentResultBinding;
import com.fabo.mathlesson.domain.*;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */

        // MyGLFragment.java
public class FragmentResult extends Fragment {
    private GLSurfaceView mGLView;
    FragmentResultBinding fragmentResultBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentResultBinding = FragmentResultBinding.inflate(inflater, container, false);

        mGLView = new MyGLSurfaceView(fragmentResultBinding.resultChart.findViewById(R.id.resultChart).getContext());

        fragmentResultBinding.buttonNewLesson.setOnClickListener(v -> {
            Navigation.findNavController(v).popBackStack(R.id.fragmentConfigure,false);
        });
        fragmentResultBinding.buttonExit.setOnClickListener(v -> {
            requireActivity().finish();
        });
        fragmentResultBinding.getRoot().addView(mGLView);
        return fragmentResultBinding.getRoot();
    }

    @Override
    public void onPause() {
        super.onPause();
        mGLView.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mGLView.onResume();
    }
}