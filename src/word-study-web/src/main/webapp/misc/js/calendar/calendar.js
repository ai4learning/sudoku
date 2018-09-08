/**
 * author:wanghaixin@jd.com
 * date:20140523
 * version:1.0.0
 */

function Calendar(arg){
	var that = this,
		relevant = Calendar.relevant,
		options = $.extend({
			frondose : false,
			week : ['日','一','二','三','四','五','六'],
			month : ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'],
			yearRange : [2000,2050],
			disabled : true,
			format : 'yyyy-MM-dd hh:mm:ss',
			sTime : 's'
		},arg || {}),
		dayNum = [31,28,31,30,31,30,31,31,30,31,30,31],
		yearIndex = Math.min(options.yearRange[0],options.yearRange[1]),
		state = 0,
		now = new Date(),
		nowYear = now.getFullYear(),
		nowMonth = now.getMonth() + 1,
		nowDay = now.getDate(),
		head = panel = week = null,
		selected = {},
		date = null,
		yearBar,monthBar,
		dayStr = nowYear*10000 + nowMonth*100 + nowDay,
		monthStr = nowYear*100 + nowMonth,
		specificTime = null,note,
		originalHeight = null,format,
		maxPanelHeight = 281,specHeight = 40;
	
	function data(){
		format = options.format;
		if(!options.frondose){
			format = format.split(' ')[0];
		}
		else if(options.sTime != 's'){
			format = format.replace(/\:ss/,'');
		}
		else{}
	}
	
	
	function getDate(){
		date = {
			year : now.getFullYear(),
			month : now.getMonth() + 1,
			date : now.getDate(),
			hour : now.getHours(),
			minute : now.getMinutes(),
			second : now.getSeconds()
		};
		
		if(!!Calendar.relevant.val()){
			var value = Calendar.relevant.val(),
				reg = /\d+/g;
			date.year = parseInt(reg.exec(value)[0],10);
			date.month = parseInt(reg.exec(value)[0],10);
			date.date = parseInt(reg.exec(value)[0],10);
			if(options.frondose){
				try{
					date.hour = parseInt(reg.exec(value)[0],10);
					date.minute = parseInt(reg.exec(value)[0],10);
					date.second = parseInt(reg.exec(value)[0],10);
				}catch(e){}
			}
		}
		
		selected.year = date.year;
		selected.month = date.month;
		selected.date = date.date;
	}
	
	function createPanel(data){
		var top = $('<div class="top"><b class="arrow"><div class="left"> <<</div></b><b class="arrow" style="float:right;"><div class="right"> >></div></b></div>').appendTo(Calendar.dom);
		$('b:last',top).after($('<b class="head"><span>' + data.year +'年' + data.month + '月</span><b>'));
		
		var str = '<div class="week">';
		
		for(var i = 0; i < 7; i++){
			str += '<div>' + options.week[i] + '</div>';
		};
		week = $(str + '</div>').appendTo(Calendar.dom);
		panel = $('<div class="days"></div>').appendTo(Calendar.dom);
		
		if(options.frondose){
			var time = {
				hour : '<input type="text" size="2" maxLength="2" title="hour" value="' + date.hour + '"/>',
				minute : '<input type="text" size="2" maxLength="2" title="minute" value="' + date.minute + '"/>',
				second : '<input type="text" size="2" maxLength="2" title="second" value="' + date.second + '"/>'
			};
			var str = '';
			str = '<div class="speic_time"><input class="sure" type="button" value="确定"/>' + time.hour + '时';
			if(options.sTime == 'm'){
				str += time.minute + '分'; 
			}
			else if(options.sTime == 's'){
				str += time.minute + '分'; 
				str += time.second + '秒';
			}
			else{
			
			}
			str += '</div>';
			specificTime = $(str).appendTo(Calendar.dom);
			note = $('<div class="note">选择月份可进一步选择日期，如有必要，请先选择年份再选择月份。</div>').appendTo(specificTime);
		}
		draw();
	}
	
	function draw(year,month){;
		switch(state){
			case 0 : {
				drawDate();
				break;
			}
			case 1 : {
				drawMonth();
				break;
			}
			default : {
			}
		}
	}
	
	function drawDate(){
		panel.empty();
		var first = new Date(date.year,parseInt(date.month,10)-1,1).getDay(),
			temp = '<div style="width:' + (39*first) + 'px;height:20px;float:left;"></div>',
			month = date.month,
			year = date.year;
		if(options.disabled){
			for(var i = 1 ; i <= dayNum[date.month - 1]; i++){
				temp += '<a href="#none" class="day';
				if(year*10000 + month*100 + i < dayStr){
					temp += ' disabled';
				}
				if((i + first) %7 == 0){
					temp += ' saturday';
				}
				if((i + first)%7 == 1){
					temp += ' sunday';
				}
				if(year == nowYear&& month == nowMonth&&i == nowDay)
					temp += ' current';
				if(year == selected.year && month == selected.month&& i == selected.date)
					temp +=' select';
				temp += '">' + i + '</a>';
			}
		}
		else{
			for(var i = 1 ; i <= dayNum[date.month - 1]; i++){
				temp += '<a href="#none" class="day';
				if((i + first) %7 == 0){
					temp += ' saturday';
				}
				if((i + first)%7 == 1){
					temp += ' sunday';
				}
				if(year == nowYear&& month == nowMonth&&i == nowDay)
					temp += ' current';
				if(year == selected.year && month == selected.month&& i == selected.date)
					temp +=' select';
				temp += '">' + i + '</a>';
			}
		}
		
		if(year%4 == 0 && year%100 != 0 && month == 2){
			temp += '<a href="#none" class="day';
			if(options.disabled && year*10000 + month*100 + i < nowStr){
				temp += ' disabled';
			}
			if(year == nowYear&& month == nowMonth&&i == nowDay)
				temp += ' current';
			if(year == selected.year && month == selected.month&& i == selected.date)
				temp +=' select';
			temp += '">' + i + '</a>';
		}
		panel.html(temp);
		var h = Calendar.dom.find('.top span')
		h.html(year + '年' + options.month[month - 1]);
		week.show();
		
		if(options.frondose){
			note.hide();
		}
	}
	
	function drawMonth(){
		var height = panel.outerHeight(true) + week.height();
		panel.empty();
		week.hide();
		var temp = '<div class="yearPanel"><div class="title">年份</div><div class="content">',
			year = date.year;
		for(var i = 0,len = Math.abs(options.yearRange[1] - options.yearRange[0]); i < len; i++){
			temp += '<a href="#none" class="year';
			if(year == i + yearIndex)
				temp += ' current';
			if(selected.year == i + yearIndex)
				temp += ' select';
			temp+= '">' + (yearIndex + i) + '</a>';
		}
		
		temp += '</div></div><div class="monthPanel"><div class="title">月份</div><div class="content">';
		for(var j = 0, len = 12; j < len; j++){
			temp +='<a href="#none" class="month';
			if(nowMonth == j + 1)
				temp += ' current';
			if(selected.month == j + 1)
				temp += ' select';
			temp+= '" index="' + j + '">' + options.month[j] + '</a>';
		}

		temp += '</div></div>';
		panel.html(temp);
		var h = Calendar.dom.find('.top span')
		h.html(date.year + '年' + date.month + '月');
		panel.find('.monthPanel .content,.yearPanel .content').css({
			maxHeight : height - 32
		});
		yearBar = new NewScrollBar(panel.find('.yearPanel .content'));
		monthBar = new NewScrollBar(panel.find('.monthPanel .content'));
		barAjust(1);
		
		if(options.frondose)
			note.show();
		
	}
	
	function reselect(flag){
		panel.find('.monthPanel .month').removeClass('select');
		panel.find('.monthPanel .month').removeClass('current');
		panel.find('.month[index="' + (date.month - 1) + '"]').addClass('select');
		panel.find('.yearPanel a').removeClass('select').eq(date.year - yearIndex).addClass('select');
		if(date.year == selected.year){
			panel.find('.month[index="' + (nowMonth - 1) + '"]').addClass('current');
		}
		var h = Calendar.dom.find('.top span')
		h.html(date.year + '年' + date.month + '月');
		barAjust(flag);
	}
	
	function barAjust(flag){
		var h = panel.find('a:first').outerHeight(true);
		
		if(typeof flag != 'undefined')
			yearBar.scrollTo((date.year - yearIndex)*h);
		monthBar.scrollTo((date.month - 1)*h);
	}
	
	
	function eventInit(){
		var h = Calendar.dom.find('.top span')
		h.click(function(){
			state = (state + 1)%2;
			draw();
		});
		
		panel.delegate('.month,.day','click',function(){
			if($(this).hasClass('disabled')) return;
			if(state == 0){
				if(options.frondose){
					$(this).addClass('select').siblings().removeClass('select');
					date.date = $(this).html();
					return;
				}
				else{
					var result = new Date(date.year,date.month - 1,$(this).html()).format(format);
					Calendar.relevant.val(result);
					that.remove();
					return;
				}
			}
			else{
				date.month = parseInt($(this).attr('index'),10)+ 1;
			}
			state = 0;
			draw();
		});
		
		panel.delegate('.yearPanel a','click',function(){
			$(this).addClass('select').siblings().removeClass('select');
			date.year = $(this).html();
			reselect();
		});
		
		Calendar.dom.find('.sure').click(function(){
			var inputs = specificTime.find('input'),
				m,s;
			if(options.sTime == 'h'){
				m = s = 0;
			}
			else if(options.sTime == 'm'){
				s = 0;
				m = inputs.eq(2).val();
			}
			else{
				m = inputs.eq(2).val();
				s = inputs.eq(3).val();
			}
			var d = new Date(date.year,parseInt(date.month,10) - 1,date.date,inputs.eq(1).val(),m,s);
				result = d.format(format);
			Calendar.relevant.val(result);
			that.remove();
			return;
		});
		
		Calendar.dom.find('.arrow:first').click(function(){	
			date.month --;
			if(date.month === 0){
				date.month = 12;
				date.year = Math.max(options.yearRange[0],date.year - 1);
			}
			if(state == 0)
				draw();
			else
				reselect(true);
			
		});
		
		Calendar.dom.find('.arrow:last').click(function(){
			date.month ++;
			if(date.month === 13){
				date.month = 1;
				date.year ++ ;
				date.year = Math.min(options.yearRange[1],date.year);
			}
			if(state == 0)
				draw();
			else
				reselect(true);
			
		});
		
		Calendar.dom.find('input').keyup(function(e){
			var result = $(this).val().match(/^\d{1,2}/g),val;
			if(result){
				val = parseInt(result[0],10);
			}
			else{
				val = 0;
			}
			if(e.keyCode == 38){
				val ++;
			}
			else if(e.keyCode == 40){
				val --;
			}
			var title = $(this).attr('title');
			if(title == 'hour'){
				val = Math.min(23,Math.max(val,0));
			}
			else{
				val = Math.min(59,Math.max(val,0));
			}
			$(this).val(val);
		});
		
		Calendar.dom.click(function(){
			return false;
		});
	}
	
	function init(){
		data();
		getDate();
		createDom();
		createPanel(date);
		setPosition();
		eventInit();
	}
	
	function createDom(){
		Calendar.dom = $('<div class="calendar"></div>').appendTo('body');
	}
	
	
	function setPosition(){
		var h = maxPanelHeight;
		if(options.frondose)
			h += specHeight;
			
		var top = relevant.offset().top + relevant.outerHeight();
		if(top + h > $(window).height() + $(window).scrollTop()){
			Calendar.dom.css({
				left : relevant.offset().left,
				top : relevant.offset().top - h
			});
		}
		else{
			Calendar.dom.css({
				left : relevant.offset().left,
				top : relevant.offset().top + relevant.outerHeight()
			});
		}	
	}
	
	this.reset = setPosition;
	
	
	
	
	this.remove = function(){
		Calendar.dom.remove();
		Calendar.relevant = null;
	};
	
	init();
}

