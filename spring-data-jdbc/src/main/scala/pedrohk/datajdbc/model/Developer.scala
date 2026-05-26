package pedrohk.datajdbc.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("developers")
case class Developer(
                      @Id
                      id: java.lang.Long,
                      fullName: String,
                      specialty: String,
                      experienceYears: Int,
                      active: Boolean
                    )