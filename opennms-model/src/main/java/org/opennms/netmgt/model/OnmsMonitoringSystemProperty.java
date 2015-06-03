/*******************************************************************************
 * This file is part of OpenNMS(R).
 *
 * Copyright (C) 2014-2015 The OpenNMS Group, Inc.
 * OpenNMS(R) is Copyright (C) 1999-2015 The OpenNMS Group, Inc.
 *
 * OpenNMS(R) is a registered trademark of The OpenNMS Group, Inc.
 *
 * OpenNMS(R) is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 *
 * OpenNMS(R) is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with OpenNMS(R).  If not, see:
 *      http://www.gnu.org/licenses/
 *
 * For more information contact:
 *     OpenNMS(R) Licensing <license@opennms.org>
 *     http://www.opennms.org/
 *     http://www.opennms.com/
 *******************************************************************************/

package org.opennms.netmgt.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlValue;

/**
 * @hibernate.class table="monitoringSystemsProperties"
 */
@Table(name="monitoringSystemsProperties", uniqueConstraints = {
        @UniqueConstraint(columnNames={"id", "key"})
})
@Entity
@XmlRootElement(name="property")
@XmlAccessorType(XmlAccessType.NONE)
public class OnmsMonitoringSystemProperty implements Serializable {

    private static final long serialVersionUID = 302023546020673864L;

    @XmlAttribute(name="id", required=true)
    private Integer m_id;

    /*
    @XmlIDREF
    @XmlAttribute(name="minion-id")
    */
    @XmlTransient
    private OnmsMonitoringSystem m_system;

    @XmlAttribute(name="key")
    private String m_key;

    @XmlValue
    private String m_value;

    public OnmsMonitoringSystemProperty() {}

    public OnmsMonitoringSystemProperty(final OnmsMonitoringSystem minion, final String key, final String value) {
        m_system = minion;
        m_key = key;
        m_value = value;
    }

    @Id
    @SequenceGenerator(name="monitoringSystemPropertySequence", sequenceName="opennmsNxtId")
    @GeneratedValue(generator="monitoringSystemPropertySequence")
    @Column(name="id", nullable=false, unique=true)
    public final Integer getId() {
        return m_id;
    }

    public final void setId(final Integer id) {
        m_id = id;
    }

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="systemid")
    public final OnmsMonitoringSystem getMonitoringSystem() {
        return m_system;
    }

    public final void setMonitoringSystem(final OnmsMonitoringSystem system) {
        m_system = system;
    }

    @Column(name="key", nullable=false)
    public final String getKey() {
        return m_key;
    }

    public final void setKey(final String key) {
        m_key = key;
    }

    @Column(name="value")
    public final String getValue() {
        return m_value;
    }

    public final void setValue(final String value) {
        m_value = value;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " [id=" + m_id + ", system=" + m_system.getId() + ", key=" + m_key + ", value=" + m_value + "]";
    }
}