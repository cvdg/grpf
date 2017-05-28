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
package eu.griend.grpf.service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import eu.griend.grpf.entity.NodeEntity;

@Service
public class NodeService {
	public List<NodeEntity> nodes = null;

	public NodeService() {
		this.nodes = new LinkedList<NodeEntity>();
	}

	public List<NodeEntity> getAllNodes() {
		return this.nodes;
	}

	public void createNode(NodeEntity node) {
		if (this.nodes.contains(node)) {
			throw new IllegalArgumentException(node.toString());
		}

		this.nodes.add(node);
	}

	public NodeEntity readNode(UUID uuid) {
		NodeEntity node = null;
		boolean found = false;

		for (int n = 0; n < this.nodes.size(); n++) {
			if (uuid.equals(this.nodes.get(n).getUUID())) {
				node = nodes.get(n);
				found = true;
				break;
			}
		}

		if (!found) {
			throw new IllegalArgumentException(uuid.toString());
		}

		return node;
	}

	public NodeEntity readNode(String hostname) {
		NodeEntity node = null;
		boolean found = false;

		for (int n = 0; n < this.nodes.size(); n++) {
			if (hostname.equals(this.nodes.get(n).getHostname())) {
				node = nodes.get(n);
				found = true;
				break;
			}
		}

		if (!found) {
			throw new IllegalArgumentException(hostname);
		}

		return node;
	}

	public void updateNode(UUID uuid, NodeEntity node) {
		boolean found = false;

		for (int n = 0; n < this.nodes.size(); n++) {
			if (uuid.equals(this.nodes.get(n).getUUID())) {
				this.nodes.set(n, node);
				found = true;
				break;
			}
		}

		if (!found) {
			throw new IllegalArgumentException(uuid.toString());
		}
	}

	public void deleteNode(UUID uuid) {
		boolean found = false;

		for (int n = 0; n < this.nodes.size(); n++) {
			if (uuid.equals(this.nodes.get(n).getUUID())) {
				this.nodes.remove(n);
				found = true;
				break;
			}
		}

		if (!found) {
			throw new IllegalArgumentException(uuid.toString());
		}
	}
}
