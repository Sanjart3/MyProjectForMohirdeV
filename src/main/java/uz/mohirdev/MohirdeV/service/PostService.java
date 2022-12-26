package uz.mohirdev.MohirdeV.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uz.mohirdev.MohirdeV.Entity.PostData;
import uz.mohirdev.MohirdeV.model.Post;
import uz.mohirdev.MohirdeV.repository.PostRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class PostService {

    @Value("${api.json placeholder}")
    private String api;

    private final RestTemplate restTemplate;

    private final PostDataService postDataService;

    public PostService(RestTemplate restTemplate, PostDataService postDataService) {
        this.restTemplate = restTemplate;
        this.postDataService = postDataService;
    }

    public List<Post> findAll(){

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<List<Post>> entity = new HttpEntity<>(headers);
        List<Post> result = restTemplate.exchange(this.api+"/posts", HttpMethod.GET,entity, List.class).getBody();
        return result;
    }

    public Object addToEntity(){
//        HttpHeaders headers2 = new HttpHeaders();
//        headers2.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//        HttpEntity<Post[]> entity = new HttpEntity<>(headers2);
//        Post[] result = restTemplate.exchange(this.api+"/posts", HttpMethod.GET, entity, Post[].class).getBody();
//        postDataService.saveAll(result);

        // 2 ND WAY USING HASHMAP
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<List<Post>> entity = new HttpEntity<>(headers);
        List<Post> result = restTemplate.exchange(this.api+"/posts", HttpMethod.GET,entity, List.class).getBody();
        postDataService.saveAll(result);
        return result;
    }

    public Post saveToServer(Post post){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Post> entity = new HttpEntity<>(post,headers);
        Post result = restTemplate.postForObject(this.api+"/posts",entity,Post.class);
        return result;
    }
}
