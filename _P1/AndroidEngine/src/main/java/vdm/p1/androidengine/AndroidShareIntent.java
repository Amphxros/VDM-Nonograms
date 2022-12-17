package vdm.p1.androidengine;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.IOException;
import java.io.InputStream;

import vdm.p1.engine.IShareIntent;

public class AndroidShareIntent implements IShareIntent {
	Context context;

	public AndroidShareIntent(Context context) {
		this.context = context;
	}

	@Override
	public void share(String imagePath) {
		Bitmap bitmap = null;
		try {
			InputStream input = context.getAssets().open(imagePath);
			bitmap = BitmapFactory.decodeStream(input);
		} catch (IOException e) {
			e.printStackTrace();
		}

		String imagepath = MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, "Nonograms", "level");
		Uri uri = Uri.parse(imagepath);

		Intent share = new Intent(Intent.ACTION_SEND);
		share.setAction(Intent.ACTION_SEND);
		share.setType("image/jpeg");
		share.putExtra(Intent.EXTRA_STREAM, uri);
		Intent chooser = Intent.createChooser(share, "share Image");
		context.startActivity(chooser);
	}
}
