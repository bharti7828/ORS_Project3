
package in.co.rays.project_3.model;

import java.util.List;

import in.co.rays.project_3.dto.OwnerDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;

public interface OwnerModelInt {
	
	public long add(OwnerDTO dto)throws ApplicationException,DuplicateRecordException;
	public void delete(OwnerDTO dto)throws ApplicationException;
	public void update(OwnerDTO dto)throws ApplicationException,DuplicateRecordException;
	public OwnerDTO findByPK(long pk)throws ApplicationException;
	public OwnerDTO findByLogin(String login)throws ApplicationException;
	public List list()throws ApplicationException;
	public List list(int pageNo,int pageSize)throws ApplicationException;
	public List search(OwnerDTO dto,int pageNo,int pageSize)throws ApplicationException;
	public List search(OwnerDTO dto)throws ApplicationException;
	public List getRoles(OwnerDTO dto)throws ApplicationException;
	



}