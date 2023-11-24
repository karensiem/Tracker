package ui;

import model.CoffeeShop;
import model.Tracker;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReviewAppGUI extends JFrame implements ActionListener {
    private Tracker tracker;
    private static final int HEIGHT = 200;
    private static final int WIDTH = 600;
    private static final String JSON_FILES = "./data/tracker.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private JFrame mainFrame;
    private JDesktopPane desktop;
    private JButton addCS;

    private String[] labelStrings = {
            "Name: ", "Address: ", "Rating: ", "Visited: "
    };
    private JTextField name;
    private JTextField address;
    private JTextField rating;
    private JTextField visited;

    public ReviewAppGUI() {
        super("Coffee Shop Tracker");
        tracker = new Tracker();
        initializeDisplay();

    }

    private void initializeDisplay() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        addMenuBar();
        buttons();
        fieldsCS();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }


    protected JComponent fieldsCS() {
        JPanel panel = new JPanel(new SpringLayout());
        panel.setLayout(new GridLayout(4, 1, 7, 7));

        add(panel, BorderLayout.WEST);
        panel.add(new JLabel("Name:"));
        panel.add(enterName());
        panel.add(new JLabel("Address:"));
        panel.add(enterAddress());
        panel.add(new JLabel("Rating (0-5):"));
        panel.add(enterRating());
        panel.add(new JLabel("Visited?"));
        panel.add(enterVisited());


        return panel;
    }

    private JTextField enterName() {
        name = new JTextField(10);
        name.setSize(100, 20);
        name.setFont(new Font("Cinzel", Font.PLAIN, 13));
        name.setVisible(true);
        return name;
    }

    private JTextField enterAddress() {
        address = new JTextField();
        address.setVisible(true);
        address.setSize(100, 20);
        address.setFont(new Font("Cinzel", Font.PLAIN, 13));
        return address;
    }

    private JTextField enterRating() {
        rating = new JTextField();
        rating.setVisible(true);
        rating.setSize(100, 20);
        rating.setFont(new Font("Cinzel", Font.PLAIN, 13));
        return rating;
    }

    private JTextField enterVisited() {
        visited = new JTextField();
        visited.setVisible(true);
        visited.setSize(100, 20);
        visited.setFont(new Font("Cinzel", Font.PLAIN, 13));
        return visited;
    }

    private JButton addCSButton() {
        addCS = new JButton("add");
        addCS.setVisible(true);
        addCS.setFont(new Font("Cinzel", Font.BOLD, 13));
        addCS.setActionCommand("add");
        addCS.setBorderPainted(true);
        addCS.setOpaque(true);
        addCS.setContentAreaFilled(true);
        return addCS;
    }

    private JComponent buttons() {
        JPanel addButton = new JPanel();
        addButton.setLayout(new GridLayout(0,1));
        addButton.setSize(new Dimension(0, 0));
        add(addButton, BorderLayout.PAGE_END);

        addButton.add(addCSButton());
        return addButton;
    }


    private void addMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu sensorMenu = new JMenu("Save");
        sensorMenu.setMnemonic('S');
        menuBar.add(sensorMenu);

        JMenu codeMenu = new JMenu("Load");
        codeMenu.setMnemonic('L');
        menuBar.add(codeMenu);

        setJMenuBar(menuBar);
    }

    private void refreshTextFields() {
        name.setText("");
        address.setText("");
        rating.setText(null);
        visited.setText(null);
    }

    //creates coffee shop from text fields
    private CoffeeShop createCS() {
        String n = name.getText();
        String a = address.getText();
        double r = Integer.parseInt(rating.getText());
        boolean v = Boolean.parseBoolean(visited.getText());
        CoffeeShop coffeeShop = new CoffeeShop(n, a, r, v);

        return coffeeShop;
    }

    public void saved() throws FileNotFoundException {
        try {
            jsonWriter.open();
            jsonWriter.write(tracker);
            jsonWriter.close();
            System.out.println("Saved tracker to" + JSON_FILES);
        } catch (FileNotFoundException e) {
            System.out.println("Could not save tracker to: " + JSON_FILES);
        }
    }

    public void load() {
        try {
            tracker = jsonReader.read();
            System.out.println("Loaded tracker from " + JSON_FILES);
        } catch (IOException e) {
            System.out.println("Could not save tracker from: " + JSON_FILES);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("add".equals(e.getActionCommand())) {
            tracker.addCS(createCS());
//            addToListScreen();
        } else if ("save".equals(e.getActionCommand())) {
            try {
                saved();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        } else {
            load();
        }
    }


    public static void main(String[] args) {
        new ReviewAppGUI();
    }

}
