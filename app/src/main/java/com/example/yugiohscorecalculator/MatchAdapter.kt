package com.example.yugiohscorecalculator

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MatchAdapter(data: MutableList<Match>) : RecyclerView.Adapter<MatchAdapter.ViewHolder>()
{
    class ViewHolder(v: View): RecyclerView.ViewHolder(v)
    {
        var TextViewDate = v.findViewById<TextView>(R.id.textViewDate)
        var TextViewPlayer1 = v.findViewById<TextView>(R.id.textViewPlayer1)
        var TextViewPlayer2 = v.findViewById<TextView>(R.id.textViewPlayer2)
        var TextViewWinner = v.findViewById<TextView>(R.id.textViewWinner)
    }

    var data = data

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_match, parent, false)

        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val match = data[position]

        holder.TextViewDate.text = match.date
        holder.TextViewPlayer1.text = match.player1
        holder.TextViewPlayer2.text = match.player2
        holder.TextViewWinner.text = match.winner
    }
}