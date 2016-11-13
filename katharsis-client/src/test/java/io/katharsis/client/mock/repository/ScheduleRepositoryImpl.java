package io.katharsis.client.mock.repository;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import io.katharsis.client.mock.models.Schedule;
import io.katharsis.queryspec.QuerySpec;
import io.katharsis.queryspec.QuerySpecLinksRepository;
import io.katharsis.queryspec.QuerySpecMetaRepository;
import io.katharsis.queryspec.QuerySpecResourceRepositoryBase;
import io.katharsis.response.LinksInformation;
import io.katharsis.response.MetaInformation;

public class ScheduleRepositoryImpl extends QuerySpecResourceRepositoryBase<Schedule, Long>
		implements ScheduleRepository, QuerySpecMetaRepository<Schedule>, QuerySpecLinksRepository<Schedule> {

	private static Map<Long, Schedule> schedules = new HashMap<>();

	public ScheduleRepositoryImpl() {
		super(Schedule.class);
	}

	@Override
	@GET
	@Path("repositoryAction")
	public String repositoryAction(@QueryParam(value = "msg") String msg) {
		return "repository action: " + msg;
	}

	@Override
	@GET
	@Path("{id}/resourceAction")
	public Response resourceAction(@PathParam("id") String msg) {
		String result = "Restful example : " + msg;
		return Response.status(200).entity(result).build();
	}

	@Override
	public Iterable<Schedule> findAll(QuerySpec querySpec) {
		return querySpec.apply(schedules.values());
	}

	@Override
	public <S extends Schedule> S save(S entity) {
		schedules.put(entity.getId(), entity);
		return null;
	}

	@Override
	public void delete(Long id) {
		schedules.remove(id);
	}

	@Override
	public LinksInformation getLinksInformation(Iterable<Schedule> resources, QuerySpec queryParams) {
		return new LinksInformation() {

			public String name = "value";
		};
	}

	@Override
	public MetaInformation getMetaInformation(Iterable<Schedule> resources, QuerySpec queryParams) {
		return new MetaInformation() {

			public String name = "value";
		};
	}

	public static void clear() {
		schedules.clear();
	}
}