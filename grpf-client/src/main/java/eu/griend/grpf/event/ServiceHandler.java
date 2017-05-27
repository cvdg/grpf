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
package eu.griend.grpf.event;

import java.util.LinkedList;
import java.util.List;

import eu.griend.grpf.event.ServiceEvent;
import eu.griend.grpf.event.ServiceListener;

public class ServiceHandler {
	private List<ServiceListener> listeners = null;

	public ServiceHandler() {
		super();

		this.listeners = new LinkedList<ServiceListener>();
	}

	public void addListener(ServiceListener listener) {
		this.listeners.add(listener);
	}

	public void fireServiceReloaded(String line) {
		ServiceEvent evemt = new ServiceEvent(this, line);

		for (ServiceListener listener : this.listeners) {
			listener.serviceReloaded(evemt);
		}
	}

	public void fireServiceStarted(String line) {
		ServiceEvent event = new ServiceEvent(this, line);

		for (ServiceListener listener : this.listeners) {
			listener.serviceStarted(event);
		}
	}

	public void fireServiceStoped(String line) {
		ServiceEvent event = new ServiceEvent(this, line);

		for (ServiceListener listener : this.listeners) {
			listener.serviceStoped(event);
		}
	}
}
