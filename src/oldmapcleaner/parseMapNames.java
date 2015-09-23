/*
 * GNU GPL v3.
 * (C) 2015 PlayerO1
 * 
 * This programm help to search and remove not using old map from Spring RTS.
 * 
 * The Spring engine folder with autodownload maps can be
 *  1. $home/.config/spring/
 *  2. $home/.spring/
 * 
 * The following folder in Spring data path can contain next folders with map data:
 *  1. /maps/*.sd7 (map folder)
 *  2. /demos/*.sdf (demo files, the file name is "date_time_mapName_springVersion.sdf")
 *  3. /cache/paths/*.pe.zip (path cashe, file name contain from mapName+number+".pe.zip")
 *  4. /cache/QTPFS/* (path cashe for alternative path finder)
 *  5. /cache/analyzedResourceMaps/ (cashe for AI and same LUA metal spots detector, can be not contain information for same maps)
 * 
 * The Spring Lobby stored same information about map:
 *  1. cashe $home/.springlobby/cache/ contains same information about mod, side and maps:
 *    - *.infoex
 *    - *.minimap.png
 *    - *.metalmap.png
 *    - *.heightmap.png
 * 
 *  2. at the chat log can be found by autohost help "Map link: http://springfiles.com/search_result.php?search=1944_Village_Crossing_v2&select=select_all"
 *    - $home/.springlobby/chatlog/Official server
 *    - /lobby/SpringLobby/chatlog/Official server/*.txt
 * 
 * The NOTA Lobby stored same cashe about map in /notalobby/maps_cache/*.qmc
 * 
 */
package oldmapcleaner;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author PlayerO1
 */
public class parseMapNames {
    
    /**
     * Search name of demo files .sdf and substring map name from file name.
     * @param pathTo demo folder, usualy "/home/user2/.spring/demos" or "/home/user2/.config/spring/demos".
     * @return list of map name, can be duplicated
     */
    public static ArrayList<String> parseFromDemosFolder(String pathTo) {
        File demoPath=new File(pathTo);
        if (!(demoPath.isDirectory() && demoPath.exists()))
            return null;
        final String demoFileExtention=".sdf";
        ArrayList<String> mapLst=new ArrayList<String>();
        for (String fname:demoPath.list()) if (fname.endsWith(demoFileExtention))
        {
            int p1=fname.indexOf("_"); // date
            int p2=fname.indexOf("_",p1+1); // time
            int p3=fname.lastIndexOf("_");
            String mname=fname.substring(p2+1,p3);
            mapLst.add(mname);
        }        
        return mapLst;
    }
    
    /**
     * search map from demo files
     * @param pathTo
     * @return 
     */
    public static ArrayList<MapInfo> parseMIFromDemosFolder(String pathTo) {
        File demoPath=new File(pathTo);
        if (!(demoPath.isDirectory() && demoPath.exists()))
            return null;
        final String demoFileExtention=".sdf";
        ArrayList<MapInfo> mapLst=new ArrayList<MapInfo>();
        for (File f:demoPath.listFiles()) if (f.isFile()){
            String fname=f.getName();
            if (fname.endsWith(demoFileExtention))
            {
                int p1=fname.indexOf("_"); // date
                int p2=fname.indexOf("_",p1+1); // time
                int p3=fname.lastIndexOf("_");
                String mname=fname.substring(p2+1,p3);
                MapInfo mi=new MapInfo(mname, new Date(f.lastModified()));
                int eqlI=mapLst.indexOf(mi);
                
                if (eqlI!=-1) {
                    MapInfo oldM=mapLst.get(eqlI);
                    oldM.merge(mi);
                } else {
                    mapLst.add(mi);
                }
            } 
        }
        return mapLst;
    }

    /**
     * Read sd7 files name from map folder.
     * @param pathTo
     * @return 
     */
    public static ArrayList<String> loadMapFiles(String pathTo) {
        File demoPath=new File(pathTo);
        if (!(demoPath.isDirectory() && demoPath.exists()))
            return null;
        final String mapFileExtention=".sd7";
        ArrayList<String> mapLst=new ArrayList<String>();
        for (String fname:demoPath.list()) if (fname.endsWith(mapFileExtention))
        {
            String mname=fname.substring(0,fname.length()-mapFileExtention.length());
            
            mapLst.add(mname);
        }        
        return mapLst;
    }
    
   public static ArrayList<MapInfo> loadMIMapFiles(String pathTo) {
        File demoPath=new File(pathTo);
        if (!(demoPath.isDirectory() && demoPath.exists()))
            return null;
        final String mapFileExtention=".sd7";
        ArrayList<MapInfo> mapLst=new ArrayList<MapInfo>();
        for (File f:demoPath.listFiles()) if (f.isFile()) {
            String fname=f.getName();
            if (fname.endsWith(mapFileExtention)) {
                String mname=fname.substring(0,fname.length()-mapFileExtention.length());
                MapInfo mi=new MapInfo(mname);
                mi.fileSize=f.length();
                mapLst.add(mi);
            }
        }        
        return mapLst;
    }
    
    public static ArrayList<MapInfo> convertToMI(ArrayList<String> lst) {
        ArrayList<MapInfo> mLst=new ArrayList<MapInfo>(lst.size());
        for (String mn:lst) mLst.add(new MapInfo(mn));// TODO delete duplicate
        return mLst;
    }
    public static ArrayList<String> convertMIToString(ArrayList<MapInfo> miLst) {
            ArrayList<String> sLst=new ArrayList<String>(miLst.size());
            for (MapInfo mi:miLst) sLst.add(mi.name);
            return sLst;
    }

    /**
    * Return full path to all stored information about map in disk (map file + lobby cashe)
    **/
    public static ArrayList<String> getExistMapFiles(String mapName, String mapDirectory, String lobbyCasheDirectory) {
            String outLst[]=new String[5];
            outLst[0]=mapDirectory+mapName+".sd7";
            outLst[1]=lobbyCasheDirectory+mapName+".infoex";
            outLst[2]=lobbyCasheDirectory+mapName+".minimap.png";
            outLst[3]=lobbyCasheDirectory+mapName+".metalmap.png";
            outLst[4]=lobbyCasheDirectory+mapName+".heightmap.png";
            ArrayList<String> existLst=new ArrayList<String>();
            for (int i=0;i<outLst.length;i++) if (new File(outLst[i]).exists()) existLst.add(outLst[i]);
            return existLst;
    }
}