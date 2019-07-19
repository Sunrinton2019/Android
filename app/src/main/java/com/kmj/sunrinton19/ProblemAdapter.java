package com.kmj.sunrinton19;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ProblemAdapter extends RecyclerView.Adapter<ProblemAdapter.ProblemHolder> {
    ArrayList<ProblemModel> mList;
    Context mainActivity;

    public ProblemAdapter(ArrayList<ProblemModel> mList, Context mainActivity) {
        this.mList = mList;
        this.mainActivity = mainActivity;
    }

    public class ProblemHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name;
        TextView star;
        ImageView key;
        public ProblemHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.problem_img);
            key=itemView.findViewById(R.id.problem_key);
            name=itemView.findViewById(R.id.problem_tv1);
            star=itemView.findViewById(R.id.problem_tv2);


        }
    }

    @NonNull
    @Override
    public ProblemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.problem_item, viewGroup, false);

        ProblemAdapter.ProblemHolder viewHolder = new ProblemAdapter.ProblemHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProblemHolder problemHolder, int i) {
        problemHolder.name.setText("문제 : "+mList.get(i).getName());
        problemHolder.star.setText("난이도 : "+mList.get(i).getStar()+"");
        if(mList.get(i).getSolveUrl().equals("")){
            problemHolder.key.setImageResource(R.drawable.problem_nokey);

        }
        else{
            problemHolder.key.setImageResource(R.drawable.problem_key);
        }
        Glide.with(mainActivity)
                .load(mList.get(i).getImageUrl())
                .into(problemHolder.img);

    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }


}
