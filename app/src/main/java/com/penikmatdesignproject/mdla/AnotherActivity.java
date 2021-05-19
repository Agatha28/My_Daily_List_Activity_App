package com.penikmatdesignproject.mdla;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.penikmatdesignproject.mdla.Adapter.ToDoAdapter;
import com.penikmatdesignproject.mdla.Model.ToDoModelSunday;
import com.penikmatdesignproject.mdla.Utils.DataBaseHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AnotherActivity extends AppCompatActivity implements OnDialogCloseListner {

    private RecyclerView mRecyclerview;
    private FloatingActionButton fab;
    private DataBaseHelper myDB;
    private List<ToDoModelSunday> mListSunday;
    private ToDoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);

        mRecyclerview = findViewById(R.id.recylcerviewsunday);
        fab = findViewById(R.id.fabsunday);
        myDB = new DataBaseHelper(AnotherActivity.this);
        mListSunday = new ArrayList<>();
        adapter = new ToDoAdapter(myDB , AnotherActivity.this);

        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerview.setAdapter(adapter);

        mListSunday = myDB.getAllTasks();
        Collections.reverse(mListSunday);
        adapter.setTasks(mListSunday);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewTask.newInstance().show(getSupportFragmentManager() , AddNewTask.TAG);
            }
        });
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new RecyclerViewTouchHelper(adapter));
        itemTouchHelper.attachToRecyclerView(mRecyclerview);
    }

    @Override
    public void onDialogClose(DialogInterface dialogInterface) {
        mListSunday = myDB.getAllTasks();
        Collections.reverse(mListSunday);
        adapter.setTasks(mListSunday);
        adapter.notifyDataSetChanged();
    }
}