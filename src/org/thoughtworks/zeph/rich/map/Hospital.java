package org.thoughtworks.zeph.rich.map;

import org.thoughtworks.zeph.rich.role.Role;

import java.util.HashSet;
import java.util.Set;

public class Hospital extends Map{
	private Set<Role> roles;

	public Hospital(int mapId){
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
