package com.mymicro.persist;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.amazonaws.services.dynamodbv2.document.Index;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.mymicro.exceptions.UserExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository{

    @Autowired
    private DynamoDBMapper dynamoDbMapper;

   public User save(User user) {
       dynamoDbMapper.save(user);
        return user;
    }

    public User findUserById(String id) {
        return dynamoDbMapper.load(User.class, id);
    }

    public User update(String id, User user){
       dynamoDbMapper.save(user,
               new DynamoDBSaveExpression()
               .withExpectedEntry("user_id",
                       new ExpectedAttributeValue(
                               new AttributeValue().withS(id)
                       )));
       return user;
    }

    public void delete(User user){
       dynamoDbMapper.delete(user);
    }


    public List<User> findAllUsersByEmail(String email){
       DynamoDBQueryExpression<User> queryExpression = new DynamoDBQueryExpression<>();
       User user = new User();
       user.setEmail(email);
       queryExpression
               .withConsistentRead(false)
               .withIndexName("email-index")
               .setHashKeyValues(user);
       List <User> listOfUsers =  dynamoDbMapper.query(User.class, queryExpression);
       return listOfUsers;
    }

}
