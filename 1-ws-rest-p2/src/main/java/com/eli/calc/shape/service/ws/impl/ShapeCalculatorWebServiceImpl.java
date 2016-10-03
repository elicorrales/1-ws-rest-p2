package com.eli.calc.shape.service.ws.impl;

import java.util.List;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.eli.calc.shape.domain.CalculationRequest;
import com.eli.calc.shape.domain.CalculationResult;
import com.eli.calc.shape.model.CalcType;
import com.eli.calc.shape.model.ShapeName;
import com.eli.calc.shape.service.ShapeCalculatorService;
import com.eli.calc.shape.service.ws.ShapeCalculatorWebService;
import com.eli.calc.shape.service.ws.parms.QueueCalculationParms;
import com.eli.calc.shape.service.ws.types.CalculatedResultsResponse;
import com.eli.calc.shape.service.ws.types.PendingRequestsResponse;
import com.eli.calc.shape.service.ws.types.RunPendingRequestsResponse;
import com.eli.calc.shape.service.ws.types.StatusCode;
import com.eli.calc.shape.service.ws.types.StatusResponse;

public class ShapeCalculatorWebServiceImpl implements ShapeCalculatorWebService {


	private static final Logger logger = LoggerFactory.getLogger(ShapeCalculatorWebServiceImpl.class);

	@Autowired
	private ShapeCalculatorService calculator;
	
	public ShapeCalculatorWebServiceImpl() {
		logger.debug("\n\n\nConstructor\n\n\n");
	}

	@Override
	public StatusResponse deletePendingRequests() {

		logger.debug("\n\n\nExecuting operation deletePendingRequests...\n\n\n");

		StatusResponse response = null;
		try {
			
			calculator.deleteAllPendingRequests();
			response = new StatusResponse(StatusCode.SUCCESS,"Requests Deleted");

		} catch (Exception e) {
			logger.error("Error attempting to deletePendingRequests",e);
			response = new StatusResponse("Error Attempting to Delete Requests",e);
		}
		
		return response;
	}

	@Override
	public StatusResponse deleteResults() {

		logger.debug("\n\n\nExecuting operation deleteResults...\n\n\n");

		StatusResponse response = null;
		try {
			
			calculator.deleteAllResults();
			response = new StatusResponse(StatusCode.SUCCESS,"Results Deleted");

		} catch (Exception e) {
			logger.error("Error attempting to deleteResults",e);
			response = new StatusResponse("Error Attempting to Delete Results",e);
		}
		
		return response;
	}

	@Override
	public StatusResponse queueCalculation(QueueCalculationParms parameters) {

		logger.debug("\n\n\nExecuting operation queueCalculationRequest:"
				+parameters.getShapeName()+","
				+parameters.getCalcType()+","+
				parameters.getDimension()+" ...\n\n\n");

		StatusResponse response = null;
		try {
			
			calculator.queueCalculationRequest(
				parameters.getShapeName(), parameters.getCalcType(), parameters.getDimension()
					);
			response = new StatusResponse(
					StatusCode.SUCCESS,
					"Request queued:"
					+parameters.getShapeName()+","
					+parameters.getCalcType()+","+
					parameters.getDimension());

		} catch (Exception e) {
			logger.error("Error attempting to queueCalculationRequest",e);
			response = new StatusResponse("Error Attempting to Queue Calculation Request",e);
		}
		
		return response;
	}

	@Override
	public PendingRequestsResponse getPendingRequests() {

		logger.debug("\n\n\nExecuting operation getPendingRequests...\n\n\n");

		PendingRequestsResponse response = null;
		try {
			
			List<CalculationRequest> list = calculator.getAllPendingRequests();
			response = new PendingRequestsResponse(list);

		} catch (Exception e) {
			logger.error("Error attempting to getPendingRequests",e);
			response = new PendingRequestsResponse(e);
		}
		
		return response;
	}

	@Override
	public CalculatedResultsResponse getCalculatedResults() {

		logger.debug("\n\n\nExecuting operation getCalculatedResults...\n\n\n");

		CalculatedResultsResponse response = null;
		try {
			
			List<CalculationResult> list = calculator.getAllCalculatedResults();
			response = new CalculatedResultsResponse(list);

		} catch (Exception e) {
			logger.error("Error attempting to getCalculatedResults",e);
			response = new CalculatedResultsResponse(e);
		}
		
		return response;
	}

	@Override
	public RunPendingRequestsResponse runPendingRequestsStopOnError() {

		logger.debug("\n\n\nExecuting operation runPendingRequestsStopOnError...\n\n\n");

		RunPendingRequestsResponse response = null;
		try {
			
			int numRun = calculator.runAllPendingRequestsStopOnError();
			response = new RunPendingRequestsResponse(numRun);

		} catch (Exception e) {
			logger.error("Error attempting to runPendingRequestsStopOnError",e);
			response = new RunPendingRequestsResponse(e);
		}
		
		return response;
	}

	@Override
	public RunPendingRequestsResponse runPendingRequestsNoStopOnError() {

		logger.debug("\n\n\nExecuting operation runPendingRequestsNoStopOnError ...\n\n\n");

		RunPendingRequestsResponse response = null;
		try {
			
			int numRun = calculator.runAllPendingRequestsNoStopOnError();
			response = new RunPendingRequestsResponse(numRun);

		} catch (Exception e) {
			logger.error("Error attempting to runPendingRequestsNoStopOnError",e);
			response = new RunPendingRequestsResponse(e);
		}
		
		return response;
	}

}
