//jquery插件把表单序列化成json格式的数据start 
(function($){
    $.fn.serializeJson=function(){
        var serializeObj={};
        var array=this.serializeArray();
        var str=this.serialize();
        $(array).each(function(){
            if(serializeObj[this.name]){
                if($.isArray(serializeObj[this.name])){
                    serializeObj[this.name].push(this.value);
                }else{
                    serializeObj[this.name]=[serializeObj[this.name],this.value];
                }
            }else{
                serializeObj[this.name]=this.value;
            }
        });
        return serializeObj;
    };
})(jQuery);

var rootURI="/";
var UserProfile = function () {
   //修改个人资料
    var ChangeProfile = function(){    	
    	//编辑提交操作
	     $.ajax( {
	             "dataType": 'json', 
	             "type": "POST", 
	             "url": rootURI+"userprofile/editprofile", 
	             "data": $("#editUserProfile").serialize(),
	             "success": function(resp,status){	            	 
	            	 if(status == "success"){ 	            		
	            		 if(resp.status){
							 handleAlerts("Edited the data successfully.","success","#editFormMsg");
						 }
						 else{
							 handleAlerts(resp.info,"danger","#editFormMsg");
						 }
					}             	 
	             },
	             "error":function(XMLHttpRequest, textStatus, errorThrown){
	            	alert(errorThrown);
	              }
	           });
			  return false;
		};
	   
						
	    //修改密码
	    var ChangePassword = function() {
                  $.ajax( {
		             "dataType": 'json', 
		             "type": "POST", 
		             "url": rootURI+"userprofile/changePassword?rand="+Math.random(), 
		             "data": $("#changePasswordForm").serialize(),
		             "success": function(resp,status){		            	 
		            	 if(status == "success"){ 		            		
		            		 if(resp.status){
		            			 if(resp.olderror){
		            				 handleAlerts("Confirm your current password is correct","danger","#changePasswordMsg");
		            			 }else{
		            				 
		            				   alert("Change the Password successfully.");
		            				   window.location.href=rootURI+"login"
		            			 }
								 
							 }
							 else{
								 alert(resp.info);
							 }
						}             	 
		             },
		             "error":function(XMLHttpRequest, textStatus, errorThrown){
		            	alert(errorThrown);
		              }
		           });
				  return false;
       };
	    


	    //验证表单
	    var changePasswordValidation = function() {
	    	var changeform = $('#changePasswordForm');
            var errorDiv = $('.alert-danger', changeform);            
                changeform.validate({
                errorElement: 'span', //default input error message container
                errorClass: 'help-block help-block-error', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                ignore: "",  // validate all fields including form hidden input                
                rules: {
                	oldpassword: {
                        minlength:6,
                        required: true,
                        maxlength:20
                    },
                    newpassword: {
                    	minlength:6,
                        required: true,
                        maxlength:20                        
                    },
                    renewpassword: {
                    	minlength:6,
                        required: true ,
                        maxlength:20,
                        equalTo:"#newpassword"
                     
                    }
                                
                },
                onfocusout:function(element){
                	$(element).valid();
                },
                invalidHandler: function (event, validator) { //display error alert on form submit              
                	//successDiv.hide();
                    errorDiv.show();                    
                },

                highlight: function (element) { // hightlight error inputs
                    $(element)
                        .closest('.form-group').addClass('has-error'); // set error class to the control group
                },

                unhighlight: function (element) { // revert the change done by hightlight
                    $(element)
                        .closest('.form-group').removeClass('has-error'); // set error class to the control group
                },

                success: function (label) {
                    label
                        .closest('.form-group').removeClass('has-error'); // set success class to the control group
                },

                submitHandler: function (form) {
                	ChangePassword();
                	errorDiv.hide();
                }
            });
    };
    
  //验证表单
    var changeProfileValidation = function() {
    	var changeform = $('#editUserProfile');
        var errorDiv = $('.alert-danger', changeform);            
            changeform.validate({
            errorElement: 'span', //default input error message container
            errorClass: 'help-block help-block-error', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            ignore: "",  // validate all fields including form hidden input                
            rules: {
            	email: {
                    required: true,
                    email:true
                }
                            
            },
            onfocusout:function(element){
            	$(element).valid();
            },
            invalidHandler: function (event, validator) { //display error alert on form submit              
            	//successDiv.hide();
                errorDiv.show();                    
            },

            highlight: function (element) { // hightlight error inputs
                $(element)
                    .closest('.form-group').addClass('has-error'); // set error class to the control group
            },

            unhighlight: function (element) { // revert the change done by hightlight
                $(element)
                    .closest('.form-group').removeClass('has-error'); // set error class to the control group
            },

            success: function (label) {
                label
                    .closest('.form-group').removeClass('has-error'); // set success class to the control group
            },

            submitHandler: function (form) {
            	ChangeProfile();
            	errorDiv.hide();
            }
        });
};
    //提示信息处理方法（是在页面中指定位置显示提示信息的方式）
	var handleAlerts = function(msg,msgType,position) {         
        Metronic.alert({
            container: position, // alerts parent container(by default placed after the page breadcrumbs)
            place: "prepent", // append or prepent in container 
            type: msgType,  // alert's type (success, danger, warning, info)
            message: msg,  // alert's message
            close: true, // make alert closable
            reset: true, // close all previouse alerts first
            focus: false, // auto scroll to the alert after shown
            closeInSeconds: 5, // auto close after defined seconds, 0 never close
            icon: "warning" // put icon before the message, use the font Awesone icon (fa-[*])
        });        

    };
    
    
    //initialize datepicker
    var datePicker = function(){
    	$('.date-picker').datepicker({
        rtl: Metronic.isRTL(),
        autoclose: true
        });
     };
    
  
    
    return {
        //main function to initiate the module
        init: function (rootPath) {
        	rootURI=rootPath;
        	changePasswordValidation();
        	changeProfileValidation();
		    datePicker();
        }

    };

}();