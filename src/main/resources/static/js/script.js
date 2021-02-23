window.onscroll = function() {scrollFunction()};
function scrollFunction() {
  if (document.body.scrollTop > 80 || document.documentElement.scrollTop > 80) {
		$('.navbar').css('padding', '1rem 1rem');
		$('.header-logo').css('width', '50px');
		$('.navbar-nav').css('font-size', '1rem');
    $('.dropdown_nav_style').css('font-size', '1rem');
  } else {
		$('.navbar').css('padding', '1.3rem 1.3rem');
		$('.header-logo').css('width', '60px');
		$('.navbar-nav').css('font-size', '20px');
    $('.dropdown_nav_style').css('font-size', '20px');
  }
}
