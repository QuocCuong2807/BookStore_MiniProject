<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Corona Admin</title>
    <!-- plugins:css -->
    <link rel="stylesheet" href="./log/assets/vendors/mdi/css/materialdesignicons.min.css">
    <link rel="stylesheet" href="./log/assets/vendors/css/vendor.bundle.base.css">
    <!-- endinject -->
    <!-- Plugin css for this page -->
    <!-- End plugin css for this page -->
    <!-- inject:css -->
    <!-- endinject -->
    <!-- Layout styles -->
    <link rel="stylesheet" href="./log/assets/css/style.css">
    <!-- End layout styles -->
    <link rel="shortcut icon" href="./log/assets/images/favicon.png" />
    
    <style type="text/css">
    	.msg, .msg-us, .msg-pw, .msg-ival{
    		color:red;
    	}
    </style>
    
  </head>
  <body>
  
  <%
  	String e_userName = request.getAttribute("e_username") + "";
  	String e_passWord = request.getAttribute("e_password") + "";
  	String e_invalidUser = request.getAttribute("e_invalidUser") + "";
  	
  	e_userName = (e_userName == null || e_userName.equals("null")) ? "" : e_userName;
  	e_passWord = (e_passWord == null || e_passWord.equals("null")) ? "" : e_passWord;
  	e_invalidUser = (e_invalidUser == null || e_invalidUser.equals("null")) ? "" : e_invalidUser;
  %>
  
    <div class="container-scroller">
      <div class="container-fluid page-body-wrapper full-page-wrapper">
        <div class="row w-100 m-0">
          <div class="content-wrapper full-page-wrapper d-flex align-items-center auth login-bg">
            <div class="card col-lg-4 mx-auto">
              <div class="card-body px-5 py-5">
                <h3 class="card-title text-left mb-3">Login</h3>
                <form id = "form" action="do-login" method = "post">
                  <div class="form-group">
                    <label>Username</label>
                    <input type="text" class="form-control p_input" id="userName" name="username" >
                  </div>
                  <span class="msg" id = "msg-us"> <%= e_userName %> </span>
                  <div class="form-group">
                    <label>Password *</label>
                    <input type="password" class="form-control p_input" id ="password" name="password" >
                  </div>
                  <span class="msg" id = "msg-pw"> <%= e_passWord %> </span>
                  <span class="msg-ival"> <%=e_invalidUser %> </span>
                  <div class="form-group d-flex align-items-center justify-content-between">
                    <div class="form-check">
                      <label class="form-check-label">
                        <input type="checkbox" class="form-check-input"> Remember me </label>
                    </div>
                    <a href="#" class="forgot-pass">Forgot password</a>
                  </div>
                  <div class="text-center">
                    <button type="button" class="btn btn-primary btn-block enter-btn" onclick="validateLogin()" >Login</button>
                  </div>
                  <p class="sign-up">Don't have an Account?<a href="#"> Sign Up</a></p>
                </form>
              </div>
            </div>
          </div>
          <!-- content-wrapper ends -->
        </div>
        <!-- row ends -->
      </div>
      <!-- page-body-wrapper ends -->
    </div>
    <!-- container-scroller -->
    <!-- plugins:js -->
    <script src="./log/assets/vendors/js/vendor.bundle.base.js"></script>
    <!-- endinject -->
    <!-- Plugin js for this page -->
    <!-- End plugin js for this page -->
    <!-- inject:js -->
    <script src="./log/assets/js/off-canvas.js"></script>
    <script src="./log/assets/js/hoverable-collapse.js"></script>
    <script src="./log/assets/js/misc.js"></script>
    <script src="./log/assets/js/settings.js"></script>
    <script src="./log/assets/js/todolist.js"></script>
    <script src="./log/assets/js/catching_err.js"></script>
    <!-- endinject -->
    <script type="text/javascript">
    function validateLogin(){
    	var userName = document.getElementById('userName').value;
    	var password = document.getElementById('password').value;
    	var count = 0
    	if(userName.length == 0){
    		document.getElementById('msg-us').innerHTML = "Vui long khong de trong ten dang nhap";
    		count++
    	}if(password.length == 0){
    		document.getElementById('msg-pw').innerHTML = "Vui long khong de trong mat khau";
    		count++
    	}if(count == 0){
    		var form = document.getElementById('form')
    		form.submit()
    	}
    	
    }
    </script>
  </body>
</html>