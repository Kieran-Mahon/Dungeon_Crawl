/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeon_crawl.Panels;

/**
 *
 * @author Kieran
 */
public class LevelCompletedPanel extends javax.swing.JPanel {

    /**
     * Creates new form LevelCompletedPanel
     */
    public LevelCompletedPanel() {
        initComponents();
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
        nextLevelButton = new javax.swing.JButton();
        quitButton = new javax.swing.JButton();
        timeTakenLabel = new javax.swing.JLabel();
        characterResetLabel = new javax.swing.JLabel();

        gameTitleLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        gameTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gameTitleLabel.setText("DUNGEON CRAWL");

        gameLevelCompletedLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        gameLevelCompletedLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gameLevelCompletedLabel.setText("LEVEL # COMPLETED!");

        nextLevelButton.setText("NEXT LEVEL");
        nextLevelButton.setPreferredSize(new java.awt.Dimension(120, 40));
        nextLevelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextLevelButtonActionPerformed(evt);
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
                .addComponent(nextLevelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(nextLevelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(quitButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(65, 65, 65))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void nextLevelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextLevelButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nextLevelButtonActionPerformed

    private void quitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_quitButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel characterResetLabel;
    private javax.swing.JLabel gameLevelCompletedLabel;
    private javax.swing.JLabel gameTitleLabel;
    private javax.swing.JButton nextLevelButton;
    private javax.swing.JButton quitButton;
    private javax.swing.JLabel timeTakenLabel;
    // End of variables declaration//GEN-END:variables
}
