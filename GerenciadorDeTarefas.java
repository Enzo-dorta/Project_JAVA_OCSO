import java.util.PriorityQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GerenciadorDeTarefas {
    private final PriorityQueue<Tarefa> tarefas;

    public GerenciadorDeTarefas() {
        // Prioridade maior primeiro
        this.tarefas = new PriorityQueue<>((t1, t2) -> Integer.compare(t2.getPrioridade(), t1.getPrioridade()));
    }

    public synchronized void adicionarTarefa(Tarefa tarefa) {
        tarefas.add(tarefa);
        System.out.println("Tarefa adicionada: " + tarefa);
    }

    public synchronized void removerTarefa(Tarefa tarefa) {
        if (tarefas.remove(tarefa)) {
            System.out.println("Tarefa removida: " + tarefa);
        } else {
            System.out.println("Tarefa não encontrada: " + tarefa);
        }
    }

    public void executarTarefas() {
        ExecutorService executor = Executors.newFixedThreadPool(tarefas.size());
        while (!tarefas.isEmpty()) {
            Tarefa tarefa = tarefas.poll();
            executor.execute(tarefa);
        }
        executor.shutdown();
    }
}
