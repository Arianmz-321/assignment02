package com.example.webchatserver;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONObject;

/**
 * This is a class that has services
 * In our case, we are using this to generate unique room IDs**/
@WebServlet(name = "chatServlet", value = "/chat-servlet")
public class ChatServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello world";
    }

    //static so this set is unique
    public static Set<String> rooms = new HashSet<>();
    public static List<ChatRoom> chatRooms = new ArrayList<>();


    /**
     * Method generates unique room codes
     * **/
    public String generatingRandomUpperAlphanumericString(int length) {
        String generatedString = RandomStringUtils.randomAlphanumeric(length).toUpperCase();
        // generating unique room code
        while (rooms.contains(generatedString)){
            generatedString = RandomStringUtils.randomAlphanumeric(length).toUpperCase();
        }
        rooms.add(generatedString);
        ChatRoom chatRoom = new ChatRoom(generatedString, "");
        chatRooms.add(chatRoom);
        return generatedString;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/plain");

        // send the random code as the response's content
        PrintWriter out = response.getWriter();
        out.println(generatingRandomUpperAlphanumericString(5));

        //JSONObject data = new JSONObject(jsonData);

        // Hello
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");

    }

    public void destroy() {
    }
}