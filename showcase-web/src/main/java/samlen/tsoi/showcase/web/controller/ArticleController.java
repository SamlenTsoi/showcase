package samlen.tsoi.showcase.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import samlen.tsoi.showcase.common.pojo.dto.Result;
import samlen.tsoi.showcase.web.entity.ArticleEs;
import samlen.tsoi.showcase.web.service.ArticleEsService;

import java.util.List;

/**
 * @author samlen_tsoi
 * @date 2018/2/5
 */
@RestController
@RequestMapping("article")
public class ArticleController {

    @Autowired
    private ArticleEsService articleEsService;

    @PostMapping("save")
    public Result save(@RequestBody ArticleEs articleEs) {
        articleEsService.save(articleEs);
        return Result.ok();
    }

    @GetMapping("get")
    public Result get(@RequestParam("id") Long id) {
        ArticleEs articleEs = articleEsService.get(id);
        return Result.ok(articleEs);
    }

    @GetMapping("search")
    public List<ArticleEs> search(@RequestParam("pageNum") Integer pageNum,
                                  @RequestParam("pageSize") Integer pageSize) {
        Page<ArticleEs> articleEsPage = articleEsService.search(pageNum, pageSize);
        return articleEsPage.getContent();
    }
}
