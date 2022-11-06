package vdm.p1.nonograms;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import vdm.p1.androidengine.AndroidEngine;
import vdm.p1.logic.Logic;

public class MainActivity extends AppCompatActivity {

    private Logic mLogic_;
    private AndroidEngine mEngine_;


    private SurfaceView renderView;

    private MyRenderClass render;

    private boolean run_example=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Creamos el SurfaceView que "contendrá" nuestra escena
        this.renderView = new SurfaceView(this);
        setContentView(this.renderView);

        if(run_example){
        MyScene scene = new MyScene();

        this.render = new MyRenderClass(this.renderView);
        scene.init(render);
        render.setScene(scene);
        }
        else{
            mEngine_= new AndroidEngine(this.renderView,this);
            mLogic_= new Logic(mEngine_);
            mEngine_.setLogic(mLogic_);
            mLogic_.initLogic();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(run_example)
        this.render.resume();
        else
            mEngine_.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(run_example)
        this.render.pause();
        else
            mEngine_.pause();
    }

  }