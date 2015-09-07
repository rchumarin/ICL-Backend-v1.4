<%--@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" --%>
<%@page import="ru.icl.test.auth.username" %>
<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Онлайн ЧАТ</title>
        <link href="../css/style_main.css" rel="stylesheet" type="text/css">
    </head>    
    <body>
    <%
            request.setCharacterEncoding("UTF-8");  
            username un = new username();            
            if (!request.getParameter("name").isEmpty()) {                
                session.setAttribute("name", request.getParameter("name"));
            }
            else { 
                session.setAttribute("name", un.getName());
            }             
    %>            
    <div class="container">        
            <div class="logo">
                <a href="main.jsp"><img src="../images/chat_.png" width="86" height="87" alt="" name="logo" /></a>
            </div>
            <div class="descr">
                <h3>Онлайн ЧАТ</h3>
            </div>
            <div class="welcome">
                <h5>Добро пожаловать, <%=session.getAttribute("name")%>!</h5>
                <h5 class="sessia">Ваш id: <%=session.getId()%></h5>
                <h6><a href="../index.jsp">Выход</a></h6>
            </div>
            <div class="search_form">
                <form name="search_form" method="GET" action="<%=request.getContextPath()%>/simple">
                    <input type="text" name="msg" size="135"/>
                    <input class="search_button" type="submit" value="Отправить"/>                                   
                </form>
            </div>                                                
            <div class="big_column">               
                <div>
                    <table>
                        <tr>
                            <th>N</th> 
                            <th>id</th> 
                            <th>Messages</th>
                        </tr>                                           
                        <%-- 
                        <c:forEach var="msg" items="${jmessage}">                                                                       
                        <tr>
                            <td> </td>
                            <td> </td>
                            <td>${msg}</td>
                        </tr>
                        </c:forEach> 
                         --%>
                    </table>
                </div>                  
            </div>                     
    </div>                                                                                                                       
    <div class="footer">© 2015 ICL. Test project</div>
    </body>
</html>

