package com.kdpark0723.communityCommon.model.dataAccessObject

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
abstract class DataAccessAdapterCrudRepository<Entity, Key, Repository : CrudRepository<Entity, Key>> : DataAccess<Entity, Key> {
    override fun delete(entity: Entity) {
        repository?.delete(entity)
    }

    override fun findById(id: Key): Entity? {
        val entity = repository?.findById(id)

        return convertNullable(entity)
    }

    protected fun <T> convertNullable(entity: Optional<T>?): T? {
        return if (entity != null) {
            if (entity.isPresent) {
                entity.get()
            } else {
                null
            }
        } else {
            null
        }
    }

    override fun save(entity: Entity) {
        repository?.save(entity)
    }

    override fun exists(id: Key): Boolean {
        return repository?.existsById(id) ?: false
    }

    protected abstract val repository: Repository?
}