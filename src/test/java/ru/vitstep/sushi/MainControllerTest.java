package ru.vitstep.sushi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


import ru.vitstep.sushi.config.SecurityConfig;
import ru.vitstep.sushi.controller.MainController;
import ru.vitstep.sushi.service.ProductService;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Import(SecurityConfig.class)
@WebMvcTest(MainController.class)
public class MainControllerTest {

    @Autowired
    private MockMvc mockMvc;



    @Test
    void shouldAllowAccessForAnonymousUser() throws Exception {
       mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("not_auth"))
                .andExpect(model().attributeExists("randList"));
    }


}
