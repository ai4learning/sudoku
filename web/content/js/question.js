var _audio_url = "/soundfile/";

function Question(questionInfo, type, index)
{
	if(questionInfo != null)
	{
		this.question = questionInfo.question;
		this.choices = questionInfo.choices;
		this.answerIndex = questionInfo.answerIndex;
		this.userChoiceIndex = questionInfo.userChoiceIndex;
		this.spelling = questionInfo.spelling;
		this.vocCode = questionInfo.vocCode;
	}
	else
	{
		this.question = new Object();
		this.choices = [];
		this.answerIndex = null;
		this.userChoiceIndex = null;
		this.spelling = null;
		this.vocCode = null;
	}
	
	this.qType = type;
	this.qSeq = index;
	this.failTimes = 0;
	
	this.userChoiceIndex = null;
	this.inputName = this.qType+'_'+this.vocCode;
}

/*
 * 创建单个问题的html
 */
function createSingleQuestionHtml(question)
{
    var qName = question.qType + '_' + question.vocCode;

	var questionStr = '<table><thead><td colspan=2>' + question.question + '</td></thead><tbody>'
						+ '<tr><td><input type="radio" name="' + qName + '" value="A" /></td><td class="questionChoices">' + question.choices["A"] + '</td></tr>'
						+ '<tr><td><input type="radio" name="' + qName + '" value="B" /></td><td class="questionChoices">' + question.choices["B"] + '</td></tr>'
						+ '<tr><td><input type="radio" name="' + qName + '" value="C" /></td><td class="questionChoices">' + question.choices["C"] + '</td></tr>'
						+ '<tr><td><input type="radio" name="' + qName + '" value="D" /></td><td class="questionChoices">' + question.choices["D"] + '</td></tr></tbody></table>';
	return questionStr;				
}

/*
 * 创建听力的html
 */
function createSingleQuestionWithListeningHtml(question)
{
	var qName = question.qType+'_'+question.vocCode;
	
	var wordSound = '<audio id = "audio_inTest_' + question.vocCode + '">'
			+ '<source src="' + _audio_url + question.spelling.substr(0, 1).toUpperCase() + '/'  + $.trim(clearSpellingForAudio(question.spelling))
            + '.mp3" type="audio/mpeg"><embed id= "audio_inTest_embed_' + question.vocCode + '"'
            + ' src="' + _audio_url + question.spelling.substr(0, 1).toUpperCase() + '/' + $.trim(clearSpellingForAudio(question.spelling))
            + '.mp3" autostart=false loop=false hidden=true /></audio>';

	var questionStr = '<table><thead><td colspan=2 class="wordAudio_symbol_inTest"><img class="audioSymbol" spelling="' + question.spelling + '" voccode="' + question.vocCode + '" alt="volume" src="/content/image/listenagin_pre.png" >'
						+ '<span class="audio_inTest">' + wordSound + '</span></td></thead><tbody>'
						+ '<tr><td><input type="radio" name="' + qName + '" value="A" /></td><td class="questionChoices">' + question.choices["A"] + '</td></tr>'
						+ '<tr><td><input type="radio" name="' + qName + '" value="B" /></td><td class="questionChoices">' + question.choices["B"] + '</td></tr>'
						+ '<tr><td><input type="radio" name="' + qName + '" value="C" /></td><td class="questionChoices">' + question.choices["C"] + '</td></tr>'
						+ '<tr><td><input type="radio" name="' + qName + '" value="D" /></td><td class="questionChoices">' + question.choices["D"] + '</td></tr></tbody></table>';
			
	
	return questionStr;
}

/*
 * 创建听写的html
 */
