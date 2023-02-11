package com.codeAI.models

import io.quarkus.hibernate.reactive.panache.PanacheEntity
import org.hibernate.annotations.CreationTimestamp
import java.time.ZonedDateTime
import javax.persistence.*


@Entity
@Table(name = "tasks")
class Task : PanacheEntity() { // omitted field declarations


    @Column(nullable = false)
    var title: String? = null

    @Column(length = 1000)
    var description: String? = null
   @Column(nullable = false)
    var priority: Int? = null

    @ManyToOne(optional = false)
    var user: User? = null

    @Column(nullable = false)
    var complete: ZonedDateTime? = null

    @ManyToOne
    var project: Project? = null

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    var created: ZonedDateTime? = null

    @Version
    var version : Int? = null
}


