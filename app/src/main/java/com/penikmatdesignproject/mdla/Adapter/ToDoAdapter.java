package com.penikmatdesignproject.mdla.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.penikmatdesignproject.mdla.AddNewTask;
import com.penikmatdesignproject.mdla.AnotherActivity;
import com.penikmatdesignproject.mdla.Model.ToDoModelSunday;
import com.penikmatdesignproject.mdla.R;
import com.penikmatdesignproject.mdla.Utils.DataBaseHelper;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.MyViewHolder> {

    private List<ToDoModelSunday> mListSunday;
    private AnotherActivity activity;
    private DataBaseHelper myDB;

    public ToDoAdapter(DataBaseHelper myDB , AnotherActivity activity){
        this.activity = activity;
        this.myDB = myDB;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_layoutsunday , parent , false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final ToDoModelSunday item = mListSunday.get(position);
        holder.mCheckBox.setText(item.getTask());
        holder.mCheckBox.setChecked(toBoolean(item.getStatus()));
        holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    myDB.updateStatus(item.getId(), 1);
                } else
                    myDB.updateStatus(item.getId(), 0);

            }
        });
    }

    public boolean toBoolean(int num){
        return num!=0;
    }

    public Context getContext(){
        return activity;
    }

    public void setTasks(List<ToDoModelSunday> mListSunday){
        this.mListSunday = mListSunday;
        notifyDataSetChanged();
    }

    public void deletTask(int position){
        ToDoModelSunday item = mListSunday.get(position);
        myDB.deleteTask(item.getId());
        mListSunday.remove(position);
        notifyItemRemoved(position);
    }

    public void editItem(int position){
        ToDoModelSunday item = mListSunday.get(position);

        Bundle bundle = new Bundle();
        bundle.putInt("id" , item.getId());
        bundle.putString("task" , item.getTask());

        AddNewTask task = new AddNewTask();
        task.setArguments(bundle);
        task.show(activity.getSupportFragmentManager() , task.getTag());
    }

    @Override
    public int getItemCount() {
        return mListSunday.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        CheckBox mCheckBox;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mCheckBox = itemView.findViewById(R.id.mcheckboxsunday);
        }
    }
}
