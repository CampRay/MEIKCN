var rootURI="/";
var local="en_US";
var isAdmin=false;

var SearchTable = function () {
	var oTable;
	var selected=[];
	var handleTable = function () {		
		var table=$('#search_table');
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
            "oLanguage": {
                "sProcessing": loadProperties("dataTable.page.process",local,rootURI),                
                "sZeroRecords":loadProperties("dataTable.page.data.zero",local,rootURI),
                "sEmptyTable": loadProperties("dataTable.page.data.empty",local,rootURI),
                "sInfo": loadProperties("dataTable.page.info",local,rootURI),
                "sInfoEmpty":loadProperties("dataTable.page.info.empty",local,rootURI),
            },            
            "columns": [               	         
               { data: "cid",defaultContent:""},
	           { data: "code",defaultContent:""},
	           { data: "clientName",defaultContent:""},	  
	           { data: "birthday",defaultContent:""},
	           { data: "mobile",defaultContent:""},
	           { data: "email",defaultContent:""},
	           { data: "createdTimeStr",defaultContent:""},	
	           { 'render':function(data,status,row){
                    var tem = row.type;
	   				var str = '';
	   				if(tem==7){	   						   					
	   					str = loadProperties("dataTable.page.status.end",local,rootURI);
	   				}
	   				else{
	   					str = loadProperties("dataTable.page.status.process",local,rootURI);
	   				}
	   				return str;
	   			  }
	           },
	           {'render':function(data,status,row){  
	        	   var tem = row.type;
	   				var str = '';	   					   					        	   
	        	    var viewurl = rootURI+"viewreg?id="+row.userId;
  					str =str+ '<a class="btn btn-sm dark"  style="display:inline;" href="'+viewurl+'">['+loadProperties("dataTable.page.view",local,rootURI)+']</a>';
  					
  					if(tem==7){	   					
	   					var url = rootURI+"jobs/download/"+row.jobId;
	   					str =str+ '<a class="btn btn-sm dark" style="display:inline;"  href="'+url+'">['+loadProperties("dataTable.page.download",local,rootURI)+']</a>';
	   				}
	   				return str;
					}
		  }
	        ],
	        "serverSide": true,
	        "serverMethod": "GET",
	        "ajaxSource": rootURI+"searchClientsList?rand="+Math.random(),
	        "fnDrawCallback":function(oSetting){
	        	selected=[];
	        }

		});	
										
		//搜索表单提交操作
		$("#searchBtn").on("click", function(event) {
			event.preventDefault();
			var jsonData=$('#searchForm').serializeJson();			
			var jsonDataStr=JSON.stringify(jsonData);			
			oTable.fnFilter(jsonDataStr);
			return false;
		});	
				
		//禁用规则
		$('#regBtn').on('click', function (e) {
			if(oTable.api().row().length>0){
			  var data = oTable.api().row(0).data();
	          var userId = data.userId;
	          location.href=rootURI+"viewreg?id="+userId;
			}
			else{
				location.href=rootURI+"reg";
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
        init: function (rootPath,local_value) {
        	rootURI=rootPath;
        	local=local_value;        	
        	handleTable();    
        	datePicker();
        }

    };

}();