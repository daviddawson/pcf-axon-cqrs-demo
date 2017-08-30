package io.pivotal.catalog.controllers;

import io.muoncore.newton.command.CommandBus;
import io.muoncore.newton.command.CommandIntent;
import io.pivotal.catalog.commands.AddProductToCatalogCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.CompletableFuture;


@RestController
public class CatalogApiController {

    private static final Logger LOG = LoggerFactory.getLogger(CatalogApiController.class);

    private final CommandBus commandBus;

    public CatalogApiController(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @PostMapping("/add")
    public CompletableFuture<String> addProductToCatalog(@RequestBody Map<String, Object> request) {
        LOG.info("Executing command: {}", AddProductToCatalogCommand.class);
        return commandBus.dispatchAsync(
                CommandIntent
                        .builder(AddProductToCatalogCommand.class.getCanonicalName())
                        .applyProperties(request)
                        .build())
        .thenApply(commandResult -> "Success!");
    }
}

