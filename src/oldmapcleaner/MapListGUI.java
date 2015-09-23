/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oldmapcleaner;

import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import static oldmapcleaner.OldMapCleaner.fileSizeToStr;
import static oldmapcleaner.OldMapCleaner.showPercent;

/**
 *
 * @author PlayerO1
 */
public class MapListGUI extends javax.swing.JFrame {

    /**
     * Creates new form MapListGUI
     */
    public MapListGUI() {
        initComponents();
    }
    
    private List<MapInfo> maps;
    private DefaultListModel showLst;
    private String lobbyCashePath;
    public long statAllSize,statUnusedSize;
    public int statUnusingMaps, statUsedPlays;
    private static final int UNUSED_LIMIT=0;// level for check unused maps
    
    public void setMapList(List<MapInfo> _maps,String _lobbyCashePath) {
        this.maps=_maps;
        this.lobbyCashePath=_lobbyCashePath;
        showLst = new DefaultListModel();
        
        statAllSize=statUnusedSize=0;
        statUnusingMaps=statUsedPlays=0;
        for (MapInfo mi:maps) {
            showLst.addElement(mi.name);
            statAllSize+=mi.fileSize;
            if (mi.count<=UNUSED_LIMIT) {
                statUnusedSize+=mi.fileSize;
                statUnusingMaps++;
            } else {
                statUsedPlays+=mi.count;
            }
        }
        
        jListMaps.setModel(showLst);
        String txt="All "+maps.size()+" maps of "+fileSizeToStr(statAllSize)+" size." // TODO file size kb/mb/gb...
                + "No used "+statUnusingMaps+" maps ("+showPercent(statUnusingMaps,maps.size())+") of "+fileSizeToStr(statUnusedSize)+" size.";
        jlTotalInfo.setText(txt);
        //TODO refresh selection and show info.
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel_MapsList = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListMaps = new javax.swing.JList();
        jPanelSelectedInfo = new javax.swing.JPanel();
        jlSelectMapName = new javax.swing.JLabel();
        jlSelectMapInfo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jlPrewiew = new javax.swing.JLabel();
        jPanelBottomAllStats = new javax.swing.JPanel();
        jlTotalInfo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Old map cleaner - for Spring RTS");

        jLabel3.setText("Maps list");

        jListMaps.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jListMaps.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListMapsValueChanged(evt);
            }
        });
        jListMaps.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jListMapsKeyTyped(evt);
            }
        });
        jScrollPane2.setViewportView(jListMaps);

        javax.swing.GroupLayout jPanel_MapsListLayout = new javax.swing.GroupLayout(jPanel_MapsList);
        jPanel_MapsList.setLayout(jPanel_MapsListLayout);
        jPanel_MapsListLayout.setHorizontalGroup(
            jPanel_MapsListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_MapsListLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jScrollPane2)
        );
        jPanel_MapsListLayout.setVerticalGroup(
            jPanel_MapsListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_MapsListLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE))
        );

        jlSelectMapName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlSelectMapName.setText("Selected map");

        jlSelectMapInfo.setText("size, uses, dates, etc.");

        jlPrewiew.setText("Map prewiew from cashe");
        jScrollPane1.setViewportView(jlPrewiew);

        javax.swing.GroupLayout jPanelSelectedInfoLayout = new javax.swing.GroupLayout(jPanelSelectedInfo);
        jPanelSelectedInfo.setLayout(jPanelSelectedInfoLayout);
        jPanelSelectedInfoLayout.setHorizontalGroup(
            jPanelSelectedInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSelectedInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSelectedInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlSelectMapName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlSelectMapInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)
        );
        jPanelSelectedInfoLayout.setVerticalGroup(
            jPanelSelectedInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSelectedInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlSelectMapName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlSelectMapInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jlTotalInfo.setText("All maps, used and unused statistic.");

        javax.swing.GroupLayout jPanelBottomAllStatsLayout = new javax.swing.GroupLayout(jPanelBottomAllStats);
        jPanelBottomAllStats.setLayout(jPanelBottomAllStatsLayout);
        jPanelBottomAllStatsLayout.setHorizontalGroup(
            jPanelBottomAllStatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBottomAllStatsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlTotalInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelBottomAllStatsLayout.setVerticalGroup(
            jPanelBottomAllStatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBottomAllStatsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlTotalInfo, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanelBottomAllStats, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel_MapsList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanelSelectedInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel_MapsList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelSelectedInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelBottomAllStats, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jListMapsValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListMapsValueChanged
        MapInfo mi=maps.get(jListMaps.getSelectedIndex());//TODO multiselect
        jlSelectMapName.setText(mi.name);
        BufferedImage img=mi.loadPrewiev(lobbyCashePath);
        if (img==null) {
            jlPrewiew.setIcon(null);
            jlPrewiew.setText("No cashe prewiev for "+jListMaps.getSelectedIndex());
        } else {
            jlPrewiew.setIcon(new ImageIcon(img));
            jlPrewiew.setText(null);
        }
        String txt="Size <b>"+fileSizeToStr(mi.fileSize)+"</b>, used "+mi.count;
        if (mi.firstTime!=null) {
            txt+="<br/>";
            SimpleDateFormat dateFormat=new SimpleDateFormat("dd.MM.yyyy");//TODO date ??? DateFormat.SHORT;
            if (mi.firstTime!=mi.lastTime)
                txt+="First "+dateFormat.format(mi.firstTime)+"; ";
            txt+="Last <b>"+dateFormat.format(mi.lastTime)+"</b>";
        }
        jlSelectMapInfo.setText("<html>"+txt+"</html>");
    }//GEN-LAST:event_jListMapsValueChanged

    private void jListMapsKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jListMapsKeyTyped
        // TODO add your handling code here:
        if (evt.getKeyChar()=='-') {
            //TODO !!!!!!!!!!!!;
            showLst.setElementAt("-", jListMaps.getSelectedIndex());
        }
    }//GEN-LAST:event_jListMapsKeyTyped

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
            java.util.logging.Logger.getLogger(MapListGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MapListGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MapListGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MapListGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MapListGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel3;
    private javax.swing.JList jListMaps;
    private javax.swing.JPanel jPanelBottomAllStats;
    private javax.swing.JPanel jPanelSelectedInfo;
    private javax.swing.JPanel jPanel_MapsList;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel jlPrewiew;
    private javax.swing.JLabel jlSelectMapInfo;
    private javax.swing.JLabel jlSelectMapName;
    private javax.swing.JLabel jlTotalInfo;
    // End of variables declaration//GEN-END:variables
}
