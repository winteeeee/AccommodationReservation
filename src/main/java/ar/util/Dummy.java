package ar.util;

import ar.control.HostControl;
import ar.entity.Host;

public class Dummy {
    public static void dataInsert() {
        HostControl hostControl = new HostControl();
        Host dummyHost = new Host();
        dummyHost.setId("test");
        dummyHost.setName("test");
        dummyHost.setPassword("test");

        hostControl.save(dummyHost);
    }
}
