package com.penikmatdesignproject.mdla.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.penikmatdesignproject.mdla.AddNewTaskFriday;
import com.penikmatdesignproject.mdla.MainActivity6;
import com.penikmatdesignproject.mdla.Model.ToDoModelFriday;
import com.penikmatdesignproject.mdla.R;
import com.penikmatdesignproject.mdla.Utils.DataBaseHelperFriday;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ToDoAdapterFriday extends RecyclerView.Adapter<ToDoAdapterFriday.MyViewHolder> {

    private List<ToDoModelFriday> mList;
    private MainActivity6 activity6;
    private DataBaseHelperFriday myDB;

    public ToDoAdapterFriday(DataBaseHelperFriday myDB , MainActivity6 activity6){
        this.activity6 = activity6;
        this.myDB = myDB;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_layoutfriday , parent , false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final ToDoModelFriday item = mList.get(position);
        holder.mCheckBox.setText(item.getTask());
        holder.mCheckBox.setChecked(toBoolean(item.getStatus()));
        holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    myDB.updateStatus(item.getId() , 1);
                }else
                    myDB.updateStatus(item.getId(), 0);
            }
        });
    }

    public boolean toBoolean(int num){
        return num!=0;
    }

    public Context getContext(){
        return activity6;
    }

    public void setTasks(List<ToDoModelFriday> mList){
        this.mList = mList;
        notifyDataSetChanged();
    }

    public void deletTask(int position){
        ToDoModelFriday item = mList.get(position);
        myDB.deleteTask(item.getId());
        mList.remove(position);
        notifyItemRemoved(position);
    }

    public void editItem(int position){
        ToDoModelFriday item = mList.get(position);

        Bundle bundle = new Bundle();
        bundle.putInt("id" , item.getId());
        bundle.putString("task" , item.getTask());

        AddNewTaskFriday task = new AddNewTaskFriday();
        task.setArguments(bundle);
        task.show(activity6.getSupportFragmentManager() , task.getTag());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        CheckBox mCheckBox;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mCheckBox = itemView.findViewById(R.id.mcheckboxfriday);
        }
    }
}
