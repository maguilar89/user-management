package nisum.com.user_management.infrastructure.config;

import nisum.com.user_management.infrastructure.rest.message.CustomMessageHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class CustomMessageHandlerRunner implements ApplicationRunner {

    private static final Logger logger = LogManager.getLogger(CustomMessageHandlerRunner.class);

    @Value("classpath:messages.properties")
    private Resource resource;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.debug("ApplicationRunner#run()");
        Properties props = System.getProperties();
        props.setProperty("javax.accessibility.assistive_technologies", "");
        CustomMessageHandler.getInstance().loadProps(resource.getInputStream());
    }
}