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
var RightsTable = function () {
	var oTable;
	var handleTable = function () {
		var selected = [];
		var table=$('#rights_table');
		oTable = table.dataTable({
			"lengthChange":false,
        	"filter":true,
        	"sort":false,
        	"info":true,
        	"scrollX":"100%",
        	"scrollXInner":"100%",         	
        	"processing":true,                
            // set the initial value
            "displayLength": 10,
            "dom": "t<'row'<'col-md-6'i><'col-md-6'p>>",
//            "sPaginationType": "bootstrap_full_number",   //bootstrap_extended
//            "oLanguage": {
//                "sLengthMenu": "_MENU_ records per page",
//                "oPaginate": {
//                    "sPrevious": "Prev",
//                    "sNext": "Next",
//                	"zeroRecords": "No records to display"
//                }
//            },
            "columnDefs": [{                    
                    'targets': 0,   
                    'render':function(data,type,row){
                    	return '<div class="checker"><span><input type="checkbox" class="checkboxes"/></span></div>';
                    },
                    //'defaultContent':'<div class="checker"><span><input type="checkbox" class="checkboxes" value="1"/></span></div>'                    
                }
            ],
            "columns": [
               {"orderable": false },
	           { title: "ID",   data: "nodeId" ,"bVisible":false},
	           { title: "Bit Flag",   data: "bitFlag" ,"bVisible":false},
	           { title: "Rights Name",  data: "name"},
	           { title: "URI", data: "uri" },
	           { title: "Request Method", data: "method" },
	           { title: "Parent ID",  data: "pid" },
	           { title: "Is Menu",    data: "isMenu" },
	           { title: "Group Name",    data: "groupName" },
	           { title: "Group Sort",    data: "groupSort" ,"bVisible":false},	           
	           { title: "Status",  
			 	    'render':function(data,status,row){
	                                var tem = row.status;
			        				var str = '';
			        				if(tem==1){
			        					str = 'Active';
			        				}else if(tem==0){
			        					str = 'Inactive';
			        				}
			        				return str;
			        			}
			           },
			   { title: "Menu Icon", data: "menuIcon", "bVisible":false}
	          ],
	        "serverSide": true,
	        "serverMethod": "GET",
	        "ajaxSource": rootURI+"rights/list?rand="+Math.random(),
	        "fnDrawCallback":function(oSetting){
	        	selected=[];
	        }

		});		

		//打开删除对话框前判断是否已选择要删除的行
		$("#openDeleteRightsModal").on("click",function(event){
			if(selected.length==0){
				handleAlerts("Please select the rows which you want to delete.","warning","");				
				return false;
			}
		});
		
		//删除操作
		$('#deleteBtn').on('click', function (e) {
			$.ajax( {
             "dataType": 'json', 
             "type": "DELETE", 
             "url": rootURI+"rights/delete/"+selected.join(), 
             "success": function(data,status){
            	 if(status == "success"){					
					 if(data.status){
						 selected=[];						 
		            	 oTable.api().draw();
		            	 oTable.$('th span').removeClass();
					 }
					 else{
						 handleAlerts("Failed to delete the data. " +data.info,"danger","");
					 }
				}             	 
             },
             "error":function(XMLHttpRequest, textStatus, errorThrown){
            	 alert(errorThrown);
             }
           });
        });  
		
		
		$("#openEditRightModal").on("click",function(event){
			if(selected.length!=1){
				handleAlerts("One and only one row can be edited.","warning","");		
				return false;				
			}
			else{
				var data = oTable.api().row($("tr input:checked").parents('tr')).data();
				var nodeId = data.nodeId;
	            var name = data.name;
	            var bitFlag=data.bitFlag;
	            var uri  = data.uri;
	            var method  = data.method;
	            var groupName  = data.groupName;
	            var groupSort  = data.groupSort;
	            var descr  = data.descr;
	            var pid  = data.pid;
	            var isMenu  = data.isMenu;
	            var status=data.status;
	            var menuIcon=data.menuIcon;
	            $("#editRightsForm option").removeAttr("selected");
	            $("#editRightsForm :radio").removeAttr("checked");
	            $("#editRightsForm :radio").parents('span').removeClass("checked");
	            
	            $("#editRightsForm input[name='nodeId']").val(nodeId);
	            $("#editRightsForm input[name='name']").val(name);
	            $("#editRightsForm input[name='bitFlag']").val(bitFlag);
	            $("#editRightsForm input[name='uri']").val(uri);
	            $("#editRightsForm input[name='menuIcon']").val(menuIcon);
	            
	            $("#editRightsForm select[name='method']").children("option[value='"+method+"']").attr("selected","true");
	            $("#editRightsForm input[name='groupName']").val(groupName);
	            $("#editRightsForm input[name='groupSort']").val(groupSort);
	            $("#editRightsForm input[name='descr']").val(descr);
	            $("#editRightsForm input[name='pid']").val(pid);
	            	            	            
	            $("#editRightsForm :radio[name='isMenu']").filter("[value='"+isMenu+"']").attr("checked","true");
	            $("#editRightsForm :radio[name='isMenu']").filter("[value='"+isMenu+"']").parents('span').addClass("checked");
	            $("#editRightsForm :radio[name='status']").filter("[value='"+status+"']").attr("checked","true");
	            $("#editRightsForm :radio[name='status']").filter("[value='"+status+"']").parents('span').addClass("checked");
	            selected=[];
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
				
                       
		//全选
        $(".group-checkable").on('change',function () {
            var set = jQuery(this).attr("data-set");
            var checked = jQuery(this).is(":checked");
            selected=[];
            if(checked){            	
	            var api=oTable.api();            
	            jQuery(set).each(function () {            	
	            	var data = api.row($(this).parents('tr')).data();
	            	var id = data.nodeId;
	                var index = $.inArray(id, selected);
	                selected.push( id );
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
            var id = data.nodeId;
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
	
	//添加操作
	var ajaxAddRights=function(){		
		$.ajax( {
         "dataType": 'json', 
         "type":'POST', 
         "url": rootURI+"rights/add", 
         "data": $('#addRightsForm').serialize(),
         "success": function(resp,status){
        	 if(status == "success"){  
        		 if(resp.status){						 
	            	 oTable.api().draw();
	            	 handleAlerts("Added the data successfully.","success","#addFormMsg");		            	 
				 }
				 else{
					 handleAlerts("Failed to add the data.","danger","#addFormMsg");						 
				 }
			}             	 
         },
         "error":function(XMLHttpRequest, textStatus, errorThrown){
        	 alert(errorThrown);
         }
       });		
    };
	
    
    //编辑表单提交操作
    var ajaxEditRights=function(){			  
		  $.ajax( {
             "dataType": 'json', 
             "type": "POST", 
             "url": rootURI+"rights/edit", 
             "data": $('#editRightsForm').serialize(),
             "success": function(resp,status){
            	 if(status == "success"){  
            		 if(resp.status){
						 selected=[];
		            	 oTable.api().draw();
		            	 handleAlerts("Edited the data successfully.","success","#editFormMsg");
					 }
					 else{
						 handleAlerts("Failed to edit the data.","danger","#editFormMsg");
					 }
				}             	 
             },
             "error":function(XMLHttpRequest, textStatus, errorThrown){
            	 alert(errorThrown);
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
            closeInSeconds: 10, // auto close after defined seconds, 0 never close
            icon: "warning" // put icon before the message, use the font Awesone icon (fa-[*])
        });        

    };
    
    //处理表单验证方法
    var addFormValidation = function() {
            var addform = $('#addRightsForm');
            var errorDiv = $('.alert-danger', addform);            

            addform.validate({
                errorElement: 'span', //default input error message container
                errorClass: 'help-block help-block-error', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                ignore: "",  // validate all fields including form hidden input                
                rules: {
                    name: {
                        minlength: 2,
                        required: true
                    },
                    uri: {
                        required: true,
                        maxlength:60                        
                    },
                    method: {
                        required: true                        
                    },
                    pid: {
                        required: true,
                        number: true
                    }                    
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
                onfocusout: function (element) { // hightlight error inputs
                    $(element).valid();
                },
                success: function (label) {
                    label
                        .closest('.form-group').removeClass('has-error'); // set success class to the control group
                },

                submitHandler: function (form) {                	
                    errorDiv.hide();
                    ajaxAddRights();                    
                }
            });
    };
    
  //处理表单验证方法
    var editFormValidation = function() {
            var editform = $('#editRightsForm');
            var errorDiv = $('.alert-danger', editform);            

            editform.validate({
                errorElement: 'span', //default input error message container
                errorClass: 'help-block help-block-error', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                ignore: "",  // validate all fields including form hidden input                
                rules: {
                    name: {
                        minlength: 2,
                        required: true
                    },
                    uri: {
                        required: true,
                        maxlength:60                        
                    },
                    method: {
                        required: true                        
                    },
                    pid: {
                        required: true,
                        number: true
                    }                    
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
                onfocusout: function (element) { // hightlight error inputs
                    $(element).valid();
                },
                success: function (label) {
                    label
                        .closest('.form-group').removeClass('has-error'); // set success class to the control group
                },

                submitHandler: function (form) {                	
                    errorDiv.hide();
                    ajaxEditRights();                    
                }
            });
    };

    return {
        //main function to initiate the module
        init: function (rootPath) {
        	rootURI=rootPath;
        	handleTable();  
        	addFormValidation();
		editFormValidation();
        }

    };

}();