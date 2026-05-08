package social

case class Comment(
                    id: String,
                    user: User,
                    message: String,
                    timestamp: Long = System.currentTimeMillis()
                  )