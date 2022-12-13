package vdm.p1.nonograms;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;


public class NonogramsMessagingService extends FirebaseMessagingService{
	@Override
	public void onMessageReceived(@NonNull RemoteMessage message) {
		super.onMessageReceived(message);
		System.out.println("message");
	}
}
