package com.penikmatdesignproject.mdla;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    ViewFlipper v_flipper;

    ListView listView;
    String mTitle[] = {"List Activity on Sunday","List Activity on Monday", "List Activity on Tuesday", "List Activity on Wednesday","List Activity on Thursday", "List Activity on Friday", "List Activity on Saturday"};
    String mDescription[] = {"Happy Sunday","Happy Monday","Happy Tuesday", "Happy Wednesday", "Happy Thursday", "Happy Friday", "Happy Saturday"};
    int images[] = {R.drawable.sunday, R.drawable.mon, R.drawable.tues, R.drawable.wed, R.drawable.thurs, R.drawable.friday, R.drawable.satur};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        listView = findViewById(R.id.listView);

        MyAdapter adapter = new MyAdapter(this, mTitle, mDescription, images);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    Intent intent = new Intent(view.getContext(), AnotherActivity.class);
                    startActivity(intent);
                }

                if(position == 1){
                    Intent intent = new Intent(view.getContext(), MainActivity2.class);
                    startActivity(intent);
                }

                if(position == 2){
                    Intent intent = new Intent(view.getContext(), MainActivity3.class);
                    startActivity(intent);
                }

                if(position == 3){
                    Intent intent = new Intent(view.getContext(), MainActivity4.class);
                    startActivity(intent);
                }

                if(position == 4){
                    Intent intent = new Intent(view.getContext(), MainActivity5.class);
                    startActivity(intent);
                }

                if(position == 5){
                    Intent intent = new Intent(view.getContext(), MainActivity6.class);
                    startActivity(intent);
                }

                if(position == 6){
                    Intent intent = new Intent(view.getContext(), MainActivity7.class);
                    startActivity(intent);
                }

            }
        });


        int images[] = {R.drawable.w1, R.drawable.w3, R.drawable.w2, R.drawable.w4};


        v_flipper = findViewById(R.id.v_flipper);

        for (int image: images){
            flipperImages(image);
        }
    }

    public void flipperImages(int image){
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);

        v_flipper.addView(imageView);
        v_flipper.setFlipInterval(4000);
        v_flipper.setAutoStart(true);

        v_flipper.setInAnimation(this, android.R.anim.slide_in_left);
        v_flipper.setOutAnimation(this, android.R.anim.slide_out_right);

    }

    class MyAdapter extends ArrayAdapter<String> {
        Context context;
        String rTitle[];
        String rDescription[];
        int rImage[];

        MyAdapter(Context c, String title[], String description[], int imgs[]) {
            super(c, R.layout.rox, R.id.textView1, title);
            this.context = c;
            this.rTitle = title;
            this.rDescription = description;
            this.rImage = imgs;
        }
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
                LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View row = layoutInflater.inflate(R.layout.rox, parent, false);
                ImageView images = row.findViewById(R.id.image);
                TextView myTitle = row.findViewById(R.id.textView1);
                TextView myDescription = row.findViewById(R.id.textView2);

                images.setImageResource(rImage[position]);
                myTitle.setText(rTitle[position]);
                myDescription.setText(rDescription[position]);

                return row;
        }
    }
}