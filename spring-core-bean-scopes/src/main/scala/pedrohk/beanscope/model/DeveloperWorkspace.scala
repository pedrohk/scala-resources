package pedrohk.beanscope.model

class DeveloperWorkspace(
                          val owner: String,
                          val createdAt: Long
                        ) {

  def identity: String = {
    s"$owner-$createdAt"
  }

}