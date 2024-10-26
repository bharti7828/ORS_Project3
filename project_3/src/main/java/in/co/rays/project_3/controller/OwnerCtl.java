package in.co.rays.project_3.controller;



import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.project_3.dto.BaseDTO;
import in.co.rays.project_3.dto.OwnerDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.model.ModelFactory;
import in.co.rays.project_3.model.OwnerModelInt;
import in.co.rays.project_3.util.DataUtility;
import in.co.rays.project_3.util.DataValidator;
import in.co.rays.project_3.util.PropertyReader;
import in.co.rays.project_3.util.ServletUtility;

@WebServlet(name = "OwnerCtl", urlPatterns = { "/ctl/OwnerCtl" })

public class OwnerCtl extends BaseCtl{

	
	@Override
	protected void preload(HttpServletRequest request) {
		
		HashMap map = new HashMap();
		map.put("MP04CC5326", "MP04CC5326");
		map.put("MP09HK1238", "MP09HK1238");
		map.put("MP17SD8203", "MP17SD8203");
		map.put("MP11CC6126", "MP11CC6126");
		map.put("MP13CC5922", "MP13CC5922");
		map.put("MP02SD9409", "MP02SD9409");
		map.put("MP10CC0043", "MP10CC0043");

		
		
		request.setAttribute("vehicleIdd", map);
		
		
		HashMap map1 = new HashMap();
		map1.put("30/03/2001", "30/03/2001");
		map1.put("12/07/2004", "12/07/2004");
		map1.put("07/07/2008", "07/07/2008");
		
		request.setAttribute("dobb", map1);
		

		
		 	}
	
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", PropertyReader.getValue("error.require", "name"));
			pass = false;
		}
		 else if (DataValidator.isTooLong(request.getParameter("name"),20)) {
				request.setAttribute("name", "name must contain max 20 charactor  ");
				pass = false;
			}
		

		
			

		if (DataValidator.isNull(request.getParameter("dob"))) {
			request.setAttribute("dob", PropertyReader.getValue("error.require", " dob"));

			pass = false;
		

		}
		if (DataValidator.isNull(request.getParameter("insuranceAmount"))) {
			request.setAttribute("insuranceAmount", PropertyReader.getValue("error.require", "insuranceAmount"));
			pass = false;
		}

		

				  
		
		if (DataValidator.isNull(request.getParameter("vehicleId"))) {
			request.setAttribute("vehicleId", PropertyReader.getValue("error.require", "vehicleId"));
			pass = false;
		}

		

		 

		return pass;

	}

	protected BaseDTO populateDTO(HttpServletRequest request) {
		OwnerDTO dto = new OwnerDTO();


		dto.setId(DataUtility.getLong(request.getParameter("id")));
		dto.setName(DataUtility.getString(request.getParameter("name")));
		dto.setInsuranceAmount(DataUtility.getInt(request.getParameter("insuranceAmount")));
		dto.setVehicleId(DataUtility.getString(request.getParameter("vehicleId")));

        dto.setDob(DataUtility.getDate(request.getParameter("dob")));



		populateBean(dto, request);

		return dto;

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String op = DataUtility.getString(request.getParameter("operation"));
		OwnerModelInt model = ModelFactory.getInstance().getOwnerModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (id > 0 || op != null) {
			OwnerDTO dto;
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
		OwnerModelInt model = ModelFactory.getInstance().getOwnerModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {
			OwnerDTO dto = (OwnerDTO) populateDTO(request);
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

			OwnerDTO dto = (OwnerDTO) populateDTO(request);
			try {
				model.delete(dto);
				ServletUtility.redirect(ORSView.OWNER_LIST_CTL, request, response);
				return;
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.OWNER_LIST_CTL, request, response);
			return;
		} else if (OP_RESET.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.OWNER_CTL, request, response);
			return;
		}
		ServletUtility.forward(getView(), request, response);

	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.OWNER_VIEW;
	}

}