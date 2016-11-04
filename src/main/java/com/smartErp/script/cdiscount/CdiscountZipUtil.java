package com.smartErp.script.cdiscount;

import java.io.File;
import java.io.IOException;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.FileSet;

public class CdiscountZipUtil {
	
	/**
	 * @param zipSrc		//需要解压到的位置		/xxx/xx
	 * @param source	//要解压的文件路径		/xxx/xx.zip
	 * @throws IOException
	 */
	public static void compress(String source, String zipSrc) throws IOException { 
		File zipFile = new File(zipSrc);   
		File srcdir = new File(source);   
        if (!srcdir.exists()) {
            throw new RuntimeException(source + "不存在！");  
        }
        Project project = new Project();
        Zip zip = new Zip();
        zip.setProject(project);
        zip.setDestFile(zipFile);   
        FileSet fileSet = new FileSet();   
        fileSet.setProject(project);   
        fileSet.setDir(srcdir);   
        zip.addFileset(fileSet);   
        zip.execute();   
	}
}