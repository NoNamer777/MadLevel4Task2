package com.nonamer777.madlevel4task2.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/** A object representation of the game Rock, Paper, Scissors. */
@Entity(tableName = "game_table")
data class Game(

    @ColumnInfo(name = "move_user")
    /** A move that the User made. */
    val userMove: Move,

    @ColumnInfo(name = "move_computer")
    /** A move that was generated for the Computer. */
    val computerMove: Move,

    @ColumnInfo(name = "won")
    /** If the User had won. Is `false` if the User had lost or played a draw. */
    var won: Boolean = false,

    @ColumnInfo(name = "draw")
    /** If the User had played a draw. Is `false` if the User had won or lost. */
    var draw: Boolean = false,

    @ColumnInfo(name = "date")
    /** The date on which the game is played. */
    val date: Date = Date(),

    /** A unique number by which a game can be identified with. */
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long? = null
)
