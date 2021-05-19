package com.penikmatdesignproject.mdla;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.penikmatdesignproject.mdla.Adapter.ToDoAdapterThursday;
import com.penikmatdesignproject.mdla.Model.ToDoModelThursday;
import com.penikmatdesignproject.mdla.Utils.DataBaseHelperThursday;
import com.penikmatdesignproject.mdla.Utils.OnDialogCloseListnerth;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity5 extends AppCompatActivity implements OnDialogCloseListnerth {

    private RecyclerView mRecyclerview;
    private FloatingActionButton fab;
    private DataBaseHelperThursday myDB;
    private List<ToDoModelThursday> mList;
    private ToDoAdapterThursday adapterThursday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        mRecyclerview = findViewById(R.id.recylcerviewthursday);
        fab = findViewById(R.id.fabthursday);
        myDB = new DataBaseHelperThursday(MainActivity5.this);
        mList = new ArrayList<>();
        adapterThursday = new ToDoAdapterThursday(myDB , MainActivity5.this);

        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerview.setAdapter(adapterThursday);

        mList = myDB.getAllTasks();
        Collections.reverse(mList);
        adapterThursday.setTasks(mList);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewTaskThursday.newInstance().show(getSupportFragmentManager() , AddNewTaskThursday.TAG);
            }
        });
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new RecyclerViewTouchHelperth(adapterThursday));
        itemTouchHelper.attachToRecyclerView(mRecyclerview);

    }

    @Override
    public void onDialogClose(DialogInterface dialogInterface) {
        mList = myDB.getAllTasks();
        Collections.reverse(mList);
        adapterThursday.setTasks(mList);
        adapterThursday.notifyDataSetChanged();
    }
}