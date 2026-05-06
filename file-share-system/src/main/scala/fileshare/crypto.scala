package fileshare

object Crypto {

  private val key: Byte = 0x5A.toByte

  def encrypt(data: Array[Byte]): Array[Byte] = {
    data.map(b => (b ^ key).toByte)
  }

  def decrypt(data: Array[Byte]): Array[Byte] = {
    data.map(b => (b ^ key).toByte)
  }
}