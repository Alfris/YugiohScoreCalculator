package com.example.yugiohscorecalculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HistoryFragment : Fragment() {

    var data = mutableListOf<Match>()

    var db: AppDatabase? = null
    var listView: RecyclerView? = null
    lateinit var adapter: MatchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        db = AppDatabase.getAppDatabase(this.activity!!.applicationContext)

        listView = view?.findViewById(R.id.match_list);
        listView?.layoutManager = LinearLayoutManager(this.activity)

        adapter = MatchAdapter(data)

        listView?.adapter = adapter

        db?.getMatchDao()?.getAll()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(
                {
                    data.clear()
                    if(!it.isNullOrEmpty())
                    {
                        data.addAll(it)
                    }
                    else
                    {
                        data = mutableListOf()
                    }
                    adapter.notifyDataSetChanged()
                }
                ,{}
            )
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

}