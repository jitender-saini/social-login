<%@ page import="com.login.enums.UserRoles" %>
<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>
        <g:layoutTitle default="Grails"/>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <script src="https://apis.google.com/js/platform.js?onload=onLoad" async defer></script>
    <meta name="google-signin-client_id"
          content="7541036959-6661hcep3tr20o2h9an2k39am7c6bo20.apps.googleusercontent.com">
    <asset:stylesheet src="application.css"/>
    <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="shortcut icon" href="${createLinkTo(dir: 'images', file: 'favicon.ico')}" type="image/x-icon"/>
    <g:layoutHead/>
</head>

<body>

<div class="navbar navbar-default navbar-static-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/#">
                <i class="fa grails-icon">
                    <asset:image src="grails-cupsonly-logo-white.svg"/>
                </i> Grails
            </a>
        </div>

        <div class="navbar-collapse collapse" aria-expanded="false" style="height: 0.8px;">
        <ul class="nav navbar-nav navbar-right">
            <g:pageProperty name="page.nav"/>
            <sec:ifLoggedIn>

                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                %{--aria-expanded="false">${session.user.name} <span class="caret"></span></a>--}%
                    <ul class="dropdown-menu">
                        <sec:ifAnyGranted roles="${UserRoles.ROLE_ADMIN}">
                            <li>
                                <a href='${createLink(controller: 'user', action: 'usersList')}'>Users List</a>
                            </li>
                        </sec:ifAnyGranted>
                        <li role="separator" class="divider"></li>
                        <li><a href="#" onclick="signOut();">Sign out</a></li>
                    </ul>
                </li>
                </ul>
            </sec:ifLoggedIn>

        </div>
    </div>
</div>
<g:if test="${flash.message}">
    <div class="alert alert-success">${flash.message}</div>
</g:if>
<g:if test="${flash.error}">
    <div class="alert alert-danger">${flash.error}</div>
</g:if>

<div id="msg"></div>

<g:layoutBody/>

%{--<div class="footer" role="contentinfo"></div>--}%


<asset:javascript src="application.js"/>

</body>
</html>
