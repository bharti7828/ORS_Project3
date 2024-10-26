
package in.co.rays.project_3.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.project_3.dto.BaseDTO;
import in.co.rays.project_3.dto.TradeDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.model.ModelFactory;
import in.co.rays.project_3.model.TradeModelInt;
import in.co.rays.project_3.util.DataUtility;
import in.co.rays.project_3.util.DataValidator;
import in.co.rays.project_3.util.PropertyReader;
import in.co.rays.project_3.util.ServletUtility;


@WebServlet(name = "TradeCtl", urlPatterns = { "/ctl/TradeCtl" })
public class TradeCtl extends BaseCtl{
	
	
	@Override
	protected void preload(HttpServletRequest request) {

		Map<Integer, String> map = new HashMap();
		map.put(1, "Buy");
		map.put(2, "Sell");
		map.put(3, "All");
		
		request.setAttribute("transactionTypeeee", map);
		System.out.println(map);

	}

	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("userId"))) {
			request.setAttribute("userId", PropertyReader.getValue("error.require", "userId"));
			pass = false;
		}else 
		
		if (DataValidator.isAlphanumeric(request.getParameter("userId"))) {
			request.setAttribute("userId", "only Alphanumeric value Allowed");
			pass = false;
		}


		if (DataValidator.isNull(request.getParameter("startDate"))) {
			request.setAttribute("startDate", PropertyReader.getValue("error.require", " startDate"));

			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("endDate"))) {
			request.setAttribute("endDate", PropertyReader.getValue("error.require", " endDate"));

			pass = false;
		}
		
		
		if (DataValidator.isNull(request.getParameter("transactionType"))) {
			request.setAttribute("transactionType", PropertyReader.getValue("error.require", "transactionType"));
			pass = false;
		}

		return pass;

	}

	protected BaseDTO populateDTO(HttpServletRequest request) {
		TradeDTO dto = new TradeDTO();

		System.out.println(request.getParameter("dob"));

		dto.setId(DataUtility.getLong(request.getParameter("id")));
		dto.setUserId(DataUtility.getString(request.getParameter("userId")));
		dto.setStartDate(DataUtility.getDate(request.getParameter("startDate")));
		dto.setEndDate(DataUtility.getDate(request.getParameter("endDate")));
		dto.setTransactionType(DataUtility.getString(request.getParameter("transactionType")));
		

		populateBean(dto, request);

		return dto;

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String op = DataUtility.getString(request.getParameter("operation"));
		TradeModelInt model = ModelFactory.getInstance().getTradeModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (id > 0 || op != null) {
			TradeDTO dto;
			try {
				dto = model.findByPK(id);
				ServletUtility.setDto(dto, request);
			} catch (Exception e) {
				e.printStackTrace();
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		ServletUtility.forward(getView(), request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String op = DataUtility.getString(request.getParameter("operation"));
		TradeModelInt model = ModelFactory.getInstance().getTradeModel();

		long id = DataUtility.getLong(request.getParameter("id"));
		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {
			TradeDTO dto = (TradeDTO) populateDTO(request);
			try {
				if (id > 0) {
					model.update(dto);

					ServletUtility.setSuccessMessage("Data is successfully Update", request);
				} else {

					try {
						model.add(dto);
						ServletUtility.setDto(dto, request);
						ServletUtility.setSuccessMessage("Data is successfully saved", request);
					} catch (ApplicationException e) {
						ServletUtility.handleException(e, request, response);
						return;
					} catch (DuplicateRecordException e) {
						ServletUtility.setDto(dto, request);
						ServletUtility.setErrorMessage("Login id already exists", request);
					}

				}
				ServletUtility.setDto(dto, request);

			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setDto(dto, request);
				ServletUtility.setErrorMessage("Login id already exists", request);
			}
		} else if (OP_DELETE.equalsIgnoreCase(op)) {

			TradeDTO dto = (TradeDTO) populateDTO(request);
			try {
				model.delete(dto);
				ServletUtility.redirect(ORSView.TRADE_LIST_CTL, request, response);
				return;
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.TRADE_LIST_CTL, request, response);
			return;
		} else if (OP_RESET.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.TRADE_CTL, request, response);
			return;
		}
		ServletUtility.forward(getView(), request, response);

	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.TRADE_VIEW;
	}


}
