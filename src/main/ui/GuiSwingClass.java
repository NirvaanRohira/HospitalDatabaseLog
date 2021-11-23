package ui;

import model.Event;
import model.EventLog;
import model.ListOfPatientData;
import model.PatientData;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


//Represents the GUI class using the Java Swing Library
public class GuiSwingClass extends JPanel {
    private static final String JSON_STORE = "./data/patients.json";
    private static final String IMAGE = "./data/QuitPic.jpeg";
    private final JsonReader jsonReader;
    private final JsonWriter jsonWriter;

    private static final String addString = "Add";
    private static final String saveString = "Save";
    private static final String loadString = "Load";
    private static final String quitString = "Quit";
    private ImageIcon image;

    private JList list;
    private JFrame frame;
    private final JLabel name = new JLabel("Name: ");
    private final JLabel age = new JLabel("Age: ");
    private final JLabel daysStayed = new JLabel("Days Stayed: ");
    private final JLabel treatmentRecieved = new JLabel("Treatment Recieved: ");
    private final JLabel condition = new JLabel("Condition: ");
    private final JLabel cost = new JLabel("Cost: ");
    private JButton saveButton;
    private JButton loadButton;
    private JButton addButton;
    private JButton quitButton;
    private JTextField patientName;
    private JTextField patientAge;
    private JTextField patientDaysStayed;
    private JTextField patientTreatmentRecieved;
    private JTextField patientCondition;
    private JTextField patientCost;
    private final DefaultListModel patientList = new DefaultListModel();
    private ListOfPatientData patientList2 = new ListOfPatientData("Patient List");
    private JPanel testPanel;


