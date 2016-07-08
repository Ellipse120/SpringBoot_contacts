package contacts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by lusai on 2016/7/8.
 */
@Repository
public class ContactRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ContactRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Contact> findAll(){
        return jdbcTemplate.query(
                "select id, firstName, lastName, phoneNumber, emailAddress " +
                        "from contacts order by lastName",
                new RowMapper<Contact>() {
                    @Override
                    public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Contact contact = new Contact();
                        contact.setId(rs.getLong(1));
                        contact.setFirstName(rs.getString(2));
                        contact.setLastName(rs.getString(3));
                        contact.setPhoneNumber(rs.getString(4));
                        contact.setEmailAddress(rs.getString(5));
                        return contact;
                    }
                }
        );
    }

    public void save(Contact contact) {
        jdbcTemplate.update(
                "INSERT INTO contacts " +
                        "(firstName, lastName, phoneNumber, emailAddress) " +
                        "VALUES (?,?,?,?)",
                contact.getFirstName(), contact.getLastName(),
                contact.getPhoneNumber(), contact.getEmailAddress()
        );
    }

}
