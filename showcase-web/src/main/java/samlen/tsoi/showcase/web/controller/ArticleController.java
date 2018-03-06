package samlen.tsoi.showcase.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import samlen.tsoi.showcase.common.pojo.dto.Result;
import samlen.tsoi.showcase.entity.po.ArticleEs;
import samlen.tsoi.showcase.service.ArticleEsService;

import java.util.List;

/**
 * @author samlen_tsoi
 * @date 2018/2/5
 */
@Slf4j
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
        log.info("---" + articleEs.toString() + "---");
        return Result.ok(articleEs);
    }

    @GetMapping("search")
    public List<ArticleEs> search(@RequestParam("pageNum") Integer pageNum,
                                  @RequestParam("pageSize") Integer pageSize) {
        Page<ArticleEs> articleEsPage = articleEsService.search(pageNum, pageSize);
        return articleEsPage.getContent();
    }
}
