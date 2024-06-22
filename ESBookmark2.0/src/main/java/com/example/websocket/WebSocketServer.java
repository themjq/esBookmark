package com.example.websocket;

import com.alibaba.fastjson.JSON;
import com.example.dto.WsDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * WebSocket服务
 */
@Component
@Slf4j
@ServerEndpoint("/ws/{id}")
public class WebSocketServer {

    /** 当前在线客户端数量（线程安全的） */
    private static AtomicInteger onlineClientNumber = new AtomicInteger(0);

    /** 当前在线客户端集合（线程安全的）：以键值对方式存储，key是连接的编号，value是连接的对象 */
    private static Map<String ,Session> onlineClientMap = new ConcurrentHashMap<>();

    /**
     * 客户端与服务端连接成功
     */
    @OnOpen
    public void onOpen(Session session,@PathParam("id") String id){
        onlineClientNumber.incrementAndGet();//在线数+1
        onlineClientMap.put(session.getId(),session);//添加当前连接的session
        log.info("时间[{}]：与用户[{}]的连接成功，当前连接编号[{}]，当前连接总数[{}]",
                LocalDateTime.now(),
                id,
                session.getId(),
                onlineClientNumber);
    }

    /**
     * 客户端与服务端连接关闭
     */
    @OnClose
    public void onClose(Session session,@PathParam("id") String id){
        /*
            do something for onClose
            与当前客户端连接关闭时
         */
        onlineClientNumber.decrementAndGet();//在线数-1
        onlineClientMap.remove(session.getId());//移除当前连接的session
        log.info("时间[{}]：与用户[{}]的连接关闭，当前连接编号[{}]，当前连接总数[{}]",
                LocalDateTime.now(),
                id,
                session.getId(),
                onlineClientNumber);
    }

    /**
     * 客户端与服务端连接异常
     */
    @OnError
    public void onError(Throwable error,Session session,@PathParam("id") String id) {
        error.printStackTrace();
    }

    /**
     * 客户端向服务端发送消息
     */
    @OnMessage
    public void onMsg(Session session,String message,@PathParam("id") String id){
        log.info("时间[{}]：来自连接编号为[{}]的消息：[{}]",
                LocalDateTime.now(),
                session.getId(),
                message);
        sendAllMessage(JSON.toJSONString(new WsDto(JSON.parseObject(message).getString("message"),LocalDateTime.now())));
    }

    //向所有客户端发送消息（广播）
    private void sendAllMessage(String message){
        Set<String> sessionIdSet = onlineClientMap.keySet(); //获得Map的Key的集合
        for (String sessionId : sessionIdSet) { //迭代Key集合
            Session session = onlineClientMap.get(sessionId); //根据Key得到value
            session.getAsyncRemote().sendText(message); //发送消息给客户端
        }
    }

    //只向特定客户端发送消息
    private void sendMessage(String message){

    }

}
