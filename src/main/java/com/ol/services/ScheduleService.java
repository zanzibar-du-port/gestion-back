package com.ol.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.ol.models.utils.UtilsLocalDateParHeure;
import com.ol.repositories.utils.UtilsLocalDateParHeureRepository;

@Service
public class ScheduleService {

	@Autowired
	UtilsLocalDateParHeureRepository utilsLocalDateParHeureRepository;
	
	@Scheduled(cron = "0 0 2 * * ?")
	public void ajoutQuotidienUtilsLocalDateParHeure() {
		LocalDateTime localDateTimeNow = LocalDateTime.now();
		for (int i = 0; i < 24; i++) {
			if (!utilsLocalDateParHeureRepository.findByLocalDateHeure(
							LocalDateTime.now().of(
									localDateTimeNow.getYear(),
									localDateTimeNow.getMonth(),
									localDateTimeNow.getDayOfMonth(),
									i, 
									0)).isPresent()) {
				utilsLocalDateParHeureRepository.save(
						new UtilsLocalDateParHeure(
								LocalDateTime.now().of(
										localDateTimeNow.getYear(),
										localDateTimeNow.getMonth(),
										localDateTimeNow.getDayOfMonth(),
										i, 
										0)));
			}
			
		}
	}
	
	
}
