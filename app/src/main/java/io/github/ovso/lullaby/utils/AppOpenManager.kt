package io.github.ovso.lullaby.utils

import android.app.Application
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.appopen.AppOpenAd
import io.github.ovso.lullaby.R

class AppOpenManager(app: Application) {
    private var appOpenAd: AppOpenAd? = null
    lateinit var loadCallback: AppOpenAd.AppOpenAdLoadCallback

    private val application: Application = app

    /** Request an ad  */
    fun fetchAd() {
        // Have unused ad, no need to fetch another.
        if (isAdAvailable) {
            return
        }
        val request: AdRequest = adRequest
        AppOpenAd.load(
            application,
            application.getString(R.string.ads_unit_id_open),
            request,
            AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT,
            loadCallback
        )

    }

    /** Creates and returns ad request.  */
    private val adRequest: AdRequest
        get() = AdRequest.Builder().build()

    /** Utility method that checks if ad exists and can be shown.  */
    private val isAdAvailable: Boolean
        get() = appOpenAd != null

    companion object {
        private const val LOG_TAG = "AppOpenManager"
    }

}
