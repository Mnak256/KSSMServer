var codeTextareaDOM, codeOutputDOM;

document.addEventListener('DOMContentLoaded', cacheDOMElements());

function cacheDOMElements() {
	codeTextareaDOM = document.getElementById('code-textarea');
	codeOutputDOM = document.getElementById('code-output');
}

function showOutput(data) {
	codeOutputDOM.innerText = data;
}

function run() {
	var code = codeTextareaDOM.value;
	var params = new URLSearchParams();
	params.append('code', code);
	ajaxPost('CompilerController', params, function(response) {
		showOutput(response);
	});
}
