package br.gov.basis.taskmanager.taskbatch;


import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.env.Environment;

import br.gov.nuvem.comum.microsservico.util.AppUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableConfigurationProperties({ LiquibaseProperties.class})
@RequiredArgsConstructor
@Slf4j
@EnableScheduling
public class TaskbatchApp implements InitializingBean {

    private final Environment env;

    @Override
    public void afterPropertiesSet() throws Exception {
        AppUtil.checkProfiles(env, log);
    }

    public static void main(String[] args) {
        AppUtil.startup(args, TaskbatchApp.class, log);
    }
}