Calendar.dom = null;
Calendar.relevant = null;


var calendar = null;
(function(){

	
	function getBaseURL(){
		var url = $('script[src*="calendar"]').attr('src'),
			index = url.lastIndexOf('calendar');
		return url.substring(0,index);
	}
	/*
	 * body区域其他地方点击calendar消失
	 */
	$(function(){
		$('<link rel="stylesheet" type="text/css" href="' + getBaseURL() +'calendar.css" />') .appendTo('head');
		$('body').click(function(e){
			if(!!$(e.target || e.srcElement).closest('.calendar').length) return;
			if(!!Calendar.relevant){
				calendar.remove();
			}
		});
		$(window).resize(function(){
			if(!!Calendar.relevant){
				calendar.reset();
			}
		});
		
	});
	
	$.fn.extend({
		calendar : function(arg){
			if(!$(this).length) return;
			$(this).attr('readOnly',true);
			
			$(this).click(function(){
				if(!!Calendar.relevant)	calendar.remove();
				Calendar.relevant = $(this);
				calendar = new Calendar(arg);
				return false;
			});
		}
	});
})();

Date.prototype.format = function(format){
	var o = {   
		"M+" : this.getMonth()+1,  
		"d+" : this.getDate(),     
		"h+" : this.getHours(),   
		"m+" : this.getMinutes(), 
		"s+" : this.getSeconds(),
		"q+" : Math.floor((this.getMonth()+3)/3), 
		"S" : this.getMilliseconds()
	};
	if(/(y+)/.test(format)) format=format.replace(RegExp.$1,(this.getFullYear()+"").substr(4 - RegExp.$1.length));   
	for(var k in o)
		if(new RegExp("("+ k +")").test(format))   
			format = format.replace(RegExp.$1,   
	RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));   
	return format; 
};


