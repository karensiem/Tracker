package ui;

import model.CoffeeShop;
import model.Tracker;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReviewAppGUI extends JFrame implements ActionListener {
    private Tracker tracker;
    private JList<CoffeeShop> trackerJ;
    private static final int HEIGHT = 400;
    private static final int WIDTH = 600;
    private static final String JSON_FILES = "./data/tracker.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private JButton addCS;

    private String[] labelStrings = {
            "Name: ", "Address: ", "Rating: ", "Visited: "
    };
    private JTextField name;
    private JTextField address;
    private JTextField rating;
    private JTextField visited;

    private DefaultListModel defModel;

    public ReviewAppGUI() {
        super("Coffee Shop Tracker");
        jsonWriter = new JsonWriter(JSON_FILES);
        jsonReader = new JsonReader(JSON_FILES);

        initializeDisplay();

    }

    private void initializeDisplay() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        addMenuBar();
        buttons();
        fieldsCS();
        add(listDisplay());
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
        panel.add(new JLabel("Rating:"));
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
        addCS.addActionListener(this);
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

        JMenu saveMenu = new JMenu("Save");
        addMenuAction(saveMenu, new SavedAction());
        menuBar.add(saveMenu);

        JMenu loadMenu = new JMenu("Load");
        addMenuAction(loadMenu, new LoadAction());
        menuBar.add(loadMenu);

        setJMenuBar(menuBar);
    }

    private void addMenuAction(JMenu menu, AbstractAction action) {
        JMenuItem menuItem = new JMenuItem(action);
        menu.add(menuItem);
    }

    private class SavedAction extends AbstractAction {
        SavedAction() {
            super("save");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            try {
                saved();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private class LoadAction extends AbstractAction {
        LoadAction() {
            super("load");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            load();
        }
    }

    //creates coffee shop from text fields
    private CoffeeShop createCS() {
        String n = name.getText();
        String a = address.getText();
        double r = Integer.parseInt(rating.getText());
        boolean v = Boolean.parseBoolean(visited.getText());
        CoffeeShop coffeeShop = new CoffeeShop(n, a, r, v);

        defModel.addElement(coffeeShop.getName());
        return coffeeShop;
    }

    public ImageIcon coffeeIcon() {
        ImageIcon coffee = new ImageIcon("./data/coffeeSmall.png");
        Image image = coffee.getImage();
        Image fitImage = image.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH);
        coffee = new ImageIcon(fitImage);
        return coffee;
    }

    public JFrame saved() throws FileNotFoundException {
        JFrame savePopUp = new JFrame();
        savePopUp.setLayout(new GridLayout(2, 1));
        JLabel text = new JLabel("Your tracker has been saved!");

        try {
            jsonWriter.open();
            jsonWriter.write(tracker);
            jsonWriter.close();
            System.out.println("Saved tracker to" + JSON_FILES);
        } catch (FileNotFoundException e) {
            System.out.println("Could not save tracker to: " + JSON_FILES);
        }

        JLabel image = new JLabel(coffeeIcon());
        savePopUp.setDefaultCloseOperation(savePopUp.DISPOSE_ON_CLOSE);
        savePopUp.setSize(200, 170);
        text.setFont(new Font("Cinzel", Font.PLAIN, 12));
        savePopUp.add(image);
        savePopUp.add(text);
        savePopUp.setLocationRelativeTo(null);
        savePopUp.setVisible(true);


        return savePopUp;
    }

    public void load() {
        try {
            tracker = jsonReader.read();
            System.out.println("Loaded tracker from " + JSON_FILES);
        } catch (IOException e) {
            System.out.println("Could not save tracker from: " + JSON_FILES);
        }

        for (CoffeeShop c : tracker.getCSList()) {
            defModel.addElement(c.getName());
        }
    }


    private JComponent listDisplay() {
        JPanel list = new JPanel(new BorderLayout());
        list.setVisible(true);
        list.setPreferredSize(new Dimension(150, 200));
        Border border = new LineBorder(Color.BLACK,2, true);
        list.setBorder(border);
        tracker = new Tracker();
        trackerJ = new JList<>();
        defModel = new DefaultListModel();
        trackerJ.setModel(defModel);

        list.setBorder(BorderFactory.createEmptyBorder(2, 0, 2, 0));
        list.add(new JSeparator(JSeparator.VERTICAL),
                BorderLayout.LINE_START);
        list.add(trackerJ, BorderLayout.CENTER);

        return list;
    }

    private void clearForm() {
        name.setText("");
        address.setText("");
        rating.setText(null);
        visited.setText(null);
    }

    @Override
    public void actionPerformed(ActionEvent act) {
        if ("add".equals(act.getActionCommand())) {
            tracker.addCS(createCS());
            clearForm();
        }
    }


    public static void main(String[] args) {
        new ReviewAppGUI();
    }

}
