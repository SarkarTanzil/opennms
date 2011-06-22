/*******************************************************************************
 * This file is part of the OpenNMS(R) Application.
 *
 * OpenNMS(R) is Copyright (C) 1999-2011 The OpenNMS Group, Inc.  All rights reserved.
 * OpenNMS(R) is a registered trademark of The OpenNMS Group, Inc.
 *
 * OpenNMS(R) is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 *
 * OpenNMS(R) is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 *     along with OpenNMS(R).  If not, see <http://www.gnu.org/licenses/>.
 *
 * For more information contact: 
 *     OpenNMS(R) Licensing <license@opennms.org>
 *     http://www.opennms.org/
 *     http://www.opennms.com/
 *******************************************************************************/
package org.opennms.ipv6.summary.gui.client;

import com.google.gwt.event.shared.GwtEvent;

public class HostUpdateEvent extends GwtEvent<HostUpdateEventHandler> {

    public static Type<HostUpdateEventHandler> TYPE = new Type<HostUpdateEventHandler>();
    
    private final String m_host;
    
    public HostUpdateEvent(String host) {
        m_host = host;
    }
    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<HostUpdateEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(HostUpdateEventHandler handler) {
        handler.onHostUpdate(this);
    }
    
    public String getHost() {
        return m_host;
    }

}
