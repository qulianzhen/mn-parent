package com.mn.mail.service;

import com.mn.mail.entity.MailIndexEs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Description: (描述)
 * @Author:Mloong
 * @Date :create in 2020/9/23-1:06
 * @Version 1.0
 * @Modified By:
 */
public interface MailService {
    public void save(MailIndexEs mailIndexEs);

    public Page<MailIndexEs> find(String phone, Pageable page);
}
