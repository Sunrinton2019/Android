package com.kmj.sunrinton19;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.SubjectHolder> {
    ArrayList<String> mList;
    MainActivity mainActivity;

    public SubjectAdapter(ArrayList<String> mList, MainActivity mainActivity) {
        this.mList = mList;
        this.mainActivity = mainActivity;
    }

    public class SubjectHolder extends RecyclerView.ViewHolder {
        private TextView subjectname;

        public SubjectHolder(@NonNull View itemView) {
            super(itemView);

                subjectname = itemView.findViewById(R.id.item_tv);


        }
    }

    @NonNull
    @Override
    public SubjectHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item, viewGroup, false);

        SubjectAdapter.SubjectHolder viewHolder = new SubjectAdapter.SubjectHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectHolder subjectHolder, int i) {

            subjectHolder.subjectname.setText(mList.get(i));


    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }


}
