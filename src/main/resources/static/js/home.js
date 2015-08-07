
page.redirect("/","/home");

page('/search', updateViewport);
page('/details/:title', updateViewport);
page('/profile/:userId', updateViewport);

page.start();


function updateViewport(context){
	console.log(context);
	
	var httpReq = new XMLHttpRequest();
	httpReq.open("get","/api"+context.path);
	httpReq.onload = function(){
		document.getElementById('mainViewport').innerHTML = httpReq.responseText;
		updateNav();
	};
	httpReq.send();
}

function updateFilter(){
	window.location = '/search?filter=' + document.getElementById('searchbox').value;
}
function keyPressed(e){
	if(e.keyCode==13){
		updateFilter();
	}
}
function viewProfile(){
	window.location = '/profile/mossman';
}
function viewHome(){
	window.location = '/home';

}
function updateNav(){
	var re = new RegExp("/(.*?)/");
	var match = window.location.pathname.match(re);
	if(match){
		var navEl = document.getElementById(match[1]+'Nav');
		if(navEl){
			navEl.className = "selected";
		}
	}
}



function viewDetails(title){
	window.location = '/details/'+title;
}
function setWatched(title){
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.open('GET','/setwatched?userId=mossman\u0026title='+title,true);
    xmlhttp.send();
}
function setToWatch(title){
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.open('GET','/settowatch?userId=mossman\u0026title='+title,true);
	xmlhttp.send();
}