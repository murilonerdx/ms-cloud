package com.murilo.hrworker.resources;

import com.murilo.hrworker.entities.Worker;
import com.murilo.hrworker.repositories.WorkerRepository;
import org.hibernate.jdbc.Work;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RefreshScope
@RestController
@RequestMapping(value = "/workers")
public class WorkerResource {

    @Value("${test.config}")
    private String testConfig;

    @Autowired
    private Environment env;

    private static Logger logger = LoggerFactory.getLogger(WorkerResource.class);

    @Autowired
    private WorkerRepository repository;

    @GetMapping
    public ResponseEntity<List<Worker>> findAll() {
        List<Worker> listWorker = repository.findAll();
        return ResponseEntity.ok().body(listWorker);
    }

    @GetMapping(value="/configs")
    public ResponseEntity<Void> getConfigs() {
       logger.info("Config = "+ testConfig);
       return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Worker> findById(@PathVariable Long id) {
        logger.info("PORT = " + env.getProperty("local.server.port"));
        Worker worker = repository.findById(id).get();
        return ResponseEntity.ok(worker);
    }
}
