package uz.mohirdev.MohirdeV.rest.web;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.mohirdev.MohirdeV.Entity.PostData;
import uz.mohirdev.MohirdeV.model.Post;
import uz.mohirdev.MohirdeV.service.PostService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostResource {

    private final PostService postService;

    public PostResource(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public ResponseEntity getAll(){
        List<Post> result = postService.findAll();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/post-uploads")
    public ResponseEntity uploads(){
        Object result = postService.addToEntity();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/posts")
    public ResponseEntity saveToServer(@RequestBody Post post){
        Post result = postService.saveToServer(post);
        return ResponseEntity.ok(result);
    }
}
