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
public class NewGamePanel extends javax.swing.JPanel {

    /**
     * Creates new form NewGamePanel
     */
    public NewGamePanel() {
        initComponents();
        //Hide error label
        this.invalidNameLabel.setVisible(false);
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
        newGameTitleLabel = new javax.swing.JLabel();
        nameTitleLabel = new javax.swing.JLabel();
        nameInput = new javax.swing.JTextField();
        invalidNameLabel = new javax.swing.JLabel();
        startGameButton = new javax.swing.JButton();
        quitButton = new javax.swing.JButton();

        gameTitleLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        gameTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gameTitleLabel.setText("DUNGEON CRAWL");

        newGameTitleLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        newGameTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        newGameTitleLabel.setText("CREATING NEW GAME");

        nameTitleLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nameTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nameTitleLabel.setText("WHAT IS YOUR NAME?");

        nameInput.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nameInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameInputActionPerformed(evt);
            }
        });

        invalidNameLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        invalidNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        invalidNameLabel.setText("INVALID NAME!");

        startGameButton.setText("START GAME");
        startGameButton.setPreferredSize(new java.awt.Dimension(120, 40));
        startGameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startGameButtonActionPerformed(evt);
            }
        });

        quitButton.setText("QUIT");
        quitButton.setPreferredSize(new java.awt.Dimension(120, 40));
        quitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(gameTitleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(nameTitleLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(153, 153, 153)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(nameInput, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(155, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(startGameButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(quitButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(155, 155, 155))))
            .addComponent(invalidNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(newGameTitleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(gameTitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(123, 123, 123)
                .addComponent(nameTitleLabel)
                .addGap(18, 18, 18)
                .addComponent(nameInput, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(invalidNameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startGameButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(quitButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(97, 97, 97)
                    .addComponent(newGameTitleLabel)
                    .addContainerGap(281, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void nameInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameInputActionPerformed

    private void startGameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startGameButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_startGameButtonActionPerformed

    private void quitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_quitButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel gameTitleLabel;
    private javax.swing.JLabel invalidNameLabel;
    private javax.swing.JTextField nameInput;
    private javax.swing.JLabel nameTitleLabel;
    private javax.swing.JLabel newGameTitleLabel;
    private javax.swing.JButton quitButton;
    private javax.swing.JButton startGameButton;
    // End of variables declaration//GEN-END:variables

    public void invalidButtonVisibility(boolean value){
        this.gameTitleLabel.setVisible(value);
    }
}