import java.awt.HeadlessException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.Exception
import java.lang.NumberFormatException
import java.net.Socket;
import javax.xml.crypto.Data

/**
 *
 * @author Paulo Henrique Cayres
 */
class Ex1Cliente {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val ex1Cliente = Ex1Cliente()
            ex1Cliente.internMain(args)
        }
    }

    fun internMain(args: Array<String>) {

        try {
            val client = Socket("localhost", 1234)

            val dataOutputStream = DataOutputStream(client.getOutputStream())

            dataOutputStream.writeInt(javax.swing.JOptionPane.showInputDialog("Digite o primeiro número: ").toInt())
            dataOutputStream.writeInt(javax.swing.JOptionPane.showInputDialog("Digite o segundo número: ").toInt())

            val dataInputStream = DataInputStream(client.getInputStream())


            javax.swing.JOptionPane.showMessageDialog(
                null,
                "O somatório é " + dataInputStream.readInt(),
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
