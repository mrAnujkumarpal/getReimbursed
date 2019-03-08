<%--
    Document   : homePage
    Author     : Anuj
--%>

<html lang="en">



    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no">
        <title>Registration</title>

        <link src="http://maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
        <link href="http://demo.geekslabs.com/materialize/v3.0/default/css/materialize.min.css" type="text/css" rel="stylesheet" media="screen,projection">
        <link href="http://demo.geekslabs.com/materialize/v3.0/default/css/style.min.css" type="text/css" rel="stylesheet" media="screen,projection">
     </head>

    <body>


        <nav>
            <div class="nav-wrapper pink darken-1">
                <a href="#" class="brand-logo center">Logo</a>
                <ul id="nav-mobile" class="left hide-on-med-and-down">
                    <!--                    <li><a href="sass.html">Sass</a></li>
                                        <li><a href="badges.html">Components</a></li>
                                        <li><a href="collapsible.html">JavaScript</a></li>-->


                    <ul id="slide-out" class="side-nav">
                        <li><a href="#!">First Sidebar Link</a></li>
                        <li><a href="#!">Second Sidebar Link</a></li>
                    </ul>
                    <a href="#" data-activates="slide-out" class="button-collapse show-on-large"><i class="mdi-navigation-menu"></i></a>
                </ul>
            </div>
        </nav>





        <div class="container-fluid">

            <div class="row" style="margin-top: 13%;">
                <div class="col s12 m6 l4 offset-l4 offset-m3 ">

                    <div id="login-page" class="row z-depth-4 card-panel">
                        <div class="row">
                            <div class="input-field col s12 center">
                                <img src="https://lh3.googleusercontent.com/-bjmRoqqiHEk/UVy4mkLcyFI/AAAAAAAAAD4/6waBBE6WzfIFwcbjKr0rtPmRH2qzf0FBQCL0B/w336-h337-no/DSCN5598.JPG" alt="" class="circle responsive-img valign profile-image-login">



                                <p class="center login-form-text"><b> Change Password </b></p>
                            </div>
                        </div>

                        <form class="col s12">
                            <div class="row">
                                <div class="input-field col s12  login-text">
                                    <input id="email" type="email" class="validate">
                                    <label for="email">Email</label>
                                </div>
                            </div>

                            <div class="row">
                                <div class="input-field col s12  login-text">
                                    <input id="password" type="password" class="validate">
                                    <label for="password">Old Password</label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="input-field col s12  login-text">
                                    <input type="password" id="newPassword" />
                                    <label for="newPassword">New Password</label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="input-field col s12  login-text">
                                    <input type="password" id="AgainPassword" />
                                    <label for="AgainPassword">Again Password</label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="input-field col s12  login-text">
                                    <input type="checkbox" id="remember-me" />
                                    <label for="remember-me">Remember me</label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="input-field col s12">
                                    <button href="#" class="btn waves-effect waves-light col s12">Change Password</button>
                                </div>
                            </div>

                        </form>
                        <div class="row">
                            <div class="input-field col s6 m6 l6">
                                <p class="margin medium-small"><a href="#">Login </a></p>
                            </div>
                            <div class="input-field col s6 m6 l6">
                                <p class="margin right-align medium-small"><a href="#">Register </a></p>
                            </div>
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

        </div>
        <!-- jQuery Library -->
        <script type="text/javascript " src="http://demo.geekslabs.com/materialize/v3.0/default/js/plugins/jquery-1.11.2.min.js "></script>
        <!--materialize js-->
        <script type="text/javascript " src="http://demo.geekslabs.com/materialize/v3.0/default/js/materialize.min.js "></script>

    </body>

</html>