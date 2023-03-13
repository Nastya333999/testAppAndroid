package eu.funventure.testapp.data.local

import eu.funventure.testapp.domain.model.NumberInfo

fun NumberInfoEntity.mapToDomain(): NumberInfo {
    return NumberInfo(
        value = this.number,
        description = this.text
    )
}

fun NumberInfo.mapToEntity(): NumberInfoEntity {
    return NumberInfoEntity(
        id = 0,
        number = this.value,
        text = this.description
    )
}

