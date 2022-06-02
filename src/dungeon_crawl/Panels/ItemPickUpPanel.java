package dungeon_crawl.Panels;

import dungeon_crawl.Controllers.DungeonCrawl;
import dungeon_crawl.Controllers.ViewController;
import dungeon_crawl.Items.Item;

/*
 * @author Kieran
 */
public class ItemPickUpPanel extends javax.swing.JPanel {

    private DungeonCrawl dungeonCrawl;
    
    public ItemPickUpPanel(DungeonCrawl dungeonCrawl) {
        initComponents();
        this.dungeonCrawl = dungeonCrawl;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gameTitleLabel = new javax.swing.JLabel();
        sidePanel1 = new javax.swing.JPanel();
        qButton = new javax.swing.JButton();
        wButton = new javax.swing.JButton();
        eButton = new javax.swing.JButton();
        rButton = new javax.swing.JButton();
        dropButton = new javax.swing.JButton();
        quitButton = new javax.swing.JButton();
        itemFoundLabel = new javax.swing.JLabel();
        itemStatsLabel = new javax.swing.JLabel();
        itemFoundInfoLabel = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        gameTitleLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        gameTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gameTitleLabel.setText("DUNGEON CRAWL");
        add(gameTitleLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 450, 60));

        sidePanel1.setBackground(new java.awt.Color(200, 200, 200));
        sidePanel1.setPreferredSize(new java.awt.Dimension(150, 400));

        qButton.setText("<html>\n<b>[ITEM NAME]</b>\n<br>\n# DMG\n<br>\n+# HEALTH\n<br>\n# TURN STUN\n</html>\n\n");
        qButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                qButtonActionPerformed(evt);
            }
        });

        wButton.setText("<html>\n<b>[ITEM NAME]</b>\n<br>\n# DMG\n<br>\n+# HEALTH\n<br>\n# TURN STUN\n</html>\n\n");
        wButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wButtonActionPerformed(evt);
            }
        });

        eButton.setText("<html>\n<b>[ITEM NAME]</b>\n<br>\n# DMG\n<br>\n+# HEALTH\n<br>\n# TURN STUN\n</html>\n\n");
        eButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eButtonActionPerformed(evt);
            }
        });

        rButton.setText("<html>\n<b>[ITEM NAME]</b>\n<br>\n# DMG\n<br>\n+# HEALTH\n<br>\n# TURN STUN\n</html>\n\n");
        rButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rButtonActionPerformed(evt);
            }
        });

        dropButton.setText("DROP ITEM");
        dropButton.setPreferredSize(new java.awt.Dimension(120, 40));
        dropButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dropButtonActionPerformed(evt);
            }
        });

        quitButton.setText("QUIT");
        quitButton.setPreferredSize(new java.awt.Dimension(120, 40));
        quitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout sidePanel1Layout = new javax.swing.GroupLayout(sidePanel1);
        sidePanel1.setLayout(sidePanel1Layout);
        sidePanel1Layout.setHorizontalGroup(
            sidePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidePanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(sidePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(quitButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(qButton)
                    .addComponent(rButton, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(wButton, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(eButton)
                    .addComponent(dropButton, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
                .addContainerGap())
        );
        sidePanel1Layout.setVerticalGroup(
            sidePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidePanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(qButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(wButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(eButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dropButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(quitButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4))
        );

        add(sidePanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 0, -1, -1));

        itemFoundLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        itemFoundLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        itemFoundLabel.setText("YOU FOUND A [ITEM NAME]");
        add(itemFoundLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 450, 78));

        itemStatsLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        itemStatsLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        itemStatsLabel.setText("<html>\n<b>ITEM STATS:</b>\n<br>\n# DMG\n<br>\n+# HEALTH\n<br>\n# TURN STUN\n</html>");
        add(itemStatsLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 450, 78));

        itemFoundInfoLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        itemFoundInfoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        itemFoundInfoLabel.setText("<html> CLICK A ITEM TO REPLACE IT WITH OR <br> CLICK DROP TO DESTROY THE ITEM </html>");
        itemFoundInfoLabel.setToolTipText("");
        add(itemFoundInfoLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 450, 78));
    }// </editor-fold>//GEN-END:initComponents

    private void qButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_qButtonActionPerformed
        this.dungeonCrawl.collectItem(0);
    }//GEN-LAST:event_qButtonActionPerformed

    private void wButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wButtonActionPerformed
        this.dungeonCrawl.collectItem(1);
    }//GEN-LAST:event_wButtonActionPerformed

    private void eButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eButtonActionPerformed
        this.dungeonCrawl.collectItem(2);
    }//GEN-LAST:event_eButtonActionPerformed

    private void rButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rButtonActionPerformed
        this.dungeonCrawl.collectItem(3);
    }//GEN-LAST:event_rButtonActionPerformed

    private void quitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitButtonActionPerformed
        //No need to save
        System.exit(0);
    }//GEN-LAST:event_quitButtonActionPerformed

    private void dropButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dropButtonActionPerformed
        this.dungeonCrawl.collectItem(-1);
    }//GEN-LAST:event_dropButtonActionPerformed
    public void updateItemButtions(String[] items) {
        this.qButton.setText(items[0]);
        this.wButton.setText(items[1]);
        this.eButton.setText(items[2]);
        this.rButton.setText(items[3]);
    }
    
    public void updateItemInfo(Item item) {
        this.itemFoundLabel.setText("YOU FOUND A " + item.getName());
        this.itemStatsLabel.setText(item.getInfo(false));
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton dropButton;
    private javax.swing.JButton eButton;
    private javax.swing.JLabel gameTitleLabel;
    private javax.swing.JLabel itemFoundInfoLabel;
    private javax.swing.JLabel itemFoundLabel;
    private javax.swing.JLabel itemStatsLabel;
    private javax.swing.JButton qButton;
    private javax.swing.JButton quitButton;
    private javax.swing.JButton rButton;
    private javax.swing.JPanel sidePanel1;
    private javax.swing.JButton wButton;
    // End of variables declaration//GEN-END:variables
}
