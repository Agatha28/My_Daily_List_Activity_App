package com.penikmatdesignproject.mdla;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.penikmatdesignproject.mdla.Adapter.ToDoAdapterSaturday;
import com.penikmatdesignproject.mdla.Model.ToDoModelSaturday;
import com.penikmatdesignproject.mdla.Utils.DataBaseHelperSaturday;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity7 extends AppCompatActivity implements OnDialogCloseListnersat{


    private RecyclerView mRecyclerview;
    private FloatingActionButton fab;
    private DataBaseHelperSaturday myDB;
    private List<ToDoModelSaturday> mList;
    private ToDoAdapterSaturday adapterSaturday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        mRecyclerview = findViewById(R.id.recylcerviewsaturday);
        fab = findViewById(R.id.fabsaturday);
        myDB = new DataBaseHelperSaturday(MainActivity7.this);
        mList = new ArrayList<>();
        adapterSaturday = new ToDoAdapterSaturday(myDB , MainActivity7.this);

        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerview.setAdapter(adapterSaturday);


        mList = myDB.getAllTasks();
        Collections.reverse(mList);
        adapterSaturday.setTasks(mList);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewTaskSaturday.newInstance().show(getSupportFragmentManager() , AddNewTaskSaturday.TAG);
            }
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new RecyclerViewTouchHelpersat(adapterSaturday));
        itemTouchHelper.attachToRecyclerView(mRecyclerview);
    }

    @Override
    public void onDialogClose(DialogInterface dialogInterface) {
        mList = myDB.getAllTasks();
        Collections.reverse(mList);
        adapterSaturday.setTasks(mList);
        adapterSaturday.notifyDataSetChanged();
    }
}