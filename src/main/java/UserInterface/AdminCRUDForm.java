/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UserInterface;

import Class.Item;
import Class.utils.Auth;
import RMIConnections.Client;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ryann
 */
public class AdminCRUDForm extends javax.swing.JFrame {

    /**
     * Creates new form AddItemForm
     */
    public AdminCRUDForm() {
        initComponents();

        // reset fields, udpate table contents, update combo box
        refresh();

        // document listener to track events from input fields
        initialisedDocumentListener();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        itemInformationPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        stockAmountInput = new javax.swing.JTextField();
        itemNameInput = new javax.swing.JTextField();
        unitPriceInput = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        addButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        searchItemPanel = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        itemIDComboBox = new javax.swing.JComboBox<>();
        searchButton = new javax.swing.JButton();
        viewItemPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        viewItemTable = new javax.swing.JTable();
        refreshButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        itemInformationPanel.setBackground(new java.awt.Color(255, 255, 255));
        itemInformationPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Item Information", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        itemInformationPanel.setForeground(new java.awt.Color(0, 0, 0));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Stock Amount:");

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Name:");

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Unit Price:");

        stockAmountInput.setBackground(new java.awt.Color(255, 255, 255));
        stockAmountInput.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        stockAmountInput.setForeground(new java.awt.Color(51, 51, 51));
        stockAmountInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stockAmountInputActionPerformed(evt);
            }
        });

        itemNameInput.setBackground(new java.awt.Color(255, 255, 255));
        itemNameInput.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        itemNameInput.setForeground(new java.awt.Color(51, 51, 51));
        itemNameInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemNameInputActionPerformed(evt);
            }
        });

        unitPriceInput.setBackground(new java.awt.Color(255, 255, 255));
        unitPriceInput.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        unitPriceInput.setForeground(new java.awt.Color(51, 51, 51));
        unitPriceInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unitPriceInputActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout itemInformationPanelLayout = new javax.swing.GroupLayout(itemInformationPanel);
        itemInformationPanel.setLayout(itemInformationPanelLayout);
        itemInformationPanelLayout.setHorizontalGroup(
            itemInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(itemInformationPanelLayout.createSequentialGroup()
                .addGroup(itemInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(itemInformationPanelLayout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jLabel4))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, itemInformationPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(itemInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(itemInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(unitPriceInput, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(itemNameInput, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stockAmountInput, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        itemInformationPanelLayout.setVerticalGroup(
            itemInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(itemInformationPanelLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(itemInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(itemNameInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(itemInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(unitPriceInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(itemInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stockAmountInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Item Information");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setForeground(new java.awt.Color(0, 0, 0));

        addButton.setBackground(new java.awt.Color(255, 255, 255));
        addButton.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        addButton.setForeground(new java.awt.Color(51, 51, 51));
        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        editButton.setBackground(new java.awt.Color(255, 255, 255));
        editButton.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        editButton.setForeground(new java.awt.Color(51, 51, 51));
        editButton.setText("Edit");
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        deleteButton.setBackground(new java.awt.Color(255, 255, 255));
        deleteButton.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        deleteButton.setForeground(new java.awt.Color(51, 51, 51));
        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addButton)
                    .addComponent(editButton)
                    .addComponent(deleteButton))
                .addGap(18, 18, 18))
        );

        cancelButton.setBackground(new java.awt.Color(255, 255, 255));
        cancelButton.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        cancelButton.setForeground(new java.awt.Color(51, 51, 51));
        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        searchItemPanel.setBackground(new java.awt.Color(255, 255, 255));
        searchItemPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Search Item ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        searchItemPanel.setForeground(new java.awt.Color(0, 0, 0));

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("Product ID:");

        itemIDComboBox.setBackground(new java.awt.Color(255, 255, 255));
        itemIDComboBox.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        itemIDComboBox.setForeground(new java.awt.Color(0, 0, 0));
        itemIDComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        itemIDComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemIDComboBoxActionPerformed(evt);
            }
        });

        searchButton.setBackground(new java.awt.Color(255, 255, 255));
        searchButton.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        searchButton.setForeground(new java.awt.Color(51, 51, 51));
        searchButton.setText("Search");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout searchItemPanelLayout = new javax.swing.GroupLayout(searchItemPanel);
        searchItemPanel.setLayout(searchItemPanelLayout);
        searchItemPanelLayout.setHorizontalGroup(
            searchItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchItemPanelLayout.createSequentialGroup()
                .addGroup(searchItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(searchItemPanelLayout.createSequentialGroup()
                        .addGroup(searchItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(searchItemPanelLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel5))
                            .addGroup(searchItemPanelLayout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, searchItemPanelLayout.createSequentialGroup()
                        .addGap(0, 20, Short.MAX_VALUE)
                        .addComponent(itemIDComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        searchItemPanelLayout.setVerticalGroup(
            searchItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, searchItemPanelLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(itemIDComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(searchButton)
                .addGap(39, 39, 39))
        );

        viewItemPanel.setBackground(new java.awt.Color(255, 255, 255));
        viewItemPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "View Items", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        viewItemPanel.setForeground(new java.awt.Color(0, 0, 0));

        viewItemTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Item ID", "Name", "Price", "Quantity"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        viewItemTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                viewItemTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(viewItemTable);

        javax.swing.GroupLayout viewItemPanelLayout = new javax.swing.GroupLayout(viewItemPanel);
        viewItemPanel.setLayout(viewItemPanelLayout);
        viewItemPanelLayout.setHorizontalGroup(
            viewItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewItemPanelLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 634, Short.MAX_VALUE)
                .addContainerGap())
        );
        viewItemPanelLayout.setVerticalGroup(
            viewItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewItemPanelLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        refreshButton.setBackground(new java.awt.Color(255, 255, 255));
        refreshButton.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        refreshButton.setForeground(new java.awt.Color(51, 51, 51));
        refreshButton.setText("Refresh");
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(refreshButton)
                        .addGap(18, 18, 18)
                        .addComponent(cancelButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(itemInformationPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(searchItemPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(viewItemPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cancelButton)
                    .addComponent(refreshButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(itemInformationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(searchItemPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(viewItemPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void stockAmountInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stockAmountInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stockAmountInputActionPerformed

    private void itemNameInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemNameInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemNameInputActionPerformed

    private void unitPriceInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unitPriceInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_unitPriceInputActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed

        String itemName = this.itemNameInput.getText();
        String unitPrice = this.unitPriceInput.getText();
        String stockAmount = this.stockAmountInput.getText();

        try {
            // check whether all input fields are filled
            if (!Auth.inputFieldsFilled(itemName, unitPrice, stockAmount)) {
                throw new Exception("All input fields are required!");
            }
            if (!Auth.isValidItemName(itemName)) {
                throw new Exception("Name must contain at least 5-25 characters.");
            }
            if (!Auth.isValidUnitPrice(unitPrice)) {
                throw new Exception("Price must contain value two decimals.");
            }
            if (!Auth.isValidStockAmount(stockAmount)) {
                throw new Exception("Stock Amount must be a value between 1-9999.");
            }

            Item newItem = new Item(itemName, Double.parseDouble(unitPrice), Integer.parseInt(stockAmount));
            Client.Object.addItem(newItem);
            JOptionPane.showMessageDialog(null, String.format("Item: %s\nUnit Price: %.2f\nStock Amount: %s \n\nItem successfully been added!", itemName, Double.parseDouble(unitPrice), Integer.parseInt(stockAmount)));

            refresh();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_addButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        new AdminMainMenuForm().setVisible(true);
        dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        try {
            // TODO add your handling code here:
            String ItemID = itemIDComboBox.getSelectedItem().toString();
            Item selectedItem = Client.Object.retrieveItemByID(ItemID);
            this.itemNameInput.setText(selectedItem.getItemName());
            this.unitPriceInput.setText(String.valueOf(selectedItem.getUnitPrice()));
            this.stockAmountInput.setText(String.valueOf(selectedItem.getStockAmount()));

            // highlight the corresponding row in the table
            DefaultTableModel tableModel = (DefaultTableModel) viewItemTable.getModel();
            int rowIndex = -1;
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                String tableItemId = tableModel.getValueAt(i, 0).toString();
                if (tableItemId.equals(itemIDComboBox.getSelectedItem())) {
                    rowIndex = i;
                    break;
                }
            }
            if (rowIndex != -1) {
                viewItemTable.setRowSelectionInterval(rowIndex, rowIndex);
            }
        } catch (Exception ex) {
            Logger.getLogger(AdminCRUDForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_searchButtonActionPerformed

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        // TODO add your handling code here:
        // get current selected item from table model
        DefaultTableModel model = (DefaultTableModel) viewItemTable.getModel();
        int selectedRowIndex = viewItemTable.getSelectedRow();
        int itemID = (int) model.getValueAt(selectedRowIndex, 0);

        String originalItemName = (String) model.getValueAt(selectedRowIndex, 1);
        double originalUnitPrice = (double) model.getValueAt(selectedRowIndex, 2);
        int originalStockAmount = (int) model.getValueAt(selectedRowIndex, 3);

        String newItemName = this.itemNameInput.getText();
        String newUnitPrice = this.unitPriceInput.getText();
        String newStockAmount = this.stockAmountInput.getText();
        //double newUnitPrice = Double.parseDouble(unitPriceInput.getText().trim());
        //int newStockAmount = Integer.parseInt(this.stockAmountInput.getText().trim());

        try {
            // check whether all input fields are filled
            if (!Auth.textFieldsFilled(itemNameInput, unitPriceInput, stockAmountInput)) {
                throw new Exception("All input fields are required!");
            }
            if (!Auth.isValidItemName(newItemName)) {
                throw new Exception("Name must contain at least 5-25 characters.");
            }
            if (!Auth.isValidUnitPrice(newUnitPrice)) {
                throw new Exception("Price must contain value two decimals.");
            }
            if (!Auth.isValidStockAmount(newStockAmount)) {
                throw new Exception("Enter a value between 1-9999.");
            }
            if (!Auth.inputsChanged(newItemName, Double.parseDouble(newUnitPrice), Integer.parseInt(newStockAmount), originalItemName, originalUnitPrice, originalStockAmount)) {
                throw new Exception("No changes have been made.");
            }
            // creating the updated the item
            Item newItem = new Item(newItemName, Double.parseDouble(newUnitPrice), Integer.parseInt(newStockAmount));
            
            // confirmation dialog for edit item
            int confirm = JOptionPane.showConfirmDialog(null, String.format("Are you sure you want to edit item '%s'?", newItemName), "Confirm Edit", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                Client.Object.updateItem(itemID, newItem);
                JOptionPane.showMessageDialog(null, String.format("Item: %s\nUnit Price: %.2f\nStock Amount: %s \n\nChanges successfully been added!", newItem.getItemName(), newItem.getUnitPrice(), newItem.getStockAmount()));

                // reset fields, udpate table contents, update combo box
                refresh();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_editButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        // TODO add your handling code here:
        String itemName = itemNameInput.getText().trim();
        double unitPrice = Double.parseDouble(unitPriceInput.getText().trim());
        int stockAmount = Integer.parseInt(stockAmountInput.getText().trim());
        Item itemToDelete = new Item(itemName, unitPrice, stockAmount);

        try {
            int confirm = JOptionPane.showConfirmDialog(null, String.format("Are you sure you want to delete item '%s'?", itemName), "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                // Delete the item                
                Client.Object.deleteItem(itemToDelete);
                JOptionPane.showMessageDialog(null, String.format("Item '%s' successfully deleted!", itemName));

                // reset fields, udpate table contents, update combo box
                refresh();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        // TODO add your handling code here:
        // reset fields, udpate table contents, update combo box
        refresh();
    }//GEN-LAST:event_refreshButtonActionPerformed

    private void viewItemTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewItemTableMouseClicked
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) viewItemTable.getModel();
        int selectedRowIndex = viewItemTable.getSelectedRow();
        itemNameInput.setText(model.getValueAt(selectedRowIndex, 1).toString());
        unitPriceInput.setText(model.getValueAt(selectedRowIndex, 2).toString());
        stockAmountInput.setText(model.getValueAt(selectedRowIndex, 3).toString());
    }//GEN-LAST:event_viewItemTableMouseClicked

    private void itemIDComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemIDComboBoxActionPerformed

    }//GEN-LAST:event_itemIDComboBoxActionPerformed

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
            java.util.logging.Logger.getLogger(AdminCRUDForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminCRUDForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminCRUDForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminCRUDForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminCRUDForm().setVisible(true);
            }
        });
    }

    private void loadTable() {
        try {
            DefaultTableModel model = Client.Object.viewTable();
            viewItemTable.setModel(model);
        } catch (Exception ex) {
            Logger.getLogger(AdminCRUDForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadComboBox() {
        try {
            // TODO add your handling code here:
            itemIDComboBox.removeAllItems();
            ArrayList<String> itemIDs = Client.Object.retrieveAllItemID();
            for (String itemID : itemIDs) {
                itemIDComboBox.addItem(itemID);
            }
        } catch (Exception ex) {
            Logger.getLogger(AdminCRUDForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void refresh() {
        loadTable();
        loadComboBox();

        addButton.setEnabled(false);
        editButton.setEnabled(false);
        deleteButton.setEnabled(false);

        //reset input fields to empty
        itemNameInput.setText("");
        unitPriceInput.setText("");
        stockAmountInput.setText("");

        itemNameInput.requestFocus();
    }

    private void initialisedDocumentListener() {
        DocumentListener inputListener = new DocumentListener() {
            private void updateButtonState() {
                boolean inputsFilled = Auth.textFieldsFilled(itemNameInput, unitPriceInput, stockAmountInput);
                boolean isValidName = Auth.isValidItemName(itemNameInput.getText());
                boolean isValidPrice = Auth.isValidUnitPrice(unitPriceInput.getText());
                boolean isValidStock = Auth.isValidStockAmount(stockAmountInput.getText());

                addButton.setEnabled(inputsFilled);
                editButton.setEnabled(inputsFilled && isValidName && isValidPrice && isValidStock);
                deleteButton.setEnabled(inputsFilled && isValidName && isValidPrice && isValidStock);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                updateButtonState();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateButtonState();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateButtonState();
            }

        };

        itemNameInput.getDocument().addDocumentListener(inputListener);
        unitPriceInput.getDocument().addDocumentListener(inputListener);
        stockAmountInput.getDocument().addDocumentListener(inputListener);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton editButton;
    private javax.swing.JComboBox<String> itemIDComboBox;
    private javax.swing.JPanel itemInformationPanel;
    private javax.swing.JTextField itemNameInput;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton refreshButton;
    private javax.swing.JButton searchButton;
    private javax.swing.JPanel searchItemPanel;
    private javax.swing.JTextField stockAmountInput;
    private javax.swing.JTextField unitPriceInput;
    private javax.swing.JPanel viewItemPanel;
    private javax.swing.JTable viewItemTable;
    // End of variables declaration//GEN-END:variables
}
