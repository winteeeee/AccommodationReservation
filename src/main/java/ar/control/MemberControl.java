package ar.control;

import ar.entity.Member;

public class MemberControl extends Control<Member, String> {
    public Member login(String id, String password) {
        Member member = findById(Member.class, id);
        return member == null || member.getPassword().equals(password) ? member : null;
    }
}
