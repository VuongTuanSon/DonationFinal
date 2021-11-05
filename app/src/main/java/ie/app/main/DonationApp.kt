package ie.app.main

import android.app.Application
import android.util.Log
import android.widget.Toast
import ie.app.database.DBManager
import ie.app.models.Donation

class DonationApp : Application() {
    val target = 10000
    @JvmField
    var totalDonated = 0

    //public List <Donation> donations    = new ArrayList<Donation>();
    var dbManager: DBManager? = null
    fun newDonation(donation: Donation): Boolean {
        val targetAchieved = totalDonated > target
        if (!targetAchieved) {
            dbManager!!.add(donation)
            totalDonated += donation.amount
        } else {
            Toast.makeText(this, "Target Exceeded!", Toast.LENGTH_SHORT).show()
        }
        return targetAchieved
    }

    override fun onCreate() {
        super.onCreate()
        Log.v("Donate", "Donation App Started")
        dbManager = DBManager(this)
        Log.v("Donate", "Database Created")
    }
}