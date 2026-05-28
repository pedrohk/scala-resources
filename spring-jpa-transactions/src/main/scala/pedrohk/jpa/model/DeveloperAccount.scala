package pedrohk.jpa.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "developer_accounts")
class DeveloperAccount {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var identifier: java.lang.Long = _

  @Column(nullable = false)
  var owner: String = _

  @Column(nullable = false)
  var stack: String = _

  @Column(nullable = false)
  var credits: Int = _

  def this(
            owner: String,
            stack: String,
            credits: Int
          ) = {

    this()

    this.owner = owner
    this.stack = stack
    this.credits = credits
  }
}