function createSingleQuestionWithListenToWriteHtml(question)
{
	var qName = question.qType+'_'+question.vocCode;
	
	var wordSound = '<audio id = "audio2_inTest_' + question.vocCode + '">'
			+ '<source src="' + _audio_url + question.spelling.substr(0, 1).toUpperCase() + '/'  + $.trim(clearSpellingForAudio(question.spelling))
            + '.mp3" type="audio/mpeg"><embed id= "audio_inTest_embed_' + question.vocCode + '"'
            + 'src="' + _audio_url + question.spelling.substr(0, 1).toUpperCase() + '/' + $.trim(clearSpellingForAudio(question.spelling))
            + '.mp3" autostart=false loop=false hidden=true /></audio>';
	var questionStr = '<table class="listenToWriteTable"><thead><td class="wordAudio_symbol_inTest"><img class="audioSymbol" spelling="' + question.spelling + '" voccode="' + question.vocCode + '" alt="volume" src="/content/image/volume-medium.png" ><span name="spanMeaning">"' + question.question + '"</span>'
						+ '<span class="audio_inTest">' + wordSound + '</span></td></thead><tbody>'
						+ '<tr><td><input type="text" name="' + qName + '" /></td></tr></tbody></table>';
	return questionStr;
}

function increaseResultPoint(originalValue, reward, divId)
{
	var currentVal = originalValue;
	var endVal = originalValue+reward;
	var Musiccoin = document.getElementById("Coindrop");
	
	// 逐个增加金币
	var pointIncrementHandle = setInterval(function ()
	{
	    var resultCoin = 0;
        if (currentVal === endVal)
        {
            clearInterval(pointIncrementHandle);
            
            if(endVal >= 90)
    		{
    			if(endVal == 100)
    			{
			    	/*Music100.play();*/
					
    			    $('#resultImage').attr('src', '/content/image/point100.png').css({'left':200, 'top':85}).fadeIn();
					$('#goldpoint').attr('src', '/content/image/goldpoints.gif').css({'left':0, 'top':44}).fadeIn();
					Musiccoin.play();
			    
					resultCoin = 10;
					$('#remark100').css("display", "inline-block").html("学界巅峰！地球人已经无法阻止你成神的脚步了！");
					
				    $('#btnClose').css({'margin-left':70}).show();	
    				
    			}
				else if(endVal >= 95 && endVal <100)
    			{
    			    $('#resultImage').attr('src', '/content/image/point95jia.png').css({'left':90}).fadeIn();
					$('#goldpoint').attr('src', '/content/image/goldpoints.gif').css({'left':0, 'top':44}).fadeIn();
					Musiccoin.play();
    				$('#btnBackToCheck').show();
    				
				    $('#remark').css("display", "inline-block").html("你是一个隐藏在民间的绝世高手，再努力一下的话就可以成神了！");
					
				    $('#btnClose').show();
				    resultCoin = 7;
    			}    	
    			else
    			{
    			    $('#resultImage').attr('src', '/content/image/point90.png').css({'left':120}).fadeIn();
					$('#goldpoint').attr('src', '/content/image/goldpoints.gif').css({'left':0, 'top':44}).fadeIn();
					Musiccoin.play();
    				$('#btnBackToCheck').show();
    				
				    $('#remark').css("display", "inline-block").html("你打败了学霸，足以称王称帝，但是不要骄傲，继续修行就可以成为一个圣人了。");
					
				    $('#btnClose').show();
				    resultCoin = 5;
    			}    			
    		}
			else if(endVal >= 80 && endVal <90)
			{
					$('#resultImage').attr('src', '/content/image/point80.png').css({'left':88}).fadeIn();
					$('#goldpoint').attr('src', '/content/image/goldpoints.gif').css({'left':0, 'top':44}).fadeIn();
					Musiccoin.play();
    				$('#btnBackToCheck').show();    				
				    $('#remark').css("display", "inline-block").html("你已经成为了一个让众学渣仰视的学霸了，继续加油，称王称帝指日可待！");					
				    $('#btnClose').show();
				    if (endVal >= 80 && endVal < 85) { resultCoin = 1; }
				    if (endVal >= 85 && endVal < 90) { resultCoin = 3; }
			}
			else if(endVal >= 70 && endVal <80)
			{
					$('#resultImage').attr('src', '/content/image/point70.png').css({'left':110}).fadeIn();
					$('#goldpoint').attr('src', '/content/image/goldpoints.gif').css({'left':0, 'top':44}).fadeIn();
					Musiccoin.play();
    				$('#btnBackToCheck').show();
    				
				    $('#remark').css("display", "inline-block").html("你也是蛮屌的，再往上一步就能成为学霸了！");
					
    				$('#btnClose').show();	
			}
			else if(endVal >= 60 && endVal <70)
			{
					$('#resultImage').attr('src', '/content/image/point60.png').css({'left':90}).fadeIn();
					$('#goldpoint').attr('src', '/content/image/goldpoints.gif').css({'left':0, 'top':44}).fadeIn();
					Musiccoin.play();
    				$('#btnBackToCheck').show();
    				
				    $('#remark').css("display", "inline-block").html("你虽然成绩平庸，但是潜力无限，多努力一下的话就可以更上一层楼了！");
					
    				$('#btnClose').show();	
			}
			else if(endVal >= 50 && endVal <60)
			{
					$('#resultImage').attr('src', '/content/image/point50.png').css({'left':120}).fadeIn();
    				$('#btnBackToCheck').show();
    				
				    $('#remark').css("display", "inline-block").html("没及格？你上课的时候是不是吃瓜去了？");
					
    				$('#btnClose').show();	
			}
			else if(endVal >= 30 && endVal <50)
			{
					$('#resultImage').attr('src', '/content/image/point30.png').css({'left':110}).fadeIn();
    			    $('#remark').css("display", "inline-block").html("你这渣渣！丢人现眼！旁边吃瓜的哥们都比你考的好！");
				    $('#btnBackToCheck').show();	
    				$('#btnClose').show();	
			}
			else if(endVal >= 10 && endVal <30)
			{
				    $('#resultImage').attr('src', '/content/image/point10.png').css({'left':90, 'top':155}).fadeIn();
    				$('#remark').css("display", "inline-block").html("才考了这么点分？当个学渣都不够格啊。");
				    $('#btnBackToCheck').show();	
    				$('#btnClose').show();	
			}
    		else
            {
              
                $('#resultImage').attr('src', '/content/image/point10jian.png').css({'left':135}).fadeIn();
    			$('#remark').css("display", "inline-block").html("不忍直视！你是不识字吗？你这文盲！");
			    $('#btnBackToCheck').show();	
    			$('#btnClose').show();
			}
            $('#resultCoin').html(resultCoin);
        }
        else
        {
            currentVal++;
            $('#'+divId).html(currentVal);
        }
    }, 50);
}

