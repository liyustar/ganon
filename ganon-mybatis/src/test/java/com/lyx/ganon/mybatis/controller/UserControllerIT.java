package com.lyx.ganon.mybatis.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.lyx.ganon.mybatis.controller.request.RewardReq;
import com.lyx.ganon.mybatis.model.BizAccount;
import com.lyx.ganon.mybatis.model.BizArticle;
import com.lyx.ganon.mybatis.model.BizArticleComment;
import com.lyx.ganon.mybatis.model.BizUser;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.util.List;
import java.util.function.IntConsumer;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void bigIntergationTest() throws JsonProcessingException {
        // clean data
        restTemplate.delete("/users");
        restTemplate.delete("/accounts");
        restTemplate.delete("/articles");
        restTemplate.delete("/comments");

        // create user01
        BizUser user01 = new BizUser();
        user01.setName("user01");
        user01.setPassword("user01");
        createUser(user01);
        log.info("--userId: {}", user01.getId());

        // user01 建账户 + 充钱
        BizAccount acc01 = new BizAccount();
        acc01.setAccCode("acc01");
        acc01.setAmt(1000.0);
        acc01.setUserId(user01.getId());
        createAccount(acc01);
        log.info("--accountId: {}", acc01.getId());

        // user01 写文章
        BizArticle article01 = new BizArticle();
        article01.setAuthorId(user01.getId());
        article01.setTitle("Title01--good");
        article01.setContent("today is good");
        createArticle(article01);
        log.info("--articleId: {}", article01.getId());


        // create user02
        BizUser user02 = new BizUser();
        user02.setName("user02");
        user02.setPassword("user02");
        createUser(user02);
        log.info("--userId: {}", user02.getId());

        // user02 建账户 + 充钱
        BizAccount acc02 = new BizAccount();
        acc02.setAccCode("acc02");
        acc02.setAmt(2000.0);
        acc02.setUserId(user02.getId());
        createAccount(acc02);
        log.info("--accountId: {}", acc02.getId());

        // user02 去评论一下
        BizArticleComment comment01 = new BizArticleComment();
        comment01.setArticleId(article01.getId());
        comment01.setCreatorId(user02.getId());
        comment01.setContent("good article");
        createComment(comment01);
        log.info("--commentId: {}", comment01.getId());

        // user02 给文章打赏
        RewardReq rewardReq01 = new RewardReq(article01.getId(), user02.getId(), 50.0);
        createReward(rewardReq01);

        // user02 再打赏一个
        RewardReq rewardReq02 = new RewardReq(article01.getId(), user02.getId(), 15.0);
        createReward(rewardReq02);
    }

    @Test
    public void queryTest() {
        // 展示文章信息与前10条评论
        List<BizArticle> articles = getArticles();

        int articleId = articles.stream().findFirst().orElseThrow(() -> new RuntimeException()).getId();
        getComments(articleId);

        // 展示文章打赏流水
        getCashLogs(articleId);
    }

    private void getCashLogs(int articleId) {
        String ret = restTemplate.getForObject("/articles/" + articleId + "/rewards", String.class);
        log.info("--ret: {}", ret);
    }

    private void getComments(int articleId) {
        String ret = restTemplate.getForObject("/articles/" + articleId + "/comments", String.class);
        log.info("--ret: {}", ret);
    }

    private List<BizArticle> getArticles() {
        ResponseEntity<ResponseObj<List<BizArticle>>> ret = restTemplate.exchange("/articles",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ResponseObj<List<BizArticle>>>() {
                });
        log.info("--ret: {}", ret.getBody());
        return ret.getBody().getData();
    }

    private void createUser(BizUser user) throws JsonProcessingException {
        postTemplate(user, "/users", user::setId);
    }

    private void createAccount(BizAccount account) throws JsonProcessingException {
        postTemplate(account, "/accounts", account::setId);
    }

    private void createArticle(BizArticle article) throws JsonProcessingException {
        postTemplate(article, "/articles", article::setId);
    }

    private void createComment(BizArticleComment comment) throws JsonProcessingException {
        postTemplate(comment,
                "/articles/" + comment.getArticleId() + "/comments",
                comment::setId);
    }

    private void createReward(RewardReq rewardReq) throws JsonProcessingException {
        postTemplate(rewardReq,
                "/articles/" + rewardReq.getArticleId() + "/rewards",
                (i) -> {});
    }

    private <T> void postTemplate(T t, String url, IntConsumer returnId) throws JsonProcessingException {
        String content = new ObjectMapper().writeValueAsString(t);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String ret = restTemplate.postForObject(url, new HttpEntity<>(content, headers), String.class);
        log.info("--ret: {}", ret);
        int id = JsonPath.parse(ret).read("$.data");
        returnId.accept(id);
    }


}