package eu.funventure.testapp.domain

import eu.funventure.testapp.domain.model.NumberInfo

interface NumberRepository {

    suspend fun getRandomNumber(): Int

    suspend fun getDescriptionByNumber(number: Int): String

    suspend fun saveNumberInfo(info: NumberInfo)

    suspend fun getNumberInfoByNumber(number: Int): NumberInfo

    suspend fun getAllNumberInfo(): List<NumberInfo>
}