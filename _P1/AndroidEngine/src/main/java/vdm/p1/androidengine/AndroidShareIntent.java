package vdm.p1.androidengine;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import vdm.p1.engine.IShareIntent;

public class AndroidShareIntent implements IShareIntent {
	Context context;

	public AndroidShareIntent(Context context) {
		this.context = context;
	}

	@Override
	public void share(String imagePath) {
		Bitmap bitmap;
		try {
			InputStream input = context.getAssets().open(imagePath);
			bitmap = BitmapFactory.decodeStream(input);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

		ContentValues values = new ContentValues();
		values.put(MediaStore.Images.Media.TITLE, "Nonograms");
		values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
		values.put(MediaStore.Images.Media.DESCRIPTION, "level");
		Uri uri = context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

		OutputStream outputStream;
		try {
			outputStream = context.getContentResolver().openOutputStream(uri);
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		Intent share = new Intent(Intent.ACTION_SEND);
		share.setType("image/jpeg");
		share.putExtra(Intent.EXTRA_STREAM, uri);

		context.startActivity(Intent.createChooser(share, "Share Image"));
	}
}
