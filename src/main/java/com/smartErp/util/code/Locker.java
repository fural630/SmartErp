package com.smartErp.util.code;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import com.smartErp.code.SystemInfo;

/*     */ public class Locker
/*     */ {
/*  24 */   private final String LOCK_FOLDER = "var/locks";
/*  25 */   private long delaySecs = 1800L;
/*     */   private String lockFile;
/*     */   private String lockTag;
/*  28 */   private MyDate mydate = new MyDate();
/*     */   
/*     */   public Locker(String lockName) {
/*  31 */     this.lockFile = (SystemInfo.getAppPath() + "var/locks" + "/" + lockName);
/*     */   }
/*     */   
/*     */   public Boolean getLock(Boolean deleteLockFile) {
/*     */     try {
/*  36 */       File file = new File(this.lockFile);
/*     */       
/*  38 */       if (file.exists())
/*     */       {
/*  40 */         FileReader lockerReader = new FileReader(file);
/*  41 */         BufferedReader lockerBuffer = new BufferedReader(lockerReader);
/*  42 */         String line = lockerBuffer.readLine();
/*  43 */         long secs = this.mydate.dateTimeDiffer(this.mydate.getCurrentDateTime(), line);
/*     */         
/*  45 */         if ((deleteLockFile.booleanValue()) && (secs >= getDelaySecs())) {
/*  46 */           return unLock();
/*     */         }
/*  48 */         return Boolean.valueOf(false); }
/*  49 */       if (checkLockTagExists().booleanValue()) {
/*  50 */         return Boolean.valueOf(false);
/*     */       }
/*     */     } catch (Exception e) {
/*  53 */       e.printStackTrace();
/*     */       
/*  55 */       return Boolean.valueOf(false);
/*     */     }
/*     */     
/*  58 */     return Boolean.valueOf(true);
/*     */   }
/*     */   
/*     */   public Boolean checkLockTagExists() throws Exception {
/*  62 */     if (null != this.lockTag) {
/*  63 */       File path = new File(SystemInfo.getAppPath() + "var/locks");
/*  64 */       System.out.println("log: " + SystemInfo.getAppPath() + "var/locks");
/*  65 */       String[] fileList = path.list();
/*  66 */       if (null == fileList) {
/*  67 */         return Boolean.valueOf(false);
/*     */       }
/*  69 */       for (String f : fileList) {
/*  70 */         if (f.indexOf(this.lockTag) == 0) {
/*  71 */           File file = new File(SystemInfo.getAppPath() + "var/locks" + "/" + f);
/*  72 */           FileReader lockerReader = new FileReader(file);
/*  73 */           BufferedReader lockerBuffer = new BufferedReader(lockerReader);
/*  74 */           String line = lockerBuffer.readLine();
/*  75 */           long secs = this.mydate.dateTimeDiffer(this.mydate.getCurrentDateTime(), line);
/*     */           
/*  77 */           if (secs >= 30L) {
/*  78 */             return Boolean.valueOf(true);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*  84 */     return Boolean.valueOf(false);
/*     */   }
/*     */   
/*     */   
/*     */   public Boolean lock(Boolean deleteLockFile) {
/* 104 */     if (!getLock(deleteLockFile).booleanValue()) {
/* 105 */       return Boolean.valueOf(false);
/*     */     }
/* 107 */     MyDate mydate = new MyDate();
/*     */     try {
/* 109 */       FileWriter fw = new FileWriter(this.lockFile);
/* 110 */       fw.write(mydate.getCurrentDateTime());
/* 111 */       fw.close();
/*     */     } catch (Exception e) {
/* 113 */       e.printStackTrace();
/* 114 */       return Boolean.valueOf(false);
/*     */     }
/*     */     
/* 117 */     return Boolean.valueOf(true);
/*     */   }
/*     */   
/*     */   public Boolean unLock() {
/*     */     try {
/* 122 */       File file = new File(this.lockFile);
/*     */       
/*     */ 
/* 125 */       if (!file.delete()) {
/* 126 */         return Boolean.valueOf(false);
/*     */       }
/*     */     } catch (Exception e) {
/* 129 */       e.printStackTrace();
/* 130 */       return Boolean.valueOf(false);
/*     */     }
/*     */     
/* 133 */     return Boolean.valueOf(true);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getDelaySecs()
/*     */   {
/* 142 */     return this.delaySecs;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDelaySecs(long delaySecs)
/*     */   {
/* 151 */     this.delaySecs = delaySecs;
/*     */   }
/*     */   
/*     */   public String getLockTag() {
/* 155 */     return this.lockTag;
/*     */   }
/*     */   
/*     */   public void setLockTag(String lockTag) {
/* 159 */     this.lockTag = lockTag;
/*     */   }
/*     */ }