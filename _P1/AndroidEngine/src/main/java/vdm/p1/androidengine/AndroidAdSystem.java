package vdm.p1.androidengine;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.rewarded.RewardedAd;

import vdm.p1.engine.IAdSystem;

public class AndroidAdSystem implements IAdSystem {
	private final String TOKEN="ca-app-pub-3940256099942544/5224354917";

	private Context context;
	private AppCompatActivity activity;

	private AdRequest request;
	private AdView view;		// banner
	private RewardedAd reward;

	private boolean rewardGranted;
	public AndroidAdSystem(Context context, AppCompatActivity activity){
		this.context=context;
		this.activity=activity;

		this.view=null;
		this.reward=null;
		this.rewardGranted=false;

		MobileAds.initialize(context, new OnInitializationCompleteListener() {
			@Override
			public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {
				System.out.println("initialization correct");
			}
		});

		//ad request
		this.request= new AdRequest.Builder().build();

	}
	public void loadBanner(AdView view){
		this.view=view;
		this.view.loadAd(this.request);
	}
	@Override
	public void showBannerAd(boolean show) {
		if(this.view==null){
			return;
		}
		this.view.setVisibility(show? View.VISIBLE : View.GONE);
	}

	@Override
	public void showRewardAd() {
		//this.activity.runOnUiThread();
	}

	@Override
	public boolean isRewardGranted() {
		return false;
	}
}
