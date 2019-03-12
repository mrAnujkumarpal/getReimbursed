<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org">
<head>
    <title>login</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">
    <style>
        .top-buffer { margin-top:50px; }

        body {
            background-color: #f6f8fa;
        }

    </style>
</head>

<body>

 <div class="container-fluid">

        <div class="row top-buffer"></div>
    <div class="row card-panel">


     <h5 class="center login-form-text"> Login ${loginEmployee}</h5>
    <form th:action="@{/login}"  class="form-signin" method="POST">

        <div class="col-xs-6 col-lg-offset-3">
            <h3 class="form-signin-heading"  text="Sign-in"></h3>

            <input type="text" id="email" name="email"   placeholder="Email"
                   class="form-control" /> <br/>
            <input type="password"  placeholder="Password"
                   id="password" name="password" class="form-control" /> <br />

            <button style="background: linear-gradient(to right, #018647 0%, #008570 50%, #008685 100%);"  class="btn btn-sm btn-primary btn-block" name="Submit" value="Login" type="Submit">Login</button>
        </div>
    </form>


</div>
</div>
</body>
</html>