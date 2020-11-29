package com.example.yugiohscorecalculator

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Match(
    @PrimaryKey(autoGenerate = true) val mid: Int,
    val date: String?,
    val player1: String?,
    val player2: String?,
    val winner: String?
)