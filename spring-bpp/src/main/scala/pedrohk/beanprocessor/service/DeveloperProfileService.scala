package pedrohk.beanprocessor.service

class DeveloperProfileService {

  private var owner: String =
    "unknown"

  private var initialized:
    Boolean = false

  def setOwner(
                owner: String
              ): Unit = {

    this.owner =
      owner
  }

  def setInitialized(
                      initialized: Boolean
                    ): Unit = {

    this.initialized =
      initialized
  }

  def currentOwner():
  String = {

    owner
  }

  def isInitialized():
  Boolean = {

    initialized
  }

  def summary():
  String = {

    s"$owner:$initialized"
  }

}