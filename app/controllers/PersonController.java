package controllers;

import models.OrderRepository;
import play.data.FormFactory;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

import static play.libs.Json.toJson;

/**
 * The controller keeps all database operations behind the repository, and uses
 * {@link play.libs.concurrent.HttpExecutionContext} to provide access to the
 * {@link play.mvc.Http.Context} methods like {@code request()} and {@code flash()}.
 */
public class PersonController extends Controller {

    private final FormFactory formFactory;
    private final OrderRepository personRepository;
    private final HttpExecutionContext ec;

    @Inject
    public PersonController(FormFactory formFactory, OrderRepository personRepository, HttpExecutionContext ec) {
        this.formFactory = formFactory;
        this.personRepository = personRepository;
        this.ec = ec;
    }

    public Result index(final Http.Request request) {
        return ok();
    }


    public CompletionStage<Result> getPersons() {
        return personRepository
                .list()
                .thenApplyAsync(personStream -> ok(toJson(personStream.collect(Collectors.toList()))), ec.current());
    }

}