/**
 * author:wanghaixin@jd.com
 * date:20140613
 * version:1.0.0
 */

 var cssStyle = '.scrollBar_outer{overflow:hidden;position:relative;}\n.bar_outer{position:absolute;right:0px;top:0px;bottom:0px;display:none;}\n.bar_container{height:100%;position:relative;}\n.bar_inner{border-radius:2px;background:#ccc;position:absolute;top:0px;left:0px;width:100%;}';
 
 
function NewScrollBar(con){

	var that = this,
		dom = $(con),
		outerContainer = null,
		innerBar,outerBar,
		step = 0,
		windowHeight,actualHeight,barCHeight,barHeight;
	
	function init(){
		if(!dom.length) return;
		rendDom();
	}
	
	function rendDom(){
		dom.wrap("<div class=\"scrollBar_outer\"></div>");
		outerContainer = dom.parent();
		outerContainer.css({
			maxHeight : dom.css('maxHeight'),
			'float' : dom.css('float')
		});
		dom.css({
			maxHeight : 'none'
		});
		
		windowHeight = outerContainer.height();
		actualHeight = dom.height();
		if(actualHeight > windowHeight){
			createBar();
			baseEvent();
		}
	}
	
	function createBar(){
		barHeight = parseInt(windowHeight*windowHeight/actualHeight,10);
		barCHeight = windowHeight - 2;
		outerBar = $('<div class="bar_outer"><div class="bar_container"></div></div>').appendTo(outerContainer).css('width',NewScrollBar.defaultBarWith);
		innerBar = $('<div class="bar_inner"></div>').appendTo(outerBar.find('div')).css('height',barHeight);
		step = (actualHeight - windowHeight)/(windowHeight - innerBar.height() - 2);
		
		if(NewScrollBar.alwaysShow){
			outerBar.css('display','block');
		}
	}
	
	function baseEvent(){
		outerContainer.mousewheel(function(e){
			var data = e.delta,
				barT = parseInt(innerBar.css('top'),10),
				conT = parseInt(dom.css('marginTop'),10);
			innerBar.css('top',Math.max(0,Math.min(barT - data*NewScrollBar.rate,barCHeight - barHeight)));
			dom.css('marginTop',Math.min(0,Math.max(data*step*NewScrollBar.rate + conT,windowHeight - actualHeight)));
			NewScrollBar.wheeling = true;
			setTimeout(function(){
				NewScrollBar.wheeling = false;
			},0);
			return false;
		});
		
		innerBar.bind('step',function(){
			var total = parseInt($(this).css('top'),10);
			
			dom.css('marginTop',- total*step);
		});
		if(!!innerBar.draggable){
			innerBar.draggable({
				containment : outerBar,
				axis : 'y',
				cursor : 'move',
				start : function(){
				},
				drag : function(){
					$(this).trigger('step');
				},
				stop : function(){
				}
				
			});
		}
		
		
		if(!NewScrollBar.alwaysShow){
			outerContainer.hover(function(){
				outerBar.show();
			},function(){
				outerBar.hide();
			});	
		}
	}
	
	this.scrollTo = function(num){
		if(num > parseInt(dom.css('marginTop'),10) + windowHeight){
			var top = Math.min(0,Math.max(windowHeight/2 - num,windowHeight - actualHeight));
			dom.css('marginTop',top);
			innerBar.css('top',Math.abs(top/step));
		}
		else if(num <parseInt(dom.css('marginTop'),10)){
			var top = num;
			dom.css('marginTop',-top);
			innerBar.css('top',Math.abs(top/step));
		}
	};
	
	this.reset = function(){
		actualHeight = dom.height();
		windowHeight = outerContainer.height();

		dom.css('marginTop',0);
		if(actualHeight > windowHeight){
			if(!!outerBar){
				barHeight = windowHeight*windowHeight/actualHeight;
				innerBar.css({
					height : barHeight,
					top : 0
				});
				step = (actualHeight - windowHeight)/(windowHeight - innerBar.height() - 2);
			}
			else{
				createBar();
				baseEvent();
			}
		}
		else{
			if(!!outerBar){
				innerBar.draggable('destroy');
				outerBar.hide();
				outerBar = null;
			}
		}
	};
	
	init();
	
}

