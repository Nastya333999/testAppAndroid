package eu.funventure.testapp.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "NumberInfo")
data class NumberInfoEntity(

    @PrimaryKey(autoGenerate = true)
    val id :Int,

    @ColumnInfo(name = "number")
    val number: Int,

    @ColumnInfo(name = "text")
    val text: String
)
