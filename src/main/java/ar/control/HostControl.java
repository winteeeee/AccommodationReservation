package ar.control;

import ar.entity.Host;

public class HostControl extends Control<Host, String>{
    public Host login(String id, String password) {
        Host host = findById(Host.class, id);
        return host == null || host.getPassword().equals(password) ? host : null;
    }
}
