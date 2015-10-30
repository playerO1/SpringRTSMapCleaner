/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oldmapcleaner;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;
import static oldmapcleaner.OldMapCleaner.applyStatistics;
import static oldmapcleaner.OldMapCleaner.autoChangeToDeleteStatus;

/**
 *
 * @author PlayerO1
 */
public class DataSourceGUI extends javax.swing.JFrame implements MapUpdateListener {

    public ArrayList<MapInfo> maps, demos;
    public String mapsPath, demosPath, lobbyMapCachePath;
    private Color defaultTextBackground;
    
// TODO default path of some specific OS and Spring engine
// TODO drag&drop folder
    
    /**
     * Creates new form DataSourceGUI
     */
    public DataSourceGUI() {
        initComponents();
        defaultTextBackground=jTxtCashe.getBackground();
        OldMapCleaner.addNotifyListener(this);
        String workPath=""; //System.getProperty("user.home")+File.separator;
        
        mapsPath=jTxtMaps.getText();
        File path=new File(mapsPath);
        if (!path.exists()) {
            workPath=System.getProperty("user.home")+File.separator;
            mapsPath=workPath+mapsPath;
            jTxtMaps.setText(mapsPath);
        }
        demosPath=workPath+jTxtDemo.getText();
        jTxtDemo.setText(demosPath);
        
        lobbyMapCachePath=jTxtCashe.getText();
        path=new File(lobbyMapCachePath);
        if (!path.exists() || !path.isDirectory()) {
            lobbyMapCachePath=workPath+lobbyMapCachePath;
            jTxtCashe.setText(lobbyMapCachePath);
            path=new File(lobbyMapCachePath);
            if (!path.exists() || !path.isDirectory()) lobbyMapCachePath=null;
        }
        
        this.maps=new ArrayList<MapInfo>();
        this.demos=new ArrayList<MapInfo>();
    }
    
