
/*
 * 获取本地时间
 */
function getLocalTime(nS) {
  return new Date(parseInt(nS)).toLocaleString().replace(/:\d{1,2}$/, ' ');
}

/*
 * 格式化时间
 */
Date.prototype.Format = function (fmt) { //author: meizz 
  var o = {
    "M+": this.getMonth() + 1, //月份 
    "d+": this.getDate(), //日 
    "h+": this.getHours(), //小时 
    "m+": this.getMinutes(), //分 
    "s+": this.getSeconds(), //秒 
    "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
    "S": this.getMilliseconds() //毫秒 
  };
  if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
  for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
  return fmt;
};

/*
 * value 为datetime对象的json格式
 */
function renderDateTime(value) {
  if (value == null || value.time == undefined || value.time == null) {
    return '未知';
  }
  else {
    return new Date(value.time).Format("yyyy-MM-dd hh:mm:ss");
  }
}
/**
 * 为获取mp3格式化单词，即去掉正则匹配中的那些字符new RegExp("[\\/?……()（）？]")
 * 注：该正则表达应与后台的音频校验相同
 * @param voc
 * @returns {String}
 */
function clearSpellingForAudio(voc) {
  var pattern = new RegExp("[\\/?……？]");
  var resultVoc = "";
  var currentLetter = "";
  for (var i = 0; i < voc.length; i++) {
    currentLetter = voc.substr(i, 1);
    resultVoc += currentLetter.replace(pattern, '_');
  }
  return resultVoc;
}

/**
 * 清除字符串中的非英文字符，主要用于第一次判断拼写是否正确
 * 注：clearNonEnglishSymbolForString 会忽略字符串首的空格，同时清除字符串中间多余的空格。
 */
function clearNonEnglishSymbolForString(voc) {
  var pattern = new RegExp("[\\[\\]·<>~……*;【】‘；：”“。●，、？]");
  //var pattern = new RegExp("[`~!@#$^&*()=|{}':;',\\[\\].·<>/?~！@#￥……&*（）&;|{}【】‘；：”“'。●，、？ ]");
  var resultVoc = "";
  var currentLetter = "";
  for (var i = 0; i < voc.length; i++) {
    currentLetter = voc.substr(i, 1);
    if (currentLetter == " ") {
      // 检查resultVoc最后一位是否为空格（resultVoc最后一位也就是新空格的前一位），如果不是空格，加入空格，如果是，则跳过。
      // 目的是为了清除字符串中间多余的空格。
      if (i > 0 && resultVoc.substr(resultVoc.length - 1, 1) != " ") {
        resultVoc = resultVoc + " ";
      }
    }
    else {
      resultVoc = resultVoc + currentLetter.replace(pattern, '_');
    }
  }
  return resultVoc;
}

/**
 * 清除字符串中的英文字符，主要用于第一次拼写不正确时，对判断标准的单词进行一些英文字符的移除，如.?!，然后再次进行拼写判断。
 * 
 */
function clearEnglishSymbolForString(voc) {
  var pattern = new RegExp("[.!?,:;…~()]");
  //var pattern = new RegExp("[`~!@#$^&*()=|{}':;',\\[\\].·<>/?~！@#￥……&*（）&;|{}【】‘；：”“'。●，、？ ]");
  var resultVoc = "";
  var currentLetter = "";
  for (var i = 0; i < voc.length; i++) {
    currentLetter = voc.substr(i, 1);
    if (currentLetter == " ") {
      // 检查resultVoc最后一位是否为空格（resultVoc最后一位也就是新空格的前一位），如果不是空格，加入空格，如果是，则跳过。
      // 目的是为了清除字符串中间多余的空格。
      if (i > 0 && resultVoc.substr(resultVoc.length - 1, 1) != " ") {
        resultVoc = resultVoc + " ";
      }
    }
    else {
      // 一小部分的符号用空格代替，如反斜杠 / （也就是说遇到 / 时，用户打空格也会判断拼写正确的）
      if (currentLetter == "/") {
        resultVoc = resultVoc + " ";
      }
      else {
        resultVoc = resultVoc + currentLetter.replace(pattern, '');
      }
    }
  }
  return resultVoc;
}

/*
 * clearStringForSpeak 会忽略字符串首的空格
 */
function clearStringForSpeak(voc) {
  var pattern = new RegExp("[*-.!?// ]");
  //var pattern = new RegExp("[`~!@#$^&*()=|{}':;',\\[\\].·<>/?~！@#￥……&*（）&;|{}【】‘；：”“'。●，、？ ]");
  var resultVoc = "";

  var currentLetter = "";
  for (var i = 0; i < voc.length; i++) {
    currentLetter = voc.substr(i, 1);
    resultVoc = resultVoc + currentLetter.replace(pattern, '');
  }
  return resultVoc;
}


