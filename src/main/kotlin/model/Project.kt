package model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal
import java.time.LocalDateTime

@Document
data class Project(
    @Id
    val id: ObjectId = ObjectId.get(),
    val name: String,
    var description: String,
    var startedDate: LocalDateTime?,
    var completedDate: LocalDateTime?,
    var moneySpent: BigDecimal?
)