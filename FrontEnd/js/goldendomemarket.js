/** 
* @author Anene Terefe
* @author Hanna Workneh
* created April, 2020
*/
"user strict";

$(document).ready(function () {
    const LOGIN_PAGE = '<div class="container"> <div class="row"> <div class="col-sm-12 col-md-7 col-lg-5 mx-auto"> <div class="alert alert-danger alert-dismissible fade show" role="alert" id="loginalertfail"> <strong>Login Failed!</strong> Wrong email or password. <button type="button" class="close" data-dismiss="alert" aria-label="Close"> <span aria-hidden="true">&times;</span> </button> </div> <div class="card card-signin my-5"> <div class="card-body"> <h4 class="card-title text-center">Golden Dome Market</h4> <h6 class="card-title text-center">Online Order Service</h6> <hr class="my-4"> <h5 class="card-title text-center">Sign In</h5> <form id="formSignin" class="form-signin"> <div class="form-label-group"> <input type="email" id="inputEmail" class="form-control" placeholder="Email address" required autofocus> <label for="inputEmail" class="form-text text-muted">Your miu email address</label> </div> <div class="form-label-group"> <input type="password" id="inputPassword" class="form-control" placeholder="Password" required> <label for="inputPassword" class="form-text text-muted">Password</label> </div> <div class="custom-control custom-checkbox mb-3"> <input type="checkbox" checked class="custom-control-input" id="customCheck1"> <label class="custom-control-label" for="customCheck1">Stay Logged in</label> </div> <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button> </form> </div> </div> </div> </div> </div>';
    function showLogin() {
        $("#content").html(LOGIN_PAGE);
        $("#loginalertfail").hide();
    }
    $.urlParam = function (name) {
        var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
        if(results!=null)
            return results[1] || 0;
        else
            return null    
    }

    if ($.urlParam('logout')!=null) {
        Cookies.remove('accountType')
        showLogin();
    }
    //====== form action listeners 
    if (Cookies.get('accountType') == null) {
        showLogin();
    } else {
        if (Cookies.get('accountType') == "admin") {
            window.location.href = "../adminpage.html?id="+Cookies.get('user');
        } else {
            window.location.href = "../studentpage.html?id="+Cookies.get('user');
        }
    }   //Login Form
    $("#formSignin").submit(function (event) {
        event.preventDefault();
        let datad = {
            "email": $("#inputEmail").val(),
            "password": $("#inputPassword").val()
        }
        $.ajax({
            type: "POST",
            url: 'http://localhost:8080/goldendomemarket/api/login',
            dataType: 'json',//json
            contentType: 'application/x-www-form-urlencoded',
            data: JSON.stringify(datad),
            success: function (response) {
                if (response != null) {
                    Cookies.set('accountType', response.accountType);
                    Cookies.set('user', response.accountId);
                    if (response.accountType == "admin") {
                        window.location.href = "../adminpage.html";
                    } else if (response.accountType == "manager") {
                        window.location.href = "../managerpage.html";
                    } else {
                        window.location.href = "../studentpage.html";
                    }
                } else {
                    $("#loginalertfail").show();
                }
            }
        }).fail(function () {
            $("#loginalertfail").show();
        });
    });
});

