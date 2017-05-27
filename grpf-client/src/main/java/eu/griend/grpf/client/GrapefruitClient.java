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
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import eu.griend.grpf.event.ServiceEvent;
import eu.griend.grpf.event.ServiceHandler;
import eu.griend.grpf.event.ServiceListener;
import eu.griend.grpf.util.LogUtil;
import eu.griend.grpf.util.ProcessUtil;

/**
 * 
 * @author cvdg
 *
 */
public class GrapefruitClient extends ServiceHandler implements Runnable {
	public static final String NAME_LOG = "/var/opt/grpf/log/grpf-{0,date,YYYYMMdd}.log";
	public static final String NAME_CTL = "/var/opt/grpf/run/grpf.ctl";
	public static final String NAME_PID = "/var/opt/grpf/run/grpf.pid";

	private File fileCtl = null;
	private File filePid = null;

	//
	// Default constructor
	//
	public GrapefruitClient() {
		super();

		this.fileCtl = new File(NAME_CTL);
		this.filePid = new File(NAME_PID);
	}

	//
	// Interface: java.lang.Runnable
	//
	public void run() {
		boolean stop = false;
		BufferedReader ctl = null;
		String line = null;
		String command = null;

		try {
			ProcessUtil.lock(this.filePid);

			fireServiceStarted("start");

			//
			// Read commands from named pipe.
			//
			ctl = new BufferedReader(new FileReader(this.fileCtl));

			while (!stop) {
				line = ctl.readLine();

				if (line != null) {
					command = line.trim().split(" ")[0];

					if (command.equals("stop")) {
						fireServiceStoped(line);
						stop = true;
					} else if (command.equals("reload")) {
						fireServiceReloaded(line);
					} else {
						LogUtil.log("unknown command: " + line);
					}
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (ctl != null) {
				try {
					ctl.close();
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
			LogUtil.setName(NAME_LOG);

			GrapefruitClient client = new GrapefruitClient();
			client.addListener(new ServiceListener() {
				public void serviceStoped(ServiceEvent event) {
					LogUtil.log(event.getLine());
				}

				public void serviceStarted(ServiceEvent event) {
					LogUtil.log(event.getLine());
				}

				public void serviceReloaded(ServiceEvent event) {
					LogUtil.log(event.getLine());
				}
			});
			client.run();
		} catch (Exception e) {
			System.err.println(e.getLocalizedMessage());
			e.printStackTrace(System.err);
		}
	}
}
