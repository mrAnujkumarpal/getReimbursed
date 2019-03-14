<%--
    Document   : homePage
    Author     : Anuj
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">
    </head>


    <body>
    <style type="text/css">
                        body {
                           background-color:#f5f5f5 ;
                        }
                        #panelBck{
                         box-shadow: 0 6px 20px 0 rgba(244, 143, 177, 0.5);
                        }
    </style>

         <%@include file="/WEB-INF/jsp/employee/header.jsp"%>
         <%@include file="/WEB-INF/jsp/employee/footer.jsp"%>

            <div class="container-fluid">
             <div class="row">
                                                    <div class="col s12 m8 l6  offset-m2  offset-l3  z-depth-5 card-panel" id="panelBck">
                                                        <div class="center-align">
                                                            <img class="responsive-img center-align "  src="assets/img/500.png">
                                                        </div>
                                                        <h6>OOps, Something went wrong. You are in wrong place.</h5>
                                                    </div>
                            </div>

            </div>





    <!--Import jQuery before materialize.js-->

    <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/materialize/0.98.2/js/materialize.min.js"></script>
    <script src="assets/js/main.js" type="text/javascript"></script>

</body>
</html>