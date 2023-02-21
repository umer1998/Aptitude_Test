package com.example.aptitudetest.UI.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.aptitudetest.UI.activities.MainActivity;
import com.example.aptitudetest.UI.fragments.QuestionDetailFragment;
import com.example.aptitudetest.UI.fragments.SummaryScreenFrag;
import com.example.aptitudetest.info.Questions;

import java.util.ArrayList;
import java.util.HashMap;

public class MyPagerAdapter extends FragmentPagerAdapter {

    private MainActivity context;
    private ArrayList<Questions> questionsList;
    HashMap<Integer, String> map;


    public MyPagerAdapter(MainActivity context, ArrayList<Questions> questionsList, HashMap<Integer, String> map) {
        super(context.getSupportFragmentManager());
        this.map = map;
        this.context = context;
        this.questionsList = questionsList;

    }

    // Returns total number of pages
    @Override
    public int getCount() {
        return questionsList.size();
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {


            return new QuestionDetailFragment(context , position , questionsList.get(position), questionsList.size() , map);

    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        return "Page " + position;
    }

}
