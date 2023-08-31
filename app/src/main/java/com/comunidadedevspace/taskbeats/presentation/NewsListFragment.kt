package com.comunidadedevspace.taskbeats.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.comunidadedevspace.taskbeats.R
import com.comunidadedevspace.taskbeats.data.News


/**
 * A simple [Fragment] subclass.
 * Use the [NewsListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewsListFragment : Fragment() {

    private val adapter = NewsListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rvNewsList = view.findViewById<RecyclerView>(R.id.rv_news)
        rvNewsList.adapter = adapter

        //FakeList
        val newsList = listOf<News>(
            News(
                "RecyclerView Example 1",
                "https://media.wired.co.uk/photos/606d9b367aff197af7c72a2f/master/w_1600%2Cc_limit/wired-uk-android-tips-1.jpg"
            ),
            News(
                "RecyclerView Example2",
                "image-url2"
            )

        )

        adapter.submitList(newsList)




    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *

         * @return A new instance of fragment NewsListFragment.
         */
        @JvmStatic
        fun newInstance() = NewsListFragment()
    }
}