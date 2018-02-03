package com.nuvomed.core;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.color.DeviceCmyk;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.layout.property.VerticalAlignment;
import com.itextpdf.layout.renderer.CellRenderer;
import com.itextpdf.layout.renderer.DrawContext;
import com.nuvomed.commons.ConvertTools;
import com.nuvomed.commons.ImageTools;
import com.nuvomed.commons.IniFileTool;
import com.nuvomed.commons.MyApplicationContextUtil;
import com.nuvomed.commons.StringTool;
import com.nuvomed.commons.ZipUtil;
import com.nuvomed.dto.TadminJob;
import com.nuvomed.dto.Tuser;
import com.nuvomed.dto.TuserData;
import com.nuvomed.service.AdminJobService;
import com.nuvomed.service.UserDataService;
import com.nuvomed.service.UserService;


/**
 * 多线程并发处理任务类
 * @author Phills
 *
 */
public class MultiThreadHandler implements Runnable {
	
	private UserService userService;
	private UserDataService userDataService;	
	private AdminJobService adminJobService;
			
	private Tuser user;
	private String zipFilePath;
	private String unzipFilePath;
	private String rootPath=System.getProperty("meik.root");
	//zip文件存放目录
	private String unzipFolderPath=System.getProperty("meik.root")+File.separator+"screendata";
	//档案目录名
	private String folderName="";
	
	private int age=0;
	private String screenDate;
	private String techName;
	private String height;
	private String weight;
	//已读取的左乳数据数
	private int leftNum=0;
	//已读取的右乳数据数
	private int righttNum=0;
	
	public MultiThreadHandler(int userId) {
		userService=(UserService)MyApplicationContextUtil.getBeanById("userService");
		userDataService=(UserDataService)MyApplicationContextUtil.getBeanById("userDataService");
		adminJobService=(AdminJobService)MyApplicationContextUtil.getBeanById("adminJobService");
		this.user=userService.getUserById(userId);
	}

	public MultiThreadHandler(Tuser user) {
		this.user=user;
		userService=(UserService)MyApplicationContextUtil.getBeanById("userService");
		userDataService=(UserDataService)MyApplicationContextUtil.getBeanById("userDataService");
		adminJobService=(AdminJobService)MyApplicationContextUtil.getBeanById("adminJobService");		
	}
		
	
	public void run() {		
		try{
			if(user==null)return;
			//保存上传的zip文件到指定目录			
			TuserData userData=userDataService.loadScreenZip(user.getUserId());
			if(userData!=null){
				zipFilePath=unzipFolderPath+File.separator+userData.getFileName();
				folderName = userData.getFileName().substring(0, userData.getFileName().lastIndexOf("."));			
				unzipFilePath = unzipFolderPath + File.separator + folderName;
				
				File zipFile=new File(zipFilePath);
				if(!zipFile.getParentFile().exists()){
					if(!zipFile.getParentFile().mkdirs()){
						return;
					}
				}
		        OutputStream out = new FileOutputStream(zipFile);
		        out.write(userData.getStream());	                   
		        out.close();  
		        userData=null;		        
		        
				File unzipFolder=new File(unzipFilePath);
				//解包zip文件
				//ZipUtil.unzip(zipFilePath, unzipFilePath, false);													
				ZipUtil.unzip(zipFile, unzipFolder);
				
				//计算分析结果
				HashMap<String,Character> resultMap=ScreenResultAnalysis(unzipFilePath);					
				int lRes=-1;
				int rRes=-1;
				try{
					lRes=resultMap.get("L")==null?-1:Integer.parseInt(String.valueOf(resultMap.get("L")));
					rRes=resultMap.get("R")==null?-1:Integer.parseInt(String.valueOf(resultMap.get("R")));
				}
				catch(Exception exe){}
				user.setResult(lRes > rRes ? lRes : rRes);
				
				if(leftNum<2||righttNum<2){
					user.setMissingData(true);
				}
				else{
					user.setMissingData(false);
				}
				userService.updateUser(user);
				
				//如果有分析结果则生成报告
				if(user.getResult()>=0){
					buildPdfReport(lRes,rRes);
					TadminJob job=adminJobService.getAdminJobByCode(user.getCode());
					if(job!=null){
						job.setType(7);
						job.setAdminUser(null);
						job.setAssignTime(null);
						job.setCloseTime(System.currentTimeMillis());
						adminJobService.updateAdminJob(job);
					}
				}
								
			}
			else{
				return;
			}																								
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			
			File unzipFolder=new File(unzipFilePath);
			if(unzipFolder!=null&&unzipFolder.exists()){
				File[] fileList = unzipFolder.listFiles();	          
		         for(File f:fileList)
		         {	
		        	 if (!f.delete()) {
			            System.gc();    //回收资源
			            f.canWrite();
			            f.delete();
				      }		        	 
		         }
		         if(!unzipFolder.delete()){
		        	 System.gc();    //回收资源
		        	 unzipFolder.canWrite();
		        	 unzipFolder.delete();
		         }
			}
			
			File zipFile=new File(zipFilePath);
			if(zipFile.exists()){							
				if (!zipFile.delete()) {
					try {
						System.gc();    //回收资源
						Thread.sleep(10000);
					} catch (InterruptedException e) {						
						e.printStackTrace();
					}
		            System.gc();    //回收资源
		            zipFile.canWrite();
		            zipFile.delete();
		        }
			}
			
			
		}
		 		
	}
	
