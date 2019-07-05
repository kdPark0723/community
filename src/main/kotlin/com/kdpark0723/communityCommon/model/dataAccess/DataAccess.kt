package com.kdpark0723.communityCommon.model.dataAccess


interface DataAccess<Entity, Key> {

    fun delete(entity: Entity)

    fun findById(id: Key): Entity?

    fun save(entity: Entity)

    fun exists(id: Key): Boolean
}
