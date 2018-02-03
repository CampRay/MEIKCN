package com.nuvomed.commons;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

/**
 * 图片生成或修改处理工具
 * @author Phills
 *
 */
public class ImageTools {
	private int imageWidth = 1088;// 图片的宽度    	    
	private int imageHeight = 730;// 图片的高度				
	
	//生成默认大小的图片
	public ImageTools() {		
	}	
	
	/** 
     * 生成默认大小的图片
     */  
    public BufferedImage createImage() {                      
        return new BufferedImage(imageWidth, imageHeight,BufferedImage.TYPE_INT_RGB);  
    } 
	
	/** 
     * 生成指定大小的图片
     */  
    public BufferedImage createImage(int width,int height) {     	
        return new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);  
    }  
	
	
	/** 
     * 导入本地图片到缓冲区 
     */  
    public BufferedImage loadImageLocal(String imgName) {  
        try {  
            return ImageIO.read(new File(imgName));  
        } catch (IOException e) {  
        	e.printStackTrace();
        }  
        return null;  
    }
    
    /** 
     * 导入本地图片到缓冲区 
     */  
    public BufferedImage loadImageLocal(File imgFile) {  
        try {  
            return ImageIO.read(imgFile);  
        } catch (IOException e) {  
        	e.printStackTrace();
        }  
        return null;  
    } 
  
    /** 
     * 导入网络图片到缓冲区 
     */  
    public BufferedImage loadImageUrl(String imgName) {  
        try {  
            URL url = new URL(imgName);  
            return ImageIO.read(url);  
        } catch (IOException e) {  
        	e.printStackTrace();
        }  
        return null;  
    }  
    
    /** 
     * 生成新图片到本地 
     */  
    public void SaveImage(BufferedImage img,String imagePath) {  
        if (imagePath != null && img != null) {  
            try {  
                File outputfile = new File(imagePath);  
                ImageIO.write(img, "jpg", outputfile);                 
            } catch (IOException e) {  
            	e.printStackTrace(); 
            }  
        }  
    }  
    
    /** 
     * 填充颜色
     */  
    public BufferedImage fillBackgroundColor(BufferedImage img,Color color) {  
        try {  
        	Graphics graphics = img.getGraphics();         	
        	graphics.setColor(color);    
            graphics.fillRect(0, 0, img.getWidth(), img.getHeight());  
            graphics.dispose();                        
        } catch (Exception e) {  
        	e.printStackTrace(); 
        }  
        return img;  
    }
    
    /** 
     * 填充颜色
     */  
    public BufferedImage fillColor(BufferedImage img,Color color,int x,int y,int width,int height) {  
        try {  
        	Graphics graphics = img.getGraphics();         	
        	graphics.setColor(color);    
            graphics.fillRect(x, y, width, height); 
            graphics.dispose();
        } catch (Exception e) {  
        	e.printStackTrace(); 
        }  
        return img;  
    }
	
	
	/** 
     * 修改图片,返回修改后的图片缓冲区（只输出一行文本） 
     */  
	public BufferedImage drawString(BufferedImage img,Object content,Color color, Font font, int x, int y) {  
  
        try {              
            //g = img.createGraphics();
            Graphics graphics = img.getGraphics();             
            graphics.setColor(color);//设置字体颜色  
            graphics.setFont(font);            
            if (content != null) {  
            	graphics.drawString(content.toString(), x, y);              	
            }  
            graphics.dispose();  
        } catch (Exception e) {  
        	e.printStackTrace(); 
        }  
        return img;  
    }
	
	/** 
     * 修改图片,返回修改后的图片缓冲区（在图片中画入另一图片） 
     */  
	public BufferedImage drawImage(BufferedImage img,BufferedImage pic, int x, int y) {  
  
        try {              
            //g = img.createGraphics();
            Graphics graphics = img.getGraphics();                              
            if (pic != null) {  
            	graphics.drawImage(pic, x, y, null);  
            }  
            graphics.dispose();  
        } catch (Exception e) {  
        	e.printStackTrace(); 
        }  
        return img;  
    }
	
	

}
