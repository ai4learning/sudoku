export function getQueryString(name, url) {
  url = (url == null) ? window.location.href : url;
  url = url.split('#')[0];

  var reg = new RegExp("(^|\\?|&)" + name + "=([^&]*)(\\s|&|$)", "i");
  return reg.test(url) ? decodeURIComponent(RegExp.$2.replace(/\+/g, " ")) : '';
}

function zero(n) {
  n = parseInt(n, 10)
  if (n > 0) {
    if (n <= 9) {
      return "0" + n
    } else {
      return n + ""
    }
  } else {
    return "00"
  }
}

export function formatTime(time) {
  time = parseInt(time, 10)
  let sec = zero(time % 60)
  let mini = zero((time / 60) % 60)
  let hour = zero((time / 3600) % 24)
  return hour + ":" + mini + ":" + sec 
}

export function clearSpellingForAudio(b) {
  for (var c = RegExp("[\\/?\u2026\u2026\uff1f]"), a = "", e, d = 0; d < b.length; d++)
    e = b.substr(d, 1),
      a += e.replace(c, "_");
  return a
}

/* istanbul ignore next */
export function hasClass(el, cls) {
  if (!el || !cls) return false;
  if (cls.indexOf(' ') !== -1) throw new Error('className should not contain space.');
  if (el.classList) {
    return el.classList.contains(cls);
  } else {
    return (' ' + el.className + ' ').indexOf(' ' + cls + ' ') > -1;
  }
}

/* istanbul ignore next */
export function addClass(el, cls) {
  if (!el) return;
  var curClass = el.className;
  var classes = (cls || '').split(' ');

  for (var i = 0, j = classes.length; i < j; i++) {
    var clsName = classes[i];
    if (!clsName) continue;

    if (el.classList) {
      el.classList.add(clsName);
    } else {
      if (!hasClass(el, clsName)) {
        curClass += ' ' + clsName;
      }
    }
  }
  if (!el.classList) {
    el.className = curClass;
  }
}

/* istanbul ignore next */
export function removeClass(el, cls) {
  if (!el || !cls) return;
  var classes = cls.split(' ');
  var curClass = ' ' + el.className + ' ';

  for (var i = 0, j = classes.length; i < j; i++) {
    var clsName = classes[i];
    if (!clsName) continue;

    if (el.classList) {
      el.classList.remove(clsName);
    } else {
      if (hasClass(el, clsName)) {
        curClass = curClass.replace(' ' + clsName + ' ', ' ');
      }
    }
  }
  if (!el.classList) {
    el.className = trim(curClass);
  }
}

export default {
  getQueryString,
  formatTime,
  clearSpellingForAudio
}
