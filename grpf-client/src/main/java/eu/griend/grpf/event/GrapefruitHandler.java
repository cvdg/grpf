package eu.griend.grpf.event;

import java.util.LinkedList;
import java.util.List;

import eu.griend.grpf.event.GrapefruitEvent;
import eu.griend.grpf.event.GrapefruitListener;

public class GrapefruitHandler {
	private List<GrapefruitListener> listeners = null;

	public GrapefruitHandler() {
		super();

		this.listeners = new LinkedList<GrapefruitListener>();
	}

	public void addListener(GrapefruitListener listener) {
		this.listeners.add(listener);
	}

	public void fireCommandStartEvent(GrapefruitEvent event) {
		for (GrapefruitListener listener : this.listeners) {
			listener.commandStartEvent(event);
		}
	}

	public void fireCommandStopEvent(GrapefruitEvent event) {
		for (GrapefruitListener listener : this.listeners) {
			listener.commandStopEvent(event);
		}
	}
}
