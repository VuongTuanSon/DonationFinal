package ie.app.activities

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import ie.app.R
import ie.app.models.Donation

class Report : Base(), OnItemClickListener {
    var listView: ListView? = null
    var adapter: DonationAdapter? = null
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)
        listView = findViewById<View>(R.id.reportList) as ListView
        adapter = app!!.dbManager?.let { DonationAdapter(this, it.all) }
        listView!!.adapter = adapter
        listView!!.onItemClickListener = this
    }

    override fun onItemClick(arg0: AdapterView<*>?, arg1: View, pos: Int, id: Long) {
        Toast.makeText(this, """You Selected Row [ $pos]
For Donation Data [ ${adapter!!.donations[pos]}]
 With ID of [$id]""", Toast.LENGTH_LONG).show()
    }
}

class DonationAdapter(context: Context, var donations: List<Donation>) : ArrayAdapter<Donation?>(context, R.layout.row_donate, donations) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.row_donate, parent, false)
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