/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oldmapcleaner;

import java.util.List;

/**
 *
 * @author PlayerO1
 */
public interface MapUpdateListener {
    /**
     * On updated same data.
     * @param modifedObjects changed objects, can me null if update too much. 
     */
    public void onMapUpdate(List<MapInfo> modifedObjects);
}
