package io.pivotal.catalog.components;

import io.muoncore.newton.EventHandler;
import io.muoncore.newton.StreamSubscriptionManager;
import io.muoncore.newton.query.RebuildingDatastoreView;
import io.muoncore.newton.query.SharedDatastoreView;
import io.pivotal.catalog.entities.Product;
import io.pivotal.catalog.events.ProductAddedEvent;
import io.pivotal.catalog.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


/*
* The @ProcessingGroup annotation hooks up this class up to an Axon Event Processor Group.
* The @EventHandler annotated method handles the Events of a given type.
*
* When the events come in, we persist a 'Product' entity to the JPA repository
* created for us by Spring Data Repositories.
*
* We have to organise the event handler into a Processor group as by default, processors read from the EventBus not AMQP,
* so we need to reconfigure things a bit - we need to add our event handler to a processor that reads from a Rabbit queue.
*
* So we annotate the class with @ProcessorGroup(“amqpEvents”) to configure the event processor (note default processor
* would use the package-name). We also need to add to the application.properties the following setting
* “axon.eventhandling.processors.amqpEvents.source=complaintEventsMethod”. You'll see this property in GitHub as that's
* where our configuration comes from for this app as it's been externalised by Spring Cloud Config.
*
* Note that the “complaintEventsMethod” keyword in the properties comes from (and must match) the @Bean name of the
* complaintEventsMethod(Serializer serializer) method in the AxonConfiguration class!
*/

@Component
public class InMemProductListView extends RebuildingDatastoreView {

    private static final Logger LOG = LoggerFactory.getLogger(InMemProductListView.class);

    private final Map<String, Product> products = new HashMap<>();

    public InMemProductListView(StreamSubscriptionManager streamSubscriptionManager) {
        super(streamSubscriptionManager);
    }

    @Override
    protected String[] eventStreams() {
        return new String[] { "products/Product" };
    }

    @EventHandler
    public void on(ProductAddedEvent productAddedEvent) {
        products.put(productAddedEvent.getId(), new Product(productAddedEvent.getId(), productAddedEvent.getName()));
        LOG.info("A product was added! Id={} Name={}", productAddedEvent.getId(), productAddedEvent.getName());
    }
}
