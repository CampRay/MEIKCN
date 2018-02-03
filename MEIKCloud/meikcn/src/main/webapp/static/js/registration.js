var rootURI="/";
var register = function () {	   
			
		$('#leftbreastmap area').unbind().bind('mouseenter',function(){
			if($("#complaintsPalpableLumps").is(":checked")){
				var n=$(this).attr("alt");
				$("#leftBreastIcon"+n).show();
			}
		}).bind("mouseleave",function(){
			if($("#complaintsPalpableLumps").is(":checked")){
				var n=$(this).attr("alt");
				var selected=$(this).attr("status");
				if(selected=="0"){
					$("#leftBreastIcon"+n).hide();
				}
			}
		}).bind('click',function(){
			if($("#complaintsPalpableLumps").is(":checked")){
				var n=$(this).attr("alt");	
				var selected=$(this).attr("status");
				if(selected=="1"){
					$(this).attr("status","0");
					$("#leftBreastIcon"+n).hide();
				}
				else{
					$(this).attr("status","1");
					$("#leftBreastIcon"+n).show();
				}
			}
						
		});
		
		$('#rightbreastmap area').unbind().bind('mouseenter',function(){
			if($("#complaintsPalpableLumps").is(":checked")){
				var n=$(this).attr("alt");
				$("#rightBreastIcon"+n).show();
			}
		}).bind("mouseleave",function(){
			if($("#complaintsPalpableLumps").is(":checked")){
				var n=$(this).attr("alt");
				var selected=$(this).attr("status");
				if(selected=="0"){
					$("#rightBreastIcon"+n).hide();
				}
			}
		}).bind('click',function(){
			if($("#complaintsPalpableLumps").is(":checked")){
				var n=$(this).attr("alt");	
				var selected=$(this).attr("status");
				if(selected=="1"){
					$(this).attr("status","0");
					$("#rightBreastIcon"+n).hide();
				}
				else{
					$(this).attr("status","1");
					$("#rightBreastIcon"+n).show();
				}
			}			
		});
		
	    
	    var submitForm = function() {
	    	var leftPosition=0;
	    	var rightPosition=0;
	    	$.each($("#leftbreastmap area[status='1']"), function(i, n){
	    		leftPosition+=parseInt($(n).attr("data"));
    		});
	    	$.each($("#rightbreastmap area[status='1']"), function(i, n){
	    		rightPosition+=parseInt($(n).attr("data"));
    		});
	    	
	    	$("#complaintsLumpsLeftPosition").val(leftPosition);
	    	$("#complaintsLumpsRightPosition").val(rightPosition);			
	    };
	    
	    //同意协议验证 
	    jQuery.validator.addMethod("isAgree", function(value, element) {     	        
	        return $(element).is(":checked");  
	    }, "You have to check the agreement box if you want to submit your information."); 
	    
	    //验证表单
	    var registerValidation = function() {
	    	var userinfoform = $('#userInfo');                      
            	userinfoform.validate({                
                focusInvalid: false, // do not focus the last invalid input                       
                rules: {
                	lastName: {                        
                        required: true,
                        maxlength:50
                    },
                    firstName: {                    	
                        required: true,
                        maxlength:50                        
                    },
                    birthday: {                    	
                        required: true,
                        maxlength:30                      
                    },
                    mobile: {                    	
                        required: true,
                        maxlength:100                        
                    },
                    email: {          
                    	email:true,
                        required: true,
                        maxlength:200                        
                    },
                    regAgree:{
                    	isAgree: true
                    }
                },
                messages:{
                	required: "This field is required.",
                	email: "Please enter a valid email address.",
                	maxlength: $.validator.format("Please enter no more than {0} characters."),  
                },
                onsubmit:function(element){
                	if($(element).hasClass("date-picker")){
                		$(element).valid();
                	}
                },         
                onfocusout:function(element){
                	if(!$(element).hasClass("date-picker")){
                		$(element).valid();
                	}
                },                
                highlight: function (element) { // hightlight error inputs
                	$(element).focus();
                    $(element).addClass('has-error'); // set error class to the control group
                },

                unhighlight: function (element) { // revert the change done by hightlight
                    $(element).removeClass('has-error'); // set error class to the control group
                    $(".error[for='"+$(element).attr("name")+"']").remove();
                },
                errorPlacement: function(error, element) {  
                	if(error.text()!=""){
	                	if(element.is(":checkbox") ){  
	                        error.insertBefore(element);
	                	}
	                	else{
	                		error.appendTo(element.parent()); 
	                	}
                	}
                	else{
                		error.hide();
                	}
                	
                },  
                success: function (element) {
                	element.removeClass('has-error'); // set success class to the control group
                	$(".error[for='"+$(element).attr("name")+"']").remove();
                },

                submitHandler: function (form) {                	
                	submitForm();
            		form.submit();                	                	
                }
            });
    };
        
    
    //initialize datepicker
    var datePicker = function(){
    	$('.date-picker').datepicker({    		
    		autoclose: true 		
        });
     };
    
  
    
    return {
        //main function to initiate the module
        init: function (rootPath) {
        	rootURI=rootPath;
        	registerValidation();        	
		    datePicker();
        }
    };

}();