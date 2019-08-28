import java.util.Arrays;

//package application;

public class Terminal {
	private Folder currentDirectory;
	private Music music;
	public static boolean parentDeletion;

	public Terminal() {
		// not sure how to know the name of the user
		// System.out.println("Welcome User");
		currentDirectory = Main.desktop;
	}

	public Folder getCurrentDirectory() {
		return currentDirectory;
	}

	public boolean changeDirectory(Folder folder) { // cd
		// System.out.println(folder.toString());
		if (folder == null)
			return false;
		// System.out.println("This is changeDirectory");
		// System.out.println(folder.Name + " " + folder.path);
		currentDirectory = folder;
		// System.out.println(currentDirectory.Name + " " + currentDirectory.path);
		// System.out.println("====================");
		return true;
	}

	public void printCurrentDirectory() { // pwd
		Main.cmdTextArea.appendText(currentDirectory.getName() + "\n");
		System.out.println(currentDirectory.getName());
	}

	public void listAllFiles() { // ls
		Main.cmdTextArea.appendText(currentDirectory.listAllFilesAndFolders());
		System.out.println(currentDirectory.listAllFilesAndFolders());
	}

	public boolean createNewFolder(String folderName) {
		if (!currentDirectory.checkForNameUniqness(folderName))
			return false;
		currentDirectory.createFolderInside(folderName);
		return true;
	}

	public boolean createNewFile(String fileName) {
		if (!currentDirectory.checkForNameUniqness(fileName))
			return false;
		currentDirectory.createFileInside(fileName);
		return true;
	}

	public boolean editFile(String fileName, String text) {
		for (int i = 0; i < currentDirectory.arrayFile.size(); i++)
			if (currentDirectory.arrayFile.get(i).Name.equals(fileName)) {
				currentDirectory.arrayFile.get(i).setText(text);
				return true;
			}
		return false;
	}

	public String readFile(String fileName) {
		for (int i = 0; i < currentDirectory.arrayFile.size(); i++)
			if (currentDirectory.arrayFile.get(i).Name.equals(fileName))
				return currentDirectory.arrayFile.get(i).getText();
		return "File Not Found!";
	}

	public boolean executeCommand(String cmd) {
		if (cmd.startsWith("ls")) {
			listAllFiles();
			return true;
		}
		if (cmd.startsWith("cd")) {
			Folder to = Folder.getFolder(cmd.split(" ")[1]);
			changeDirectory(to);
			return to != null;
		}
		if (cmd.startsWith("pwd")) {
			printCurrentDirectory();
			return true;
		}
		if (cmd.startsWith("create")) // create folderName
		{
			createNewFolder(cmd.split(" ")[1]);
			return true;
		}
		if (cmd.startsWith("play")) {
			music = new Music(cmd.split(" ")[1]);
			music.play();
			return true;
		}
		if (cmd.startsWith("stop")) {
			music.stop();
			return true;
		}
		if (cmd.startsWith("touch")) { // create fileName
			createNewFile(cmd.split(" ")[1]);
			return true;
		}
		if (cmd.startsWith("deleteFolder")) {
			String path = cmd.split(" ")[1];
			// Main.cmdTextArea.appendText(path + "\n");
			// System.out.println(path);
			if (path.equals("desktop")) {
				Main.cmdTextArea.appendText("You cannot delete desktop" + "\n");
				System.out.println("You cannot delete desktop");
				return false;
			} else {
				Folder to = Folder.getFolder(cmd.split(" ")[1]);
				String folderName = cmd.split(" ")[1];
				String pathNames[] = folderName.split("/");
				String parentPath = "";
				for (int i = 0; i < pathNames.length - 1; i++)
					if (i != pathNames.length - 1)
						parentPath += pathNames[i] + "/";
				parentPath = parentPath.substring(0, parentPath.length() - 1);
				parentDeletion = true;
				to.deleteAll();
				System.out.println("parentPath: " + parentPath);
				Folder parentFolder = Folder.getFolder(parentPath);
				parentFolder.deleteFolderInside(pathNames[pathNames.length - 1]);
				return true;
			}
		}
		if (cmd.startsWith("deleteFile")) {
			String path = cmd.split(" ")[1];
			int last = path.lastIndexOf("/");
			String parentPath = path.substring(0, last);
			Folder parent = Folder.getFolder(parentPath);
			parent.deleteFileInside(path.substring(last + 1));
			return true;

		}
		if (cmd.startsWith("openFile")) {
			String fileName = cmd.split(" ")[1];
			fileName = fileName.split("/")[fileName.split("/").length - 1];
			// System.out.println(fileName);
			Main.cmdTextArea.appendText(readFile(fileName) + "\n");
			System.out.println(readFile(fileName));
			return true;
		}
		if (cmd.startsWith("editFile")) {
			String fileName = cmd.split(" ")[1];
			int prefix = 2 + cmd.split(" ")[0].length() + cmd.split(" ")[1].length();
			String text = cmd.substring(prefix);
			fileName = fileName.split("/")[fileName.split("/").length - 1];
			editFile(fileName, text);
			Main.cmdTextArea.appendText(readFile(fileName) + "\n");
			System.out.println(fileName);
			System.out.println(readFile(fileName));
			return true;
		}
		return false;
	}

}
