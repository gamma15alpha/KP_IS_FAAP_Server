package com.kp2.kpspringserver.common.model

import jakarta.persistence.*

@Entity
@Table(name = "lecturer_subjects")
data class LecturerSubject(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,
    @ManyToOne(cascade = [(CascadeType.ALL)])
    var lecturer: User?,
    @ManyToOne(cascade = [(CascadeType.ALL)])
    var subject: Subject?,
)
{
    constructor() : this(null, null, null)
}
