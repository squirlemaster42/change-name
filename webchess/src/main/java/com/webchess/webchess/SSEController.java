package com.webchess.webchess;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Controller
public class SSEController {

//    private final Set<SseEmitter> sseEmitters = new HashSet<>();
//    private int messageCount = 0;
//
//    @RequestMapping("/emitter")
//    public SseEmitter getRealTimeMessageAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        final SseEmitter sseEmitter = new SseEmitter();
//
//        sseEmitter.onCompletion(() -> {
//            synchronized (this.sseEmitters) {
//                this.sseEmitters.remove(sseEmitter);
//            }
//        });
//
//        sseEmitter.onTimeout(sseEmitter::complete);
//
//        sseEmitters.add(sseEmitter);
//
//        return sseEmitter;
//    }
//
//    @Scheduled(fixedDelay = 2 * 1000)
//    public void scheduledMsgEmitter() throws IOException {
//        if(!sseEmitters.isEmpty()) {
//            ++messageCount;
//        }else{
//            System.out.println("No emitters active");
//        }
//
//        System.out.println("Sent Messages: " + messageCount);
//
//        sseEmitters.forEach(sseEmitter -> {
//            if(null != sseEmitter){
//                try {
//                    System.out.println("Timeout: " + sseEmitter.getTimeout());
//                    sseEmitter.send("MessageCounter: " + messageCount);
//                    sseEmitter.complete();
//                }catch (IOException e){
//                    e.printStackTrace();
//                }
//            }
//        });
//    }
}
