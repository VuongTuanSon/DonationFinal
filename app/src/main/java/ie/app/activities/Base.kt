package ie.app.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import ie.app.R
import ie.app.main.DonationApp

open class Base : AppCompatActivity() {
    @JvmField
    var app: DonationApp? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = application as DonationApp
        app!!.dbManager?.open()
        app!!.dbManager?.setTotalDonated(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        app!!.dbManager?.close()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_donate, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        super.onPrepareOptionsMenu(menu)
        val report = menu.findItem(R.id.menuReport)
        val donate = menu.findItem(R.id.menuDonate)
        val reset = menu.findItem(R.id.menuReset)
        if (app!!.dbManager?.all?.isEmpty() == true) {
            report.isEnabled = false
            reset.isEnabled = false
        } else {
            report.isEnabled = true
            reset.isEnabled = true
        }
        if (this is Donate) {
            donate.isVisible = false
            if (!app!!.dbManager?.getAll()?.isEmpty()!!) {
                report.isVisible = true
                reset.isEnabled = true
            }
        } else {
            report.isVisible = false
            donate.isVisible = true
            reset.isVisible = false
        }
        return true
    }

    fun report(item: MenuItem?) {
        startActivity(Intent(this, Report::class.java))
    }

    fun donate(item: MenuItem?) {
        startActivity(Intent(this, Donate::class.java))
    }

    open fun reset(item: MenuItem?) {}
}