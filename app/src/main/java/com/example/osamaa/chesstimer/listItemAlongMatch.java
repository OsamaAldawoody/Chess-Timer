package com.example.osamaa.chesstimer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class listItemAlongMatch extends AppCompatActivity {
        static int myTime;
        public  listItemAlongMatch(){

        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_along_match);
        TextView textView_10 =(TextView)findViewById(R.id.txtView_10);
        TextView textView_15 =(TextView)findViewById(R.id.txtView_15);
        TextView textView_20 =(TextView)findViewById(R.id.txtView_20);
        TextView textView_30 =(TextView)findViewById(R.id.txtView_30);
        TextView textView_60 =(TextView)findViewById(R.id.txtView_60);
        TextView textView_90 =(TextView)findViewById(R.id.txtView_90);
        TextView textView_120 =(TextView)findViewById(R.id.txtView_120);


        textView_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myTime=600;
                Intent i =new Intent(listItemAlongMatch.this,MainActivity.class);
                startActivity(i);
            }
        });

        textView_15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myTime=900;
                Intent i =new Intent(listItemAlongMatch.this,MainActivity.class);
                startActivity(i);
            }
        });
        textView_20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myTime=1200;
                Intent i =new Intent(listItemAlongMatch.this,MainActivity.class);
                startActivity(i);
            }
        });
        textView_30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myTime=1800;
                Intent i =new Intent(listItemAlongMatch.this,MainActivity.class);
                startActivity(i);
            }
        });
        textView_60.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myTime=3600;
                Intent i =new Intent(listItemAlongMatch.this,MainActivity.class);
                startActivity(i);
            }
        });
        textView_90.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myTime=5400;
                Intent i =new Intent(listItemAlongMatch.this,MainActivity.class);
                startActivity(i);
            }
        });
        textView_120.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myTime=7200;
                Intent i =new Intent(listItemAlongMatch.this,MainActivity.class);
                startActivity(i);
            }
        });


    }
}
