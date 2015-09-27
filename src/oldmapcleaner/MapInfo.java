/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oldmapcleaner;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.Date;
import java.io.File;
import java.lang.ref.SoftReference;


/**
 *
 * @author PlayerO1
 */
public class MapInfo {
    public static final boolean IS_LINUX_FS=true;// for String comparing: equal/equal IgnoreCase

    public final String name; // name of map
    private final String upperName; // for compare ignore case
    public Date firstTime, lastTime; // first and last playing/spectating time on this map
    public int count; // cout of using this map
    public long fileSize = 0; // size of sd7 file, set only by loadMIMapFiles
	public boolean markToDelete;// touo use it.
    protected SoftReference<BufferedImage> prewiev = null; // cashe of prewiew images

    public MapInfo(String _name) {
        if (_name==null) throw new NullPointerException("name must be not null");
        this.name=_name;
        this.upperName=name.toUpperCase();
        count=1;
        firstTime=lastTime=null;
    }
    public MapInfo(String _name, Date date) {
        if (_name==null) throw new NullPointerException("name must be not null");
        this.name=_name;
        this.upperName=name.toUpperCase();
        count=1;
        firstTime=lastTime=date;
    }

    /**
     * Increase count and register date/time of using.
     * @param date
     */
    public void addUsing(Date date) {
        if (firstTime==null) firstTime=lastTime=date;
        else {
            if (firstTime.after(date)) firstTime=date;
            else
            if (lastTime.before(date)) lastTime=date;
        }
        count++;
    }

    /**
     * Do as addUsing, but work with MapInfo object.
     * @param mi2
     */
    public void merge(MapInfo mi2) {
        if (!equals(mi2)) throw new IllegalArgumentException("Name of map is not mapch.");
        if (mi2.firstTime!=null) {
            if (firstTime!=null) {
                if (firstTime.after(mi2.firstTime)) firstTime=mi2.firstTime;
                if (lastTime.before(mi2.lastTime)) lastTime=mi2.lastTime;
            } else {
                firstTime=mi2.firstTime;
                lastTime=mi2.lastTime;
            }
        }
        count+=mi2.count;
    }

    /**
     * Just increase count, do not modife date/time.
     */
    public void addUsing() {
        count++;
    }

    	/**
	* Load prewiev of map from lobby cashe
	* and add it to memory cashe into mi
	* @param pathToLobbyCashe if null then do not load, use only memory cashe.
	**/
    public BufferedImage loadPrewiev(String pathToLobbyCashe) {
        BufferedImage img=null;
        if (prewiev!=null) img=prewiev.get();
        if (img==null && pathToLobbyCashe!=null) {
            String fileName=name+".minimap.png";
            File imgF=new File(pathToLobbyCashe,fileName);
            if (IS_LINUX_FS) { // TODO it is not good bug fix for ignore map name case
                if (!imgF.exists()) {
                    imgF=new File(pathToLobbyCashe);
                    for (String n:imgF.list()) if (fileName.equalsIgnoreCase(n)) {
                        fileName=n;
                        imgF=new File(pathToLobbyCashe,fileName);
                        break;
                    };
                }
            }
            try {
                if (imgF.exists()) img=ImageIO.read(imgF);
                if (img!=null) prewiev=new SoftReference<BufferedImage>(img);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return img;
    }

    /**
     * Add all to first list and merge information.
     * @param addTo
     * @param lst2 from
     */
    public static void mergeList(ArrayList<MapInfo> addTo,ArrayList<MapInfo> lst2) {
        for (MapInfo mi:lst2) {
            int n=addTo.indexOf(mi);
            if (n!=-1) {
                addTo.get(n).merge(mi);
            } else addTo.add(mi);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this==o) return true;
//        if (IS_LINUX_FS) {
//            if (o instanceof MapInfo) return name.equals(((MapInfo)o).name);
//            if (o instanceof String) return name.equals((String)o);
//        } else {
            if (o instanceof MapInfo) return name.equalsIgnoreCase(((MapInfo)o).name);
            if (o instanceof String) return name.equalsIgnoreCase((String)o);
//        }
        return false;
    }

    @Override
    public int hashCode() {
        return upperName.hashCode(); //hash;
    }

    @Override
    public String toString() {
        return "MapInfo{name=" + name + ", firstTime=" + firstTime + ", lastTime=" + lastTime + ", count=" + count + '}';
    }

}