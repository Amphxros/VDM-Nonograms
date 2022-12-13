package vdm.p1.androidengine;

import android.app.Activity;
import android.content.Intent;

import vdm.p1.engine.IShareIntent;

// TODO: Finish this
public class AndroidShareIntent implements IShareIntent {
	private final Activity activity;

	public AndroidShareIntent(Activity activity) {
		this.activity = activity;
	}

	@Override
	public void onShareIntent(String tittle, String msg) {
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain");

		intent.putExtra(Intent.EXTRA_SUBJECT, tittle);
		intent.putExtra(Intent.EXTRA_TEXT, msg);

		Intent shareIntent = Intent.createChooser(intent, null);
	}
}
