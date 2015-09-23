/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oldmapcleaner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author PlayerO1
 */
public class OldMapCleaner {

    
    public static String fileSizeToStr(long size) {
        return size/1024+" Kbyte"; // todo more user like message
    }
    public static String showPercent(long part, long all) {
        return (part*1000/all)/10.0+"%";
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String springPath="/home/user2/.config/spring/";
        String demosPath=springPath+"demos";
        String mapsPath=springPath+"maps";
        String lobbyMapCashePath="/home/user2/.springlobby/cache/";

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

        applyStatistics(maps, demos); // initialize statistic to maps

        Collections.sort(maps, new SortByName()); //Sort test

        MapListGUI guiForm=new MapListGUI();
        guiForm.setMapList(maps, lobbyMapCashePath);
        guiForm.setVisible(true);
        
        System.out.println("End.");
    }


    public static void applyStatistics(ArrayList<MapInfo> maps, ArrayList<MapInfo> demos) {
        for (MapInfo map:maps) {
            int demoI=demos.indexOf(map);
            if (demoI!=-1) {
                MapInfo demo=demos.get(demoI);
                map.count=demo.count;
                map.firstTime=demo.firstTime;
                map.lastTime=demo.lastTime;
            } else {
                map.count=0;
            }
        }
    }

    public static class SortByName implements Comparator<MapInfo> {
        @Override
        public int compare(MapInfo a, MapInfo b) {
            return a.name.compareTo(b.name);
        }
    }

    public static class SortByCount implements Comparator<MapInfo> {
        @Override
        public int compare(MapInfo a, MapInfo b) { // TODO sort by asc/desc
            return a.count < b.count ? -1 :
                    a.count == b.count ? 0 : 1;
        }
    }

    public static class SortByEffective implements Comparator<MapInfo> {
        @Override
        public int compare(MapInfo a, MapInfo b) { // TODO sort by asc/desc
            float e1=effective(a);
            float e2=effective(b);
            return e1 < e2 ? -1 :
                    e1 == e2 ? 0 : 1;
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