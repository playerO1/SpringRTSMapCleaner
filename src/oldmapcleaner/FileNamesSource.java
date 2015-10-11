/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oldmapcleaner;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import org.apache.commons.compress.archivers.sevenz.*;

/**
 * Facade/Interface for collect file names and info from native path/zip/7z.
 * 
 * @author PlayerO1
 */
public class FileNamesSource {
    
    public static List<FileInfo> getFileFrom(File path) {
        if (path==null) return null;
        if (!path.exists()) return null;
        if (path.isDirectory()) {
            File[] fArr=path.listFiles();
            List<FileInfo> fLst=new ArrayList<FileInfo>(fArr.length);
            for (File f:fArr) fLst.add(new FileInfo(f));
            return fLst;
        }
        if (path.isFile()) {
            switch (getArchiveType(path)) {
                case 1: //ZIP
                    return getFileInfoListZIP(path);
                    //break;
                case 2://7z
                    return getFileInfoList7Z(path);
                    //break;
                default: //TODO rename to IOException
                    System.err.println("Uncknown file format or wrong path: "+path.getName());
                    //throw new RuntimeException("Uncknown file format: "+path.getName());  
            }
        }
        return null;
    }
    
    /**
     * Check file type.
     * By file extension. TODO by content.
     * @param archive
     * @return -1 - uncknown, 1=ZIP, 2=7z
     */
    public static int getArchiveType(File archive) {
        String name=archive.getName();
        String nameExt=name.substring(name.lastIndexOf('.'));
        if (nameExt.equalsIgnoreCase(".ZIP")) return 1;
        if (nameExt.equalsIgnoreCase(".7Z")) return 2;
        return-1;
    }
    /*
    public static ArrayList<String> getFilesList1ZIP(String zipName) {
        ArrayList<String> fLst=new ArrayList<String>();
        ZipInputStream zin = null;
        try {
            zin = new ZipInputStream(new FileInputStream(zipName));
        } catch (FileNotFoundException e) {
            return null;
        }
        ZipEntry entry;
        try {
            while ( (entry = zin.getNextEntry()) != null) {
                fLst.add( entry.toString() );  //TODO data collect...
                System.out.println("Name: "+entry.getName()+"; size: "+entry.getSize()+"; time="+new Date(entry.getTime()));
                System.out.println("Name2: "+new File(entry.getName()).getName());
                //add info
                zin.closeEntry();
            }
        } catch (Exception e) {  //IOException
            e.printStackTrace();
        } finally {
            try {
                zin.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fLst;
    }
*/

    public static ArrayList<FileInfo> getFileInfoListZIP(File zipFile) {
        ArrayList<FileInfo> fLst=new ArrayList<FileInfo>();
        ZipFile zf;
        try {
            zf = new ZipFile(zipFile);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        try {
            List<? extends ZipEntry> iterableList=Collections.list(zf.entries()); // for itterable ovver Enumiration
            for (ZipEntry ze:iterableList) fLst.add(new FileInfo(ze));
        } finally {
            try {
                zf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fLst;
    }
    
    public static ArrayList<FileInfo> getFileInfoList7Z(File sevenZipFile) {
        SevenZFile sevenZFile;
        try {
            sevenZFile = new SevenZFile(sevenZipFile);
        } catch (IOException ex) {
            System.out.println(ex);
            return null;
        }
        ArrayList<FileInfo> fLst=new ArrayList<FileInfo>();
        try {
            SevenZArchiveEntry entry;
            //TODO too slow get names
            while( (entry = sevenZFile.getNextEntry()) != null)
                fLst.add(new FileInfo(entry));
        } catch (IOException ex) {
            System.out.println(ex);
        }
        finally {
            try {
                sevenZFile.close();
            } catch (IOException ex) {
                System.err.println(ex);
            }
        }
        return fLst;
    }

    /**
     * "interface" for different file attribute source.
     */
    public static class FileInfo{
        public final String filePath;
        public final String fileName;
        public final long fileSize;
        public final boolean isFile;
        public final boolean isDirectory;
        public final long date;

        public FileInfo(File fromFile) {
            this.filePath = fromFile.getPath();
            this.fileName = fromFile.getName();
            this.fileSize = fromFile.length();
            this.isFile = fromFile.isFile();
            this.isDirectory = fromFile.isDirectory();
            this.date = fromFile.lastModified();
        }
        
        public FileInfo(ZipEntry fromEntry) {
            this.filePath = new File(fromEntry.getName()).getPath();
            this.fileName = fromEntry.getName();
            this.fileSize = fromEntry.getSize();
            this.isFile = !fromEntry.isDirectory();
            this.isDirectory = fromEntry.isDirectory();
            this.date = fromEntry.getTime();
        }

        public FileInfo(SevenZArchiveEntry fromEntry) {
            this.filePath = new File(fromEntry.getName()).getPath();
            this.fileName = fromEntry.getName();
            this.fileSize = fromEntry.getSize();
            this.isFile = !fromEntry.isDirectory();//TODO what is fromEntry.isAntiItem()?
            this.isDirectory = fromEntry.isDirectory();
            if (fromEntry.getHasLastModifiedDate()) this.date = fromEntry.getLastModifiedDate().getTime();
            else if (fromEntry.getHasCreationDate()) this.date = fromEntry.getCreationDate().getTime();
            else this.date = 0; //TODO check date
        }
        
        //TODO from 7Z Entry
        
        public boolean isFile() {
            return isFile;
        }
        public String getName() {
            return fileName;
        }
        public long lastModified() {
            return date; //TODO it is created or last modified date?
        }
        public long length() {//TODO where is using?
            return fileSize;
        }
    }

}
