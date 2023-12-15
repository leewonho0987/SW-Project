package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        final int PORT = 12345;

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("서버가 시작되었습니다. 포트: " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("클라이언트가 연결되었습니다.");

                ClientHandler clientHandler = new ClientHandler(clientSocket);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler implements Runnable {
        private Socket clientSocket;
        private BufferedReader reader;
        private PrintWriter writer;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
            try {
                this.reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                this.writer = new PrintWriter(clientSocket.getOutputStream(), true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
                String request;
                while ((request = reader.readLine()) != null) {
                    System.out.println("클라이언트로부터 받은 요청: " + request);

                    // 요청을 처리하고 응답을 클라이언트에게 보내는 로직
                    String response = handleRequest(request);

                    // 처리 결과를 클라이언트에게 전송
                    writer.println(response);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private String handleRequest(String request) {
            // 실제 요청을 처리하는 로직
            // UserDao의 메서드를 호출하고 응답을 클라이언트에게 전송하는 등의 작업을 수행
            return "서버 응답: " + request;
        }
    }
}
