package eu.griend.grpf.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import eu.griend.grpf.event.GrapefruitEvent;
import eu.griend.grpf.event.GrapefruitHandler;
import eu.griend.grpf.event.GrapefruitListener;
import eu.griend.grpf.util.LogFormat;

public class Application extends GrapefruitHandler implements Runnable {
	public static final String GRPF_CMD = "/var/opt/grpf/run/grpf.cmd";
	public static final String GRPF_PID = "/var/opt/grpf/run/grpf.pid";

	private File cmd = null;
	private File pid = null;
			
	//
	// Default constructor
	//
	public Application() {
		super();
		
		this.cmd = new File(GRPF_CMD);
		this.pid = new File(GRPF_PID);
	}

	
	//
	// Interface: java.lang.Runnable
	//
	public void run() {
		boolean stop = false;
		BufferedWriter writer = null;
		BufferedReader reader = null;
		String command = "";

		try {
			if (this.pid.exists()) {
				System.out.println(LogFormat.format("Error: lock " + GRPF_PID + " exist"));
				System.exit(1);
			}
			
			writer = new BufferedWriter(new FileWriter(this.pid));
			writer.write(Long.valueOf(LogFormat.getPID()).toString());
			writer.newLine();
			writer.flush();
			
			fireCommandStartEvent(new GrapefruitEvent(this, "start"));

			reader = new BufferedReader(new FileReader(this.cmd));

			while (!stop) {
				command = reader.readLine();

				if (command != null) {
					if (command.equals("stop")) {
						stop = true;
					} else {
						System.out.println(LogFormat.format(command));
					}
				}
			}

			fireCommandStopEvent(new GrapefruitEvent(this, "stop"));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
			
			if (writer != null) {
				try {
					writer.close();
					
					this.pid.delete();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

	
	//
	// Main
	//
	public static void main(String[] args) {
		try {
			Application application = new Application();
			application.addListener(new GrapefruitListener());
			application.run();
		} catch (Exception e) {
			System.err.println(e.getLocalizedMessage());
			e.printStackTrace(System.err);
		}
	}
}
