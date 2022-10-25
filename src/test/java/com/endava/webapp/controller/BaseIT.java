package com.endava.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.FileCopyUtils;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UncheckedIOException;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.core.io.ResourceLoader.CLASSPATH_URL_PREFIX;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Sql(scripts = "classpath:sql/init-db.sql")
public class BaseIT {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private ResourceLoader resourceLoader;

    protected String readJsonFromFile(final String path) {
        try (Reader reader = new InputStreamReader(getResourceAsInputStream(path + ".json"), UTF_8)) {
            return FileCopyUtils.copyToString(reader);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
    private InputStream getResourceAsInputStream(final String path) throws IOException {
        return resourceLoader.getResource(CLASSPATH_URL_PREFIX + path).getInputStream();
    }
}
