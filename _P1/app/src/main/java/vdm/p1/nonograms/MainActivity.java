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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mEngine_= new AndroidEngine();
        mLogic_= new Logic();

        mEngine_.setLogic(mLogic_);
        mLogic_.initLogic();




        /**
         * //Creamos el SurfaceView que "contendrá" nuestra escena
         *         this.renderView = new SurfaceView(this);
         *         setContentView(this.renderView);
         *         Logic scene = new MyScene();
         *
         *         this.render = new MyRenderClass(this.renderView);
         *         scene.init(render);
         *         render.setScene(scene);
         */

    }

    @Override
    protected void onResume() {
        super.onResume();
        //this.render.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //this.render.pause();
    }
}
