package fileshare

case class FileData(
                     id: String,
                     name: String,
                     encryptedContent: Array[Byte],
                     deleted: Boolean
                   )