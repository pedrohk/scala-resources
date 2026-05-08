package social

case class Photo(
                  id: String,
                  owner: User,
                  url: String,
                  description: String,
                  tags: Set[Tag] = Set.empty,
                  comments: List[Comment] = List.empty,
                  createdAt: Long = System.currentTimeMillis()
                ) {

  def addTag(tag: Tag): Photo = {
    copy(tags = tags + tag)
  }

  def addComment(comment: Comment): Photo = {
    copy(comments = comments :+ comment)
  }
}