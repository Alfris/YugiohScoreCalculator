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
import kotlinx.android.synthetic.main.fragment_coin_flip.*
import java.util.*

class CoinFlipFragment : Fragment(),  View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_coin_flip, container, false)
    }

    lateinit var navController: NavController
    lateinit var coinImage: ImageView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        //view.findViewById<Button>(R.id.toGame).setOnClickListener(this);
        coinImage =  view.findViewById(R.id.coin_image)
        coinImage.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v!!.id) {
            R.id.coin_image -> {

                coin_image.animate().apply{
                    duration = 1000;
                    rotationYBy(1800f)
                    coin_image.isClickable = false
                }.withEndAction{
                    val randomNumber =  Random().nextInt(6) + 1
                    val drawableResource = when (randomNumber) {
                        1 -> (R.drawable.tails)
                        2 -> (R.drawable.heads)
                        3 -> (R.drawable.tails)
                        4 -> (R.drawable.heads)
                        5 -> (R.drawable.tails)
                        else -> (R.drawable.heads)
                    }
                    coinImage.setImageResource(drawableResource)
                    coin_image.isClickable = true
                }

            }
            //R.id.toGame -> navController.navigate(R.id.action_diceRollFragment_to_gameFragment)
        }
    }
}