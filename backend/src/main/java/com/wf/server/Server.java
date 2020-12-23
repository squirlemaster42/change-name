package com.wf.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.apache.commons.text.StringEscapeUtils;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Server {

    private final HttpServer server;

    public Server(String host, int port) throws IOException {
        this.server = HttpServer.create(new InetSocketAddress(host, port), 0);
        server.createContext("/registrations", new HttpHandler());
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        server.setExecutor(threadPoolExecutor);
        server.start();
        System.out.println("Started Server");
        //TODO Create Logger
    }

    private static class HttpHandler implements com.sun.net.httpserver.HttpHandler {

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String requestParamValue = null;
            System.out.println(exchange.getRequestURI());
            InputStreamReader isr =  new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(isr);
            String value = br.readLine();
            if("GET".equals(exchange.getRequestMethod())) {
                requestParamValue = handleGetRequest(exchange);
            }else if("POST".equals(exchange.getRequestMethod())){
                System.out.println("Found POST");
                requestParamValue = handlePostRequest(exchange);
            }else if("OPTIONS".equals(exchange.getRequestMethod())){
                System.out.println("Found options");
                requestParamValue = handleOptionsRequest(exchange);
            }
            handleResponse(exchange, requestParamValue);
        }

        private String handleGetRequest(HttpExchange exchange){
            return exchange.getRequestURI().toString().split("\\?")[1].split("=")[1];
        }

        private String handlePostRequest(HttpExchange exchange){
            System.out.println(exchange.getRequestURI());
            try {
                InputStream stream = exchange.getRequestBody();
                StringBuilder sb = new StringBuilder();
                int i;
                while ((i = stream.read()) != -1) {
                    sb.append((char) i);
                }
                System.out.println(sb.toString());
            }catch (IOException e){
                e.printStackTrace();
            }
            return "";
        }

        private String handleOptionsRequest(HttpExchange exchange){
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "http://localhost:3000");
            exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
            exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type,Authorization");
            exchange.getResponseHeaders().add("Allow", "OPTIONS, GET, HEAD, POST");
            exchange.getResponseHeaders().add("Content-Length", "0");
            exchange.getResponseHeaders().add("Allow-Control-Allow-Credentials", "true");
            return "";
        }

        private void handleResponse(HttpExchange exchange, String requestParamValue) throws IOException {
            OutputStream outputStream = exchange.getResponseBody();

            String htmlBuilder = "";
            String htmlResponse = StringEscapeUtils.escapeHtml4(htmlBuilder);

            exchange.sendResponseHeaders(200, htmlResponse.length());
            outputStream.write(htmlResponse.getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
            outputStream.close();
        }
    }
}
