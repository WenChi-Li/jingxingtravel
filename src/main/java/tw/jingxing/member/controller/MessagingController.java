package tw.jingxing.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tw.jingxing.member.listener.WebSocketEventListener;
import tw.jingxing.member.model.bean.Greeting;
import tw.jingxing.member.model.bean.Message;
import tw.jingxing.member.service.MemberService;

@RestController
public class MessagingController {
	
    @Autowired
    private WebSocketEventListener webSocketEventListener;
    
	@Autowired
	private MemberService mService;

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")	
    public Greeting greeting(Message message) throws Exception {
        Thread.sleep(500);
        return new Greeting(message.getContent());
    }
    
    //查看在線人數
    @GetMapping("/websocketCount.controller")
    public ResponseEntity<Integer> getConnectionCount() {
        int count = webSocketEventListener.getConnectionCount();
        return ResponseEntity.ok(count);
    }
    
    //查看會員佔用客服
    @GetMapping("/countMessaging.controller")
	public ResponseEntity<Integer> countMessaging() throws JSONException {
    	 int count = mService.countMessaging();
        return ResponseEntity.ok(count);
	}
    
	// 更新User客服狀態
	@PostMapping("/updateMessagingStatus.controller")
	public String updateMessagingStatus(@RequestBody String body) throws JSONException {
		JSONObject responseJson = new JSONObject();
		JSONObject requestBodyJson = new JSONObject(body);
		String messaging = requestBodyJson.getString("messaging");
		String mid = requestBodyJson.getString("mid");
		mService.updateMessagingStatus(messaging, mid);
		return responseJson.toString();
	}
    
    
}