NewScrollBar.defaultBarWith  = 5;
NewScrollBar.alwaysShow = true;
NewScrollBar.rate = 5;
NewScrollBar.wheeling = false;

$(function(){
	$('<style type="text/css">' + cssStyle + '</style>').appendTo('head');
});

(function(){
	if(!!$.mousewheel) return;
	$.fn.extend({
		mousewheel : function(arg){
			if(typeof arg != 'function') return;
			
			function addEvent(el, type, callback, useCapture  ){
			      if(el.dispatchEvent){
			          el.addEventListener( type, callback, true  );
			      }else {
			          el.attachEvent( "on"+type, callback );
			      }
			      return callback;
			  }
			  var wheel = function(obj,callback){
			      var wheelType = "mousewheel";
			      try{
			          document.createEvent("MouseScrollEvents");
			          wheelType = "DOMMouseScroll";
			      }catch(e){}
			      addEvent(obj, wheelType,function(event){
			          if ("wheelDelta" in event){
			              var delta = event.wheelDelta;
			              if( window.opera && opera.version() < 10 )
			                  delta = -delta;
			              event.delta = Math.round(delta) /120;
			          }else if( "detail" in event ){
			              event.wheelDelta = -event.detail * 40;
			              event.delta = event.wheelDelta /120;
			          }
			          event.returnValue = false;
			          event.preventDefault();
			          callback.call(obj,event);	          
			          return false;
			      });
			  };
			var _callback = arg, _ele = this[0];
			this.each(function(index,n){
				wheel(n,_callback);
			});		
		}
	});
})();