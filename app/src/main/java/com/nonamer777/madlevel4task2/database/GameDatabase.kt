package com.nonamer777.madlevel4task2.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nonamer777.madlevel4task2.dao.GameDao
import com.nonamer777.madlevel4task2.model.Converters
import com.nonamer777.madlevel4task2.model.Game

@TypeConverters(Converters::class)
@Database(entities = [Game::class], version = 1, exportSchema = false)
abstract class GameDatabase: RoomDatabase() {

    abstract fun gameDao(): GameDao

    companion object {

        private const val DATABASE_NAME = "GAME_DATABASE"

        @Volatile
        private var gameDatabaseInstance: GameDatabase? = null

        fun getDatabase(context: Context): GameDatabase? {

            if (gameDatabaseInstance == null) {

                synchronized(GameDatabase::class.java) {

                    if (gameDatabaseInstance == null) {
                        gameDatabaseInstance = Room.databaseBuilder(
                            context.applicationContext,
                            GameDatabase::class.java,
                            DATABASE_NAME
                        ).build()
                    }
                }
            }
            return gameDatabaseInstance
        }
    }
}
