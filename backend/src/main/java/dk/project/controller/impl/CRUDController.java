package dk.project.controller.impl;

import dk.project.service.internal.EntityManagerService;
import dk.project.util.ContextHelper;
import io.javalin.http.Context;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class CRUDController <T> {

    // Attributes
    protected final EntityManagerService<T> classService;
    protected final Class<T> classSpecific;
    private final Function<T, Object> classMapper;

    // _________________________________________________________________________________________________________________

    protected CRUDController(EntityManagerService<T> classService, Class<T> classSpecific, Function<T, Object> classMapper) {
        this.classService = classService;
        this.classSpecific = classSpecific;
        this.classMapper = classMapper;
    }

    // _________________________________________________________________________________________________________________

    public void create(Context ctx) {
        try {
            T entity = ctx.bodyAsClass(classSpecific);
            T created = classService.create(entity);
            ctx.json(classMapper.apply(created));
        } catch (Exception e) {
            e.printStackTrace(); // debug
            throw new dk.project.exception.ApiException(500, e.getMessage());
        }
    }

    // _________________________________________________________________________________________________________________

    public void update(Context ctx) {
        T entity = ctx.bodyValidator(classSpecific)
                .check(e -> e != null, classSpecific.getSimpleName() + " can't be null")
                .get();
        T updated = classService.update(entity);
        ctx.json(classMapper.apply(updated));
    }

    // _________________________________________________________________________________________________________________

    public void updateById(Context ctx) {
        String idStr = ctx.pathParam("id");
        T entity = ContextHelper.notNull(
                classService.getById(idStr),
                classSpecific.getSimpleName()
        );
        T updated = classService.update(entity);
        ctx.json(classMapper.apply(updated));
    }

    // _________________________________________________________________________________________________________________

    public void getById(Context ctx) {
        String idStr = ctx.pathParam("id");
        UUID uuid = UUID.fromString(idStr);
        T entity = ContextHelper.notNull(
                classService.getById(uuid),
                classSpecific.getSimpleName()
        );
        ctx.json(classMapper.apply(entity));
    }

    // _________________________________________________________________________________________________________________

    public void getAll(Context ctx) {
        List<Object> result = classService.getAll().stream()
                .map(classMapper)
                .collect(Collectors.toList());
        ctx.json(result);
    }

    // _________________________________________________________________________________________________________________

    public void deleteById(Context ctx) {
        String idStr = ctx.pathParam("id");
        UUID uuid = UUID.fromString(idStr);
        T entity = ContextHelper.notNull(
                classService.deleteById(uuid),
                classSpecific.getSimpleName()
        );
        ctx.json(classMapper.apply(entity));
    }

    // _________________________________________________________________________________________________________________

    public void deleteAll(Context ctx) {
        int deleted = classService.deleteAll();
        ctx.json(deleted);
    }

    // _________________________________________________________________________________________________________________

    public void deleteAllSafe(Context ctx) {
        int deleted = classService.deleteAllSafe();
        ctx.json(deleted);
    }

}