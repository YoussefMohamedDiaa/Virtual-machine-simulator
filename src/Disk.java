import java.util.Arrays;

public class Disk {
	int size;
	String[] memory;
	final static String FREE = "free";

	Disk() {
		size = 16348;
		memory = new String[size];
		Arrays.fill(memory, FREE);
	}

	public boolean assignFile(File file) {
		if (getAvailable() < file.getSize())
			return false;
		for (int i = 0, assigned = 0; assigned < file.getSize(); i++)
			if (memory[i].equals(FREE)) {
				memory[i] = file.getPath();
				assigned++;
			}
		return true;
	}

	public boolean removeProcess(File file) {
		int assigned = 0;
		for (String x : memory)
			if (x.equals(file.getPath()))
				assigned++;
		if (assigned != file.getSize())
			return false;
		for (int i = 0; i < memory.length; i++)
			if (memory[i].equals(file.getPath()))
				memory[i] = FREE;
		return true;
	}

	public int getAvailable() {
		int ans = 0;
		for (String x : memory)
			if (x.equals(FREE))
				ans++;
		return ans;
	}
}
