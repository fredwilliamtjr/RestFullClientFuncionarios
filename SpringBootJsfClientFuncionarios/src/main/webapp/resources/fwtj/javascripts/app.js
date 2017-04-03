$(function() {
	$('.js-toggle').bind('click', function(event) {
		$('.js-sidebar, .js-content').toggleClass('is-toggled');
		event.preventDefault();
	});	
});





//$(function() {
//
//$('li').click(function(){
//    $(this).toggleClass('is-selected');
//});
//});

$(function() {

$('a').click(function(){
    $(this).toggleClass('is-selected');
});
});

