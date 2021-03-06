/* Copyright 2004, 2005, 2006 Acegi Technology Pty Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sample.contact;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.jdbc.object.SqlUpdate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import java.util.List;

import javax.sql.DataSource;


/**
 * Base implementation of {@link ContactDao} that uses Spring JDBC services.
 *
 * @author Ben Alex
 */
public class ContactDaoSpring extends JdbcDaoSupport implements ContactDao {
    //~ Instance fields ================================================================================================

    private ContactDelete contactDelete;
    private ContactInsert contactInsert;
    private ContactUpdate contactUpdate;
    private ContactsAllQuery contactsAllQuery;
    private ContactsByIdQuery contactsByIdQuery;
    private PrincipalsAllQuery principalsAllQuery;
    private RolesAllQuery rolesAllQuery;

    //~ Methods ========================================================================================================

    public void create(Contact contact) {
        contactInsert.insert(contact);
    }

    public void delete(Long contactId) {
        contactDelete.delete(contactId);
    }

    public List<Contact> findAll() {
        return contactsAllQuery.execute();
    }

    public List<String> findAllPrincipals() {
        return principalsAllQuery.execute();
    }

    public List<String> findAllRoles() {
        return rolesAllQuery.execute();
    }

    public Contact getById(Long id) {
        List<Contact> list = contactsByIdQuery.execute(id.longValue());

        if (list.size() == 0) {
            return null;
        } else {
            return (Contact) list.get(0);
        }
    }

    protected void initDao() throws Exception {
        contactInsert = new ContactInsert(getDataSource());
        contactUpdate = new ContactUpdate(getDataSource());
        contactDelete = new ContactDelete(getDataSource());
        contactsAllQuery = new ContactsAllQuery(getDataSource());
        principalsAllQuery = new PrincipalsAllQuery(getDataSource());
        rolesAllQuery = new RolesAllQuery(getDataSource());
        contactsByIdQuery = new ContactsByIdQuery(getDataSource());
    }

    public void update(Contact contact) {
        contactUpdate.update(contact);
    }

    //~ Inner Classes ==================================================================================================

    protected class AclObjectIdentityByObjectIdentityQuery extends MappingSqlQuery<Long> {
        protected AclObjectIdentityByObjectIdentityQuery(DataSource ds) {
            super(ds, "SELECT id FROM acl_object_identity WHERE object_identity = ?");
            declareParameter(new SqlParameter(Types.VARCHAR));
            compile();
        }

        protected Long mapRow(ResultSet rs, int rownum)
            throws SQLException {
            return new Long(rs.getLong("id"));
        }
    }

    protected class AclObjectIdentityInsert extends SqlUpdate {
        protected AclObjectIdentityInsert(DataSource ds) {
            super(ds, "INSERT INTO acl_object_identity VALUES (?, ?, ?, ?)");
            declareParameter(new SqlParameter(Types.BIGINT));
            declareParameter(new SqlParameter(Types.VARCHAR));
            declareParameter(new SqlParameter(Types.INTEGER));
            declareParameter(new SqlParameter(Types.VARCHAR));
            compile();
        }

        protected int insert(String objectIdentity, Long parentAclObjectIdentity, String aclClass) {
            Object[] objs = new Object[] {null, objectIdentity, parentAclObjectIdentity, aclClass};
            super.update(objs);

            return getJdbcTemplate().queryForInt("call identity()");
        }
    }

    protected class ContactDelete extends SqlUpdate {
        protected ContactDelete(DataSource ds) {
            super(ds, "DELETE FROM contacts WHERE id = ?");
            declareParameter(new SqlParameter(Types.BIGINT));
            compile();
        }

        protected void delete(Long contactId) {
            super.update(contactId.longValue());
        }
    }

    protected class ContactInsert extends SqlUpdate {
        protected ContactInsert(DataSource ds) {
            super(ds, "INSERT INTO contacts VALUES (?, ?, ?)");
            declareParameter(new SqlParameter(Types.BIGINT));
            declareParameter(new SqlParameter(Types.VARCHAR));
            declareParameter(new SqlParameter(Types.VARCHAR));
            compile();
        }

