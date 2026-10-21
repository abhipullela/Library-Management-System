package library.repositories;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import library.models.Member;

public class MemberRepository {
    private final Map<String, Member> members = new HashMap<>();

    public void add(Member member) {
        if (member == null) {
            throw new IllegalArgumentException("Member cannot be null");
        }

        if (members.containsKey(member.getMemberId())) {
            throw new IllegalArgumentException("Member ID already exists");
        }

        members.put(member.getMemberId(), member);
    }

    public Member findById(String memberId) {
        return members.get(memberId);
    }

    public boolean remove(String memberId) {
        return members.remove(memberId) != null;
    }

    public Collection<Member> findAll() {
        return members.values();
    }

    public int count() {
        return members.size();
    }
}
