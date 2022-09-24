package ex7

import java.awt.HeadlessException
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.IOException
import java.net.Socket
import java.nio.charset.Charset

/**
 *
 * @author Paulo Henrique Cayres
 */
class Client {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val ex1Cliente = Client()
            ex1Cliente.internMain(args)
        }
    }

    fun internMain(args: Array<String>) {

        try {
            val client = Socket("localhost", 1234)

            val dataOutputStream = DataOutputStream(client.getOutputStream())

            dataOutputStream.writeDouble(javax.swing.JOptionPane.showInputDialog("Digite o valor do produto: ").toDouble())

            val dataInputStream = DataInputStream(client.getInputStream())

            val valorComGorjeta = dataInputStream.readBytes().toString(Charset.forName("UTF-8"))
            javax.swing.JOptionPane.showMessageDialog(
                null,
                "O valor de venda é  $valorComGorjeta",
                "Resultado",
                javax.swing.JOptionPane.INFORMATION_MESSAGE
            );

            client.close()
            dataInputStream.close()
            dataOutputStream.close()

        } catch (e: Exception){
            when (e) {
                is IOException -> println(e.message)
                is HeadlessException -> println(e.message)
                is NumberFormatException -> println(e.message)
                else -> println(e.message)
            }
        }

    }

}
