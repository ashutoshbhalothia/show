package com.backend.show.handler;

import com.backend.show.entity.UsersDataEntity;
import com.backend.show.mapper.UserDataEntityToUserDataMapper;
import com.backend.show.mapper.UserDataToUserDataEntityMapper;
import com.backend.show.model.UserData;
import com.backend.show.service.ExternalClientService;
import com.backend.show.service.UserDataService;
import com.backend.show.utils.ExternalCallTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class MongoDbRequestHandler extends CompletableFutureHandler{
    private static final Logger LOGGER = LoggerFactory.getLogger(MongoDbRequestHandler.class);

    @Autowired
    private UserDataService userDataService;

    @Autowired
    private ExternalClientService externalClientService;

    @Autowired
    private UserDataEntityToUserDataMapper userDataEntityToUserDataMapper;

    @Autowired
    private UserDataToUserDataEntityMapper userDataToUserDataEntityMapper;

    public String handleSaveRequest(UserData userData) {
        var entity = userDataToUserDataEntityMapper.map(userData);
        return userDataService.saveUser(entity);
    }

    public List<UserData> handleFetchRequest(String name) {
        var entity = userDataService.fetchUsersData(name);
        return userDataEntityToUserDataMapper.map(entity);
    }

    public String handleSaveRequest(List<UserData> dataList) {
        List<UsersDataEntity> list = Collections.emptyList();
        try {
            list = dataList.stream()
                    .map(userData ->
                                    ExternalCallTemplate.executePOST("http://localhost:8080/start/save",userData,null))
                    .map(s -> {
                        var entity = new UsersDataEntity();
                        entity.setName(s);
                        return entity;
                    })
                    .toList();
        } catch (Exception e) {
            LOGGER.error("Error occured while saving a list of data ", e);
        }
        LOGGER.info("Saved successfully with msgs : {}", list);
        return "resp";
    }

    public Object findAllArticles() {
//        var httpResponse = ExternalCallTemplate.executeGET("https://nprssfeeds.indiatimes.com/inlinegalleries.cms?&order=1&feedSection=news&feedtype=sjson&debug=tru");
//        var response =
//                CompletableFuture
//                        .supplyAsync(() ->
//                                externalClientService.executeGET("https://nprssfeeds.indiatimes.com/inlinegalleries.cms?&order=1&feedSection=news&feedtype=sjson&debug=tru",Object.class)
//                                .block()
//                                , MyThreadPoolExecutor.getExecutor());
//        return response.get(HttpStatus.REQUEST_TIMEOUT);
        var response = externalClientService.executeGET("https://nprssfeeds.indiatimes.com/inlinegalleries.cms?&order=1&feedSection=news&feedtype=sjson&debug=tru",Object.class).toFuture();
        return handleCompletableResponse(response,new Object());
    }
}
