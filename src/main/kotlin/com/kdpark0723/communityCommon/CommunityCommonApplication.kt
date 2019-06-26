package com.kdpark0723.communityCommon

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters
import java.util.*
import javax.annotation.PostConstruct


@SpringBootApplication
@EntityScan(basePackageClasses = [CommunityCommonApplication::class, Jsr310JpaConverters::class])
class CommunityCommonApplication {
    @PostConstruct
    internal fun init() {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT"))
    }
}

fun main(args: Array<String>) {
    runApplication<CommunityCommonApplication>(*args)
}