package com.kdpark0723.communityCommon.model.dataAccessObject

abstract class MockDataAccess<Entity, Key> : DataAccess<Entity, Key> {
    protected val repository: MutableMap<Key, Entity> = mutableMapOf()

    override fun delete(entity: Entity) {
        repository.remove(getEntityKey(entity))
    }

    override fun findById(id: Key): Entity? {
        return repository[id]
    }

    override fun save(entity: Entity) {
        repository[getEntityKey(entity)] = entity
    }

    override fun exists(id: Key): Boolean {
        return repository[id] != null
    }

    abstract fun getEntityKey(entity: Entity): Key
}

