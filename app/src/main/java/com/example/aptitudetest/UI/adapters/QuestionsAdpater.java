package com.example.aptitudetest.UI.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aptitudetest.R;
import com.example.aptitudetest.UI.activities.MainActivity;
import com.example.aptitudetest.utils.SurvayAdapterListener;


public class QuestionsAdpater
        extends RecyclerView.Adapter<QuestionsAdpater.ViewHolder> {

    String answer = "strongly disagree";
    private SurvayAdapterListener mItemClickListener;

    private Context context;
    private int optionn = 0;


    public QuestionsAdpater( MainActivity context) {
        this.context = context;


    }

    class ViewHolder extends RecyclerView.ViewHolder implements SurvayAdapterListener {

        RadioButton r1, r2, r3, r4;
        RelativeLayout rl1, rl2, rl3, rl4, rlnextQuestion;
        TextView questionno , nextQuestion, totalQuestion;
        int count;


        public ViewHolder(View itemView) {
            super(itemView);


            count = 0;

            r1 = itemView.findViewById(R.id.r1);
            r2 = itemView.findViewById(R.id.r2);
            r3 = itemView.findViewById(R.id.r3);
            r4 = itemView.findViewById(R.id.r4);

            rl1 = itemView.findViewById(R.id.q1);
            rl2 = itemView.findViewById(R.id.q2);
            rl3 = itemView.findViewById(R.id.q3);
            rl4 = itemView.findViewById(R.id.q4);

            questionno = itemView.findViewById(R.id.questionno);
            nextQuestion = itemView.findViewById(R.id.nextquestionno);

            rlnextQuestion = itemView.findViewById(R.id.next);
            totalQuestion = itemView.findViewById(R.id.totalquestion);


        }

        @Override
        public void onItemClick(int position, String option) {
            mItemClickListener.onItemClick(getPosition(), option);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.question_adapter, parent, false);

        ViewHolder vh = new ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {



        holder.r1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {

                holder.r1.setChecked(true);
                holder.rl1.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.question_background));
                holder.rl1.setElevation(10f);

                holder.r2.setChecked(false);
                holder.rl2.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.circular_round_cornor));
                holder.rl2.setElevation(0f);

                holder.r3.setChecked(false);
                holder.rl3.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.circular_round_cornor));
                holder.rl3.setElevation(0f);

                holder.r4.setChecked(false);
                holder.rl4.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.circular_round_cornor));
                holder.rl4.setElevation(0f);
            }
        });

        holder.r2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.r2.setChecked(true);
                holder.rl2.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.question_background));
                holder.rl2.setElevation(10f);

                holder.r1.setChecked(false);
                holder.rl1.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.circular_round_cornor));
                holder.rl1.setElevation(0f);

                holder.r3.setChecked(false);
                holder.rl3.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.circular_round_cornor));
                holder.rl3.setElevation(0f);


                holder.r4.setChecked(false);
                holder.rl4.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.circular_round_cornor));
                holder.rl4.setElevation(0f);
            }
        });

        holder.r3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.r3.setChecked(true);
                holder.rl3.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.question_background));
                holder.rl3.setElevation(10f);

                holder.r2.setChecked(false);
                holder.rl2.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.circular_round_cornor));
                holder.rl2.setElevation(0f);

                holder.r1.setChecked(false);
                holder.rl1.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.circular_round_cornor));
                holder.rl1.setElevation(0f);

                holder.r4.setChecked(false);
                holder.rl4.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.circular_round_cornor));
                holder.rl4.setElevation(0f);
            }
        });

        holder.r4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.r4.setChecked(true);
                holder.rl4.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.question_background));
                holder.rl4.setElevation(10f);

                holder.r2.setChecked(false);
                holder.rl2.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.circular_round_cornor));
                holder.rl2.setElevation(0f);

                holder.r3.setChecked(false);
                holder.rl3.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.circular_round_cornor));
                holder.rl3.setElevation(0f);

                holder.r1.setChecked(false);
                holder.rl1.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.circular_round_cornor));
                holder.rl1.setElevation(0f);
            }
        });

        holder.nextQuestion.setText(holder.count+1 +"");
        holder.questionno.setText(position+1+"");

        holder.totalQuestion.setText(6 +"");

        holder.rlnextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                notifyDataSetChanged();
            }
        });



    }


    public void setOnItemClickListener(final SurvayAdapterListener mItemClickListener) {

        this.mItemClickListener = mItemClickListener;

    }


    @Override
    public int getItemCount() {
        return 6;
    }


}

