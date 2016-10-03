package com.eli.calc.shape.service.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.eli.calc.shape.service.ws.parms.QueueCalculationParms;
import com.eli.calc.shape.service.ws.types.CalculatedResultsResponse;
import com.eli.calc.shape.service.ws.types.PendingRequestsResponse;
import com.eli.calc.shape.service.ws.types.RunPendingRequestsResponse;
import com.eli.calc.shape.service.ws.types.StatusResponse;

//@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//@Produces(MediaType.APPLICATION_XML)
//@Consumes(MediaType.APPLICATION_XML)
public interface ShapeCalculatorWebService {

	@DELETE
	@Path("/pending")
	StatusResponse deletePendingRequests();
	
	@DELETE
	@Path("/results")
	StatusResponse deleteResults();
	
	/**
	 * Since executing this operation results in a pending request,
	 * let's use the same /pending endpoint.
	 * 
	 * @param parameters
	 * @return StatusReponse
	 */
	@PUT
	@Path("/pending")
    StatusResponse queueCalculation(QueueCalculationParms parameters);

    @GET
    @Path("/pending")
	PendingRequestsResponse getPendingRequests();
	
    @GET
    @Path("/results")
	CalculatedResultsResponse getCalculatedResults();
	
    @PUT
    @Path("/run/stop")
	RunPendingRequestsResponse runPendingRequestsStopOnError();

    @PUT
    @Path("/run/nostop")
	RunPendingRequestsResponse runPendingRequestsNoStopOnError();
	
}

//			@XmlElement(name="ShapeName", required=true) ShapeName shapeName, 
//			@XmlElement(name="CalcType", required=true) CalcType calcType, 
//			@XmlElement(name="Dimension", required=true) double dimension);