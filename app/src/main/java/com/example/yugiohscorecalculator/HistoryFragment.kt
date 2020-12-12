package com.example.yugiohscorecalculator

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_game.*
import java.text.SimpleDateFormat
import java.util.*

class HistoryFragment : Fragment() {

    var player1: String = ""
    var player2: String = ""
    var winner: String = ""
    var date: String = ""
    var check=0

    var data: MutableList<Match>? = null

    var db: AppDatabase? = null
    var listView: RecyclerView? = null
    var adapter: MatchAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments?.isEmpty == false) {
            player1 = arguments!!.getString("player1")!!
            player2 = arguments!!.getString("player2")!!
            winner = arguments!!.getString("winner")!!
            date = arguments!!.getString("date")!!
            check = 1
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        db = AppDatabase.getAppDatabase(this.activity!!.applicationContext)
        data = mutableListOf()


        listView = view.findViewById(R.id.match_list)
        listView?.layoutManager = LinearLayoutManager(this.activity)
        adapter = MatchAdapter(data!!)
        listView?.adapter = adapter

        if(check == 1) {
            addMatch(date, player1, player2, winner)
        }


        db?.getMatchDao()?.getAll()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(
                {
                    if(!it.isNullOrEmpty())
                    {
                        data = it.toMutableList()
                    }
                    adapter = MatchAdapter(data!!)
                    listView?.adapter = adapter
                }
                ,{}
            )

    }

    @SuppressLint("CheckResult")
    fun addMatch(date: String, player1: String, player2: String, winner: String)
    {
        val newMatch = Match(0, date, player1, player2, winner)

        db?.getMatchDao()?.insert(newMatch)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(
                {
                    data?.add(newMatch)
                    adapter?.notifyItemInserted(data!!.lastIndex)
                }
                ,{}
            )

    }

}