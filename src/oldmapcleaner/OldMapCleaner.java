/*
 * Utils for find and remove old map in SPring RTS game.
 * (C) PlayerO1 2015 (http://github.com/playerO1)
 */
package oldmapcleaner;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import static oldmapcleaner.MapListGUI.UNUSED_LIMIT;

/**
 *
 * @author PlayerO1 (C) 2015 http://github.com/playerO1
 */
public class OldMapCleaner {

    public static String fileSizeToStr(long size) {
        if (size==0) return "0";
        String[] dimensionName={"b","KB","MB","GB","TB"};
        long[] dimensionM={1,1024,1024*1024,1024*1024*1024,1024*1024*1024*1024L};
        long absS=Math.abs(size);
        int d;
        for (d=dimensionM.length-1;d>0;d--) if (absS>=dimensionM[d]) break;
        return size*10/dimensionM[d]/10.0 + dimensionName[d];
    }
    
    public static String showPercent(long part, long all) {
        if (all==0) return "\u221e%"; // division by zero fix :)
        return (part*1000/all)/10.0+"%";
    }
    
    private final static ArrayList<MapUpdateListener> NOTIFY_LIST=new ArrayList<MapUpdateListener>();

    /**
     * Notify all window about list data update. Without fromEditor window.
     * @param fromEditor do not send update to this object
     * @param modifedObjects update date, or null if uncknown update or all was updated
     */
    public static void notifyListUpdate(final MapUpdateListener fromEditor,final List<MapInfo> modifedObjects) {
        for (MapUpdateListener listener:NOTIFY_LIST) 
            if (listener!=fromEditor) listener.onMapUpdate(modifedObjects);
/* //TODO check thread safe update
        new Thread() { 
            public void run() {
                for (MapUpdateListener listener:NOTIFY_LIST) 
                    if (listener!=fromEditor) synchronized (listener) {
                        listener.onMapUpdate(modifedObjects);
                    }
            }
        }.start(); */
    }
    
    /**
     * Notify all window about list data update. Without fromEditor window.
     */
    public static void notifyListUpdate(MapUpdateListener fromEditor, MapInfo modifedObject) {
        ArrayList<MapInfo> modifedLst=new ArrayList<MapInfo>(1);
        modifedLst.add(modifedObject);
        notifyListUpdate(fromEditor, modifedLst);
    }
    
    public static boolean addNotifyListener(MapUpdateListener fromEditor) {
        if (NOTIFY_LIST.contains(fromEditor)) return false;
        return NOTIFY_LIST.add(fromEditor);
    }
    public static boolean removeNotifyListener(MapUpdateListener fromEditor) {
        return NOTIFY_LIST.remove(fromEditor);
    }    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*
        String springPath="/home/user2/.config/spring/";
        String demosPath=springPath+"demos";
        String mapsPath=springPath+"maps";
        String lobbyMapCachePath="/home/user2/.springlobby/cache/";
        */

        DataSourceGUI.main(args);
        
