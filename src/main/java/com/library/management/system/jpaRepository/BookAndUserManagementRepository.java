package com.library.management.system.jpaRepository;

import com.library.management.system.jpaEntity.BookAndUserRelation;
import com.library.management.system.jpaEntity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookAndUserManagementRepository extends CrudRepository<BookAndUserRelation, String> {

    public BookAndUserRelation save(BookAndUserRelation bookAndUserRelation);

    public BookAndUserRelation getBookAndUserRelationByBookId(String bookId);

    public void deleteBookAndUserRelationByBookId(String bookId);

    public List<BookAndUserRelation> getBookAndUserRelationByUserId(String userId);

}
