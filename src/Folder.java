import java.util.ArrayList;
import java.util.Arrays;

public class Folder {

	String Name;
	String path;
	private int size;
	ArrayList<Folder> arrayFolder;
	ArrayList<File> arrayFile;

	Folder(String Name) {
		// by default the memory for folder is 5
		usedMemory(5);

		this.Name = Name;
		arrayFile = new ArrayList();
		arrayFolder = new ArrayList();

	}

	public ArrayList<Folder> getArrayFolder() {
		return arrayFolder;
	}

	public ArrayList<File> getArrayFile() {
		return arrayFile;
	}

	public String getName() {
		return Name;
	}

	public void Rename(String newName) {
		Name = newName;
	}

	public void openFolder() {
		// TODO GUI HANDELING
	}

	public void createFolderInside(String name) {

		if (!checkForNameUniqness(name)) {
			System.out.println("There is folder or file existing with the same name");
			return;
		}
		// System.out.println("here");
		usedMemory(this.size + 5);
		Folder newFolder = new Folder(name);
		// System.out.println(newFolder.Name);
		newFolder.path = this.path + "/" + name;
		// System.out.println(newFolder.path);
		arrayFolder.add(newFolder);
		for (Folder f : arrayFolder)
			System.out.println(f.Name + " " + f.path + " " + f.size);
		System.out.println("Created successfully");

	}

	public void createFileInside(String name) {

		if (!checkForNameUniqness(name)) {
			System.out.println("There is folder or file existing with the same name");
			return;
		}
		File newFile = new File(name);
		newFile.path = this.path + "/" + name;
		newFile.setPath(newFile.path);
		boolean can = Main.disk.assignFile(newFile);
		if (!can) {
			System.out.println("Not enough disk space!");
			return;
		}
		usedMemory(this.size + 1);
		arrayFile.add(newFile);
		System.out.println("Created successfully");
	}

	public void deleteFolderInside(String name) {
		boolean found = false;

		for (int i = 0; i < arrayFolder.size(); i++) {
			Folder curFolder = arrayFolder.get(i);
			if (curFolder.Name.equals(name)) {
				found = true;
				arrayFolder.remove(i);
				break;
			}

		}

		if (found)
			System.out.println("Deleted successfully");
		else
			System.out.println("Deletion failed");

	}

	public void deleteAll() {
		if(Terminal.parentDeletion) {
		  Main.recycleBin.arrayFolder.add(this);
		  Terminal.parentDeletion=false;
		}	
		for (Folder folder : arrayFolder) {
			folder.deleteAll();
		}
		arrayFolder.clear();
		for (File file : arrayFile) {
			Main.recycleBin.arrayFile.add(file);
			Main.disk.removeProcess(file);
			}
		arrayFile.clear();
	}

	public void deleteFileInside(String name) {
		boolean found = false;

		for (int i = 0; i < arrayFile.size(); i++) {
			File curFile = arrayFile.get(i);
			if (curFile.Name.equals(name)) {
				found = true;
				Main.recycleBin.arrayFile.add(curFile);
				Main.disk.removeProcess(curFile);
				arrayFile.remove(i);
				break;
			}
		}

		if (found)
			System.out.println("Deleted successfully");
		else
			System.out.println("Deletion failed");

	}

	public Object Find(String name) {

		// this method as if using control F to find anything

		for (int i = 0; i < arrayFile.size(); i++) {
			if (arrayFile.get(i).Name.equals(name)) {
				return arrayFile.get(i);
			}
		}

		for (int i = 0; i < arrayFolder.size(); i++) {
			if (arrayFolder.get(i).Name.equals(name)) {
				return arrayFolder.get(i);
			}
		}

		return null;

	}

	private void usedMemory(int size) {
		this.size = size;
	}

	public int getSize() {
		return this.size;
	}

	public void updateSize() {
		int totalSize = 5;

		for (int i = 0; i < arrayFile.size(); i++) {
			totalSize += arrayFile.get(i).getSize();
		}

		for (int i = 0; i < arrayFolder.size(); i++) {
			totalSize += arrayFolder.get(i).getSize();
		}

		this.usedMemory(totalSize);

	}

	public boolean checkForNameUniqness(String name) {

		for (int i = 0; i < arrayFolder.size(); i++) {
			if (arrayFolder.get(i).Name.equals(name))
				return false;
		}

		for (int i = 0; i < arrayFile.size(); i++) {
			if (arrayFile.get(i).Name.equals(name))
				return false;
		}

		return true;

	}

	public String listAllFilesAndFolders() {
		StringBuilder ans = new StringBuilder();
		for (File file : arrayFile)
			ans.append(file.getName() + "\n");
		for (Folder folder : arrayFolder)
			ans.append(folder.getName() + "\n");
		return ans.toString();
	}

	public static Folder getFolder(String path) {
		String[] x = path.split("/");
		// System.out.println("This is the x: " + Arrays.toString(x));
		if (!x[0].equals("desktop"))
			return null;
		Folder curr = Main.desktop;
		// System.out.println("in here");
		// System.out.println(path);
		for (int i = 1; i < x.length; i++) {
			boolean found = false;
			// System.out.println("in loop");
			for (Folder inside : curr.arrayFolder) {
				System.out.println(inside.Name);
				if (inside.getName().equals(x[i])) {
					curr = inside;
					found = true;
					break;
				}
			}
			if (!found)
				return null;

		}
		return curr;

	}
}
