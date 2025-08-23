package ru.skypro.homework.controller;


import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.service.AdService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AdsControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AdService adService;

    @Test
    @WithMockUser
    public void getInformationAboutAdAuthorizedTest() throws Exception {
        int id = 1;
        when(adService.getInformationAboutAd(id)).thenReturn(new ExtendedAd());
        this.mockMvc.perform(MockMvcRequestBuilders.get("/ads/{id}?id=" + id, "id=")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(200));
    }

    @Test
    public void getInformationAboutAdNoAuthorizedTest() throws Exception {
        int id = 1;
        when(adService.getInformationAboutAd(id)).thenReturn(new ExtendedAd());
        this.mockMvc.perform(MockMvcRequestBuilders.get("/ads/{id}?id=" + id, "id=")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(401));
    }

    @Test
    @WithMockUser
    public void getAdsFromAuthorizedTest() throws Exception {
        Ads user = new Ads();
        when(adService.getAdsFromAuthorized()).thenReturn(user);
        mockMvc.perform(MockMvcRequestBuilders.get("/ads/me")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(200));
    }

    @Test
    public void getAdsFromNoAuthorizedTest() throws Exception {
        Ads user = new Ads();
        when(adService.getAdsFromAuthorized()).thenReturn(user);
        mockMvc.perform(MockMvcRequestBuilders.get("/ads/me")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(401));
    }


    @Test
    @WithMockUser
    public void getAllAdsAuthorizedTest() throws Exception {
        when(adService.getAllService()).thenReturn(new Ads());
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/ads")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(200));
    }

    @Test
    @WithMockUser
    public void updatingInformationAboutAdAuthorizedTest() throws Exception {
        CreateOrUpdateAd createOrUpdateAd = new CreateOrUpdateAd();
        int id = 1;
        JSONObject createObject = new org.json.JSONObject();
        createObject.put("title", "");
        createObject.put("description", "");
        createObject.put("price", 1000000);

        when(adService.updatingInformationAboutAd(id, createOrUpdateAd)).thenReturn(new Ad());
        mockMvc.perform(MockMvcRequestBuilders.patch("/ads/{id}?id=" + id, "id=")
                        .content(createObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(200));
    }

    @Test
    public void updatingInformationAboutAdNoAuthorizedTest() throws Exception {
        CreateOrUpdateAd createOrUpdateAd = new CreateOrUpdateAd();
        int id = 1;
        JSONObject createObject = new org.json.JSONObject();
        createObject.put("title", "");
        createObject.put("description", "");
        createObject.put("price", 1000000);

        when(adService.updatingInformationAboutAd(id, createOrUpdateAd)).thenReturn(new Ad());
        mockMvc.perform(MockMvcRequestBuilders.patch("/ads/{id}?id=" + id, "id=")
                        .content(createObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(401));
    }


}

