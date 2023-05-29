package vdm.p1.nonograms;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.SurfaceView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import vdm.p1.androidengine.AndroidEngine;
import vdm.p1.logic.Logic;

public class MainActivity extends AppCompatActivity {
	SharedPreferences mPreferences;
	private AndroidEngine engine;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Creamos el SurfaceView que "contendrá" nuestra escena
		SurfaceView renderView = new SurfaceView(this);
		setContentView(renderView);

		engine = new AndroidEngine(renderView, this);
		engine.getGraphics().setResolution(400, 600);

		Logic logic = new Logic(engine);
		engine.setLogic(logic);

		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) actionBar.hide();
	}

	@Override
	protected void onResume() {
		super.onResume();
		engine.resume();
	}

	@Override
	protected void onPause() {
		super.onPause();
		engine.pause();
	}
}
