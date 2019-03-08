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


    <style type="text/css">
     body { background-image: linear-gradient(to right, rgba(255,0,0,0), rgba(100,0,0,1));}
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

    </style>
<%@include file="/WEB-INF/jsp/employee/header.jsp"%>
 <%@include file="/WEB-INF/jsp/employee/footer.jsp"%>

        <div class="container-fluid">
            <div class="row">
               <div class="col s10 m6 l4   offset-m3 offset-l4  offset-s1 z-depth-4 card-panel">
                    <div class="input-field col s12 center">
                        <h5 class="center login-form-text"> Reset Password</h5>
                     </div>
                  <form class="login-form" method="post" action="/changePassword">
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

                    <div class="row">



  
                      <div class="input-field col s12">
                        <input type="password" name="oldpassword"/>
                        <label for="oldPassword">Old Password</label>
                      </div>

                      <div class="input-field col s12">
                        <input type="password" name="newpassword"/>
                        <label for="newPassword">New Password</label>
                      </div>

                      <div class="input-field col s12">
                         <input type="password" name="confrmpassword"/>
                         <label for="confrmpassword">Confirm Password</label>
                      </div>



                    <div class="row">
                      <div class="input-field col s12">
                       <button href="#" style="background: linear-gradient(to right, #018647 0%, #008570 50%, #008685 100%);" class="btn waves-effect waves-light col s12">Change</button>
                      </div>
                    </div>


                  </form>
                </div>
              </div>


            </div>
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