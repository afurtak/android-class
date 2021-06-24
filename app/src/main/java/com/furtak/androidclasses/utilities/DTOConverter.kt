package com.furtak.androidclasses.utilities

import com.furtak.androidclasses.model.Child
import kotlin.random.Random
import com.furtak.androidclasses.storage.model.Child as ChildDTO

interface DTOConverter<Model, DTO> {
    fun toDto(model: Model): DTO

    fun fromDto(dto: DTO): Model

    fun toDto(models: List<Model>): List<DTO> {
        return models.map(::toDto)
    }

    fun fromDto(dtos: List<DTO>): List<Model> {
        return dtos.map(::fromDto)
    }
}

class JsonChildConverter : DTOConverter<Child, ChildDTO> {
    override fun toDto(model: Child): ChildDTO = ChildDTO(
        id = Random.nextLong(),
        name = model.name,
        surname = model.surname,
        phoneNumber = model.phoneNumber,
        photoPath = model.photoPath,
    )

    override fun fromDto(dto: ChildDTO): Child = Child(
        id = Random.nextLong(),
        name = dto.name,
        surname = dto.surname,
        phoneNumber = dto.phoneNumber,
        photoPath = dto.photoPath,
    )
}