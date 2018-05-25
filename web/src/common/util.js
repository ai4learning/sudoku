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

export default {
  getQueryString
}
