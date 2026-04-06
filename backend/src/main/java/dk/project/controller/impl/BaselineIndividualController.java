package dk.project.controller.impl;

import dk.project.entity.BaselineIndividual;
import dk.project.entity.SideEffect;
import dk.project.mapper.response.BaselineIndividualResponseMapper;
import dk.project.service.internal.BaselineIndividualService;
import dk.project.service.internal.EntityManagerService;
import dk.project.util.ContextHelper;
import dk.project.util.TryCatchHelper;
import io.javalin.http.Context;

public class BaselineIndividualController extends CRUDController<BaselineIndividual> {

    // Attributes
    private final BaselineIndividualService baselineIndividualService;

    // _________________________________________________________________________________________________________________

    public BaselineIndividualController(EntityManagerService<BaselineIndividual> service) {
        super(service, BaselineIndividual.class, BaselineIndividualResponseMapper::toDTO);
        this.baselineIndividualService = (BaselineIndividualService) service;
    }

    // _________________________________________________________________________________________________________________

    public void getSideEffects(Context ctx) {
        TryCatchHelper.tryCatchHelper(ctx, () -> {
            int id = ContextHelper.pathInt(ctx, "id");
            return baselineIndividualService.getSideEffects(id);
        }, "Side effects retrieved");
    }

    // _________________________________________________________________________________________________________________

    public void addSideEffect(Context ctx) {
        TryCatchHelper.tryCatchHelperVoid(ctx, () -> {
            int id = ContextHelper.pathInt(ctx, "id");
            SideEffect sideEffect = ctx.bodyAsClass(SideEffect.class);
            baselineIndividualService.addSideEffect(id, sideEffect);
        }, "SideEffect added");
    }

    // _________________________________________________________________________________________________________________

    public void removeSideEffect(Context ctx) {
        TryCatchHelper.tryCatchHelperVoid(ctx, () -> {
            int id = ContextHelper.pathInt(ctx, "id");
            SideEffect sideEffect = ctx.bodyAsClass(SideEffect.class);
            baselineIndividualService.removeSideEffect(id, sideEffect);
        }, "SideEffect removed");
    }

}