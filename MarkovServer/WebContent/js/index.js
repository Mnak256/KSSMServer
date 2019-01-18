// this file is no longer used/required.

var loginFormDOM, signupFormDOM;

document.addEventListener('DOMContentLoaded', function() {
	cacheDOMElements();
	setSubmitHandlers();
});

function cacheDOMElements() {
	loginFormDOM = document.getElementById('login-form');
	signupFormDOM = document.getElementById('signup-form');
}

function setSubmitHandlers() {
	document.getElementById('signup-form').addEventListener('submit', signup);
	document.getElementById('login-form').addEventListener('submit', login);
}

function login(event) {
	event.preventDefault();
	var email = loginFormDOM.value;
	var password = loginFormDOM.value;
	var data = new URLSearchParams();
	data.append('email', email);
	data.append('password', password);
	ajaxPost("LoginController", data, function(response) {
		
	});
}

function signup(event) {
	event.preventDefault();
	var email = signupFormDOM.value;
	var password = signupFormDOM.value;
	var data = new URLSearchParams();
	data.append('email', email);
	data.append('password', password);
	ajaxPost("SignupController", data, function(response) {
		alert(response);
	});
}
