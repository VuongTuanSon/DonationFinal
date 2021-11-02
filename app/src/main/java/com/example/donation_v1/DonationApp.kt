package com.example.donation_v1

import android.app.Application
import android.util.Log


class DonationApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Log.v("Donate", "Donation App Started")
    }
}
