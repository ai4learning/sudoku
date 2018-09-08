$(document).ready(function () {
  // allQuestions:保存了所有问题的数组
  var allQuestions = new Array();
  // 标准测试时间，根据题目数量设定，单位秒
  var standardDuration = null;
  // 每道题的测试时间，单位：秒
  var SINGLE_TEST_TIME = 6;
  // 测试倒计时
  var timeCounter = 600;
  // 交卷次数限制
  var submitTimes = 0;
  // 错题数
  var errorNbr = 0;

  var secondleft;
  var secondStr = "0";
  var minuteleft;
  var minuteStr;
  var hourleft;
  var hourStr;

  /*
  * 获取url的参数
  */
  function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null)
      return unescape(r[2]);

    return null;
  }

  function runClockCount() {
    --secondleft;
    if (secondleft < 0) {
      secondleft = 60 + secondleft;
      minuteleft = minuteleft - 1;
    }
    if (minuteleft < 0) {
      minuteleft = 60 + minuteleft;
      hourleft = hourleft - 1;
    }
    if (hourleft < 0) {
      hourleft = 0;
    }

    minuteStr = minuteleft >= 10 ? minuteleft : ("0" + minuteleft);
    secondStr = secondleft >= 10 ? secondleft : ("0" + secondleft);
    if (hourleft > 0) {
      hourStr = hourleft >= 10 ? hourleft : ("0" + hourleft);
    }
    else {
      hourStr = "00";
    }
    $("#clock").html(hourStr + ":" + minuteStr + ":" + secondStr);

  }

  /*
  * 加载试卷
  */
  function loadingTestPaper() {
    //$('#overlay_loading').css({'width':document.documentElement.clientWidth, 'height':document.documentElement.clientHeight}).show();
    $('#overlay_loading').fadeIn();
    var moduleCode = getQueryString("moduleCode");
    var unit = getQueryString("unit");
    if (moduleCode != null && moduleCode != "" && unit != null && unit != "") {

      $.ajax({
        type: 'get',
        url: '/api/Ajax/GetUnitExam',
        data: { unitNbr: unit, moduleCode: moduleCode },
        dataType: 'json',
        cache: false,
        success: function (result) {
          // 删除加载画面
          $('#overlay_loading').fadeOut();
          if (result.success) {
            // 听写
            allQuestions = new Array();
            if (result.data.dataListen2Ch != null && result.data.dataListen2Ch.length > 0) {
              var dataListen2ChHtml = "";
              $(result.data.dataListen2Ch).each(function (index, item) {
                var q = new Question(item, "dataListen2Ch", index);
                dataListen2ChHtml += createSingleQuestionWithListeningHtml(q);
                // 记录该问题
                allQuestions.push(q);
              });

              $('#dataFromListenToCh').html(dataListen2ChHtml);
              $('#dataFromListenToChDiv').show();
            } else {
              $('#dataFromListenToChDiv').hide();
            }

            // 英译中
            if (result.data.dataEn2Ch != null && result.data.dataEn2Ch.length > 0) {
              var dataEn2ChHtml = "";

              $(result.data.dataEn2Ch).each(function (index, item) {
                var q = new Question(item, "dataEn2Ch", index);
                dataEn2ChHtml += createSingleQuestionHtml(q);
                // 记录该问题
                allQuestions.push(q);
              });

              $('#dataFromEnToCh').html(dataEn2ChHtml);
              $('#dataFromEnToChDiv').show();
            } else {
              $('#dataFromEnToChDiv').hide();
            }


            // 中译英
            if (result.data.dataCh2En != null && result.data.dataCh2En.length > 0) {
              var dataCh2EnHtml = "";

              $(result.data.dataCh2En).each(function (index, item) {
                var q = new Question(item, "dataCh2En", index);
                dataCh2EnHtml += createSingleQuestionHtml(q);
                // 记录该问题
                allQuestions.push(q);
              });

              $('#dataFromChToEn').html(dataCh2EnHtml);
              $('#dataFromChToEnDiv').show();

            } else {
              $('#dataFromChToEnDiv').hide();
            }
            // 中译英
            // 听写		        		


            if (allQuestions.length > 0) {
              standardDuration = SINGLE_TEST_TIME * allQuestions.length;	// 测试时间为每道题60秒*题数
              secondleft = standardDuration % 60 + 1;
              minuteleft = parseInt((standardDuration / 60) % 60);
              hourleft = parseInt(standardDuration / 3600);
              timeCounter = standardDuration;
              // 开启倒计时
              startTestTimer();
            }
            else {
              standardDuration = 100;
              timeCounter = standardDuration;
            }
          }
          else {
            //alert(result.data.msg);
            $('#btnPaperHandOn').hide();
            $('#btnPaperExit').show().click(function () { window.close(); });
            // 删除加载画面
            $('#overlay_loading').fadeOut();
            $('#erorr_info').html("这个单元没有单词啊？看来不需要测试了  ^_^ ").show();
          }

        },
        error: function () {
          //alert('网断了？!');
        }
      });
    }
    else {
      $('#btnPaperHandOn').hide();
      $('#btnPaperExit').show().click(function () { window.close(); });
      // 删除加载画面
      $('#overlay_loading').fadeOut();
      $('#erorr_info').html("本次测试已结束，下次再接再励！").show();
    }


  }

  loadingTestPaper();

  function checkTestTimeLeft() {
    runClockCount();

    if (timeCounter == 0) {
      $('#btnPaperHandOn').click();
    }
    else {
      // 倒计时-1
      timeCounter = timeCounter - 1;
    }
  }

  var testTimerID = null;

  function startTestTimer() {
    testTimerID = setInterval(checkTestTimeLeft, 1000);
  }

  $('#btnPaperHandOn').click(function () {
    window.clearInterval(testTimerID);
    // 计算分数
    if (allQuestions.length > 0 && submitTimes == 0) {
      submitTimes++;
      var score = checkPaper(allQuestions);
      var moduleCode = getQueryString("moduleCode");
      var unitNbr = getQueryString("unit");
      var realDuration = standardDuration - timeCounter;
      var standardDurationNew = standardDuration;

      $(this).find('input').eq(0).val("关闭页面");
      $(this).click(function () { window.close(); });
      window.scrollTo(0, 0);
      var windowWidth = document.documentElement.clientWidth;
      var resultArea = $('#resultArea');
      var resultDivWidth = $(resultArea).outerWidth();
      resultArea.show();
      $(resultArea).css('left', (windowWidth - resultDivWidth) / 2);
      $('#overlay_loading').fadeIn(function () { $('#resultArea').slideDown(); });
      increaseResultPoint(0, score, "resultPoint");
      var resultCoin = 0;
      if (score == 100) {
        resultCoin = 10;
      }
      else if (score >= 95 && score < 100) {
        resultCoin = 7;
      }
      else if (score >= 90 && score < 95) {
        resultCoin = 5;
      }
      else if (score >= 80 && score < 85) {
        resultCoin = 1;

      } else if (score >= 85 && score < 90) {
        resultCoin = 3;
      }
      $.ajax({
        url: '/api/Ajax/AjaxSaveMutiTest',
        type: 'post',
        data: { moduleCode: moduleCode, resultScore: score, testType: 1, unitNbr: unitNbr, realDuration: realDuration, standardDuration: standardDurationNew, errorNbr: errorNbr, CashPoint: resultCoin, cashPointType: 4 },
        dataType: 'json',
        cache: false,
        success: function (result) {
        },
        error: function () {
          alert('network error');
        }
      });

    }
    else {
      if (submitTimes > 0) {
        //alert('已经交过一次试卷了，请不要重复提交！');
      }
      else
        alert('试卷为空');
    }
  });
});