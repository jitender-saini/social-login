<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Welcome to Grails</title>

    <asset:link rel="icon" href="favicon.ico" type="image/x-ico"/>
</head>

<body>
<div class="container well">
    <div class="col-md-4 col-md-offset-4">

        <div class="panel panel-default">
            <div class="panel-heading custom-heading">
                <div class="row-fluid user-row">
                    Login
                </div>
            </div>

            <div class="panel-body">
                <form action="${postUrl ?: '/login/authenticate'}" method="POST" id="loginForm" class="form-group"
                      autocomplete="off">

                    %{--<g:form id="loginForm" controller="login" action="loginHandler" method="POST" class="form-group">--}%
                    <fieldset>
                        <label class="panel-login">
                            <div class="login_result"></div>
                        </label>
                        <input class="form-control" placeholder="User Name/Email Id" id="userName" type="text"
                               name="${usernameParameter ?: 'username'}" required="required"><br>
                        <input class="form-control" placeholder="Password" id="password" type="password"
                               name="${passwordParameter ?: 'password'}" required="required">
                        <input type="checkbox" class="chk" name="${rememberMeParameter ?: 'remember-me'}"
                               id="remember_me" <g:if test='${hasCookie}'>checked="checked"</g:if>/>
                        <br>
                        <button type="submit" class="btn btn--right btn-lg btn-success btn-block"
                                id="login">Sign in</button>
                    </fieldset>
                    %{--</g:form><br/>--}%
                </form>

                <div class="row">
                    <div class="col-xs-4">
                        <div class="g-signin2" data-onsuccess="onSignIn"></div>
                    </div>

                    <div class="col-xs-4">
                        <fb:login-button
                                scope="public_profile,email"
                                onlogin="checkLoginState();">
                        </fb:login-button>
                    </div>

                    <div class="col-xs-4">

                    </div>
                </div>
                %{--<p>Forget Password <a href="javascript:void(0)" data-toggle="modal"--}%
                %{--data-target="#forgotPassword">Click Here</a></p>--}%
            </div>
        </div>
    </div>
</div>
</body>
</html>
