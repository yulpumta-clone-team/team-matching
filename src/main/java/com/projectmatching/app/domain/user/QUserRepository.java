package com.projectmatching.app.domain.user;

import com.projectmatching.app.domain.user.entity.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.projectmatching.app.domain.user.QUser.user;

@Repository
@RequiredArgsConstructor
public class QUserRepository {

    private final JPAQueryFactory jpaQueryFactory;
    private final UserRepository userRepository;


    /**
     * 유저 카드 (리스트) 표시
     */
    public List<User> find(Pageable pageable){
        return jpaQueryFactory.selectFrom(user)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

    }


    /**
     * 유저 상세 표시
     */
    public Optional<User> find(Long id){
        return Optional.ofNullable(jpaQueryFactory
                .selectFrom(user)
                .where(user.id.eq(id))
                .fetchOne()
        );

    }



}
