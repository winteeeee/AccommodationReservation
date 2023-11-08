package ar.util;

import ar.control.AccommodationControl;
import ar.control.HostControl;
import ar.entity.Accommodation;
import ar.entity.Host;

import java.util.ArrayList;
import java.util.List;

public class Dummy {
    public static void dataInsert() {
        HostControl hostControl = new HostControl();
        AccommodationControl accommodationControl = new AccommodationControl();
        List<Host> hostList = new ArrayList<>();
        Host dummyHost1 = new Host();
        dummyHost1.setId("host1");
        dummyHost1.setName("host1");
        dummyHost1.setPassword("host1");
        Host dummyHost2 = new Host();
        dummyHost2.setId("host2");
        dummyHost2.setName("host2");
        dummyHost2.setPassword("host2");
        Host dummyHost3 = new Host();
        dummyHost3.setId("host3");
        dummyHost3.setName("host3");
        dummyHost3.setPassword("host3");
        Host dummyHost4 = new Host();
        dummyHost4.setId("host4");
        dummyHost4.setName("host4");
        dummyHost4.setPassword("host4");
        hostList.add(dummyHost1);
        hostList.add(dummyHost2);
        hostList.add(dummyHost3);
        hostList.add(dummyHost4);
        hostControl.save(hostList);
    }
}
