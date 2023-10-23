package com.database.backend.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @描述
 * @日期 2023/10/11 10:16
 */
@Repository
public class UserDao {
    @Autowired
    JdbcTemplate template;

    @Autowired
    DataSourceTransactionManager transactionManager;

    @Autowired
    TransactionTemplate transactionTemplate;

    public int getUserCount() {
        String sql = "select count(1) from t_user";
        Integer count = template.queryForObject(sql, Integer.class);

        return count;
    }

    @Transactional
    public int testTransactional() {
        String sql = "select count(1) from t_user";
        Integer count = template.queryForObject(sql, Integer.class);
        System.out.println(count);
        return count;
    }

    public void testTransactionTemplate() {
        int isolationLevel = transactionTemplate.getIsolationLevel();
        PlatformTransactionManager transactionManager1 = transactionTemplate.getTransactionManager();

        Object execute = transactionTemplate.execute(status -> {
            try {
                System.out.println("Transaction");
            } catch (Exception e) {
                // 如果操作失败，抛出异常，事务将回滚
                System.out.println("Rollback");
                status.setRollbackOnly();
            }
            return null;
        });
    }

    public void testTransaction() {
        TransactionDefinition definition = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(definition);
        String message = "执行成功，没有事务回滚";

        try {
            String sql1 = "select count(1) from t_user";
            String sql2 = "select count(1) from t_user";
            template.execute(sql1);
            template.execute(sql2);
            transactionManager.commit(status);
        } catch (Exception e) {
            e.printStackTrace();
            transactionManager.rollback(status);
            message = "主键重复，事务回滚";
        }
        System.out.println(message);
    }
}
