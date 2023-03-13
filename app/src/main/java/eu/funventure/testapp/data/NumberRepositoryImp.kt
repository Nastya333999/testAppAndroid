package eu.funventure.testapp.data

import eu.funventure.testapp.data.local.NumberInfoEntity
import eu.funventure.testapp.data.local.NumberInfoDao
import eu.funventure.testapp.data.local.mapToDomain
import eu.funventure.testapp.data.local.mapToEntity
import eu.funventure.testapp.data.remote.ApiInterface
import eu.funventure.testapp.data.remote.NumberInfoDTO
import eu.funventure.testapp.domain.NumberRepository
import eu.funventure.testapp.domain.model.NumberInfo
import retrofit2.Response


class NumberRepositoryImp(
    private val api: ApiInterface,
    private val dataBase: NumberInfoDao
) : NumberRepository {

    override suspend fun getRandomNumber(): Int {
        val response: Response<NumberInfoDTO> = api.getRandomData()
        return if (response.isSuccessful) {
            response.body()?.number ?: 0
        } else {
            0
        }
    }

    override suspend fun getDescriptionByNumber(number: Int): String {
        val response = api.getData(number)
        return if (response.isSuccessful) {
            response.body()?.text ?: ""
        } else {
            ""
        }
    }

    override suspend fun getNumberInfoByNumber(number: Int): NumberInfo {
        val info: NumberInfoEntity = dataBase.getByNumber(number)
        return info.mapToDomain()
    }

    override suspend fun getAllNumberInfo(): List<NumberInfo> {
        val infoList: List<NumberInfoEntity> = dataBase.getAll()
        return infoList.map { it.mapToDomain() }
    }

    override suspend fun saveNumberInfo(info: NumberInfo) {
        dataBase.save(info.mapToEntity())
    }
}