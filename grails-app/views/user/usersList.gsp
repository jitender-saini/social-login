<head xmlns:color="http://www.w3.org/1999/xhtml" xmlns:color="http://www.w3.org/1999/xhtml"
      xmlns:color="http://www.w3.org/1999/xhtml" xmlns:color="http://www.w3.org/1999/xhtml">
    <meta name="layout" content="main"/>
</head>


<body>
<div class="container-fluid well">
    <div class="my-panel">
        <div class="panel panel-default">
            <div class="panel-heading custom-heading">
                <div class="row">

                    <div class="col-sm-6">
                        <span class="panel-title">Users</span>
                    </div>

                    <div class="col-sm-3">
                        <div class="dropdown pull-right">
                            <button class="btn btn-default dropdown-toggle" type="button"
                                    data-toggle="dropdown">Users
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu">
                                <li><g:link controller="user" action="usersList">All Users</g:link></li>
                                <li><g:link controller="user" action="usersList"
                                            params="[enabled: true]">Active Users</g:link></li>
                                <li><g:link controller="user" action="usersList"
                                            params="[enabled: false]">InActive Users</g:link></li>
                            </ul>
                        </div>
                    </div>

                    <div class="col-sm-3">
                        <g:form class="navbar-form" controller="user" action="usersList">

                            <div class="inner-addon left-addon" style="margin-top: -10px">
                                <i class="fa fa-search"></i>
                                <input type="text" name="q" class="form-control search-box" placeholder="search"/>
                            </div>
                            <!--            <button type="submit" class="btn btn-default">Submit</button>-->
                        </g:form>
                    </div>

                </div>
            </div>

            <div class="panel-body">

                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <g:sortableColumn property="id" title="Id"/>
                        <g:sortableColumn property="username" title="Username"/>
                        <g:sortableColumn property="email" title="Email"/>
                        <g:sortableColumn property="name" title="Name"/>
                        <g:sortableColumn property="enabled" title="Active"/>
                        <g:sortableColumn property="enabled" title="Manage"/>
                    </tr>
                    </thead>
                    <tbody>
                    <g:each var="user" in="${users}">
                        <g:if test="${user.enabled}">
                            <tr style="background-color:#3ece45">
                        </g:if>
                        <g:else>
                            <tr style="background-color: #eee">
                        </g:else>
                        <td>${user.id}</td>
                        <td>
                            <a href='${createLink(controller: 'user', action: 'profile', params: [userId: user.id])}'
                               style="color: black">
                                ${user.username}
                            </a>
                        </td>
                        <td>${user.email}</td>
                        <td>${user.name}</td>
                        <td>${user.enabled}</td>
                        <td>
                            <g:if test="${user.enabled}">
                                <g:link controller="user" action="toggleActive"
                                        params="[userId: user.id]">Deactivate</g:link>
                            </g:if>
                            <g:else>
                                <g:link controller="user" action="toggleActive"
                                        params="[userId: user.id]">Activate</g:link>
                            </g:else>
                        </td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
                <g:paginate max="5" total="${count}"/>
            </div>

        </div>

    </div>
</div>
</body>
</html>

