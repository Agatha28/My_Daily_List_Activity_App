package com.penikmatdesignproject.mdla;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.penikmatdesignproject.mdla.Adapter.TodoAdapterr;
import com.penikmatdesignproject.mdla.Model.ToDoModeL;
import com.penikmatdesignproject.mdla.Utils.DataBaseHelperMonday;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity2 extends AppCompatActivity implements OnDialogCloseListnerr {

    private RecyclerView mRecyclerview;
    private FloatingActionButton fab;
    private DataBaseHelperMonday myDB;
    private List<ToDoModeL> mList;
    private TodoAdapterr adapterr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mRecyclerview = findViewById(R.id.recylcerviewmonday);
        fab = findViewById(R.id.fabmonday);
        myDB = new DataBaseHelperMonday(MainActivity2.this);
        mList = new ArrayList<>();
        adapterr = new TodoAdapterr(myDB , MainActivity2.this);

        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerview.setAdapter(adapterr);


        mList = myDB.getAllTasks();
        Collections.reverse(mList);
        adapterr.setTasks(mList);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewTaskMonday.newInstance().show(getSupportFragmentManager(), AddNewTaskMonday.TAG);
            }
        });
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new RecyclerViewTouchHelperr(adapterr));
        itemTouchHelper.attachToRecyclerView(mRecyclerview);
    }

    @Override
    public void onDialogClose(DialogInterface dialogInterface) {
        mList = myDB.getAllTasks();
        Collections.reverse(mList);
        adapterr.setTasks(mList);
        adapterr.notifyDataSetChanged();
    }
}