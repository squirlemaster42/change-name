package com.webchess.webchess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class WebchessApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebchessApplication.class, args);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/emitter")
    public SseEmitter eventEmitter(@RequestParam String userId) {
        System.out.println("Here");
        SseEmitter emitter = new SseEmitter(12000L);
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try{
                for(int i = 0; i < 4; i++) {
                    emitter.send("message" + i);
                }
            }catch (Exception e){
                emitter.completeWithError(e);
            }finally {
                emitter.complete();
            }
        });
        executor.shutdown();
        return emitter;
    }

}
