var params = new URLSearchParams(document.location.search.substring(1));
var msg = params.get('er');
document.write(msg);
