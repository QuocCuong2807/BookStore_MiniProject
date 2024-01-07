/**
 * 
 */

function passwordCatching() {
    var password = document.getElementById('password').value
    var rePassword = document.getElementById('Repassword').value
    
    if(password != rePassword){
        document.getElementById("msg-repass").innerHTML = "sai mat khau nhap lai"
        return false
    }else{
		document.getElementById("msg-repass").innerHTML =  "";
	    return true
	}

}

function checkingErr(){
    if(passwordCatching()){
        document.querySelector('#submit').style.display = "block"
    }
    else{
		document.querySelector('#submit').style.display = "none"
	}
	
}

