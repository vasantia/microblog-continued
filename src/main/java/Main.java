import spark.ModelAndView;
import spark.Session;
import spark.Spark;
import spark.template.mustache.MustacheTemplateEngine;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vasantia on 7/11/16.
 */
public class Main {
    public static final String SESSION_USERNAME = "userName";
    static Map<String, User> users = new HashMap<>();

    public static void main(String[] args) {
        Spark.init();

        Spark.get("/", (request, response) -> {
            HashMap m = new HashMap();
            Session session = request.session();
            String name = session.attribute(SESSION_USERNAME);
            User user = users.get(name);

            if(user == null){
                return new ModelAndView(m, "index.html");
            }
            else {
                m.put("user", user);
                return new ModelAndView(m, "messages.html");
            }
        }, new MustacheTemplateEngine());

        Spark.get("/create-user", ((request, response) -> {
            HashMap m = new HashMap();
            return new ModelAndView(m, "index.html");
        }), new MustacheTemplateEngine());

        Spark.post("/create-user", (request, response) -> {
            String name = request.queryParams("submitName");
            String password = request.queryParams("submitPassword");
            User user = users.get(name);

            if(user == null) {
                user = new User(name, password);
                users.put(name, user);
                Session session = request.session();
                session.attribute(SESSION_USERNAME, name);
                response.redirect("/");
            }
            else if (user != null && request.queryParams("submitPassword").equals(user.getPassword())){
            Session session = request.session();
            session.attribute(SESSION_USERNAME, name);
            response.redirect("/");
            }
            else {
            response.redirect("/create-user");
            }

            return "";
        });

        Spark.get("/create-message", ((request, response) -> {
            HashMap m = new HashMap();
            return new ModelAndView(m, "messages.html");
        }), new MustacheTemplateEngine());

        Spark.post("/create-message", ((request, response) -> {
            Session session = request.session();
            String name = session.attribute(SESSION_USERNAME);
            User user = users.get(name);

            String submitMessage = request.queryParams("submitMessage");
            Message message = new Message(submitMessage);
            user.myMessages.add(message);
            response.redirect("/");
            return "";
        }));

        Spark.post("/delete-message", (request, response) -> {
            Session session = request.session();
            String name = session.attribute(SESSION_USERNAME);
            User user = users.get(name);

            int messageNum = Integer.valueOf(request.queryParams("messageNum"));
            user.myMessages.remove((messageNum-1));
            response.redirect("/");
            return "";
        });

        Spark.post("/edit-message", (request, response) -> {
            Session session = request.session();
            String name = session.attribute(SESSION_USERNAME);
            User user = users.get(name);

            int messageNum = Integer.valueOf(request.queryParams("messageNum"));
            String editMessage = request.queryParams("editMessage");
            user.myMessages.remove((messageNum-1));
            user.myMessages.add((messageNum-1), new Message(editMessage));
            response.redirect("/");
            return "";
        });

        Spark.post("/logout", (request, response) -> {
            request.session().invalidate();
            response.redirect("/");
            return "";
        });

    }
}
