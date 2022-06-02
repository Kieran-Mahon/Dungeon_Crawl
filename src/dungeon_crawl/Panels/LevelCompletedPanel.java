package dungeon_crawl.Panels;

import dungeon_crawl.Controllers.DungeonCrawl;

/*
 * @author Kieran
 */
public class LevelCompletedPanel extends javax.swing.JPanel {

    private DungeonCrawl dungeonCrawl;
    
    public LevelCompletedPanel(DungeonCrawl dungeonCrawl) {
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
        gameLevelCompletedLabel = new javax.swing.JLabel();
        continueButton = new javax.swing.JButton();
        quitButton = new javax.swing.JButton();
        timeTakenLabel = new javax.swing.JLabel();
        characterResetLabel = new javax.swing.JLabel();

        gameTitleLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        gameTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gameTitleLabel.setText("DUNGEON CRAWL");

        gameLevelCompletedLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        gameLevelCompletedLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gameLevelCompletedLabel.setText("LEVEL # COMPLETED!");

        continueButton.setText("CONTINUE");
        continueButton.setPreferredSize(new java.awt.Dimension(120, 40));
        continueButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                continueButtonActionPerformed(evt);
            }
        });

        quitButton.setText("QUIT");
        quitButton.setPreferredSize(new java.awt.Dimension(120, 40));
        quitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitButtonActionPerformed(evt);
            }
        });

        timeTakenLabel.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        timeTakenLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        timeTakenLabel.setText("TIME TAKEN: ## MINUTES AND ## SECONDS");

        characterResetLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        characterResetLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        characterResetLabel.setText("YOUR CHARACTER HAS BEEN RESET!");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addComponent(continueButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(quitButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(98, 98, 98))
            .addComponent(gameTitleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(gameLevelCompletedLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(characterResetLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(timeTakenLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(gameTitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(gameLevelCompletedLabel)
                .addGap(36, 36, 36)
                .addComponent(timeTakenLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(characterResetLabel)
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(continueButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(quitButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(65, 65, 65))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void continueButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_continueButtonActionPerformed
        this.dungeonCrawl.levelContinue();
    }//GEN-LAST:event_continueButtonActionPerformed

    private void quitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitButtonActionPerformed
        //Game is saved when this screen is shown so no need to save again
        System.exit(0);
    }//GEN-LAST:event_quitButtonActionPerformed
    
    public void updateText(String level, String time) {
        this.gameLevelCompletedLabel.setText("LEVEL " + level + " COMPLETED!");
        this.timeTakenLabel.setText("TIME TAKEN: " + time);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel characterResetLabel;
    private javax.swing.JButton continueButton;
    private javax.swing.JLabel gameLevelCompletedLabel;
    private javax.swing.JLabel gameTitleLabel;
    private javax.swing.JButton quitButton;
    private javax.swing.JLabel timeTakenLabel;
    // End of variables declaration//GEN-END:variables
}
