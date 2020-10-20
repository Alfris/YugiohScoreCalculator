package com.example.yugiohscorecalculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.Navigation

class MainFragment : Fragment(), View.OnClickListener {

    lateinit var navController : NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view);
        view.findViewById<Button>(R.id.create_btn).setOnClickListener(this);
        view.findViewById<Button>(R.id.history_btn).setOnClickListener(this);
        view.findViewById<Button>(R.id.about_btn).setOnClickListener(this);
    }

    override fun onClick(v: View?) {
        when(v!!.id)
        {
            R.id.create_btn -> navController.navigate(R.id.action_mainFragment_to_createGameFragment)
            R.id.history_btn -> navController.navigate(R.id.action_mainFragment_to_historyFragment)
            R.id.about_btn -> navController.navigate(R.id.action_mainFragment_to_aboutFragment)
        }
    }
}