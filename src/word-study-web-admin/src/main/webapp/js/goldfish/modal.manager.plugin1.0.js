﻿/*
	author:zsb1987@foxmail.com
	date:2013-08-22
	version:v1.0.0
	
	@description:

	
*/

/*
	dialog弹出层
*/
(function($,w){
	w.gDialog = w.gDialog || {};
	var dialog = w.gDialog;
	var modalStart='<div class="modal hide fade" data-backdrop="static" id="@{modalId}">';
	var modalEnd='<div class="_JDialogContent"></div> </div>';
	var headTemplate='<div class="modal-header">'+
				'<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>'+
				'<h3 class="_JHeadContent">@{title}</h3></div>';
	
	var alertTemplate='<div class="modal-header"><h3 class="_JHeadContent">@{title}</h3></div><div class="modal-body">'+
	'<p>@{msgContent}</p></div>  <div class="modal-footer"><a role="button" class="btn btn-primary JConfirmBtn">确定</a></div>';

	var confirmTemplate='<div class="modal-header"><h3 class="_JHeadContent">@{title}</h3></div><div class="modal-body">'+
	'<p>@{msgContent}</p></div>  <div class="modal-footer"><a role="button" class="btn btn-success JConfirmBtn">确定</a>'+
	'<a role="button" class="btn JCancelBtn">取消</a></div>';
	$.extend(dialog,{
		fCreate:function(config){
			if(!config) return;
			if(!config.modalId){
				config.modalId=this.fGenModalId();
			}
			var modalContent=modalStart;
			//if has title,then render head
			if(config.title){
				modalContent+=headTemplate;
			}
			modalContent+=modalEnd;
			var modalContent=gUtils.fRenderTemplate(modalContent,config);
			var $modalContent=$(modalContent).appendTo('body');
			
			if(config.url && config.url!=''){
					$.ajax({
					url : config.url,
					dataType : 'html',
					cache:false,
					async:false,
					data:config.param || "",
					success : function(data,textStatus){
						$modalContent.find('._JDialogContent').html(data);
					}
				});
			}else if(config.content){
			  $modalContent.find('._JDialogContent').html(config.content);
			}

			var dragConfig=$.extend({handle:'.modal-header'},config.dragConfig);
			var modalConfig={
					keyboard: false,
					show:false,
					dragConfig:dragConfig
				};
			var width=config['width'];
			if(width){
				modalConfig['width']=width;
			}
			$modalContent.modal(modalConfig);	
				
			var modal= $modalContent.data('modal');
			//modal can be reuse
			console.log(modal);
			if(!config['reuse']){
				modal['removeDomWhenHide']=true;
			}
			return modal;
		},
		fClose:function(id){
		 var modalManager=$('body').modalmanager().data('modalmanager');
			if(modalManager!=null){
				if(modalManager.hasOpenModal()){
					modalManager.getTopModal().hide();
				}
			}
		},
		fConfirmClose:function(){
			$('.modal-scrollable').remove();
			$('.modal-backdrop').remove();
		},

		fTips:function(msg,titleContent,callback){
			var data={title:titleContent,msgContent:msg};
			var alertContent=gUtils.fRenderTemplate(alertTemplate,data);
			var config={content:alertContent,width:600};
			var modal=this.fCreate(config);
			modal.$element.find(".JConfirmBtn").click(function(){
				modal.hide();
				if(callback){
					callback();
				}
			});
			modal.show();
//			bootbox.alert(msg);
		},
		
		fAlert:function(msg,callback){
			var data={title:'警告',msgContent:msg};
			var alertContent=gUtils.fRenderTemplate(alertTemplate,data);
			var config={content:alertContent,width:600};
			var modal=this.fCreate(config);
			modal.$element.find(".JConfirmBtn").click(function(){
				modal.hide();
				if(callback){
					callback();
				}
			});
			modal.show();
//			bootbox.alert(msg);
		},
		fConfirm:function(title,content,callback){
			var data={title:title,msgContent:content};
			var confirmContent=gUtils.fRenderTemplate(confirmTemplate,data);
			var config={content:confirmContent,width:600};
			var modal=this.fCreate(config);
			modal.$element.find(".JConfirmBtn").click(function(){
				modal.hide();
				if(callback){
					callback(true);
				}
			});
			modal.$element.find(".JCancelBtn").click(function(){
				modal.hide();
				if(callback){
					callback(false);
				}
			});
			modal.show();
			
			
			/*
			bootbox.dialog(content,[{
						label: "确定",
						'class': "btn-success",
						callback: callback
				},{
						label: "取消",
						'class': "btn"
				}
			],{'header':title}); */
		},

		fGenModalId:function(){
			return 'modal-'+new Date().getTime();
		}
	});
})(jQuery,window);

