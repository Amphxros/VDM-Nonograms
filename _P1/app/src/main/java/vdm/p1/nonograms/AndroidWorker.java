package vdm.p1.nonograms;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;


@SuppressLint("WorkerHasAPublicModifier")
class AndroidWorker extends Worker {

	public AndroidWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
		super(context, workerParams);
	}

	@NonNull
	@Override
	public Result doWork() {
		final String title = getInputData().getString("title");
		final String contentText = getInputData().getString("contentText");
		final String biggerText = getInputData().getString("biggerText");
		final String CHANNEL_ID = getInputData().getString("CHANNEL_ID");
		final boolean autoCancel  = getInputData().getBoolean("autocancel", true);
		createAndSendNotification(title, contentText, biggerText, CHANNEL_ID, autoCancel);
		return Result.success();
	}

	public void createAndSendNotification(String title, String content, String subtitle, String CHANNEL_ID, boolean autoCancel){

	}
}
