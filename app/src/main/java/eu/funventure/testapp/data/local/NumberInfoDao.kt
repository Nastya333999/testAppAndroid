package eu.funventure.testapp.data.local

import androidx.room.*

@Dao
interface NumberInfoDao {

    @Query("SELECT * FROM NumberInfo")
    fun getAll(): List<NumberInfoEntity>

    @Query("SELECT * FROM NumberInfo WHERE number = :param")
    fun getByNumber(param: Int): NumberInfoEntity

    @Insert
    fun save(entity: NumberInfoEntity)
}