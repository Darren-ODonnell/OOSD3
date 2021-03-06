import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomisePanel extends JPanel {

    private JLabel configLabel = new JLabel();

    final private JPanel topPanel  = new JPanel();
    final private JPanel customisePanel = new JPanel();
    private ImageIcon icon;
    private Order order;

    final int PROCESSOR = 0;
    final int RAM = 1;
    final int DISK_TYPE = 2;
    final int DISK_SPACE = 3;

    JPanel[] customisePanels = new JPanel[4];

    Font font = new Font(Font.SERIF, Font.PLAIN,20);
    Font fontBold = new Font(Font.SERIF, Font.BOLD,20);

    public CustomisePanel(MainWindow mainWindow){
        // Sets font for all instances of JLabel and JRadioButton
        UIManager.put("Label.font",font);
        UIManager.put("RadioButton.font", font);

        Dimension dim = new Dimension(1000,1000);
        this.setSize(dim);
        this.setLayout(new MigLayout("","[100%]", "[30%][18%][18%][18%][18%]"));

        icon = new ImageIcon("Images/ProBook150.png");
        order = new Order();

        buildTopPanel(mainWindow);
        buildCustomisePanel();

        add(topPanel, "wrap");
        add(customisePanel);

    }

    // Adds all necessary components to fill top Panel
    public void buildTopPanel(MainWindow mainWindow) {

        // Create 4 columns here for Image, Headers, Details and Price
        topPanel.setLayout(new MigLayout("","[30%, center][25%, center][25%, left][20%, center]"));
        topPanel.setSize(1000,200);

        // create component for headers and bold this text
        JLabel label = new JLabel();
        label.setFont(fontBold);
        label.setText(order.headers());

        // create button
        JButton addToCartButton = new JButton("Add To Cart");
        addToCartButton.setFont(fontBold);
        addToCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                mainWindow.addToCart(order);
                JLabel label = new JLabel("Computer added to cart successfully");
                label.setFont(new Font("Serif", Font.BOLD, 18));
                JOptionPane.showMessageDialog(CustomisePanel.this,label);
                order = new Order();
            }
        });


        // create component for the details of the computer
        configLabel.setFont(font);
        configLabel.setText(order.toHtml());

        // add these four elements to the top panel
        topPanel.add(label);
        topPanel.add(configLabel);
        topPanel.add(addToCartButton);



    }

    public void buildCustomisePanel() {
        customisePanel.setLayout(new MigLayout("","[100%]","[25%][25%][25%][25%]"));
        customisePanel.setSize(1000,800);

        // configuration options
        String[] processorOptions   = new String[]{"i3", "i5", "i7", "i9"};
        String[] ramOptions         = new String[]{"8 GB", "16 GB", "32 GB", "64 GB"};
        String[] diskTypeOptions    = new String[]{"SSD","HDD"};
        String[] diskSpaceOptions   = new String[]{"128 GB", "256 GB", "512 GB", "1024 GB"};

        // build each config panel
        buildCustomisePanel("Processor",     PROCESSOR,  processorOptions );
        buildCustomisePanel("RAM",           RAM,        ramOptions );
        buildCustomisePanel("Disk Type",     DISK_TYPE,  diskTypeOptions );
        buildCustomisePanel("Disk Space",    DISK_SPACE, diskSpaceOptions );

        // add panels to customisePanel
        for (JPanel p : customisePanels) {
            customisePanel.add(p,"wrap");
        }
    }

    // Generic creation of radioButtons and a listener which is used for the 4 sets of radio buttons needed in the customise panel
    public JPanel buildRadioButtonPanel(final String[] options,int type) {

        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout("", "[Center]"));
        JRadioButton[] radioButtons = new JRadioButton[options.length]; // the array of radioButtons

        ButtonGroup radioButtonGroup = new ButtonGroup(); // used for exclusion
        for (int i = 0; i < options.length; i++) {
            radioButtons[i] = new JRadioButton(options[i]);
            radioButtons[i].addActionListener(new ActionListener() {
                // int type is used to allow the 4 panels to use the same actionListeners, when a listener is called it is updated accordingly
                int newVar = type;

                @Override
                public void actionPerformed(ActionEvent e) {
                    String str = e.getActionCommand();

                    switch (newVar) {
                        case 0:
                            order.setMainCourseOption(str);
                            break;
                        case 1:
                            order.setSideOption(str);
                            break;
                        case 2:
                            order.setDrinkOption(str);
                            break;
                    }
                    //Updates text in top panel when a radio button is pressed
                    configLabel.setText(order.toHtml());
                }
            });
            radioButtonGroup.add(radioButtons[i]);

            panel.add(radioButtons[i]);
        }
        radioButtons[0].setSelected(true);
        return panel;
    }

    // Adds components to the customise Panel
    public void buildCustomisePanel(String header, int type, String[] options  ) {

        JPanel panel = initCustomisePanel(header);

        JPanel rbPanel = buildRadioButtonPanel(options,type);

        panel.add(rbPanel);

        customisePanels[type] = panel;
    }

    // Creates the initial customise Panel
    public JPanel initCustomisePanel(String headerStr) {
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout("","[30%,center][50%,left][20%, center]"));
        panel.setSize(1000,180);

        // add Header label in first column
        JLabel headerPnl = new JLabel();
        headerPnl.setFont(fontBold);
        headerPnl.setText(headerStr);
        panel.add(headerPnl);

        return panel;
    }

}
