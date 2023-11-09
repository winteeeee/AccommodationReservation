package ar.util;

import ar.control.AccommodationControl;
import ar.control.MemberControl;
import ar.entity.Member;
import ar.entity.RoleType;

import java.util.ArrayList;
import java.util.List;

public class Dummy {
    public static void dataInsert() {
        MemberControl hostControl = new MemberControl();
        AccommodationControl accommodationControl = new AccommodationControl();
        List<Member> hostList = new ArrayList<>();
        Member dummyHost1 = new Member();
        dummyHost1.setId("host1");
        dummyHost1.setName("host1");
        dummyHost1.setPassword("host1");
        dummyHost1.setRoleType(RoleType.HOST);
        Member dummyHost2 = new Member();
        dummyHost2.setId("host2");
        dummyHost2.setName("host2");
        dummyHost2.setPassword("host2");
        dummyHost1.setRoleType(RoleType.HOST);
        Member dummyHost3 = new Member();
        dummyHost3.setId("host3");
        dummyHost3.setName("host3");
        dummyHost3.setPassword("host3");
        dummyHost1.setRoleType(RoleType.HOST);
        Member dummyHost4 = new Member();
        dummyHost4.setId("host4");
        dummyHost4.setName("host4");
        dummyHost4.setPassword("host4");
        dummyHost1.setRoleType(RoleType.HOST);
        hostList.add(dummyHost1);
        hostList.add(dummyHost2);
        hostList.add(dummyHost3);
        hostList.add(dummyHost4);

        hostControl.save(hostList);
    }
}
