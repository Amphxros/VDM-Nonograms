package vdm.p1.androidengine;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

import vdm.p1.engine.IAdSystem;

public class AndroidAdSystem implements IAdSystem {
	private final String TOKEN = "ca-app-pub-3940256099942544/5224354917";

	private Context context;
	private AppCompatActivity activity;

	private AdRequest request;
	private AdView view;        // banner
	private RewardedAd reward;

	private boolean rewardGranted;

	public AndroidAdSystem(Context context, AppCompatActivity activity) {
		this.context = context;
		this.activity = activity;



		this.view = new AdView(this.context);
		view.setAdSize(AdSize.BANNER);
		view.setAdUnitId(TOKEN);


		this.reward = null;
		this.rewardGranted = false;

		MobileAds.initialize(context, new OnInitializationCompleteListener() {
			@Override
			public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {
				System.out.println("initialization correct");
			}
		});

		//ad request
		this.request = new AdRequest.Builder().build();
loadBanner(view);


	}
	public AdView getView(){
		return this.view;
	}
	public void loadBanner(AdView v) {
		this.view = v;
		v.loadAd(this.request);
	}

	public void loadReward() {
		this.reward.load(this.activity, TOKEN, new AdRequest.Builder().build(), new RewardLoadCallback());
	}

	@Override
	public void showBannerAd(boolean show) {
		if (this.view == null) {
			return;
		}
		this.view.setVisibility(show ? View.VISIBLE : View.GONE);
	}

	@Override
	public void showRewardAd() {
		this.activity.runOnUiThread(new ShowReward(activity));
	}

	@Override
	public boolean isRewardGranted() {
		return rewardGranted;
	}


	private class ShowReward implements Runnable {

		private AppCompatActivity activity;
		ShowReward(AppCompatActivity activity){
			this.activity=activity;
		}
		@Override
		public void run() {
			if(reward==null){
				return;
			}
			else {
				reward.setFullScreenContentCallback(new FullScreenRewardContentCallback());
				if(reward!=null) {
					reward.show(this.activity, new EarnedRewardListener());
				}
				else{
					System.out.println("The reward ad wasnt ready");
				}
				reward=null;
				loadReward();
			}
		}
	}

	private class EarnedRewardListener implements OnUserEarnedRewardListener {
		@Override
		public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
			// Handle the reward.

			//int amount
			rewardGranted= true;
		}
	}


	private class RewardLoadCallback extends RewardedAdLoadCallback{

		RewardLoadCallback(){

		}
		@Override
		public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
			reward=null;
		}

		@Override
		public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
			super.onAdLoaded(rewardedAd);
			reward=rewardedAd;
		}
	}

	private class FullScreenRewardContentCallback extends FullScreenContentCallback{
		@Override
		public void onAdClicked() {
			super.onAdClicked();
		}

		@Override
		public void onAdDismissedFullScreenContent() {
			super.onAdDismissedFullScreenContent();
			reward=null;
		}

		@Override
		public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
			reward=null;
		}

		@Override
		public void onAdImpression() {
			super.onAdImpression();
		}

		@Override
		public void onAdShowedFullScreenContent() {
			super.onAdShowedFullScreenContent();
		}
	}
}
