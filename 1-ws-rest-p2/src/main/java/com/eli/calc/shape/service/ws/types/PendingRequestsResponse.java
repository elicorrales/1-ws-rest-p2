package com.eli.calc.shape.service.ws.types;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.eli.calc.shape.domain.CalculationRequest;

@XmlRootElement
public class PendingRequestsResponse extends StatusResponse {

	private List<CalculationRequest> pendingRequests;
	
	private int numPending;
	
	public PendingRequestsResponse() {
		super();
	}
	
	public PendingRequestsResponse(List<CalculationRequest> pendingRequests) {

		super(StatusCode.SUCCESS,"Pending Requests");

		this.pendingRequests = pendingRequests;
		this.numPending = (null!=pendingRequests?pendingRequests.size():0);
	}

	public PendingRequestsResponse(Exception e) {

		super("Pending Requests Error",e);

	}

	public List<CalculationRequest> getPendingRequests() {
		return pendingRequests;
	}

	public void setPendingRequests(List<CalculationRequest> pendingRequests) {
		this.pendingRequests = pendingRequests;
	}

	public int getNumPending() {
		return numPending;
	}

	public void setNumPending(int numPending) {
		this.numPending = numPending;
	}

	
}
