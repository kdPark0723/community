package com.kdpark0723.communityCommon.model.dataAccess

import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono


@Repository
abstract class ReactiveDataAccessorAdapterDataAccessor<Entity, Key, Repository : DataAccessor<Entity, Key>> : ReactiveDataAccessor<Entity, Key> {

    protected abstract val repository: Repository?

    override fun delete(entity: Entity): Mono<Unit> {
        return Mono.fromSupplier { this.repository?.delete(entity) }
    }

    override fun findById(id: Key): Mono<Entity?> {
        return Mono.fromSupplier { this.repository?.findById(id) }
    }

    override fun save(entity: Entity): Mono<Unit> {
        return Mono.fromSupplier { this.repository?.save(entity) }
    }

    override fun exists(id: Key): Mono<Boolean> {
        return Mono.fromSupplier { this.repository?.exists(id) }
    }

}