package com.example.yugiohscorecalculator

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_game.*
import java.text.SimpleDateFormat
import java.util.*

class GameFragment : Fragment(), View.OnClickListener {

    lateinit var navController : NavController
    lateinit var player1: String
    lateinit var player2: String

    var winner: String = "None";

    var data: MutableList<Match>? = null

    var db: AppDatabase? = null
    var adapter: MatchAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        player1 = arguments!!.getString("player1")!!
        player2= arguments!!.getString("player2")!!

        db = AppDatabase.getAppDatabase(this.activity!!.applicationContext)

        data = mutableListOf()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view);
        view.findViewById<Button>(R.id.coin_flip_btn).setOnClickListener(this);
        view.findViewById<Button>(R.id.dice_roll_btn).setOnClickListener(this);
        view.findViewById<Button>(R.id.coin_flip_btn2).setOnClickListener(this);
        view.findViewById<Button>(R.id.dice_roll_btn2).setOnClickListener(this);
        view.findViewById<Button>(R.id.add_btn).setOnClickListener(this);
        view.findViewById<Button>(R.id.add_btn2).setOnClickListener(this);
        view.findViewById<Button>(R.id.subtract_btn).setOnClickListener(this);
        view.findViewById<Button>(R.id.subtract_btn2).setOnClickListener(this);
        view.findViewById<Button>(R.id.test_btn).setOnClickListener(this);
        view.findViewById<FloatingActionButton>(R.id.reset_point_btn).setOnClickListener(this);

        player1_text.text = player1
        player2_text.text = player2
        player1_score.text = "8000"
        player2_score.text = "8000"


    }

    fun addMatch(v:View)
    {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        var date = timeStamp.toString()
        val score1: String = player1_score.text.toString()
        val score2: String = player2_score.text.toString()
        if (score1.toInt() <= 0)
        {
            winner = player2_text.text.toString()
        }
        if(score2.toInt() <= 0)
        {
            winner = player1_text.text.toString()
        }

        var newMatch = Match(0, date, player1, player2, winner)

        db?.getMatchDao()?.insert(newMatch)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(
                {
                    data?.add(newMatch)
                    adapter?.notifyItemInserted(data!!.size)

                }
                ,{}
            )

    }

    override fun onClick(v: View?) {
        when(v!!.id) {
            R.id.coin_flip_btn -> navController.navigate(R.id.action_gameFragment_to_coinFlipFragment)
            R.id.dice_roll_btn -> navController.navigate(R.id.action_gameFragment_to_diceRollFragment)
            R.id.coin_flip_btn2 -> navController.navigate(R.id.action_gameFragment_to_coinFlipFragment)
            R.id.dice_roll_btn2 -> navController.navigate(R.id.action_gameFragment_to_diceRollFragment)
            R.id.add_btn -> player1_score.text  = (player1_score.text.toString().toInt() + input_number.text.toString().toInt()).toString()
            R.id.add_btn2 -> player2_score.text  = (player2_score.text.toString().toInt() + input_number.text.toString().toInt()).toString()
            R.id.subtract_btn -> player1_score.text  = (player1_score.text.toString().toInt() - input_number.text.toString().toInt()).toString()
            R.id.subtract_btn2 ->  player2_score.text  = (player2_score.text.toString().toInt() - input_number.text.toString().toInt()).toString()
            R.id.reset_point_btn -> {
                player1_score.text = "8000"
                player2_score.text = "8000"}
            R.id.test_btn -> addMatch(v)
        }
    }
}