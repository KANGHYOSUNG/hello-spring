package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

// 단축키 option + enter 로 인터페이스 메소드 구현
public class MemoryMemberRepository implements MemberRepository {
	//  실무에서는 공유 변수이므로 동시성 문제때문에 ConcurrnetHashMap 을 써야하지만 예제니까 고냥 해시맵으로.
	private static Map<Long, Member> store = new HashMap<>();
	private static long sequence = 0L; // 요것도 위와 같은 사유로 실무에서는 AtomicLong 으로 사용함.

	@Override
	public Member save(Member member) {
		member.setId(++sequence);
		store.put(member.getId(), member);
		return member;
	}

	@Override
	public Optional<Member> findById(Long id) {
		return Optional.ofNullable(store.get(id));
	}

	@Override
	public Optional<Member> findByName(String name) {
		return store.values().stream()
				.filter(member -> member.getName().equals(name))
				.findAny();
	}

	@Override
	public List<Member> findAll() {
		return new ArrayList<>(store.values());
	}
}
