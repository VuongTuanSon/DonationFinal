package com.example.donation_v1

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ListView


class ReportActivity : Base() {
    var listView: ListView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)

        listView = findViewById<View>(R.id.reportList) as ListView
        val adapter = DonationAdapter(this, donations)
        listView!!.adapter = adapter

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.settings_report, menu)
        val item = menu?.findItem(R.id.reportFragment)
        if (item != null) item.isVisible = false
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.donateMenu -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent);
                return true;
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}