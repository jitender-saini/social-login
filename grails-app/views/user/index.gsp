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
                    User Details <i class="fa fa-pencil-square-o pull-right" aria-hidden="true"></i>
                </div>
            </div>

            <div class="panel-body">
                <div class="user-details">

                <ls:userImage id="${user.id}">
                    <g:img dir='images' file='user.png' style='height: 200px;'/>
                </ls:userImage>
                <div class="info">
                    <div class="desc">${user.name}</div>

                    <div class="desc">${user.email}</div>

                    <div class="desc">Tech geek</div>
                </div>
                %{-- <div class="bottom">
                     <a class="btn btn-primary btn-twitter btn-sm" href="https://twitter.com/webmaniac">
                         <i class="fa fa-twitter"></i>
                     </a>
                     <a class="btn btn-danger btn-sm" rel="publisher"
                        href="https://plus.google.com/+ahmshahnuralam">
                         <i class="fa fa-google-plus"></i>
                     </a>
                     <a class="btn btn-primary btn-sm" rel="publisher"
                        href="https://plus.google.com/shahnuralam">
                         <i class="fa fa-facebook"></i>
                     </a>
                     <a class="btn btn-warning btn-sm" rel="publisher" href="https://plus.google.com/shahnuralam">
                         <i class="fa fa-behance"></i>
                     </a>
                 </div>--}%
            </div>
        </div>
    </div>
</div>
</div>
</body>
</html>