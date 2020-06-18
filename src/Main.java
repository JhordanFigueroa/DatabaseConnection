import javax.swing.*;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {
        JFrame main = new JFrame("DB Client");

        Properties databaseProperties = new Properties();

        ConnectorDialog inputDBInfo = new ConnectorDialog(main, "Database Info", databaseProperties);
        inputDBInfo.setVisible(true);

        if(inputDBInfo.isCanceled) {
            System.exit(1);
        }

        Connector conn = new Connector(inputDBInfo.getProps(), inputDBInfo.pass.getText());

        if(!conn.connect()) {
            System.exit(1);
        }

        DatabasePanel dbPanel = new DatabasePanel(conn);

        main.setSize(800, 600);
        main.add(dbPanel);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setVisible(true);
    }


}
