package com.meli.mutant.mutantapp.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log4jImpl implements ILoggerStrategy {

    private Logger logger = LoggerFactory.getLogger(Log4jImpl.class);

    @Override
    public void logInfo(String msg) {
        logger.info(msg);
    }

    @Override
    public void logError(String msg) {
        logger.error(msg);
    }

}
