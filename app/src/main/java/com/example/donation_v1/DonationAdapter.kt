package com.example.donation_v1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView


internal class DonationAdapter(context: Context, var donations: List<Donation>) : ArrayAdapter<Donation?>(context, R.layout.row_donate, donations) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = inflater.inflate(R.layout.row_donate, parent, false)
        val donation = donations[position]
        val amountView = view.findViewById<View>(R.id.row_amount) as TextView
        val methodView = view.findViewById<View>(R.id.row_method) as TextView
        amountView.text = "$" + donation.amount
        methodView.text = donation.method
        return view
    }

    override fun getCount(): Int {
        return donations.size
    }
}

