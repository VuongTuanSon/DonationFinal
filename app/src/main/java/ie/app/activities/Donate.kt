package ie.app.activities

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.widget.Toolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import ie.app.R
import ie.app.models.Donation

class Donate : Base() {
    private var donateButton: Button? = null
    private var paymentMethod: RadioGroup? = null
    private var progressBar: ProgressBar? = null
    private var amountPicker: NumberPicker? = null
    private var amountText: EditText? = null
    private var amountTotal: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donate)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        val fab = findViewById<View>(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        donateButton = findViewById<View>(R.id.donateButton) as Button
        paymentMethod = findViewById<View>(R.id.paymentMethod) as RadioGroup
        progressBar = findViewById<View>(R.id.progressBar) as ProgressBar
        amountPicker = findViewById<View>(R.id.amountPicker) as NumberPicker
        amountText = findViewById<View>(R.id.paymentAmount) as EditText
        amountTotal = findViewById<View>(R.id.totalSoFar) as TextView
        amountPicker!!.minValue = 0
        amountPicker!!.maxValue = 1000
        progressBar!!.max = 10000
        amountTotal!!.text = "$" + app!!.totalDonated
    }

    fun donateButtonPressed(view: View?) {
        val method = if (paymentMethod!!.checkedRadioButtonId == R.id.PayPal) "PayPal" else "Direct"
        var donatedAmount = amountPicker!!.value
        if (donatedAmount == 0) {
            val text = amountText!!.text.toString()
            if (text != "") donatedAmount = text.toInt()
        }
        if (donatedAmount > 0) {
            app!!.newDonation(Donation(donatedAmount, method))
            progressBar!!.progress = app!!.totalDonated
            val totalDonatedStr = "$" + app!!.totalDonated
            amountTotal!!.text = totalDonatedStr
        }
    }

    override fun reset(item: MenuItem?) {
        app!!.dbManager?.reset()
        app!!.totalDonated = 0
        amountTotal!!.text = "$" + app!!.totalDonated
    }
}