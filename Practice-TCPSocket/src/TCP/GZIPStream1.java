package TCP;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GZIPStream1 {
    public static void main(String[] args) {
        String host = "36.50.135.242";
        int port = 2210;

        try (Socket socket = new Socket(host, port)) {
            socket.setSoTimeout(5000);

            // 1. Dùng syncFlush = true để đẩy dữ liệu nén đi ngay mà không cần finish()
            GZIPOutputStream gout = new GZIPOutputStream(socket.getOutputStream(), true);

            // a. Gửi mã sinh viên và mã câu hỏi kết thúc bằng \n
            String req = "B23DCCN275;Y87ZDpRi\n";
            gout.write(req.getBytes(StandardCharsets.UTF_8));
            gout.flush();

            // b. Đọc chuỗi nhận được từ server
            GZIPInputStream gin = new GZIPInputStream(socket.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(gin, StandardCharsets.UTF_8));
            String resp = reader.readLine();

            if (resp != null) {
                // Xóa bỏ khoảng trắng thừa hoặc \r nếu có từ server
                resp = resp.trim();

                // c. Đảo ngược chuỗi
                String reversed = new StringBuilder(resp).reverse().toString();

                // Mã hóa Base64 của chuỗi đảo ngược
                String b64 = Base64.getEncoder().encodeToString(reversed.getBytes(StandardCharsets.UTF_8));

                // Định dạng kết quả kèm \n ở cuối dòng
                String result = reversed + "|" + b64 + "\n";

                // Gửi tiếp trên luồng gout hiện tại (KHÔNG new lại GZIPOutputStream)
                gout.write(result.getBytes(StandardCharsets.UTF_8));
                gout.flush();
                gout.finish(); // Kết thúc toàn bộ phiên gửi
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}