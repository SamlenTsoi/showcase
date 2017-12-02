package samlen.tsoi.showcase.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import samlen.tsoi.showcase.entity.UserFile;
import samlen.tsoi.showcase.service.UserFileWriteService;
import samlen.tsoi.showcase.service.dao.UserFileMapper;

/**
 * @author samlen_tsoi
 * @date 2017/12/2
 */
@Service
public class UserFileWriteServiceImpl implements UserFileWriteService {

    @Autowired
    private UserFileMapper userFileMapper;

    @Override
    public void insert(UserFile userFile) {
        userFileMapper.insertSelective(userFile);
    }

}
