package com.mn.mail.dao;

import com.mn.mail.entity.MailIndexEs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * @Description: (描述)
 * @Author:Mloong
 * @Date :create in 2020/9/23-0:53
 * @Version 1.0
 * @Modified By:
 */
public interface  MailRepository extends ElasticsearchRepository<MailIndexEs,Long>, PagingAndSortingRepository<MailIndexEs,Long> {
    List<MailIndexEs> findByCreateTimeAndPhone(String createTime, String phone);

    List<MailIndexEs> findByContentOrDbid(String content, String dbid);

    Page<MailIndexEs> findByPhone(String phone, Pageable page);

    Page<MailIndexEs> findByPhoneNot(String phone,Pageable page);

    Page<MailIndexEs> findByCreateTimeBetween(String createTime,Pageable page);

    Page<MailIndexEs> findByExceptionNameLike(String exceptionName,Pageable page);

    @Query("{\"bool\" : {\"must\" : {\"term\" : {\"message\" : \"?0\"}}}}")
    Page<MailIndexEs> findByMessage(String message, Pageable pageable);
}
