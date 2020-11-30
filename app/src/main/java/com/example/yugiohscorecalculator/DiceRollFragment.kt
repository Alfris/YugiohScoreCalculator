package com.example.yugiohscorecalculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.*
import java.util.*

class DiceRollFragment : Fragment(), View.OnClickListener {

    lateinit var navController: NavController
    lateinit var diceImage:ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dice_roll, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        diceImage =  view.findViewById(R.id.dice_image)
        diceImage.setOnClickListener(this)
    }
    override fun onClick(v: View?) {

        when(v!!.id) {
            R.id.dice_image -> {
                val randomNumber = Random().nextInt(6) + 1
                val drawableResource = when (randomNumber) {
                    1 -> (R.drawable.dice1)
                    2 -> (R.drawable.dice2)
                    3 -> (R.drawable.dice3)
                    4 -> (R.drawable.dice4)
                    5 -> (R.drawable.dice5)
                    else -> (R.drawable.dice6)
                }
                diceImage.setImageResource(drawableResource)
            }

        }
    }

}