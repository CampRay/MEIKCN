
var rootURI="/";
var local="zh_CN";
var LicensesTable = function () {
	var oTable;
	var oLogTable;
	var selected = [];
	var handleTable = function () {							
		var table=$('#licenses_table');
		 oTable = table.dataTable({
			"lengthChange":false,
        	"filter":true,
        	"sort":false,
        	"info":true,
        	"processing":true,        	
            "displayLength": 15,
            "dom": "t<'row'<'col-md-6'i><'col-md-6'p>>",
            "oLanguage": {
                "sProcessing": loadProperties("dataTable.page.process",local,rootURI),                
                "sZeroRecords":loadProperties("dataTable.page.data.zero",local,rootURI),
                "sEmptyTable": loadProperties("dataTable.page.data.empty",local,rootURI),
                "sInfo": loadProperties("dataTable.page.info",local,rootURI),
                "sInfoEmpty":loadProperties("dataTable.page.info.empty",local,rootURI),
            },
            "columnDefs": [{                    
                    'targets': 0,   
                    'render':function(data,type,row){
                    	return '<div class="checker"><span><input type="checkbox" class="checkboxes"/></span></div>';
                    },                                        
                }
            ],
            "columns": [
               {"orderable": false },
	           {data: "license"  },
	           {'render':function(data,status,row){                   
	   			   var str = 'MEIK Screen';
	   			   if(row.type=="1"){
	   					str ="MEIK Report";
	   			   }
	   			   return str;
	   			}
	           },	  
	           {data: "deviceId",defaultContent:""},
	           {data: "deadlineStr",defaultContent:""},
	           {data: "cpuId",defaultContent:"" },
	           {data: "createdTimeStr",defaultContent:"" },
	           {data: "activeTimeStr",defaultContent:"" },	           
	           {'render':function(data,status,row){
                    var tem = row.status;
    				var str = '';
    				if(tem==1){
    					str = loadProperties("dataTable.page.enable",local,rootURI);
    				}else if(tem==0){
    					str = loadProperties("dataTable.page.disable",local,rootURI);
    				}
    				return str;
    			}
	           },	           
	          
	        ],
	        "serverSide": true,
	        "serverMethod": "GET",
	        "ajaxSource": rootURI+"license/list?rand="+Math.random(),
	        "fnDrawCallback":function(oSetting){
	        	selected=[];
	        }
		});		
		 		
		$("#openActiveLicenseModal").on("click",function(event){
			if(selected.length==0){
				handleAlerts(loadProperties("error.active.select",local,rootURI),"warning","");				
				return false;
			}
		});
		$("#openDeactiveLicenseModal").on("click",function(event){
			if(selected.length==0){
				handleAlerts(loadProperties("error.deactive.select",local,rootURI),"warning","");				
				return false;
			}
		});
		$("#deleteLicenseModal").on("click",function(event){
			if(selected.length==0){
				handleAlerts(loadProperties("error.delete.select",local,rootURI),"warning","");				
				return false;
			}
		});
		
		//删除操作
		$('#deleteBtn').on('click', function (e) {				
			$.ajax( {
             "dataType": 'json', 
             "type": "DELETE", 
             "url": rootURI+"license/delete/"+selected.join(), 
             "success": function(data,status){
            	 if(status == "success"){					
					 if(data.status){
						 selected=[];						 
		            	 oTable.api().draw();
		            	 oTable.$('th span').removeClass();
		            	 handleAlerts("delete the licenses successfully.","success","");
					 }
					 else{
						 handleAlerts("Failed to delete the licenses. " +data.info,"danger","");
					 }
				}             	 
             },
             "error":function(XMLHttpRequest, textStatus, errorThrown){
            	 alert(errorThrown);
             }
           });
        });  
		
		//激活操作
		$('#activeBtn').on('click', function (e) {
			$.ajax( {
             "dataType": 'json', 
             "type": "POST", 
             "url": rootURI+"license/active/"+selected.join(), 
             "success": function(data,status){
            	 if(status == "success"){					
					 if(data.status){
						 selected=[];						 
		            	oTable.api().draw();
		            	oTable.$('th span').removeClass();
		            	 handleAlerts("activateBtn the rules successfully.","success","");
					 }
					 else{
						 alert(data.info);
					 }
				}             	 
             },
             "error":function(XMLHttpRequest, textStatus, errorThrown){
            	 alert(errorThrown);
             }
           });
        }); 
		
		//禁用操作
		$('#deactivateBtn').on('click', function (e) {
			$.ajax( {
             "dataType": 'json', 
             "type": "POST", 
             "url": rootURI+"license/deactive/"+selected.join(), 
             "success": function(data,status){
            	 if(status == "success"){					
					 if(data.status){
						 selected=[];						 
		            	oTable.api().draw();
		            	oTable.$('th span').removeClass();
		            	 handleAlerts("activateBtn the rules successfully.","success","");
					 }
					 else{
						 alert(data.info);
					 }
				}             	 
             },
             "error":function(XMLHttpRequest, textStatus, errorThrown){
            	 alert(errorThrown);
             }
           });
        }); 
		
		$("#openEditModal").on("click",function(event){
			if(selected.length!=1){
				handleAlerts("One and only one row can be edited.","warning","");		
				return false;				
			}
			else{
				var data = oTable.api().row($("tr input:checked").parents('tr')).data();
				var license = data.license;
	            var deviceId = data.deviceId;
	            var cpuId=data.cpuId;
	            var activeTime  = data.activeTime;
	            var activeTimeStr  = data.activeTimeStr;
	            var deadline  = data.deadline;
	            var deadlineStr  = data.deadlineStr;
	            var status  = data.status;
	            var createdTime  = data.createdTime;
	            
	            $("#editLicenseForm input[name='license']").val(license);
	            $("#editLicenseForm input[name='deviceId']").val(deviceId);	            
	            $("#editLicenseForm input[name='cpuId']").val(cpuId);
	            $("#editLicenseForm input[name='activeTime']").val(activeTime);
	            $("#editLicenseForm input[name='deadline']").val(deadline);	            	            
	            $("#editLicenseForm input[name='deadlineStr']").val(deadlineStr);
	            $("#editLicenseForm input[name='status']").val(status);	
	            $("#editLicenseForm input[name='createdTime']").val(createdTime);	
			}
		});		
	
		//搜索表单提交操作
		$("#searchForm").on("submit", function(event) {
			event.preventDefault();
			var jsonData=$(this).serializeJson();
			var jsonDataStr=JSON.stringify(jsonData);			
			oTable.fnFilter(jsonDataStr);
			return false;
		});	
											
		
		$(".group-checkable").on('change',function () {
            var set = jQuery(this).attr("data-set");
            var checked = jQuery(this).is(":checked");
            selected=[];
            if(checked){            	
	            var api=oTable.api();            
	            jQuery(set).each(function () {            	
	            	var data = api.row($(this).parents('tr')).data();
	            	var ids=data.license;
	                var index = $.inArray(ids, selected);
	                selected.push( ids );
                    $(this).attr("checked", true);
                    $(this).parents('tr').addClass("active");
                    $(this).parents('span').addClass("checked");
	            });
            }
            else{
            	jQuery(set).removeAttr("checked");
            	jQuery(set).parents('tr').removeClass("active");
            	jQuery(set).parents('span').removeClass("checked");
            }
            jQuery.uniform.update(set);
        });
                        
        
        //单选
        table.on('change', 'tbody tr .checkboxes', function () {
            $(this).parents('tr').toggleClass("active");            
            var data = oTable.api().row($(this).parents('tr')).data();
            var id = data.license;
            var index = $.inArray(id, selected);     
            if ( index === -1 ) {
                selected.push( id );
                $(this).parents('span').addClass("checked");
                $(this).attr("checked","checked");
            } else {
                selected.splice( index, 1 );
                $(this).parents('span').removeClass("checked");
                $(this).removeAttr("checked");
            }
        });
               
 
        /* handle show/hide columns*/
        var tableColumnToggler = $('#column_toggler');		
		$('input[type="checkbox"]', tableColumnToggler).change(function () {
		    /* Get the DataTables object again - this is not a recreation, just a get of the object */
		    var iCol = parseInt($(this).attr("data-column"));
		    var bVis = oTable.fnSettings().aoColumns[iCol].bVisible;
		    oTable.fnSetColumnVis(iCol, (bVis ? false : true));
		});
        
        
	};
	
	
	//提示信息处理方法（是在页面中指定位置显示提示信息的方式）
	var handleAlerts = function(msg,msgType,position) { 
		if(position==""){
			position = $("#msg");
		}
        Metronic.alert({
            container: position, // alerts parent container(by default placed after the page breadcrumbs)
            place: "prepent", // append or prepent in container 
            type: msgType,  // alert's type (success, danger, warning, info)
            message: msg,  // alert's message
            close: true, // make alert closable
            reset: true, // close all previouse alerts first
            focus: false, // auto scroll to the alert after shown
            closeInSeconds: 10, // auto close after defined seconds, 0 never close
            icon: "warning" // put icon before the message, use the font Awesone icon (fa-[*])
        });        

    };
    //添加操作
	var AddLicense = function(){		
		$.ajax( {
         "dataType": 'json', 
         "type":'POST', 
         "url": rootURI+"license/add",
         "data": $('#addLicenseForm').serialize(),
         "success": function(resp,status){
        	 if(status == "success"){  
        		 if(resp.status){						 
	            	 oTable.api().draw();
	            	 handleAlerts("Added the data successfully.","success","");		            	 
				 }
				 else{
					 handleAlerts("Failed to add the data. "+resp.info+"  the license is exist","danger","");						 
				 }
			}             	 
         },
         "error":function(XMLHttpRequest, textStatus, errorThrown){
        	 alert(errorThrown);
         }
       });	
		
		$('#add_license').modal('hide');
    };            			           		  
    
    var AddLicenseValidation = function() {
        var form = $('#addLicenseForm');
        var errorDiv = $('.alert-danger', form);            
        form.validate({
            errorElement: 'span', //default input error message container
            errorClass: 'help-block help-block-error', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            ignore: "",  // validate all fields including form hidden input                
            rules: {             
            },
           invalidHandler: function (event, validator) { //display error alert on form submit              
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
            onfocusout:function(element){
            	$(element).valid();
            },
            submitHandler: function (form) { 
            	errorDiv.hide();
            	AddLicense();
            }
        });
    };
    
	//编辑表单提交操作
	var EditLicense= function() {
	  $.ajax( {
         "dataType": 'json', 
         "type": "POST", 
         "url": rootURI+"license/edit", 
         "data" :$('#editLicenseForm').serializeJson(),
         "success": function(resp,status){
        	 if(status == "success"){  
        		 if(resp.status){
					 selected=[];
	            	 oTable.api().draw();
	            	 handleAlerts("Edited the data successfully.","success","");
				 }
				 else{
					 handleAlerts("Failed to add the data. "+resp.info+"the license is exist","danger","");
				 }
			}             	 
         },
         "error":function(XMLHttpRequest, textStatus, errorThrown){
        	 alert(errorThrown);
         }
       });
	  $('#edit_license').modal('hide');
	};
		
            
	var EditLicenseValidation = function() {
		var form = $('#editLicenseForm');
		var errorDiv = $('.alert-danger', form);            
		form.validate({
			errorElement: 'span', //default input error message container
			errorClass: 'help-block help-block-error', // default input error message class
			focusInvalid: false, // do not focus the last invalid input
			ignore: "",  // validate all fields including form hidden input                
			rules: {				

	        },
	       invalidHandler: function (event, validator) { //display error alert on form submit              
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
	        onfocusout:function(element){
	        	$(element).valid();
	        },
	        submitHandler: function (form) { 
	        	event.preventDefault();
	        	errorDiv.hide();
	        	EditLicense();
	        }
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
        init: function (rootPath,local_value) {
        	rootURI=rootPath;
        	local=local_value;
        	handleTable();  
        	datePicker();
        	AddLicenseValidation();
        	EditLicenseValidation();
        }

    };
}();
