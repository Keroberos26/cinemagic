<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Dashboard | ${theater.name}</title>
        <%@include file="/general/head.jsp" %>
    </head>
    <body>
        <div id="page" class="site">
            <%@include file="/general/sidebar.jsp" %>

            <div class="main-content">
                <%@include file="/general/theater-header.jsp" %>           
            </div>
        </div>


        <%@include file="/general/script.jsp" %>
    </body>
</html>
