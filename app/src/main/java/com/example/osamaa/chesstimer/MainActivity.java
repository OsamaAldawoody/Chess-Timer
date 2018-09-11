package com.example.osamaa.chesstimer;


import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  {
    int Sec;
    int Sec2;
    TextView textView;
    TextView textView2;
    ImageView start;
    ImageView pause;
    Handler handler;
    static int turn = 0;
    int id;
    int resID;
    int resID2;
    MediaPlayer witch;
    MediaPlayer startStopWatch;
    MediaPlayer endMatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        Sec = listItemAlongMatch.myTime;
        Sec2 = listItemAlongMatch.myTime;
        textView = findViewById(R.id.textView);
        textView.setText(String.format("%d:%02d:%02d", Sec / 3600, (Sec % 3600) / 60, Sec % 60));
        textView2 = findViewById(R.id.textView2);
        textView2.setText(String.format("%d:%02d:%02d", Sec2 / 3600, (Sec2 % 3600) / 60, Sec2 % 60));

        start = (ImageButton) findViewById(R.id.button);
        pause = (ImageButton) findViewById(R.id.button2);


        start.setSoundEffectsEnabled(false);
        pause.setSoundEffectsEnabled(false);
        String mDrawableName = "stop";
        resID = getResources().getIdentifier(mDrawableName, "drawable", getPackageName());

        String mDrawableName2 = "resume";
        resID2 = getResources().getIdentifier(mDrawableName2, "drawable", getPackageName());

        id = resID;
        handler = new Handler();


        reset();



        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                releaseWatch();
                witch = MediaPlayer.create(MainActivity.this, R.raw.witch);
                witch.start();
                witch.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        releaseWatch();
                    }
                });
                turn++;
                pause.setImageResource(R.drawable.resume);
                id = resID;
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable, 0);

            }
        });

        pause.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                releaseStartStopWatch();
                witch = MediaPlayer.create(MainActivity.this, R.raw.witch);
                startStopWatch = MediaPlayer.create(MainActivity.this, R.raw.stopwatch_start_click);
                startStopWatch.start();
                startStopWatch.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        releaseStartStopWatch();
                    }
                });
                if (turn > 0) {
                    if (id == resID) {

                        handler.removeCallbacks(runnable);
                        id = resID2;
                        pause.setImageResource(R.drawable.stop);

                    } else {
                        handler.removeCallbacks(runnable);
                        handler.post(runnable);
                        id = resID;
                        pause.setImageResource(R.drawable.resume);
                    }
                }

            }
        });



    }

    void reset() {
        turn = 0;
    }


    public Runnable runnable = new Runnable() {

        public void run() {

            if (turn % 2 == 1) {
                int hours = Sec / 3600;
                int minutes = (Sec % 3600) / 60;
                int sec = Sec % 60;
                String time = String.format("%d:%02d:%02d", hours, minutes, sec);
                textView.setText(time);
                Sec--;
                if(Sec ==0 ){
                    endMatch = MediaPlayer.create(MainActivity.this, R.raw.whistle);
                    endMatch.start();
                    endMatch.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            releaseEndMatch();
                        }
                    });
                }
                handler.postDelayed(this, 1000);
            } else {
                int hours2 = Sec2 / 3600;
                int minutes2 = (Sec2 % 3600) / 60;
                int sec2 = Sec2 % 60;
                String time = String.format("%d:%02d:%02d", hours2, minutes2, sec2);
                textView2.setText(time);
                Sec2--;
                if(Sec2 ==0 ){
                    endMatch = MediaPlayer.create(MainActivity.this, R.raw.whistle);
                    endMatch.start();
                    endMatch.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            releaseEndMatch();
                        }
                    });
                }
                handler.postDelayed(this, 1000);
            }

        }
    };

    @Override
    protected void onStop() {
        super.onStop();
        releaseStartStopWatch();
        releaseWatch();

    }
    private void releaseStartStopWatch() {
        // If the media player is not null, then it may be currently playing a sound.
        if (startStopWatch != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            startStopWatch.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            startStopWatch = null;
        }
    }

    private void releaseWatch() {
        // If the media player is not null, then it may be currently playing a sound.
        if (witch != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            witch.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            witch = null;
        }
    }

    private void releaseEndMatch() {
        // If the media player is not null, then it may be currently playing a sound.
        if (witch != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            endMatch.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            endMatch = null;
        }
    }

}