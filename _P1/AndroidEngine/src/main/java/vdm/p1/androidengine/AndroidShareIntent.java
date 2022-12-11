package vdm.p1.androidengine;

import android.app.Activity;
import android.content.Intent;

import vdm.p1.engine.IShareContent;

public class AndroidShareIntent implements IShareContent {
	private Activity currActivity;
	public AndroidShareIntent(Activity activity){

		this.currActivity= activity;
	}
	@Override
	public void onShareIntent(String title, String msg){
		Intent share = new Intent(android.content.Intent.ACTION_SEND);
		share.setType("text/plain");

		share.putExtra(android.content.Intent.EXTRA_SUBJECT, title);
		share.putExtra(android.content.Intent.EXTRA_TEXT, msg);
		share.createChooser(share, "Compartir vía");
	}
}
