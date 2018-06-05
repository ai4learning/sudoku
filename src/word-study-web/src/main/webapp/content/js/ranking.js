$(document).ready(function(){
	
	var pageWidth = document.documentElement.clientWidth;
	var pageHeight = document.documentElement.clientHeight;	

	getTodayStudyTime();
	getMonthStudyTime(-1);	
	getWeekCashPoint();

	
	function getWeekCashPoint()
	{
		$('#weekCashPointRankingDiv').show();
		$.ajax({
	    	type:'post',
	    	url:'/api/Ajax/GetWeekCashPoint',
	    	data: {  },
	        dataType: 'json',
	        cache: false,
	        success: function (result)
	        {	        	
	        	if(result.success)
	        	{
	        		var messageList = "";
	        		if(result.ranking.length > 0)
	        		{
	        			var userClass = "item-user-top";
		        		// 绑定消息
		        		$(result.ranking).each(function(index, item)
		        		{
		        			if(index > 2)
		        				userClass = "item-user";
		        			messageList += '<span class="ranking-item"><span class="item-index">'+(index+1)+'.</span><span class="'+userClass+'">'+item.userId+'</span><span class="item-value">'
											+ '<span ><img class="item-value-img" src="/content/image/coins0.png"></span>'
											+ '<span>' + item.CashPoint + '</span></span></span>';
		        		});        		
		        		//<span class="ranking-item"><span class="item-index">1.</span><span class="item-user-top">mdsda3993</span><span class="item-value"><span><img class="item-value-img" src="/content/image/coins0.png" /></span><span>90860</span></span></span>
	        		}
	        		else
	        		{
	        			messageList  = '<span class="ranking-item">暂无排名数据！</span>';
	        		}
	        		$('#weekCashPointRanking').html(messageList);
	        		
	        	}
	        	else
	        	{
	        		$('#weekCashPointRanking').html('<span class="ranking-item">暂无排名数据！</span>');
	        	}
	        	
	        },
	        error:function()
	        {
	        	$('#weekCashPointRanking').html('<span class="ranking-item">暂无排名数据！</span>');
	        }
	    	
	    });
	}

	
	
	function getTodayStudyTime()
	{
		
		$('#todyaStudytimeRankingDiv').show();
		$.ajax({
	    	type:'post',
	    	url: '/api/Ajax/GetStudyResultByDay',
	    	data: { ClassID: getQueryString("ClassID") },
	        dataType: 'json',
	        cache: false,
	        success: function (result)
	        {	        	
	        	if(result.success)
	        	{
	        	    var messageList = "";
	        		if (result.ranking.length > 0)
	        		{
	        			var userClass = "item-user-top";
		        		// 绑定消息
	        			$(result.ranking).each(function (index, item)
		        		{
		        			if(index > 2)
		        				userClass = "item-user";
		        			messageList += '<span class="ranking-item"><span class="item-index">' + (index + 1) + '.</span><span class="' + userClass + '">' + item.userId + '</span><span class="item-value">'
		        							+ '<span><img class="item-value-img"  src="/content/image/rankingtime.png"></span>'
		        							+ '<span>'+item.totalTime+'</span></span></span>';
		        			
		        		});
	        		}
	        		else
	        		{
	        		    messageList = '<span class="ranking-item">暂无排名数据！</span>';
	        		}
	        	
	        		$('#todyaStudytimeRanking').html(messageList);
	        		
	        	}
	        	else
	        	{	        		
	        	    $('#todyaStudytimeRanking').html('<span class="ranking-item">暂无排名数据！</span>');
	        	}
	        	
	        },
	        error:function()
	        {
	            $('#todyaStudytimeRanking').html('<span class="ranking-item">暂无排名数据！</span>');
	        }
	    	
	    });
	}	
	
	
	
	
	/**
	 * 获取某个月的学习时间排行
	 */
	function getMonthStudyTime(addMonth)
	{
		$('#monthStudytimeRankingDiv').show();
		
		$.ajax({
	    	type:'post',
	    	url: '/api/Ajax/GetStudyResultByMonth',
	    	data: { },
	        dataType: 'json',
	        cache: false,
	        success: function (result)
	        {	        	
	        	if(result.success)
	        	{
	        		var studyTimeList = "";
	        		if(result.ranking.length > 0)
	        		{
	        			var userClass = "item-user-top";
		        		// 绑定消息
		        		$(result.ranking).each(function(index, item)
		        		{
		        			if(index > 2)
		        				userClass = "item-user";
		        			studyTimeList += '<span class="ranking-item"><span class="item-index">'+(index+1)+'.</span><span class="'+userClass+'">'+item.userId+'</span><span class="item-value">'										
						        			+ '<span><img class="item-value-img"  src="/content/image/rankingtime.png"></span>'
											+ '<span>'+item.totalTime+'</span></span></span>';
		        		});
	        		}
	        		else
	        		{
	        			studyTimeList = '<span class="ranking-item">暂无排名数据！</span>';
	        		}	        		
	        		
	        		$('#monthStudytimeRanking').html(studyTimeList);
	        	}
	        	else
	        	{
	        		$('#monthStudytimeRanking').html('<span class="ranking-item">暂无排名数据！</span>');
	        	}
	        	
	        },
	        error:function()
	        {
	        	$('#monthStudytimeRanking').html('<span class="ranking-item">暂无排名数据！</span>');
	        }
	    	
	    });
	}
	function getQueryString(name) 
	{
	    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	    var r = window.location.search.substr(1).match(reg);
	    if (r!=null) 
	        return unescape(r[2]); 
	   
	    return null;
	}
	
	// 限定logo的复制数量
	var nbrOfLogo = 0;
	$("#logo_ranking").click(function(){

		//$("#noticeThrowable").appendTo($('#outsideWorld')).throwable({damping: 3, drag: true});
		
		if(nbrOfLogo++ < 3)
		{
			var cloneLogo = $("#logo_ranking").clone().appendTo($("#logo_ranking").parent());
			$(cloneLogo).throwable({
		        gravity:{x:0,y:1},
		        shape:'circle',
		        autostart:false,
		    });
		}		
	});		
   
});