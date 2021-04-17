import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(12345);

        while (true) {

            try (Socket socket = serverSocket.accept();
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(
                                                                socket.getOutputStream()), true)) {

                String line;
                while ((line = in.readLine())  != null) {
                    if ("end".equals(line)) break;
                    long inputLong = Long.parseLong(line);
                    long out = getFibonacci(inputLong);
                    printWriter.println(out);
                }
            }
        }
    }

    private static long getFibonacci(long n) {

        long first = 0;
        long second = 1;
        long result = 0;

        for (int i = 2; i < n; i++) {
            result = first + second;
            first = second;
            second = result;
        }

        return result;
    }

}
