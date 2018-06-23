package marmar.com.top10

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import marmar.com.top10.model.FeedEntry

/**
 * Created by Marcin on 14/05/2018.
 */

class ViewHolder(v: View) {
    val tvName: TextView = v.findViewById(R.id.name)
    val tvArtist: TextView = v.findViewById(R.id.artist)
    val tvSummary: TextView = v.findViewById(R.id.sumarry)
}

class FeedAdapter(context: Context, private val resource: Int, private val applications: List<FeedEntry>): ArrayAdapter<FeedEntry>(context, resource) {
    private val TAG = "FeedAdapter"
    private val inflater = LayoutInflater.from(context)

    override fun getCount(): Int {
        return applications.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val viewHolder: ViewHolder
        if (convertView == null) {
            view = inflater.inflate(resource, parent, false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        val currentApp = applications[position]

        viewHolder.tvName.text = currentApp.name.label
        viewHolder.tvArtist.text = currentApp.artist.label
        viewHolder.tvSummary.text = currentApp.summary.label

        return view
    }
}