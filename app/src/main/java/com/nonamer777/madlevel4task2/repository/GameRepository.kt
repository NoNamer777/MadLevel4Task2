package com.nonamer777.madlevel4task2.repository

import android.content.Context
import com.nonamer777.madlevel4task2.dao.GameDao
import com.nonamer777.madlevel4task2.database.GameDatabase
import com.nonamer777.madlevel4task2.model.Game

class GameRepository(context: Context) {

    private val gameDao: GameDao

    init {
        val database = GameDatabase.getDatabase(context)

        gameDao = database!!.gameDao()
    }

    suspend fun getAllGames(): List<Game> = gameDao.getAllGames()

    suspend fun saveGame(game: Game) = gameDao.saveGame(game)

    suspend fun updateGame(game: Game) = gameDao.updateGame(game)

    suspend fun deleteGame(game: Game) = gameDao.deleteGame(game)

    suspend fun deleteAllGames() = gameDao.deleteAllGames()

    suspend fun getNumberOfWins(): Int = gameDao.getNumberOfWins()

    suspend fun getNumberOfDraws(): Int = gameDao.getNumberOfDraws()

    suspend fun getNumberOfLosses(): Int = gameDao.getNumberOfLosses()

    suspend fun getLastGame(): Game = gameDao.getLastGame()
}
