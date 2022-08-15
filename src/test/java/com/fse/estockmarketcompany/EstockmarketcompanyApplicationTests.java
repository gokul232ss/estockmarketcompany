package com.fse.estockmarketcompany;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fse.estockmarketcompany.model.CompanyAll;
import com.fse.estockmarketcompany.model.Stock;
import com.fse.estockmarketcompany.model.request.CompanyRequest;
import com.fse.estockmarketcompany.repository.CompanyAllRepository;
import com.fse.estockmarketcompany.service.CompanyService;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.mock;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("junit")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EstockmarketcompanyApplicationTests {


    @Autowired
    private WebApplicationContext webAppContext;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper ob;
    @Autowired
    private CompanyService service;
    @MockBean
    private CompanyAllRepository mongoRepo;

    @BeforeEach
    public void initialize() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webAppContext)
                .build();
    }

    @Test
    @Order(1)
    public void testA1ForConnect() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/company/getping")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string(equalTo("Success")));

    }

    @Test
    @Order(2)
    public void testA2CreateStock() throws Exception {
        CompanyAll com=new CompanyAll();
        com.setCompanyCode(100);
        com.setCompanyCEO("newCEO");
        com.setCompanyTurnOver(100000000);
        com.setCompanyName("newName");
        Mockito.when(mongoRepo.save(com)).thenReturn(com);
        CompanyRequest company = new CompanyRequest();
        company.setCompanyCEO("newCEO");
        company.setCompanyName("newName");
        company.setCompanyTurnOver("100000000");
        String requestJson = ob.writeValueAsString(com);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/company/register")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(MockMvcResultMatchers.status().isCreated());

    }

    @Test
    @Order(3)
    public void testA3CreateStockBadRequest() throws Exception {
        CompanyRequest com = new CompanyRequest();
        com.setCompanyName("newName");
        com.setCompanyTurnOver("100000000");
        String requestJson = ob.writeValueAsString(com);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/company/register")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

    @Test
    @Order(4)
    public void testA4CreateStockBadRequest() throws Exception {
        CompanyRequest com = new CompanyRequest();
        com.setCompanyCEO("newCEO");
        com.setCompanyName("newName");
        com.setCompanyTurnOver("100000");
        String requestJson = ob.writeValueAsString(com);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/company/register")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

    @Test
    @Order(5)
    public void testA5ForGetCompany() throws Exception {
        CompanyAll com=new CompanyAll();
        com.setCompanyCode(1);
        com.setCompanyCEO("CE");
        com.setCompanyTurnOver(100000000);
        com.setCompanyName("sample");
        Optional<CompanyAll> op=Optional.of(com);
        Mockito.when(mongoRepo.findById(1)).thenReturn(op);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/company/info/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    @Order(6)
    public void testA6ForGetCompanyNotFound() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/company/info/10")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

    @Test
    @Order(7)
    public void testA7ForGetAllCompany() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/company/getall")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    @Order(8)
    public void testBBUpdateCompanyDetail() throws Exception {
        CompanyAll com=new CompanyAll();
        com.setCompanyCode(100);
        com.setCompanyCEO("CE");
        com.setCompanyTurnOver(100000000);
        com.setCompanyName("sample");
        Optional<CompanyAll> op=Optional.of(com);
        Mockito.when(mongoRepo.findById(100)).thenReturn(op);
        Stock stock=new Stock();
        stock.setCompanyCode(100);
        stock.setStockDateTime(String.valueOf(System.currentTimeMillis()));
        stock.setStockName("stockName");
        stock.setPrice(54.76);
        service.updateCompanyStockDetail(stock);
    }

    @Test
    @Order(9)
    public void testBB1DeleteCompany() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/company/delete/100")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

}
