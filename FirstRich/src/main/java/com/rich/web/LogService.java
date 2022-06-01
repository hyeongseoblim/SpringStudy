package com.rich.web;

import com.rich.common.Logger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogService {

    private final Logger logger;

    public void logic(String id) {
        logger.log("service id = " + id);
    }
}