    protected static String pathNotFoundDecorator(String path) {
        return "<html><font color='red'>Path \""+path+"\" not exist.</font></html>";
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelMaps = new javax.swing.JPanel();
        jBtnLoadMaps = new javax.swing.JButton();
        jTxtMaps = new javax.swing.JTextField();
        jLblMapStatus = new javax.swing.JLabel();
        jPanelLobbyCache = new javax.swing.JPanel();
        jTxtCashe = new javax.swing.JTextField();
        jPanelDemos = new javax.swing.JPanel();
        jTxtDemo = new javax.swing.JTextField();
        jButtonLoadDemo = new javax.swing.JButton();
        jLabelDemoStatus = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButtonOpenGUIList = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jCBSortBy = new javax.swing.JComboBox();
        jBtnDoClear = new javax.swing.JButton();
        jLabelAbout = new javax.swing.JLabel();
        jButtonShowToDelete = new javax.swing.JButton();
        jCBAutoMarkToDelete = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Map cleaner for Spring RTS");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanelMaps.setBorder(javax.swing.BorderFactory.createTitledBorder("Spring RTS map folder"));

        jBtnLoadMaps.setText("load");
        jBtnLoadMaps.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnLoadMapsActionPerformed(evt);
            }
        });

        jTxtMaps.setText(".config/spring/maps");

        jLblMapStatus.setText("Read map name and size from this path");

        javax.swing.GroupLayout jPanelMapsLayout = new javax.swing.GroupLayout(jPanelMaps);
        jPanelMaps.setLayout(jPanelMapsLayout);
        jPanelMapsLayout.setHorizontalGroup(
            jPanelMapsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMapsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMapsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTxtMaps)
                    .addGroup(jPanelMapsLayout.createSequentialGroup()
                        .addComponent(jLblMapStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtnLoadMaps)))
                .addContainerGap())
        );
        jPanelMapsLayout.setVerticalGroup(
            jPanelMapsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMapsLayout.createSequentialGroup()
                .addComponent(jTxtMaps, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMapsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLblMapStatus)
                    .addComponent(jBtnLoadMaps))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        jPanelLobbyCache.setBorder(javax.swing.BorderFactory.createTitledBorder("Spring Lobby cache folder for prewiew"));

        jTxtCashe.setText(".springlobby/cache/");

        javax.swing.GroupLayout jPanelLobbyCacheLayout = new javax.swing.GroupLayout(jPanelLobbyCache);
        jPanelLobbyCache.setLayout(jPanelLobbyCacheLayout);
        jPanelLobbyCacheLayout.setHorizontalGroup(
            jPanelLobbyCacheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLobbyCacheLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTxtCashe)
                .addContainerGap())
        );
        jPanelLobbyCacheLayout.setVerticalGroup(
            jPanelLobbyCacheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTxtCashe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanelDemos.setBorder(javax.swing.BorderFactory.createTitledBorder("Spring replays folder"));

        jTxtDemo.setText(".config/spring/demos");

        jButtonLoadDemo.setText("load");
        jButtonLoadDemo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLoadDemoActionPerformed(evt);
            }
        });

        jLabelDemoStatus.setText("Read statistic of usages from path/ZIP/7z. Can split 2 folder by \":\"");

        javax.swing.GroupLayout jPanelDemosLayout = new javax.swing.GroupLayout(jPanelDemos);
        jPanelDemos.setLayout(jPanelDemosLayout);
        jPanelDemosLayout.setHorizontalGroup(
            jPanelDemosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDemosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDemosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTxtDemo)
                    .addGroup(jPanelDemosLayout.createSequentialGroup()
                        .addComponent(jLabelDemoStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonLoadDemo)))
                .addContainerGap())
        );
        jPanelDemosLayout.setVerticalGroup(
            jPanelDemosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDemosLayout.createSequentialGroup()
                .addComponent(jTxtDemo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDemosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDemoStatus)
                    .addComponent(jButtonLoadDemo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel1.setToolTipText("");
        jPanel1.setName("AboutLink"); // NOI18N

        jButtonOpenGUIList.setText("Show map list");
        jButtonOpenGUIList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOpenGUIListActionPerformed(evt);
            }
        });

        jLabel1.setText("sorted by");

        jCBSortBy.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "no sort", "name", "playing", "size", "last using date", "download date", "effective" }));

        jBtnDoClear.setText("Remove selected maps");
        jBtnDoClear.setEnabled(false);
        jBtnDoClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnDoClearActionPerformed(evt);
            }
        });

        jLabelAbout.setText("<html><u>(C) PlayerO1 2015, GNU GPL V2</u></html>");
        jLabelAbout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelAboutMouseClicked(evt);
            }
        });

        jButtonShowToDelete.setText("show to delete list");
        jButtonShowToDelete.setToolTipText("Show only select to delete maps");
        jButtonShowToDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonShowToDeleteActionPerformed(evt);
            }
        });

        jCBAutoMarkToDelete.setSelected(true);
        jCBAutoMarkToDelete.setText("auto mark old");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButtonOpenGUIList)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCBSortBy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCBAutoMarkToDelete))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jBtnDoClear)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonShowToDelete)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelAbout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonOpenGUIList)
                        .addComponent(jLabel1)
                        .addComponent(jCBSortBy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jCBAutoMarkToDelete))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnDoClear)
                    .addComponent(jButtonShowToDelete))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelAbout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelMaps, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelLobbyCache, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelDemos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelMaps, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelLobbyCache, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelDemos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnLoadMapsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnLoadMapsActionPerformed
        mapsPath=jTxtMaps.getText();
        if (!mapsPath.endsWith(File.separator)) mapsPath+=File.separator;
        File path=new File(mapsPath);
        if (!path.exists()) {
            jLblMapStatus.setText(pathNotFoundDecorator(mapsPath));
        } else {
            maps=parseMapNames.loadMIMapFiles(mapsPath); // TODO load from 2 or more different path and add into one list.
            jLblMapStatus.setText("Read "+maps.size()+" name of map files.");
        }
    }//GEN-LAST:event_jBtnLoadMapsActionPerformed

    private void jButtonLoadDemoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLoadDemoActionPerformed
        demosPath=jTxtDemo.getText();
        String[] subPath=demosPath.split(File.pathSeparator); //TODO checl split and regular template
        demos=null;
        boolean noErrors = subPath.length>0;
        for (String demoPath:subPath) {
            demoPath=demoPath.trim();// for skip space
            if (!demoPath.endsWith(File.separator)) demoPath+=File.separator;
            File path=new File(demoPath);
            if (!path.exists()) {
                noErrors=false;
                jLabelDemoStatus.setText(pathNotFoundDecorator(demoPath));
            } else {
                ArrayList<MapInfo> demo2=parseMapNames.parseMIFromDemosFolder(demoPath);
                if (demos==null) demos=demo2;
                else MapInfo.mergeList(demos, demo2);
            }
        }
        if (noErrors) jLabelDemoStatus.setText("Read demo files for "+demos.size()+" maps.");
    }//GEN-LAST:event_jButtonLoadDemoActionPerformed

    private void jButtonOpenGUIListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOpenGUIListActionPerformed
        if (maps.isEmpty()) jBtnLoadMapsActionPerformed(null); // if user forgot press it
        if (demos.isEmpty()) jButtonLoadDemoActionPerformed(null);
        
        if (!lobbyMapCachePath.endsWith(File.separator)) lobbyMapCachePath+=File.separator;
        File path=new File(lobbyMapCachePath);
        if (!path.exists() || !path.isDirectory()) {
            jTxtCashe.setBackground(Color.red);
            lobbyMapCachePath=null;
        } else {
            jTxtCashe.setBackground(defaultTextBackground);
        }
        
        applyStatistics(maps, demos, true); // initialize statistic to maps
        
        if (jCBAutoMarkToDelete.isSelected()) {
            autoChangeToDeleteStatus(maps);
            this.onMapUpdate(null); //TODO just refresh button, do not update.
        }
        
        switch (jCBSortBy.getSelectedIndex()) {
            case 0: // do not sort
                break;
            case 1: 
                Collections.sort(maps, new OldMapCleaner.SortByName()); 
                break;
            case 2:
                Collections.sort(maps, new OldMapCleaner.SortByCount()); 
                break;
            case 3: // size
                Collections.sort(maps, new OldMapCleaner.SortBySize()); 
                break;
            case 4: // last using date.
                Collections.sort(maps, new OldMapCleaner.SortByLastTime()); 
                break;
            case 5: // download time
                Collections.sort(maps, new OldMapCleaner.SortByDownloadTime()); 
                break;
            case 6: // .. effective
                Collections.sort(maps, new OldMapCleaner.SortByEffective()); 
                break;
            default:
                throw new UnsupportedOperationException("No sort type found for jCBSortBy.getSelectedIndex()="+jCBSortBy.getSelectedIndex());
        }

        MapListGUI guiForm=new MapListGUI();
        guiForm.setMapList(maps, lobbyMapCachePath);
        guiForm.setVisible(true);
        //TODO launch GUI, apply statistics!!
    }//GEN-LAST:event_jButtonOpenGUIListActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        OldMapCleaner.removeNotifyListener(this);
    }//GEN-LAST:event_formWindowClosed

    private void jBtnDoClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnDoClearActionPerformed
        // TODO add your handling code here:
        ArrayList<MapInfo> toDeleteSelect=OldMapCleaner.selectByDeleting(maps, true);
