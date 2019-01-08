
// this file is no longer used/required.

document.addEventListener('DOMContentLoaded', setSubmitHandlers);

function setSubmitHandlers() {
	document.getElementById('signup-form').addEventListener('submit', signup);
	document.getElementById('login-form').addEventListener('submit', login);
}

function login(event) {
	event.preventDefault();
	var email = event.target.children[0].value;
	var password = event.target.children[1].value;
	var data = new URLSearchParams();
	data.append('email', email);
	data.append('password', password);
	ajaxPost("LoginController", data, function(response) {
		
	});
}

function signup(event) {
	event.preventDefault();
	var email = event.target.children[0].value;
	var password = event.target.children[1].value;
	var data = new URLSearchParams();
	data.append('email', email);
	data.append('password', password);
	ajaxPost("SignupController", data, function(response) {
		alert(response);
	});
}

function ajaxPost(target, data, callback) {
	var xmlHttp = new XMLHttpRequest();
	xmlHttp.onreadystatechange = function() {
		if(xmlHttp.readyState == 4 && xmlHttp.status == 200) {
			callback(xmlHttp.responseText);
		}
	}
	xmlHttp.open("post", target, true);
	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlHttp.send(data);
}
