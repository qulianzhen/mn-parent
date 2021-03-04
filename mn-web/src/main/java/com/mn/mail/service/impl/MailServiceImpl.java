package com.mn.mail.service.impl;

import com.mn.mail.dao.MailRepository;
import com.mn.mail.entity.MailIndexEs;
import com.mn.mail.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

/**
 * @Description: (描述)
 * @Author:Mloong
 * @Date :create in 2020/9/23-1:06
 * @Version 1.0
 * @Modified By:
 */
@Service
public class MailServiceImpl implements MailService {
    @Autowired
    private MailRepository repository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;


    @Override
    public void save(MailIndexEs mailIndexEs) {
        repository.save(mailIndexEs);
    }

    @Override
    public Page<MailIndexEs> find(String phone, Pageable page) {
        return repository.findByPhone(phone,page);
    }
}
