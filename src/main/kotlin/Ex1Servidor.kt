import java.io.DataInputStream
import java.io.DataOutputStream
import java.lang.Exception
import java.net.ServerSocket

class Ex1Servidor {

    companion object {

        fun seila() = "ok"

        @JvmStatic
        fun main(args: Array<String>) {
            Ex1Servidor().internMain(args)
        }
    }

    fun internMain(args: Array<String>) {
        try {

            val socketRecepcao = ServerSocket(1234)

            println("Servidor esperando conexão na porta 1234");

            val socketConexao = socketRecepcao.accept()
            println("Socket Conexão: $socketConexao");

            println("Cliente ${socketConexao.inetAddress.hostAddress} conectado");

            val dataInputStream = DataInputStream(socketConexao.getInputStream())

            val num1 = dataInputStream.readInt()
            val num2 = dataInputStream.readInt()
            val somatorio =  num1 + num2


            val dataOutputStream = DataOutputStream(socketConexao.getOutputStream())
            dataOutputStream.writeInt(somatorio)

            dataInputStream.close()
            dataOutputStream.close()

            socketConexao.close();
            socketRecepcao.close();


        } catch (e: Exception) {
            e.printStackTrace();
        }
    }
}
