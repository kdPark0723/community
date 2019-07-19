package com.kdpark0723.communityCommon.model.dataAccess

import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers


@Repository
abstract class ReactiveDataAccessorAdapterDataAccessor<Entity, Key, Repository : DataAccessor<Entity, Key>> : ReactiveDataAccessor<Entity, Key> {

    protected abstract val repository: Repository?

    private val scheduler = Schedulers.newElastic("myThreads")

    override fun delete(entity: Entity): Mono<Unit> {
        return Mono.fromSupplier { this.repository?.delete(entity) }
            .subscribeOn(this.scheduler)
    }

    override fun findById(id: Key): Mono<Entity?> {
        return Mono.fromSupplier { this.repository?.findById(id) }
            .subscribeOn(this.scheduler)
    }

    override fun save(entity: Entity): Mono<Unit> {
        return Mono.fromSupplier { this.repository?.save(entity) }
            .subscribeOn(this.scheduler)
    }

    override fun exists(id: Key): Mono<Boolean> {
        return Mono.fromSupplier { this.repository?.exists(id) }
            .subscribeOn(this.scheduler)
    }

}