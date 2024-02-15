package com.backend.show.handler;

import com.backend.show.entity.UsersDataEntity;
import com.backend.show.mapper.UserDataToUserDataEntityMapper;
import com.backend.show.model.UserData;
import com.backend.show.service.UserDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Component
public class MongoDbRequestHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(MongoDbRequestHandler.class);

    @Autowired
    private UserDataService userDataService;

    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private UserDataToUserDataEntityMapper userDataToUserDataEntityMapper;

    public String handleSaveRequest(UserData userData) {
        var entity = userDataToUserDataEntityMapper.map(userData);
        return userDataService.saveUser(entity);
    }

    public String handleSaveRequest(List<UserData> dataList) {
        List<UsersDataEntity> list = Collections.emptyList();
        try {
            list = dataList.stream()
                    .map(userData ->
                            restTemplate.postForObject("http://localhost:8080/start/save", new HttpEntity<>(userData), String.class))
                    .map(s -> {
                        var entity = new UsersDataEntity();
                        entity.setName(s);
                        return entity;
                    })
                    .toList();
        }catch (Exception e){
            LOGGER.error("Error occured while saving a list of data ",e);
        }
        LOGGER.info("Saved successfully with msgs : {}",list);
        return "resp";
    }

    public List<UsersDataEntity> handleFetchRequest(String name) {
        return userDataService.fetchAllUsersData(name);
    }

    public Object findAllArticles() {
        var httpResponse = restTemplate.execute("https://nprssfeeds.indiatimes.com/inlinegalleries.cms?&order=1&feedSection=news&feedtype=sjson&debug=tru", HttpMethod.GET, null,null);
        return httpResponse;
    }
}
