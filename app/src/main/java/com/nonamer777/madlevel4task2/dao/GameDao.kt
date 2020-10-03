package com.nonamer777.madlevel4task2.dao

import androidx.room.*
import com.nonamer777.madlevel4task2.model.Game

@Dao
interface GameDao {

    @Query("select * from game_table")
    suspend fun getAllGames(): List<Game>

    @Insert
    suspend fun saveGame(game: Game)

    @Update
    suspend fun updateGame(game: Game)

    @Delete
    suspend fun deleteGame(game: Game)

    @Query("delete from game_table")
    suspend fun deleteAllGames()

    @Query("select count(*) from game_table where won = 1")
    suspend fun getNumberOfWins(): Int

    @Query("select count(*) from game_table where draw = 1")
    suspend fun getNumberOfDraws(): Int

    @Query("select count(*) from game_table where won = 0 and draw = 0")
    suspend fun getNumberOfLosses(): Int

    @Query("select * from game_table where date = (select max(date) from game_table)")
    suspend fun getLastGame(): Game
}
