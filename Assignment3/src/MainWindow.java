
import net.miginfocom.swing.MigLayout;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class MainWindow extends JFrame {

    // List of orders currently in the cart;
    private List<Order> orderList = new ArrayList<>();

    // References to each panel
    CustomisePanel customisePanel;
    OrderPanel orderPanel;
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

    public MainWindow() {
        this.setLayout(new MigLayout(""));
        this.setFont(font);

        buildCustomisationPanel();
        buildCartPanel();

       // buildMainPanel();
        // this.add(mainPanel);


        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        buildMenu(this);
        this.setJMenuBar(menuBar);

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
        orderList.add(order);
    }

    // returns cart size
    public int getCartSize() {
        return orderList.size();
    }

    // returns full cart
    public List<Order> getCart() {
        return orderList;
    }

    // Removes all cart items
    public void clearCart() {
        orderList.clear();
    }

    private void buildCartPanel() {
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
                buildCartPanel();

                MainWindow.this.getContentPane().removeAll();
                MainWindow.this.getContentPane().add(orderPanel);

                if (orderList.size() > 0) {
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

                if (orderList.size() > 0) {
                    JLabel label = new JLabel("Are you sure you want to clear the cart?");
                    label.setFont(new Font("Serif", Font.BOLD, 18));
                    int result = JOptionPane.showConfirmDialog(MainWindow.this, label, "Clear Cart", JOptionPane.YES_NO_OPTION);

                    if (result == JOptionPane.YES_OPTION) {
                        orderList.clear();
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

    public List<Order> buildOrders(int count) {

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
        JLabel label = new JLabel("<HTML>Welcome! To view available orders,<br>To view our orders available, click the orders menu option</HTML>");
        label.setFont(new Font("Serif", Font.BOLD, 18));
        JOptionPane.showMessageDialog(null, label);

        return orderList;

    }

}

