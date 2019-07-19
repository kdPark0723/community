package com.kdpark0723.communityCommon.model.dataAccess

import reactor.core.publisher.Mono


interface ReactiveDataAccessor<Entity, Key> {

    fun delete(entity: Entity): Mono<Unit>

    fun findById(id: Key): Mono<Entity?>

    fun save(entity: Entity): Mono<Unit>

    fun exists(id: Key): Mono<Boolean>
}