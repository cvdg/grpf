/*
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
package eu.griend.grpf.entity;

import java.util.Date;
import java.util.UUID;

public class NodeEntity {
	private UUID uuid = null;

	private String hostname = null;
	
	private Date dateStart = null;

	private Date dateStop = null;

	public NodeEntity() {
		super();
	}

	public final UUID getUUID() {
		return this.uuid;
	}

	public final void setUUID(UUID uuid) {
		this.uuid = uuid;
	}

	public String getHostname() {
		return this.hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public Date getDateStart() {
		return this.dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public Date getDateStop() {
		return this.dateStop;
	}

	public void setDateStop(Date dateStop) {
		this.dateStop = dateStop;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
		result = prime * result + ((hostname == null) ? 0 : hostname.hashCode());
		result = prime * result + ((dateStart == null) ? 0 : dateStart.hashCode());
		result = prime * result + ((dateStop == null) ? 0 : dateStop.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NodeEntity other = (NodeEntity) obj;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		if (hostname == null) {
			if (other.hostname != null)
				return false;
		} else if (!hostname.equals(other.hostname))
			return false;
		if (dateStart == null) {
			if (other.dateStart != null)
				return false;
		} else if (!dateStart.equals(other.dateStart))
			return false;
		if (dateStop == null) {
			if (other.dateStop != null)
				return false;
		} else if (!dateStop.equals(other.dateStop))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder tmp = new StringBuilder();
		tmp.append("Node [uuid=");
		tmp.append(this.uuid);
		tmp.append(", hostname=");
		tmp.append(this.hostname);
		tmp.append(", dateStart=");
		tmp.append(this.dateStart);
		tmp.append(", dateStop=");
		tmp.append(this.dateStop);
		tmp.append("]");
		
		return tmp.toString();
	}
}
