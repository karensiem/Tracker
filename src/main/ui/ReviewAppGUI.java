package ui;

import model.CoffeeShop;
import model.Tracker;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReviewAppGUI extends JFrame implements ActionListener {
    private Tracker tracker;
    private JList<CoffeeShop> trackerJ;
    private JList<CoffeeShop> filterJ;
    private static final int HEIGHT = 250;
    private static final int WIDTH = 600;
    private static final String JSON_FILES = "./data/tracker.json";
    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;
    private JButton addCS;
    private JButton removeCS;
    private JButton filterCS;

    private final String[] labelStrings = {
            "Name: ", "Address: ", "Rating: ", "Visited: "
    };
    private JTextField name;
    private JTextField address;
    private JTextField rating;
    private JTextField visited;

    private DefaultListModel defModel;
    private DefaultListModel filterModel;


    //EFFECTS: constructs JSON writer, reader, and initializes the display
    public ReviewAppGUI() {
        super("Coffee Shop Tracker");
        jsonWriter = new JsonWriter(JSON_FILES);
        jsonReader = new JsonReader(JSON_FILES);

        initializeDisplay();

    }

    //EFFECTS: initializes main JFrame
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

    //MODIIFIES: this.
    //EFFECTS: constructs panel with text fields
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


    //EFFECTS: constructs name text fields
    private JTextField enterName() {
        name = new JTextField(10);
        name.setSize(100, 20);
        name.setFont(new Font("Cinzel", Font.PLAIN, 13));
        name.setVisible(true);
        return name;
    }

    //EFFECTS: constructs address text fields
    private JTextField enterAddress() {
        address = new JTextField();
        address.setVisible(true);
        address.setSize(100, 20);
        address.setFont(new Font("Cinzel", Font.PLAIN, 13));
        return address;
    }

    //EFFECTS: constructs rating text fields
    private JTextField enterRating() {
        rating = new JTextField();
        rating.setVisible(true);
        rating.setSize(100, 20);
        rating.setFont(new Font("Cinzel", Font.PLAIN, 13));
        return rating;
    }

    //EFFECTS: constructs visited text fields
    private JTextField enterVisited() {
        visited = new JTextField();
        visited.setVisible(true);
        visited.setSize(100, 20);
        visited.setFont(new Font("Cinzel", Font.PLAIN, 13));
        return visited;
    }

    //EFFECTS: constructs the "add" button
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

    //EFFECTS: constructs the "remove" button
    private JButton removeCSButton() {
        removeCS = new JButton("remove");
        removeCS.setVisible(true);
        removeCS.setFont(new Font("Cinzel", Font.BOLD, 13));
        removeCS.addActionListener(this);
        removeCS.setActionCommand("add");
        removeCS.setBorderPainted(true);
        removeCS.setOpaque(true);
        removeCS.setContentAreaFilled(true);
        return removeCS;
    }

    //EFFECTS: constructs the "filter" button
    private JButton filterCSButton() {
        filterCS = new JButton("filter");
        filterCS.setVisible(true);
        filterCS.setFont(new Font("Cinzel", Font.BOLD, 13));
        filterCS.addActionListener(this);
        filterCS.setActionCommand("filter");
        filterCS.setBorderPainted(true);
        filterCS.setOpaque(true);
        filterCS.setContentAreaFilled(true);
        return filterCS;
    }


    //EFFECTS: construct panel that adds the button
    private JComponent buttons() {
        JPanel button = new JPanel();
        button.setLayout(new GridLayout(0,3));
        button.setSize(new Dimension(0, 0));
        add(button, BorderLayout.PAGE_END);

        button.add(addCSButton());
        button.add(removeCSButton());
        button.add(filterCSButton());
        return button;
    }


    //EFFECTS: constructs menu bar with save and load functions
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

    //EFFECTS: helper that connects action listener with menu item
    private void addMenuAction(JMenu menu, AbstractAction action) {
        JMenuItem menuItem = new JMenuItem(action);
        menu.add(menuItem);
    }


    private class SavedAction extends AbstractAction {

        //EFFECTS: constructs sub "Save" item
        SavedAction() {
            super("save");
        }

        //EFFECTS: links action performed with the saved menu item
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

        //EFFECTS: constructs sub "Load" item
        LoadAction() {
            super("load");
        }

        //EFFECTS: links action performed with the load menu item
        @Override
        public void actionPerformed(ActionEvent evt) {
            load();
        }
    }


    //EFFECTS: constructs coffee shop and adds to list and tracker
    private CoffeeShop createCS() {
        String n = name.getText();
        String a = address.getText();
        double r = Integer.parseInt(rating.getText());
        boolean v = Boolean.parseBoolean(visited.getText());
        CoffeeShop coffeeShop = new CoffeeShop(n, a, r, v);

        defModel.addElement(coffeeShop.getName());
        return coffeeShop;
    }


    //EFFECTS: constructs icon for saved pop up
    public ImageIcon coffeeIcon() {
        ImageIcon coffee = new ImageIcon("./data/coffeeSmall.png");
        Image image = coffee.getImage();
        Image fitImage = image.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH);
        coffee = new ImageIcon(fitImage);
        return coffee;
    }


    //EFFECTS: constructs pop up for coffee shops saved and saves the coffee shop tracker
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


    //EFFECTS: loads the saved tracker to the right panel
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


    //EFFECTS: returns right panel for the JFrame and list out added coffee shops and/or loaded tracker
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

    //MODIIFIES: this.
    //EFFECTS: clears out the text fields
    private void clearForm() {
        name.setText("");
        address.setText("");
        rating.setText(null);
        visited.setText(null);
    }

    //EFFECTS: constructs list to display for the filtered function
    public JPanel filterList() {
        JPanel filterList = new JPanel(new BorderLayout());
        filterList.setVisible(true);
        filterList.setPreferredSize(new Dimension(150, 200));
        Border border = new LineBorder(Color.BLACK,2, true);
        filterList.setBorder(border);
        filterList.setLayout(new BorderLayout());
        JLabel text = new JLabel("High rated Coffee Shops:");
        filterJ = new JList<>();
        filterModel = new DefaultListModel();
        filterJ.setModel(filterModel);

        for (CoffeeShop c : tracker.getCSList()) {
            if (c.getRating() >= 3.5) {
                filterModel.addElement(c.getName());
            }
        }

        filterList.add(filterJ, BorderLayout.CENTER);

        return filterList;
    }

    //EFFECTS: constructs pop up for filtered high rated coffee shops
    public JFrame filter() {
        JFrame filterPopUp = new JFrame();
        filterPopUp.setLayout(new BorderLayout());
        JLabel text = new JLabel("High rated Coffee Shop:");

        filterPopUp.setDefaultCloseOperation(filterPopUp.DISPOSE_ON_CLOSE);
        filterPopUp.setSize(200, 170);
        text.setFont(new Font("Cinzel", Font.PLAIN, 12));
        filterPopUp.add(text);
        filterPopUp.add(filterList(), BorderLayout.CENTER);
        filterPopUp.setLocationRelativeTo(null);
        filterPopUp.setVisible(true);

        return filterPopUp;
    }



    //EFFECTS: action performed when any button is clicked
    @Override
    public void actionPerformed(ActionEvent act) {
        if ("add".equals(act.getActionCommand())) {
            tracker.addCS(createCS());
            clearForm();
        } else {
            if ("filter".equals(act.getActionCommand())) {
                filter();
            }
        }
    }




    public static void main(String[] args) {
        new ReviewAppGUI();
    }

}