/*
 * 获取radio input选中的值
 */
function getRadioValue(inputName){
    var obj;    
    obj=document.getElementsByName(inputName);
    if(obj!=null){
        var i;
        for(i=0;i<obj.length;i++){
            if(obj[i].checked){
                return obj[i].value;            
            }
        }
    }
    return null;
}

/**
 * 把ABCD转化为数字序号
 * @param letter
 * @returns
 */
function getDigitalFromABCD(letter)
{
    switch(letter)
    {
        case 'A':return 0;
        break;
        case 'B':return 1;
        break;
        case 'C':return 2;
        break;
        case 'D':return 3;
        break;
        default:return null;
    }
}

/*
 * 检查试卷，看得了多少分
 */
function checkPaper(allQuestions)
{
	var errorNbr = 0;
	$(allQuestions).each(function(index, item)
	{
		var userAnswer = '';
		if(item.qType != "dataFromListenToWrite")
		{
			userAnswer = getRadioValue(item.inputName);
		}
		else if(item.qType == "dataFromListenToWrite")
		{
			if(compareUserInputAndStandarSpelling(item.spelling, $('input[name='+item.inputName+']').val()) == -1)
				userAnswer = "A";		// A是听写题的默认答案
		}		
		if(userAnswer != item.answerIndex)
		{
			errorNbr++;				
			if(item.qType != "dataFromListenToWrite")
			{
			    $('.question-area table thead').eq(index).attr('isright', '0').css("background", "#fff").siblings('tbody').find('tr').eq(getDigitalFromABCD(item.answerIndex)).css("color", "#FF0000");			
			}
			else if(item.qType == "dataFromListenToWrite")
			{
				var fontSize = "25px";
				if(item.spelling.length > 13)
					fontSize = "18px";
				$('.question-area table thead').eq(index).attr('isright', '0').css("background", "#c9c9c9").siblings('tbody').find('input').eq(0).css({'width':'47%', 'font-size':fontSize}).after('<input type="text" style="width:48%; font-size:'+fontSize+'; color:#d70c18; margin-left:2px;" value="'+ item.spelling + '" />');
			}
		}
	});
	
	var socre = parseInt(100 - (100/allQuestions.length*errorNbr));
	return socre > 0 ? socre : 0;
}

