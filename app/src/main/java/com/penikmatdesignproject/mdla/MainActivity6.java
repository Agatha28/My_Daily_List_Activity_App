package com.penikmatdesignproject.mdla;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.penikmatdesignproject.mdla.Adapter.ToDoAdapterFriday;
import com.penikmatdesignproject.mdla.Model.ToDoModelFriday;
import com.penikmatdesignproject.mdla.Utils.DataBaseHelperFriday;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity6 extends AppCompatActivity implements OnDialogCloseListnerfri {

    private RecyclerView mRecyclerview;
    private FloatingActionButton fab;
    private DataBaseHelperFriday myDB;
    private List<ToDoModelFriday> mList;
    private ToDoAdapterFriday adapterFriday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        mRecyclerview = findViewById(R.id.recylcerviewfriday);
        fab = findViewById(R.id.fabfriday);
        myDB = new DataBaseHelperFriday(MainActivity6.this);
        mList = new ArrayList<>();
        adapterFriday = new ToDoAdapterFriday(myDB , MainActivity6.this);

        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerview.setAdapter(adapterFriday);

        mList = myDB.getAllTasks();
        Collections.reverse(mList);
        adapterFriday.setTasks(mList);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewTaskFriday.newInstance().show(getSupportFragmentManager() , AddNewTaskFriday.TAG);
            }
        });
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new RecyclerViewTouchHelperfri(adapterFriday));
        itemTouchHelper.attachToRecyclerView(mRecyclerview);


    }

    @Override
    public void onDialogClose(DialogInterface dialogInterface) {
        mList = myDB.getAllTasks();
        Collections.reverse(mList);
        adapterFriday.setTasks(mList);
        adapterFriday.notifyDataSetChanged();
    }
}