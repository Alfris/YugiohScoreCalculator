package com.example.yugiohscorecalculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_game.*

class GameFragment : Fragment(), View.OnClickListener {

    lateinit var navController : NavController
    lateinit var player1: String
    lateinit var player2: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        player1 = arguments!!.getString("player1")!!
        player2= arguments!!.getString("player2")!!
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

        player1_text.text = player1
        player2_text.text = player2

    }

    override fun onClick(v: View?) {
        when(v!!.id) {
            R.id.coin_flip_btn -> navController.navigate(R.id.action_gameFragment_to_coinFlipFragment)
            R.id.dice_roll_btn -> navController.navigate(R.id.action_gameFragment_to_diceRollFragment)
            R.id.coin_flip_btn2 -> navController.navigate(R.id.action_gameFragment_to_coinFlipFragment)
            R.id.dice_roll_btn2 -> navController.navigate(R.id.action_gameFragment_to_diceRollFragment)
            R.id.add_btn -> navController.navigate(R.id.action_gameFragment_to_addScoreFragmentP1)
            R.id.add_btn2 -> navController.navigate(R.id.action_gameFragment_to_addScoreFragmentP2)
            R.id.subtract_btn -> navController.navigate(R.id.action_gameFragment_to_subtractScoreFragmentP1)
            R.id.subtract_btn2 -> navController.navigate(R.id.action_gameFragment_to_subtractScoreFragmentP2)
        }
    }
}