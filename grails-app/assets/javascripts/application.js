// This is a manifest file that'll be compiled into application.js.
//
// Any JavaScript file within this directory can be referenced here using a relative path.
//
// You're free to add application-wide JavaScript to this file, but it's generally better
// to create separate JavaScript files as needed.
//
//= require jquery-2.2.0.min
//= require bootstrap
//= require_tree .
//= require_self

if (typeof jQuery !== 'undefined') {
    (function ($) {
        $(document).ajaxStart(function () {
            $('#spinner').fadeIn();
        }).ajaxStop(function () {
            $('#spinner').fadeOut();
        });
    })(jQuery);
}

function onSignIn(googleUser) {
    var profile = googleUser.getBasicProfile();
    console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
    var userCO = {
        name: profile.getName(),
        email: profile.getEmail(),
        photoUrl: profile.getImageUrl()
    };

    $.ajax({
        url: '/login/googleLogin',
        type: 'POST',
        data: userCO,
        success: function () {
            window.location = "/user/index";
        },
        error: function () {
            $("#msg").html("Error in response");
            $("#msg").addClass("alert alert-danger");

        }
    })
}

function appSignOut() {
    var auth2 = gapi.auth2.getAuthInstance();
    if (auth2) {
        auth2.signOut().then(function () {
            console.log('google signed out.');
        });
    }
    else {
        //fb logout
        FB.logout(function (response) {
            console.log('facebook signed out.');
        });
    }
}

function signOut() {
    jQuery.ajax({
        url: '/logout',
        success: function () {
            $("#msg").html("Sign out success");
            window.location = "/login/index";
            appSignOut();
        },
        error: function () {
            $("#msg").html("Error in response");
        }
    })
}

//onLoad of the main layout it will set google auth
function onLoad() {
    gapi.load('auth2', function () {
        gapi.auth2.init();
    });
}

// Facebook login


window.fbAsyncInit = function () {
    FB.init({
        appId: '1375407885885060',
        cookie: true,
        xfbml: true,
        version: 'v2.8'
    });
    FB.AppEvents.logPageView();
};


// Load the SDK asynchronously
(function (d, s, id) {
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) {
        return;
    }
    js = d.createElement(s);
    js.id = id;
    js.src = "//connect.facebook.net/en_US/sdk.js";
    fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));


// FB.getLoginStatus(function(response) {
//     statusChangeCallback(response);
//     console.log(response);
// });


function checkLoginState() {
    FB.getLoginStatus(function (response) {
        statusChangeCallback(response);
    });
}

function statusChangeCallback(response) {
    console.log('statusChangeCallback');
    console.log(response);
    // The response object is returned with a status field that lets the
    // app know the current login status of the person.
    // Full docs on the response object can be found in the documentation
    // for FB.getLoginStatus().
    if (response.status === 'connected') {
        // Logged into your app and Facebook.
        FB.api('/me', function (response) {
            console.log(JSON.stringify(response));
            console.log(response);
            var userCO = {
                name: response.name,
                username: response.id,
                photoUrl: "http://graph.facebook.com/" + response.id + "/picture"
            };

            $.ajax({
                url: '/login/googleLogin',
                type: 'POST',
                data: userCO,
                success: function () {
                    window.location = "/user/index";
                },
                error: function () {
                    $("#msg").html("Error in response");
                    $("#msg").addClass("alert alert-danger");

                }
            });


        });




        // testAPI();
    } else {
        // The person is not logged into your app or we are unable to tell.
        document.getElementById('status').innerHTML = 'Please log ' +
            'into this app.';
    }
}

function testAPI() {
    console.log('Welcome!  Fetching your information.... ');
    FB.api('/me', function (response) {
        console.log('Successful login for: ' + response.name);
        console.log(response);
    });

    // document.getElementById('status').innerHTML =
    //     'Thanks for logging in, ' + response.name + '!';
    FB.api('/me', function (response) {
        alert("Name: " + response.name + "\nFirst name: " + response.first_name + "email: " + response.email);
        var img_link = "http://graph.facebook.com/" + response.id + "/picture";
        console.log(img_link);
    });
    FB.api('/me', function (response) {
        console.log(JSON.stringify(response));
    });
}
