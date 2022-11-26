import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SimulationView extends JFrame {
    private JTextField nrOfClients = new JTextField(10);
    private JTextField nrOfQueues = new JTextField(10);
    private JTextField minArrivalTime = new JTextField(10);
    ;
    private JTextField maxArrivalTime = new JTextField(10);
    ;
    private JTextField minServiceTime = new JTextField(10);
    ;
    private JTextField maxServiceTime = new JTextField(10);
    ;
    private JTextField simulationPeriod = new JTextField(10);
    ;
    private JButton generate = new JButton("GENERATE");

    SimulationView() {

        JPanel content = new JPanel();
        this.setContentPane(content);
        this.pack();
        this.setTitle("Queues/Clients management");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 500);
        content.setLayout(null);

        JLabel l1;
        Font f3 = new Font(Font.DIALOG, Font.ITALIC, 15);
        content.add(l1 = new JLabel("Cati clienti stau azi la coada?"));
        l1.setBounds(30, 30, 300, 20);
        l1.setFont(f3);
        nrOfClients.setBounds(300, 30, 100, 20);
        content.add(nrOfClients);
        content.add(l1 = new JLabel("Cate cozi avem azi?"));
        l1.setBounds(30, 80, 300, 20);
        l1.setFont(f3);
        nrOfQueues.setBounds(300, 80, 100, 20);
        content.add(nrOfQueues);

        content.add(l1 = new JLabel("Timpul minim de a ajunge la coada?"));
        l1.setBounds(30, 130, 300, 20);
        l1.setFont(f3);
        minArrivalTime.setBounds(300, 130, 100, 20);
        content.add(minArrivalTime);
        content.add(l1 = new JLabel("Dar timpul maxim?"));
        l1.setBounds(30, 180, 300, 20);
        l1.setFont(f3);
        maxArrivalTime.setBounds(300, 180, 100, 20);
        content.add(maxArrivalTime);

        content.add(l1 = new JLabel("Timpul minim de a sta la coada?"));
        l1.setBounds(30, 230, 300, 20);
        l1.setFont(f3);
        minServiceTime.setBounds(300, 230, 100, 20);
        content.add(minServiceTime);
        content.add(l1 = new JLabel("Dar timpul maxim?"));
        l1.setBounds(30, 280, 300, 20);
        l1.setFont(f3);
        maxServiceTime.setBounds(300, 280, 100, 20);
        content.add(maxServiceTime);

        content.add(l1 = new JLabel("Care e durata simularii?"));
        l1.setBounds(30, 330, 300, 20);
        l1.setFont(f3);
        simulationPeriod.setBounds(300, 330, 100, 20);
        content.add(simulationPeriod);

        generate.setBounds(300, 380, 100, 50);
        content.add(generate);

        ImageIcon lineIcon = new ImageIcon("src/main/resources/line.jpg");
        JLabel lineLabel = new JLabel(lineIcon);
        lineLabel.setBounds(430, 30, 350, 150);
        content.add(lineLabel);

        ImageIcon manIcon = new ImageIcon("src/main/resources/man.jpeg");
        JLabel manLabel = new JLabel(manIcon);
        manLabel.setBounds(430, 200, 350, 233);
        content.add(manLabel);
    }

    void addGenerateListener(ActionListener mal) {
        generate.addActionListener(mal);
    }

    public JTextField getNrOfClients() {
        return nrOfClients;
    }

    public JTextField getNrOfQueues() {
        return nrOfQueues;
    }

    public JTextField getMinArrivalTime() {
        return minArrivalTime;
    }

    public JTextField getMaxArrivalTime() {
        return maxArrivalTime;
    }

    public JTextField getMinServiceTime() {
        return minServiceTime;
    }

    public JTextField getMaxServiceTime() {
        return maxServiceTime;
    }

    public JTextField getSimulationPeriod() {
        return simulationPeriod;
    }

    void showError(String errMessage) {
        JOptionPane.showMessageDialog(this, errMessage);
    }

}
