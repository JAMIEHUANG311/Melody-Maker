// TODO: write this class

import java.util.*;
import java.io.*;
import melody.audio.*;

public class Melody {
	private String title;
	private String artist;
	private int numNote;
	private ArrayList<Note> noteArr;
	private int octaveAdjust = 0;
	private double tempoRatio = 1.0;

	public Melody(File file) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader(file));
		title = f.readLine();
		artist = f.readLine();
		StringTokenizer st = new StringTokenizer(f.readLine());
		numNote = Integer.parseInt(st.nextToken());
		noteArr = new ArrayList<Note>();
		for (int i=0; i < numNote; i++) {
			st = new StringTokenizer(f.readLine());
			double duration = Double.parseDouble(st.nextToken());
			Pitch pitch = Pitch.getValueOf(st.nextToken());
			int octave = Integer.parseInt(st.nextToken());
			Accidental accidental = Accidental.getValueOf(st.nextToken());
			boolean repeat = (st.nextToken().equals("true") ? true : false);
							  
			noteArr.add(new Note(duration, pitch, octave, accidental, repeat));
		}
		f.close();
	}
	
	public void changeTempo(double ratio) {
		tempoRatio = ratio;
	}
	
	public String getArtist() {
		return artist;
	}

	public String getTitle() {
		return title;
	}
	
	public double getTotalDuration() {
		double totalDuration = 0;
		for (Note n : noteArr) {
			totalDuration += n.getDuration();
		}
		return totalDuration;
	}
	
	public boolean octaveDown() {
		octaveAdjust--;
		return true;
	}
	
	public boolean octaveUp() {
		octaveAdjust++;
		System.out.println("octaveAdjust = " + octaveAdjust);
		return true;
	}
	
	public void play() {
		for (Note n : noteArr) {
			n.setOctave(n.getOctave() + octaveAdjust);
			n.setDuration(n.getDuration() / tempoRatio);
			n.play();
			n.setOctave(n.getOctave() - octaveAdjust);
			n.setDuration(n.getDuration() * tempoRatio);
		}
	}
	
	public void reverse() {
		Collections.reverse(noteArr);
	}
	
	public String toString() {
		// TODO: write this method
		return "";
	}
}

