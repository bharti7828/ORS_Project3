
package in.co.rays.project_3.model;

import java.util.List;

import in.co.rays.project_3.dto.TradeDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;

public interface TradeModelInt {
		public long add(TradeDTO dto)throws ApplicationException,DuplicateRecordException;
		public void delete(TradeDTO dto)throws ApplicationException;
		public void update(TradeDTO dto)throws ApplicationException,DuplicateRecordException;
		public TradeDTO findByPK(long pk)throws ApplicationException;
		public TradeDTO findByLogin(String login)throws ApplicationException;
		public List list()throws ApplicationException;
		public List list(int pageNo,int pageSize)throws ApplicationException;
		public List search(TradeDTO dto,int pageNo,int pageSize)throws ApplicationException;
		public List search(TradeDTO dto)throws ApplicationException;
		public List getRoles(TradeDTO dto)throws ApplicationException;
		
	}
