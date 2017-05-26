/*
 * grpf - Grapefruit
 *   
 * Copyright (C) 2017 C.A. van de Griend <c.vande.griend@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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

/**
 * 
 * @author cvdg
 *
 */
public class GrapefruitClient extends GrapefruitHandler implements Runnable {
	public static final String GRPF_CMD = "/var/opt/grpf/run/grpf.cmd";
	public static final String GRPF_PID = "/var/opt/grpf/run/grpf.pid";

	private File cmd = null;
	private File pid = null;
			
	//
	// Default constructor
	//
	public GrapefruitClient() {
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
			GrapefruitClient client = new GrapefruitClient();
			client.addListener(new GrapefruitListener());
			client.run();
		} catch (Exception e) {
			System.err.println(e.getLocalizedMessage());
			e.printStackTrace(System.err);
		}
	}
}
