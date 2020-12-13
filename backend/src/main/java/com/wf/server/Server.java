package com.wf.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.apache.commons.text.StringEscapeUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Server {

    private final HttpServer server;

    public Server(String host, int port) throws IOException {
        this.server = HttpServer.create(new InetSocketAddress(host, port), 0);
        server.createContext("/test", new HttpHandler());
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        server.setExecutor(threadPoolExecutor);
        server.start();
        //TODO Create Logger
    }

    private static class HttpHandler implements com.sun.net.httpserver.HttpHandler {

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String requestParamValue = null;
            if("GET".equals(exchange.getRequestMethod())) {
                requestParamValue = handleGetRequest(exchange);
            }else if("POST".equals(exchange.getRequestMethod())){
                requestParamValue = handlePostRequest(exchange);
            }
            handleResponse(exchange, requestParamValue);
        }

        private String handleGetRequest(HttpExchange exchange){
            return exchange.getRequestURI().toString().split("\\?")[1].split("=")[1];
        }

        private String handlePostRequest(HttpExchange exchange){
            return handleGetRequest(exchange);
        }

        private void handleResponse(HttpExchange exchange, String requestParamValue) throws IOException {
            OutputStream outputStream = exchange.getResponseBody();

            String htmlBuilder = "<html>" +
                    "<body>" +
                    "<h1>" +
                    "Hello " +
                    requestParamValue +
                    "</h1>" +
                    "</body>" +
                    "</html>";
            String htmlResponse = StringEscapeUtils.escapeHtml4(htmlBuilder);

            exchange.sendResponseHeaders(200, htmlResponse.length());
            outputStream.write(htmlResponse.getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
            outputStream.close();
        }
    }
}
