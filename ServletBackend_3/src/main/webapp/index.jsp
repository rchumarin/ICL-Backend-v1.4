<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Онлайн Чат::Вход</title>
        <link href="css/style_index.css" rel="stylesheet" type="text/css">
    </head>
    <body>       
        <div class="main">
            <div class="content">
                <p class="title"><span class="text"><img src="images/chat_.png" width="76" height="77" hspace="10" vspace="10" align="middle"></span></p>
                <p class="title">Онлайн ЧАТ</p>
                <p class="text">Доступны функции отправки и просмотра сообщений.</p>
                <p class="text">Проект находится в разработке, поэтому дизайн и функционал будет дорабатываться.</p>
                <p class="text">По всем вопросам обращайтесь по адресу <a href="mailto:rchumarin@gmail.com">rchumarin@gmail.com</a></p>
                <p>&nbsp;</p>
            </div>
            <div class="login_div">
                <p class="title">вход в чат:</p>
                <form class="login_form" name="username" action="<%=request.getContextPath()%>/pages/main.jsp" method="POST">
                    Имя <input type="text" name="name" value="" size="20" /> <!--required="true"--!>                                                         
                    <input type="submit" value="Войти" />
                </form>
            </div>
            <div class="footer">
                Разработчик: Рафаэль Чумарин, 2015 г
            </div>            
        </div>        
    </body>
</html>
