package com.eli.calc.shape.service.ws.types;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.eli.calc.shape.domain.CalculationResult;

@XmlRootElement
public class CalculatedResultsResponse extends StatusResponse {

	private List<CalculationResult> calculatedResults;
	
	private int numCalculated;
	
	public CalculatedResultsResponse() {
		super();
	}
	
	public CalculatedResultsResponse(List<CalculationResult> calculatedResults) {

		super(StatusCode.SUCCESS,"Calculated Results");

		this.calculatedResults = calculatedResults;
		this.numCalculated = (null!=calculatedResults?calculatedResults.size():0);
	}

	public CalculatedResultsResponse(Exception e) {

		super("Calculated Results Error",e);

	}

	public List<CalculationResult> getCalculatedResults() {
		return calculatedResults;
	}

	public void setCalculatedResults(List<CalculationResult> calculatedResults) {
		this.calculatedResults = calculatedResults;
	}

	public int getNumCalculated() {
		return numCalculated;
	}

	public void setNumCalculated(int numCalculated) {
		this.numCalculated = numCalculated;
	}

	@Override
	public String toString() {
		return "CalculatedResultsResponse [calculatedResults=" + calculatedResults + ", numCalculated=" + numCalculated
				+ "]";
	}

}
