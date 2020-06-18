import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

public class ConnectorDialog extends JDialog {

    Properties dbProperties;
    boolean isCanceled = false;

    JLabel hostLBL = new JLabel("Host");
    JTextField host = new JTextField();

    JLabel portLBL = new JLabel("Port");
    JTextField port = new JTextField();

    JLabel dbNameLBL = new JLabel("DB Name");
    JTextField dbName = new JTextField();

    JLabel userLBL = new JLabel("User");
    JTextField user = new JTextField();

    JLabel passLBL = new JLabel("Password");
    JTextField pass = new JTextField();

    JButton submitBTN = new JButton("submit");
    JButton cancelBTN = new JButton("cancel");


    public ConnectorDialog(JFrame owner, String title, Properties props) {
        super(owner, title, true);

        setSize(400, 400);

        dbProperties = props;

        submitBTN.addActionListener(this::performOperation);

        cancelBTN.addActionListener(this::performOperation);

        JPanel dataPanel = new JPanel();

        dataPanel.setLayout(new GridLayout(5, 2));

        dataPanel.add(hostLBL);
        dataPanel.add(host);

        dataPanel.add(portLBL);
        dataPanel.add(port);

        dataPanel.add(dbNameLBL);
        dataPanel.add(dbName);

        dataPanel.add(userLBL);
        dataPanel.add(user);

        dataPanel.add(passLBL);
        dataPanel.add(pass);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(submitBTN);
        buttonPanel.add(cancelBTN);

        add(dataPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void performOperation(ActionEvent e) {
        if(e.getSource() == cancelBTN) {
            this.isCanceled = true;
        }
        dispose();
    }

    public Properties getProps() {
        dbProperties.setProperty("db_name", dbName.getText());
        dbProperties.setProperty("db_host", host.getText());
        dbProperties.setProperty("db_port", port.getText());
        dbProperties.setProperty("db_user", user.getText());
        return dbProperties;
    }
}
