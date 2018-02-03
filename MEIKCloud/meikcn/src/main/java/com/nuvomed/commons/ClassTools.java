package com.nuvomed.commons;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
public class ClassTools {
	@SuppressWarnings("rawtypes")
	public static List<Class> getClassByPackage(String packageName) throws ClassNotFoundException{
		List<Class> clazzs = null;
		try {
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			String packagePath = packageName.replace(".", File.separator);
			Enumeration<URL> resources = classLoader.getResources(packagePath);
			List<File> dir = null;
			if(resources != null){
				dir = new ArrayList<File>();
				while (resources.hasMoreElements()) {
					URL url = (URL) resources.nextElement();
					
					dir.add(new File(url.getPath().replace("%20", " ")));
				}
			}
			
			
			if(dir != null){
				clazzs = new ArrayList<Class>();
				for(File file :dir){
					clazzs.addAll(findClass(file,packagePath));
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return clazzs;
	}
	
	@SuppressWarnings("rawtypes")
	public static List<Class> findClass(File file ,String packagePath) throws ClassNotFoundException{
	     List<Class> classes = new ArrayList<Class>();
		if(!file.exists()){
		    return classes;
		}
		File [] files = file.listFiles();
		if(files != null){
			classes = new ArrayList<Class>();
			for(File f : files){
				if(f.isDirectory()){
					classes.addAll(findClass(f, packagePath));
				}else if(f.getName().endsWith(".class")){
					classes.add(Class.forName(packagePath.replace(File.separator, ".")+"."+f.getName().substring(0,f.getName().length()-6)));
				}
			}
		}
		return classes;
	}

}
