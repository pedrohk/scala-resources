package pedrohk.cache.model

case class Portfolio(
                      identifier: Long,
                      owner: String,
                      technology: String,
                      repositoryCount: Int
                    )