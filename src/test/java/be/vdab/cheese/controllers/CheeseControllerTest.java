package be.vdab.cheese.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Sql({"/countries.sql", "/colours.sql", "/cheese.sql", "/flavours.sql", "/cheeseflavours.sql",
        "/animals.sql", "/cheeseanimals.sql", "/webpages.sql"})
@AutoConfigureMockMvc
class CheeseControllerTest extends AbstractTransactionalJUnit4SpringContextTests {
    private final static String CHEESE = "cheese";

    private final static String WEBPAGES = "webpages";
    private final static String CHEESEANIMALS = "cheeseanimals";
    private final static String CHEESEFLAVOURS = "cheeseflavours";

    private final MockMvc mockMvc;

    CheeseControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }


    private long idVanTestCheese() {
        return jdbcTemplate.queryForObject("select id from cheese where name = 'testCheese'", long.class);
    }

    @Test
    void findByNameContains() throws Exception {
        mockMvc.perform(get("/cheese?nameContains=test"))
                .andExpectAll(status().isOk(), jsonPath("length()")
                        .value(countRowsInTableWhere(CHEESE, "name like '%test%'")));
    }

    @Test
    void findByIdAnimals() throws Exception {
        var id = idVanTestCheese();
        mockMvc.perform(get("/cheese/" + id + "/animals"))
                .andExpectAll(status().isOk(),
                        jsonPath("length()")
                                .value(countRowsInTableWhere(CHEESEANIMALS, "cheeseId = " + id)));
    }

    @Test
    void findByIdMetFlavours() throws Exception {
        var id = idVanTestCheese();
        mockMvc.perform(get("/cheese/" + id + "/withFlavours")).andExpectAll(
                status().isOk(),
                jsonPath("flavours.length()")
                        .value(countRowsInTableWhere(CHEESEFLAVOURS, "cheeseId = " + id)));
    }

    @Test
    void findWebpagesById() throws Exception {
        var id = idVanTestCheese();
        mockMvc.perform(get("/cheese/" + id + "/webpages"))
                .andExpectAll(status().isOk(),
                        jsonPath("length()").value(countRowsInTableWhere(WEBPAGES, "cheeseId = " + id)));

    }

    @Test
    void likeCheese() throws Exception {
        var id = idVanTestCheese();
        mockMvc.perform(post("/cheese/" + id + "/likes")).andExpect(status().isOk());
        assertThat(countRowsInTableWhere(CHEESE, "name = 'testCheese' and likes = 2")).isOne();
    }
}