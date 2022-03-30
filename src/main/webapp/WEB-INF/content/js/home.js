 document.addEventListener('DOMContentLoaded', function() {

	let login_form = document.querySelector("#login > form")
	let registration_form = document.querySelector("#register > form")
	
	let login = document.querySelector("#login")
	let registration = document.querySelector("#register")
	
	login_form.style.display = "none"
	registration_form.style.display = "none"
	
	login.addEventListener("mouseover", function () {
	    
	    login_form.style.display = ""
	    registration_form.style.display = "none"
	
	});
	
	registration.addEventListener("mouseover", function () {
		
	    login_form.style.display = "none"
	    registration_form.style.display = ""
	    
	});
});