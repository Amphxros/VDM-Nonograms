package vdm.p1.nonograms;

import android.content.Intent;
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
	private String sharedPrefFile = "MySharedPreferences";    // TEMPORAL

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

		//if the user enters by a notification
		Intent intent = getIntent();
		if (intent.getExtras() != null && intent.getExtras().containsKey("notification")) {
			logic.handleOpeningNotifications();
		}

		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) actionBar.hide();
	}

	@Override
	protected void onResume() {
		super.onResume();

		mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
		//if (savedInstanceState != null) {
		//mColor = mPreferences.getInt("lastColor", 0);
		//soundFX = mPreferences.getBoolean("lastColor", True);
		//soundMUSIC = mPreferences.getBoolean("lastColor", True);

		// METHODS TO SET THE PREFERENCES ON THE LOGIC SHOULD BE HERE

		//} else {}

		engine.resume();
	}

	@Override
	protected void onPause() {
		super.onPause();

		// Editor object is mandatory for the changes on the SharedPreferences object
		SharedPreferences mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
		SharedPreferences.Editor preferencesEditor = mPreferences.edit();

		// PREFERENCES SAVING
		//preferencesEditor.putInt("lastColor", mColor);
		//preferencesEditor.putBoolean("soundFX", logic.getSoundFX());
		//preferencesEditor.putBoolean("soundMusic", logic.getSoundMusic());

		preferencesEditor.apply(); // APPLIES ALL CHANGED PREFERENCES

		engine.pause();
	}

}
