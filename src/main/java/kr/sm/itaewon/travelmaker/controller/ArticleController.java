package kr.sm.itaewon.travelmaker.controller;

import kr.sm.itaewon.travelmaker.model.Article;
import kr.sm.itaewon.travelmaker.model.Basket;
import kr.sm.itaewon.travelmaker.model.Link;
import kr.sm.itaewon.travelmaker.repo.ArticleRepository;
import kr.sm.itaewon.travelmaker.repo.BasketRepository;
import kr.sm.itaewon.travelmaker.repo.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private BasketRepository basketRepository;

    @Autowired
    private LinkRepository linkRepository;

    @RequestMapping("/")
    public ResponseEntity<Void> badRequest(){
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }


    ////////////Article

    @GetMapping("/getArticleAll")
    public ResponseEntity<List<Article>> getArticleAll(){

        try {
            List<Article> list = new ArrayList<>();
            Iterable<Article> articles = articleRepository.findAll();

//            for(Article article: articles){
//                if(article.getLink().getLinkId()>-1){
//
//                    Link link = linkRepository.findByLinkId(article.getLink().getLinkId());
//                    if(link !=null){
//                        article.setLink(link);
//                    }
//                }
//            }
            articles.forEach(list::add);

            if(list == null){
                return new ResponseEntity<List<Article>>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<List<Article>>(list, HttpStatus.OK);

        }catch (Exception e){

            e.printStackTrace();
            return new ResponseEntity<List<Article>>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/getArticleById/{article_id}")
    public ResponseEntity<List<Article>> getArticleById(@PathVariable long article_id){

        try {
            List<Article> list = articleRepository.findByArticleId(article_id);

            if(list == null){
                return new ResponseEntity<List<Article>>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<List<Article>>(list, HttpStatus.OK);

        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<List<Article>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getArticleByCustomerId/{customer_id}")
    public ResponseEntity<List<Article>> getArticleByCustomerId(@PathVariable long customer_id){

        try {
            List<Article> list = articleRepository.findByCustomerId(customer_id);

            if(list == null){
                return new ResponseEntity<List<Article>>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<List<Article>>(list, HttpStatus.OK);

        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<List<Article>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/getArticleByLocationId/{location_id}")
    public ResponseEntity<List<Article>> getArticleByLocationId(@PathVariable long location_id){

        try {
            List<Article> list = articleRepository.findByLocationId(location_id);

//            for(Article article: list) {
//                if (article.getLink().getLinkId() > -1) {
//
//                    Link link = linkRepository.findByLinkId(article.getLink().getLinkId());
//                    if (link != null) {
//                        article.setLink(link);
//                    }
//                }
//            }
            return new ResponseEntity<List<Article>>(list, HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<List<Article>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/postArticle/{customer_id}")
    public ResponseEntity<Void> postArticle(@PathVariable long customer_id, @RequestBody Article article){
        if(article == null){
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }

        try {
            articleRepository.save(article);
            return new ResponseEntity<Void>(HttpStatus.CREATED);

        }catch (Exception e){
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}
