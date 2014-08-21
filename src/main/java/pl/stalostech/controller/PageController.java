package pl.stalostech.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.stalostech.rest.model.Menu;

/**
 * Main controller responsible for managin page.
 * @author Bartosz Wysocki
 */
@RestController
public class PageController {

    @RequestMapping("/menu")
    public List<Menu> menu() {

        List<Menu> r = new ArrayList<Menu>();
        r.add(new Menu(1, "Home"));

        return r;

    }

}
