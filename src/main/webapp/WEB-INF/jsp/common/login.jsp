<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org">
    <head>
        <title>login</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
            <script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
            <script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">
                </head>
                <style>
                    .error {
                        background-color: #f2dede;
                        color: #b71c1c;
                        text-align: center;
                    }
                    body {
                        background-color: #f6f8fa;
                    }

                </style>


                <body>

                    <div class="container-fluid">
                        <div class="row">
                            <div class="col s12 m8 l6  offset-m2 offset-l3">

                                <div class="card-panel">
                                    <div class="row">
                                        <div class="s12" style="border-bottom:1px dotted #e6e6e6;">
                                            <span  class="left-align" style="font-size:18px;">Sign in</span>
                                            <span   class="right" ><small><a href="#">Forgot Password ?</a></small></span>
                                        </div>

                                        <div class="input-field col s10 offset-s1">
                                            <c:if test="${errorMessge ne null}">
                                                <c:if test="${success eq  'true'}">
                                                    <div class="error alert-info text-center ">${errorMessge} </div>
                                                </c:if>
                                            </c:if>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="s12">
                                            <form th:action="@{/login}"  class="form-signin" method="POST">
                                                <div class="s12">
                                                    <input type="text" id="email" name="email"   placeholder="Email" class="form-control"/>
                                                </div>

                                                <div class="s12">
                                                    <input type="password"  placeholder="Password"id="password" name="password" class="form-control"/>
                                                </div>
                                                <br/>
                                                <div class="s12">
                                                    <button style="background: linear-gradient(to right, #018647 0%, #008570 50%, #008685 100%);"  class="btn btn-sm btn-primary btn-block" name="Submit" value="Login" type="Submit">Login</button>
                                                </div>

                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </body>
                </html>