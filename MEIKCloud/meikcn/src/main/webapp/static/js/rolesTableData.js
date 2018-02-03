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
var RolesTable = function () {
	var oTable;
	var selected=[];
	var handleTable = function () {		
		var table=$('#roles_table');
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
            "columnDefs": [{                    
                    'targets': 0,   
                    'render':function(data,type,row){
                    	return '<div class="checker"><span><input type="checkbox" class="checkboxes"/></span></div>';
                    },
                    //'defaultContent':'<div class="checker"><span><input type="checkbox" class="checkboxes" value="1"/></span></div>'                    
                },
                {                	
                	'targets':-1,                	
                	'render':function(data,type,row){
                		if(data==null){
                			return '0';
                		}
                		else{
                			return data["roleRights"];
                		}
                    }                    
                }
            ],
            "columns": [
               {"orderable": false },
	           { title: "ID",   data: "roleId" },	           
	           { title: "Role Name",  data: "roleName"},	           
	           { title: "Parent ID",  data: "pid" },	           
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
	           { title: "Role Rights",  data: "adminRoleRights" }
	        ],
	        "serverSide": true,
	        "serverMethod": "GET",
	        "ajaxSource": rootURI+"roles/rolesList?rand="+Math.random(),
	        "fnDrawCallback":function(oSetting){
	        	selected=[];
	        }

		});	
		
		//打开删除对话框前判断是否已选择要删除的行
		$("#openDeleteRoleModal").on("click",function(event){
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
             "url": rootURI+"roles/roles/"+selected.join(), 
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
		
		//打开编辑对话框前的表单数据加载处理
		$("#openEditRoleModal").on("click",function(event){
			if(selected.length!=1){
				handleAlerts("One and only one row can be edited.","warning","");		
				return false;				
			}
			else{
				var data = oTable.api().row($("tr input:checked").parents('tr')).data();
				var roleId = data.roleId;
	            var roleName = data.roleName;
	            var pid  = data.pid;
	            var status=data.status;
	            $("#editRoleForm option").removeAttr("selected");
	            $("#editRoleForm :radio").removeAttr("checked");
	            $("#editRoleForm :radio").parents('span').removeClass("checked");
	            
	            $("#editRoleForm input[name='roleId']").val(roleId);
	            $("#editRoleForm input[name='roleName']").val(roleName);
	            $("#editRoleForm input[name='pid']").val(pid);	            
	            $("#editRoleForm :radio[name='status']").filter("[value='"+status+"']").attr("checked","true");
	            $("#editRoleForm :radio[name='status']").filter("[value='"+status+"']").parents('span').addClass("checked");
			}
			
		});	
		
		//打开编辑分配角色权限的对话框
		$("#openRoleRigthsModal").on("click",function(event){
			if(selected.length!=1){
				handleAlerts("One and only one row can be edited.","warning","");		
				return false;				
			}
			else{
				var data = oTable.api().row($("tr input:checked").parents('tr')).data();
				var roleId = data.roleId;
				var roleRights="0";
				
				if(data.adminRoleRights){
					roleRights= (data.adminRoleRights.roleRights*1).toString(2);
				}
				var allrightslength=roleRights.length;
				$("#editRoleRightsForm input[name='roleId']").val(roleId); 
				//$("#editRoleRightsForm input[name='roleRights']").val(roleRights); 
				
				var allRightsBox=$("#editRoleRightsForm :checkbox");
				allRightsBox.removeAttr("checked");
				allRightsBox.parents('span').removeClass("checked");
				jQuery(allRightsBox).each(function () {
					
					var rightsVal=($(this).val()*1).toString(2);
					var ind=rightsVal.length;
					if(allrightslength>=ind){
					if(roleRights.substr(-ind,1)=="1"){					
						$(this).attr("checked","true");
						$(this).parents('span').addClass("checked");
					}
					}
				});	           	            
			}						
		});
		
		//分配角色权限表单提交操作
		$("#editRoleRightsForm").on("submit", function(event) {
			event.preventDefault();			
			$.ajax( {
				 "dataType": 'json', 
				 "type": "POST", 
				 "url": rootURI+"roles/editRoleRights", 
				 "data": $('#editRoleRightsForm').serialize(),
				 "success": function(resp,status){
					 if(status == "success"){  
						 if(resp.status){
							 selected=[];
				        	 oTable.api().draw();
				        	 handleAlerts("Edited the data successfully.","success","#editRoleRightsFormMsg");
						 }
						 else{
							 handleAlerts("Failed to edit the data.","danger","#editRoleRightsFormMsg");
						 }
					}             	 
				 },
				 "error":function(XMLHttpRequest, textStatus, errorThrown){
				    	 alert(errorThrown);
				  }
			});
			return false;
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
	            	var id = data.roleId;
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
            var id = data.roleId;
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
	var ajaxAddRole=function(){		
		$.ajax( {
         "dataType": 'json', 
         "type":'POST', 
         "url": rootURI+"roles/addRole", 
         "data": $('#addRoleForm').serialize(),
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
    var ajaxEditRole=function(){				
		$.ajax( {
		  "dataType": 'json', 
		 "type": "POST", 
		 "url": rootURI+"roles/editRole", 
		 "data": $('#editRoleForm').serialize(),
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
            var addform = $('#addRoleForm');
            var errorDiv = $('.alert-danger', addform);            

            addform.validate({
                errorElement: 'span', //default input error message container
                errorClass: 'help-block help-block-error', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                ignore: "",  // validate all fields including form hidden input                
                rules: {
                    roleName: {
                        minlength: 2,
                        maxlength: 40,
                        required: true
                    },
                    pid: {
                        required: true,
                        number: true                        
                    },
                    status:{
                    	required: true
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

                success: function (label) {
                    label
                        .closest('.form-group').removeClass('has-error'); // set success class to the control group
                },

                submitHandler: function (form) {                	
                    errorDiv.hide();
                    ajaxAddRole();                    
                }
            });
    };

  //处理表单验证方法
    var editFormValidation = function() {
        var editform = $('#editRoleForm');
        var errorDiv = $('.alert-danger', editform);            

        editform.validate({
            errorElement: 'span', //default input error message container
            errorClass: 'help-block help-block-error', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            ignore: "",  // validate all fields including form hidden input                
            rules: {
            	roleName: {
                    minlength: 2,
                    maxlength: 40,
                    required: true
                },
                pid: {
                    required: true,
                    number: true                        
                },
                status:{
                	required: true
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
                ajaxEditRole();                    
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