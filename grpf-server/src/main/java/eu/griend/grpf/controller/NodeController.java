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
package eu.griend.grpf.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import eu.griend.grpf.entity.NodeEntity;
import eu.griend.grpf.service.NodeService;

@RestController
public class NodeController {
	@Autowired
	private NodeService nodeService;

	@RequestMapping(value = "/nodes", method = RequestMethod.GET)
	public List<NodeEntity> allNodes() {
		return this.nodeService.getAllNodes();
	}

	@RequestMapping(value = "/nodes", method = RequestMethod.POST)
	public void createNode(@RequestBody NodeEntity node) {
		this.nodeService.createNode(node);
	}

	@RequestMapping(value = "/nodes/{id}", method = RequestMethod.GET)
	public NodeEntity readNode(@PathVariable String id) {
		UUID uuid = UUID.fromString(id);

		return this.nodeService.readNode(uuid);
	}

	@RequestMapping(value = "/nodes/{id}", method = RequestMethod.PUT)
	public void updateNode(@PathVariable String id, @RequestBody NodeEntity node) {
		UUID uuid = UUID.fromString(id);

		this.nodeService.updateNode(uuid, node);
	}

	@RequestMapping(value = "/nodes/{id}", method = RequestMethod.DELETE)
	public void deleteNode(@PathVariable String id) {
		UUID uuid = UUID.fromString(id);
		
		this.nodeService.deleteNode(uuid);
	}
}
