package com.example.aptitudetest.UI.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aptitudetest.R;
import com.example.aptitudetest.UI.activities.MainActivity;


public class EndTestFragment extends Fragment {

    private MainActivity context;
    private int total,  size;
    private TextView tvTotal, tvAttempted;

    public EndTestFragment(MainActivity context, int size, int total) {
        this.context = context;
        this.size = size;
        this.total = total;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_end_test2, container, false);

        tvAttempted = view.findViewById(R.id.attempted);
        tvAttempted.setText(size+"");

        tvTotal = view.findViewById(R.id.total);
        tvTotal.setText(total+"");

        return view;
    }
}