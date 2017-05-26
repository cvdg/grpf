package eu.griend.grpf.event;

import java.io.Serializable;

import eu.griend.grpf.util.LogFormat;

public class GrapefruitListener implements Serializable {
	private static final long serialVersionUID = -5929596847879314179L;

	public GrapefruitListener() {
	}

	public void commandReloadEvent(GrapefruitEvent event) {
		System.out.println(LogFormat.format("reload"));
	}
	
	public void commandStartEvent(GrapefruitEvent event) {
		System.out.println(LogFormat.format("start"));
	}
	
	public void commandStopEvent(GrapefruitEvent event) {
		System.out.println(LogFormat.format("stop"));
	}
}
