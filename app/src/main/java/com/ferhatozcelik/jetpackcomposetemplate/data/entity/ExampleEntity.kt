package com.ferhatozcelik.jetpackcomposetemplate.data.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "example_table")
data class ExampleEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val title: String?,
    val description: String?
) : Parcelable


