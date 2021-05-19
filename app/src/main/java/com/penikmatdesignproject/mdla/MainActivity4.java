package com.penikmatdesignproject.mdla;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.penikmatdesignproject.mdla.Adapter.ToDoAdapterw;
import com.penikmatdesignproject.mdla.Model.ToDoModelw;
import com.penikmatdesignproject.mdla.Utils.DataBaseHelperWed;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity4 extends AppCompatActivity implements OnDialogCloseListnerw {

    private RecyclerView mRecyclerview;
    private FloatingActionButton fab;
    private DataBaseHelperWed myDB;
    private List<ToDoModelw> mList;
    private ToDoAdapterw adapterw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        mRecyclerview = findViewById(R.id.recylcerviewwednesday);
        fab = findViewById(R.id.fabwednesday);
        myDB = new DataBaseHelperWed(MainActivity4.this);
        mList = new ArrayList<>();
        adapterw = new ToDoAdapterw(myDB , MainActivity4.this);

        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerview.setAdapter(adapterw);

        mList = myDB.getAllTasks();
        Collections.reverse(mList);
        adapterw.setTasks(mList);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewTaskWed.newInstance().show(getSupportFragmentManager() , AddNewTaskWed.TAG);
            }
        });
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new RecyclerViewTouchHelperw(adapterw));
        itemTouchHelper.attachToRecyclerView(mRecyclerview);

    }

    @Override
    public void onDialogClose(DialogInterface dialogInterface) {
        mList = myDB.getAllTasks();
        Collections.reverse(mList);
        adapterw.setTasks(mList);
        adapterw.notifyDataSetChanged();
    }
}