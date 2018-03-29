package com.shop.mapper;

import com.shop.domain.Users;
import com.shop.domainDto.UsersDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsersMapperTestSuite {
    @Autowired
    private UsersMapper usersMapper;

    @Test
    public void testUsersDtoToUsers() {
        //Given
        UsersDto usersDto = new UsersDto(1L, "TestName", "TestPassword");
        //When
        Users users = usersMapper.usersDtoToUsers(usersDto);
        Long id = users.getId();
        String name = users.getName();
        String password = users.getPassword();
        //Then
        assertEquals(new Long(1), id);
        assertEquals("TestName", name);
        assertEquals("TestPassword", password);
    }

    @Test
    public void testUsersToUsersDto() {
        //Given
        Users users = new Users(1L, "TestName", "TestPassword");
        //When
        UsersDto usersDto = usersMapper.usersToUsersDto(users);
        Long id = usersDto.getId();
        String name = usersDto.getName();
        String password = usersDto.getPassword();
        //Then
        assertEquals(new Long(1), id);
        assertEquals("TestName", name);
        assertEquals("TestPassword", password);
    }
}
