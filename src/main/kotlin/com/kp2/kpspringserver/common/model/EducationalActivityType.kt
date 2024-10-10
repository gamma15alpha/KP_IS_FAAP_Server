package com.kp2.kpspringserver.common.model

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank

@Entity
@Table(name = "educational_activity_types")
data class EducationalActivityType(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,
    @Column(unique = true, nullable = false)
    @field:NotBlank(message = "Name is required")
    var name: String? = null,
    @Column(nullable = false)
    var isDeleted: Boolean?,
){
    constructor() : this(null, null, null)
}
