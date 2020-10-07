package com.yabloko;

import com.yabloko.controllers.MainController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// import static org.hamcrest.core.StringContains.containsString;

@AutoConfigureMockMvc
// создает структуру классов, которфе авто подменяют слой MVC -> все будет происходить в ФЕЙК-окружении
// в результате не нужно создавать REST-темплейт
@RunWith(SpringRunner.class) // указываем окружение которое стартует наши тесты
@SpringBootTest
public class MainControllerTest {
    @Autowired
    private MainController controller;
    @Autowired
    private MockMvc mockMvc;

    //простая проверка что Контроллер подтянут в КОНТЕКСТ
    @Test
    public void contextTest() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    public void getMainPage() throws Exception {
        // Запрос через подмененный MVC-слой
        this.mockMvc.perform(get("/main"))
                .andDo(print())
                .andExpect(status().isOk()) // обертка над методом assertThat
                .andExpect(content().string(containsString("Insert your url")))
                .andExpect(content().string(containsString("UrlShort")));
    }

    @Test
    public void emptyUrl() throws Exception {
        this.mockMvc.perform(post("/main").param("userUrl", ""))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("URL must not be empty")));
    }

    @Test
    public void withSpaceUrl() throws Exception {
        this.mockMvc.perform(post("/main").param("userUrl", "https://ya ndex.ru"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("URL must not contain spaces")));
    }

    @Test
    public void wrongRegionUrl() throws Exception {
        this.mockMvc.perform(post("/main").param("userUrl", "https://yandex.r"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("URL is incorrect regex")));
    }
}