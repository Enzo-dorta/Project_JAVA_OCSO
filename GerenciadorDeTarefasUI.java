import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GerenciadorDeTarefasUI extends JFrame {
    private final GerenciadorDeTarefas gerenciador;

    public GerenciadorDeTarefasUI() {
        gerenciador = new GerenciadorDeTarefas();
        setTitle("Gerenciador de Tarefas");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JTextField descricaoField = new JTextField();
        JTextField tempoField = new JTextField();
        JComboBox<String> prioridadeComboBox = new JComboBox<>(new String[]{"Baixa", "Média", "Alta"});
        JButton adicionarButton = new JButton("Adicionar Tarefa");
        JButton executarButton = new JButton("Executar Tarefas");

        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        inputPanel.add(new JLabel("Descrição:"));
        inputPanel.add(descricaoField);
        inputPanel.add(new JLabel("Tempo (ms):"));
        inputPanel.add(tempoField);
        inputPanel.add(new JLabel("Prioridade:"));
        inputPanel.add(prioridadeComboBox);

        inputPanel.add(adicionarButton);
        inputPanel.add(executarButton);

        JTextArea logArea = new JTextArea();
        logArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(logArea);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = gerenciador.hashCode(); // ID único baseado no hash do objeto
                    String descricao = descricaoField.getText();
                    int tempo = Integer.parseInt(tempoField.getText());
                    String prioridadeStr = (String) prioridadeComboBox.getSelectedItem();

                    int prioridade;

                    switch (prioridadeStr) {
                        case "Alta":
                            prioridade = 3;
                            break;
                        case "Média":
                            prioridade = 2;
                            break;
                        default:
                            prioridade = 1;
                            break;
                    }

                    Tarefa tarefa = new Tarefa(id, descricao, tempo, prioridade, logArea);
                    gerenciador.adicionarTarefa(tarefa);
                    logArea.append("Tarefa adicionada: " + tarefa + "\n");
                } catch (NumberFormatException ex) {
                    logArea.append("Erro ao adicionar tarefa: valores inválidos.\n");
                }
            }
        });

        executarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logArea.append("Executando tarefas...\n");
                gerenciador.executarTarefas();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GerenciadorDeTarefasUI().setVisible(true));
    }
}
