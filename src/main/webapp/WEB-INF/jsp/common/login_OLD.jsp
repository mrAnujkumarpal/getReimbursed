<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org">
    <head>
        <title>login</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
            <script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
            <script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">
                <style type="text/css">
                    .top-buffer { margin-top:50px; }



                        body {
                           background-image: url("/assets/img/500.png");
                        }


                </style>
                </head>

                <body>

                    <div class="container-fluid">
                        <div class="row top-buffer"></div>
                        <div class="row">
                            <div class="col s12 m8 l6  offset-m2 offset-l3">
                                <div class="card-panel z-depth-5">


                                    <h5 class="center login-form-text"> Login ${loginEmployee}</h5>
                                    <form th:action="@{/login}"  class="form-signin" method="POST">
                                        <h3 class="form-signin-heading"  text="Sign-in"></h3>

                                        <input type="text" id="email" name="email"   placeholder="Email" class="form-control"/> <br/>
                                        <input type="password"  placeholder="Password"id="password" name="password" class="form-control"/> <br/>

                                        <button style="background: linear-gradient(to right, #018647 0%, #008570 50%, #008685 100%);"  class="btn btn-sm btn-primary btn-block" name="Submit" value="Login" type="Submit">Login</button>

                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </body>
                </html>