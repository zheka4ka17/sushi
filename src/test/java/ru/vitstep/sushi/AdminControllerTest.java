package ru.vitstep.sushi;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import ru.vitstep.sushi.config.SecurityConfig;

import java.security.Security;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Import(SecurityConfig.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldRedirectAnonymousUserToLogin() throws Exception {
        this.mockMvc.perform(get("/admin"))
                .andExpect(status().is3xxRedirection());
    }
}
//@Test
//     @WithMockUser("mike")
//    void shouldAllowAccessToPageAdminForAnonymousUser() throws Exception {
//        this.mockMvc.perform(get("/admin"))
//               // .with(SecurityMockMvcRequestPostProcessors.user("mike")))
//                .andExpect(status().isOk())
//                .andExpect(view().name("admin_users"))
//                .andExpect(model().attributeExists("users"));
//               // .andExpect(model().attributeExists("user"));
//
//    }
//}
