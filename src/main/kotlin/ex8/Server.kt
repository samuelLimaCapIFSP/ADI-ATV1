package ex8

import java.io.DataInputStream
import java.io.DataOutputStream
import java.net.ServerSocket
import java.nio.charset.Charset
import kotlin.math.roundToInt

class Server {

    companion object {

        fun seila() = "ok"

        @JvmStatic
        fun main(args: Array<String>) {
            Server().internMain(args)
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

            val pesoAtual = dataInputStream.readDouble()
            val pesoDesejado = dataInputStream.readDouble()

            val percentualAPerderPeso = 1 - pesoDesejado/pesoAtual


            val dataOutputStream = DataOutputStream(socketConexao.getOutputStream())
            dataOutputStream.write(percentualAPerderPeso.toString().toByteArray(Charset.forName("UTF-8")))

            dataInputStream.close()
            dataOutputStream.close()

            socketConexao.close();
            socketRecepcao.close();


        } catch (e: Exception) {
            e.printStackTrace();
        }
    }
}
