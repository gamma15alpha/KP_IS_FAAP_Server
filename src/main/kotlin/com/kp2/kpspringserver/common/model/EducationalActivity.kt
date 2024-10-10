package com.kp2.kpspringserver.common.model

import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*
import java.sql.Date

@Entity
@Table(name = "educational_activityes")
data class EducationalActivity(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,
    @Column(nullable = false)
    var date: Date? = null,
    @Column(nullable = false)
    var isDeleted: Boolean?,

    @OneToOne
    var group: Group? = null,
    @ManyToOne
    var type: EducationalActivityType? = null,
    @ManyToOne
    var lecturerSubject: LecturerSubject? = null,

    @OneToMany(mappedBy = "educationalActivity")
    @JsonManagedReference
    var tasks: List<Task>? = null,
)
{
    constructor() : this(null, null, null, null, null, null, null)
}
