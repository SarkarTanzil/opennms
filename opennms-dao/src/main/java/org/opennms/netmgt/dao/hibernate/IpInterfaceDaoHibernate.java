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
package org.opennms.netmgt.dao.hibernate;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.opennms.netmgt.dao.IpInterfaceDao;
import org.opennms.netmgt.model.OnmsIpInterface;
import org.opennms.netmgt.model.OnmsNode;
import org.springframework.util.Assert;
/**
 * <p>IpInterfaceDaoHibernate class.</p>
 *
 * @author david
 */
public class IpInterfaceDaoHibernate extends AbstractDaoHibernate<OnmsIpInterface, Integer>  implements IpInterfaceDao {
    
    String m_findByServiceTypeQuery = null;

    /**
     * <p>Constructor for IpInterfaceDaoHibernate.</p>
     */
    public IpInterfaceDaoHibernate() {
        super(OnmsIpInterface.class);
        
        
        m_findByServiceTypeQuery = System.getProperty("org.opennms.dao.ipinterface.findByServiceType", 
                                                      "select distinct ipInterface from OnmsIpInterface as ipInterface join ipInterface.monitoredServices as monSvc where monSvc.serviceType.name = ?");
        
    }

    /** {@inheritDoc} */
    public OnmsIpInterface get(OnmsNode node, String ipAddress) {
        return findUnique("from OnmsIpInterface as ipInterface where ipInterface.node = ? and ipInterface.ipAddress = ?", node, ipAddress);
    }

    /** {@inheritDoc} */
    public List<OnmsIpInterface> findByIpAddress(String ipAddress) {
        return find("from OnmsIpInterface ipInterface where ipInterface.ipAddress = ?", ipAddress);
    }
    
    /** {@inheritDoc} */
    public OnmsIpInterface findByNodeIdAndIpAddress(Integer nodeId, String ipAddress) {
        return findUnique("select ipInterface from OnmsIpInterface as ipInterface where ipInterface.node.id = ? and ipInterface.ipAddress = ?", 
                          nodeId, 
                          ipAddress);
        
    }

    /** {@inheritDoc} */
    public OnmsIpInterface findByForeignKeyAndIpAddress(String foreignSource, String foreignId, String ipAddress) {
        return findUnique("select ipInterface from OnmsIpInterface as ipInterface join ipInterface.node as node where node.foreignSource = ? and node.foreignId = ? and ipInterface.ipAddress = ?", 
                          foreignSource, 
                          foreignId, 
                          ipAddress);
        
    }

    /** {@inheritDoc} */
    public List<OnmsIpInterface> findByServiceType(String svcName) {
        
        return find(m_findByServiceTypeQuery, svcName);
    }

    /** {@inheritDoc} */
    public List<OnmsIpInterface> findHierarchyByServiceType(String svcName) {
        return find("select distinct ipInterface " +
                    "from OnmsIpInterface as ipInterface " +
                    "left join fetch ipInterface.node as node " +
                    "left join fetch node.assetRecord " +
                    "left join fetch ipInterface.node.snmpInterfaces as snmpIf " +
                    "left join fetch snmpIf.ipInterfaces " +
                    "join ipInterface.monitoredServices as monSvc " +
                    "where monSvc.serviceType.name = ?", svcName);
    }

    /**
     * <p>getInterfacesForNodes</p>
     *
     * @return a {@link java.util.Map} object.
     */
    public Map<InetAddress, Integer> getInterfacesForNodes() {
        Map<InetAddress, Integer> map = new HashMap<InetAddress, Integer>();
        
        @SuppressWarnings("unchecked")
        List<Object[]> l = getHibernateTemplate().find("select distinct ipInterface.ipAddress, ipInterface.node.id from OnmsIpInterface as ipInterface");

        for (Object[] tuple : l) {
            InetAddress ip = (InetAddress) tuple[0];
            Integer nodeId = (Integer) tuple[1];
            map.put(ip, nodeId);
        }
        
        return map;
    }

    /**
     * <p>addressExistsWithForeignSource</p>
     *
     * @param ipAddress a {@link java.lang.String} object.
     * @param foreignSource a {@link java.lang.String} object.
     * @return a boolean.
     */
    public boolean addressExistsWithForeignSource(String ipAddress, String foreignSource) {
        Assert.notNull(ipAddress, "ipAddress cannot be null");
        if (foreignSource == null) {
            return queryInt("select count(ipInterface.id) from OnmsIpInterface as ipInterface " +
                    "join ipInterface.node as node " +
                    "where node.foreignSource is NULL " +
                    "and ipInterface.ipAddress = ? ", ipAddress) > 0;
        } else {
            return queryInt("select count(ipInterface.id) from OnmsIpInterface as ipInterface " +
                    "join ipInterface.node as node " +
                    "where node.foreignSource = ? " +
                    "and ipInterface.ipAddress = ? ", foreignSource, ipAddress) > 0;
        }
    }

}
