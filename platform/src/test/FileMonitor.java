package test;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class FileMonitor {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//
		FileMonitor fm = new FileMonitor();
		fm.monitor(new File("D:\\Sun\\tomcat-6.0.20\\webapps\\platform\\resource\\videofile\\"));
		fm.listFile();
	}

	private Map<Long , File> fileMap= new TreeMap<Long , File>();
	
	private void listFile(){
		
		for(Long key :fileMap.keySet())
		{
			File file = fileMap.get(key);
			if(file.isDirectory()){				
				System.out.println(key.toString() + " isDirectory"+ file.getName());
			}else{
				System.out.println(key.toString() + " isFile"+ file.getName() + file.getPath());
			}
		}
	}

	
	private void monitor(File pFile){
		File[] files = pFile.listFiles();
		Calendar lastModifiedTime = Calendar.getInstance();
		Calendar thirtyDaysBefore = Calendar.getInstance();
		
		thirtyDaysBefore.setTime(new Date());
		thirtyDaysBefore.add(Calendar.DATE, -30);
 
		int i =0;
		for(File file : files){
			Long key = file.lastModified()*10000 + i;
			i++;
			if(i>1000) i = 0;
			System.out.println(key);
			this.fileMap.put(key, file);
			
			if(file.isDirectory()){
				//lastModifiedTime.setTimeInMillis(file.lastModified());
				monitor(file);
			}else if(file.isFile()){
				//System.out.println(file.getName());
			}
		}
	}
}
