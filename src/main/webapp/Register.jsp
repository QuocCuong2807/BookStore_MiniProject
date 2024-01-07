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
    	#msg{
    		color:red;
    	}
    	#msg-repass{
    		color:red;
    	}
    </style>
  </head>
  <body>
  
  <%
  	String err_email = request.getAttribute("e_email") + "";
  	String err_userName = request.getAttribute("e_userName") + "";
  	String err_passWord = request.getAttribute("e_password") + "";
  	String err_rePassWord = request.getAttribute("e_rePassWord") + "";
  	String err_phoneNumber = request.getAttribute("e_phoneNumber") + "";
  	String err_gender = request.getAttribute("e_gender") + "";
  	String err_cusFullName = request.getAttribute("e_cusFullName") + "";
  	
  	//Parse to empty string
  	String [] errValues = {err_userName, err_email, err_phoneNumber, err_passWord, err_rePassWord, err_cusFullName, err_gender};
  	
  	for(int i = 0; i< errValues.length; i++){
  		if(errValues[i] == null || errValues[i].equals("null"))
  			errValues[i] = "";
  	}
  %>
  
    <div class="container-scroller">
      <div class="container-fluid page-body-wrapper full-page-wrapper">
        <div class="row w-100 m-0">
          <div class="content-wrapper full-page-wrapper d-flex align-items-center auth login-bg">
            <div class="card col-lg-4 mx-auto">
              <div class="card-body px-5 py-5">
                <h3 class="card-title text-left mb-3">Register</h3>
                <form method="post" action="do-register">
                  <div class="form-group">
                    <label>Username</label>
                    <input type="text" id="username" name="userName" class="form-control p_input" required="required">
                  </div>
                  <span id="msg" > <%=errValues[0]%> </span>
                  <div class="form-group">
                    <label>Email</label>
                    <input type="email" id="email" name="email" class="form-control p_input" required="required">
                  </div>
                  <span id="msg" > <%=errValues[1] %> </span>
                  <div class="form-group">
                    <label>SDT</label>
                    <input type="text" id="sdt" name="phoneNumber" class="form-control p_input" required="required">
                  </div>
                  <span id="msg" > <%=errValues[2] %> </span>
                  <div class="form-group">
                    <label>Password</label>
                    <input id="password" type="password" name="password" class="form-control p_input" required="required">
                  </div>
                  <span id="msg" ><%=errValues[3] %></span>
                  <div class="form-group">
                    <label>re-Enter Password</label>
                    <input id="Repassword" type="password" name="rePassWord"  class="form-control p_input" required="required" onkeyup="checkingErr()">
                  </div>
                  <span id="msg-repass" ><%=errValues[4] %></span>
                  <div class="form-group">
                    <label>Full name</label>
                    <input type="text" id ="fullName"  name="cusFullName" class="form-control p_input" required="required">
                  </div>
                  <span id="msg" ><%=errValues[5] %></span>
                  <div class="form-group">
                    <label>Gioi tinh</label>
                    <select name="gender">
                      <option value="0">female</option>
                      <option value="1">male</option>
                    </select>
                  </div>
                  <span id="msg" ><%=errValues[6] %></span>
                  <div class="text-center">
                    <button id="submit" type="submit" class="btn btn-primary btn-block enter-btn" style="display:none">Register</button>
                  </div>
                  <p class="sign-up text-center">Already have an Account?<a href="#"> Sign Up</a></p>
                  <p class="terms">By creating an account you are accepting our<a href="#"> Terms & Conditions</a></p>
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
    <script type="text/javascript" src="./log/assets/js/catching_err.js"></script>
    <!-- endinject -->
  </body>
</html>