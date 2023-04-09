package com.example.webchatserver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class represents the data you may need to store about a Chat room
 * You may add more method or attributes as needed
 * **/
public class ChatRoom {

    public class User {
        private String id;
        private String name;
        private Session session;
    
        public User(String id, String name, Session session) {
            this.id = id;
            this.name = name;
            this.session = session;
        }
    
        public String getId() {
            return id;
        }
    
        public void setId(String id) {
            this.id = id;
        }
    
        public String getName() {
            return name;
        }
    
        public void setName(String name) {
            this.name = name;
        }
    
        public Session getSession() {
            return session;
        }
    
        public void setSession(Session session) {
            this.session = session;
        }
    }

    private String  code;
    private static List<ChatRoom> rooms = new ArrayList<>();
   
    //each user has an unique ID associate to their ws session and their username
    private Map<String, String> users = new HashMap<String, String>() ;
    private List<Session> sessions  = new ArrayList<>() ;

    // when created the chat room has at least one user
    public ChatRoom(String code, String user){
        this.code = code;
        // when created the user has not entered their username yet
        this.users.put(user, "");
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public Map<String, String> getUsers() {
        return users;
    }

    public static void add(ChatRoom chatRoom) {
        rooms.add(chatRoom);    
    }

    /**
     * This method will add the new userID to the room if not exists, or it will add a new userID,name pair
     * **/
    public void setUserName(String userID, String name) {
        // update the name
        if(users.containsKey(userID)){
            users.remove(userID);
            users.put(userID, name);
        }else{ // add new user
            users.put(userID, name);
        }
    }

    /**
     * This method will remove a user from this room
     * **/

    public void addUser(String id, String name, Session session) {
        User newUser = new User(id, name, session);
        this.sessions.add(session);
    }
     
    public void removeUser(String userID){
        if(users.containsKey(userID)){
            users.remove(userID);
        }

    }

    public boolean inRoom(String userID){
        return users.containsKey(userID);
    }
}
