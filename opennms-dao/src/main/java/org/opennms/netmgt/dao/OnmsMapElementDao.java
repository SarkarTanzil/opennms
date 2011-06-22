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
package org.opennms.netmgt.dao;

import org.opennms.netmgt.model.OnmsMap;
import org.opennms.netmgt.model.OnmsMapElement;

import java.util.Collection;

/**
 * <p>OnmsMapElementDao interface.</p>
 */
public interface OnmsMapElementDao extends OnmsDao<OnmsMapElement, Integer> {
    /**
     * <p>findAll</p>
     *
     * @param offset a {@link java.lang.Integer} object.
     * @param limit a {@link java.lang.Integer} object.
     * @return a {@link java.util.Collection} object.
     */
    Collection<OnmsMapElement> findAll(Integer offset, Integer limit);
    /**
     * <p>findElementById</p>
     *
     * @param id a int.
     * @return a {@link org.opennms.netmgt.model.OnmsMapElement} object.
     */
    OnmsMapElement findElementById(int id);
    /**
     * <p>findElement</p>
     *
     * @param elementId a int.
     * @param type a {@link java.lang.String} object.
     * @param map a {@link org.opennms.netmgt.model.OnmsMap} object.
     * @return a {@link org.opennms.netmgt.model.OnmsMapElement} object.
     */
    OnmsMapElement findElement(int elementId, String type, OnmsMap map);
    /**
     * <p>findElementsByMapId</p>
     *
     * @param map a {@link org.opennms.netmgt.model.OnmsMap} object.
     * @return a {@link java.util.Collection} object.
     */
    Collection<OnmsMapElement> findElementsByMapId(OnmsMap map);
    /**
     * <p>findElementsByNodeId</p>
     *
     * @param nodeId a int.
     * @return a {@link java.util.Collection} object.
     */
    Collection<OnmsMapElement> findElementsByNodeId(int nodeId);
    /**
     * <p>findElementsByElementIdAndType</p>
     *
     * @param elementId a int.
     * @param type a {@link java.lang.String} object.
     * @return a {@link java.util.Collection} object.
     */
    Collection<OnmsMapElement> findElementsByElementIdAndType(int elementId, String type);
    /**
     * <p>findElementsByMapIdAndType</p>
     *
     * @param mapId a int.
     * @param type a {@link java.lang.String} object.
     * @return a {@link java.util.Collection} object.
     */
    Collection<OnmsMapElement> findElementsByMapIdAndType(int mapId, String type);
    /**
     * <p>findElementsByType</p>
     *
     * @param type a {@link java.lang.String} object.
     * @return a {@link java.util.Collection} object.
     */
    Collection<OnmsMapElement> findElementsByType(String type);
    /**
     * <p>deleteElementsByMapId</p>
     *
     * @param map a {@link org.opennms.netmgt.model.OnmsMap} object.
     */
    void deleteElementsByMapId(OnmsMap map);
    /**
     * <p>findMapElementsOnMap</p>
     *
     * @param mapId a int.
     * @return a {@link java.util.Collection} object.
     */
    Collection<OnmsMapElement> findMapElementsOnMap(int mapId);
    /**
     * <p>findNodeElementsOnMap</p>
     *
     * @param mapId a int.
     * @return a {@link java.util.Collection} object.
     */
    Collection<OnmsMapElement> findNodeElementsOnMap(int mapId);
    /**
     * <p>deleteElementsByNodeid</p>
     *
     * @param nodeId a int.
     */
    void deleteElementsByNodeid(int nodeId);
    /**
     * <p>deleteElementsByType</p>
     *
     * @param type a {@link java.lang.String} object.
     */
    void deleteElementsByType(String type);
    /**
     * <p>deleteElementsByElementIdAndType</p>
     *
     * @param elementId a int.
     * @param type a {@link java.lang.String} object.
     */
    void deleteElementsByElementIdAndType(int elementId, String type);
    /**
     * <p>deleteElementsByMapType</p>
     *
     * @param mapType a {@link java.lang.String} object.
     */
    void deleteElementsByMapType(String mapType);
    /**
     * <p>countElementsOnMap</p>
     *
     * @param mapid a int.
     * @return a int.
     */
    int countElementsOnMap(int mapid);
}