//        MapListGUI guiForm=new MapListGUI();
//        guiForm.setMapList(toDeleteSelect, lobbyMapCashePath);
//        guiForm.setVisible(true);
        
        ArrayList<File> toDeleteFiles=new ArrayList<File>();
        for (MapInfo mi:toDeleteSelect) {
            toDeleteFiles.addAll(parseMapNames.getExistMapFiles(mi.name, mapsPath, lobbyMapCachePath));
        };
        long filesSize=0;
        for (File f:toDeleteFiles) filesSize+=f.length();

        int doDelete = JOptionPane.showConfirmDialog(this, "Will be remove "+OldMapCleaner.fileSizeToStr(filesSize)+" size of "+toDeleteFiles.size()+" files.\nAre you sure?",
                "Files to deleting", JOptionPane.YES_NO_OPTION);
        if (doDelete == JOptionPane.YES_OPTION){
            int deletingSuccess=0; long deletingSuccessSize=0;
            for (File f:toDeleteFiles) {
                long fSize=f.length();
                boolean wasDeleting=false;
                try {
                    if (f.delete()) wasDeleting=true;
                } catch (Exception e) {
                    System.out.println("File "+f.getName()+" exceptin:"+e);
                }
                if (wasDeleting) {
                    deletingSuccess++;
                    deletingSuccessSize+=fSize;
                    System.out.println("Deleted> "+f.getPath());
                } else {
                    System.out.println("Can not remove> "+f.getPath());
                }
            }
            JOptionPane.showMessageDialog(this, "Removed "+OldMapCleaner.fileSizeToStr(deletingSuccessSize)+" size of "+deletingSuccess+" files.",
                "Deleting finish",JOptionPane.INFORMATION_MESSAGE);
            //TODO notify Update & refresh map list.
        }

    }//GEN-LAST:event_jBtnDoClearActionPerformed

    private void jButtonShowToDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonShowToDeleteActionPerformed
        ArrayList<MapInfo> toDeleteSelect=OldMapCleaner.selectByDeleting(maps, true);
        MapListGUI guiForm=new MapListGUI();
        guiForm.setMapList(toDeleteSelect, lobbyMapCachePath);
        guiForm.setVisible(true);
    }//GEN-LAST:event_jButtonShowToDeleteActionPerformed

    private void jLabelAboutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAboutMouseClicked
        String aboutMsg="Utility for help clear old and unusible maps from Spring RTS, and show statistics of using maps and hard disc space of store it."
                +"\nUse demo files (replays) for collect statistic of playing on mpas and show using/unusing maps with preview images."
                +"\nThe preview take from Spring lobby cashe. If you not set this parameter this program will be not show preview image. The casne can not have some of previev."
                +"\nAt the start check selected directory, then press button \"load\" for read map file names."
                +"\nAt the next step press button \"Show map list\" and select maps to delete (using delete key for mark and insert to unmark). Allow multiselection."
                +"\nYou can open more that 1 lists of maps sorted by different parameters."
                +"\nAt the final you should check list for remove by button \"show list\" then press \"Remove...\" to execure removing data of selected maps."
                +"\n\nThis software distribute under GNU GPL v2 license. (C) 2015 PlayerO1 (http://github.com/playerO1).";
        JOptionPane.showMessageDialog(this, aboutMsg);
    }//GEN-LAST:event_jLabelAboutMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DataSourceGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DataSourceGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DataSourceGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DataSourceGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DataSourceGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnDoClear;
    private javax.swing.JButton jBtnLoadMaps;
    private javax.swing.JButton jButtonLoadDemo;
    private javax.swing.JButton jButtonOpenGUIList;
    private javax.swing.JButton jButtonShowToDelete;
    private javax.swing.JCheckBox jCBAutoMarkToDelete;
    private javax.swing.JComboBox jCBSortBy;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelAbout;
    private javax.swing.JLabel jLabelDemoStatus;
    private javax.swing.JLabel jLblMapStatus;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelDemos;
    private javax.swing.JPanel jPanelLobbyCache;
    private javax.swing.JPanel jPanelMaps;
    private javax.swing.JTextField jTxtCashe;
    private javax.swing.JTextField jTxtDemo;
    private javax.swing.JTextField jTxtMaps;
    // End of variables declaration//GEN-END:variables

    @Override
    public void onMapUpdate(List<MapInfo> modifedObjects) {
        int toRemoveCount=0; long toRemoveSize=0;
        for (MapInfo mi:maps) if (mi.markToDelete) {
            toRemoveCount++;
            toRemoveSize+=mi.fileSize;
        }
        if (toRemoveSize>0) {
            jBtnDoClear.setEnabled(true);
            jBtnDoClear.setText("Remove "+toRemoveCount+" maps ("+OldMapCleaner.fileSizeToStr(toRemoveSize)+")");
        } else {
            jBtnDoClear.setEnabled(false);
            jBtnDoClear.setText("Remove selected maps");
        }
    }
}
