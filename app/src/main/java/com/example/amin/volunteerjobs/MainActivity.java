package com.example.amin.volunteerjobs;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.PixelFormat;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.j256.ormlite.dao.Dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import ORMLite.DataBaseHelper;
import ORMLite.Hospital;
import ORMLite.Patient;


public class MainActivity extends Activity  {
    private VideoView vv;
    private int position=0;
    private MediaController mediaControls;
    private ProgressDialog pd;
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_main);
         vv= (VideoView) findViewById(R.id.videoView1);
        if (mediaControls == null)
            mediaControls = new MediaController(this);




            pd=new ProgressDialog(this);
        pd.setMessage("Loading...");
        pd.setTitle("waite");
        pd.setCancelable(false);
        pd.show();

        vv.setMediaController(mediaControls);
        vv.setVideoURI(Uri.parse("http://aminmirzaei.xzn.ir/amin.3gp"));
        vv.requestFocus();
        vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                pd.dismiss();
                vv.seekTo(position);

                if (position == 0) {
                    vv.start();
                } else {
                    vv.pause();
                }

            }
        });

    }


    /*
        if(mediaControls==null)
        {
            mediaControls=new MediaController(this);
        }
        myVideoView= (VideoView) findViewById(R.id.videoView1);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
//progress Dialog.........................................
        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("video example");
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
//..........................................................
        myVideoView.setMediaController(mediaControls);
        myVideoView.setVideoURI(Uri.parse("http://aminmirzaei.xzn.ir/amin.3gp"));
        myVideoView.requestFocus();
        myVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                progressDialog.dismiss();
                if (position == 0) {
                    myVideoView.start();
                } else {
                    myVideoView.pause();
                }
            }
        });
        */
/*
        Button b = (Button) findViewById(R.id.button);
        EditText e1= (EditText) findViewById(R.id.editText);
        EditText e2= (EditText) findViewById(R.id.editText2);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str="";

                DataBaseHelper dataBaseHelper =new DataBaseHelper(getBaseContext());
                try {

                    Dao<Hospital,Integer> hospitalDao=dataBaseHelper.getHospitalDao();
                    Hospital h =new Hospital();
                    h.setName("amin");
                    hospitalDao.create(h);

                } catch (SQLException e) {
                    e.printStackTrace();
                }

                Toast.makeText(getBaseContext(),str,Toast.LENGTH_LONG).show();
            }
        });
*/


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("Position", vv.getCurrentPosition());
        vv.pause();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        position = savedInstanceState.getInt("Position");
        vv.seekTo(position);

    }

}
