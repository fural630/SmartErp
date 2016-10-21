package com.smartErp.script.cdiscount;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CdiscountZipUtil {
	
	public static void compress(String source,String destinct) throws IOException { 
        List fileList = loadFilename(new File(source));
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(new File(destinct))); 
        
        byte[] buffere = new byte[8192]; 
        int length; 
        BufferedInputStream bis; 
        
        for(int i = 0; i < fileList.size(); i++) { 
            File file = (File) fileList.get(i); 
            zos.putNextEntry(new ZipEntry(getEntryName(source, file))); 
            bis = new BufferedInputStream(new FileInputStream(file)); 
            
            while (true) { 
                length = bis.read(buffere); 
                if (length == -1) break; 
                zos.write(buffere,0,length); 
            } 
            bis.close(); 
            zos.closeEntry(); 
        } 
        zos.close(); 
    } 
	
	public static String getEntryName(String base, File file) {
		File baseFile = new File(base);
		String filename = file.getPath();
		if (baseFile.getParentFile().getParentFile() == null) {
			return filename.substring(baseFile.getParent().length());
		}
		String resultEntryName = filename.substring(baseFile.getPath().length() + 1);
		System.out.println(resultEntryName);
		return resultEntryName;
	}
	
	public static List loadFilename(File file) {
		List filenameList = new ArrayList();
		if (file.isFile()) {
			filenameList.add(file);
		}
		if (file.isDirectory()) {
			for (File f : file.listFiles()) {
				filenameList.addAll(loadFilename(f));
			}
		}
		return filenameList;
	}
}