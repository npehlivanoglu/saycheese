package be.vdab.cheese.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Sql({"/countries.sql", "/colours.sql", "/cheese.sql", "/flavours.sql", "/cheeseflavours.sql",
        "/animals.sql", "/cheeseanimals.sql", "/webpages.sql"})
@AutoConfigureMockMvc
class CountryControllerTest extends AbstractTransactionalJUnit4SpringContextTests {
    private final MockMvc mockMvc;
    private final static String CHEESE = "cheese";


    CountryControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    private long idVanTestCheese() {
        return jdbcTemplate.queryForObject("select id from cheese where name = 'testCheese'", long.class);
    }

    private long idVanTestCountry() {
        return jdbcTemplate.queryForObject("select id from countries where name = 'testCountry'", long.class);
    }


    @Test
    void findByIdMetCheeses() throws Exception {
        mockMvc.perform(get("/countries/" + idVanTestCountry() + "/withcheeses")).andExpectAll(status().isOk(),
                jsonPath("length()").value(countRowsInTableWhere(CHEESE, "countryId = " + idVanTestCountry())));
    }
}