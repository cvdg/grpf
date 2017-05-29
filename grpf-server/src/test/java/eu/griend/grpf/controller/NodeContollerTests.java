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
package eu.griend.grpf.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import eu.griend.grpf.entity.NodeEntity;
import eu.griend.grpf.service.NodeService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class NodeContollerTests {
	@Autowired
	private NodeService nodeService = null;

	@Autowired
	private TestRestTemplate template = null;

	@Before
	public void setUp() {
		try {
			NodeEntity test = this.nodeService.readNode("test");
			UUID uuid = test.getUUID();
			this.nodeService.deleteNode(uuid);
		} catch (IllegalArgumentException e) {
			// ignore if the NodeEntity does not exist
		}
	}

	@Test
	public void testAllNodes() throws Exception {
		// -----------------------------------------------------
		NodeEntity[] array = template.getForObject("/nodes", NodeEntity[].class);
		List<NodeEntity> list = Arrays.asList(array);
		// -----------------------------------------------------

		assertNotNull(list);
		assertEquals(0, list.size());
		
		UUID uuid0 = UUID.randomUUID();
		NodeEntity node0 = new NodeEntity();
		node0.setUUID(uuid0);
		node0.setHostname("node0");
		
		UUID uuid1 = UUID.randomUUID();
		NodeEntity node1 = new NodeEntity();
		node1.setUUID(uuid1);
		node1.setHostname("node1");
		
		UUID uuid2 = UUID.randomUUID();
		NodeEntity node2 = new NodeEntity();
		node2.setUUID(uuid2);
		node2.setHostname("node2");
		
		this.nodeService.createNode(node0);
		this.nodeService.createNode(node1);
		this.nodeService.createNode(node2);

		// -----------------------------------------------------
		array = template.getForObject("/nodes", NodeEntity[].class);
		list = Arrays.asList(array);
		// -----------------------------------------------------
		
		assertNotNull(list);
		assertEquals(3, list.size());
		
		assertEquals(node0, list.get(0));
		assertEquals(node1, list.get(1));
		assertEquals(node2, list.get(2));
		
		this.nodeService.deleteNode(uuid0);
		this.nodeService.deleteNode(uuid1);
		this.nodeService.deleteNode(uuid2);
		
		// -----------------------------------------------------
		array = template.getForObject("/nodes", NodeEntity[].class);
		list = Arrays.asList(array);
		// -----------------------------------------------------
		
		assertNotNull(list);
		assertEquals(0, list.size());
	}
	
	@Test
	public void testCreateNode() throws Exception {
		UUID uuid = UUID.randomUUID();
		NodeEntity test = new NodeEntity();
		test.setUUID(uuid);
		test.setHostname("test");
		test.setDateStart(new Date());
		
		// -----------------------------------------------------
		this.template.postForObject("/nodes", test, NodeEntity.class);
		// -----------------------------------------------------
		
		assertEquals(test, this.nodeService.readNode(uuid));
		assertEquals(test, this.nodeService.readNode("test"));
	}
}
