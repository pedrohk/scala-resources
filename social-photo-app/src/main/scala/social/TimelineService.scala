package social

class TimelineService(repository: PhotoRepository) {

  def publishPhoto(
                    id: String,
                    owner: User,
                    url: String,
                    description: String
                  ): Photo = {

    require(id.nonEmpty)
    require(url.nonEmpty)

    if (repository.exists(id)) {
      throw new IllegalArgumentException("Photo already exists")
    }

    val photo = Photo(
      id = id,
      owner = owner,
      url = url,
      description = description
    )

    repository.save(photo)
  }

  def tagPhoto(photoId: String, tag: Tag): Photo = {
    val photo = repository
      .findById(photoId)
      .getOrElse(throw new IllegalArgumentException("Photo not found"))

    val updated = photo.addTag(tag)

    repository.save(updated)
  }

  def commentPhoto(
                    photoId: String,
                    commentId: String,
                    user: User,
                    message: String
                  ): Photo = {

    require(message.nonEmpty)

    val photo = repository
      .findById(photoId)
      .getOrElse(throw new IllegalArgumentException("Photo not found"))

    val comment = Comment(
      id = commentId,
      user = user,
      message = message
    )

    val updated = photo.addComment(comment)

    repository.save(updated)
  }

  def timeline(): List[Photo] = {
    repository.findAll()
  }

  def deletePhoto(photoId: String): Boolean = {
    repository.delete(photoId)
  }

  def getPhoto(photoId: String): Option[Photo] = {
    repository.findById(photoId)
  }
}