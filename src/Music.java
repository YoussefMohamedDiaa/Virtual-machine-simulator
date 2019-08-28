import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Music {
	// AudioInputStream ais = AudioSystem.getAudioInputStream(in);
	private String path;
//	private long clipTime;
	private Clip clip;
	private File f;

	public Music(String path) {
		this.path = path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getPath() {
		return this.path;
	}

	public void play() {
		try {
			f = new File(path);
			System.out.println(path);
			// InputStream in = new FileInputStream(path);
			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(f));
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (Exception exception) {
			System.out.println("Failed To Play The WAV File!");
		}
	}

	public void stop() {
//		clipTime = clip.getLongFramePosition();
//		Main.memory.removeProcess(process);
		clip.close();
	}

	// public void resume() {
	// System.out.println(clipTime);
	// clip.setMicrosecondPosition(clipTime);
	// clip.start();
	// }
}
