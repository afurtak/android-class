package com.furtak.androidclasses.database.utilities

import com.furtak.androidclasses.database.model.ChildEntity
import com.furtak.androidclasses.model.Child
import com.furtak.androidclasses.utilities.DTOConverter

class ChildEntityConverter : DTOConverter<Child, ChildEntity> {
    override fun toDto(model: Child): ChildEntity {
        return ChildEntity(
            model.id,
            model.name,
            model.surname,
            model.phoneNumber,
            model.photoPath,
        )
    }

    override fun fromDto(dto: ChildEntity): Child {
        return Child(
            dto.id,
            dto.name,
            dto.surname,
            dto.phoneNumber,
            dto.photoPath,
        )
    }
}