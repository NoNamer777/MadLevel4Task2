package com.nonamer777.madlevel4task2.model

import com.nonamer777.madlevel4task2.R

/** Legal moves that exist in the game of Rock, Paper, Scissors. */
enum class Move (

    /** Text representation of a Move, for storage in the database. */
    val value: String,

    /** Link to the visual representation of a Move. */
    val img: Int

) {

    ROCK("rock", R.drawable.rock),

    PAPER("paper", R.drawable.paper),

    SCISSORS("scissors", R.drawable.scissors);

}
