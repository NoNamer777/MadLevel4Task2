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

    companion object{
        /** Parses String values into Move values. */
        fun parse(value: String): Move? {

            return when (value) {
                PAPER.value -> PAPER
                ROCK.value -> ROCK
                SCISSORS.value -> SCISSORS
                else -> throw error("'$value' is not recognized as a correct move value.")
            }
        }
    }
}
