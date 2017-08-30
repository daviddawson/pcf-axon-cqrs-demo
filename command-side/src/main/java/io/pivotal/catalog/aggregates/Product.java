package io.pivotal.catalog.aggregates;

import io.muoncore.newton.AggregateRoot;
import io.muoncore.newton.EventHandler;
import io.muoncore.newton.eventsource.AggregateConfiguration;
import io.pivotal.catalog.events.ProductAddedEvent;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

@AggregateConfiguration(context = "products")
@Getter
public class Product extends AggregateRoot<String> {

    private static final Logger LOG = LoggerFactory.getLogger(Product.class);

    private String id;
    private String name;

    public Product() {
    }

    public Product(String id, String name) {
        Assert.hasLength(id, "ID should NOT be empty or null.");
        Assert.hasLength(name, "Name should NOT be empty or null.");
        raiseEvent(new ProductAddedEvent(id, name));
    }

    @EventHandler
    public void on(ProductAddedEvent evnt) {
        LOG.debug("Handling {} event: {}, {}", evnt.getClass().getSimpleName(), evnt.getId(), evnt.getName());
        this.id = evnt.getId();
        this.name = evnt.getName();
        LOG.trace("Done handling {} event: {}, {}", evnt.getClass().getSimpleName(), evnt.getId(), evnt.getName());
    }
}
