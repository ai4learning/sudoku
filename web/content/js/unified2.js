$(document).ready(function() {
	//微信
	
 $("#entofParent").click(function(){
  $(this).toggleClass("hover");
  $(".div-weixin").toggle();
});
});
//导航结束

//轮播图

var i = -1;
var timer = 0;
$(document).ready(function() {

	var len = $(".banner li").index();
	var olh = '<li></li>';
	for(var i = 0; i < (len + 1); i++) {
		$("ol").append(olh);
	} //根据图片的index数，加载原点
	$("ol").find('li').first().addClass('red');

	move();
	timer = setInterval("move()", 4000);
	$(".banner li").hover(function() {
		clearInterval(timer);
	}, function() {
		timer = setInterval("move()", 4000);
	}) //鼠标经过停止，离开运行

	$('.hc-banner ol li').click(function() {
		var ddIndex = $(this).index() - 1;
		i = ddIndex;
		move();
	}) //小圆点控制
});

var screenWidth = window.screen.width;

function move() {



	i++;

	if(i >= 3) {
		i = 0;
	}

	if(screenWidth >= 1100) {
		$('.hc-fly').css({ "height": "100vh", opacity: 0 });
		if(i < 3) {
			if(i == 0) {
				//$('.fst-li').fadeIn(100).siblings().fadeOut(100);
				$('.hc-banner ol li').eq(i).addClass('red').siblings().removeClass('red');
				$('.hc-banner ul li').eq(i).fadeIn(100).siblings().fadeOut();
				$('.hc-fly').animate({ "height": "45vh", opacity: 1 }, 2000);
			}
			if(i == 1) {

				//$('.fst-li').fadeIn(100).siblings().fadeOut(100);
				$('.hc-banner ol li').eq(i).addClass('red').siblings().removeClass('red');
				$('.hc-banner ul li').eq(i).fadeIn(100).siblings().fadeOut();
				$('.hc-fly').animate({ "height": "45vh", opacity: 1 }, 2000);

			}
			if(i == 2) {

				//$('.fst-li').fadeIn(100).siblings().fadeOut(100);
				$('.hc-banner ol li').eq(i).addClass('red').siblings().removeClass('red');
				$('.hc-banner ul li').eq(i).fadeIn(100).siblings().fadeOut();
				$('.hc-fly').animate({ "height": "45vh", opacity: 1 }, 2000);

			}
		}
	} else if(screenWidth <= 768) {
		$('.fly-one').css({ "top": "-20%", opacity: 0 });
		$('.fly-two').css({ "height": "0vh", opacity: 0 });
		$('.fly-three').css({ "left": "-100%", opacity: 0 });
		$('.fly-four').css({ "right": "-100%", opacity: 0 });
		if(i < 2) {
			if(i == 0) {
				//$('.fst-li').fadeIn(100).siblings().fadeOut(100);
				$('.hc-banner ol li').eq(i).addClass('red').siblings().removeClass('red');
				$('.hc-banner ul li').eq(i).fadeIn(100).siblings().fadeOut();
				$('.fly-one').animate({ "top": "30%", opacity: 1 }, 1000);
				$('.fly-two').animate({ "height": "30vh", opacity: 1 }, 1000, function() {
					$('.fly-three').animate({ "left": "0", opacity: 1 }, 1000);
					$('.fly-four').animate({ "right": "0", opacity: 1 }, 1000);
				})
			}
			if(i == 1) {

				//$('.fst-li').fadeIn(100).siblings().fadeOut(100);
				$('.hc-banner ol li').eq(i).addClass('red').siblings().removeClass('red');
				$('.hc-banner ul li').eq(i).fadeIn(100).siblings().fadeOut();
				$('.fly-one').animate({ "top": "30%", opacity: 1 }, 1000);
				$('.fly-two').animate({ "height": "30vh", opacity: 1 }, 1000, function() {
					$('.fly-three').animate({ "left": "0", opacity: 1 }, 1000);
					$('.fly-four').animate({ "right": "0", opacity: 1 }, 1000);
				})

			}
			if(i == 2) {

				//$('.fst-li').fadeIn(100).siblings().fadeOut(100);
				$('.hc-banner ol li').eq(i).addClass('red').siblings().removeClass('red');
				$('.hc-banner ul li').eq(i).fadeIn(100).siblings().fadeOut();
				$('.fly-one').animate({ "top": "30%", opacity: 1 }, 1000);
				$('.fly-two').animate({ "height": "30vh", opacity: 1 }, 1000, function() {
					$('.fly-three').animate({ "left": "0", opacity: 1 }, 1000);
					$('.fly-four').animate({ "right": "0", opacity: 1 }, 1000);
				})

			}
		}
	} else {
		$('.fly-one').css({ "top": "-20%", opacity: 0 });
		$('.fly-two').css({ "height": "0vh", opacity: 0 });
		$('.fly-three').css({ "left": "-100%", opacity: 0 });
		$('.fly-four').css({ "right": "-100%", opacity: 0 });
		if(i == 0) {
			//$('.fst-li').fadeIn(100).siblings().fadeOut(100);
			$('.hc-banner ol li').eq(i).addClass('red').siblings().removeClass('red');
			$('.hc-banner ul li').eq(i).fadeIn(100).siblings().fadeOut();
			$('.fly-one').animate({ "top": "30%", opacity: 1 }, 1000);
			$('.fly-two').animate({ "height": "30vh", opacity: 1 }, 1000, function() {
				$('.fly-three').animate({ "left": "0", opacity: 1 }, 1000);
				$('.fly-four').animate({ "right": "0", opacity: 1 }, 1000);
			})
		}
		if(i == 1) {

			//$('.fst-li').fadeIn(100).siblings().fadeOut(100);
			$('.hc-banner ol li').eq(i).addClass('red').siblings().removeClass('red');
			$('.hc-banner ul li').eq(i).fadeIn(100).siblings().fadeOut();
			$('.fly-one').animate({ "top": "30%", opacity: 1 }, 1000);
			$('.fly-two').animate({ "height": "30vh", opacity: 1 }, 1000, function() {
				$('.fly-three').animate({ "left": "0", opacity: 1 }, 1000);
				$('.fly-four').animate({ "right": "0", opacity: 1 }, 1000);
			})

		}
		if(i == 2) {

			//$('.fst-li').fadeIn(100).siblings().fadeOut(100);
			$('.hc-banner ol li').eq(i).addClass('red').siblings().removeClass('red');
			$('.hc-banner ul li').eq(i).fadeIn(100).siblings().fadeOut();
			$('.fly-one').animate({ "top": "30%", opacity: 1 }, 1000);
			$('.fly-two').animate({ "height": "30vh", opacity: 1 }, 1000, function() {
				$('.fly-three').animate({ "left": "0", opacity: 1 }, 1000);
				$('.fly-four').animate({ "right": "0", opacity: 1 }, 1000);
			})

		}
	}
}

//轮播图结束