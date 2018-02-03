var rootURI="/";
var local="zh_CN";
var isAdmin=false;

var JobsTable = function () {
	var oTable;
	var selected=[];
	var handleTable = function () {		
		var table=$('#jobs_table');
		oTable = table.dataTable({
			"lengthChange":false,
        	"filter":true,
        	"sort":false,
        	"info":true,        	        	
        	"processing":true,                
            // set the initial value
            "displayLength": 30,
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
                    //'defaultContent':'<div class="checker"><span><input type="checkbox" class="checkboxes" value="1"/></span></div>'                    
                }
            ],
            "columns": [
               {"orderable": false },
	           { data: "jobId" ,bVisible:false},	
	           { data: "code",defaultContent:""},
	           { data: "clientName",defaultContent:""},
	           { data: "createdBy"},	           
	           { data: "createdTimeStr" },	
	           {'render':function(data,status,row){
			   		var str = '';
                   if(row.screenResult!=null){
	                   	var url = rootURI+"jobs/downloadScreenPdf/"+row.jobId;
	                   	var csvUrl = rootURI+"jobs/downloadScreenCsv/"+row.jobId;
	                   	var result="";
	                   	if(row.screenResult==0){
	                   		result=loadProperties("job.screen.result.normal",local,rootURI);	                    		
	                   	}
	                   	if(row.screenResult==1){
	                   		result=loadProperties("job.screen.result.benign",local,rootURI);	                    		
	                   	}
	                   	else if(row.screenResult==2){
	                   		result=loadProperties("job.screen.result.suspicious",local,rootURI);	                    		
	                   	}
	                   	
	                   	if(result!=""){
	                   		if(row.missingData){
	                   			str = '<div class="actions" style="font-style:italic;">'+result+": "+'<a class="btn btn-sm dark"  href="'+url+'" style="text-decoration: underline">PDF</a></div>';
	                   		}
	                   		else{
	                   			str = '<div class="actions">'+result+": "+'<a class="btn btn-sm dark"  href="'+url+'" style="text-decoration: underline">PDF</a></div>';
	                   		}
	                   	}
                   }		   						   				
	   			   return str;
				}
	           },
	           { data: "doctor",defaultContent:"" ,bVisible:isAdmin},
	           { data: "assignTimeStr",defaultContent:"" ,bVisible:isAdmin},	           
	           { data: "doneTimeStr",defaultContent:"" ,bVisible:isAdmin },	           	           
	           {'render':function(data,status,row){                  	
	   				var str = '';
	   				if(row.doneTime!=null){
	   					var url = rootURI+"jobs/download/"+row.jobId;
	   					str = '<div class="actions"><a class="btn btn-sm dark"  href="'+url+'" style="text-decoration: underline">PDF</a></div>'	   					
	   				}
	   				else{
	   					str ="";
	   				}
	   				return str;
					}
	           },
	           { 'render':function(data,status,row){
	                    var tem = row.type;
	    				var str = '';
	    				if(tem==1){
	    					str = loadProperties("job.status.data.uploaded",local,rootURI);
	    				}else if(tem==2){
	    					str = loadProperties("job.status.data.assigned",local,rootURI);
	    				}
	    				else if(tem==3){
	    					str = loadProperties("job.status.data.downloaded",local,rootURI);
	    				}
	    				else if(tem==4){
	    					str = loadProperties("job.status.report.uploaded",local,rootURI);
	    				}
	    				else if(tem==5){
	    					str = loadProperties("job.status.report.assigned",local,rootURI);
	    				}
	    				else if(tem==6){
	    					str = loadProperties("job.status.report.downloaded",local,rootURI);
	    				}
	    				else if(tem==7){
	    					str = loadProperties("job.status.report.closed",local,rootURI);
	    				}
	    				else{
	    					str = loadProperties("job.status.data.uploaded",local,rootURI);
	    				}
	    				return str;
	    			}
	           }			   			  
	        ],
	        "serverSide": true,
	        "serverMethod": "GET",
	        "ajaxSource": rootURI+"jobs/list?rand="+Math.random(),
	        "fnDrawCallback":function(oSetting){
	        	selected=[];
	        }

		});	
		
		//打开指定醫生对话框前判断是否已选择要删除的行
		$("#openAssignDoctorModal").on("click",function(event){
			if(selected.length==0){
				handleAlerts(loadProperties("error.jobs.select.assign",locale,rootURI),"warning","");				
				return false;
			}
		});

		//確定指定醫生操作
		$('#assignSubmit').on('click', function (e) {
			var jsonStr="ids="+selected.join()+"&doctor="+$("#doctorId").val();
			$.ajax( {
             "dataType": 'json', 
             "type": "POST", 
             "url": rootURI+"jobs/assign",             
             "data": jsonStr,
             "success": function(data,status){
            	 if(status == "success"){					
					 if(data.status){
						 selected=[];						 
						 oTable.api().draw();		            	 
		            	 handleAlerts("Assign the doctor successfully.","success","");
					 }
					 else{
						 handleAlerts("Failed to assign the doctor. Exception Message: " +data.info,"danger","");
					 }
				}             	 
             },
             "error":function(XMLHttpRequest, textStatus, errorThrown){
            	 alert(errorThrown);
             }
           });
			$("#assign_doctor").modal('hide');
        });  
		
		//打开删除对话框前判断是否已选择要删除的行
		$("#openDeleteJobsModal").on("click",function(event){
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
             "url": rootURI+"jobs/delete/"+selected.join(), 
             "success": function(data,status){
            	 if(status == "success"){					
					 if(data.status){
						 selected=[];						 
		            	 oTable.api().draw();
		            	 oTable.$('th span').removeClass();
					 }
					 else{
						 handleAlerts(loadProperties("msg.job.delete.failed",local,rootURI) +data.info,"danger","");
					 }
				}             	 
             },
             "error":function(XMLHttpRequest, textStatus, errorThrown){
            	 alert(errorThrown);
             }
           });
        });  
		
		//打开执行生成pdf对话框前判断是否已选择要执行的行
		$("#openPdfJobsModal").on("click",function(event){
			if(selected.length==0){
				handleAlerts(loadProperties("error.jobs.select.pdf",local,rootURI),"warning","");				
				return false;
			}
		});
		
		//确认执行生成pdf操作
		$('#pdfBtn').on('click', function (e) {
			$.ajax( {
             "dataType": 'json', 
             "type": "GET", 
             "url": rootURI+"jobs/autoPdf/"+selected.join(), 
             "success": function(data,status){
            	 if(status == "success"){					
					 if(data.status){
						 selected=[];						 
		            	 oTable.api().draw();
		            	 oTable.$('th span').removeClass();
					 }
					 else{
						 handleAlerts("Failed to create the PDF resport. Exception Message: " +data.info,"danger","");
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
				
                       
		//全选
        $(".group-checkable").on('change',function () {
            var set = jQuery(this).attr("data-set");
            var checked = jQuery(this).is(":checked");
            selected=[];
            if(checked){            	
	            var api=oTable.api();            
	            jQuery(set).each(function () {            	
	            	var data = api.row($(this).parents('tr')).data();
	            	var id = data.jobId;
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
            var id = data.jobId;
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
        init: function (rootPath,local_value,isadmin) {
        	rootURI=rootPath;
        	local=local_value;
        	isAdmin=isadmin;
        	handleTable(); 
        	datePicker();
        }

    };

}();