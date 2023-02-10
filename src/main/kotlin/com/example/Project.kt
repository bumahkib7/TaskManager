package com.example

import io.quarkus.hibernate.reactive.panache.PanacheEntity
import org.hibernate.annotations.CreationTimestamp
import java.time.ZonedDateTime
import javax.persistence.*


@Entity
@Table(name = "projects",
        uniqueConstraints = [UniqueConstraint(columnNames = ["name", "user_id"])]
    )
class Project : PanacheEntity() {

    @Column(name = "name", nullable = false)
    public var name: String? = null

    @ManyToOne(optional = false)
    var user: User? = null

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    var created: ZonedDateTime? = null

    @Version
    var version: Int? = null

}