
public abstract class Process implements Comparable<Process> {
	private int id;
	private int priority;
	private int memory; // size of memory process owns
	private String state;
	private int memoryStart; // first index in the array representing memory
	private String pcb;
	public Process(int id, int priority, int memory,String pcb) {
		this.id = id;
		this.priority = priority;
		this.memory = memory;
		this.state= "New";
		
		this.pcb=pcb;
	}

	public int getID() {
		return id;
	}

	public int getPriority() {
		return priority;
	}

	public int getMemory() {
		return memory;
	}

	public String getState() {
		return state;
	}

//	public int getMemoryStart() {
//		return memoryStart;
//	}

	public void setMemory(int memory) {
		this.memory = memory;
	}

	public void setState(String state) {
		this.state = state;
	}

//	public void setMemoryStart(int memoryStart) {
//		this.memoryStart = memoryStart;
//	}

	public void setID(int id) {
		this.id = id;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}
	public String getPcb() {
		return pcb;
	}
	public void setPcb(String pcb) {
		this.pcb=pcb;
	}
	public int compareTo(Process p) {
		return this.priority - priority;
		// the process with the least priority goes first
	}
}
