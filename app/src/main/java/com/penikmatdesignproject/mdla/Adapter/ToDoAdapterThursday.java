package com.penikmatdesignproject.mdla.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.penikmatdesignproject.mdla.AddNewTaskThursday;
import com.penikmatdesignproject.mdla.MainActivity5;
import com.penikmatdesignproject.mdla.Model.ToDoModelThursday;
import com.penikmatdesignproject.mdla.R;
import com.penikmatdesignproject.mdla.Utils.DataBaseHelperThursday;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ToDoAdapterThursday extends RecyclerView.Adapter<ToDoAdapterThursday.MyViewHolder> {

    private List<ToDoModelThursday> mList;
    private MainActivity5 activity5;
    private DataBaseHelperThursday myDB;

    public ToDoAdapterThursday(DataBaseHelperThursday myDB , MainActivity5 activity5){
        this.activity5 = activity5;
        this.myDB = myDB;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_layoutthursday , parent , false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final ToDoModelThursday item = mList.get(position);
        holder.mCheckBox.setText(item.getTask());
        holder.mCheckBox.setChecked(toBoolean(item.getStatus()));
        holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    myDB.updateStatus(item.getId(), 1);
                }else
                    myDB.updateStatus(item.getId(), 0);
            }
        });
    }

    public boolean toBoolean(int num){
        return num!=0;
    }

    public Context getContext(){
        return activity5;
    }

    public void setTasks(List<ToDoModelThursday> mList){
        this.mList = mList;
        notifyDataSetChanged();
    }

    public void deletTask(int position){
        ToDoModelThursday item = mList.get(position);
        myDB.deleteTask(item.getId());
        mList.remove(position);
        notifyItemRemoved(position);
    }

    public void editItem(int position){
        ToDoModelThursday item = mList.get(position);

        Bundle bundle = new Bundle();
        bundle.putInt("id" , item.getId());
        bundle.putString("task" , item.getTask());

        AddNewTaskThursday task = new AddNewTaskThursday();
        task.setArguments(bundle);
        task.show(activity5.getSupportFragmentManager() , task.getTag());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        CheckBox mCheckBox;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mCheckBox = itemView.findViewById(R.id.mcheckboxthursday);
        }
    }
}
