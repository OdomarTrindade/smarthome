/**
 * Copyright (c) 2014 openHAB UG (haftungsbeschraenkt) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.smarthome.binding.wemo.internal;


import java.util.Set;

import org.eclipse.smarthome.binding.wemo.handler.WemoHandler;
import org.eclipse.smarthome.core.thing.Thing;
import org.eclipse.smarthome.core.thing.ThingTypeUID;
import org.eclipse.smarthome.core.thing.binding.BaseThingHandlerFactory;
import org.eclipse.smarthome.core.thing.binding.ThingHandler;
import org.eclipse.smarthome.config.discovery.DiscoveryServiceRegistry;
import org.eclipse.smarthome.io.transport.upnp.UpnpIOService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Sets;

import static org.eclipse.smarthome.binding.wemo.config.WemoConfiguration.UDN;

/**
 * The {@link WemoHandlerFactory} is responsible for creating things and thing 
 * handlers.
 * 
 * @author Hans-Jörg Merk - Initial contribution
 */
public class WemoHandlerFactory extends BaseThingHandlerFactory {
    
	private Logger logger = LoggerFactory.getLogger(WemoHandlerFactory.class);

	private UpnpIOService upnpIOService;
	private DiscoveryServiceRegistry discoveryServiceRegistry;

	public final static Set<ThingTypeUID> SUPPORTED_THING_TYPES = Sets.newHashSet(WemoHandler.SUPPORTED_THING_TYPES);
	

    @Override
    public boolean supportsThingType(ThingTypeUID thingTypeUID) {
        return SUPPORTED_THING_TYPES.contains(thingTypeUID);
    }

    @Override
    protected ThingHandler createHandler(Thing thing) {

        ThingTypeUID thingTypeUID = thing.getThingTypeUID();

        if (WemoHandler.SUPPORTED_THING_TYPES.contains(thingTypeUID)) {
        	logger.debug("Creating a WemoHandler for thing '{}' with UDN '{}'",thing.getUID(),thing.getConfiguration().get(UDN));
            return new WemoHandler(thing, upnpIOService, discoveryServiceRegistry);
        }

        return null;
    }

    protected void setUpnpIOService(UpnpIOService upnpIOService) {
		this.upnpIOService = upnpIOService;
	}

	protected void unsetUpnpIOService(UpnpIOService upnpIOService) {
		this.upnpIOService = null;
	}
    
    protected void setDiscoveryServiceRegistry(DiscoveryServiceRegistry discoveryServiceRegistry) {
        this.discoveryServiceRegistry = discoveryServiceRegistry;
    }
    
    protected void unsetDiscoveryServiceRegistry(DiscoveryServiceRegistry discoveryServiceRegistry) {
    	this.discoveryServiceRegistry = null;
    }
}
