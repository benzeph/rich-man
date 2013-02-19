package org.thoughtworks.zeph.rich.map;

import org.thoughtworks.zeph.rich.role.Role;

import java.util.HashSet;
import java.util.Set;

public class Prison extends Map{
	private Set<Role> roles;

	public Prison(int mapId){
		super(mapId);
		roles = new HashSet<Role>();
	}
	public void addRole(Role role) {
		roles.add(role);
	}

	public Set<Role> getRoles() {
		return roles;
	}
}
