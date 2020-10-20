package com.example.yugiohscorecalculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_create_game.*

class CreateGameFragment : Fragment(), View.OnClickListener {

    lateinit var navController : NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view);
        view.findViewById<Button>(R.id.start_btn).setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        val bundle = Bundle()
        bundle.putString("player1", player1_field.text.toString())
        bundle.putString("player2", player2_field.text.toString())

        navController.navigate(R.id.action_createGameFragment_to_gameFragment, bundle)
    }


}