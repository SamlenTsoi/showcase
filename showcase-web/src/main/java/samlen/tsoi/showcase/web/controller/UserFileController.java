package samlen.tsoi.showcase.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import samlen.tsoi.showcase.entity.UserFile;
import samlen.tsoi.showcase.service.UserFileWriteService;

/**
 * @author samlen_tsoi
 * @date 2017/12/2
 */
@Slf4j
@RestController
@RequestMapping("user-file")
public class UserFileController {

    @Autowired
    private UserFileWriteService userFileWriteService;

    @PostMapping("add")
    public void create(@RequestBody UserFile userFile) {
        userFileWriteService.insert(userFile);
    }

}
