package com.kdpark0723.communityCommon.models.dao

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
abstract class SpringDAOAdapter<Entity, Key, Repository : CrudRepository<Entity, Key>> : DataAccessObject<Entity, Key> {
    protected final var registeredRepository: Repository? = null

    override fun delete(entity: Entity) {
        registeredRepository?.delete(entity)
    }

    override fun findById(id: Key): Entity? {
        val entity = registeredRepository?.findById(id)

        return unOptional(entity)
    }

    protected fun unOptional(entity: Optional<Entity>?): Entity? {
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
        registeredRepository?.save(entity)
    }

    override fun exists(id: Key): Boolean {
        return registeredRepository?.existsById(id) ?: false
    }

}