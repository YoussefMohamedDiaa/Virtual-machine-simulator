import java.util.PriorityQueue;

public class Kernel {
	private int id;
	private PriorityQueue<Process> processQueue;

	public Kernel(int id) {
		this.id = id;
		processQueue = new PriorityQueue<>();
	}

	public int getID() {
		return id;
	}

	public PriorityQueue<Process> getPriorityQueue() {
		return processQueue;
	}

	public void pushProcess(Process process) {
		try {
			getPriorityQueue().add(process);
		} catch (Exception e) {

		}
	}
}
