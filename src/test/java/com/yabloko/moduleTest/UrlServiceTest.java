package com.yabloko.moduleTest;


import com.yabloko.repositories.UrlRepository;
import com.yabloko.service.UrlService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.net.URL;


@RunWith(SpringRunner.class)
@SpringBootTest

@TestPropertySource("/application-test.properties")
@Sql(value = {"/create-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/delete-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class UrlServiceTest {
    @Value("${host.prefix}")
    private String hostPrefix;
    @Autowired
    UrlService urlService;
    @Autowired
    UrlRepository urlRepository;

    @Test
    public void saveAndReturnEquals() throws IOException {
        String userUrl = "https://www.youtube.com/watch?v=t_HSdkEVwh4&list=PLRq6lyEop3yejXNdtVxdh4dbeKRDdf3ZV&index=1&t=325s";
        String shortUrl = urlService.saveAndReturnShort(userUrl);
        URL url = new URL(shortUrl);
        String path = url.getPath();
        String suffixOfShortUrl = path.substring(1,path.length());
        String newUserUrl = urlService.findBySuffix(suffixOfShortUrl);
        Assert.assertEquals(userUrl ,newUserUrl);
    }

    @Test
    public void checkIfExist() throws IOException {
        String userUrl = "https://github.com/avito-tech/auto-backend-trainee-assignment";
        String shortUrl = urlService.saveAndReturnShort(userUrl);
        Assert.assertEquals("URL already exists!", shortUrl);
    }

    @Test
    public void testFind() throws IOException {
        String suffix = "Jik1UVmm";
        String userUrl = urlService.findBySuffix(suffix);
        Assert.assertEquals(userUrl, "https://github.com/avito-tech/auto-backend-trainee-assignment");
    }
}
