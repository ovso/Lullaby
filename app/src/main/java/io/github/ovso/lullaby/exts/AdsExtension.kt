package io.github.ovso.lullaby.exts

import android.app.Activity
import com.google.android.gms.ads.*
import com.google.android.gms.ads.appopen.AppOpenAd
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import io.github.ovso.lullaby.R
import io.github.ovso.lullaby.utils.AppOpenManager

fun Activity.showOpeningAds(callback: FullScreenContentCallback? = null) {
  val manager = AppOpenManager(this.application)
  manager.loadCallback = object : AppOpenAd.AppOpenAdLoadCallback() {
    override fun onAdLoaded(appOpenAd: AppOpenAd) {
      super.onAdLoaded(appOpenAd)
      with(appOpenAd) {
        fullScreenContentCallback = callback
        show(this@showOpeningAds)
      }
    }

    override fun onAdFailedToLoad(p0: LoadAdError) {
      super.onAdFailedToLoad(p0)
      callback?.onAdFailedToShowFullScreenContent(p0)
    }
  }
  manager.fetchAd()
}

fun Activity.showInterstitialAd(
  onAdFailedToLoad: (LoadAdError) -> Unit,
  onAdLoaded: (InterstitialAd) -> Unit,
) {
  val adRequest = AdRequest.Builder().build()
  InterstitialAd.load(
    this,
    getString(R.string.ads_unit_id_interstitial),
    adRequest,
    object : InterstitialAdLoadCallback() {
      override fun onAdFailedToLoad(adError: LoadAdError) {
        //Log.d(TAG, adError?.message)
        onAdFailedToLoad.invoke(adError)
      }

      override fun onAdLoaded(interstitialAd: InterstitialAd) {
        //Log.d(TAG, 'Ad was loaded.')
        onAdLoaded.invoke(interstitialAd)
      }
    })
}
