package com.nonamer777.madlevel4task2.model

import androidx.room.TypeConverter
import java.util.*

/** Converters for object attributes. */
class Converters {

    @TypeConverter
    fun fromTimestampToDate(value: Long?): Date? = value?.let { Date(it) }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? = date?.time

    @TypeConverter
    fun fromStringToMove(value: String?): Move? = value?.let { Move.parse(value) }

    @TypeConverter
    fun moveToString(move: Move?): String? = move?.value

}
