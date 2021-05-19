package com.penikmatdesignproject.mdla.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.penikmatdesignproject.mdla.AddNewTaskWed;
import com.penikmatdesignproject.mdla.MainActivity4;
import com.penikmatdesignproject.mdla.Model.ToDoModelw;
import com.penikmatdesignproject.mdla.R;
import com.penikmatdesignproject.mdla.Utils.DataBaseHelperWed;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ToDoAdapterw extends RecyclerView.Adapter<ToDoAdapterw.MyViewHolder> {

    private List<ToDoModelw> mList;
    private MainActivity4 activity4;
    private DataBaseHelperWed myDB;

    public ToDoAdapterw(DataBaseHelperWed myDB , MainActivity4 activity4){
        this.activity4 = activity4;
        this.myDB = myDB;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_layoutwednesday , parent , false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final ToDoModelw item = mList.get(position);
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
        return activity4;
    }

    public void setTasks(List<ToDoModelw> mList){
        this.mList = mList;
        notifyDataSetChanged();
    }

    public void deletTask(int position){
        ToDoModelw item = mList.get(position);
        myDB.deleteTask(item.getId());
        mList.remove(position);
        notifyItemRemoved(position);
    }

    public void editItem(int position){
        ToDoModelw item = mList.get(position);

        Bundle bundle = new Bundle();
        bundle.putInt("id" , item.getId());
        bundle.putString("task" , item.getTask());

        AddNewTaskWed task = new AddNewTaskWed();
        task.setArguments(bundle);
        task.show(activity4.getSupportFragmentManager() , task.getTag());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        CheckBox mCheckBox;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mCheckBox = itemView.findViewById(R.id.mcheckboxwednesday);
        }
    }
}
