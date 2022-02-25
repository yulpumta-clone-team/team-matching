package com.projectmatching.app.domain.comment.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserComment is a Querydsl query type for UserComment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserComment extends EntityPathBase<UserComment> {

    private static final long serialVersionUID = 1231827632L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserComment userComment = new QUserComment("userComment");

    public final com.projectmatching.app.domain.QBaseTimeEntity _super = new com.projectmatching.app.domain.QBaseTimeEntity(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> parentId = createNumber("parentId", Long.class);

    public final BooleanPath secret = createBoolean("secret");

    //inherited
    public final StringPath status = _super.status;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final com.projectmatching.app.domain.user.entity.QUser user;

    public final SetPath<com.projectmatching.app.domain.liking.entity.UserCommentLiking, com.projectmatching.app.domain.liking.entity.QUserCommentLiking> userCommentLikings = this.<com.projectmatching.app.domain.liking.entity.UserCommentLiking, com.projectmatching.app.domain.liking.entity.QUserCommentLiking>createSet("userCommentLikings", com.projectmatching.app.domain.liking.entity.UserCommentLiking.class, com.projectmatching.app.domain.liking.entity.QUserCommentLiking.class, PathInits.DIRECT2);

    public QUserComment(String variable) {
        this(UserComment.class, forVariable(variable), INITS);
    }

    public QUserComment(Path<? extends UserComment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserComment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserComment(PathMetadata metadata, PathInits inits) {
        this(UserComment.class, metadata, inits);
    }

    public QUserComment(Class<? extends UserComment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.projectmatching.app.domain.user.entity.QUser(forProperty("user")) : null;
    }

}