        /*
        System.out.println("Scan path "+demosPath);

        //ArrayList<String> demos=parseMapNames.parseFromDemosFolder(demosPath);
        //System.out.println(demos);

        ArrayList<MapInfo> demos=parseMapNames.parseMIFromDemosFolder(demosPath);
        System.out.println("Maps in demos "+demos.size()+":"+demos);

        //ArrayList<String> mapNames=parseMapNames.loadMapFiles(mapsPath);
        //System.out.println(mapNames);
        ArrayList<MapInfo> maps=parseMapNames.loadMIMapFiles(mapsPath);
        System.out.println("Maps files "+maps.size()+":"+maps);

        System.out.println("No using map...");
        ArrayList<MapInfo> noUsingMaps=(ArrayList<MapInfo>)maps.clone();
        noUsingMaps.removeAll(demos); // or retainAll
        System.out.println(noUsingMaps.size()+"; "+noUsingMaps);

        applyStatistics(maps, demos, true); // initialize statistic to maps

        Collections.sort(maps, new SortByName()); //Sort test

        MapListGUI guiForm=new MapListGUI();
        guiForm.setMapList(maps, lobbyMapCachePath);
        guiForm.setVisible(true);
        
        System.out.println("End.");
        
        */
    }

    public static ArrayList<MapInfo> selectByDeleting(ArrayList<MapInfo> from,boolean whereToDelete) {
        ArrayList<MapInfo> out=new ArrayList<>();
        for (MapInfo mi:from) if (mi.markToDelete==whereToDelete) out.add(mi);
        return out;
    }
    
    /**
     * Copy count and first and last date to maps files
     * @param maps clear start date and apply new to this
     * @param demos copy from this
     * @param clearBeforeAdd if true - stat from maps slear, if false - stat from demos add to maps stat.
     */
    public static void applyStatistics(ArrayList<MapInfo> maps, ArrayList<MapInfo> demos, boolean clearBeforeAdd) {
        for (MapInfo map:maps) {
            int demoI=demos.indexOf(map);
            if (demoI!=-1) {
                MapInfo demo=demos.get(demoI);
                if (clearBeforeAdd) {
                    map.count=demo.count;
                    map.firstTime=demo.firstTime;
                    map.lastTime=demo.lastTime;
                } else {
                    map.merge(demo); // add statistic information
                }
            } else if (clearBeforeAdd) {
                map.count=0;
            }
        }
    }
    
    public static void autoChangeToDeleteStatus(ArrayList<MapInfo> maps) {
        for (MapInfo map:maps) if (map.count<=UNUSED_LIMIT) map.markToDelete=true;
        //TODO return modiffed
    }

    
    public static abstract class MapInfoAscComparator implements Comparator<MapInfo> {
        protected int INVERT=1; // 1/-1 for invert TODO sort by asc/desc
        protected int FIRST;
        protected int LAST;
        public MapInfoAscComparator() {
            FIRST=1*INVERT;
            LAST=-1*INVERT;
        }
    }
    public static abstract class MapInfoDescComparator extends MapInfoAscComparator {
        protected int INVERT=-1; // 1/-1 for invert TODO sort by asc/desc
        protected int FIRST;
        protected int LAST;
        public MapInfoDescComparator() {
            FIRST=1*INVERT;
            LAST=-1*INVERT;
        }
    }
    
    public static class SortByName extends MapInfoAscComparator {
        @Override
        public int compare(MapInfo a, MapInfo b) {
            return a.name.compareTo(b.name)*INVERT;
        }
    }
    
    //TODO add sort by name length

    public static class SortByCount extends MapInfoDescComparator {
      
        @Override
        public int compare(MapInfo a, MapInfo b) {
            return a.count < b.count ? LAST :
                    a.count == b.count ? 0 : FIRST;
        }
    }
    
    public static class SortBySize  extends MapInfoDescComparator {
        @Override
        public int compare(MapInfo a, MapInfo b) {
             // Do add lobby cashe file size, or just map file size?
            return a.fileSize < b.fileSize ? LAST :
                    a.fileSize == b.fileSize ? 0 : FIRST;
        }
    }
    
    public static class SortByLastTime extends MapInfoDescComparator {
        @Override
        public int compare(MapInfo a, MapInfo b) {
            if (a.lastTime==null && b.lastTime==null) return 0;
            if (a.lastTime!=null && b.lastTime==null) return FIRST;
            if (a.lastTime==null && b.lastTime!=null) return LAST;
            return a.lastTime.compareTo(b.lastTime)*INVERT;
        }
    }
    
    public static class SortByDownloadTime extends MapInfoDescComparator {
        @Override
        public int compare(MapInfo a, MapInfo b) {
            if (a.mapFileTime==null && b.mapFileTime==null) return 0;
            if (a.mapFileTime!=null && b.mapFileTime==null) return FIRST;
            if (a.mapFileTime==null && b.mapFileTime!=null) return LAST;
            return a.mapFileTime.compareTo(b.mapFileTime)*INVERT;
        }
    }
    
    public static class SortByEffective extends MapInfoDescComparator {
        @Override
        public int compare(MapInfo a, MapInfo b) {
            float e1=effective(a);
            float e2=effective(b);
            return e1 < e2 ? LAST :
                    e1 == e2 ? 0 : FIRST;
        }
        public static float effective(MapInfo mi) {
            float e=mi.count*mi.count+1;
            if (mi.lastTime!=null) {
                e = e + 1.5f/(System.currentTimeMillis()-mi.lastTime.getTime())*1000*60*60*24;
            }
            e = e / (mi.fileSize + 1024.0f);
            return e;
        }
    }

}