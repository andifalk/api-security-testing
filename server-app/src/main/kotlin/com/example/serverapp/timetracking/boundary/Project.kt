package com.example.serverapp.timetracking.boundary

import org.springframework.data.jpa.domain.AbstractPersistable
import javax.persistence.Entity
import javax.validation.constraints.NotNull

@Entity
class Project(@NotNull var title: String, var description: String?) : AbstractPersistable<Long>()