	/**
	 * 分析扫描结果
	 */
	public HashMap<String,Character> ScreenResultAnalysis(String archiveFolderPath){
		HashMap<String,Character> result=new HashMap<String,Character> ();
		try{			
			ImageTools imageTools =new ImageTools();
			BufferedImage bufferedImage=imageTools.createImage();
			bufferedImage=imageTools.fillBackgroundColor(bufferedImage, java.awt.Color.BLACK);
			bufferedImage=imageTools.fillColor(bufferedImage, java.awt.Color.GRAY,0,30,bufferedImage.getWidth(),1);
			//bufferedImage=imageTools.fillColor(bufferedImage, java.awt.Color.WHITE,0,0,bufferedImage.getWidth(),30);
			//写入图片头文字
			for (int n = 0; n < 7; n++) {
				String title="Layer "+(n+1)+" ("+(4+7*n)+" mm)";			
				bufferedImage=imageTools.drawString(bufferedImage, title, java.awt.Color.WHITE, new java.awt.Font("Helvetica", java.awt.Font.PLAIN,16), 154*n+30, 20);	
			}		
			
			File folder=new File(archiveFolderPath);
			int row=0;
			if(folder.exists()){
				if(folder.isDirectory()){				
					 File[] fileList = folder.listFiles();	 
					 
					 for (int index = fileList.length-1; index >=0; index--) {
						 File file=fileList[index];
			        	 //处理所有xml文件
			        	 if(file.getName().endsWith(".xml")){
			        		 String pos=getXmlResult(file,result);
			        		 if(pos!=null){
			        			 String layerTitle="L".equalsIgnoreCase(pos)?"Left Breast:":"Right Breast:";			
			        			 bufferedImage=imageTools.drawString(bufferedImage, layerTitle, java.awt.Color.WHITE, new java.awt.Font("Helvetica", java.awt.Font.PLAIN,14), 10, 174*row+50);
			        			 for (int i = 1; i <= 7; i++) {
		        					 String imgLayer=file.getAbsolutePath().replace(".xml", "_L"+i+".jpg");
		        					 File imgFile=new File(imgLayer);
		        					 if(imgFile.exists()){	        						 
		        						 bufferedImage=imageTools.drawImage(bufferedImage, imageTools.loadImageLocal(imgFile), 154*(i-1)+10, 174*row+60);		        						 
		        					 }
								}		        			 		        			 
			        			row++;
			        		 }
			        	 }			        	 
			         }
					 //刪除所有L1-L7的圖片
					 try{
						 for (File file : fileList) {
							 if(file.getName().endsWith(".jpg")){
								 file.delete();
							 }
						}
					 }
					 catch(Exception ex){}
				}
			}
			imageTools.SaveImage(bufferedImage,archiveFolderPath+ File.separator+user.getCode()+".jpg");
			bufferedImage=null;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 解析xml文件
	 * @param file
	 * @param result
	 * @return L or R
	 */
	private String getXmlResult(File file,HashMap<String,Character> result){
		String breast=null;
		try{
			SAXReader reader = new SAXReader();
	   		org.dom4j.Document fileDocument = reader.read(file);//读取xml文件
	   		Element root = fileDocument.getRootElement();//获取根元素  
	   		breast=root.elementText("Breast");
	   		//如果左右乳已读2份数据，则不用再读取
	   		if("L".equalsIgnoreCase(breast)){
	   			if(leftNum>=2){
	   				return null;
	   			}
	   			leftNum++;
	   		}
	   		else if("R".equalsIgnoreCase(breast)){
	   			if(righttNum>=2){
	   				return null;
	   			}
	   			righttNum++;
	   		}
	   		
	   		//String layer1=root.element("Layer1").getText();
	   		String layer2=root.elementText("Layer2");
	   		String layer3=root.elementText("Layer3");
	   		//String layer4=root.element("Layer4").getText();	   	
	   		Character oldVal=(Character)result.get(breast);	   		
	   		int index=0;
	   		while(index!=-1){
	   			index++;
	   			//查找所有2的值
	   			index=layer2.indexOf('2', index);
	   			int y=index/36;
	   			int x=index%36;	 
	   			int distance=(int)Math.sqrt((x-17)*(x-17)+(y-17)*(y-17));
   				if(distance>4&&distance<15){//设置半路径为15，不包括乳头中心4个单位
   					List<Character> list=new ArrayList<Character>();
   					list.add(layer3.charAt((y-1)*36+(x-1)));
   					list.add(layer3.charAt((y-1)*36+(x)));
   					list.add(layer3.charAt((y-1)*36+(x+1)));
   					list.add(layer3.charAt((y)*36+(x-1)));
   					list.add(layer3.charAt((y)*36+(x)));
   					list.add(layer3.charAt((y)*36+(x+1)));
   					list.add(layer3.charAt((y+1)*36+(x-1)));
   					list.add(layer3.charAt((y+1)*36+(x)));
   					list.add(layer3.charAt((y+1)*36+(x+1)));	   						   						   					
   					if(list.contains('2')){
   						if(oldVal==null||'2'>oldVal){
   							result.put(breast, '2');
   							oldVal='2';
   							break;
   						}
   					}
   				}   					   			
	   		}
	   		//如果分析Layer2结束，没有找到值为2的数据,则开始查找值为1的数据
			if(oldVal==null||oldVal<='0'){
				index=0;
				while(index!=-1){
					index++;
		   			//查找所有1的值
		   			index=layer2.indexOf('1', index);
		   			int y=index/36;
		   			int x=index%36;	 
		   			int distance=(int)Math.sqrt((x-17)*(x-17)+(y-17)*(y-17));
	   				if(distance>4&&distance<15){//设置半路径为15，不包括乳头中心4个单位
	   					List<Character> list=new ArrayList<Character>();
	   					list.add(layer3.charAt((y-1)*36+(x-1)));
	   					list.add(layer3.charAt((y-1)*36+(x)));
	   					list.add(layer3.charAt((y-1)*36+(x+1)));
	   					list.add(layer3.charAt((y)*36+(x-1)));
	   					list.add(layer3.charAt((y)*36+(x)));
	   					list.add(layer3.charAt((y)*36+(x+1)));
	   					list.add(layer3.charAt((y+1)*36+(x-1)));
	   					list.add(layer3.charAt((y+1)*36+(x)));
	   					list.add(layer3.charAt((y+1)*36+(x+1)));	   						   						   					
	   					if(list.contains('1')){
	   						if(oldVal==null||'1'>oldVal){
	   							result.put(breast, '1');
	   							oldVal='1';
	   							break;
	   						}
	   					}
	   				}   					   			
		   		}
			}
			if(oldVal==null){
				result.put(breast, '0');
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return breast;
	}
		
		
	/**
	 * 生成报告
	 * @param userData
	 */
	private void buildPdfReport(int leftVal,int rightVal){
		try{
			IniFileTool iniFileTool=new IniFileTool(unzipFilePath+File.separator +user.getCode()+".ini");
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");  
			// 指定一个日期  
			Date date = dateFormat.parse(user.getBirthday());  
			// 对 calendar 设置为 date 所定的日期  
			Calendar calendar = Calendar.getInstance();		      
			int year=calendar.get(Calendar.YEAR);
			calendar.setTime(date); 		      
			int birthYear=calendar.get(Calendar.YEAR);
			age=year-birthYear;
			String regDay=iniFileTool.get("Personal data", "registration date");
			String regMonth=iniFileTool.get("Personal data", "registration month");
			String regYear=iniFileTool.get("Personal data", "registration year");
			screenDate=regYear+"-"+regMonth+"-"+regDay;
			height=iniFileTool.get("Personal data", "height");
			weight=iniFileTool.get("Personal data", "weight");
			techName=iniFileTool.get("Report", "Technician Name");
									
			String pdfPath=unzipFilePath+File.separator+folderName+".pdf";
			PdfDocument pdf = new PdfDocument(new PdfWriter(pdfPath));
			Image bgImg = new Image(ImageDataFactory.create(rootPath+File.separator+"static"+File.separator+"images"+File.separator+"A4-bg.png"));
			Image titleImg = new Image(ImageDataFactory.create(rootPath+File.separator+"static"+File.separator+"images"+File.separator+"title.png"));
//			Image bgImg = new Image(ImageDataFactory.create(rootPath+File.separator+"A4-bg.png"));
//			Image titleImg = new Image(ImageDataFactory.create(rootPath+File.separator+"title.png"));
			IEventHandler handler = new TransparentImage(bgImg,titleImg);
			//添加Event处理器
			pdf.addEventHandler(PdfDocumentEvent.END_PAGE, handler);
			Document document = new Document(pdf,PageSize.A4);
			document.setTopMargin(115);
			document.setBottomMargin(35);
			// 加载中文字体			
			PdfFont fontUnicode =PdfFontFactory.createFont(rootPath+File.separator+"static"+File.separator+"font"+File.separator+"msyh.ttf", PdfEncodings.IDENTITY_H, false);
			Color color =new DeviceCmyk(0.90f, 0.58f, 0.44f, 0.02f);
			//设置表格
			Table table = new Table(new float[]{1,4});
			table.setWidthPercent(100).setHorizontalAlignment(HorizontalAlignment.CENTER)
			   .setTextAlignment(TextAlignment.LEFT).setFontColor(Color.BLACK);	
			
			Cell headCell = new Cell().setPadding(0).setMargin(0).setWidth(140).setTextAlignment(TextAlignment.CENTER).
					setVerticalAlignment(VerticalAlignment.MIDDLE).setFontColor(Color.WHITE)
					.setBorder(null).setFontSize(10);
			headCell.add(new Paragraph("客户资料").setFontSize(10).setFont(fontUnicode)).setNextRenderer(new RoundedCornersCellRenderer(headCell));
			table.addCell(headCell);	
			table.addCell(new Cell().setBorder(null));
			
			Cell cell = new Cell(1,2).setPaddingLeft(10).setPaddingTop(6).setPaddingBottom(6).setBorder(new SolidBorder(color,1)).setBackgroundColor(Color.WHITE).setFontSize(8);
			//cell.add(new Paragraph().setFont(fontUnicode).add("客户号码").add((StringTool.isEmptyOrNull(user.getCid())?"N/A":user.getCid())));
			cell.add(new Paragraph().setFont(fontUnicode).add("姓名: "+user.getClientName()));
			cell.add(new Paragraph().setFont(fontUnicode).add("年龄: "+age));
			cell.add(new Paragraph().setFont(fontUnicode).add("身高(cm): "+(StringTool.isEmptyOrNull(height)?"N/A":height)));
			cell.add(new Paragraph().setFont(fontUnicode).add("体重(kg): "+(StringTool.isEmptyOrNull(weight)?"N/A":weight)));
			cell.add(new Paragraph().setFont(fontUnicode).add("电话号码: "+(StringTool.isEmptyOrNull(user.getMobile())?"N/A":user.getMobile())));
			cell.add(new Paragraph().setFont(fontUnicode).add("电子邮箱 : "+(StringTool.isEmptyOrNull(user.getEmail())?"N/A":user.getEmail())));
			table.addCell(cell);
			
			Table table2 = new Table(new float[]{1,4});
			table2.setWidthPercent(100).setHorizontalAlignment(HorizontalAlignment.CENTER)
			   .setTextAlignment(TextAlignment.LEFT).setFontColor(Color.BLACK);	
			
			Cell headCell2 = new Cell().setPadding(0).setMargin(0).setWidth(126).setTextAlignment(TextAlignment.CENTER).
					setVerticalAlignment(VerticalAlignment.MIDDLE).setFontColor(Color.WHITE)
					.setBorder(null).setFontSize(10);
			headCell2.add(new Paragraph("检查记录").setFontSize(10).setFont(fontUnicode)).setNextRenderer(new RoundedCornersCellRenderer(headCell2));
			table2.addCell(headCell2);	
			table2.addCell(new Cell().setBorder(null));
			
			Cell cell2 = new Cell(1,2).setPaddingLeft(10).setPaddingTop(6).setPaddingBottom(6).setBorder(new SolidBorder(color,1)).setBackgroundColor(Color.WHITE).setFontSize(8);
			cell2.add(new Paragraph().setFont(fontUnicode).add("检测编号: "+user.getCode()));
			cell2.add(new Paragraph().setFont(fontUnicode).add("检测日期: "+screenDate));
			cell2.add(new Paragraph().setFont(fontUnicode).add("检测地点: "+(StringTool.isEmptyOrNull(user.getLocation())?"N/A":user.getLocation())));
			cell2.add(new Paragraph().setFont(fontUnicode).add("检测员名称: "+(StringTool.isEmptyOrNull(techName)?"N/A":techName)));		
			table2.addCell(cell2);
			document.add(table).add(new Paragraph().setHeight(3)).add(table2);
			
			
			Table table3 = new Table(new float[]{1,1,1,1,1});
			table3.setWidthPercent(100).setHorizontalAlignment(HorizontalAlignment.CENTER)
			   .setTextAlignment(TextAlignment.LEFT).setFontColor(Color.BLACK);	
			Cell headCell3 = new Cell().setPadding(0).setMargin(0).setWidth(126).setTextAlignment(TextAlignment.CENTER).
					setVerticalAlignment(VerticalAlignment.MIDDLE).setFontColor(Color.WHITE)
					.setBorder(null).setFontSize(10);
			headCell3.add(new Paragraph("扫描图像").setFontSize(10).setFont(fontUnicode)).setNextRenderer(new RoundedCornersCellRenderer(headCell3));			
			table3.addCell(headCell3);	
			table3.addCell(new Cell().setBorder(null)).addCell(new Cell().setBorder(null)).addCell(new Cell().setBorder(null)).addCell(new Cell().setBorder(null));
		    String imgFile=unzipFilePath+File.separator+user.getCode()+".jpg";	
			Cell cell3 = new Cell(1,5).setPaddingLeft(45).setPaddingTop(5).setPaddingBottom(5).setWidth(500).setHeight(290).setBorder(new SolidBorder(color,1)).setBackgroundColor(Color.WHITE).setFontSize(8).setHorizontalAlignment(HorizontalAlignment.CENTER);
			cell3.add(new Image(ImageDataFactory.create(imgFile)).scaleToFit(500, 290));			
			table3.addCell(cell3);
			
			Table table4 = new Table(new float[]{1,4});
			table4.setWidthPercent(100).setHorizontalAlignment(HorizontalAlignment.CENTER)
			   .setTextAlignment(TextAlignment.LEFT).setFontColor(Color.BLACK);	
			
			Cell headCell4 = new Cell().setPadding(0).setMargin(0).setWidth(126).setTextAlignment(TextAlignment.CENTER).
					setVerticalAlignment(VerticalAlignment.MIDDLE).setFontColor(Color.WHITE)
					.setBorder(null).setFontSize(10);
			headCell4.add(new Paragraph("结果分析").setFontSize(10).setFont(fontUnicode)).setNextRenderer(new RoundedCornersCellRenderer(headCell4));
			table4.addCell(headCell4);	
			table4.addCell(new Cell().setBorder(null));
						
		    String leftResult = leftVal == 2 ? "发现可疑病变" : (leftVal == 1 ? "发现良性病变" : "正常");
		    String rightResult = rightVal == 2 ? "发现可疑病变" : (rightVal == 1 ? "发现良性病变" : "正常");
			
			Cell cell4 = new Cell(1,2).setPaddingLeft(10).setPaddingRight(10).setPaddingTop(6).setPaddingBottom(6).setBorder(new SolidBorder(color,1)).setBackgroundColor(Color.WHITE).setFontSize(8);
			Table innerTable = new Table(UnitValue.createPercentArray(new float[]{4, 1})).setWidthPercent(100);
			innerTable.addCell(new Cell(1,2).setPadding(0).setMargin(0).setBorder(null).add(new Paragraph().setFont(fontUnicode).add("左乳: "+leftResult)));
			innerTable.addCell(new Cell(1,2).setPadding(0).setMargin(0).setBorder(null).add(new Paragraph().setFont(fontUnicode).add("右乳: "+rightResult)));
			innerTable.addCell(new Cell().setPadding(0).setMargin(0).setBorder(null));
			innerTable.addCell(new Cell().setPadding(0).setMargin(0).setBorder(null).setTextAlignment(TextAlignment.LEFT).add(new Paragraph().setFont(fontUnicode).add(screenDate).setBorderBottom(new SolidBorder(1))));
			innerTable.addCell(new Cell().setPadding(0).setMargin(0).setBorder(null));
			innerTable.addCell(new Cell().setPadding(0).setMargin(0).setBorder(null).setTextAlignment(TextAlignment.LEFT).add(new Paragraph().setFont(fontUnicode).add("日期")));
			cell4.add(innerTable);
			table4.addCell(cell4);
                        			
			
			//移除Event处理器
//			pdf.removeEventHandler(PdfDocumentEvent.START_PAGE, handler);
//			document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
			document.add(new Paragraph().setHeight(3)).add(table3).add(new Paragraph().setHeight(3)).add(table4);
			document.close();
			
			//上傳傳速報告到數據庫						
			InputStream inputStream=null;
			try {	
				inputStream =new FileInputStream(new File(pdfPath));
				byte [] fileBytes=ConvertTools.toByteArray(inputStream);		
				
				TuserData userDataPDF=userDataService.loadScreenPdfReport(user.getUserId());
				boolean noRecord=(userDataPDF==null);
				if(noRecord){
					userDataPDF=new TuserData();								
					
				}				
				userDataPDF.setFileName(folderName+".pdf");
				userDataPDF.setStream(fileBytes);	
				userDataPDF.setUserId(user.getUserId());
				userDataPDF.setDataType(5);
				if(noRecord){
					userDataService.createUserData(userDataPDF);
				}
				
				else{					
					userDataService.updateUserData(userDataPDF);
				}								
				
			} catch (Exception e) {			
				e.printStackTrace();
			}
			finally{
				if(inputStream!=null){
					try{
						inputStream.close();
					}
					catch(Exception ex){}
				}
			}
						
//			//如果自动分析结果为正常情况，则设置任务状态为结束
//			TadminJob adminJob=adminJobService.getAdminJobByCode(user.getCode());
//			//判断是否已经上传过数据
//			if(adminJob!=null){
//				if(user.getResult()<2){
//					adminJob.setStatus(true);
//					adminJob.setCloseTime(System.currentTimeMillis());
//					adminJobService.updateAdminJob(adminJob);
//				}
//			}
			
		}
		catch(Exception e){
			
		}
	}
			
	
	/**
	 * 處理多頁事件，主要生成每頁都顯示的內容
	 * @author Phills
	 *
	 */
	protected class TransparentImage implements IEventHandler {
	    //protected PdfExtGState gState;
	    protected Image bgImg;
	    protected Image titleImg;
	    public TransparentImage(Image bgImg,Image titleImg) {
	        this.bgImg = bgImg;
	        this.titleImg = titleImg;
	        //gState = new PdfExtGState().setFillOpacity(1.0f);
	    }
	    	    
	    public void handleEvent(Event event) {
	        PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
	        PdfDocument pdf = docEvent.getDocument();
	        PdfPage page = docEvent.getPage();
	        Rectangle pageSize = page.getPageSize();
	        PdfCanvas pdfCanvas = new PdfCanvas(
	            page.newContentStreamBefore(), page.getResources(), pdf);
	        Color color =new DeviceCmyk(0.90f, 0.58f, 0.44f, 0.02f);
	        PdfFont bold=null;
	        PdfFont fontUnicode =null;
			try {
				bold = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);
				fontUnicode =PdfFontFactory.createFont(rootPath+File.separator+"static"+File.separator+"font"+File.separator+"msyhbd.ttf", PdfEncodings.IDENTITY_H, false);
			} catch (IOException e) {
				e.printStackTrace();
			}
	        	        	        
	        //pdfCanvas.saveState().setExtGState(gState);
	        Canvas canvas = new Canvas(pdfCanvas, pdf, page.getPageSize());
	        canvas.add(bgImg.scaleAbsolute(pageSize.getWidth(), pageSize.getHeight()));	
	        canvas.close();
	        Canvas logoCanvas = new Canvas(pdfCanvas, pdf, new Rectangle(pageSize.getWidth()/2-41, pageSize.getTop()-50, 82, 22));
	        logoCanvas.add(titleImg.scaleToFit(82, 22));
	        logoCanvas.close(); 
	        Canvas titleCanvas = new Canvas(pdfCanvas, pdf, new Rectangle(pageSize.getWidth()/2-200, pageSize.getTop()-80, 400, 36));
	        titleCanvas.add(new Paragraph("快速乳房健康检测报告").setPadding(0).setMargin(0).setTextAlignment(TextAlignment.CENTER).setFont(fontUnicode).setFontSize(22).setFontColor(color));	        	       
	        titleCanvas.close();
	        Canvas titleCanvas2 = new Canvas(pdfCanvas, pdf, new Rectangle(pageSize.getWidth()/2-250, pageSize.getTop()-102, 500, 30));	        
	        titleCanvas2.add(new Paragraph("Rapid Breast Health Screening Report").setPadding(0).setMargin(0).setTextAlignment(TextAlignment.CENTER).setFont(bold).setFontSize(16).setFontColor(color));	        
	        titleCanvas2.close();
	        Canvas footCanvas = new Canvas(pdfCanvas, pdf, new Rectangle(0, -pageSize.getBottom(), pageSize.getWidth(), 30));	        
	        footCanvas.add(new Paragraph().setWidth(pageSize.getWidth()).setHeight(30).setBackgroundColor(color));        
	        footCanvas.close();
			
//	        pdfCanvas.beginText().setColor(color, false)    
//            .setFontAndSize(fontUnicode, 22)
//            .moveText(260, pageSize.getTop()-80)           
//            .showText("快速乳房健康檢測報告")
//            .moveText(260, pageSize.getTop()-100)           
//            .showText("Rapid Breast Health Screening Report")   
//            .endText();	 	        
	        
	        //pdfCanvas.restoreState();
	        pdfCanvas.release();
	    }
	}
	
	
	/**
	 * 生成顶部是圆角边框的单元格样式
	 * @author Phills
	 *
	 */
	private class RoundedCornersCellRenderer extends CellRenderer {
        public RoundedCornersCellRenderer(Cell modelElement) {
            super(modelElement);
        }

        @Override
        public void drawBorder(DrawContext drawContext) {
            Rectangle rectangle = getOccupiedAreaBBox();
            float llx = rectangle.getX();
            float lly = rectangle.getY();
            float urx = rectangle.getX() + getOccupiedAreaBBox().getWidth() ;
            float ury = rectangle.getY() + getOccupiedAreaBBox().getHeight();
            PdfCanvas canvas = drawContext.getCanvas();
            float r = 4;
            float b = 0.4477f;
            Color color =new DeviceCmyk(0.90f, 0.58f, 0.44f, 0.02f);
            canvas.setFillColor(color);
            canvas.moveTo(llx, lly).lineTo(urx, lly).lineTo(urx, ury - r)
                    .curveTo(urx, ury - r * b, urx - r * b, ury, urx - r, ury)
                    .lineTo(llx + r, ury)
                    .curveTo(llx + r * b, ury, llx, ury - r * b, llx, ury - r)
                    .lineTo(llx, lly).fill().stroke();
            super.drawBorder(drawContext);
        }
    }

}
