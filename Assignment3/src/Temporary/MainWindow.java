package Temporary;
import net.miginfocom.swing.MigLayout;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;

public class MainWindow extends JFrame {

    // List of orders currently in the cart;
//    private List<Order> orderList = new ArrayList<>();

    // References to each panel
    CustomisePanel customisePanel;
    OrderPanel orderPanel;
    Order order = null; // single order object used throughout program
    JPanel mainPanel = new JPanel();


    final private JMenuBar menuBar = new JMenuBar();// Menu Bar contains Home, orders and Cart Menus

    final private JMenu homeMenu = new JMenu("Home");// Home Menu contains an Exit option
    final private JMenuItem exitMenuITem = new JMenuItem("Exit");

    final private JMenu shoppingMenu = new JMenu("orders");// orders contains preset orders option and customise Computer options
    final private JMenuItem customiseMenuItem = new JMenuItem("Make Order");

    final private JMenu cartMenu = new JMenu("Cart");// Cart contains view Cart and clear Cart options
    final private JMenuItem viewCartMenuItem = new JMenuItem("View Cart");
    final private JMenuItem clearCartMenuItem = new JMenuItem("Clear Cart");

    Font font = new Font(Font.SERIF, Font.PLAIN, 20);
    Font font30 = new Font(Font.SERIF, Font.BOLD, 30);
    // Create Deliver to Chef Label
    JLabel deliverToChef = new JLabel("---------");

    // Create Cooking/Cooked Label
    JLabel cooking = new JLabel("-------");

    // Create Delivered to Customer Label
    JLabel deliveredToCustomer = new JLabel("delivered to customer");



    public MainWindow() {
        this.setLayout(new MigLayout(""));
        this.setFont(font);

        buildCustomisationPanel();
        buildOrderPanel();

        // order processing labels
        buildOrderProcessingLabels();


       // buildMainPanel();
        // this.add(mainPanel);


        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        buildMenu(this);
        this.setJMenuBar(menuBar);

    }

    private void buildOrderProcessingLabels() {

        Border border = BorderFactory.createLineBorder(Color.BLUE, 5);

        deliverToChef.setBackground(Color.cyan);
        deliverToChef.setFont(font30);
        deliverToChef.setOpaque(true);
        deliverToChef.setBorder(border);

        deliverToChef.setText("Waiting");

        cooking.setBackground(Color.cyan);
        cooking.setBorder(border);
        cooking.setFont(font30);
        cooking.setOpaque(true);

        cooking.setText("Waiting");

        deliveredToCustomer.setBackground(Color.green);
        deliveredToCustomer.setBorder(border);
        deliveredToCustomer.setFont(font30);
        deliveredToCustomer.setOpaque(true);

        deliveredToCustomer.setText("Waiting");
    }
    public JLabel changeLabelState(JLabel label){
        if(label.getText().equals("Waiting")){
            label.setText("Processing");
            label.setBackground(Color.red);
        }else if(label.getText().equals("Processing")){
            label.setText("Delivered");
            label.setBackground(Color.green);
        }else if(label.getText().equals("Delivered")){
            label.setText("Waiting");
            label.setBackground(Color.gray);

        }
        return label;
        }

    private void buildCustomisationPanel() {
        customisePanel = new CustomisePanel(this);
        customisePanel.setVisible(false);

    }

    // Builds the landing page of the project
    public void buildMainPanel(){
        mainPanel.setLayout(new MigLayout("", "[50%][50%]", "[50%][50%]"));
        String[] pictureArray = new String[]{"Images/ProBook500.png", "Images/EliteBook500.png", "Images/Pavilion500.png", "Images/HP Personal500.png"};
        for(int i =0; i < pictureArray.length; i++) {
            ImageIcon icon = new ImageIcon(pictureArray[i]);
            JLabel picLabel = new JLabel(icon);
            if(i == 1 || i == 3) {
                mainPanel.add(picLabel, "wrap");
            }else{
                mainPanel.add(picLabel);
            }
        }
    }

    // adds computer to the cart
    public void addToCart(Order order) {
        this.order = order;
    }


    // returns full cart
    public Order getOrder() {
        return order;
    }



    // Removes all cart items
    public void clearOrder() {
        order = null;
    }

    public void buildOrderPanel() {
        orderPanel = new OrderPanel(this);
        orderPanel.setVisible(false);

    }

    // Builds the menu and provides functionality to them through ActionListeners
    public void buildMenu(MainWindow mainWindow) {

        menuBar.add(homeMenu);
        menuBar.add(shoppingMenu);
        menuBar.add(cartMenu);

        homeMenu.add(exitMenuITem);

        shoppingMenu.add(customiseMenuItem);

        cartMenu.add(viewCartMenuItem);
        cartMenu.add(clearCartMenuItem);

        // Exit menu item selected
        exitMenuITem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JLabel label = new JLabel("Please Confirm Exit operation");
                label.setFont(new Font("Serif", Font.BOLD, 18));
                if (JOptionPane.showConfirmDialog(MainWindow.this, label, "Exit", JOptionPane.OK_CANCEL_OPTION) == 0) {
                    System.exit(0);
                }
            }
        });


        // Customise orders menu item selected
        customiseMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainWindow.this.getContentPane().removeAll();
                MainWindow.this.getContentPane().add(customisePanel);

                customisePanel.setVisible(true);
                mainWindow.repaint();
            }
        });

        // View Cart menu item selected
        viewCartMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buildOrderPanel();

                MainWindow.this.getContentPane().removeAll();
                MainWindow.this.getContentPane().add(orderPanel);

                if (order != null) {
                    orderPanel.setVisible(true);
                    mainWindow.repaint();
                } else {
                    JLabel label = new JLabel("Cart is empty");
                    label.setFont(new Font("Serif", Font.BOLD, 18));
                    JOptionPane.showMessageDialog(MainWindow.this, label);
                }
            }
        });

        // Clear Cart menu item selected
        clearCartMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (order != null) {
                    JLabel label = new JLabel("Are you sure you want to clear the cart?");
                    label.setFont(new Font("Serif", Font.BOLD, 18));
                    int result = JOptionPane.showConfirmDialog(MainWindow.this, label, "Clear Cart", JOptionPane.YES_NO_OPTION);

                    if (result == JOptionPane.YES_OPTION) {
                        clearOrder();
                        MainWindow.this.getContentPane().remove(orderPanel);
                        MainWindow.this.getContentPane().repaint();
                    }
                } else {
                    JLabel label = new JLabel("Cart Already Empty");
                    label.setFont(new Font("Serif", Font.BOLD, 18));
                    JOptionPane.showMessageDialog(MainWindow.this, label);
                }
            }
        });
    }

    public void buildOrders(int count) {

        String systemLookAndFeelClassName = UIManager.getSystemLookAndFeelClassName();
        try {
            UIManager.setLookAndFeel(systemLookAndFeelClassName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Font f = new Font(Font.SERIF, Font.PLAIN, 24);
        // Sets font for all instances of menu, menuBar and Menuitem
        UIManager.put("Menu.font", f);
        UIManager.put("MenuBar.font", f);
        UIManager.put("MenuItem.font", f);

        // Creates and assigns default values to the main Window
        MainWindow window = new MainWindow();
        window.setTitle("Cosy Cafe");
        window.setSize(1000, 1000);
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        // Displays greeting message on initial opening of project
        JLabel label = new JLabel("<HTML>Welcome!<br>To view our orders available, click the orders menu option</HTML>");
        label.setFont(new Font("Serif", Font.BOLD, 18));
        JOptionPane.showMessageDialog(null, label);

    }

}

