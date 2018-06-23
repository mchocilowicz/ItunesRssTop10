package marmar.com.top10.activity

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import marmar.com.top10.FeedAdapter
import marmar.com.top10.ItunesRssService
import marmar.com.top10.R
import marmar.com.top10.model.Response
import retrofit2.Call
import retrofit2.Callback
import javax.inject.Inject

class MainActivity : BaseActivity() {
    private val TAG = "MainActivity"

    private var feedLimit = 10
    private var feedUrl = 0
    private var feedCachedUrl = -1
    private var cachedLimit = 10
    private val STATE_URL = "feedURL"
    private val STATE_LIMIT = "feedLimit"
    private lateinit var request: Call<Response>

    @Inject lateinit var service: ItunesRssService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getInjector().inject(this)
        if (savedInstanceState != null) {
            feedUrl = savedInstanceState.getInt(STATE_URL)
            feedLimit = savedInstanceState.getInt(STATE_LIMIT)
        }
        downloadUrl(R.string.topFreeURL, cachedLimit)
    }

    override fun onDestroy() {
        super.onDestroy()
        request.cancel()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.feeds_menu, menu)
        if(feedLimit == 10)
            menu?.findItem(R.id.menu10)?.isChecked = true
        else
            menu?.findItem(R.id.menu25)?.isChecked = true
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            R.id.menuFree -> feedUrl = R.string.topFreeURL
            R.id.menuPaid -> feedUrl = R.string.topPaidsUrl
            R.id.menu10, R.id.menu25 -> {
                if(!item.isChecked) {
                    item.isChecked = true
                    feedLimit = 35 - feedLimit
                    Log.d(TAG, "${item.title}: ${feedLimit}")
                } else {
                    Log.d(TAG, "${item.title}: ${feedLimit}")
                }
            }
            R.id.menuRefresh -> feedCachedUrl = -1
            else -> return super.onOptionsItemSelected(item)
        }
        downloadUrl(feedUrl, feedLimit)
        return true
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putInt(STATE_URL, feedUrl)
        outState?.putInt(STATE_LIMIT, feedLimit)
    }

    private fun downloadUrl(feedUrl: Int, feedLimit: Int) {
        if(feedUrl != feedCachedUrl || feedLimit !=  cachedLimit) {
            feedCachedUrl = feedUrl
            cachedLimit = feedLimit
            request = service.getFeedWithLimit(getString(feedUrl), feedLimit)
            request.enqueue(object: Callback<Response> {
                override fun onFailure(call: Call<Response>?, t: Throwable?) {
                    Log.d(TAG, t.toString())
                    Toast.makeText(applicationContext, t.toString(), Toast.LENGTH_LONG).show()
                }
                override fun onResponse(call: Call<Response>?, response: retrofit2.Response<Response>?) {
                    if(response != null && response.isSuccessful) {
                        val body = response.body()
                        if (body != null) {
                            val feedAdapter = FeedAdapter(applicationContext, R.layout.list_record, body.feed.entry)
                            listView.adapter = feedAdapter
                            var count = feedAdapter.count
                            var s = ""
                        }
                    }
                }
            })
        }
    }

}
