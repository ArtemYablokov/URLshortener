package com.yabloko.moduleTest;

import com.yabloko.repositories.UrlRepository;
import com.yabloko.service.UrlService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest

@TestPropertySource("/application-test.properties")
@Sql(value = {"/create-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/delete-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class MockTest {
    @MockBean
    UrlRepository urlRepository;
    @Autowired
    UrlService urlService;

    @Test
    public void testRepoCallsTimes() throws IOException {
        String suffix = "Jik1UVmm";
        String userUrl = urlService.findBySuffix(suffix);
        int n = 0;
        Mockito.verify(urlRepository, Mockito.times(1))
                .findBySuffixHashId(urlService.generateHashCode(suffix));
    }
}
