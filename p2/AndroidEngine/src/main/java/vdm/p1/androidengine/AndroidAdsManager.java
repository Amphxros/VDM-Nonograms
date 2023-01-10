package vdm.p1.androidengine;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
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

import vdm.p1.engine.IAdsManager;

public class AndroidAdsManager implements IAdsManager {

	private final String AD_TOKEN = "ca-app-pub-3940256099942544/5224354917";
	private AdView adView;
	private AdRequest adRequest;
	private RewardedAd rewardAd;
	private boolean hasRewardAdShown;

	private AppCompatActivity activity;
	private Context context;

	public AndroidAdsManager(AppCompatActivity activity, Context context, AdView adView){
		this.activity=activity;
		this.context=context;

		MobileAds.initialize(this.context, new OnInitializationCompleteListener() {
			@Override
			public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {
				System.out.println("initialization" + initializationStatus.toString());
			}
		});
		this.adRequest = new AdRequest.Builder().build();
		this.adView=adView;
		adView.loadAd(this.adRequest);

	}
	@Override
	public boolean onAdRewardShown() {
		if(hasRewardAdShown){
			hasRewardAdShown=false;
			return true;
		}
		return false;
	}
	public void loadRewardAd(){
		RewardedAd.load(this.activity, AD_TOKEN, adRequest,
				new UserRewardedAdLoadCallback());
	}
	@Override
	public void showRewardAd() {
		this.activity.runOnUiThread(new ShowRewardAd(this.activity));
	}

	@Override
	public void showBanner(boolean visible) {
		if(this.adView==null){
			return;
		}
		this.adView.setVisibility(visible? View.VISIBLE : View.GONE);
	}

	private class ShowRewardAd implements Runnable{

		private AppCompatActivity activity;
		ShowRewardAd(AppCompatActivity activity){
			this.activity=activity;
		}
		@Override
		public void run() {
			if (rewardAd == null)
				System.out.println("Reward ad not set up yet.");
			else {
				// show ad (we assume one has been preloaded before)
				rewardAd.setFullScreenContentCallback(new UserFullScreenContentCallback());
				rewardAd.show(this.activity, new UserEarnedRewardListener());
				rewardAd = null;
			}

			// load ad for next showing
			loadRewardAd();
		}
	}

	private class UserEarnedRewardListener implements OnUserEarnedRewardListener {
		/**
		 *	// Handle the reward.
		 * @param rewardItem
		 */
		@Override
		public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
			System.out.println("Rewarded granted.");
			hasRewardAdShown=true;

		}
	}


	/**
	 *
	 */
	private class UserRewardedAdLoadCallback extends RewardedAdLoadCallback {
		/**
		 * Handle the error.
		 * @param loadAdError
		 */
		@Override
		public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {

			System.err.println("Rewarded ad not loaded correctly");
			rewardAd = null;
		}

		@Override
		public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
			// obtain data
			System.out.println("Rewarded ad properly loaded");
			rewardAd = rewardedAd;
		}
	}


	// full screen callback
	private class UserFullScreenContentCallback extends FullScreenContentCallback {
		@Override
		/**
		 *  Called when a click is recorded for an ad.
		 */
		public void onAdClicked() {
			System.out.println("Ad was clicked.");
		}

		/**
		 * Called when ad is dismissed.
		 */
		@Override
		public void onAdDismissedFullScreenContent() {

			// Set the ad reference to null so you don't show the ad a second time.
			rewardAd = null;
		}

		/**
		 * Called when ad fails to show.
		 * @param adError
		 */
		@Override
		public void onAdFailedToShowFullScreenContent(AdError adError) {

			rewardAd = null;
		}

		/**
		 * Called when an impression is recorded for an ad.
		 */
		 @Override
		public void onAdImpression() {
			System.out.println("Ad recorded an impression.");
		}

		/**
		 * Called when ad is shown
		 */
		@Override
		public void onAdShowedFullScreenContent() {
			System.out.println("Ad showed fullscreen content.");
		}
	}
}
