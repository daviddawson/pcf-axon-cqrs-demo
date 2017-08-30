package io.pivotal.catalog.commands;

import io.muoncore.newton.command.Command;
import io.muoncore.newton.eventsource.EventSourceRepository;
import io.pivotal.catalog.aggregates.Product;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@ToString
public class AddProductToCatalogCommand implements Command {

    @Setter
    private String id;
    @Setter
    private String name;

    @Autowired
    private EventSourceRepository<Product> repository;

    @Override
    public void execute() {
        repository.newInstance(() -> new Product(id, name));
    }
}
