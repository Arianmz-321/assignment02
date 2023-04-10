package com.example.webchatserver;

import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

/**
 * This class represents a web socket server, a new connection is created and it receives a roomID as a parameter
 **/
@ServerEndpoint(value="/ws/{roomID}")
public class ChatServer {

    // contains a static List of ChatRoom used to control the existing rooms and their users
    private static List<ChatRoom> chatRooms = new ArrayList<>();

    // you may add other attributes as you see fit


    @OnOpen
    public void open(@PathParam("roomID") String roomID, Session session) throws IOException, EncodeException {

        // the roomID parameter accessed
        System.out.println("New connection to room: " + roomID);

        // this code will make a new room if (chat) room doesn't exist
        ChatRoom chatRoom = getChatRoom(roomID);

        if (chatRoom == null) {

            chatRoom = new ChatRoom(roomID, session.getId());
            ChatRoom.add(chatRoom);
            
        } else {
            chatRoom.addUser(session.getId(), "", session);
        }

        // prints the Massage below to the user
        session.getBasicRemote().sendText("Hey, Welcome to the room: " + roomID);
    }

    @OnClose
    public void close(Session session) throws IOException, EncodeException {
        String userId = session.getId();

        // this code will be removing user from every chat room
        for (ChatRoom chatRoom : chatRooms) {
            chatRoom.removeUser(userId);
        }
    }

    @OnMessage
    public void handleMessage(String message, Session session) throws IOException, EncodeException {

        // this code will get the ID of sender
        String userId = session.getId();

        // this code will get (chat)room for user
        ChatRoom chatRoom = getChatRoomForUser(userId);
        if (chatRoom == null) {
            session.getBasicRemote().sendText("You are NOT in the room!");
            return;
        }

        // analyze the client's JSON message
        JSONObject jsonText = new JSONObject(message);
        String TypeMsg = jsonText.getString("type");

        
        switch (TypeMsg) {

            case "join":
                String username = jsonText.getString("username");
                chatRoom.addUser(userId, username, session);
                break;

            case "message":

                String chatText = jsonText.getString("message");
                String usernameForSender = chatRoom.getUsers().get(userId);

                if (usernameForSender == null || usernameForSender.isEmpty()) {
                    usernameForSender = "Anonymous";
                }

                String formatted_text = usernameForSender + ": " + chatText;
                List<Session> sessions = chatRoom.getSession();

                for (Session s : sessions) {
                    s.getBasicRemote().sendText(formatted_text);
                }

                break;

            default:
                session.getBasicRemote().sendText("Invalid message type!!!");
                break;
        }
    }

    private ChatRoom getChatRoom(String roomID) {
        for (ChatRoom chatRoom : chatRooms) {
            if (chatRoom.getCode().equals(roomID)) {
                return chatRoom;
            }
        }
        return null;
    }

    private ChatRoom getChatRoomForUser(String userId) {
        for (ChatRoom chatRoom : chatRooms) {
            if (chatRoom.inRoom(userId)) {
                return chatRoom;
            }
        }
        return null;
    }

}