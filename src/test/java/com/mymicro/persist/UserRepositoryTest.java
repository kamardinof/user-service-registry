package com.mymicro.persist;

import org.bouncycastle.asn1.gnu.GNUObjectIdentifiers;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {

    @Mock
    private UserRepository repository;

    @Test
    void save() {
        UUID guid = UUID.randomUUID();
        User user = new User(guid.toString(), "testemail@email.com", new Date(), new Date(), "Test", "Test", new Date(), Gender.MALE);
       this.repository.save(user);
        assertTrue(repository.findUserById(guid.toString()) != null );

    }
}