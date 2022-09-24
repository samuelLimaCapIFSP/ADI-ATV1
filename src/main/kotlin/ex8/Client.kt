package ex8

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

            dataOutputStream.writeDouble(javax.swing.JOptionPane.showInputDialog("Peso atual: ").toDouble())

            dataOutputStream.writeDouble(javax.swing.JOptionPane.showInputDialog("Peso desejado: ").toDouble())

            val dataInputStream = DataInputStream(client.getInputStream())

            val percentualParaEliminar = dataInputStream.readBytes().toString(Charset.forName("UTF-8")).toDouble()
            val percentualArredondado = Math.round(percentualParaEliminar*100)/100.00
            val resultadoFinalEmPorcentagem = "${(percentualArredondado*100)}%"
            javax.swing.JOptionPane.showMessageDialog(
                null,
                "O percentual de peso para perder é:  $resultadoFinalEmPorcentagem",
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
