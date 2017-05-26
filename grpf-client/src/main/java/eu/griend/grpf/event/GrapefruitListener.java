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

import java.io.Serializable;

import eu.griend.grpf.util.LogUtil;

public class GrapefruitListener implements Serializable {
	private static final long serialVersionUID = -5929596847879314179L;

	public GrapefruitListener() {
	}

	public void commandReloadEvent(GrapefruitEvent event) {
		System.out.println(LogUtil.format("reload"));
	}
	
	public void commandStartEvent(GrapefruitEvent event) {
		System.out.println(LogUtil.format("start"));
	}
	
	public void commandStopEvent(GrapefruitEvent event) {
		System.out.println(LogUtil.format("stop"));
	}
}