/*
 * clearExtraBlankString:将单词中的多余空格除去
 */
function clearExtraBlankString(voc) {
  var resultVoc = "";
  var currentLetter = "";
  for (var i = 0; i < voc.length; i++) {
    currentLetter = voc.substr(i, 1);
    if (currentLetter == " ") {
      // 检查resultVoc最后一位是否为空格（resultVoc最后一位也就是新空格的前一位），如果不是空格，加入空格，如果是，则跳过
      if (i > 0 && resultVoc.substr(resultVoc.length - 1, 1) != " ") {
        resultVoc = resultVoc + " ";
      }
    }
    else {
      resultVoc = resultVoc + currentLetter;
    }

  }
  return resultVoc;
}

/**
 * 获取 standardStr 与 inputStr 比对中第一个不一样的字符的位置
 * @param standardStr
 * @param inputStr
 * @returns {Number}
 */
function getIndexOfFirstErorrLetter(standardStr, inputStr) {
  // 定义出错的字符的 index， 在哪个字符比对出错，则返回该 index
  var errorIndex = -1;
  // standardLength： 比对标准的字符串长度
  var standardLength = standardStr.length;
  // inputLength：给定输入的字符串的长度
  var inputLength = inputStr.length;

  // 循环进行字符的比对，如果出错，则记录出错字符的 index，并跳出比对
  for (var i = 0; i < standardLength; i++) {
    if (inputStr[i] == null || standardStr[i] != inputStr[i]) {
      errorIndex = i;
      break;
    }
  }

  if (errorIndex == -1 && inputLength > standardLength) {
    errorIndex = standardLength - 1;
  }

  return errorIndex;
}

/*
 * 对标准单词与用户输入进行多级比对，以判断用户的输入是否正确。
 * 如果正确返回-1, 如果不正确则返回出错的index位置
 */
function compareUserInputAndStandarSpelling(stardard, userInput) {

  // 1. 一级比对
  var errorIndex = getIndexOfFirstErorrLetter(stardard, $.trim(clearExtraBlankString(userInput)));

  // 2. 二级比对
  // 如果是最后一个字符出错时，判断这个字符是不是非英文字符，如标点等。如果是，那么忽略最后一个字符的错误
  // 其实二级比对的工作可由三级比对完成，但考虑到一般应用场景，二级比对即可完成判断，可以大幅提高效率，因此保留该二级比对
  if (errorIndex == stardard.length - 1 && !isEnglishLetter(stardard.charAt(errorIndex))) {
    errorIndex = -1;
  }

  // 3. 三级比对
  // 如果这次判断的结果为用户拼写错误，那么分别移除判断标准，及用户输入单词中的英文符号，再次进行判断
  if (errorIndex != -1) {
    errorIndex = getIndexOfFirstErorrLetter($.trim(clearEnglishSymbolForString(stardard)),
      $.trim(clearEnglishSymbolForString(userInput)));
  }

  return errorIndex;
}

/*
 * 判断localstorage中的用户与当前登陆用户是否是同一个
 */
function isSameUserInLocalStorage() {
  if (window.localStorage) {
    if (window.localStorage.userinfo) {
      var userId = $('#currentUserId').val();
      // 判断是不是同一个用户
      var userinfo = JSON.parse(window.localStorage.userinfo);
      if (userinfo.userId && userinfo.userId == userId)
        return true;
      else
        return false;
    }
    else {
      return false;
    }
  }
  else {
    return false;
  }
}

/**
 * 向textarea中插入字符
 */
function insertAtCaret(areaId, text) {
  var txtarea = document.getElementById(areaId);
  var scrollPos = txtarea.scrollTop;
  var strPos = 0;
  var br = ((txtarea.selectionStart || txtarea.selectionStart == '0') ?
    "ff" : (document.selection ? "ie" : false));
  if (br == "ie") {
    txtarea.focus();
    var range = document.selection.createRange();
    range.moveStart('character', -txtarea.value.length);
    strPos = range.text.length;
  }
  else if (br == "ff") strPos = txtarea.selectionStart;

  var front = (txtarea.value).substring(0, strPos);
  var back = (txtarea.value).substring(strPos, txtarea.value.length);
  txtarea.value = front + text + back;
  strPos = strPos + text.length;
  if (br == "ie") {
    txtarea.focus();
    range = document.selection.createRange();
    range.moveStart('character', -txtarea.value.length);
    range.moveStart('character', strPos);
    range.moveEnd('character', 0);
    range.select();
  }
  else if (br == "ff") {
    txtarea.selectionStart = strPos;
    txtarea.selectionEnd = strPos;
    txtarea.focus();
  }
  txtarea.scrollTop = scrollPos;
};

