package com.example.tourismAgency.business.rules;

import org.springframework.stereotype.Service;

import com.example.tourismAgency.business.constants.messages.Message;
import com.example.tourismAgency.core.utilities.exceptions.BusinessException;
import com.example.tourismAgency.dataAccess.abstracts.FacilityRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FacilityBusinessRules {

	private FacilityRepository facilityRepository;

	public void existsFacilityByName(String name) {
		if (this.facilityRepository.existsFacilityByNameEquals(name)) {
			throw new BusinessException(Message.FacilityMessages.FACILITY_ALREADY_EXISTS);
		}

	}

}
