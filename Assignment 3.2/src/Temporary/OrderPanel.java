package Temporary;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class OrderPanel extends JPanel {
    private Font font = new Font(Font.SERIF, Font.PLAIN,20);
    private Font font30 = new Font(Font.SERIF, Font.PLAIN,30);

    // Scroll pane for order details panel
    private JScrollPane orderScrollPane = new JScrollPane();

    // List of orderDetailsPanels
    private List<JPanel> orderDetailsPanels = new ArrayList<>();

    // Panel containing individual order details for orders
    final private JPanel orderDetailsPanel = new JPanel();

    // Panel containing orderDetailsPanels
    final private JPanel orderItemsPanel = new JPanel();

    // Holds components for displaying Total Price and the Buy Button
    final private JPanel orderSummaryPanel = new JPanel();

    final private JPanel orderProcessingPanel = new JPanel();

    // Label for Total price of all items in cart
    private JLabel orderTotalAmountLabel = new JLabel();

    // Array of options for use in the quantity combobox
    private Integer[] quantityArray = new Integer[]{0,1,2,3,4,5,6,7,8,9};

    // Total price of all items in cart
    private int cartTotal;


    public OrderPanel(MainWindow mainWindow){
        // Sets the font for all instances of JLabel and JComboBox to font
        UIManager.put("Label.font",font);
        UIManager.put("ComboBox.font",font);

        // Sets the dimensions and layout for cartPanel
        Dimension dim = new Dimension(1000,1000);
        this.setSize(dim);
        this.setLayout(new MigLayout("","[grow]", "[][center]"));

        // Build Cart Items panel
//        buildCartPanel(mainWindow);
        buildOrderProcessingPanel(mainWindow);

        // Adds each set of computer details to cartItemsPanel
        for(JPanel cp: orderDetailsPanels){
            cp.setLayout(new MigLayout(", wrap", "[400][300][300]"));
            orderItemsPanel.add(cp, "wrap");
        }

        // Adding a scroll pane to the panel of cart items
        JScrollPane scrollPane = new JScrollPane(orderProcessingPanel);
        scrollPane.getHorizontalScrollBar().setEnabled(false);
        scrollPane.setMaximumSize(new Dimension(1000, 600));
        this.add(scrollPane, "wrap");

        // Builds the summary panel and displays
        buildSummaryPanel(mainWindow);
        this.add(orderProcessingPanel);
    }

    private void buildSummaryPanel(MainWindow mainWindow){
        orderSummaryPanel.setLayout(new MigLayout("","[50%, right][50%, left]", "20px[]20px[]"));

        Font newFont = new Font(Font.SERIF, Font.BOLD, 30);

        orderSummaryPanel.setSize(1000,200);

        JLabel cartTotalLabel = new JLabel("Total: ");

        orderTotalAmountLabel.setText("€" + cartTotal);

        JButton buyButton = new JButton("Buy");

        buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JLabel label = new JLabel("<HTML>Thank You for shopping your total came to: €" + cartTotal + "<BR>You will now be returned to the home screen </HTML>");
                //label.setFont(new Font("Serif", Font.BOLD, 18));

                mainWindow.clearOrder();

                mainWindow.getContentPane().removeAll();
                mainWindow.getContentPane().add(mainWindow.mainPanel);

                mainWindow.mainPanel.setVisible(true);
                mainWindow.repaint();

            }
        });

        //Sets fonts to components in cartSummaryPanel
        buyButton.setFont(newFont);

        // Adds components to cartSummaryPanel
        orderSummaryPanel.add(buyButton, "span 2");

    }

    private void buildOrderProcessingPanel(MainWindow mainWindow) {

        // Set dimensions for the panel for each individual computer in cart
        orderProcessingPanel.setLayout(new MigLayout("","[grow]", "[][center]"));
        orderProcessingPanel.setSize(1000, 200);

        // Show Order in a Label

        Order order = mainWindow.getOrder();
        if(order != null) {
            JLabel orderDetailsHeading = new JLabel("Order Details");
            JLabel orderDetails = new JLabel("Order: " + order.toDisplay());
            orderDetails.setFont(font30);
            // Create Deliver to Chef Label
            JLabel deliverToChefHeading = new JLabel("To Chef");

            // Create Cooking/Cooked Label
            JLabel cookingHeading = new JLabel("State");

            // Create Delivered to Customer Label
            JLabel deliveredToCustomerHeading = new JLabel("Customer Status");
            orderDetailsHeading.setFont(font30);
            deliverToChefHeading.setFont(font30);
            cookingHeading.setFont(font30);
            deliveredToCustomerHeading.setFont(font30);

            orderProcessingPanel.add(orderDetailsHeading);
            orderProcessingPanel.add(deliverToChefHeading);
            orderProcessingPanel.add(cookingHeading);
            orderProcessingPanel.add(deliveredToCustomerHeading,"Wrap");

            orderProcessingPanel.add(orderDetails);
            orderProcessingPanel.add(mainWindow.deliverToChef);
            orderProcessingPanel.add(mainWindow.cooking);
            orderProcessingPanel.add(mainWindow.deliveredToCustomer);

        }
    }

    private void buildCartPanel(MainWindow mainWindow) {

        // Set dimensions for the panel for each individual computer in cart
        orderDetailsPanel.setSize(1000, 200);

        Order order = mainWindow.getOrder();
            //Computer computer = new Computer();


            // Computer Details
        JLabel orderDetails = new JLabel(order.toHtml());
        orderDetailsPanel.add(orderDetails);

        // subPanel houses individual price, quantity and total price for each item
        JPanel subPanel = new JPanel();

        subPanel.setLayout(new MigLayout("","[50%,right][50%,left]"));
        // Base Price components
        JLabel priceAmountLabel = new JLabel("€ " + order.basePrice);
        JLabel priceLabel = new JLabel("Price: ");
        subPanel.add(priceLabel);
        subPanel.add(priceAmountLabel, "wrap");

        // Quantity ComboBox
        JLabel quantityLabel = new JLabel("Quantity: ");
        subPanel.add(quantityLabel);
        JComboBox quantityComboBox = new JComboBox(quantityArray);
        quantityComboBox.setSelectedIndex(order.getQuantity());
        subPanel.add(quantityComboBox, "wrap");

        // Total Price Components
        int totalPrice = order.basePrice;
        JLabel totalPriceLabel = new JLabel("Total Price:");
        JLabel totalPriceAmountLabel = new JLabel("€" + totalPrice);
        subPanel.add(totalPriceLabel);
        subPanel.add(totalPriceAmountLabel, "wrap");

        orderDetailsPanel.add(subPanel);

        quantityComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                order.setQuantity(quantityComboBox.getSelectedIndex());
                totalPriceAmountLabel.setText("€"+order.getTotalPrice());

                subPanel.repaint();
            }
        });
        orderDetailsPanels.add(orderDetailsPanel);

    }
}
