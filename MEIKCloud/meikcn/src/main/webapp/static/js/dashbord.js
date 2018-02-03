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
var Dashbord = function () {
	
	var dd,categories;
	
	var handleTable = function () {
		var selected = [];
		var table=$('#rights_table');
		var oTable = table.dataTable({
			"lengthChange":false,
        	"filter":false,
        	"sort":false,
        	"info":true,
        	"processing":true,                
            // set the initial value
            "displayLength": 10,
            "dom": "<'row'<'col-md-6'l><'col-md-6'f>r>t<'row'<'col-md-6'i><'col-md-6'p>>",
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
                },
                {                	
                	'targets':-1,
                	'data':null,//定义列名
                	'render':function(data,type,row){
                    	return '<button>View</button>';
                    },
                    'class':'center'
                }
            ],
            "columns": [
               {"orderable": false },
	           { title: "ID",   data: "nodeId" },
	           { title: "Bit Flag",   data: "bitFlag" },
	           { title: "Rights Name",  data: "name"},
	           { title: "URI", data: "uri" },
	           { title: "Request Method", data: "method" },
	           { title: "Parent ID",  data: "pid" },
	           { title: "Is Menu",    data: "isMenu" },
	           { title: "Group Name",    data: "groupName" },
	           { title: "Group Sort",    data: "groupSort" },
	           { title: "Status",    data: "status" },
	           { title: "Action" ,"class":"center"}
	        ],
	        "serverSide": true,
	        "serverMethod": "GET",
	        "ajaxSource": rootURI+"rightsList?rand="+Math.random(),
//	        "fnServerData": function ( sSource, aoData, fnCallback, oSettings ) {
//	           $.ajax( {
//	             "dataType": 'json', 
//	             "type": "POST", 
//	             "url": sSource, 
//	             "data": aoData,
////	             "contentType":"application/json",
//	             "success": function(resp){ 		            	
//	            	 fnCallback(resp);
//	             },
//	             "error":function(XMLHttpRequest, textStatus, errorThrown){
//	            	 alert(errorThrown);
//	             }
//	           } );
//	         },
//	        "fnServerParams": function ( aoData ) {
//	           aoData.push( { "name": "more_data", "value": "my_value" } );
//	         },
//	        "fnRowCallback": function( nRow, aData, iDisplayIndex ) {				
//	        	$('td:eq(0)', nRow).html( '<input type="checkbox" class="checkboxes" value="1"/>' );
//				return nRow;
//			},

		});		
};
	 var systemUserAmount = function(){
		
		 $.ajax({
			 type:"GET",
			 url:rootURI+"home/getAmount?id=0&rand="+Math.random(),
			 dataType:"json",
			 success:function(data){
				var e=eval(data);
				if(e.status){
				    $("#system_user").html(e.amount);
				 }else{
					alert(e.info);
				 }
			 }
		     
		 });
	 };	
	 
	 var pointUserAmount = function(){
			
		 $.ajax({
			 type:"GET",
			 url:rootURI+"home/getAmount?id=1&rand="+Math.random(),
			 dataType:"json",
			 success:function(data){
				var e=eval(data);
				if(e.status){
				    $("#point_user").html(e.amount);
				 }else{
					alert(e.info);
				 }
			 }
		     
		 });
	 };
	 
	 var pointRuleAmount = function(){
			
		 $.ajax({
			 type:"GET",
			 url:rootURI+"home/getAmount?id=2&rand="+Math.random(),
			 dataType:"json",
			 success:function(data){
				var e=eval(data);
				if(e.status){
					
				    $("#point_rule").html(e.amount);
				 }else{
					alert(e.info);
				 }
			 }
		     
		 });
	 };
	 
	 //获得系统内存信息
	 var systemMemChart=function() {
			
			Highcharts.setOptions({
				global : {
					useUTC : false
				}
			});
			
			// Create the chart
			$('#chart_mem').highcharts('StockChart', {
				
				chart : {
					events : {
						load : function() {

							// set up the updating of the chart each second
							
							var series = this.series[0];
							setInterval(function() {
								var x = (new Date()).getTime();// current time
							    //y;
								$.ajax({
									type:"GET",
									url:rootURI+"home/getMemStatus?rand="+Math.random(),
									dataType:"json",
									success:function(data){
									    
										var e = eval(data);
									    var m=e.m;
									    series.addPoint([x, m], true, true);
									}
								});
								
								
							}, 1000);
						}
					}
				},
				yAxis: {
					minorTickInterval: 'auto',
					lineColor: '#000',
					lineWidth: 1,
					tickWidth: 1,
					tickColor: '#000',
					labels: {
						style: {
							color: '#000',
							font: '11px Trebuchet MS, Verdana, sans-serif'
						}
					},
					title: {
						style: {
							color: '#333',
							fontWeight: 'bold',
							fontSize: '12px',
							fontFamily: 'Trebuchet MS, Verdana, sans-serif'
						}
					}
				},
				rangeSelector: {
					buttons: [{
						count: 1,
						type: 'minute',
						text: '1M'
					}, {
						count: 5,
						type: 'minute',
						text: '5M'
					}, {
						type: 'all',
						text: 'All'
					}],
					inputEnabled: false,
					selected: 0
				},
				exporting: {
					enabled: false
				},
				series : [{
					name : 'data',
					data : (function() {
						// generate an array of random data
						var data = [], time = (new Date()).getTime(), i;

						for( i = -999; i <= 0; i++) {
							data.push([
								time + i * 1000,
								0
							]);
						}
						return data;
					})()
				}]
			});

		};
	     
		//获得系统CPU信息
		 var systemCpuChart=function() {
				
				Highcharts.setOptions({
					global : {
						useUTC : false
					}
				});
				
				// Create the chart
				$('#chart_cpu').highcharts('StockChart', {
					
					chart : {
						events : {
							load : function() {

								// set up the updating of the chart each second
								
								var series = this.series[0];
								
								setInterval(function() {
									var x = (new Date()).getTime();// current time
									$.ajax({
										type:"GET",
										url:rootURI+"/home/getCpuStatus?rand="+Math.random(),
										dataType:"json",
										success:function(data){
											
											var e = eval(data);
											var c=e.cpu;
											series.addPoint([x, c], true, true);
										}
									});
									
									
								}, 1000);
							}
						}
					},
					yAxis: {
						minorTickInterval: 'auto',
						lineColor: '#000',
						lineWidth: 1,
						tickWidth: 1,
						tickColor: '#000',
						labels: {
							style: {
								color: '#000',
								font: '11px Trebuchet MS, Verdana, sans-serif'
							}
						},
						title: {
							style: {
								color: '#333',
								fontWeight: 'bold',
								fontSize: '12px',
								fontFamily: 'Trebuchet MS, Verdana, sans-serif'
							}
						}
					},
					rangeSelector: {
						buttons: [{
							count: 1,
							type: 'minute',
							text: '1M'
						}, {
							count: 5,
							type: 'minute',
							text: '5M'
						}, {
							type: 'all',
							text: 'All'
						}],
						inputEnabled: false,
						selected: 0
					},
					exporting: {
						enabled: false
					},
					series : [{
						name : 'data',
						data : (function() {
							// generate an array of random data
							var data = [], time = (new Date()).getTime(), i;

							for( i = -999; i <= 0; i++) {
								data.push([
									time + i * 1000,
									0
								]);
							}
							return data;
						})()
					}]
				});

			};
			
		var getInterfaceData=function(){
			$.ajax({
		    	type:"GET",
		    	url:rootURI+"/home/getInterfaceStatus?rand="+Math.random(),
		    	dataType:"json",
		    	success:function(data){
		    		categories=data.categories;
		    		dd=data.data;
		    		interfaceChart();
		    	}
		    	
		    });
			
		};	
		var interfaceChart=function () {
		    
		    var colors = Highcharts.getOptions().colors,name = '';
		    function setChart(name, categories, data, color) {
		    chart.xAxis[0].setCategories(categories, false);
			chart.series[0].remove(false);
			chart.addSeries({
				name: name,
				data: data,
				color: color || 'white'
			}, false);
			chart.redraw();
		    }
		    var chart = $('#interface_chart').highcharts({
		        chart: {
		            type: 'column'
		        },
		        title: {
		            text: ''
		        },
		        subtitle: {
		            text: ''
		        },
		        xAxis: {
		            categories: categories
		        },
		        yAxis: {
		            title: {
		                text: 'The total number of visits'
		            }
		        },
		        plotOptions: {
		            column: {
		                cursor: 'pointer',
		                point: {
//		                    events: {
//		                        click: function() {
//		                            var drilldown = this.drilldown;
//		                            if (drilldown) { // drill down
//		                                setChart(drilldown.name, drilldown.categories, drilldown.data, drilldown.color);
//		                            } else { // restore
//		                                setChart(name, categories, data);
//		                            }
//		                        }
//		                    }
		                },
		                dataLabels: {
		                    enabled: true,
		                    color: colors[1],
		                    style: {
		                        fontWeight: 'bold'
		                    },
		                    formatter: function() {
		                        return this.y;
		                    }
		                }
		            }
		        },
		        tooltip: {
		            formatter: function() {
		                var point = this.point,
		                    s = this.x +':<b>'+ this.y;
		             //   if (point.drilldown) {
		             //       s += 'Click to view '+ point.category +' versions';
		             //   } else {
		             //       s += 'Click to return to browser brands';
		             //   }
		                return s;
		            }
		        },
		        series: [{
		            name: name,
		            data: dd,
		            color: 'white'
		        }],
		        exporting: {
		            enabled: false
		        }
		    })
		     .highcharts(); // return chart
		};
		
		
			
			
	 return {
        //main function to initiate the module
        init: function (rootPath) {
        	rootURI=rootPath;
        	//handleTable();  
        	systemUserAmount();
        	pointUserAmount();
        	pointRuleAmount();
//          systemCpuChart();
//          systemMemChart();
//          getInterfaceData();
           }

    };

}();