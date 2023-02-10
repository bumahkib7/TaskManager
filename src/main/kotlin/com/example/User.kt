package com.example

import io.quarkus.hibernate.reactive.panache.PanacheEntity
import org.hibernate.annotations.CreationTimestamp
import java.time.ZonedDateTime
import javax.persistence.*

@Entity
@Table(name = "users")
public class Users : PanacheEntity() {
    @Column(name = "name", unique = true, nullable = false)
    public var name: String? = null

    @Column(name = "password", nullable = false)
    public var password: String? = null

    @CreationTimestamp
    @Column(name = "created", nullable = false, updatable = false)
    public var created: ZonedDateTime? = null

    @Version
    @Column(name = "version", nullable = false)
    public var version: Long? = null

    @ElementCollection(fetch = javax.persistence.FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = [javax.persistence.JoinColumn(name = "user_id")])

    @Column(name = "role", nullable = false)
    public var roles: List<String>? = null


}