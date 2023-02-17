package com.example.tourismAgency.config.mapper;

import org.modelmapper.ModelMapper;

public interface MapperService {
	ModelMapper forResponse();
	ModelMapper forRequest();
}
