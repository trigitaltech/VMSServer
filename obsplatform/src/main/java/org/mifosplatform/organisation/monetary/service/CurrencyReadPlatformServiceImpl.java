/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifosplatform.organisation.monetary.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import org.mifosplatform.infrastructure.core.domain.JdbcSupport;
import org.mifosplatform.infrastructure.core.service.TenantAwareRoutingDataSource;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.organisation.monetary.data.CurrencyData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service
public class CurrencyReadPlatformServiceImpl implements CurrencyReadPlatformService {

    private final JdbcTemplate jdbcTemplate;
    private final PlatformSecurityContext context;
    private final CurrencyMapper currencyRowMapper;

    @Autowired
    public CurrencyReadPlatformServiceImpl(final PlatformSecurityContext context, final TenantAwareRoutingDataSource dataSource) {
        this.context = context;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.currencyRowMapper = new CurrencyMapper();
    }

    @Override
    public Collection<CurrencyData> retrieveAllowedCurrencies() {

        context.authenticatedUser();

        final String sql = "select " + currencyRowMapper.schema() + " from m_organisation_currency c order by c.name";

        return this.jdbcTemplate.query(sql, currencyRowMapper, new Object[] {});
    }

    @Override
    public Collection<CurrencyData> retrieveAllPlatformCurrencies() {

        final String sql = "select " + currencyRowMapper.schema() + " from m_currency c order by c.name";

        return this.jdbcTemplate.query(sql, currencyRowMapper, new Object[] {});
    }

    private static final class CurrencyMapper implements RowMapper<CurrencyData> {

        @Override
        public CurrencyData mapRow(final ResultSet rs, @SuppressWarnings("unused") final int rowNum) throws SQLException {

            final String code = rs.getString("code");
            final String name = rs.getString("name");
            final int decimal_places = JdbcSupport.getInteger(rs, "decimal_places");
            final String display_symbol = rs.getString("display_symbol");
            final String internationalized_name_code = rs.getString("internationalized_name_code");
            final int ResourceId=rs.getInt("ResourceId");
            final int Type =rs.getInt("Type");

            return new CurrencyData(code, name, decimal_places, display_symbol, internationalized_name_code,ResourceId,Type);
        }

        public String schema() {
            return " c.code as code, c.name as name, c.decimal_places as decimal_places, c.display_symbol as display_symbol, c.internationalized_name_code as internationalized_name_code, c.ResourceId as ResourceId, c.Type as Type ";
        }
    }
}