package com.example.real_netvorking_app.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.real_netvorking_app.R
import com.example.real_netvorking_app.adapter.HomeAdapter
import com.example.real_netvorking_app.model.Home
import com.example.real_netvorking_app.network.retrofit.RetrofitHttp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private var recyclerView: RecyclerView? = null
    private var loadMore: ProgressBar? = null
    lateinit var adapter: HomeAdapter
    var array: List<Home> = ArrayList()
    var a = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.item_fragment_home, container, false)
        initViews(view)
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        apiPosterList(a)
    }

    private fun initViews(view: View?) {
        recyclerView = view?.findViewById(R.id.recyclerView)
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView?.layoutManager = layoutManager
        layoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE
        loadMore = view?.findViewById(R.id.loadMore)
        loadMore?.visibility = View.VISIBLE

        recyclerView!!.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
//                val into = intArrayOf(0, 0)
//                val lastVisiblePosition = layoutManager.findLastVisibleItemPositions(into)
//                if (lastVisiblePosition[1] == layoutManager.itemCount - 1 || lastVisiblePosition[0] == layoutManager.itemCount - 1)
//                    apiPosterList(++a)
                if (!recyclerView.canScrollVertically(1)){
                    loadMore!!.visibility = View.VISIBLE
                    apiPosterList(++a)
                }
            }
        })
    }

    private fun apiPosterList(page: Int) {
        RetrofitHttp.homeServices.getPhotos(page, 20)
            .enqueue(object : Callback<ArrayList<Home>?> {
                override fun onResponse(call: Call<ArrayList<Home>?>, response: Response<ArrayList<Home>?>) {
                    val d = Log.d("Response", response.body().toString())
                    loadMore!!.visibility = View.GONE
                    if (a==1){
                        refreshAdapter(response.body()!!)
                    }else{
                        adapter.addList(response.body()!!)
                    }
                }

                override fun onFailure(call: Call<ArrayList<Home>?>, t: Throwable) {
                    Log.d("Error", t.message.toString())
                }
            })
    }

    private fun refreshAdapter(body: ArrayList<Home>) {
        adapter = HomeAdapter(this, body)
        recyclerView!!.adapter = adapter
        loadMore!!.visibility = View.GONE

    }
}

//    fun openItemDetails(home: Home) {
//        var intent = Intent(this.context, DetailsActivity::class.java)
//        intent.putExtra("image_name", home.urls.small)
//        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(context as Activity)
//        startActivity(intent, options.toBundle())
//    }