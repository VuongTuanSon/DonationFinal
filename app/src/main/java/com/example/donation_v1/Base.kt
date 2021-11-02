package com.example.donation_v1

import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


open class Base : AppCompatActivity() {
    val target = 10000
    var totalDonated = 0
    fun newDonation(donation: Donation): Boolean {
        val targetAchieved = totalDonated > target
        if (!targetAchieved) {
            donations.add(donation)
            totalDonated += donation.amount
        } else {
            val toast = Toast.makeText(this, "Target Exceeded!", Toast.LENGTH_SHORT)
            toast.show()
        }
        return targetAchieved
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.settings_report, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        super.onPrepareOptionsMenu(menu)
        val report: MenuItem = menu.findItem(R.id.reportFragment)
        val donate: MenuItem = menu.findItem(R.id.donateMenu)
        if (donations.isEmpty()) report.setEnabled(false) else report.setEnabled(true)
            report.setVisible(false)
            donate.setVisible(true)
        return true
    }

    fun settings(item: MenuItem?) {
        Toast.makeText(this, "Settings Selected", Toast.LENGTH_SHORT).show()
    }

    fun report(item: MenuItem?) {
        startActivity(Intent(this, ReportActivity::class.java))
    }

    fun donate(item: MenuItem?) {
        startActivity(Intent(this, MainActivity::class.java))
    }

    companion object {
        var donations: MutableList<Donation> = ArrayList()
    }
}