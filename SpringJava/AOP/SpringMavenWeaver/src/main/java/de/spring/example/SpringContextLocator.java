package de.spring.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 *
 */
public final class SpringContextLocator {
	private static final Logger LOGGER = LoggerFactory.getLogger(SpringContextLocator.class);


        // Singleton Pattern
        private static SpringContextLocator instance;

        // Spring ApplicationContext
        private static ApplicationContext context;

        // Spring Context
        private static final String SPRING_CONFIG_CONTEXT="/spring-config.xml";


        /**
         * Private constructor. Singleton pattern.
         */
        private SpringContextLocator() {
                String[] factoryFiles = null;
                LOGGER.info("Loading context files: " + SpringContextLocator.SPRING_CONFIG_CONTEXT);

                factoryFiles = new String[] { SPRING_CONFIG_CONTEXT };

                SpringContextLocator.context = new ClassPathXmlApplicationContext(factoryFiles);

                LOGGER.info("The context has been loaded successfully!! ");
        }

        /**
         * Singleton pattern not thread safety. To use SingletoHolder pattern as the best approximation 
         * otherwise to use an Enum class (see Effective Java Second Edition and ) if we need serialization.
         */
        public static SpringContextLocator getInstance() {
                if (SpringContextLocator.instance == null) {
                        SpringContextLocator.instance = new SpringContextLocator();
                }
                return SpringContextLocator.instance;
        }

        /**
         * Return bean from application context.
         */
        public Object getBean(final String name) {
                return SpringContextLocator.context.getBean(name);
        }
}