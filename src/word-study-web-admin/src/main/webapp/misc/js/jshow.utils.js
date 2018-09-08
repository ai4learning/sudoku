/**
 util类
*/
(function($,w){
	w.gUtils = w.gUtils || {};
	var utils = w.gUtils;
 
	$.extend(utils,{
			 /** 渲染简单模板
			  * @param template 模板
			  * @param data 数据
			  * */
		 fRenderTemplate:function(template,data){
			var content='';
			if(data instanceof Array){
				for(var i=0;i<data.length;i++){
					content+=this.fRenderTemplate(template,data[i]);
				}
			}else{
				var t=template;
				for(var attr in data){
					var value=data[attr];
					if(value==null){
						value='';
					}
					t=t.replace(new RegExp("@{"+attr+"}","gm"),value);
				}
				content+=t;
			}
			return content;
		 },
		/**
		 *    弹出确认框 操作成功刷新列表，操作失败提示消息      
		 **/
		fConfirmAndRefreshList:function(config) {	
			gDialog.fConfirm(config.title, config.info, function(rs) {
				if(!rs){
					return ;
				}
	            jQuery.ajax({
	    			url: config.url,
	    			type:'post',
	    			dataType: "json",
	    			data: config.param,
	    			success: function(data, ts) {
                        $('body').modalmanager('hideLoading');
						gDialog.fClose();
						if(data['success']) {
								if(data['message']!=null & data['message']!=''){
									message_box.show(data['message'],'success');
								}
							var pageNo=1;
							if(config['queryList'] && typeof config['queryList']=='string'){
								pageNo=gUtils.fGetCurrentPage('J_Input_page_'+config['queryList']);
							}
							if(config['queryList']){
								if($.isFunction(config['queryList'])){
									config['queryList'].call(this,pageNo);
								}else if(window[config['queryList']]){
									window[config['queryList']].call(this,pageNo);
								}
							}else{
								queryList(gUtils.fGetCurrentPage());
							}
						 }else{
							var sMessage=data['message'];
							if(sMessage==null){
								sMessage='处理异常，请联系管理员！';
							}
							gDialog.fAlert(sMessage);
						 }
					},beforeSend: function() {
                        $('body').modalmanager('loading');
                    }
				});
			});
		},
        /**
         * ajax form 提交多文件上传支持
         * @param param 表单参数
         * @param validateSet 验证规则
         * @param url URL
         * @param successHandler 操作成功处理
         * @param errorHandler 操作失败处理
         */
        fSubmitFormWithFiles: function(fileElementIds,param, validateSet, url, successHandler, errorHandler) {

            if (form.validate(validateSet)) {
                $('body').modalmanager('loading');
                $.ajaxFileUpload({
                    url: url,
                    type:'post',
                    fileElementId:fileElementIds,
                    dataType: "json",
                    data: param,
                    success: function(data, ts) {
                        $('body').modalmanager('hideLoading');
                        gUtils.fProcessResult(data, successHandler, errorHandler);
                    },
                    error: function (data, status, e){
                        $('body').modalmanager('hideLoading');
                        alert(e);
                    }

            });
            }
        },
		/**
		 * 
		 * @param param 表单参数
		 * @param validateSet 验证规则
		 * @param url URL
		 * @param successHandler 操作成功处理
		 * @param errorHandler 操作失败处理
		 */
		fSubmitForm: function(param, validateSet, url, successHandler, errorHandler) {
            if(validateSet!=null && !form.validate(validateSet)){
                return;
            }
            $.ajax({
                url: url,
                type:'post',
                dataType: "json",
                data: param,
                success: function(data, ts) {
                    gUtils.fProcessResult(data, successHandler, errorHandler);
                },
                beforeSend: function() {
                    $('body').modalmanager('loading');
                }
            });
		},
		/**
		 *
		 * @param param 表单参数
		 * @param url URL
		 * @param successHandler 操作成功处理
		 * @param errorHandler 操作失败处理
		 */
		fSubmitUrl: function(url,param, successHandler, errorHandler,loading) {
			$.ajax({
				url: url,
				type:'post',
				dataType: "json",
				timeout:10000,
				data: param,
				success: function(data, ts) {
					gUtils.fProcessResult(data, successHandler, errorHandler);
				},
				beforeSend: function() {
					if(loading){
						$('body').modalmanager('loading');
					}
				},
				error:function(){
					var data={'success':false,'message':'服务器异常，请联系管理员或重试！'};
					gUtils.fProcessResult(data, successHandler, errorHandler);
				}
			});
		},
		fProcessResult: function(data, successHandler, errorHandler) {
            $('body').modalmanager('hideLoading');
			if (data) {
				if (data['success'] && data['success'] == true) {
					successHandler.call(this,data);
				} else {

					if (errorHandler) {
						errorHandler.call(this,data);
					} else {
						var sMessage=data['message'];
				 		if(!sMessage||sMessage==''){
							sMessage='处理异常，请联系管理员！';
						}
				 		gDialog.fAlert(sMessage);
					}

					//$('.btn_submit').attr('disabled', false);
				}
			}
		},
		/** 获取主内容*/
		fGetBody: function(url, param,loadingMsg) {
			this.fGetHtml(url,'main_content' ,param,loadingMsg);
		},
		fGetHtml: function(url, id, param,loadingMsg) {
		//	$('#' + id).html('');
			var jContainer=$("#"+id);
			var timeFlag=(new Date()).getTime();
			jContainer.data('timeFlag',timeFlag);
			$.ajax({
				url:  url,
				dataType: "html",
				type:'post',
				data: param,
				success: function(data, textStatus) {
					var newTimeFlag=jContainer.data('timeFlag');
					if(timeFlag<newTimeFlag){
						//console.log('已经被新的内容更新，丢弃');
						return ;
					}
					jContainer.html(data);
				},
				beforeSend: function() {
					if(loadingMsg!=null &&loadingMsg!=false){
						gUtils.fLoading(id,loadingMsg);
					}
				}
			});
		},
		fLoading: function(id,loadingMsg){
			if(loadingMsg==true){
				loadingMsg='页面加载中，请稍候...';
			}
			$("#"+id).html('<div id="loading" class="data_loading">'+loadingMsg+'</div>');
		},
		fPageSize:function(methodName,dom,event){
			var $this=$(dom);
			var value=$this.val();
			value=value.replace(/[^\d]/g,'');
			$this.val(value);
			//解决浏览器之间的差异问题 
			var keyCode=event.keyCode ? event.keyCode:(event.which?event.which:event.charCode);
			if(keyCode!=13){ 
				return ;
			} 
			if(isNaN(value)){
				return;
			}
			var pageFun=eval(methodName);
			if(pageFun!=null){
				pageFun(1,value);
			}
			
		},
		fPostForm:function(url,param,target){
			target=target?target:'_blank';
			var _jForm = $("<form></form>",{ 
                            'id':'tempForm', 
                            'method':'post', 
                            'action':url, 
                            'target':target, 
                            'style':'display:none' 
                            }).appendTo($("body")); 


    		for(var attr in param){
    			var value=param[attr];
    			if(value!=null&&value!=''){
    				_jForm.append($("<input>",{'type':'hidden','name':attr,'value':value})); 
    			}
    		}
			
    		//触发提交事件 
            _jForm.trigger("submit"); 
            //表单删除 
          	 _jForm.remove(); 
		},
		/**
		 * 更新导航
		 * 
		 * @param id
		 * @param text
		 * @param url
		 */
		fUpdateNav : function(flag, id, text, url) {
			if (id == null) return;
			
			if (text!=null&&text!='') {
				var template='';
				var datas={'menuId':id,'menuName':text,'menuUrl':url};
				if(url==null||url==''){
					template='<li id="@{menuId}"><a href="javascript:;">@{menuName}</a></li>';
				}else{
					template='<li id="@{menuId}"><a href="javascript:;" onclick="gUtils.fGotoNavLink(\'@{menuUrl}\',\'@{menuId}\')">@{menuName}</a></li>';
				}
				var content=this.fRenderTemplate(template,datas);
				if(flag){
					$('#crumbs').html(content);
				}else{
					$('#crumbs').append(content);
				}
			}else{
				$('#' + id).remove();
			}
		},
		
		/**
		 * 跳转到指定页面，同时删除该导航后面的选项
		 */
		fGotoNavLink : function(url,id){
			var next=$("#"+id).next();
			if(next.length>0){
				$(next).remove();
			}
			this.fGetBody(url);
		},
		/**
		 * 获取当前页码
		 * */
		fGetCurrentPage:function(domId){
			if(domId==null || domId==''){
				domId='J_Input_page_queryList';
			}
			var page=$("#"+domId).val();
			if(isNaN(page)){
				page=1;
			}
			return page;
		},
		fMergeParam : function (param, formId) {
			var jObj=formId;
			if( typeof formId === 'string'){
				jObj=$("#"+formId);
			}
			return $.extend(param,jObj.serializeObject(true,true,true));
		},
		fMakeFullLink:function(url){
			var href=window.location.href;
			var params=gUtils.fGetUrlParam(href);
			var linkParam={};
			if(params['from']){
				linkParam['from']=params['from'];
			}
			if(params['menuId']){
				linkParam['menuId']=params['menuId'];
			}
			return gUtils.fContactParam(url,linkParam);
		},
		fGetUrlParam:function(url){
			var params = {};
			if (url && url.indexOf("?") != -1) {
				var str = url.substr(url.indexOf("?")+1);
				console.log(str);

				var strs = str.split("&");
				for (var i = 0; i < strs.length; i++) {
					params[strs[i].split("=")[0]] = strs[i].substr(strs[i].indexOf("=") + 1);
				}
			}
			console.log(params);
			return params;
		},
		fContactParam:function(url,param){
			if(url.indexOf("?")==-1){
				url=url+"?";
			}
			if($.isPlainObject(param)){
				for(var key in param){
					url+="&"+key+"="+param[key];
				}
			}else{
				url+="&"+param;
			}
			return url;
		}

	});
})(jQuery,window);




 /**
  * This tiny script just helps us demonstrate
  * what the various example callbacks are doing
  */
 window.message_box = (function($) {
     "use strict";

	 var alertContainerTemplate='<div id="message_box_container"  style="width:650px;position:fixed;left:50%;margin-left:-270px;z-index:10000;top:30px;"></div>';
	 var alertTemplate='<div id="message_box_info_@{randomId}" class="alert @{alertStyle}" style="display:none;margin-bottom: 5px;" role="alert"><button type="button" class="close">&times;</button> @{alertInfo}'+
		 '@{alertMessage}</div>';

	 var container;
	 var that={};
     that.init = function() {
     	if(container){
     		return;
     	}
		 $("body").append(alertContainerTemplate);
		 container=$("#message_box_container");
     };
     
     that.show = function(text,type,time) {
		 if(container==null ||container==undefined){
			 that.init();
		 }
		 var alertClass='';
		 var alertInfo='';
		 if(type=='info'){
			 alertClass='alert-info';
			 alertInfo='<strong><i class="fa fa-2x fa-info-circle"></i> &nbsp;提醒：</strong>';
		 }else if(type=='success'){
			 alertClass='alert-success';
			 alertInfo='<strong><i class="fa fa-2x fa-check-circle"></i>&nbsp;成功！</strong>';
		 }else if(type=='error'){
			 alertClass='alert-danger';
			 alertInfo='<strong><i class="fa fa-2x fa-times-circle"></i>&nbsp;错误：</strong>';
		 }else if(type=='danger'){
			 alertClass='alert-danger';
			 alertInfo='<strong><i class="fa fa-2x fa-minus-circle"></i>&nbsp;危险：</strong>';
		 }else{
			 alertClass='alert-warning';
			 alertInfo='<strong><i class="fa fa-2x fa-exclamation-triangle"></i>&nbsp;警告：</strong>';
		 }
		 var data={'alertStyle':alertClass ,'alertInfo':alertInfo, 'randomId':new Date().getTime(),'alertMessage':text};
		 var html=gUtils.fRenderTemplate(alertTemplate,data);
		 var elem=$(html).appendTo(container);
		 container.show();
		 elem.fadeIn();
		 if(time==null || isNaN(time)){
			 time=8000;
		 }
		 setTimeout(function() {
			 that.removeAlert(elem);
		 }, time);

		 elem.find('button.close').click(function(){
			 that.removeAlert(elem);
		 });
     };

	 that.removeAlert = function(elem) {
		 elem.fadeOut();
		 elem.remove();

		 if(that.alertCount()==0){
			 container.hide();
		 }
	 };
	 that.alertCount=function () {
		 var count=container.children().length;
		 return count;
	 };

	 that.info=function(text,time){
         that.show(text,"info",time);
     };

     that.danger=function(text,time){
         that.show(text,"danger",time);
     };
	 that.error=function(text,time){
		 that.show(text,"error",time);
	 };
     that.warning=function(text,time){
         that.show(text,"waring",time);
     };

     that.success=function(text,time){
         that.show(text,"success",time);
     };

     return that;
 }(jQuery));

/*----------------jquery扩展，讲form序列化为json object----tangzl---------------*/
$.fn.serializeObject = function(removeNullField,fieldsToStr,trim) {
	var o = {};
	var a = this.serializeArray();

	$.each(a, function () {
		if (removeNullField && !this.value) {
			return true;
		}

		var value=this.value;
		if(trim){
			value= $.trim(value);
		}

		if (o[this.name]) {
			if (fieldsToStr) {
				o[this.name] = o[this.name] + "," + value || '';
			} else {
				if (!o[this.name].push) {
					o[this.name] = [o[this.name]];
				}
				o[this.name].push(value || '');
			}

		} else {
			o[this.name] = value || '';
		}

	});
	return o;
}