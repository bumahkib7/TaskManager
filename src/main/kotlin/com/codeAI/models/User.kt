package com.codeAI.models

import com.fasterxml.jackson.annotation.JsonProperty
import io.quarkus.hibernate.reactive.panache.PanacheEntity
import org.hibernate.annotations.CreationTimestamp
import java.time.ZonedDateTime
import javax.persistence.*

@Entity
@Table(name = "users")
@NamedQueries(
    NamedQuery(name = "User.countAll", query = "SELECT count(u) FROM User u order by u.name asc")

)
@NamedNativeQueries(
    NamedNativeQuery(name = "User.findAll", query = "SELECT * FROM users order by name ", resultClass = User::class)
)

@SequenceGenerator(
    name = "userSeq", sequenceName = "user_id_seq", allocationSize = 1, initialValue = 1, schema = "public"
)
class User : PanacheEntity() {
    @Column(name = "name", unique = true, nullable = false)
    var name: String? = null

    @Column(name = "password", nullable = false)
    @JsonProperty("password")
    var password: String? = null

    @CreationTimestamp
    @Column(name = "created", nullable = false, updatable = false)
    var created: ZonedDateTime? = null

    @Version
    @Column(name = "version", nullable = false)
    var version: Long? = null



    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = [JoinColumn(name = "id")])
    @Column(name = "role", nullable = false)
    var roles: List<String>? = null



}