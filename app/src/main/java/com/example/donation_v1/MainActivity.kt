package com.example.donation_v1

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    var totalDonated = 0
    val target = 10000

    fun newDonation(donation: Donation): Boolean {
        val targetAchieved = totalDonated > target
        if (!targetAchieved) {
            Base.donations.add(donation)
            totalDonated += donation.amount
        } else {
            val toast = Toast.makeText(this, "Target Exceeded!", Toast.LENGTH_SHORT)
            toast.show()
        }
        return targetAchieved
    }

    fun reset(item: MenuItem?) {
        totalDonated = 0
        amountTotal.text = "$" + totalDonated
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
            amountPicker.setMinValue(0)
            amountPicker.setMaxValue(1000)
            progressBar.setMax(10000);
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.settings_report, menu)
        val item = menu?.findItem(R.id.donateMenu)
        if (item != null) item.isVisible = false
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.reportFragment -> {
                val intent = Intent(this, ReportActivity::class.java)
                startActivity(intent);
                return true;
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    fun donateButtonPressed(view: View) {
        val method = if (paymentMethod.checkedRadioButtonId === R.id.PayPal) "PayPal" else "Direct"
        var donatedAmount = amountPicker.value
        if (donatedAmount == 0) {
            val text = amountText.text.toString()
            if (text != "") donatedAmount = text.toInt()
        }
        if (donatedAmount > 0) {
            newDonation(Donation(donatedAmount, method))
            progressBar.progress = totalDonated
            val totalDonatedStr = "$$totalDonated"
            amountTotal.text = totalDonatedStr
        }
    }

}