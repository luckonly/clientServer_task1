import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {

        try {

            Socket socket = new Socket("127.0.0.1", 12345);

            try (PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

                final ByteBuffer inputBuffer = ByteBuffer.allocate(2 << 10);
                Scanner scanner = new Scanner(System.in);

                while (true) {
                    System.out.printf("Введите число\n");
                    String inputLine = scanner.nextLine();
                    out.println(inputLine);
                    if ("end".equals(inputLine)) break;
                    System.out.println("Число Фибоначчи: " + in.readLine());
                    inputBuffer.clear();
                }

            } catch (NumberFormatException exc) {
                System.out.println("Введено некорректное число. Попробуйте повторить попытку...");
            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

