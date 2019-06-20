package xin.liujiajun.socket.bio;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

/**
 * @author LiuJiaJun
 * @date 2019/6/12 18:34
 */
public class HttpServer {

    private static final Logger logger = Logger.getLogger("httpServer");

    private final byte[] content;
    private final byte[] header;
    private final int port;
    private final String encoding;

    public HttpServer(String data, String encoding, String mimeType, int port) throws UnsupportedEncodingException {
        this(data.getBytes(encoding), encoding, mimeType, port);
    }

    public HttpServer(byte[] data, String encoding, String mimeType, int port) {
        this.content = data;
        this.port = port;
        this.encoding = encoding;
        String header = "HTTP/1.1 200 OK \r\n"
                + "Server: oneFile 2.0\r\n"
                + "Content-Length: " + this.content.length + "\r\n"
                + "Content-Type: " + mimeType + ";charset=" + encoding + "\r\n\r\n";
        this.header = header.getBytes(Charset.forName("US-ASCII"));
    }

    public void start() {
        ExecutorService pool = Executors.newFixedThreadPool(100);

        try (ServerSocket serverSocket = new ServerSocket(this.port);) {
            while (true) {
                Socket socket = serverSocket.accept();
                logger.info("接受到" + socket.getLocalAddress().getHostAddress()+"一个请求");
                pool.submit(new HttpHandler(socket));
            }
        } catch (IOException e) {

        }
    }

    class HttpHandler implements Callable<Void> {
        private Socket socket;

        public HttpHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public Void call() throws Exception {
            try {
                BufferedOutputStream outputStream = new BufferedOutputStream(socket.getOutputStream());
                BufferedInputStream inputStream = new BufferedInputStream(socket.getInputStream());
                StringBuilder request = new StringBuilder(80);
                while (true) {
                    int c = inputStream.read();
                    if (c == '\n' || c == '\n' || c == -1) {
                        break;
                    }
                    request.append((char) c);
                }
                if (request.toString().contains("HTTP/")) {
                    outputStream.write(header);
                }
                outputStream.write(content);
                outputStream.flush();
            } finally {
                socket.close();
            }

            return null;
        }
    }

    public static void main(String[] args) throws IOException {
        int port = 8080;
        String encoding = "UTF-8";
        String file = "./data/hello.html";
        Path path = Paths.get(file);
        byte[] data = Files.readAllBytes(path);

        String contentType = URLConnection.getFileNameMap().getContentTypeFor(file);
        HttpServer httpServer = new HttpServer(data, encoding, contentType, port);
        httpServer.start();
    }

}