        protected void insert(Contact contact) {
            Object[] objs = new Object[] {contact.getId(), contact.getName(), contact.getEmail()};
            super.update(objs);
        }
    }

    protected class ContactUpdate extends SqlUpdate {
        protected ContactUpdate(DataSource ds) {
            super(ds, "UPDATE contacts SET contact_name = ?, address = ? WHERE id = ?");
            declareParameter(new SqlParameter(Types.VARCHAR));
            declareParameter(new SqlParameter(Types.VARCHAR));
            declareParameter(new SqlParameter(Types.BIGINT));
            compile();
        }

        protected void update(Contact contact) {
            Object[] objs = new Object[] {contact.getName(), contact.getEmail(), contact.getId()};
            super.update(objs);
        }
    }

    protected class ContactsAllQuery extends MappingSqlQuery<Contact> {
        protected ContactsAllQuery(DataSource ds) {
            super(ds, "SELECT id, contact_name, email FROM contacts ORDER BY id");
            compile();
        }

        protected Contact mapRow(ResultSet rs, int rownum) throws SQLException {
            Contact contact = new Contact();
            contact.setId(new Long(rs.getLong("id")));
            contact.setName(rs.getString("contact_name"));
            contact.setEmail(rs.getString("email"));

            return contact;
        }
    }

    protected class ContactsByIdQuery extends MappingSqlQuery<Contact> {
        protected ContactsByIdQuery(DataSource ds) {
            super(ds, "SELECT id, contact_name, email FROM contacts WHERE id = ? ORDER BY id");
            declareParameter(new SqlParameter(Types.BIGINT));
            compile();
        }

        protected Contact mapRow(ResultSet rs, int rownum) throws SQLException {
            Contact contact = new Contact();
            contact.setId(new Long(rs.getLong("id")));
            contact.setName(rs.getString("contact_name"));
            contact.setEmail(rs.getString("email"));

            return contact;
        }
    }

    protected class PermissionDelete extends SqlUpdate {
        protected PermissionDelete(DataSource ds) {
            super(ds, "DELETE FROM acl_permission WHERE ACL_OBJECT_IDENTITY = ? AND RECIPIENT = ?");
            declareParameter(new SqlParameter(Types.BIGINT));
            declareParameter(new SqlParameter(Types.VARCHAR));
            compile();
        }

        protected void delete(Long aclObjectIdentity, String recipient) {
            super.update(new Object[] {aclObjectIdentity, recipient});
        }
    }

    protected class PermissionInsert extends SqlUpdate {
        protected PermissionInsert(DataSource ds) {
            super(ds, "INSERT INTO acl_permission VALUES (?, ?, ?, ?);");
            declareParameter(new SqlParameter(Types.BIGINT));
            declareParameter(new SqlParameter(Types.BIGINT));
            declareParameter(new SqlParameter(Types.VARCHAR));
            declareParameter(new SqlParameter(Types.INTEGER));
            compile();
        }

        protected int insert(Long aclObjectIdentity, String recipient, Integer mask) {
            Object[] objs = new Object[] {null, aclObjectIdentity, recipient, mask};
            super.update(objs);

            return getJdbcTemplate().queryForInt("call identity()");
        }
    }

    protected class PrincipalsAllQuery extends MappingSqlQuery<String> {
        protected PrincipalsAllQuery(DataSource ds) {
            super(ds, "SELECT username FROM users ORDER BY username");
            compile();
        }

        protected String mapRow(ResultSet rs, int rownum) throws SQLException {
            return rs.getString("username");
        }
    }

    protected class RolesAllQuery extends MappingSqlQuery<String> {
        protected RolesAllQuery(DataSource ds) {
            super(ds, "SELECT DISTINCT authority FROM authorities ORDER BY authority");
            compile();
        }

        protected String mapRow(ResultSet rs, int rownum) throws SQLException {
            return rs.getString("authority");
        }
    }
}
