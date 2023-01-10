package vdm.p1.nonograms;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.SurfaceView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import vdm.p1.androidengine.AndroidEngine;
import vdm.p1.engine.INotificationHandler;
import vdm.p1.engine.Notification;
import vdm.p1.logic.Logic;

public class MainActivity extends AppCompatActivity {
	private SharedPreferences mPreferences;
	private AndroidEngine engine;
	private String sharedPrefFile = "MySharedPreferences";    // TEMPORAL

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Creamos el SurfaceView que "contendrá" nuestra escena
		SurfaceView renderView = findViewById(R.id.surfaceView);
		AdView adView = findViewById(R.id.adView);
		setContentView(R.layout.layout);

		engine = new AndroidEngine(this, renderView, adView, this);
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
		handleNotifications();
	}

	private void handleNotifications() {
		INotificationHandler notificationHandler = engine.getNotificationHandler();
		ArrayList<Notification> notifications = notificationHandler.getPendingEntries();
		for (Notification notification : notifications) {
			Data input = new Data.Builder()
					.putString(NotificationWorker.INPUT_CHANNEL_ID, notificationHandler.getChannelID())
					.putString(NotificationWorker.INPUT_TITLE, notification.getTitle())
					.putString(NotificationWorker.INPUT_CONTENT, notification.getContent())
					.putString(NotificationWorker.INPUT_BIGGER_TEXT, notification.getSubtitle())
					.putBoolean(NotificationWorker.INPUT_AUTO_CANCEL, true)
					.build();

			OneTimeWorkRequest notificationWork = new OneTimeWorkRequest.Builder(NotificationWorker.class)
					.setInitialDelay(notification.getDelay(), TimeUnit.SECONDS)
					.setInputData(input)
					.build();

			WorkManager.getInstance(this).enqueue(notificationWork);
		}

		notifications.clear();
	}
}
