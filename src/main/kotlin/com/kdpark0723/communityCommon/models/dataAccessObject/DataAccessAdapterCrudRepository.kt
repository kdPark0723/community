package com.kdpark0723.communityCommon.models.dataAccessObject

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
abstract class DataAccessAdapterCrudRepository<Entity, Key, Repository : CrudRepository<Entity, Key>> : DataAccess<Entity, Key> {
    protected abstract val repository: Repository?

    override fun delete(entity: Entity) {
        repository?.delete(entity)
    }

    override fun findById(id: Key): Entity? {
        val entity = repository?.findById(id)

        return convertNullable(entity)
    }

    protected fun convertNullable(entity: Optional<Entity>?): Entity? {
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
}