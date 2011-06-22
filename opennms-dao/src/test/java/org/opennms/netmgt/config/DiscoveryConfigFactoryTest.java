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
package org.opennms.netmgt.config;

import static org.junit.Assert.assertEquals;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.opennms.core.utils.InetAddressUtils;
import org.opennms.netmgt.model.discovery.IPPollAddress;

/**
 * 
 */
public class DiscoveryConfigFactoryTest {
    @Test
    public void testAddToSpecificsFromURLViaURL() throws Exception {
        final List<IPPollAddress> specifics = new ArrayList<IPPollAddress>();
        final URL in = Thread.currentThread().getContextClassLoader().getResource(
                "org/opennms/netmgt/config/validDiscoveryIncludeFile.txt"
        );
        final long timeout = 100;
        final int retries = 1;
        DiscoveryConfigFactory.addToSpecificsFromURL(specifics, in.toString(), timeout, retries);
        assertEquals(8, specifics.size());
        assertEquals("127.0.0.1", InetAddressUtils.str(specifics.get(0).getAddress()));
        assertEquals("10.1.1.1", InetAddressUtils.str(specifics.get(1).getAddress()));
        assertEquals("10.2.1.1", InetAddressUtils.str(specifics.get(2).getAddress()));
        assertEquals("8.8.8.8", InetAddressUtils.str(specifics.get(3).getAddress()));
        assertEquals("fe80:0000:0000:0000:ffff:eeee:dddd:cccc", InetAddressUtils.str(specifics.get(4).getAddress()));
        assertEquals("0000:0000:0000:0000:0000:0000:0000:0001", InetAddressUtils.str(specifics.get(5).getAddress()));
        assertEquals("fe80:0000:0000:0000:ffff:eeee:dddd:cccd", InetAddressUtils.str(specifics.get(6).getAddress()));
        assertEquals("fe80:0000:0000:0000:ffff:eeee:dddd:cccc", InetAddressUtils.str(specifics.get(7).getAddress()));
    }
    
    @Test
    public void testAddToSpecificsFromURLViaStream() throws Exception {
        final List<IPPollAddress> specifics = new ArrayList<IPPollAddress>();
        final InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(
                "org/opennms/netmgt/config/validDiscoveryIncludeFile.txt"
        );
        final long timeout = 100;
        final int retries = 1;
        DiscoveryConfigFactory.addToSpecificsFromURL(specifics, in, timeout, retries);
        assertEquals(8, specifics.size());
        assertEquals("127.0.0.1", InetAddressUtils.str(specifics.get(0).getAddress()));
        assertEquals("10.1.1.1", InetAddressUtils.str(specifics.get(1).getAddress()));
        assertEquals("10.2.1.1", InetAddressUtils.str(specifics.get(2).getAddress()));
        assertEquals("8.8.8.8", InetAddressUtils.str(specifics.get(3).getAddress()));
        assertEquals("fe80:0000:0000:0000:ffff:eeee:dddd:cccc", InetAddressUtils.str(specifics.get(4).getAddress()));
        assertEquals("0000:0000:0000:0000:0000:0000:0000:0001", InetAddressUtils.str(specifics.get(5).getAddress()));
        assertEquals("fe80:0000:0000:0000:ffff:eeee:dddd:cccd", InetAddressUtils.str(specifics.get(6).getAddress()));
        assertEquals("fe80:0000:0000:0000:ffff:eeee:dddd:cccc", InetAddressUtils.str(specifics.get(7).getAddress()));
    }
}
