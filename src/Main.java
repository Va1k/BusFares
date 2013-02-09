import javax.swing.*;
import java.awt.event.*;
import java.text.*;
import java.util.Locale;

public class Main extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox comboSections;
    private JComboBox comboPassenger;
    private JRadioButton radioPeak;
    private JRadioButton radioOffPeak;
    private JTextField txtboxResult;

    public Main() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

// call onCancel() when the exit button on the window is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

// call onCancel() when someone hits ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {    // Worst code ever, puts everything inside of the 'onOK' method
// Variables :D
        double total = 0;
        String sections = (String) comboSections.getSelectedItem();
        String passenger = (String) comboPassenger.getSelectedItem();
// Let's get down to business
        // Works out what the section cost is
        if (sections == "1 Section") {
            total = 1.20;
        } else if (sections == "2 Sections") {
            total = 2.20;
        } else if (sections == "3 Sections") {
            total = 3.00;
        } else if (sections == "4 Sections") {
            total = 3.60;
        } else {
            dispose(); // something is wrong, end...
        }
        // Passenger Types Calculation
        if (passenger == "Child") {
            total = (total / 2);
        } else if (passenger == "Pensioner") {
            total = (total * 0.25);
        } else {
           //do nothing
        }
        // Peak or Off Peak Calculation
        if (radioOffPeak.isSelected()) {
            total = (total /2);
        }
// Output
        // Converts 'double' into a 'string' and formats it like a currency
        NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);
        String result = n.format(total);
        // Outputs the shiny new string into the result text box
        txtboxResult.setText(result);
    }

    private void onCancel() {  // What the cancel button does GG
        dispose();
    }

    public static void main(String[] args) {     // Main method, starts the whole GUI thing
        Main dialog = new Main();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
