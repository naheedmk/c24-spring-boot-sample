import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * Created by iainporter on 26/06/2014.
 */
@Controller
public class DataLoaderController {


    @Autowired
    private DataSource dataSource;

    private JdbcTemplate template;

    @PostConstruct
    private void init() {
        template = new JdbcTemplate(dataSource);
    }

    @RequestMapping(value="/", method= RequestMethod.GET)
    public String index(Model model) {

        List<Map<String, Object>> messages = template.queryForList("select * from Messages;");
        model.addAttribute("messages", messages);
        return "index";
    }

}