    //EFFECTS: Creates a Constructor
    public GuiSwingClass() {
        super(new BorderLayout());
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        frame = new JFrame();
        frame.setBounds(0, 0, 7000, 700);

        refreshListOfPatientData();
        listScrollPane();
        createBoxPanel();


        frame.add(this);
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            //EFFECTS: Creates an event Log
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                EventLog el = EventLog.getInstance();
                for (Event next : el) {
                    System.out.println(next.toString() + "\n");
                }
                System.exit(0);
            }
        });


        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setVisible(true);


    }

    // EFFECTS: Create a panel that uses the BoxLayout and adds all the panel functionalities.
    @SuppressWarnings("methodlength")
    public void createBoxPanel() {

        JScrollPane listScrollPane = new JScrollPane(list);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.add(loadButton);
        buttonPane.add(saveButton);
        saveButton.setEnabled(true);
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(name);
        buttonPane.add(patientName);
        buttonPane.add(age);
        buttonPane.add(patientAge);
        buttonPane.add(daysStayed);
        buttonPane.add(patientDaysStayed);
        buttonPane.add(treatmentRecieved);
        buttonPane.add(patientTreatmentRecieved);
        buttonPane.add(condition);
        buttonPane.add(patientCondition);
        buttonPane.add(cost);
        buttonPane.add(patientCost);
        buttonPane.add(addButton);
        buttonPane.add(quitButton);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 100, 50));
        add(listScrollPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.PAGE_END);


    }


    //Creates a scrollable list in a panel
    @SuppressWarnings("methodlength")
    public void listScrollPane() {
        newJList();
        addButton = new JButton(addString);
        AddListener addListener = new AddListener(addButton);
        AudioListener audioListener = new AudioListener();
        addButton.setActionCommand(addString);
        addButton.addActionListener(addListener);
        addButton.addActionListener(audioListener);
        addButton.setEnabled(false);

        saveButton = new JButton(saveString);
        saveButton.setActionCommand(saveString);
        saveButton.addActionListener(new SaveListener());
        saveButton.addActionListener(audioListener);
        saveButton.setEnabled(false);

        loadButton = new JButton(loadString);
        loadButton.setActionCommand(loadString);
        loadButton.addActionListener(new LoadListener());
        loadButton.addActionListener(audioListener);

        quitButton = new JButton(quitString);
        quitButton.setActionCommand(quitString);
        quitButton.addActionListener(new PictureListener());
        quitButton.addActionListener(audioListener);


        patientName = new JTextField();
        patientName.addActionListener(new LoadListener());
        patientName.getDocument().addDocumentListener(addListener);

        patientAge = new JTextField();
        patientAge.addActionListener(new LoadListener());
        patientAge.getDocument().addDocumentListener(addListener);

        patientDaysStayed = new JTextField();
        patientDaysStayed.addActionListener(new LoadListener());
        patientDaysStayed.getDocument().addDocumentListener(addListener);

        patientTreatmentRecieved = new JTextField();
        patientTreatmentRecieved.addActionListener(new LoadListener());
        patientTreatmentRecieved.getDocument().addDocumentListener(addListener);

        patientCondition = new JTextField();
        patientCondition.addActionListener(new LoadListener());
        patientCondition.getDocument().addDocumentListener(addListener);

        patientCost = new JTextField();
        patientCost.addActionListener(new LoadListener());
        patientCost.getDocument().addDocumentListener(addListener);

        image = new ImageIcon(IMAGE);
        image.getImage();
        image.setDescription(IMAGE);


    }

    // REQUIRES: a java list of patient data
    // MODIFIES: this
    // EFFECTS:  returns a Default List model of patient profiles for JList to process
    private void refreshListOfPatientData() {
        for (PatientData patient : patientList2.getList()) {
            patientList.addElement((patient.getName() + " : " + patient.getAge() + " : " + patient.getDaysStayed()
                    + " : " + patient.getTreatmentReceived() + " : " + patient.getCondition() + " : "
                    + patient.getTreatmentCost()));
        }
    }

    // EFFECTS: Creates a new JList
    public void newJList() {
        list = new JList(patientList);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.setVisibleRowCount(5);


    }

    //EFFECTS: Saves Patient Data to File
    private void savePatientData() {
        try {
            jsonWriter.open();
            jsonWriter.write(patientList2);
            jsonWriter.close();
            EventLog.getInstance().logEvent(new Event("saved"));
            //stringobject.add("Saved " + patientList2.getNameList() + " to " + JSON_STORE);
            System.out.println("Saved " + patientList2.getNameList() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found... sorry cannot save " + JSON_STORE);
        }
    }

    //MODIFIES: this
    //EFFECTS: Loads a list of saved patient data
    private void loadPatientData() {
        try {
            patientList2 = jsonReader.read();
            EventLog.getInstance().logEvent(new Event("loaded "));
            System.out.println("Loaded " + patientList2.getNameList() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to load data from file... sorry  " + JSON_STORE);
        }

    }

    // EFFECTS: Creates an ActionListener object for save button
    class SaveListener implements ActionListener {

        // EFFECTS: returns the action performed by the save button
        public void actionPerformed(ActionEvent e) {

            savePatientData();


            int size = patientList.getSize();

            //saveButton.setEnabled(size != 0);
        }
    }

    // EFFECTS: Creates an ActionListener object for quit button to show a picture
    class PictureListener implements ActionListener {

        // EFFECTS: returns the action performed by the quit button
        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame picFrame = new JFrame();
            JPanel picPanel = new JPanel();
            JLabel picLabel = new JLabel("Loaded!");
            JLabel label = new JLabel(new ImageIcon(IMAGE));

            picFrame.setPreferredSize(new Dimension(1500, 1400));

            picFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            picFrame.pack();

            picPanel.setLayout(null);
            picFrame.add(picPanel);
            picFrame.add(label);

            picLabel.setBounds(10, 30, 300, 45);
            picPanel.add(picLabel);
            picFrame.setVisible(true);


//            EventLog el = EventLog.getInstance();
//            for (Event next : el) {
//                System.out.println(next.toString() + "\n");
//            }
//            System.exit(0);
//
        }
    }

    // EFFECTS: Creates an ActionListener object for load button
    class LoadListener implements ActionListener {

        // EFFECTS: returns the action performed by the load button
        public void actionPerformed(ActionEvent e) {


            loadPatientData();
            refreshListOfPatientData();
            //saveButton.setEnabled(true);

            int size = patientList.getSize();

            if (size == 0) {
                loadButton.setEnabled(true);

            } else {
                loadButton.setEnabled(false);
            }
        }
    }

    // EFFECTS: returns an audio note saved in the computer under soundName;
    //          Or throws an exception.
    public void playSound(String soundName) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Cannot Play the sound... sorry ");
            ex.printStackTrace();
        }
    }


    // EFFECTS: Creates an Audio ActionListener object for save,load and add buttons
    class AudioListener implements ActionListener {

        // EFFECTS: returns the action performed/audio note on pressing the buttons
        public void actionPerformed(ActionEvent e) {
            playSound("./data/blip.wav");
            //when button is pressed you show image of a tick
        }
    }


    // EFFECTS: Creates an ActionListener object for the add button and the two text fields
    class AddListener implements ActionListener, DocumentListener {
        private boolean alreadyEnabled = false;
        private final JButton button;

        // EFFECTS: Creates an AddListener constructor
        public AddListener(JButton button) {
            this.button = button;
        }

        // EFFECTS: returns the action performed in the class
        @SuppressWarnings("methodlength")
        public void actionPerformed(ActionEvent e) {

            String name = patientName.getText();
            String age = patientAge.getText();
            String daysStayed = patientDaysStayed.getText();
            String treatmentRecieved = patientTreatmentRecieved.getText();
            String condition = patientCondition.getText();
            String cost = patientCost.getText();
            int ageConvert = Integer.parseInt(age);
            int daysStayedConvert = Integer.parseInt(daysStayed);
            int costConvert = Integer.parseInt(cost);
            patientList2.addPatientData(name, ageConvert, daysStayedConvert, treatmentRecieved,
                    condition, costConvert);
            //User didn't type in a unique name...
            if (name.equals("")) {
                Toolkit.getDefaultToolkit().beep();
                patientName.requestFocusInWindow();
                patientName.selectAll();
                return;
            }
            patientList.addElement((patientName.getText() + " : " + patientAge.getText() + " : "
                    + patientDaysStayed.getText() + " : " + patientTreatmentRecieved.getText() + " : "
                    + patientCondition.getText() + " : " + patientCost.getText()));

            patientName.requestFocusInWindow();
            patientName.setText("");

            patientAge.requestFocusInWindow();
            patientAge.setText("");

            patientDaysStayed.requestFocusInWindow();
            patientDaysStayed.setText("");

            patientTreatmentRecieved.requestFocusInWindow();
            patientTreatmentRecieved.setText("");

            patientCondition.requestFocusInWindow();
            patientCondition.setText("");

            patientCost.requestFocusInWindow();
            patientCost.setText("");

        }


        // EFFECTS: Enables the add button when text is inputted
        public void insertUpdate(DocumentEvent e) {
            enableButton();
        }

        // EFFECTS: Disables the add button when no text inputted
        public void removeUpdate(DocumentEvent e) {
            handleEmptyTextField(e);
        }

        // EFFECTS: Updates the enable state of the button
        public void changedUpdate(DocumentEvent e) {
            if (!handleEmptyTextField(e)) {
                enableButton();
            }
        }

        // EFFECTS: Enables functionality of the button
        private void enableButton() {
            if (!alreadyEnabled) {
                button.setEnabled(true);
            }
        }

        // EFFECTS: Disables functionality of the button
        private boolean handleEmptyTextField(DocumentEvent e) {
            if (e.getDocument().getLength() <= 0) {
                button.setEnabled(false);
                alreadyEnabled = false;
                return true;
            }
            return false;
        }

    }


}