package kr.sm.itaewon.routepang.controller.android;

import kr.sm.itaewon.routepang.model.redis.ChatMessage;
import kr.sm.itaewon.routepang.service.ChatService;
import kr.sm.itaewon.routepang.service.CustomerService;
import kr.sm.itaewon.routepang.service.fcm.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private CustomerService customerService;

    @PostMapping("/send")
    public ResponseEntity<Void> sendMessage(@RequestBody ChatMessage chatMessage) throws ExecutionException, InterruptedException {
        ChatMessage chatMessageModel = chatService.build(chatMessage);

        //TODO push
        notificationService.pushMessage(chatMessage);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/read")
    public ResponseEntity<List<ChatMessage>> readMessage(@RequestHeader long receiverId, @RequestHeader long senderId){

        //TODO key mapping
        String key = receiverId+"";
        List<ChatMessage> chatMessageList = chatService.findAllByKey(key);
        return new ResponseEntity<>(chatMessageList, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ChatMessage>> readMessageAll(){
        List<ChatMessage> chatMessageList = chatService.findAll();

        return new ResponseEntity<>(chatMessageList, HttpStatus.OK);
    }
}
