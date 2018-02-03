var rootURI="/";
var local="zh_CN";
var GroupsTable = function () {
	var oTable;
	var oGroupUserTable;
	var selected = [];
	var userSelected = [];
	var handleTable = function () {				
		var viewTable = function(groupId){	
			$("#groupId").val(groupId);
			var groupUserTable=$('#groupusers_table');
			oGroupUserTable = groupUserTable.dataTable({
				"lengthChange":false,
		    	"filter":false,
		    	"sort":false,
		    	"info":true,
		    	"bRetrieve": true,
		    	"processing":true,
		    	"bDestroy":true,
		        // set the initial value
		        "displayLength": 5,
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
	                    }	                                     
	                }],
		        "columns": [	
		               {"orderable": false },
		 	           {data: "adminId",defaultContent:""},
		 	           {data: "email",defaultContent:""},
		 	           {data: "roleName",defaultContent:""}		 	           
		 	        ],
     	        "serverSide": true,
     	        "serverMethod": "GET",
     	        "ajaxSource": rootURI+"groupuser/groupUserList/"+groupId+"?rand="+Math.random()
			});	
			
			//打开删除对话框前判断是否已选择要删除的行
			$("#openDeleteGroupUsersModal").on("click",function(event){
				if(userSelected.length==0){
					handleAlerts(loadProperties("error.delete.select",local,rootURI),"warning","");				
					return false;
				}
			});
			
			//删除操作
			$('#deleteUserBtn').on('click', function (e) {
				$.ajax( {
	             "dataType": 'json', 
	             "type": "DELETE", 
	             "url": rootURI+"groupuser/delete/"+userSelected.join(), 
	             "success": function(data,status){
	            	 if(status == "success"){					
						 if(data.status){
							 userSelected=[];						 
							 oGroupUserTable.api().draw();
							 oGroupUserTable.$('th span').removeClass();
			            	 handleAlerts(loadProperties("msg.groupuser.delete.success",local,rootURI),"success","");
						 }
						 else{
							 handleAlerts(loadProperties("msg.groupuser.delete.failed",local,rootURI) +data.info,"danger","");
						 }
					}             	 
	             },
	             "error":function(XMLHttpRequest, textStatus, errorThrown){
	            	 alert(errorThrown);
	             }
	           });
	        }); 
			
			//全选		
			$("#groupuser_chk").on('change',function () {
	            var set = jQuery(this).attr("data-set");
	            var checked = jQuery(this).is(":checked");
	            userSelected=[];
	            if(checked){            	
		            var api=oGroupUserTable.api();            
		            jQuery(set).each(function () {            	
		            	var data = api.row($(this).parents('tr')).data();
		            	var ids=data.id;
		                var index = $.inArray(ids, selected);
		                userSelected.push( ids );
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
			groupUserTable.on('change', 'tbody tr .checkboxes', function () {
	            $(this).parents('tr').toggleClass("active");            
	            var data = oGroupUserTable.api().row($(this).parents('tr')).data();
	            var id = data.id;
	            var index = $.inArray(id, userSelected);     
	            if ( index === -1 ) {
	            	userSelected.push( id );
	                $(this).parents('span').addClass("checked");
	                $(this).attr("checked","checked");
	            } else {
	            	userSelected.splice( index, 1 );
	                $(this).parents('span').removeClass("checked");
	                $(this).removeAttr("checked");
	            }
	        });
								    			
		};
	
		var table=$('#groups_table');
		 oTable = table.dataTable({
			"lengthChange":false,
        	"filter":true,
        	"sort":false,
        	"info":true,
        	"processing":true,        	
            "displayLength": 10,
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
                },
                {                	
                	'targets':-1,
                	'data':null,//定义列名
                	'render':function(data,type,row){                    	
                		return '<div class="actions"><a  class="btn btn-sm dark" data-toggle="modal"  href="#view_group" id="openGroupViewModal">'+loadProperties("dataTable.page.group.member",local,rootURI)+'</a></div>';
                    },
                    'class':'center'
                }
            ],
            "columns": [
               {"orderable": false },	           
	           {data: "groupName" },
	           {data: "groupInfo" },
	           { 'render':function(data,status,row){
	        				var tem = row.deleted;
	        				var str = '';
	        				if(tem){
	        					str = loadProperties("dataTable.page.disable",local,rootURI);
	        				}else{
	        					str = loadProperties("dataTable.page.enable",local,rootURI);
	        				}
	        				return str;
	        			}
	           },	           
	           { data: "createdTimeStr",defaultContent:""},	            
	           {"class":"center"},
	        ],
	        "serverSide": true,
	        "serverMethod": "GET",
	        "ajaxSource": rootURI+"group/groupList?rand="+Math.random(),
	        "fnDrawCallback":function(oSetting){
	        	selected=[];
	        }
		});		
		 
		//打开删除对话框前判断是否已选择要删除的行
		$("#openDeleteGroupsModal").on("click",function(event){
			if(selected.length==0){
				handleAlerts(loadProperties("error.delete.select",local,rootURI),"warning","");				
				return false;
			}
		});		
		
		$("#openActiveGroupsModal").on("click",function(event){
			if(selected.length==0){
				handleAlerts(loadProperties("error.active.select",local,rootURI),"warning","");				
				return false;
			}
		});
		$("#openDeactiveGroupsModal").on("click",function(event){
			if(selected.length==0){
				handleAlerts(loadProperties("error.deactive.select",local,rootURI),"warning","");				
				return false;
			}
		});
		//删除操作
		$('#deleteBtn').on('click', function (e) {
			$.ajax( {
             "dataType": 'json', 
             "type": "DELETE", 
             "url": rootURI+"group/delete/"+selected.join(), 
             "success": function(data,status){
            	 if(status == "success"){					
					 if(data.status){
						 selected=[];						 
		            	 oTable.api().draw();
		            	 oTable.$('th span').removeClass();
		            	 handleAlerts(loadProperties("msg.group.delete.success",local,rootURI),"success","");
					 }
					 else{
						 handleAlerts(loadProperties("msg.group.delete.failed",local,rootURI) +data.info,"danger","");
					 }
				}             	 
             },
             "error":function(XMLHttpRequest, textStatus, errorThrown){
            	 alert(errorThrown);
             }
           });
        });  
		
		//激活规则
		$('#activateBtn').on('click', function (e) {
			$.ajax( {
             "dataType": 'json', 
             "type": "POST", 
             "url": rootURI+"group/activate/"+selected.join(), 
             "success": function(data,status){
            	 if(status == "success"){					
					 if(data.status){
						 selected=[];						 
		            	oTable.api().draw();
		            	oTable.$('th span').removeClass();
		            	 handleAlerts(loadProperties("msg.group.active.success",local,rootURI),"success","");
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
		//禁用规则
		$('#deactivateBtn').on('click', function (e) {
			$.ajax( {
             "dataType": 'json', 
             "type": "POST", 
             "url": rootURI+"group/deactivate/"+selected.join(), 
             "success": function(data,status){
            	 if(status == "success"){					
					 if(data.status){
						 selected=[];						 
		            	 oTable.api().draw();
		            	 oTable.$('th span').removeClass();
		            	 handleAlerts(loadProperties("msg.group.deactive.success",local,rootURI),"success","");
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
		//搜索表单提交操作
		$("#searchForm").on("submit", function(event) {
			event.preventDefault();
			var jsonData=$(this).serializeJson();
			var jsonDataStr=JSON.stringify(jsonData);			
			oTable.fnFilter(jsonDataStr);
			return false;
		});	
		$("#openEditGroupModal").on("click",function(event){
			if(selected.length>1){
				handleAlerts(loadProperties("error.edit.select",local,rootURI),"warning","");
				event.stopPropagation();
			}else if(selected.length==0)
			{
				handleAlerts(loadProperties("error.row.select",local,rootURI),"warning","");
				event.stopPropagation();
			}
			else{
				var data = oTable.api().row($("tr input:checked").parents('tr')).data();
	            var id = data.id;
	            var groupName =data.groupName;
	            var groupInfo=data.groupInfo;
	            var creatime=data.createdTime;
	            var deleted=data.deleted;
	            $("#editGroupForm input[name='id']").val(id);
	            $("#editGroupForm input[name='groupName']").val(groupName);
	            $("#editGroupForm input[name='groupInfo']").val(groupInfo);
	            $("#editGroupForm input[name='createdTime']").val(creatime);
	            $("#editGroupForm input[name='deleted']").val(deleted);
			}
		});
				           
		//全选		
		$("#group_chk").on('change',function () {
            var set = jQuery(this).attr("data-set");
            var checked = jQuery(this).is(":checked");
            selected=[];
            if(checked){            	
	            var api=oTable.api();            
	            jQuery(set).each(function () {            	
	            	var data = api.row($(this).parents('tr')).data();
	            	var ids=data.id;
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
            var id = data.id;
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
        
        //Action栏的单击事件
        table.on('click', 'tbody tr a',function(){
            var data = oTable.api().row($(this).parents('tr')).data();
           var ids=data.id;
           if(oGroupUserTable!=null){
        	   oGroupUserTable.fnDestroy();
        	   viewTable(ids); 
           }else{
        	   viewTable(ids);
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
	var AddGroup = function(){
		
		$.ajax( {
         "dataType": 'json', 
         "type":'POST', 
         "url": rootURI+"group/addGroup", 
         "data": $('#addGroupForm').serialize(),
         "success": function(resp,status){
        	 if(status == "success"){  
        		 if(resp.status){						 
	            	 oTable.api().draw();
	            	 handleAlerts(loadProperties("msg.group.add.success",local,rootURI),"success","");		            	 
				 }
				 else{
					 handleAlerts(loadProperties("msg.group.add.failed",local,rootURI)+resp.info,"danger","");						 
				 }
			}             	 
         },
         "error":function(XMLHttpRequest, textStatus, errorThrown){
        	 alert(errorThrown);
         }
       });
		$('#add_group').modal('hide');
    };
    
    var AddGroupValidation = function() {
        var form = $('#addGroupForm');
        var errorDiv = $('.alert-danger', form);            
        form.validate({
            errorElement: 'span', //default input error message container
            errorClass: 'help-block help-block-error', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            ignore: "",  // validate all fields including form hidden input                
            rules: {
             groupName: {
            	required: true,
            	minlength:3,
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
            onfocusout:function(element){
            	$(element).valid();
            },
            submitHandler: function (form) { 
            	errorDiv.hide();
            	AddGroup();
            }
        });
    };
    
	//编辑表单提交操作
	var EditGroup= function() {
	  $.ajax( {
         "dataType": 'json', 
         "type": "POST", 
         "url": rootURI+"group/editGroup", 
         "data" :$('#editGroupForm').serializeJson(),
         "success": function(resp,status){
        	 if(status == "success"){  
        		 if(resp.status){
					 selected=[];
	            	 oTable.api().draw();
	            	 handleAlerts(loadProperties("msg.group.edit.success",local,rootURI),"success","");
				 }
				 else{
					 handleAlerts(loadProperties("msg.group.edit.failed",local,rootURI)+resp.info,"danger","");
				 }
			}             	 
         },
         "error":function(XMLHttpRequest, textStatus, errorThrown){
        	 alert(errorThrown);
         }
       });
	  $('#edit_group').modal('hide');
	};
		
            
	var EditGroupValidation = function() {
		var form = $('#editGroupForm');
		var errorDiv = $('.alert-danger', form);            
		form.validate({
			errorElement: 'span', //default input error message container
			errorClass: 'help-block help-block-error', // default input error message class
			focusInvalid: false, // do not focus the last invalid input
			ignore: "",  // validate all fields including form hidden input                
			rules: {
				groupName: {
					required: true,
					minlength:3,
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
	        onfocusout:function(element){
	        	$(element).valid();
	        },
	        submitHandler: function (form) { 
	        	errorDiv.hide();
	        	EditGroup();
	        }
	    });
	};
    
	
	//添加操作
	var AddUsers = function(){				
		$.ajax( {
         "dataType": 'json', 
         "type":'POST', 
         "url": rootURI+"groupuser/addGroupUser", 
         "data": $('#addUsersForm').serialize(),
         "success": function(resp,status){
        	 if(status == "success"){  
        		 if(resp.status){
        			 $('#add_groupUser').modal('hide');
        			 userSelected=[];
        			 oGroupUserTable.api().draw();
	            	 handleAlerts(loadProperties("msg.groupuser.add.success",local,rootURI),"success","");		            	 
				 }
				 else{
					 handleAlerts(loadProperties("msg.groupuser.add.failed",local,rootURI)+resp.info,"danger","");						 
				 }
			}             	 
         },
         "error":function(XMLHttpRequest, textStatus, errorThrown){
        	 alert(errorThrown);
         }
       });
		
								 		 		 
    };
    
    var AddUsersValidation = function() {
        var form = $('#addUsersForm');
        var errorDiv = $('.alert-danger', form);            
        form.validate({
            errorElement: 'span', //default input error message container
            errorClass: 'help-block help-block-error', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            ignore: "",  // validate all fields including form hidden input                
            rules: {
             adminId: {
            	required: true,            	
                },
             

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
            	AddUsers();
            }
        });
    };

    return {
        //main function to initiate the module
        init: function (rootPath,local_value) {
        	rootURI=rootPath;
        	local=local_value; 
        	handleTable();  
        	AddGroupValidation();
        	EditGroupValidation(); 
        	AddUsersValidation();
        }

    };

}();
