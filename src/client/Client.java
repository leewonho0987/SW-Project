package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        final String SERVER_IP = "localhost";
        final int SERVER_PORT = 12345;

        try (Socket socket = new Socket(SERVER_IP, SERVER_PORT);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {

            // 클라이언트에서 서버로 로그인 요청 보내기
            writer.println("LOGIN,user123,password123");

            // 서버에서 온 응답 받기
            String response = reader.readLine();
            System.out.println("서버 응답: " + response);

            // 클라이언트에서 서버로 사용자 정보 조회 요청 보내기
            writer.println("GET_USER,user123");

            // 서버에서 온 응답 받기
            response = reader.readLine();
            System.out.println("서버 응답: " + response);

            // 클라이언트에서 서버로 모든 사용자 목록 조회 요청 보내기
            writer.println("GET_ALL_USERS");

            // 서버에서 온 응답 받기
            response = reader.readLine();
            System.out.println("서버 응답: " + response);

            // 클라이언트에서 서버로 사용자 차단 요청 보내기
            writer.println("BLOCK_USER,user123,2023-01-01 00:00:00");

            // 서버에서 온 응답 받기
            response = reader.readLine();
            System.out.println("서버 응답: " + response);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
