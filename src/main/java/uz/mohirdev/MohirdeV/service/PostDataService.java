package uz.mohirdev.MohirdeV.service;

import org.springframework.stereotype.Service;
import uz.mohirdev.MohirdeV.Entity.PostData;
import uz.mohirdev.MohirdeV.model.Post;
import uz.mohirdev.MohirdeV.repository.PostRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostDataService {

    private final PostRepository postRepository;

    public PostDataService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostData save(PostData postData){

        return postRepository.save(postData);
    }

    public List<PostData> saveAll(Post[] postList){
        ArrayList<PostData> postDatas = new ArrayList<>();
        for (Post post:postList) {
            PostData postData = new PostData();
            postData.setPostId(post.getId());
            postData.setBody(post.getBody());
            postData.setTitle(post.getTitle());
            postDatas.add(postData);
        }
//        for (int i=0;i<postList.toArray().length;i++) {
//            PostData postData = new PostData();
//            postData.setPostId(postList.get(i).getId());
//            postData.setUserId(postList.get(i).getUserId());
//            postData.setTitle(postList.get(i).getTitle());
//            postData.setBody(postList.get(i).getBody());
//            postDatas.add(postData);
//        }



//        List<PostData> postDataList = postList
//                .stream()
//                .map(post -> {
//                    PostData postData = new PostData();
//                    postData.setPostId(post.getId());
//                    postData.setUserId(post.getUserId());
//                    postData.setTitle(post.getTitle());
//                    postData.setBody(post.getBody());
//                    // there we can just return this postData object in every cycle of the loop, instead of saving them into the list first!
//                    return postData;
//                }).collect(Collectors.toList());
        return postRepository.saveAll(postDatas);
    }
}
