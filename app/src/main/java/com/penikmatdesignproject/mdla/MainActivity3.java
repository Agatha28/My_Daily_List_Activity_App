package com.penikmatdesignproject.mdla;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.penikmatdesignproject.mdla.Adapter.TodOAdapterrr;
import com.penikmatdesignproject.mdla.Model.ToDoModeLL;
import com.penikmatdesignproject.mdla.Utils.DataBaseHelperTuesday;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity3 extends AppCompatActivity implements OnDialogCloseListnerrr {

    private RecyclerView mRecyclerview;
    private FloatingActionButton fab;
    private DataBaseHelperTuesday myDB;
    private List<ToDoModeLL> mList;
    private TodOAdapterrr adapterrr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        mRecyclerview = findViewById(R.id.recylcerviewtuesday);
        fab = findViewById(R.id.fabtuesday);
        myDB = new DataBaseHelperTuesday(MainActivity3.this);
        mList = new ArrayList<>();
        adapterrr = new TodOAdapterrr(myDB , MainActivity3.this);

        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerview.setAdapter(adapterrr);

        mList = myDB.getAllTasks();
        Collections.reverse(mList);
        adapterrr.setTasks(mList);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewTaskTuesday.newInstance().show(getSupportFragmentManager() , AddNewTaskTuesday.TAG);
            }
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new RecyclerViewTouchHelperrr(adapterrr));
        itemTouchHelper.attachToRecyclerView(mRecyclerview);
    }

    @Override
    public void onDialogClose(DialogInterface dialogInterface) {
        mList = myDB.getAllTasks();
        Collections.reverse(mList);
        adapterrr.setTasks(mList);
        adapterrr.notifyDataSetChanged();
    }
}