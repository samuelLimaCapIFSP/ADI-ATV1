package ex6

import java.io.BufferedOutputStream
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.OutputStream
import java.io.OutputStreamWriter
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

            val input = dataInputStream.readInt()
            val total = input + input*0.1
            val totalComGorjeta = (total * 100).roundToInt() /100.00


            val dataOutputStream = DataOutputStream(socketConexao.getOutputStream())
            dataOutputStream.write(totalComGorjeta.toString().toByteArray(Charset.forName("UTF-8")))

            dataInputStream.close()
            dataOutputStream.close()

            socketConexao.close();
            socketRecepcao.close();


        } catch (e: Exception) {
            e.printStackTrace();
        }
    }
}
