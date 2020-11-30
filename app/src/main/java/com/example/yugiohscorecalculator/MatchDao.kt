package com.example.yugiohscorecalculator

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface MatchDao
{
    @Insert
    fun insert(match: Match) : Completable

    @Query("SELECT * FROM `Match`")
    fun getAll() : Single<List<Match>>

    @Query("Select * FROM `Match` WHERE mid = (:matchId)")
    fun getById(matchId: Int) : Single<Match>


}