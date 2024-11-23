import javax.swing.JTextArea;
import java.util.concurrent.TimeUnit;

public class Tarefa implements Runnable {
    private final int id;
    private final String descricao;
    private final int tempoExecucao; // em milissegundos
    private final int prioridade;
    private final JTextArea logArea;

    public Tarefa(int id, String descricao, int tempoExecucao, int prioridade, JTextArea logArea) {
        this.id = id;
        this.descricao = descricao;
        this.tempoExecucao = tempoExecucao;
        this.prioridade = prioridade;
        this.logArea = logArea;
    }

    public int getPrioridade() {
        return prioridade;
    }

    @Override
    public void run() {
        try {
            logArea.append("Executando tarefa: " + descricao +  "🙋" + "\n");
            TimeUnit.MILLISECONDS.sleep(tempoExecucao);
            logArea.append("Tarefa concluída: " + descricao + "👍" + "\n");
        } catch (InterruptedException e) {
            System.err.println("Tarefa " + descricao + " interrompida.");
        }
    }

    @Override
    public String toString() {
        return "Tarefa{id=" + id + ", descricao='" + descricao + '\'' + 
               ", prioridade=" + prioridade + '}';
    }
}

