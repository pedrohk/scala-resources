package ioc

@Component
class ApplicationService(
                          val userService: UserService,
                          val notificationService: NotificationService
                        ) {

  def process(id: Long): String = {

    val user = userService.getUser(id)

    notificationService.send(user)
  }
}