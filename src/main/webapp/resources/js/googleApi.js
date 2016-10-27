function start() {
	gapi.load('auth2',
			function() {
		auth2 = gapi.auth2.init({
			client_id : '412974983923-jg7l8j84308pughfqt5o8q0gc0pslh83.apps.googleusercontent.com'
		});
	});
}