import java.util.PriorityQueue;

public class User {
	private int id;
	private PriorityQueue<Process> processQueue;

	public User() {
		// this.id = id;
		this.id = 0;
		processQueue = new PriorityQueue<>();
	}

	public int getID() {
		upProcess();
		return id;
	}

	public PriorityQueue<Process> getPriorityQueue() {
		return processQueue;
	}

	public void pushProcess(Process process) {
		try {
			getPriorityQueue().add(process);
			process.setState("Ready");
		} catch (Exception e) {

		}
	}

	public void upProcess() {
		this.id++;
	}
}
