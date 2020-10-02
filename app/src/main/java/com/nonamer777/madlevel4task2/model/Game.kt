package com.nonamer777.madlevel4task2.model

import java.util.*

/** A object representation of the game Rock, Paper, Scissors. */
data class Game(

    /** The date on which the game is played. */
    val date: Date,

    /** A move that the User made. */
    val playerMove: Move,

    /** A move that was generated for the Computer. */
    val computerMove: Move,

    /** If the User had won. Is `false` if the User had lost or played a draw. */
    var won: Boolean = false,

    /** If the User had played a draw. Is `false` if the User had won or lost. */
    var draw: Boolean = false,

    /** A unique number by which a game can be identified with. */
    private val id: Int? = null

)
