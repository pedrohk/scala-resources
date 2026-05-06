package notes

enum SyncStatus {
  case PENDING, SYNCED
}

case class Note(
                 id: String,
                 title: String,
                 content: String,
                 deleted: Boolean,
                 status: SyncStatus
               )