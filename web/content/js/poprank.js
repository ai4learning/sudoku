// JavaScript Document
// 判断浏览器ie6创建的div样式和非ie6创建的div样式
// 显示弹出层
function showGreyBox(idname) {
 var isIE = (document.all) ? true : false;
 var isIE6 = isIE
   && ( [ /MSIE (\d)\.0/i.exec(navigator.userAgent) ][0][1] == 6);
 var newbox = document.getElementById(idname);
 newbox.style.zIndex = "9999";
 newbox.style.display = "block"
 newbox.style.position = !isIE6 ? "fixed" : "absolute";
 newbox.style.top = newbox.style.left = "50%";
 newbox.style.marginTop = -newbox.offsetHeight / 2 + "px";
 newbox.style.marginLeft = -newbox.offsetWidth / 2 + "px";
 var layer = document.getElementById("layer");
 if (layer == null || layer == "undefined") {
  layer = document.createElement("div");
  layer.id = "layer";
  document.body.appendChild(layer);
 } else {
  layer.style.display = "block";
 }
 layer.style.width = layer.style.height = "100%";
 layer.style.position = !isIE6 ? "fixed" : "absolute";
 layer.style.top = layer.style.left = 0;
 layer.style.backgroundColor = "#000";
 layer.style.zIndex = "9998";
 layer.style.opacity = "0.6";
// 隐藏下拉列表
 var sel = document.getElementsByTagName("select");
 for ( var i = 0; i < sel.length; i++) {
  sel[i].style.visibility = "hidden";
 }
 function layer_iestyle() {
  layer.style.width = Math.max(document.documentElement.scrollWidth,
    document.documentElement.clientWidth)
    + "px";
  layer.style.height = Math.max(document.documentElement.scrollHeight,
    document.documentElement.clientHeight)
    + "px";
 }
 function newbox_iestyle() {
  newbox.style.marginTop = document.documentElement.scrollTop
    - newbox.offsetHeight / 2 + "px";
  newbox.style.marginLeft = document.documentElement.scrollLeft
    - newbox.offsetWidth / 2 + "px";
 }
 if (isIE) {
  layer.style.filter = "alpha(opacity=30)";
 }
 if (isIE6) {
  layer_iestyle()
  newbox_iestyle();
  window.attachEvent("onscroll", function() {
   newbox_iestyle();
  })
  window.attachEvent("onresize", layer_iestyle)
 }
}
/**
 * 关闭弹出层
 */
function closeGreyBox(divId) {
 var uwindow = document.getElementById(divId);// 关闭弹出层
 uwindow.style.display = "none";
 var grayLayer = document.getElementById("layer");// 关闭遮罩层
 grayLayer.style.display = "none";
}
