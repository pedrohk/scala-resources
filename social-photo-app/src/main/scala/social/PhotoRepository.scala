package social

import scala.collection.mutable

class PhotoRepository {

  private val photos = mutable.LinkedHashMap[String, Photo]()

  def save(photo: Photo): Photo = {
    photos.put(photo.id, photo)
    photo
  }

  def findById(id: String): Option[Photo] = {
    photos.get(id)
  }

  def delete(id: String): Boolean = {
    photos.remove(id).isDefined
  }

  def findAll(): List[Photo] = {
    photos.values.toList.sortBy(_.createdAt).reverse
  }

  def exists(id: String): Boolean = {
    photos.contains(id)
  }

  def count(): Int = {
    photos.size
  }
}