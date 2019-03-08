<%--
    Document   : homePage
    Author     : Anuj
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org">
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">
    </head>


    <body style="background-color:#e6e7e9;">
    <style type="text/css">
                        .error {
                           background-color: #f2dede;
                           color: #b71c1c;
                           padding: 10px 0;
                           text-align: center;
                           border-radius: 7px;
                           border: 1px solid #b71c1c;
                        }
                        .sucess{
                           background-color:#dff0d8;
                           color: #1b5e20 ;
                           padding: 10px 0;
                           text-align: center;
                           border-radius: 7px;
                           border :1px dotted #004d40;
                        }
                        body {
                           background-image: url("assets/img/backgroung.jpg");
                           background-color:#D30047;
                           background-repeat: no-repeat;
                        }
    </style>


        <div class="container-fluid">
            <div class="row">
               <div class="col s10 m6 l4   offset-m3 offset-l4  offset-s1 z-depth-4 card-panel">
                    <div class="input-field col s12 center">
                        <h5 class="center login-form-text"> Login ${loginEmployee}</h5>
                     </div>
                  <form class="login-form" method="POST" th:action="@{/login}"  >
                            <div class="input-field col s10 offset-s1">
                                <c:if test="${success ne null}">
                                    <c:if test="${success eq  'true'}">
                                        <div class="sucess text-center ">${message} </div>
                                    </c:if>
                                    <c:if test="${success eq  'false'}">
                                        <div class="error alert-info text-center ">${message} </div>
                                    </c:if>
                                </c:if>
                            </div>

                    <div class="row margin">
                      <div class="input-field col s12">
                          <input name="email" type="email" class="validate" >
                           <label for="email">Email</label>
                      </div>
                    </div>

                    <div class="row margin">
                      <div class="input-field col s12">
                        <input type="password" name="password"/>
                        <label for="Password">Password</label>
                      </div>
                    </div>

                    <div class="row">
                      <div class="input-field col s12">

                       <button class="btn btn-sm btn-primary btn-block" name="Submit" value="Login" type="Submit">Login</button>
                      </div>
                    </div>
                    <div class="row">
                      <div class="input-field col s6 m6 l6">
                        <p class="margin medium-small"><a href="/empRegistration">Register Now</a></p>
                      </div>
                      <div class="input-field col s6 m6 l6">
                          <p class="margin right-align medium-small"><a href="page-forgot-password.html">Forgot password</a></p>
                      </div>
                    </div>
                  </form>
                </div>
              </div>


            </div>
        </div>




    <!-- Floating Action Button -->
    <div class="fixed-action-btn " style="bottom: 50px; right: 19px; ">
        <a class="btn-floating btn-large z-depth-5">
            <i class="fa fa-user" aria-hidden="true"></i>
        </a>
        <ul>
            <li><a href="#" class="btn-floating red z-depth-5"><b>A</b></a></li>
            <li><a href="#" class="btn-floating yellow darken-1 z-depth-5"><b>N</b></a></li>
            <li><a href="#" class="btn-floating green z-depth-5"><b>U</b></a></li>
            <li><a href="#" class="btn-floating blue z-depth-5"><b>J</b></a></li>
        </ul>
    </div>

    <!--Import jQuery before materialize.js-->

    <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/materialize/0.98.2/js/materialize.min.js"></script>
    <script src="assets/js/main.js" type="text/javascript"></script>
      <script>
            $(document).ready(function () {
                $('select').formSelect();
            });
             $( document ).ready(function()) {
                         $(".dropdown-button").dropdown();
                      });
        </script>
</body>
</html>