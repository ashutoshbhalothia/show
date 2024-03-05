package com.backend.show.handler;

import com.backend.show.mapper.UserDataEntityToUserDataMapper;
import com.backend.show.mapper.UserDataToUserDataEntityMapper;
import com.backend.show.model.UserData;
import com.backend.show.service.ExternalClientService;
import com.backend.show.service.UserDataService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MongoDbRequestHandler extends CompletableFutureHandler{
    private static final Logger LOGGER = LoggerFactory.getLogger(MongoDbRequestHandler.class);

    @Value("${news.feed.url:}")
    private String url;

    private final UserDataService userDataService;

    private final ExternalClientService externalClientService;

    private final UserDataEntityToUserDataMapper userDataEntityToUserDataMapper;

    private final UserDataToUserDataEntityMapper userDataToUserDataEntityMapper;

    public String handleSaveRequest(UserData userData) {
        var entity = userDataToUserDataEntityMapper.map(userData);
        return userDataService.saveUser(entity);
    }

    public List<UserData> handleFetchRequest(String name) {
        var entity = userDataService.fetchUsersData(name);
        return userDataEntityToUserDataMapper.map(entity);
    }

    public String handleSaveRequest(List<UserData> dataList) {
        dataList.forEach(userData -> handleSaveRequest(userData));
        return "saved";
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
        var response = externalClientService.executeGET(url,Object.class);
        return handleCompletableResponse(response,new Object());
    }
}