function hideCorrectQuestions() {
	$('.question-area table thead').each(function(index, item){
		if($('.question-area table thead').eq(index).attr('isright') == undefined || $('.question-area table thead').eq(index).attr('isright') != '0') {
			$(this).parent('table').hide();
		}
	});
}
$(document).ready(function()
{
	// 扩展choice item的可选范围，不仅仅是仅点radio才可选
	$('.questionChoices').live('click',function()	{		
		var currentRadio = $(this).siblings().eq(0).children().eq(0);
		if($(currentRadio).attr("type") == "radio")
		{			
			$(currentRadio).attr('checked', true);
		}
	});
	
	/*
	 * 单词发音的点击事件
	 * 扩展发音的可选范围，不仅仅是点图片才可发音
	 */
    //var clickCount = 0;
	$('.wordAudio_symbol_inTest').live('click', function()
	{
		var audioObject = $(this).find('audio').get(0);
	
		
		// 先获取当前.wordAudio_symbol_inTest的spelling属性值，然后根据该值找到对应的audio
		if ($.browser.msie)
        {		        				
            if ($.browser.version >= 9.0)
            {		        	            	
            	$(this).children('.audio_inTest').eq(0).children('audio').get(0).play();
            }
            else
            {       
            	//alert('暂不支技IE9以下老版本的IE浏览器，请使用IE9，IE10或谷歌或360安全浏览器。');
            	var audioId = "audio_inTest_embed_" + $(this).find('img').eq(0).attr("voccode");
            	try
            	{
            		document.getElementById(audioId).play();
            	}
            	catch(e)
            	{
            		$('#'+ audioId).attr('autostart', true);
            	}
            }
        }
        else
        {        	
        	$(this).children('.audio_inTest').eq(0).children('audio').get(0).play();
		}	   
});
	
	var lastFouceInputNameTemp = "";
	function pronouceAfterFocus()
	{
		$(this).parents('table').eq(0).find('.wordAudio_symbol_inTest').click();
		$(this).unbind("focus");
		$('#dataFromListenToWrite input[name='+lastFouceInputNameTemp+']').focus(pronouceAfterFocus);
		lastFouceInputNameTemp = $(this).attr("name");
	}
	
	// 绑定input获取焦点后的发音事件
	$('#dataFromListenToWrite input[type=text]').live('focus', pronouceAfterFocus);
	
	// 绑定到#wsWriting的键盘事件
	function dataFromListenToWriteKeyUp(e) 
	{
		if (e.keyCode == 17)
		{
			// 找到光标所在的位置，然后发音			
			$(this).parents('table').eq(0).find('.wordAudio_symbol_inTest').eq(0).click();
		}		
	}
	
	$('#dataFromListenToWrite').delegate("input", "keyup", dataFromListenToWriteKeyUp);
	
	$('#btnBackToCheck').click(function()
	{
		// 隐藏正确的单词
		hideCorrectQuestions();
		$('#resultArea').fadeOut();
		$('#overlay_loading').fadeOut();
	});	
	$('#btnClose').click(function(){window.close();});
	
	/**
	 * 设置clock的位置
	 */
	function setClockPosition()
	{
		var paperPosition = $('#testPaper').position();
		var paperWidth = $('#testPaper').width();
		$('#clock').css({'left':paperPosition.left+paperWidth});
	}
	setClockPosition();
	
	/**
	 * 为 dataFromListenToWrite 绑定回车事件，用来切换单词
	 */
	$(document).delegate("#dataFromListenToWrite", "keypress", keypressFuncForPaper);
	function keypressFuncForPaper(event)
	{
		if(event.keyCode == 13)		// 13：回车键
		{
			var isFound = false;
			$('#dataFromListenToWrite').find('input').each(function(index, item){
				if(!isFound)
				{
					if($(item).is(":focus"))
						isFound = true;
				}
				else
				{
					$(item).focus();
					return false;
				}
				
			});
		}
	}
});
