package com.example.tourismAgency.business.rules;

import org.springframework.stereotype.Service;

import com.example.tourismAgency.business.constants.messages.Message;
import com.example.tourismAgency.core.utilities.exceptions.BusinessException;
import com.example.tourismAgency.dataAccess.abstracts.HostelTypeRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class HostelTypeBusinessRules {
	
	private HostelTypeRepository hostelTypeRepository;
	
	public void checkIfIdExists(int id) {
		if (!this.hostelTypeRepository.existsById(id)) {
			throw new BusinessException(Message.HostelTypeMessages.HOSTEL_TYPE_NOT_FOUND);
		}
		
	}

}
