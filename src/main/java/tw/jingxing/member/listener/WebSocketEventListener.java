package tw.jingxing.member.listener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class WebSocketEventListener {

    private final AtomicInteger counter = new AtomicInteger(0);

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        counter.incrementAndGet();
        System.out.println("New client connected. Total clients: " + counter.get());
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        counter.decrementAndGet();
        System.out.println("Client disconnected. Total clients: " + counter.get());
    }
    
    public int getConnectionCount() {
        return counter.get();
    }
    
    
}