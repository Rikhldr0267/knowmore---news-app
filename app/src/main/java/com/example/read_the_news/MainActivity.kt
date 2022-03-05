package com.example.read_the_news

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity(), clickhandler {

    private lateinit var mAdapter: myadapter

    private lateinit var recycle :RecyclerView   //declare a variable of type recyclerview

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val recyclerView  = findViewById(R.id.recyclerview)

        recycle = findViewById(R.id.recyclerview)   //getting the recycler view

        recycle.layoutManager = LinearLayoutManager(this)   // creating the layout manager


        listadapter()
        mAdapter = myadapter(this)

        recycle.adapter = mAdapter

    }

    private fun listadapter(){
        val queue = Volley.newRequestQueue(this)
        val url = "https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=87740e3457ac45a68da6c244ec22acc4"
        val getRequest: JsonObjectRequest = object: JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener {
                      val newsJSONArray = it.getJSONArray("articles")
                      val newsArray = ArrayList<news>()
                for(i in 0 until newsJSONArray.length()){
                    val newsJSONObject = newsJSONArray.getJSONObject(i)
                    val newss = news(
                        newsJSONObject.getString("title"),
                        newsJSONObject.getString("author"),
                        newsJSONObject.getString("url"),
                        newsJSONObject.getString("urlToImage")
                    )
                    newsArray.add(newss)

                }
                mAdapter.updateNews(newsArray)
            },
            Response.ErrorListener { error ->


            }
        ){
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val params: MutableMap<String, String> = HashMap()
                params["User-Agent"] = "Mozilla/5.0"
                return params
            }
        }
        queue.add(getRequest)

// Access the RequestQueue through your singleton class.
        //MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }

    override fun onclicked(item: news) {
        val builder = CustomTabsIntent.Builder()
        val CustomTabsIntent = builder.build()
        CustomTabsIntent.launchUrl(this, Uri.parse(item.url))
    }


}





// So , what we did ?

// 1) made recyclerview in activity_main.xml file
// 2) made xml for scrolling ( i.e. recyclerview_items.xml )
/* 3) made a viewholder class that contains items which will be filled by value later and this is to show as a list later on
   4) Made a adapter class

       implement 3 methods

        a) onCreateViewHolder - to inflate and to handle clicks
        b) getItemCount  -  to count list size
        c) onBindViewHolder   - holder to bind with current item

     5) in Main activity
        a) layoutmanager is created
        b) taking items
        c) putting them in adapter
        d) connecting adapter with recyclerview

    6) Adding volley Library
        a) add implementation in built.gradle(app level)
        b) add Internet permission in menifest file
*/