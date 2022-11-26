import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SimulationView2 extends JFrame {
    private List<Client> generatedCLients = new ArrayList<>();
    JTextArea textArea = new JTextArea(5, 5);
    JTextArea[] textAreaQueues = new JTextArea[25];
    JPanel content = new JPanel();
    JLabel[] queues = new JLabel[1000];
    JLabel[] clients = new JLabel[1000];
    private JLabel jTime = new JLabel("0");
    private int nrOfQueues;
    private int nrOfClients;

    public JLabel getjTime() {
        return jTime;
    }

    SimulationView2(int nrOfClients, int nrOfQueues, List<Client> generatedCLients) {
        this.generatedCLients = generatedCLients;
        this.nrOfQueues = nrOfQueues;
        this.nrOfClients = nrOfClients;
        JPanel content = new JPanel();
        this.setContentPane(content);
        this.pack();
        this.setTitle("Simulation");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 500);
        content.setLayout(null);

        System.out.println(nrOfClients);
        System.out.println(nrOfQueues);
        for (int i = 0; i < nrOfQueues; i++) {
            queues[i] = new JLabel("COADA " + i);
            queues[i].setBounds(30 + (i % 6) * 100, 15 + i / 6 * 100, 70, 15);
            content.add(queues[i]);
        }
        Font f = new Font(Font.DIALOG, Font.BOLD, 50);
        jTime.setFont(f);
        content.add(jTime);
        jTime.setBounds(680, 30, 100, 50);


        textArea.setEditable(false);
        JTextArea textAreaList = new JTextArea();
        textAreaList.add(textArea);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(570, 330, 170, 120);
        content.add(scrollPane);
        for (int i = 0; i < nrOfQueues; i++) {
            textAreaQueues[i] = new JTextArea(3, 3);
            textAreaQueues[i].setBounds((10 + (i % 6) * 100), 30 + i / 6 * 100, 80, 80);
            content.add(textAreaQueues[i]);
            textAreaQueues[i].setEditable(false);
        }
    }

    public void addThingsToScroll(String where, Client c) {
        if (where.equals("generatedClients"))
            textArea.append(c.toString() + "\n");
        else {
            for (int i = 0; i < nrOfQueues; i++)
                if (where.equals("Coada " + i))
                    textAreaQueues[i].append("ID = " + c.getID() + "\n");
        }
    }

    public void removeThingsFromScroll(String which) {
        if (which.equals("")) {
            String ramas = textArea.getText().substring(textArea.getText().indexOf('}'));
            textArea.setText("");
            textArea.append(ramas.substring(2));
        } else {
            for (int i = 0; i < nrOfQueues; i++)
                if (textAreaQueues[i].getText().contains(which)) {
                    String ramas = textAreaQueues[i].getText().substring(textAreaQueues[i].getText().indexOf('\n'));
                    textAreaQueues[i].setText("");
                    textAreaQueues[i].append(ramas.substring(1));
                    break;
                }
        }
    }
}