function getTestType(value) {
  testType = "学习测试";

  if (value == 1) {
    testType = "单元测试";
  }
  else if (value == 2) {
    testType = "学前测试";
  }
  else if (value == 3) {
    testType = "学后测试";
  }
  else if (value == 4) {
    testType = "混合测试";
  }
  else if (value == 5) {
    testType = "自主测试";
  }
  else if (value == 6) {
    testType = "听读训练";
  }
  else if (value == 8) {
    testType = "错词强化测";
  }
  else if (value == -1) {
    testType = "全书测试";
  }

  return testType;
}

/*
 * 从localStorage中获取是否在当前电脑进行跟读的信息follow_speak_on_this
 */
function getFollowSpeakOnThisInLocalStorage(currentUserId) {
  var followSpeakOnThis = 0;
  // 获取学生的ID，如果ID与当前学生相同，则获取该用户的follow_speak_on_this
  if (window.localStorage) {
    if (window.localStorage.userinfo) {
      var userinfo = JSON.parse(window.localStorage.userinfo);
      // localstorage里有该用户，获取该用户的follow_speak_on_this
      if (userinfo.userId == currentUserId) {
        if (userinfo.follow_speak_on_this) {
          followSpeakOnThis = userinfo.follow_speak_on_this;
        }
        else {
          userinfo.userId = currentUserId;
          userinfo.follow_speak_on_this = followSpeakOnThis;
          window.localStorage.userinfo = JSON.stringify(userinfo);
        }
      }
      else {
        // 若localstorage中没有该用户，则生成一个新的用户信息
        var userinfo = new Object();
        userinfo.userId = currentUserId;
        userinfo.follow_speak_on_this = followSpeakOnThis;
        window.localStorage.userinfo = JSON.stringify(userinfo);
      }
    }
    else {
      // localstorage中没有用户信息，生成一个新的用户信息
      var userinfo = new Object();
      userinfo.userId = currentUserId;
      userinfo.follow_speak_on_this = followSpeakOnThis;
      window.localStorage.userinfo = JSON.stringify(userinfo);
    }
  }

  return followSpeakOnThis;
}

/*
 * 向localStorage中设置是否在当前电脑进行跟读的信息follow_speak_on_this
 */
function setFollowSpeakOnThisInLocalStorage(currentUserId, followSpeakOnThis) {
  // 获取学生的ID，如果ID与当前学生相同，则获取该用户的follow_speak_on_this
  if (window.localStorage) {
    var userinfo = new Object();
    if (window.localStorage.userinfo) {
      userinfo = JSON.parse(window.localStorage.userinfo);
      // localstorage里有该用户，获取该用户的follow_speak_on_this
      if (userinfo.userId == currentUserId) {
        userinfo.follow_speak_on_this = followSpeakOnThis;
      }
      else {
        // 若localstorage中没有该用户，则生成一个新的用户信息					
        userinfo.userId = currentUserId;
        userinfo.follow_speak_on_this = followSpeakOnThis;
      }
    }
    else {
      // localstorage中没有用户信息，生成一个新的用户信息				
      userinfo.userId = currentUserId;
      userinfo.follow_speak_on_this = followSpeakOnThis;
    }
    window.localStorage.userinfo = JSON.stringify(userinfo);
  }
}

/**
 * 判断一个字符是否是英文字母
 */
function isEnglishLetter(letter) {
  var isEnglish = true;
  if (!((letter > 'A' && letter <= 'Z') || (letter > 'a' && letter < 'z'))) {
    isEnglish = false;
  }
  return isEnglish;
}

/**
 * asyncLoadJs 用于异步延时加载 js 以减小服务器负担，window.attachEvent 解决了阻塞 onload 事件触发的问题；
 * async属性是HTML5新增的。表示 js 它将在下载后尽快执行，不能保证脚本会按顺序执行。它们将在 onload 事件之前完成。
 */
function asyncLoadJs(fileUrlArray) {
  function async_load() {
    for (var i = 0; i < fileUrlArray.length; i++) {
      var scriptElement = document.createElement('script');
      scriptElement.type = 'text/javascript';
      scriptElement.async = true;
      scriptElement.src = fileUrlArray[i];
      var x = document.getElementsByTagName('script')[0];
      x.parentNode.insertBefore(scriptElement, x);
    }
  }

  if (window.attachEvent)
    window.attachEvent('onload', async_load);
  else
    window.addEventListener('load', async_load, false);
}

