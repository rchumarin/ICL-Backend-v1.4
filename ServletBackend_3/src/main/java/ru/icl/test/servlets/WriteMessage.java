package ru.icl.test.servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import ru.icl.test.db.Database;

//@WebServlet(name = "WriteMessage", urlPatterns = {"/WriteMessage"})
public class WriteMessage extends HttpServlet {
                
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {        
                
        response.setContentType("text/html; charset=UTF-8");        
        try(PrintWriter out = response.getWriter()) {            
            out.println("<html>");
            out.println("<head>");
            out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
            out.println("<title>Онлайн ЧАТ</title>");
            out.println("<link href=\"css/style_main.css\" rel=\"stylesheet\" type=\"text/css\">");
            out.println("</head>");        
            out.println("<body>");        
            out.println("<div class=\"container\">");
            out.println("<div class=\"logo\">");
            out.println("<a href=\"main.jsp\"><img src=\"images/chat_.png\" width=\"86\" height=\"87\" alt=\"\" name=\"logo\" /></a>");
            out.println("</div>");
            out.println("<div class=\"descr\">");
            out.println("<h3>Онлайн ЧАТ</h3>");
            out.println("</div>");
            out.println("<div class=\"welcome\">");
                        
            HttpSession session = request.getSession(true);            
            String id = session.getId();
            String name = (String) session.getAttribute("name");
                                                
            out.println("<h5>Добро пожаловать, " + name + "!</h5>");             
            out.println("<h5 class=\"sessia\">Ваш id: "  + id + "</h5>");
            out.println("<h6>");           
            out.println("<a href=\"index.jsp\">Выход</a>");
            out.println("</h6>");
            out.println("</div>");            
            out.println("<div class=\"search_form\">");
            out.println("<form name=\"search_form\" method=\"GET\" action=\"");
            out.println(request.getContextPath());
            out.println("/simple\">");            
            out.println("<input type=\"text\" name=\"msg\" size=\"135\"/>");
            out.println("<input class=\"search_button\" type=\"submit\" value=\"Отправить\"/>");
            out.println("</form>");
            out.println("</div>");            
            out.println("<div class=\"big_column\">");
            out.println("<div>");
            out.println("<table>");
            out.println("<tr>");
            out.println("<th>N</th>");
            out.println("<th>id</th>");
            out.println("<th>Messages</th>");
            out.println("</tr>");
            
            boolean active = (boolean) request.getServletContext().getAttribute("active");            
            
            if(active) { //Парсинг JSON   
                JSONObject jsonOb = (JSONObject) request.getServletContext().getAttribute("sessionMap");                 
                Iterator interatorKey = jsonOb.keySet().iterator();
                //jsonOb.keySet() возвращает набор, состоящий из id
                //проверка
                //System.out.println("1) " + jsonOb.keySet());
                
                Iterator<JSONObject> iteratorValue = jsonOb.values().iterator();            
                //jsonOb.values() возвращает коллекцию, состоящую из msg и name
                //проверка
                //System.out.println("2) " + jsonOb.values());

                while (iteratorValue.hasNext() || interatorKey.hasNext()) {
                    String jsonIdValue = (String) interatorKey.next();                                                                
                    //проверка
                    //System.out.println("3) " + jsonIdValue); 

                    JSONObject jsonMsgAndName = iteratorValue.next();                                 
                    //проверка
                    //System.out.println("4) " + jsonMsgAndName);                

                    JSONArray jsonMsgArrayValue = (JSONArray) jsonMsgAndName.get("msg");                                 
                    //проверка
                    //System.out.println("5) " + jsonMsgArrayValue);                
                    int count=1;
                    for(Object jsonMsgValue : jsonMsgArrayValue) {                                        
                        out.println("<tr>");                      
                            out.println("<td>" + (count++) + "</td>");                    
                        out.println("<td style=\"color:blue\">" + jsonIdValue + "</td>");                    
                        out.println("<td>" + jsonMsgValue + "</td>");                                        
                        //проверка
                        //System.out.println("6) " + jsonMsgValue);                                        
                        out.println("</tr>");
                    }            
                } 
            } else { //Десериализация GSON
                String jstring = (String) request.getServletContext().getAttribute("jstring"); 
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                HashMap<String, List> sessionMap = gson.fromJson(jstring, HashMap.class);                                          
                int count=1;                        
                /*
                for (String str1 : listMsg) {                                                         
                    out.println("<tr>");
                    out.println("<td>" + (count++) + "</td>");
                    out.println("<td>" + id + "</td>");
                    out.println("<td>" + str1 + "</td>");
                    out.println("</tr>");
                */                                                             
                for (Map.Entry<String, List> entry : sessionMap.entrySet()) {
                    String sessionId = entry.getKey();
                    List listMessages = entry.getValue();
                    for (Object str : listMessages) {
                        out.println("<tr>");                 
                        out.println("<td>" + (count++) + "</td>");
                        out.println("<td style=\"color:green\">" + sessionId + "</td>");                       
                        out.println("<td>" + str + "</td>");
                        out.println("</tr>");
                    }
                }
            }    
            out.println("</table>");
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");        
            out.println("<div class=\"footer\">© 2015 ICL. Test project</div>");
            out.println("</body>");
            out.println("</html>");   
        }                               
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
