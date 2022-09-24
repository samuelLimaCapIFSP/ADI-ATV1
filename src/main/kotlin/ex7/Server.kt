package ex7

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

            val inputValorProduto = dataInputStream.readDouble()
            val porcentagemVenda = 0.25
            val valorTotal = inputValorProduto + (inputValorProduto*porcentagemVenda)


            val dataOutputStream = DataOutputStream(socketConexao.getOutputStream())
            dataOutputStream.write(valorTotal.toString().toByteArray(Charset.forName("UTF-8")))

            dataInputStream.close()
            dataOutputStream.close()

            socketConexao.close();
            socketRecepcao.close();


        } catch (e: Exception) {
            e.printStackTrace();
        }
    }
}
