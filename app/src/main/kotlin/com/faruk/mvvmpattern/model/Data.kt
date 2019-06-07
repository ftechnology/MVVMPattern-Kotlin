package com.faruk.mvvmpattern.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Class which provides a model for data
 * @constructor Sets all properties of the data
 * @property userId the unique identifier of the author of the data
 * @property id the unique identifier of the data
 * @property title the title of the data
 * @property body the content of the data
 */
@Entity
data class Data(
        val userId: Int,
        @field:PrimaryKey
        val id: Int,
        val title: String,
        val body: String
)